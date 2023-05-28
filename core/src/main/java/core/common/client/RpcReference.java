package core.common.client;

import core.proxy.ProxyFactory;

public class RpcReference {

    public ProxyFactory proxyFactory;

    public RpcReference(ProxyFactory proxyFactory) {
        this.proxyFactory = proxyFactory;
    }

    /**
     * 根据接口类型获取代理对象
     * @param tClass
     * @param <T>
     * @return
     * @throws Throwable
     */
    public <T> T get(Class<T> tClass) throws Throwable {
        return proxyFactory.getProxy(tClass);
    }
}
