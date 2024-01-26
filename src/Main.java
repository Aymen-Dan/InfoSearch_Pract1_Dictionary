import java.util.Scanner;
public class Main {
    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        long startTime = System.nanoTime();//start time

        Vocabulary v = new Vocabulary("src/res"); // CREATE VOCAB

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime; //final time it took

        System.out.println("\n1 - Show vocabulary;\n2 - Show stats;\n3 - Show time needed to form vocabulary\n0 - " +
                "Exit\n");

        int i = in.nextInt();

        while(i!=0) {
            switch (i) {
                case 1:
                    v.print();
                    break;
                case 2:
                    System.out.println(v.Stats());
                    break;
                case 3:
                    System.out.println("time: " + elapsedTime / 1000000000 + " s");
                    break;
                default:
                    System.out.println("Wrong format");
            }
            System.out.println("\n1 - Show vocabulary;\n2 - Show stats;\n3 - Show time needed to form vocabulary\n0 -" +
                    "Exit\n");
            i = in.nextInt();
        }
    }
}