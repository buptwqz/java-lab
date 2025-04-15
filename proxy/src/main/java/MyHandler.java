/**
 * @Author: Qizheng Wang
 * @Email: qizheng.wang@foxmail.com
 * @Date: 2025/4/14 22:32
 * @Description:
 **/
public interface MyHandler {
    public String functionBody(String methodName);

    default void setProxy(MyInterface proxy) {

    }
}