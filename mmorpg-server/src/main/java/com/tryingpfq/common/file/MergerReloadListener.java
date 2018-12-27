package com.tryingpfq.common.file;

import java.io.InputStream;

/**
 * @author tryingpfq
 * @date 2018/12/27 11:23
 */
public abstract class MergerReloadListener implements FileReloadListener{

    public void reload(InputStream inputStream){
        load(inputStream);
    }
}
