
package hospitalappointment;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Mseloka
 */
public class FXMLDocumentController implements Initializable {
    
   @FXML
   private Button Login;
   
   @FXML
   private PasswordField Password;
   
   @FXML
   private TextField Username;
   
   String password = "password";
   String userName= "userName";
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       Login.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               
               //authentication to check the username and password and open the next screen
               
            // if (Password.getText().trim().equals(password) && 
                   // Username.getText().trim().equals(userName)){
                 
                 //calling the next stage after clicking login and the username and password are correct
                 new MainUI();
                 
                 //closing the login stage 
                  HospitalAppointment.loginStage.close();
                 
                 
             }
            /* else{
                 
                 //this is an dialog alert if the username or password is incorrect
                 Alert alert = new Alert(Alert.AlertType.WARNING);
                   alert.setTitle("Authentication failed");
                   alert.setHeaderText(null);
                   alert.setContentText("Please confirm your username and password combination and try again.");
                   alert.show();
             }
           }*/
       });
    }    
    
}
