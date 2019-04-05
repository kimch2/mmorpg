import java.util.concurrent.locks.ReentrantLock;

public class TestOne {

    private static int a;

    public static void main(String[] args) {
        int b = 6;
        a = 3;
        TestOne tt = new TestOne();
        int c = tt.calc(a,b);
        System.out.println(c);

        ReentrantLock lock = new ReentrantLock();
    }

    public  int calc(int aa,int bb){
        if(aa > bb){
            return aa - bb;
        }else{
            return aa + bb;
        }
    }


}
