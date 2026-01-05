/**
 * @Author: Qizheng Wang
 * @Email: qizheng.wang@foxmail.com
 * @Date: 2026/01/05 21:35
 * @Description:
 **/
public class DCLSingleton {
    private static volatile DCLSingleton instance;

    private DCLSingleton() {
        if (instance != null) {
            throw new RuntimeException("禁止反射创建");
        }
    }
    public static DCLSingleton getInstance() {
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                if (instance == null) {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }
}
