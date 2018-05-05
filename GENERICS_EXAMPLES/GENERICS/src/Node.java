package generics_example;

public class Node<D> {
	
	Node next;
	D data;
	
	public Node(D newData) {
		data = newData;
		next = null;
	}
	
	//<D> not required in constructor for Node
	public Node(D newData, Node nextNode) {
		data = newData;
		next = nextNode;
	}
	
	// make sure to put the <D> in the Node 
	public Node<D> getNext() {
		return next;
	}
	
	// make sure to put the <D> in the Node
	public void setNext(Node<D> nextNode) {
		next = nextNode;
	}

}
