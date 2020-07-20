package com.example.springbootjetty;

import org.eclipse.jetty.util.ConcurrentHashSet;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * <p>Description: jetty内存泄漏的问题</p>
 *
 * @author dbx
 * @date 2020/7/20 10:17
 * @since JDK1.8
 */
public class TestLeak {

    /**
     * QueuedThreadPool 中的线程池
     * @param args
     */
    public static void main(String[] args) {
//        ConcurrentHashSet<Object> queue=new ConcurrentHashSet<Object>();//使用ConcurrentHashSet替代老版的ConcurrentLinkedQueue

        ConcurrentLinkedQueue<Object> queue = new ConcurrentLinkedQueue<>();//最新版的已经修复
        queue.add(new Object()); //Required for the leak to appear.
        Object object=new Object();
        int loops=0;
        Runtime rt=Runtime.getRuntime();
        long last=System.currentTimeMillis();
        while(true) {
            if(loops%10000==0) {
                long now=System.currentTimeMillis();
                long duration=now-last;
                last=now;
                System.err.printf("duration=%d q.size=%d memory max=%d free=%d total=%d%n",duration,queue.size(),rt.maxMemory(),rt.freeMemory(),rt.totalMemory());
            }
            queue.add(object);
            queue.remove(object);
            ++loops;
        }
    }
}
