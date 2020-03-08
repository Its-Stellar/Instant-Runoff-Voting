package DataStructures.Stacks;

public class ArrayStack<E> implements Stack<E>{
	
	private int top;
	private E[] elements;
	
	
	@SuppressWarnings("unchecked")
	public ArrayStack(int initialCapacity) {
		
		if (initialCapacity < 1) {
			throw new IllegalArgumentException();
		}
		
		elements  = (E[]) new Object[initialCapacity];
		top = 0;
		
	}

	@Override
	public int size() {
		return top;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public void push(E e) {
		
		if (size() == elements.length) {
			reAllocate();
		}
		
		elements[top++] = e;
		
	}

	@SuppressWarnings("unchecked")
	private void reAllocate() {
		E[] newArr = (E[]) new Object[size() *2];
		for (int i = 0; i < size(); i++) {
			newArr[i] = elements[i];
		}
		elements = newArr;
	}
	

	@Override
	public E pop() {
		if (isEmpty()) {
			return null;
		}
		E result = elements[--top];
		elements[top] = null;
		return result;
	}

	@Override
	public E top() {
		if(isEmpty())
			return null;
		
		return elements[--top];
	}

	@Override
	public void clear() {
		
		while(!isEmpty()) {
			pop();
		}
	}
	
}
