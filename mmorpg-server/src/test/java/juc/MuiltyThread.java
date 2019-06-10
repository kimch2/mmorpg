package juc;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author tryingpfq
 * @date 2019/3/7 10:07
 */
public class MuiltyThread {
    private long count = 0;
    //private AtomicLong count = new AtomicLong(0);
    //private volatile long = 0;
    public static void main(String[] args) {
        try{
            System.out.println(cal());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public  static long cal() throws InterruptedException {
        MuiltyThread test = new MuiltyThread();
        Thread t1 = new Thread(()->{
            test.add();
        });
        Thread t2 = new Thread(()->{
            test.add();
        });
        t1.start();
       // t1.join();
        t2.start();
        t1.join();
        t2.join();
       //return test.count.get();
        return test.count;
    }

    public void add(){
        int dx = 0;
        while(dx++ < 10000){
            count ++;
            //count.incrementAndGet();
        }
    }
}


