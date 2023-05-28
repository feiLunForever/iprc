package core.proxy.jdk;

import core.proxy.ProxyFactory;

import java.lang.reflect.Proxy;

public class JDKProxyFactory implements ProxyFactory {

    @Override
    public <T> T getProxy(final Class<T> clazz) throws Throwable {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz},
                new JDKClientInvocationHandler(clazz));
    }
}
