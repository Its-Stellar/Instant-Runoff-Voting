package DataStructures.Stacks;

public class LinkedListStack<E> implements Stack<E> {

	private int currentSize;
	private Node header;
	
	private class Node {
		private E value;
		private Node next;

		public Node(E value, Node next) {
			this.value = value;
			this.next = next;
		}

		public Node(E value) {
			this.value = value;
			this.next = null;
		}
		
		public Node() {
			this(null, null);
		}

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
		
		public void clear() {
			value = null;
			next = null;
		}


	}
	public LinkedListStack() {
		currentSize = 0;
		header =  new Node();
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public void push(E e) {
		Node newNode = new Node(e, header.getNext());
		header.setNext(newNode);
		currentSize++;
	}

	@Override
	public E pop() {
		if(isEmpty())
			return null;
		Node rmNode = header.getNext();
		E result = rmNode.getValue();
		header.setNext(rmNode.getNext());
		rmNode.clear();
		currentSize--;
		return result;
		
	}

	@Override
	public E top() {
		if(isEmpty())
			return null;
		return header.getNext().getValue();
	}

	@Override
	public void clear() {
		while(!isEmpty()) {
			pop();
		}
	}

}
