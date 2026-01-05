/**
 * @Author: Qizheng Wang
 * @Email: qizheng.wang@foxmail.com
 * @Date: 2026/01/05 18:54
 * @Description:
 **/
public class SmsStaticProxy implements SmsService{
    private final SmsService target;

    public SmsStaticProxy(SmsService target) {
        this.target = target;
    }

    @Override
    public void send(String message) {
        System.out.println("before sending");
        target.send(message);
        System.out.println("after sending");
    }
}
