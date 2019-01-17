//Molly Soja and Ally Rooney

import java.io.FileReader;
import java.io.IOException;
import edu.princeton.cs.algs4.BinaryOut;
import edu.princeton.cs.algs4.BinaryIn;
import java.io.File;
import java.io.*;
import java.util.*;
import java.util.zip.DataFormatException;

public class Puff {

  private static final int MAGIC_NUMBER = 0x0BC0; //magic(dank) number
  public char addChar; //at the char at the leaf later
  private final String[] args; //get from command line
  //construction
  /**
   * [Puff Constructor]
   * @param String[]args [description]
   */
  public Puff(String[]args){
    this.args = args;
  }

  /**
   * [Go function to run the puff]
   * includes building the Symbol Table, Huffman Tree, and writing the textfile
   */
  private void go(){
  FileIO io = new FileIOC();
  BinaryIn input = io.openBinaryInputFile(args[0]);
  int twoBytes = input.readShort(); //reads the first two bytes
    if( twoBytes != MAGIC_NUMBER){
      System.out.print("Error");
      System.exit(0); //exit
    }
  SymbolTable newST = new SymbolTableC(input); //create new symbol table
    //it puts c and f in for us with the constructor

  HuffmanTree htc = new HuffmanTreeC(newST); //create new huffman tree
  FileWriter outputFile = io.openOutputFile();
  //System.out.println(htc.toString() + "first one");
  //System.out.format("%c %n%n", htc.getSymbol());
  //System.out.println(htc.getWeight());

  //number of characters in the text
  int N = htc.getWeight(); //how many characters we need to write

  for (int i = 1; i < N; i++) { //for loop so that it does it for all characters in text
    HuffmanTree t = new HuffmanTreeC(newST);
    //System.out.println(i);
    //System.out.println("trying:");
    //System.out.println(trying);
    while(!t.isLeaf()) {
      int b = input.readInt(1); //reads the 4 bytes into 1 or 0
      System.out.println(b);
      if(b == 0) {
        t = t.getLeft(); //left if 0 right if 1
      }
      else{ //if b ==1
        t = t.getRight();
      }
    } 
      addChar = (char) t.getSymbol(); //character found at leaf node
      System.out.format("leaf: %c %n", addChar);
      try{
        outputFile.write(addChar);
      }
      catch(IOException exception){
        System.out.println("error");
      }
        System.out.println(t.toString());
  }
  try {
    outputFile.close();
    }
    catch(IOException exception2) {
      System.out.println("error close");
    }

  }
  public static void main(String[] args){
    new Puff(args).go();
  }
}
