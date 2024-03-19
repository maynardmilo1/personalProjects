package Java;
import java.util.ArrayList;
import java.util.List;

public class reverselist{
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(4);
        a.add(3);
        a.add(2);
        for (Integer integer : a) {
            System.out.print(integer + ", ");
        }
        System.out.println();
        System.out.println(reverseArray(a));
    }

    public static List<Integer> reverseArray(List<Integer> a){
        List<Integer> b = new ArrayList<>();
            for (int i = a.size() -1; i >= 0; i--) {
                b.add(a.get(i));
            }
        
        return b;
    }
}