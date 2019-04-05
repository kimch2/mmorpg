package com.tryingpfq.common.file;

import java.io.InputStream;

/**
 * @author tryingpfq
 * @date 2018/12/27 11:24
 */
public interface FileReloadListener extends FileLoadListener {
    void reload(InputStream inputStream);
}
