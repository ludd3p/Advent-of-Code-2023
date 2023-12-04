package Day1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Test2 {
    Map<String, Integer> stringMap;
    String filePath = "src/Day1/strings.txt";

    public Test2() {
        initialiseMap();
        readFile();
    }

    private void initialiseMap() {
        stringMap = new HashMap<>();
        stringMap.put("one", 1);
        stringMap.put("two", 2);
        stringMap.put("three", 3);
        stringMap.put("four", 4);
        stringMap.put("five", 5);
        stringMap.put("six", 6);
        stringMap.put("seven", 7);
        stringMap.put("eight", 8);
        stringMap.put("nine", 9);
    }



    public void readFile() {
        List<Integer> numbersList = new ArrayList<>();
        int sum = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String numberString;
            List<Integer> integerList;

            while ((line = br.readLine()) != null) {
                integerList = new ArrayList<>();
                numberString = "";

                for (int i = 0; i < line.length(); i++) {
                    if (Character.isDigit(line.charAt(i))) {
                        integerList.add(Character.getNumericValue(line.charAt(i)));
                        continue;
                    }
                    for (String s : stringMap.keySet()) {
                        if (s.length() + i <= line.length()) {
                            int j;
                            for (j = 0; j < s.length(); j++) {
                                if (s.charAt(j) != line.charAt(i+j)) break;
                            }
                            if (j == s.length()){
                                integerList.add(stringMap.get(s));
                                break;
                            }
                        }


                    }
                }
                numberString += integerList.getFirst();
                numberString += integerList.getLast();
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