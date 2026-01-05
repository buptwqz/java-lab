import sun.management.HotSpotDiagnostic;

/**
 * @Author: Qizheng Wang
 * @Email: qizheng.wang@foxmail.com
 * @Date: 2026/01/05 21:40
 * @Description:
 **/
public class StaticInnerSingleton {
    private static class Holder {
        private static final StaticInnerSingleton instance = new StaticInnerSingleton();
    }

    private StaticInnerSingleton() {
        if (Holder.instance != null) {
            throw new RuntimeException("单例模式禁止反射创建");
        }
    }

    public static StaticInnerSingleton getInstance() {
        return Holder.instance;
    }
}
