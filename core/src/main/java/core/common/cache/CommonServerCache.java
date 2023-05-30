package core.common.cache;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import core.common.config.ServerConfig;
import core.filter.server.ServerFilterChain;
import core.registry.RegistryService;
import core.registry.URL;
import core.serialize.SerializeFactory;
import core.server.ServiceWrapper;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CommonServerCache {

    public static final Map<String, Object> PROVIDER_CLASS_MAP = Maps.newHashMap();

    public static final Set<URL> PROVIDER_URL_SET = Sets.newHashSet();

    public static RegistryService REGISTRY_SERVICE;
    public static SerializeFactory SERVER_SERIALIZE_FACTORY;

    public static ServerConfig SERVER_CONFIG;
    public static ServerFilterChain SERVER_FILTER_CHAIN;
    public static final Map<String, ServiceWrapper> PROVIDER_SERVICE_WRAPPER_MAP = new ConcurrentHashMap<>();
}