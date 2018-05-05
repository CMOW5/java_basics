package generics_example;

import java.util.LinkedList;

public class Queuey<D> {
	
	LinkedList<D> queue;
	
	public Queuey() {
		queue = new LinkedList<>();
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public int size() {
		return queue.size();
	}
	
	public void enqueue(D n) {
		queue.addLast(n);
	}
	
	public D dequeue() {
		return queue.remove(0);
	}
	
	public D peek() {
		return queue.get(0);
	}
	
}
