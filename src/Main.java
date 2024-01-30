import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        System.out.println("\n1 - Show vocabulary;\n2 - Show stats;\n3 - Show time needed to form vocabulary\n0 - " +
                "Exit\n");
        System.out.println("Your input here: ");
        int i = in.nextInt();


        long startTime = System.nanoTime();//start time
        Vocabulary v = new Vocabulary("src/res"); // CREATE VOCAB
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime; //final time it took

        while(i != 0) {
            switch (i) {
                case 1:
                    v.print();
                    break;
                case 2:
                    System.out.println(v.Stats());
                    break;
                case 3:
                    System.out.println("Time: " + elapsedTime / 1000000000 + " s");
                    break;
                case 4:
                    System.out.println("Path to vocabulary.txt: " + v.getVocabularyFilePath());
                    break;
                case 5:
                    try {
                        File file = new File(v.getVocabularyFilePath());
                        Desktop.getDesktop().open(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Wrong format");
            }
            System.out.println("\n1 - Show vocabulary;\n2 - Show stats;\n3 - Show time needed to form vocabulary;\n4 - Show path to vocabulary.txt on disc;\n5 - Open vocabulary.txt file;\n0 - " +
                    "Exit\n");
            System.out.println("Your input here: ");
            i = in.nextInt();
        }
    }
}