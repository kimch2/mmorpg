package com.tryingpfq.common.file;

import com.google.common.collect.Lists;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.util.List;

/**
 * @author tryingpfq
 * @date 2018/12/27 15:56
 */
public class FileAlterationMoniter implements Runnable {
    private final List<FileAlterationObserver> observers = Lists.newCopyOnWriteArrayList();

    public FileAlterationMoniter(FileAlterationObserver... observers){
        if(observers != null){
            for(FileAlterationObserver observer : observers){
                addObserver(observer);
            }
        }
    }

    public void addObserver(FileAlterationObserver observer) {
        if(observer != null){
            observers.add(observer);
        }
    }

    @Override
    public void run() {
        for(FileAlterationObserver observer : observers){
            observer.checkAndNotify();
        }
    }
}
