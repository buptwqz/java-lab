/**
 * @Author: Qizheng Wang
 * @Email: qizheng.wang@foxmail.com
 * @Date: 2026/01/05 21:29
 * @Description:
 **/
public class LazySyncSingleton {
    private static LazySyncSingleton instance;

    private LazySyncSingleton() {}

    public static synchronized LazySyncSingleton getInstance() {
        if (instance == null) {
            instance = new LazySyncSingleton();
        }
        return instance;
    }
}
