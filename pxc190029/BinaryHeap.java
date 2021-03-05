// Starter code for SP9

// Change to your netid
package pxc190029;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class BinaryHeap<T extends Comparable<? super T>> {
    Comparable[] pq;
    int size;

    // Constructor for building an empty priority queue using natural ordering of T
    public BinaryHeap(int maxCapacity) {
        pq = new Comparable[maxCapacity];
        size = 0;
    }

    // add method: resize pq if needed
    public boolean add(T x) {
	    size++;
	    pq[size - 1] = x;
	    percolateUp(size - 1);
        return true;
    }

    public boolean offer(T x) {
	return add(x);
    }

    // throw exception if pq is empty
    public T remove() throws NoSuchElementException {
        T result = poll();
        if(result == null) {
            throw new NoSuchElementException("Priority queue is empty");
        } else {
            return result;
        }
    }

    // return null if pq is empty
    public T poll() {
        if (size < 1) return null;
        Comparable res = pq[0];
	    swap(0, size - 1);
	    size--;
	    percolateDown(0);

        return (T) res;
    }
    
    public T min() { 
	return peek();
    }

    // return null if pq is empty
    public T peek() {

        if (size < 1) return null;
        return (T) pq[0];
    }

    int parent(int i) {
	return (i-1)/2;
    }

    int leftChild(int i) {
	return 2*i + 1;
    }

    /** pq[index] may violate heap order with parent */
    void percolateUp(int index) {
        while (index > 0) {
            int parIdx = parent(index);
            if (pq[index].compareTo(pq[parIdx]) < 0) {
                swap(index, parIdx);
            } else break;
            index = parIdx;
        }
    }

    /** pq[index] may violate heap order with children */
    void percolateDown(int index) {
       while (index < size){
           int lc = leftChild(index);
           int rc = lc + 1;
           if (lc > size - 1) break;
           int minIdx = lc;
           if (rc < size) {
               minIdx = getMin(lc, rc);
           }
           if (pq[index].compareTo(pq[minIdx]) > 0) {
               swap(index, minIdx);
           } else break;
           index = minIdx;
       }
    }

    int getMin(int idx1, int idx2) {
       if (pq[idx1].compareTo(pq[idx2]) <= 0) return idx1;
       return idx2;
    }

    void swap (int idx1, int idx2){
        Comparable tmp =  pq[idx1];
        pq[idx1] = pq[idx2];
        pq[idx2] = tmp;
    }

	/** use this whenever an element moved/stored in heap. Will be overridden by IndexedHeap */
    void move(int dest, Comparable x) {
	pq[dest] = x;
    }

    int compare(Comparable a, Comparable b) {
	return ((T) a).compareTo((T) b);
    }
    
    /** Create a heap.  Precondition: none. */
    void buildHeap() {
	for(int i=parent(size-1); i>=0; i--) {
	    percolateDown(i);
	}
    }

    public boolean isEmpty() {
	return size() == 0;
    }

    public int size() {
	return size;
    }

    // Resize array to double the current size
    void resize() {
    }
    
    public interface Index {
        public void putIndex(int index);
        public int getIndex();
    }
	
	// IndexedHeap is useful to implement algorithms, such as Kruskal's MST, that requires
	// decreseKey() operation. You can implement this now or later when you implement MST algorithms
    public static class IndexedHeap<T extends Index & Comparable<? super T>> extends BinaryHeap<T> {
        /** Build a priority queue with a given array */
        IndexedHeap(int capacity) {
            super(capacity);
		}

        /** restore heap order property after the priority of x has decreased */
        void decreaseKey(T x) {
        }

	@Override
        void move(int i, Comparable x) {
            super.move(i, x);
        }
    }

    public static void main(String[] args) {
	Integer[] arr = {0};
	BinaryHeap<Integer> h = new BinaryHeap(arr.length);

	System.out.print("Before:");
	for(Integer x: arr) {
	    h.offer(x);
	    System.out.print(" " + x);
	}
	System.out.println();

	for(int i=0; i<arr.length; i++) {
	    arr[i] = h.poll();
	}

	System.out.print("After :");
	for(Integer x: arr) {
	    System.out.print(" " + x);
	}
	System.out.println();
    }
}
