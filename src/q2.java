import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class q2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Input file path: ");
            String inputPath = scanner.nextLine();

            System.out.print("Word to be replaced: ");
            String oldWord = scanner.nextLine();

            System.out.print("Replacement word: ");
            String newWord = scanner.nextLine();

            System.out.print("Output file path: ");
            String outputPath = scanner.nextLine();

            File inputFile = new File(inputPath);

            if(!inputFile.exists()) {
                System.out.println("Input file does not exist.");
                return;
            }

            Scanner fileReader = new Scanner(inputFile);
            FileWriter fileWriter = new FileWriter(outputPath);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String modifiedLine = line.replace(oldWord, newWord);

                fileWriter.write(modifiedLine + "\n");
            }

            fileReader.close();
            fileWriter.close();

            System.out.println("Replaced '" + oldWord + "' with '" + newWord + "' in " + inputPath);
            System.out.println("Saved to " + outputPath);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
