/**
 * @Author: Qizheng Wang
 * @Email: qizheng.wang@foxmail.com
 * @Date: 2026/01/05 20:04
 * @Description:
 **/
public class HungrySingleton {
    private static final HungrySingleton INSTANCE = new HungrySingleton();

    private HungrySingleton() {
        if (INSTANCE != null) {
            throw new RuntimeException("反射禁止创建");
        }
    }
    public static HungrySingleton getSingleton() {
        return INSTANCE;
    }
}
