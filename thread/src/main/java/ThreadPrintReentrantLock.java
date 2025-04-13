import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Qizheng Wang
 * @Email: qizheng.wang@foxmail.com
 * @Date: 2025/4/13 12:11
 * @Description:
 **/
public class ThreadPrintReentrantLock {
    static ReentrantLock lock = new ReentrantLock();
    static int count = 0;
    static Condition condition = lock.newCondition();
    static volatile boolean flag = true;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new Thread(() -> {
            while (count <= 200) {
                if (flag) {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    flag = false;
                    condition.signal();
                    lock.unlock();
                }
            }
        }).start();
        new Thread(() -> {
            while (count <= 200) {
                if (!flag) {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    flag = true;
                    condition.signal();
                    lock.unlock();
                }
            }
        }).start();
    }
}
