//encoded.txt is smaller than input.txt because it contains less characters.
//encoded2.txt is still smaller than input.txt because of the same reason,
//however it is larger than encoded.txt because it contains larger integers.

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
//import java.util.Arrays;
import java.util.Random;

public class lab02{

    public static void mapAndEncodedTextBuilder1(String inputTextFileName, String outputTextFileName) {
        ArrayList<String> wordList = new ArrayList<String>();

        try{
            FileReader reader = new FileReader(inputTextFileName);
            BufferedReader bufferedReader = new BufferedReader(reader);
            FileWriter writerForMap = new FileWriter("map.txt");

            FileWriter writerForOutput = new FileWriter(outputTextFileName);


            String word;            
            String line;
            while((line = bufferedReader.readLine()) != null){

                boolean endOfLine = false;
                while(endOfLine == false){
                    if(line.indexOf(' ') == -1){
                        word = line;
                        endOfLine = true;
                    }
                    else{
                        word = line.substring(0,line.indexOf(' '));
                    }
                    
                    if(!wordList.contains(word)){
                        wordList.add(word);
                        writerForMap.write(wordList.size()-1 + ": " + word);
                        writerForMap.write("\n");;
                    }

                    int indexOfWordInWordList = -1;
                    for(int i = 0; i<wordList.size(); i++){
                        if(wordList.get(i).equals(word)){
                            indexOfWordInWordList = i;
                        }
                    }
                    writerForOutput.write(""+indexOfWordInWordList);


                    if(endOfLine == false){
                        line = line.substring(line.indexOf(' ')+1, line.length());
                        writerForOutput.write(" ");
                    }
                    else{
                        writerForOutput.write("\n");
                    }
                }
            }
            reader.close();
            writerForMap.close();
            writerForOutput.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void mapAndEncodedTextBuilder2(String inputTextFileName, String outputTextFileName) {
        ArrayList<String> wordList = new ArrayList<String>();

        try{
            FileReader reader = new FileReader(inputTextFileName);
            BufferedReader bufferedReader = new BufferedReader(reader);
            FileWriter writerForMap = new FileWriter("map2.txt");
            FileWriter writerForOutput = new FileWriter("encoded2.txt");

            
            String word;
            String line;
            while((line = bufferedReader.readLine()) != null){
                boolean endOfLine = false;
                while(endOfLine == false){
                    if(line.indexOf(' ') == -1){
                        word = line;
                        endOfLine = true;
                    }
                    else{
                        word = line.substring(0,line.indexOf(' '));
                    }
                    
                    if(!wordList.contains(word)){
                        wordList.add(word);
                    }                   

                    if(endOfLine == false){
                        line = line.substring(line.indexOf(' ')+1, line.length());
                    }
                }
        }
        /*
         Randomizer --> The commented parts were for a previous design which I thought did not randomize sufficiently.
         To use that old version, you can delete the for second for loop.*/

        //Boolean[]isThisIndexRandomized = new Boolean[wordList.size()];
        //Arrays.fill(isThisIndexRandomized, Boolean.FALSE);
        Random rand = new Random();
        for(int i=0; i<wordList.size(); i++){
            int randomIndex = rand.nextInt(wordList.size());
            String temp;
            //if(isThisIndexRandomized[i] == false && isThisIndexRandomized[randomIndex] == false){                
                temp = wordList.set(i, wordList.get(randomIndex));
                temp = wordList.set(randomIndex, temp);
                // isThisIndexRandomized[i] = true;
              //  isThisIndexRandomized[randomIndex] = true;
            //}
           // writerForMap.write(i + ": " + wordList.get(i) + "\n");
        }
        ArrayList<Integer> randomMap = new ArrayList<Integer>();
        int generatedNumber = rand.nextInt(100);
        randomMap.add(generatedNumber);

        for(int i = 0; i< wordList.size(); i++){
            while(randomMap.contains(generatedNumber) == true){
                generatedNumber = rand.nextInt(100);
            }
            randomMap.add(generatedNumber);
        }

        for(int i=0; i<wordList.size(); i++){
            writerForMap.write(randomMap.get(i) + ": " + wordList.get(i) + "\n");
        }

        FileReader reader2 = new FileReader(inputTextFileName);
        BufferedReader bufferedReader2 = new BufferedReader(reader2);

        boolean firstLoop = true;
        while((line = bufferedReader2.readLine()) != null){
            if(firstLoop == false){
                writerForOutput.write("\n");
            }
            firstLoop = false;
            boolean endOfLine = false;
            while(endOfLine == false){
                if(line.indexOf(' ') == -1){
                    word = line;
                    endOfLine = true;
                }
                else{
                    word = line.substring(0,line.indexOf(' '));
                }
                boolean isBreak = false;
                for(int i = 0; i<wordList.size() && isBreak == false; i++){
                    if(word.equals(wordList.get(i))){
                        writerForOutput.write("" + randomMap.get(i));
                        isBreak = true;
                    }
                }

                if(endOfLine == false){
                    line = line.substring(line.indexOf(' ')+1, line.length());
                    writerForOutput.write(" ");
                }
            }
    }
    writerForMap.close();
    writerForOutput.close();
    reader2.close();
    reader.close();
    }
    catch(IOException e){
        e.printStackTrace();
    }
    }
    public static void decodedBuilder(String mapName, String encodedName){
        try{
            FileReader readerForMap = new FileReader(mapName);
            BufferedReader bufferedReaderForMap = new BufferedReader(readerForMap);
            FileReader readerForLineCount = new FileReader(mapName);
            BufferedReader bufferedReaderForLineCount = new BufferedReader(readerForLineCount);
            FileReader readerForEncoded = new FileReader(encodedName);
            BufferedReader bufferedReaderForEncoded = new BufferedReader(readerForEncoded);
            FileWriter writer = new FileWriter("decoded.txt");
            
            int lineCount = 0;
            while(bufferedReaderForLineCount.readLine() != null){
                lineCount ++;
            }
            String[] map = new String[lineCount];
            int[] intMap = new int[lineCount];
            int mapElementCount = 0;
            int intMapElementCount = 0;
            String line;
            String word;
            int mapNumber;
            
            //The array is built
            while((line = bufferedReaderForMap.readLine()) != null){
                mapNumber = Integer.parseInt(line.substring(0,line.indexOf(":")));
                word = line.substring(line.indexOf(":")+2,line.length());
                map[mapElementCount++] = word;
                intMap[intMapElementCount++] = mapNumber;
            }

            //Now building decoded file
            while((line = bufferedReaderForEncoded.readLine()) != null){

                boolean endOfLine = false;
                while(endOfLine == false){
                    if(line.indexOf(' ') == -1){
                        word = line;
                        endOfLine = true;
                    }
                    else{
                        word = line.substring(0,line.indexOf(' '));
                    }
                    

                    int indexOfWordInWordList = -1;
                    for(int i = 0; i<intMap.length; i++){
                      if(intMap[i] == Integer.parseInt(word)){
                        indexOfWordInWordList = i; 
                      }                      
                    }
                    writer.write(map[indexOfWordInWordList]);


                    if(endOfLine == false){
                        line = line.substring(line.indexOf(' ')+1, line.length());
                        writer.write(" ");
                    }
                    else{
                        writer.write("\n");
                    }
                }
            }


            readerForMap.close();
            readerForLineCount.close();
            readerForEncoded.close();
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
    mapAndEncodedTextBuilder1("input.txt", "encoded.txt");
    mapAndEncodedTextBuilder2("input.txt", "encoded2.txt");
    decodedBuilder("map2.txt", "encoded2.txt");
}
}