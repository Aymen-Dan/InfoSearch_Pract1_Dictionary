import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){



        Scanner in = new Scanner(System.in);

        File textsFolder = new File("src/res");

        if (textsFolder.isDirectory()) {
            File[] files = textsFolder.listFiles();

            if (files != null && files.length > 0) {
                System.out.println("\nGreat! Files present. Let's begin.\n\n");
            } else {
                System.out.println("\nThere are no files in the folder 'src/res'. Please add some files.\n\n");
            }
        } else {
            System.out.println("\nThe folder 'src/res' is not a directory. Please create the folder and add some files to it.\n\n");
        }



        //TXT SAVE
        long startTimeTXT = System.nanoTime();//start time
        Vocabulary vTXT = new Vocabulary("src/res"); // CREATE VOCAB
        long endTimeTXT = System.nanoTime();
        long elapsedTimeTXT = endTimeTXT - startTimeTXT; //final time it took

        //SER SAVE
        long startTimeSER = System.nanoTime();//start time
        Vocabulary vSER = new Vocabulary("src/res"); // CREATE VOCAB
        vSER.serialize("vocabulary.ser");
        long endTimeSER = System.nanoTime();
        long elapsedTimeSER = endTimeSER - startTimeSER; //final time it took


        System.out.println("\n0 - Print vocabulary.txt;\n1 - Print list of files;\n2 - Show stats;\n3 - Show time needed to form vocabulary\n4 - Show path to vocabulary files stored on disc;\n5 - Open vocabulary.txt file;\n-1 - " +
                "Exit\n");
        System.out.println("Your input here: ");
        int i = in.nextInt();



        while(i != -1) {
            switch (i) {
                case 0:
                    vTXT.print();
                    break;
                case 1:
                   System.out.println(vTXT.listOfFiles());
                    break;
                case 2:
                    System.out.println(vTXT.statsTXT());
                    System.out.println(vSER.statsSer());
                    break;
                case 3:
                    System.out.println("Time for vocabulary.txt: " + elapsedTimeTXT + " ns, or " + elapsedTimeTXT / 1_000_000.0 + " ms, or " + elapsedTimeTXT / 1_000_000_000.0 + " s");
                    System.out.println("Time for vocabulary.ser: " + elapsedTimeSER + " ns, or " + elapsedTimeSER / 1_000_000.0 + " ms, or " + elapsedTimeSER / 1_000_000_000.0 + " s");
                    break;
                case 4:
                    System.out.println("Path to vocabulary.txt: " + vTXT.getVocabularyFilePath());
                    System.out.println("Path to vocabulary.ser: " + vSER.getFilePath());
                    break;
                case 5:
                    try {
                        File file = new File(vTXT.getVocabularyFilePath());
                        Desktop.getDesktop().open(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Wrong format");
            }
            System.out.println("\n0 - Print vocabulary.txt;\n1 - Print list of files;\n2 - Show stats;\n3 - Show time needed to form vocabulary;\n4 - Show path to vocabulary files stored on disc;\n5 - Open vocabulary.txt file;\n-1 - " +
                    "Exit\n");
            System.out.println("Your input here: ");
            i = in.nextInt();
        }
    }




}