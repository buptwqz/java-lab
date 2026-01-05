/**
 * @Author: Qizheng Wang
 * @Email: qizheng.wang@foxmail.com
 * @Date: 2026/01/05 19:02
 * @Description:
 **/
public class SmsServiceImpl implements SmsService{
    @Override
    public void send(String message) {
        System.out.println("send " + message);
    }
}
