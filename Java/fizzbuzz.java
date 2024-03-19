package Java;
public class fizzbuzz {
    public static void main(String[] args){
        for( int i = 0 ; i <= 21; i++){
            System.out.print(i);
            if (i%3==0){
                System.out.println("buzz");
            }else if(i%5==0){
                System.out.println("fizzbuzz");
            }else{
                System.out.println("fizz");
            }
        }
    }
}
