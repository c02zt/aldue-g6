package A02_Queue;

import A01_Stack.Node;

public class Queue<T>
{
    private Node<T> first;
    
    private Node<T> last;
    /**
     * Das vorderste (=erste) Element aus der Queue entfernen und zurückliefern.
     * Existiert kein Element, wird eine Exception ausgelöst.
     * @throws QueueEmptyException 
     */
    public T dequeue() throws QueueEmptyException {
    	if (first == null) {
    		throw new QueueEmptyException();
    	}
    	T data = first.getData();
    	if (first.equals(last)) {
    		first = null;
    		last = null;
    	} else {
    		first = first.getNext();
    	}
    	return data;
    }
    
    
    
    /**
     * Übergebenen Integer am Ende der Queue anhängen.
     * @param i Zahl
     */
    public void enqueue(T i) {
    	Node<T> newNode = new Node<>(i);
    	if (last == null) {
    		last = newNode;
    		first = last;
    	} else {
    		last.setNext(newNode);
    		last = newNode;
    	}
    }
    
    /**
     * Liefert die Anzahl der Elemente in der Queue
     * @return
     */
    public int getCount() {
		if (first == null) {
			return 0;
		}
		Node<T> currentNode = first;
		int count = 1;
		while (!currentNode.equals(last)) {
			currentNode = currentNode.getNext();
			count++;
		}
		return count;
    }
}
