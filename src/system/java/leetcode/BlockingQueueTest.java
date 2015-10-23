package system.java.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by khuang on 10/11/15.
 */
public class BlockingQueueTest {
    public static void main(String [] args) {
        BoundedBlockingQueue<Integer> q = new BoundedBlockingQueue<Integer>(10);

        Thread C1 = new Thread(new Consumer(1, q));
        Thread C2 = new Thread(new Consumer(2, q));
        Thread P1 = new Thread(new Producer(1, q));
        Thread P2 = new Thread(new Producer(2, q));

        C1.start();
        C2.start();
        P1.start();
        P2.start();

        // Join Producers here. Consumers will be blocked on waiting for new item.
        try {
            P1.join();
            P2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("===== Done!");
    }
}

class BoundedBlockingQueue<E> {
    private Queue<E> queue;
    private int capacity;

    private final ReentrantLock lock = new ReentrantLock();
    private Condition notFull = this.lock.newCondition();
    private Condition notEmpty = this.lock.newCondition();
    private final ReentrantLock pushlock = new ReentrantLock();

    // only initialize this queue once and throws exception if it is initialized again.
    public BoundedBlockingQueue(int capacity) {
        this.lock.lock();
        try {
            this.capacity = capacity;
            this.queue = new LinkedList<E>();
        } finally {
            this.lock.unlock();
        }
    }

    public int size() {
        this.lock.lock();
        try {
            return queue.size();
        } finally {
            this.lock.unlock();
        }
    }

    public void push(E e) throws Exception {
        this.pushlock.lock();
        this.lock.lock();
        try {
            while (this.capacity == this.queue.size())
                this.notFull.await();
            this.queue.offer(e);
            this.notEmpty.signalAll();
        } finally {
            this.lock.unlock();
            this.pushlock.unlock();
        }
    }

    public E pop() throws Exception {
        this.lock.lock();
        try {
            while (this.queue.size() == 0)
                this.notEmpty.await();
            E res = this.queue.poll();
            this.notFull.signalAll();

            return res;
        } finally {
            this.lock.unlock();
        }
    }

    public void pushList(List<E> objs) throws Exception {
        this.pushlock.lock();
        this.lock.lock();
        try {
            for (E obj : objs) {
                while (this.queue.size() == this.capacity)
                    notEmpty.wait();
                this.queue.add(obj);
                this.notEmpty.notifyAll();
            }
        } finally {
            this.lock.unlock();
            this.pushlock.unlock();
        }
    }
}

class Consumer implements Runnable {
    int id;
    BoundedBlockingQueue<Integer> q = null;

    public Consumer(int id, BoundedBlockingQueue<Integer> q) {
        this.q = q;
        this.id = id;
    }

    @Override
    public void run() {
        Integer res = null;
        while (true) {
            try {
                Thread.sleep(100);
                res = q.pop();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Consumer " + this.id + " consumes " + res);
        }
    }
}

class Producer implements Runnable {
    int id;
    BoundedBlockingQueue<Integer> q = null;

    public Producer(int id, BoundedBlockingQueue<Integer> q) {
        this.q = q;
        this.id = id;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 20) {
            try {
                Thread.sleep(20);
                q.push(this.id * 1000 + i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Producer "+this.id+" produces "+(this.id * 1000 + i));
            i++;
        }
    }
}

