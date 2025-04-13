import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Qizheng Wang
 * @Email: qizheng.wang@foxmail.com
 * @Date: 2025/4/13 11:55
 * @Description: AtomicInteger + flag 实现线程轮流输出 忙等待，比较消耗CPU
 **/
public class ThreadPrintAtomicInteger {
    static AtomicInteger counter = new AtomicInteger(0);
    static volatile boolean flag = true;
    public static void main(String[] args) {
        new Thread(() -> {
            while (counter.get() <= 200) {
                if (flag) {
                    System.out.println(Thread.currentThread().getName() + ":" + counter.getAndIncrement());
                    flag = false;
                }
            }
        }).start();
        new Thread(() -> {
            while (counter.get() <= 200) {
                if (!flag) {
                    System.out.println(Thread.currentThread().getName() + ":" + counter.getAndIncrement());
                    flag = true;
                }
            }
        }).start();
    }
}
