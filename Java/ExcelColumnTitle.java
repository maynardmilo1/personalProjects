package Java;

public class ExcelColumnTitle {
    public static void main(String[] args) {
    
        System.out.println(convertToTitle(30));
    }
    public static String convertToTitle(int columnNumber){
        StringBuilder columnName = new StringBuilder();
        while(columnNumber > 0){
            int remainder = (columnNumber -1) % 26;
            columnName.insert(0,(char)(remainder +'A'));
            columnNumber = (columnNumber-1)/26;
        }

        return columnName.toString();
    }
}
