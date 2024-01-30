import java.io.*;
import java.util.ArrayList;

public class Vocabulary {


    public String[] vocab;
    private int init_capacity;
    private int num;
    private int numTotal;
    private double vocabSize;
    private ArrayList<Double> fileSizes;
    private ArrayList<String> fileNames;

    private String vocabPath;

    //constructor
    public Vocabulary(String folder){
        File dir = new File(folder);
        File[] files = dir.listFiles();

        init_capacity = files.length;
        fileSizes = new ArrayList<>();
        fileNames = new ArrayList<>();
        vocab = new String[init_capacity];

        for (File file : files) {

            if(file.isFile()) {
                fileSizes.add(file.length()/1024.0);
                fileNames.add(file.getName()); // Store the file name
                BufferedReader br = null;
                String line;
                try {

                    br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        addWords(line);
                    }

                } catch(IOException e) {
                    System.out.println(e);
                }
                finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("vocabulary.txt"));
            bw.write(toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        vocabSize = new File("vocabulary.txt").length()/1024.0;
        vocabPath = new File("vocabulary.txt").getAbsolutePath();
    }

    //checks words in a line and adds them to an array
    private void addWords(String line){
        if(line.equals("")) return;
        String[] temp = line.split("[\\W]+");
        for(int i = 0; i<temp.length; i++){
            if(temp[i].matches("[a-zA-Z0-9_]+")) {
                numTotal++;
                addWord(temp[i].toLowerCase());
            }
        }
    }

    public int length(){
        return vocab.length;
    }

    public int number(){
        return num;
    }

    public int numberTotal(){
        return numTotal;
    }

    //make room for a word in an array
    private void shift(int idx){
        if(num>=length()-1) {
            String[] res = new String[length() * 2];
            for (int i = 0; i < idx; i++) {
                res[i] = vocab[i];
            }
            for(int i=num; i>=idx; i--){
                res[i+1] = vocab[i];
            }
            vocab = res;
        }
        else{
            for(int i=num; i>=idx; i--){
                vocab[i+1] = vocab[i];
            }
        }

    }

    private int index(String word) {
        return binarySearch(0, num -1, word);
    }

    //adds words to an array
    private void addWord(String word){
        int idx = index(word);
        if(idx < length() && word.equals(vocab[idx])) return;
        shift(idx);
        vocab[idx]=word;
        num++;
    }


    //search for a place to put a word in
    private int binarySearch(int first, int last, String word) {
        int res;
        if (first > last) res = first;
        else {
            int mid = first + (last - first) / 2;
            String midWord = vocab[mid];
            if (word.equals(midWord)) res = mid;
            else if (word.compareTo(midWord) < 0){
                res = binarySearch(first, mid - 1, word);}
            else res = binarySearch(mid + 1, last, word);
        }
        return res;
    }

    //returns statistics
    public String Stats(){
        String s = "Size of a collection: ";
        double size=0;

        for(int i=0; i<fileSizes.size(); i++){
            size += fileSizes.get(i);
            s += "\nFile " + (i+1) + ": " + fileNames.get(i) + " - " + fileSizes.get(i) + " kb";
        }

        s += "\n\nTotal: " + size + " kb";

        s += "\nNumber of words in a collection: " + numTotal;

        s += "\nVocabulary size: " + vocabSize + " kb";

        s += "\nNumber of words in a vocabulary: " + num;
        return s;
    }

    //returns elements of a created vocab
    public void print (){
        OutputStream out = new BufferedOutputStream( System.out );
        String res="";
        for(int i=0;i<num;i++){
            try {
                out.write((vocab[i]+"\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //returns the path to the vocabulary.txt file on disc
    public String getVocabularyFilePath() {
        File file = new File("vocabulary.txt");
        return file.getAbsolutePath();
    }

    public String toString(){
        String res = "";
        for(int i = 0; i < num; i++){
            res += vocab[i] + "\n";
        }
        return res;
    }
}