package core.common.cache;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import core.registry.URL;

import java.util.Map;
import java.util.Set;

public class CommonServerCache {

    public static final Map<String, Object> PROVIDER_CLASS_MAP = Maps.newHashMap();

    public static final Set<URL> PROVIDER_URL_SET = Sets.newHashSet();

}