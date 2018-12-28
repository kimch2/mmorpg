import other.EnumTest;

public class TestOne {

    public static void main(String[] args) {
       /* int i =  1 << 2;
        System.out.println(i);

        System.out.println(EnumTest.MONDAY.isXingQi());
        System.out.println(EnumTest.MONE.isXingQi());*/
       int a = 1,b = 0,c = 1;
       try{
            c = calcuDiv(a,b);
           System.out.println("有执行到这里吗");
       }catch (Exception e){
           e.printStackTrace();
       }
        System.out.println(c);
    }

    public static int calcuDiv(int a,int b){
        return a/b;
    }
}
