import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static String usernameCheck() {
        Scanner scanner = new Scanner(System.in);
        String username;
        boolean check=false;
        //do while loop is used to validate the username
        do {
            System.out.print("Please enter username: ");
            String input = scanner.nextLine();
            //while loop checks username is only using english alphabet letters
            while (!input.matches("[a-zA-Z]+$")) {
                //user will be asked to enter again if incorrect characters are used
                System.out.printf("\"%s\" is not a valid username, must not have spaces or numbers.%n", input);
                System.out.print("Please enter username: ");
                input = scanner.nextLine();
                check=false;
            }
            //stores valid username
            username= input;
            //check = true will stop do while loop
            check=true;
        } while (check==false);
        System.out.println(" Your username: "+ username);
        return username;
    }
    static String wordCheck(){
        Scanner scanner = new Scanner(System.in);
        String word;
        boolean check=false;
        //do while loop is used to validate the word
        do {
            System.out.print("Please enter valid word: ");
            String input = scanner.nextLine();
            //while loop checks the word is only using english alphabet letter are used
            while (!input.matches("[a-zA-Z]+$")) {
                //user will be asked to enter again if incorrect characters are used
                System.out.printf("\"%s\" is not a valid word, must not have spaces or numbers.%n", input);
                System.out.print("Please enter valid word: ");
                input = scanner.nextLine();
                check=false;
            }
            //stores valid username
            word= input;
            //check = true will stop do while loop
            check=true;
        } while (check==false);
        System.out.println(" The word entered is: "+ word);
        return word;
    }

    public static void main(String[] args) {
        List<String> values = new ArrayList<>();
        boolean anagram;
        Scanner scanner = new Scanner(System.in);
        File file= new File("data.txt");
        if (file.exists()){
            try {
                int i=0;
                Scanner inputFile= new Scanner(file);
                while (inputFile.hasNext()){values.add(inputFile.nextLine());}
                for (String value:values){System.out.println(value);}
            } catch (FileNotFoundException e) {throw new RuntimeException(e);}
        }
        //call method for creating username
        String username=usernameCheck();
        System.out.println("\nYou will now enter two words to check if they are an Anagram");
        //call method for creating words for anagram check
        String str1 =wordCheck();
        String str2 =wordCheck();
        //store words into an Array as char
        char arr1[]=str1.toCharArray();
        char arr2[]=str2.toCharArray();
        //sort the characters of each array into alphabetical order
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        //store the characters of each array to a string
        String sortedstr1= new String(arr1);
        String sortedstr2= new String(arr2);
        //if both strings match then both words are anagram
        if (sortedstr1.equals(sortedstr2)){
            System.out.println("Is an Anagram");
            anagram=true;
        }else {
            System.out.println("Is not an Anagram");
            anagram=false;
        }
        String inputCheck="name:"+ username+", Word1:"+str1+", Word2:"+str2+", Anagram:"+anagram;
        System.out.println(inputCheck);
        boolean dataMatch= false;
        for (String value:values){if(value.matches(inputCheck)){dataMatch=true;}}
        if (dataMatch==false) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt", true));
                writer.write(inputCheck+"\n");
                writer.close();
                System.out.print("This information has been saved");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {System.out.print("This information has been used before");}
    }
}