package DataStructures.List;

public interface ReverseIterator<E> {
	public boolean hasPrev();
	public E prev();
	public void remove();
}
