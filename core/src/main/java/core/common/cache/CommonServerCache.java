package core.common.cache;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import core.registry.RegistryService;
import core.registry.URL;
import core.serialize.SerializeFactory;

import java.util.Map;
import java.util.Set;

public class CommonServerCache {

    public static final Map<String, Object> PROVIDER_CLASS_MAP = Maps.newHashMap();

    public static final Set<URL> PROVIDER_URL_SET = Sets.newHashSet();

    public static RegistryService REGISTRY_SERVICE;
    public static SerializeFactory SERVER_SERIALIZE_FACTORY;

}