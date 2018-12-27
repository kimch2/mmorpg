package com.tryingpfq.common.file;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author tryingpfq
 * @date 2018/12/27 15:26
 * 文件装在器
 */
public class FileLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileLoader.class);

    private static final FileAlterationMoniter moniter;

    private List<FileLoadListener> listenerList = Lists.newArrayList();

    private static ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    public static void load(String filePath,FileLoadListener... fileLoadListeners) throws FileNotFoundException{
        if(fileLoadListeners == null || fileLoadListeners.length == 0){
            LOGGER.warn("file {} has not fileLoadListener",filePath);
            return ;
        }
        File file = getFile(filePath);
        List<FileLoadListener> listenerList = Arrays.asList(fileLoadListeners);
        FileLoader fileLoader = new FileLoader(file,listenerList);
        fileLoader.load(file);
    }

    static {
        int interval = 100000;
        moniter = new FileAlterationMoniter();
        scheduler.scheduleWithFixedDelay(moniter,interval,interval,TimeUnit.MILLISECONDS);
    }

    private FileLoader(File file, List<FileLoadListener> listenerList){
        if(hasReload(listenerList)){
            FileAlterationObserver observer = new FileAlterationObserver(file.getParentFile(),new NameFileFilter(file.getName()));
            observer.addListener(new FileLoaderListener());
            moniter.addObserver(observer);
        }
        this.listenerList.addAll(listenerList);
    }

    private class FileLoaderListener extends FileAlterationListenerAdaptor{
        @Override
        public void onFileChange(File file) {
            reload(file);
        }
    }

    private void load(File file) throws FileNotFoundException {
        executeCommand(file, new DealCommand() {
            @Override
            public void execute(InputStream inputStream) {
                for(FileLoadListener fileLoadListener : listenerList){
                    fileLoadListener.load(inputStream);
                }
            }
        });
    }

    private void reload(File file){
        try{
            executeCommand(file, new DealCommand() {
                @Override
                public void execute(InputStream inputStream) {
                    for(FileLoadListener fileLoadListener : listenerList){
                        if(fileLoadListener instanceof FileReloadListener){
                            ((FileReloadListener) fileLoadListener).reload(inputStream);
                        }
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void executeCommand(File file,DealCommand comand) throws FileNotFoundException {
        InputStream inputStream = null;
        try{
            inputStream = new BufferedInputStream(new FileInputStream(file));
            LOGGER.info("begin read and load file {}",file.getName());
            comand.execute(inputStream);
            LOGGER.info("end reand and load file {}" ,file.getName());
        }finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    private static interface DealCommand{
        void execute(InputStream inputStream);
    }

    private boolean hasReload(List<FileLoadListener> listenerList){
        if(listenerList == null || listenerList.size() == 0){
            return false;
        }
        for(FileLoadListener listener : listenerList){
            if(listener instanceof FileReloadListener){
                return true;
            }
        }
        return false;
    }

    public static File getFile(String filaPath){
        File file = new File(filaPath);
        if(!file.exists()){
           // URL url = this.getClass().getClassLoader().getResource(filaPath);
            URL url = Thread.currentThread().getContextClassLoader().getResource(filaPath);
            Preconditions.checkNotNull(url,"File[%s] NOT FOUNT",filaPath);
            file = new File(url.getFile());
        }
        return file;
    }
}
