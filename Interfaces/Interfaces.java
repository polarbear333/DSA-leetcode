import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/*
 Solve the following problems by reading a text file one line at a time
and performing operations on each line in the appropriate data structure(s). 
Your implementations should be fast enough that even files containing a million lines 
can be processed in a few seconds.
 */

public class Interfaces {
    /*
     Read the input one line at a time and then write the lines out in
    reverse order, so that the last input line is printed first, then the
    second last input line, and so on.

     */

    public static void reverseLines(String filename) throws IOException{
        Stack<String> lines = new Stack<>();
        FileReader file = new FileReader(filename);
        try(BufferedReader reader = new BufferedReader(file)){
            String line;
            while((line = reader.readLine()) != null){
                lines.push(line);
            }
        }
        while (!lines.isEmpty()) {
            System.out.println(lines.pop());
        }
    }

    /*
    Read the first 50 lines of input and then write them out in reverse
    order. Read the next 50 lines and then write them out in reverse order.
    Do this until there are no more lines left to read, at which
    point any remaining lines should be output in reverse order
     */

     public static void fiftyLinesUntilNone(String filename) throws Exception {
        Stack<String> stack = new Stack<>();
        FileReader file = new FileReader(filename);
        try(BufferedReader reader = new BufferedReader(file)){
            int count = 0;
            while(reader.readLine() != null){
                stack.push(reader.readLine());
                count++;
                if(count == 50){
                    while(!stack.isEmpty()){
                        System.out.println(stack.pop());
                    }
                    count = 0;
                }
            }
            if(!stack.isEmpty()){
                System.out.println(stack.pop());
            }
        }
     }

     public static void FourtyTwoLinesProcess(String filename) throws IOException{
        Deque<String> deque = new LinkedList<>();
        FileReader file = new FileReader(filename);

        try(BufferedReader reader = new BufferedReader(file)){
            int count = 0;
            while (reader.readLine() != null) {
                count++;
                deque.addLast(reader.readLine());
            
                if(count > 43){
                    deque.removeFirst();
                }

                if(count > 42 && reader.readLine().isBlank()){
                    System.out.println(deque.getFirst());
                }
            }
        }
     }
        
    /*
    Read the input one line at a time and write each line to the output
    if it is not a duplicate of some previous input line. Take special care
    so that a file with a lot of duplicate lines does not use more memory
    than what is required for the number of unique lines.
    */

     public static void duplicateLines(String filename) throws IOException{
        Set<String> uniqueLines = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(uniqueLines.add(line)){
                    System.out.println(line);
                }

            }
        }
     }
    

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java Exercise1_1 <filename>");
            System.exit(1);
        }

        String filename = args[0];
        reverseLines(filename); 
    }
}
