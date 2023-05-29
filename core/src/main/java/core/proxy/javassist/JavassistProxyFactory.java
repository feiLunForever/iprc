package core.proxy.javassist;

import core.proxy.ProxyFactory;

public class JavassistProxyFactory implements ProxyFactory {

    @Override
    public <T> T getProxy(Class<T> clazz) throws Throwable {
        return (T) ProxyGenerator.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                clazz, new JavassistInvocationHandler(clazz));
    }
}
