package com.tryingpfq.common.file;

import java.io.InputStream;

/**
 * @author tryingpfq
 * @date 2018/12/27 15:29
 */
public interface FileLoadListener {
    void load(InputStream inputStream);
}
