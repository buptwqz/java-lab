import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: Qizheng Wang
 * @Email: qizheng.wang@foxmail.com
 * @Date: 2026/01/05 19:06
 * @Description:
 **/
public class SmsDynamicProxy implements InvocationHandler {

    private final Object target;

    public SmsDynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("dynamic proxy start");
        Object result = method.invoke(target, args);
        System.out.println("dynamic proxy end");
        return result;
    }

    public static void main(String[] args) {
        SmsService realService = new SmsServiceImpl();

        SmsService proxy = (SmsService) Proxy.newProxyInstance(realService.getClass().getClassLoader(),
                new Class[]{SmsService.class}, new SmsDynamicProxy(realService));

        proxy.send("hello");
    }
}
