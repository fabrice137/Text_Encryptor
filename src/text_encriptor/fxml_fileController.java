/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text_encriptor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import static text_encriptor.fxEnc.encryptor;
import static text_encriptor.fxEnc.decryptor;
public class fxml_fileController implements Initializable {
    
    @FXML
    private TextField textField;
    
    @FXML 
    private RadioButton radioButton;
    
    @FXML
    private TextArea textArea;
    
    String regularString;
    String codedString;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textArea.setWrapText(true); 
    }    
    
    
    @FXML
    public void encrypt(ActionEvent event){
        regularString = textField.getText();
        codedString = encryptor(regularString);
        textArea.setText(codedString); 
    }
    
    @FXML
    public void decrypt(ActionEvent event){
        codedString = textField.getText();
        regularString = decryptor(codedString);
        textArea.setText(regularString); 
    }
    
    @FXML
    public void clickRadio(ActionEvent event){
        boolean isSelected = radioButton.isSelected();
        textArea.setEditable(isSelected); 
    }
    
    @FXML
    public void code(ActionEvent event){
        regularString = textArea.getText();
        String[] lines = manyLines(regularString);
        for (int i=0; i<lines.length ; i++){
            lines[i] = encryptor(lines[i]);
        }
        
        codedString = toPrint(lines);
        textArea.setText(codedString); 
    }
    
    @FXML
    public void decode(ActionEvent event){
        codedString = textArea.getText();
        String[] lines = manyLines(codedString);
        for (int i=0; i<lines.length ; i++){
            lines[i] = decryptor(lines[i]);
        }
        
        regularString = toPrint(lines);
        textArea.setText(regularString); 
    }
    
    public static String[] manyLines(String txt){
        String[] out = txt.split("\n");
        return out;
    }
    
    public static String toPrint(String[] txt){
        StringBuilder sb = new StringBuilder();
        for (String txt1 : txt) {
            sb.append(txt1).append("\n");
        }
        return sb.toString();
    }
    
}
