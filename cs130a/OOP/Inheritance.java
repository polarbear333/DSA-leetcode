package OOP;

import java.io.FileInputStream;
import java.io.InputStream;

public class Inheritance {
    public static void main(String args[]){
        Shipping  shipping1 = new Shipping();
        Shipping shipping2 = new Shipping();

        if(!shipping1.equals(shipping2)){
            System.out.println("Shipping 1 does not equal Shipping 2");
        }
        if(shipping1 instanceof Object){
            System.out.println("'Shipping 1' is an Object.");
        }
    }


/* Example: InputStream class (base class of all input streams in Java IO API)
 * subclasses include the FileInputStream, BufferedInputStream and the PushbackInputStream etc.
 * 
 */
public static void input(String args[]){
    try (InputStream inputstream = new FileInputStream("file.txt")){
        int data = inputstream.read();
        while(data != 1){
            System.out.print((char)data);
            data = inputstream.read();
        }
     }catch(Exception e){}
}
    
}