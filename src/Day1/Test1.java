package Day1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test1 {

    public Test1() {
        readFile();
    }

    String filePath = "src/Day1/strings.txt";
    List<Integer> numbersList = new ArrayList<>();

    public void readFile() {
        int sum = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String numberString;

            while ((line = br.readLine()) != null) {
                numberString = "";

                for (int i = 0; i < line.length(); i++) {
                    if (Character.isDigit(line.charAt(i))) {
                        numberString += line.charAt(i);
                        break;
                    }
                }

                for (int i = line.length() - 1; i >= 0; i--) {
                    if (Character.isDigit(line.charAt(i))) {
                        numberString += line.charAt(i);
                        break;
                    }
                }

                numbersList.add(Integer.valueOf(numberString));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i : numbersList) {
            sum += i;
        }

        System.out.println(sum);
    }
}