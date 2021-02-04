import java.util.HashMap;
import java.util.Map;

public class BankOcr {

    Map<Number, String> numbers;

    public BankOcr(){

        numbers = new HashMap<>();

        String[] zero = new String[]{" _ ",
                                     "| |",
                                     "|_|"};

        String[] one = new String[]{"   ",
                                    "  |",
                                    "  |"};

        String[] two = new String[]{" _ ",
                                    " _|",
                                    "|_ "};

        String[] three = new String[]{" _ ",
                                      " _|",
                                      " _|"};

        String[] four = new String[]{"   ",
                                     "|_|",
                                     "  |"};

        String[] five = new String[]{" _ ",
                                     "|_ ",
                                     " _|"};

        String[] six = new String[]{" _ ",
                                    "|_ ",
                                    "|_|"};

        String[] seven = new String[]{" _ ",
                                      "  |",
                                      "  |"};

        String[] eight = new String[]{" _ ",
                                      "|_|",
                                      "|_|"};

        String[] nine = new String[]{" _ ",
                                     "|_|",
                                     " _|"};

        numbers.put(new Number(zero),"0");
        numbers.put(new Number(one),"1");
        numbers.put(new Number(two),"2");
        numbers.put(new Number(three),"3");
        numbers.put(new Number(four),"4");
        numbers.put(new Number(five),"5");
        numbers.put(new Number(six),"6");
        numbers.put(new Number(seven),"7");
        numbers.put(new Number(eight),"8");
        numbers.put(new Number(nine),"9");
    }

    public String getInputResult(String[] number){
        String result = parseString(number);
        if(result.contains("?")) return result + " ILL";
        if(!isValidChecksum(result)) return result + " ERR";
        return result;
    }

    public String parseString(String[] number){
        StringBuilder builder = new StringBuilder();
        for(int i = 0;i < number[0].length();i = i+3){
            String[] res = new String[]{number[0].substring(i,i+3),number[1].substring(i,i+3),number[2].substring(i,i+3)};
            Number result = new Number(res);
            builder.append(getNumber(result));
        }
        return builder.toString();
    }

    private String getNumber(Number number){
        String result = numbers.get(number);
        if(result == null) return "?";
        return numbers.get(number);
    }

    public boolean isValidChecksum(String numbers){
        int result = Character.getNumericValue(numbers.charAt(numbers.length()-1));
        for(int i = 2;i < numbers.length()+1;i++){
            result += (i*Character.getNumericValue(numbers.charAt(numbers.length()-i)));
        }
        return result % 11 == 0;
    }

    


}
