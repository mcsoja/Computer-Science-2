/* file: Shannon.java
   author: Bob Muller

   CSCI 1102 Computer Science 2

   This is an implementation of C. Shannon's n-gram algorithm
   for English text.
*/
import javafx.scene.Node;

public class Shannon {

  private Node input, output;
  private Integer degree;

  public Shannon(Node input, Integer degree, Node output) {
    this.input = input;
    this.output = output;
    this.degree = degree;
  }

  private String getInputText() {
    return Main.getText(this.input);
  }

  private void setOutputText(String text) {
    Main.setText(this.output, text);
  }

  public void process() {
    String inputText = getInputText(); //returns type String
    int deg = this.degree;
    ModelC m = new ModelC(deg, inputText);
    setOutputText(m.generateText());
  }
}
