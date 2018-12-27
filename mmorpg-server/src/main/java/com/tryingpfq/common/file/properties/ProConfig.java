package com.tryingpfq.common.file.properties;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.tryingpfq.common.file.MergerReloadListener;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author tryingpfq
 * @date 2018/12/27 11:27
 */
public class ProConfig extends MergerReloadListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProConfig.class);

    private volatile Map<String,String> map = Maps.newHashMap();

    private Set<ProConfigListener> proListeners = Sets.newCopyOnWriteArraySet();

    @Override
    public void load(InputStream inputStream) {
        init(inputStream);
        /*for(ProConfigListener listener : proListeners){
            listener.reload(this);
        }*/
    }

    public void addListener(ProConfigListener listener){
        proListeners.add(listener);
    }

    public String getStrVal(String key,String defaultStr){
        String val = map.get(key);
        return val == null ? defaultStr : val;
    }
    private void init(InputStream inputStream) {
        try{
            Map<String,String> tempMap = Maps.newHashMap();
            Properties properties = new Properties();
            Reader reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            properties.load(reader);
            for(Map.Entry<Object,Object> entry : properties.entrySet()){
                String key = StringUtils.trimToNull(entry.getKey().toString());
                if(key == null)
                    continue;
                tempMap.put(key,StringUtils.trimToNull(entry.getValue().toString()));
            }
            this.map = tempMap;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
