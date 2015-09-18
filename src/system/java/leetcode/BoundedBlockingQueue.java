package system.java.leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by khuang on 9/10/15.
 */
public class BoundedBlockingQueue<E> {
    private final Queue<E> queue = new LinkedList<E>();
    private final int capacity;
    private final AtomicInteger count = new AtomicInteger(0);

    private final ReentrantLock putLock = new ReentrantLock();

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public int size() {
        return count.get();
    }

    public void add(E e) throws RuntimeException {
    }
}
