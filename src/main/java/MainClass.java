import java.util.Arrays;

public class MainClass {


    public static void main(String[] args) {

        MainClass m = new MainClass();
        int[] arr = {1,4,1};
        System.out.println(Arrays.toString(m.arrAfter4(arr)));

        System.out.println(Arrays.toString((arr)));
        System.out.println(m.onlyOneOrFourInArray(arr));

    }

    public int[] arrAfter4(int[] arr){
        int indexOf4 = -1;
        for (int i = arr.length - 1; i >= 0; i--)
            if (arr[i] == 4) {
                indexOf4 = i;
                break;
            }
        if ((indexOf4 == -1)) throw new RuntimeException("there is no 4 digit");
        int[] arrNew = new int[(arr.length - 1) - indexOf4];
        System.arraycopy(arr,indexOf4 + 1,arrNew,0,(arr.length - 1) - indexOf4);

        return arrNew;
    }

    public boolean onlyOneOrFourInArray(int[] arr) {
        boolean four = false;
        boolean one = false;

        for (int i = 0; i < arr.length; i++) {
            int buf = arr[i];
            if ((buf != 4) && (buf != 1)) return false;
            if (buf == 4) four = true;
            else if (buf == 1) one = true;
        }

        return four & one;
    }




}
