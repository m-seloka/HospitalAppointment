
package hospitalappointment;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Mseloka
 */
public class UpdateDoctor extends Stage{
    
    
    
    public UpdateDoctor(){
        
     setTitle("Add Doctor");//setting a heading on this stage
     getIcons().add(new Image(MainUI.class.getResourceAsStream("add.png")));//setting the image onto this stage
     
     //creating the root layout which is the borderpane
     BorderPane root = new BorderPane();
     
     //---------------------------------CONTENT----------------------------------------------
    
        GridPane gp = new GridPane(); // setting the grodpane to the center
        root.setCenter(gp); //creating the gridpane
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(2);
        gp.setHgap(2);
        
        
        
       TextField firstname = new TextField();
       gp.add(new Label("FirstName:"), 0, 1);
       gp.add(firstname, 1, 1);
       firstname.setPromptText("First name");
       firstname.setPrefWidth(200);
       
       TextField lastname = new TextField();
       gp.add(new Label("LastName:"), 0, 2);
       gp.add(lastname, 1, 2);
       lastname.setPromptText("Last name");
       lastname.setPrefWidth(200);
       
       TextField speciality = new TextField();
       gp.add(new Label("Speciality:"), 0, 3);
       gp.add(speciality, 1, 3);
       speciality.setPromptText("Speciality");
       speciality.setPrefWidth(200);
       
       ComboBox<String>gn= new ComboBox<>(FXCollections.observableArrayList("Male","Female"));
        gn.setValue(gn.getItems().get(0));
        gp.add(new Label("Gender:"), 0, 4);
        gp.add(gn, 1, 4);
        gn.setPrefWidth(200);
       
        Button send = new Button("Submit");
        send.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
            
             if(firstname.getText().trim().equals("")&&
                     lastname.getText().trim().equals("")&&
                     speciality.getText().trim().equals("")){
                 
                 
                 Alert alert = new Alert(Alert.AlertType.WARNING);
                 alert.setTitle("Warning");
                 alert.setHeaderText(null);
                 alert.setHeaderText("Please check if you have filled all the information");
                 alert.show();
                 
             }
             else{
                 Doctor doc = new Doctor("",firstname.getText().trim(),lastname.getText().trim(),speciality.getText().trim(),gn.getValue());
                 MysqlConnector connector = new MysqlConnector();
                 if(connector.insertDoc(doc)== true){

                         //this is an dialog alert the doctor record has been successfully added
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText(null);
                        alert.setContentText("Doctor has been added successfully");
                        alert.show();
                        
                        //the following line of code are for clearing the text field when you submit
                        firstname.clear();
                        lastname.clear();
                        speciality.clear();
                       
                     }
                     else{
                         //this is an dialog alert if the doctor record is not successfully added
                        Alert alert = new Alert(Alert.AlertType.ERROR);//setting the alert type to error
                        alert.setTitle("Error");//alert heading
                        alert.setHeaderText(null);
                        alert.setContentText("Error occured while trying to add the doctor");//setting the content of the error message
                        alert.show();
                     }
             }
             
         }
     });
       
        
       
        
        
     
     
      HBox hbox = new HBox();
      root.setBottom(hbox);
        
        hbox.setAlignment(Pos.CENTER_RIGHT);//aligning elements in my hbox to the right of the hbox
        hbox.getChildren().addAll(send);//adding the buttons on to the hbox
     
     
     
     //-------------------------------END OF CONTENT-----------------------------------------
     
      Scene sc = new Scene(root, 400, 300);
      setScene(sc);
      setResizable(false);//setting the screen not to be resizable
      initModality(Modality.APPLICATION_MODAL);
      show();
     
    }
    
    
}
