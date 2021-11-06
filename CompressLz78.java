import java.util.ArrayList;
import java.util.Scanner;

public class LZ78 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Text do you need compress");
        String Text = input.nextLine();

        ArrayList<Integer> position = new ArrayList<Integer>();
        ArrayList<Character> NextSymbol = new ArrayList<Character>();
        ArrayList<String> DICTIONARY = new ArrayList<String>();

        DICTIONARY.add("");// because we staring dictionary from 1 
        String temp = "";
        for (int i = 0; i < Text.length(); i++) {
            temp = Text.substring(i, i + 1);
            int counter = i;
            for (int j = 0; j <= i; j++) {
                if (DICTIONARY.contains(temp)) {
                    if (counter + 1 < Text.length()) {
                        counter++;
                        temp = temp + Text.substring(counter, counter + 1);
                        continue;
                    }
                }
                if (DICTIONARY.contains(temp)) {// last substring (if we have input look : ABCDA hence we enter if in//
                                                // last char )
                    // important if hence save some in memory instead of we tell in last char <0,A>
                    // we tell <1,null>

                    position.add(DICTIONARY.indexOf(temp));// as enter hence so temp (substring)exist in dictionary so
                                                           // position will be same position in dictionary
                    NextSymbol.add(null);
                    // DICTIONARY.add(temp); //note hence we don't need add temp in dictionary
                    // because it already exists
                    break;
                } else if (j == 0) {// new character (if we have input look : ABCDAB hence we enter else if in
                    // A,B,C,D)
                    position.add(j);
                    NextSymbol.add(temp.charAt(j));
                    DICTIONARY.add(temp);
                    break;
                } else {// new substring ((if we have input look : ABCDAB hence we enter else in Fifth
                    // Char(A) )
                    position.add(DICTIONARY.indexOf(temp.substring(0, counter - i)));
                    NextSymbol.add(temp.charAt(j));
                    DICTIONARY.add(temp);
                    break;
                }
            }
            i = counter;
        }
        input.close();
        for (int i = 0; i < position.size(); i++)
            System.out.println("<" + position.get(i) + "," + NextSymbol.get(i) + ">");
    }
}
