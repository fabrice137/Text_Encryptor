/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text_encriptor;

/**
 *
 * @author fabruz
 */
public class fxEnc {
    
    
    public static String encryptor(String line){
        
        int[] char2dec = turn2Dec(line); 
        
        String[] twoLetter = new String[char2dec.length];
        for(int i=0 ; i<char2dec.length ; i++) twoLetter[i] = twoletter(char2dec[i]); 

        String unbelievable = makeWord(twoLetter);

        String smarter = smarten(unbelievable, 1);

        String[] textOUT = believable(smarter);
        
        StringBuilder finiBuilder = new StringBuilder();
        for (String textOUT1 : textOUT) {
            finiBuilder.append(randomUpperCase(textOUT1, true)).append(' ');
        }
        return addDigitInSpace(finiBuilder.toString());
    }
    
    public static String decryptor(String line){
        String[] textIN_Arr = replaceDigitWithSpace(line);
        
        String unbelievable = makeWord(textIN_Arr);
        unbelievable = randomUpperCase(unbelievable, false);
        
        String notsmart = smarten(unbelievable, -1);
        
        int smallsize = notsmart.length() / 2;
        
        String[] twoLetter = new String[smallsize];
        
        for(int i=0 ; i<smallsize ; i++) 
            twoLetter[i] = notsmart.charAt(i*2) + "" + notsmart.charAt((i*2)+1);
        
        int[] two2dec = new int[smallsize];
        
        for(int i=0 ; i<smallsize ; i++) two2dec[i] = UN2letter(twoLetter[i]);
        
        char[] dec2char = turn2Char(two2dec);
        
        String textOUT = makeWord(dec2char);
        
        return textOUT; 
    }
    
    
    private static char[] alphaBet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static int [] integerArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5, 6, 5, 4, 3};
    
    private static int[] turn2Dec(String x){     /* returns an int array from a word's character values */
        int[] decims = new int[x.length()];
        for(int i=0 ; i<x.length() ; i++) decims[i] = (int)x.charAt(i);
        return decims;
    }

    private static String twoletter(int x){
        /* THE MOD CAN BE ANY NUMBER BTWN 0 AND 25 TO MATCH ALPHABET  BUT THE DIV CAN ONLY BE SO SMALL
        * THEN WHEN MOD IS 13 OR GREATER THE NEXT LETTER WILL BE <= DIV LETTERS IN THAT DIRECTION
        * WHEN MOD IS 12 OR LESS THE NEXT LETTER WILL BE => DIV LETTERS IN THAT DIRECTION.*/
        int div = x / 26;     int mod = x % 26;
        int next = mod + div;    if(mod > 12) next = mod - div;
        String sol = "" + alphaBet[mod] + alphaBet[next] + "";
        return sol;
    }
    
    private static int UN2letter(String two){
        int mod = two.charAt(0) - 97;     int next = two.charAt(1) - 97;
        
        int div = next - mod;  if(mod > 12) div = mod - next;
        int sol = (26 * div) + mod;

        return sol;
    }

    private static String makeWord(String[] arr){
        /* Taking-in an array of characters and returning a string/word.
        *  the word follows the sequence of the array. */
        StringBuilder sb = new StringBuilder();
        for (String arr1 : arr) sb.append(arr1);
        return sb.toString(); 
    }
    
    private static String makeWord(char[] arr){
        /*  Taking-in an array of characters and returning a string/word.
        *  the word follows the sequence of the array. */
        StringBuilder sb = new StringBuilder();
        for(char ka: arr) sb.append(ka);
        return sb.toString();
    }
    
    private static String randomFirst(String word_string, int do_OR_undo){
        /* do_OR_undo to do(positive) and remove(negative) the effect
        * ADD A RANDOM CHARACTER OR TWO BEFORE THE WORD_STRING  */
        if(do_OR_undo > 0) 
            return alphaBet[(int)(Math.random()*26)] + word_string;
        else 
            return word_string.substring(1);
    }
    
    private static String smarten(String word, int do_OR_undo){
        /* do_OR_undo to do(positive) and remove(negative) the effect
        * TO SCRABBLE WORDS THAT THEY ARE TIED TO THEIR POSITION IN THE STRING
        * in AARON (THE 1st A HAS A DIFFERENT LETTER FROM THE 2nd A
        * PLUS A RANDOM CHARACTER AT THE BEGINNIG OF THE WORD  WITH ITS INT AS RANDOM  */

        if(do_OR_undo > 0) word = randomFirst(word, do_OR_undo);

        int random = (int)word.charAt(0);

        StringBuilder sbSmart = new StringBuilder().append(word.charAt(0));

        for(int i=1 ; i<word.length() ; i++){
            sbSmart.append(shuffleCHAR(word.charAt(i), i, random, do_OR_undo));
        }
        if(do_OR_undo < 0) return sbSmart.substring(1);
        
        return sbSmart.toString();
    }

    private static char shuffleCHAR(char kar, int position, int random, int do_OR_undo){
        /* do_OR_undo to do(positive) and remove(negative) the effect
        * TO SHUFFLE CHARACTERS CONSIDERING THEIR POSITION
        * THE do_OR_undo IS FOR REVERSING THE EFFECT NEGATIVE VS POSITIVE  */
        int adder = random ;
        if(do_OR_undo < 0) adder = -random;

        adder += ((int)kar - 97);
        
        int newPosition = 11 + adder + position ;
        while(newPosition > 25) newPosition = newPosition - 26;
        // THE REVERSER STARTS HERE
        if(do_OR_undo < 0) {
            newPosition = adder - (11 + position);
            while(newPosition < 0) newPosition = newPosition + 26;
        }
        char finkar = alphaBet[newPosition];

        return finkar;
    }
    
    private static String[] believable(String x){ 
        /*ADDING SPACES HERE AND THERE */
        int szBut1 = x.length() - 1;
        StringBuilder sb = new StringBuilder(x);
        
        int at = 0;
        while (at <= szBut1){
            at += integerArray[(int)(Math.random() * 17)];
            if (at < szBut1) sb.insert(at, ' ');
        }
        return line2Arr(sb.toString()); 
    }
    
    private static String[] line2Arr(String phrase){
        /* you enter a phrase and it turns it into an arrayList of individual words. */
        phrase += " ";
        int szP = phrase.length();   // szP = size of the phrase
        
        java.util.ArrayList<String> list = new java.util.ArrayList<>(); 
        StringBuilder builder = new StringBuilder();

        for(int i=0 ; i<szP ; i++) {
            int intKar = (int)phrase.charAt(i);
            if (intKar > 32) builder.append(phrase.charAt(i));
            else if (intKar == 32 && !"".equals(builder.toString())){
                list.add(builder.toString());
                builder.delete(0, builder.length());
            }
            else builder.delete(0, builder.length());
        }
        int newsz = list.size();
        String[] arr = new String[newsz];
        for (int i=0; i<newsz; i++) arr[i] = list.get(i);
        
        return arr;
    }
    
    private static char[] turn2Char(int[] arr){  /* returns an int array's character value array  */
        char[] chars = new char[arr.length];
        for(int i=0 ; i<arr.length ; i++) chars[i] = (char)arr[i];
        return chars;
    }
    
    private static String addDigitInSpace(String any){
        StringBuilder sb = new StringBuilder(any);
        for (int i=0 ; i<any.length() ; i++){
            if(sb.charAt(i) == ' ') 
                sb.replace(i, i+1, "" + (char)('0' + Math.random() * ('9' - '0' + 1)));
        }
        return sb.toString();
    }
    
    private static String[] replaceDigitWithSpace(String any){
        StringBuilder sb = new StringBuilder(any);
        int ka = 0;
        for (int i=0 ; i<any.length() ; i++){
            ka = (int) sb.charAt(i);
            if(ka > 47 && ka < 58) 
                sb.replace(i, i+1, " ");
        }
        return line2Arr(sb.toString()); 
    }
    
    private static String randomUpperCase(String any, boolean do_OR_undo){
        /* WITH TRUE ADD SOME RANDOM UPPERCASE HERE AND THERE WITH FALSE TO REMOVE THEM */
        if(do_OR_undo){
            StringBuilder sb = new StringBuilder(any);
            for (int i=0 ; i<any.length() ; i++){
                if ((int)(Math.random() * 11) %2 == 0)
                    sb.replace(i, i+1, Character.toString(any.charAt(i)).toUpperCase());
            } return sb.toString();
        } return any.toLowerCase();
    }
    
    
}
