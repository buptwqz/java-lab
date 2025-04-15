import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Qizheng Wang
 * @Email: qizheng.wang@foxmail.com
 * @Date: 2025/4/14 09:01
 * @Description:
 **/
public class MyInterfaceFactory {

    private static final AtomicInteger count = new AtomicInteger(0);

    private static File createJavaFile(String className, MyHandler handler) throws IOException {
        String fuc1Body = handler.functionBody("func1");
        String fuc2Body = handler.functionBody("func2");
        String fuc3Body = handler.functionBody("func3");
        String context = "/**\n" +
                " * @Author: Qizheng Wang\n" +
                " * @Email: qizheng.wang@foxmail.com\n" +
                " * @Date: 2025/4/14 08:41\n" +
                " * @Description:\n" +
                " **/\n" +
                "public class " + className +" implements MyInterface{\n" +
                " MyInterface myInterface;" +
                "    @Override\n" +
                "    public void func1() {\n" +
                "        " + fuc1Body + "\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void func2() {\n" +
                "        " + fuc2Body + "\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void func3() {\n" +
                "        " + fuc3Body + "\n" +
                "    }\n" +
                "}\n";
        File javaFile = new File("proxy/" + className + ".java");
        Files.writeString(javaFile.toPath(), context);
        return javaFile;
    }

    private static String getClassName() {
        return "MyInterface$proxy" + count.incrementAndGet();
    }

    private static MyInterface newInstance(String className, MyHandler myHandler) throws Exception {
        Class<?> aClass = MyInterfaceFactory.class.getClassLoader().loadClass(className);
        Constructor<?> constructor = aClass.getConstructor();
        MyInterface proxy = (MyInterface) constructor.newInstance();
        myHandler.setProxy(proxy);
        return proxy;
    }

    public static MyInterface createProxyObject(MyHandler handler) throws Exception {
        String className = getClassName();
        File javaFile = createJavaFile(className, handler);
        Compiler.compile(javaFile);;
        return newInstance(className, handler);
    }
}
