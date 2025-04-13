import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Qizheng Wang
 * @Email: qizheng.wang@foxmail.com
 * @Date: 2025/4/13 12:05
 * @Description: synchronized + wait + notify 实现
 **/
public class ThreadPrintSynchronized {
    static AtomicInteger counter = new AtomicInteger(0);
    static final Object obj = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            while (counter.get() <= 200) {
                synchronized (obj) {
                    obj.notify();
                    System.out.println(Thread.currentThread().getName() + ":" + counter.getAndIncrement());
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
        new Thread(() -> {
            while (counter.get() <= 200) {
                synchronized (obj) {
                    obj.notify();
                    System.out.println(Thread.currentThread().getName() + ":" + counter.getAndIncrement());
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
}
