import eg.edu.alexu.csd.datastructure.hangman.cs26.IHangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hangman implements IHangman {
    String randomword;
    String[] dictionary ;
    int numberofWords = 0;
    int maxOfGuesses;
    int Leftguesses;
    char[] displayedWord;    //declaring array
    boolean win=false;

    public void readFromDictionary(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("dictionary.txt"));
            String[] words = new String[50];
            String line = br.readLine();
            while (line != null) {
                words[numberofWords] = line;
                numberofWords++;
                line = br.readLine();
            }
            setDictionary(words);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't open file !");
        }

    }

       public void setDictionary(String[] words)  {
           dictionary = new String[numberofWords];
           if (numberofWords >= 0) System.arraycopy(words, 0, dictionary, 0, numberofWords);
        }

      public String selectRandomSecretWord(){
          randomword = dictionary[(int) (Math.random() * numberofWords)];
          displayedWord = new char[randomword.length()];
          Arrays.fill(displayedWord, '-');
          return (randomword);
    }

     public String guess(Character c) throws Exception{

         Pattern pattern = Pattern.compile("[^A-Za-z]"); //checks that the word only contains letters
         Matcher match = pattern.matcher(randomword);
         if(match.find()){
             throw new Exception("Buggy word!");
         }

         c=Character.toLowerCase(c);
         int index = randomword.toLowerCase().indexOf(c);
         if(index==-1)
             Leftguesses--;

         for (int k = 0; k < randomword.length() ; k++) {
             if(k==index) {
                 displayedWord[k]= randomword.charAt(index) ;
                 index = randomword.toLowerCase().indexOf(c, index + 1);
             }
         }

         if(Leftguesses==0)
             throw new Exception("You reached maximum number of wrong guesses!!");
         if(String.valueOf(displayedWord).indexOf('-')==-1)
             win=true;
         return String.valueOf(displayedWord);

    }

    public void setMaxWrongGuesses(Integer max)
    {
        if(max!=0)
            maxOfGuesses = Leftguesses =max;
        else
            maxOfGuesses = Leftguesses = 1;
    }

    }
