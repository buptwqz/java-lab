/**
 * @Author: Qizheng Wang
 * @Email: qizheng.wang@foxmail.com
 * @Date: 2025/4/14 08:44
 * @Description:
 **/
public class NameAndLengthImpl implements MyInterface{
    @Override
    public void func1() {
        String methodName = "func1";
        System.out.println(methodName);
        System.out.println(methodName.length());
    }

    @Override
    public void func2() {
        String methodName = "func2";
        System.out.println(methodName);
        System.out.println(methodName.length());
    }

    @Override
    public void func3() {
        String methodName = "func3";
        System.out.println(methodName);
        System.out.println(methodName.length());
    }
}
