package swat.algorithms.collection;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Sujal
 */
public class StackWithOneQueue<T> {

	Queue<T> queue;

	public StackWithOneQueue() {
		queue = new LinkedList<T>();
	}

	public void push(T element) {
		queue.add(element);
	}

	public T pop() {
		for(int i=1 ; i<queue.size() ; i++) {
			queue.add(queue.remove());
		}
		return queue.remove();
	}

	public int size() {
		return queue.size();
	}
}