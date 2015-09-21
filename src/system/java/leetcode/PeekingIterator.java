package system.java.leetcode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class PeekingIterator1<E> implements Iterator<E> {
    Iterator it;
    E top;

    public PeekingIterator1(Iterator<E> iterator) {
        // initialize any member here.
        this.it = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public E peek() {
        if (top == null)
            top = (E)it.next();
        return top;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public E next() {
        E res = top;
        if (res == null)
            res = (E)it.next();
        top = null;
        return res;
    }

    @Override
    public boolean hasNext() {
        return top != null ? true : it.hasNext();
    }
}

public class PeekingIterator {
    public static void main(String[] args) {
        List<Integer> l = new LinkedList<Integer>(Arrays.asList(1, 2, 3, 4));
        Iterator<Integer> it = l.iterator();
        PeekingIterator1 p = new PeekingIterator1<Integer>(it);
        while (p.hasNext()) {
            System.out.println(p.peek());
            p.next();
        }
    }
}
