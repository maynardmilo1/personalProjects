package Java;
//Given array a, print 
// 1, 2, 3, 6, 5, 4, 7, 8, 9
public class Smatrix {
    public static void main(String[] args) {

        int[][] a = {{1,2,3},{4,5,6},{7,8,9}};
    
        for(int y = 0; y<=2; y++){
            
            if (y % 2 == 0) {
                for(int x = 0; x <= 2 ;x++){
                    System.out.print(a[y][x]+ " ");
                }
            }else{
                for(int x = 2; x >= 0 ;x--){
                    System.out.print(a[y][x] + " ");
                }
            }
        }
    }
}
