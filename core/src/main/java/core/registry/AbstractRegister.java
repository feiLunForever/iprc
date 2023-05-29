package core.registry;

import core.common.cache.CommonClientCache;
import core.common.cache.CommonServerCache;

import java.util.List;

public abstract class AbstractRegister implements RegistryService {

    @Override
    public void register(URL url) {
        CommonServerCache.PROVIDER_URL_SET.add(url);
    }

    @Override
    public void unRegister(URL url) {
        CommonServerCache.PROVIDER_URL_SET.remove(url);
    }

    @Override
    public void subscribe(URL url) {
        doBeforeSubscribe(url);
        CommonClientCache.SUBSCRIBE_SERVICE_LIST.add(url.getServiceName());
        doAfterSubscribe(url);
    }

    @Override
    public void doUnSubscribe(URL url) {
        CommonClientCache.SUBSCRIBE_SERVICE_LIST.remove(url.getServiceName());
    }

    /**
     * 留给子类扩展
     *
     * @param url
     */
    public abstract void doAfterSubscribe(URL url);

    /**
     * 留给子类扩展
     *
     * @param url
     */
    public abstract void doBeforeSubscribe(URL url);

    /**
     * 留给子类扩展
     *
     * @param serviceName
     * @return
     */
    public abstract List<String> getProviderIps(String serviceName);
}
