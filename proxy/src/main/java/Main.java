/**
 * @Author: Qizheng Wang
 * @Email: qizheng.wang@foxmail.com
 * @Date: 2025/4/14 22:28
 * @Description:
 **/
public class Main {
    public static void main(String[] args) throws Exception {
        MyInterface proxyObject = MyInterfaceFactory.createProxyObject(new PrintFunctionName());
        proxyObject.func1();
        proxyObject.func2();
        proxyObject.func3();
    }
    static class PrintFunctionName implements MyHandler {

        @Override
        public String functionBody(String methodName) {
            return "System.out.println(\""+ methodName + "\");";
        }
    }
}
