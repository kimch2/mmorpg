package com.tryingpfq.common.file.properties;

import com.tryingpfq.common.file.properties.ProConfig;

/**
 * @author tryingpfq
 * @date 2018/12/27 11:30
 */
public interface ProConfigListener {
    void reload(ProConfig proConfig);
}
