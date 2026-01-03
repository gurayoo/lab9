import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class q1 {
    public static void main(String[] args) {
        int totalWords = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("enter a path");
        String filePath = sc.nextLine();
        HashMap<String, Integer>
                wordCounts = new HashMap<>();
        try {
            File file = new File(filePath);
            Scanner fr = new Scanner(file);
            while (fr.hasNext()) {
                String word = fr.next().toLowerCase();
                word=word.replace(",","");
                word=word.replace(".","");
                if (wordCounts.containsKey(word)) {
                    wordCounts.put(word, wordCounts.get(word) + 1);
                    totalWords++;
                } else {
                    wordCounts.put(word, 1);
                    totalWords++;
                }
            }
            fr.close();
            PrintWriter writer = new PrintWriter("word_stats.txt");
            System.out.println("Total words: " + totalWords);
            System.out.println("Unique words: " + wordCounts.size());
            writer.println("Total words: " + totalWords);
            writer.println("Unique words: " + wordCounts.size());
            for (String w : wordCounts.keySet()) {
                String result = w + ":" + wordCounts.get(w);
                System.out.println(result);
                writer.println(result);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
        }
    }
}