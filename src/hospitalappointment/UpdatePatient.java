
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
public class UpdatePatient extends Stage{
    
    public UpdatePatient(){
        
        setTitle("Add Patient");//setting a heading on this stage
        getIcons().add(new Image(MainUI.class.getResourceAsStream("patient.png")));//setting the image onto this stage
        BorderPane root = new BorderPane();//creating the root layout which is the borderpane
        
      
        //-------Content goes here-----
        
        
       GridPane grid = new GridPane();
       root.setCenter(grid);
       grid.setAlignment(Pos.CENTER);
       grid.setVgap(2);
       grid.setHgap(2);
        
       TextField firstname = new TextField();
       grid.add(new Label("FirstName:"), 0, 1);
       grid.add(firstname, 1, 1);
       firstname.setPromptText("First name");
       firstname.setPrefWidth(200);
       
      
       TextField lastname = new TextField();
       grid.add(new Label("LastName:"), 0, 2);
       grid.add(lastname, 1, 2);
       lastname.setPromptText("Last name");
       lastname.setPrefWidth(200);
       
       TextField cl = new TextField();
        grid.add(new Label("Cell:"), 0, 3);
        grid.add(cl, 1, 3);
        cl.setPromptText("Cellphone number");
        
        
        TextField em = new TextField();
        grid.add(new Label("Email:"), 0, 4);
        grid.add(em, 1, 4);
        em.setPromptText("Email");
        
       
        ComboBox<String>gen= new ComboBox<>(FXCollections.observableArrayList("Male","Female"));
        gen.setValue(gen.getItems().get(0));
        grid.add(new Label("Gender:"), 0, 5);
        grid.add(gen, 1, 5);
        gen.setPrefWidth(200);
        
        
    
        TextField pyAd = new TextField();
        grid.add( new Label("PhysicalAddress:"), 0, 6);
        grid.add(pyAd, 1, 6);
        pyAd.setPromptText("Physical Address");
     
      
       
        TextField postAd =new TextField();
        grid.add(new Label("PostalAddress:"), 0, 7);
        grid.add(postAd, 1, 7);
        postAd.setPromptText("Postal Address");
        
      
       
       TextField occ = new TextField();  
       grid.add(new Label("Occupation:"), 0, 8);
       grid.add(occ, 1, 8);
       occ. setPromptText("Occupation");
       
      
       
        Button sub = new Button("Submit");
        sub.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                if(firstname.getText().trim().equals("")&&
                        lastname.getText().trim().equals("")&&
                        cl.getText().trim().equals("")&&
                        em.getText().trim().equals("")&&
                        pyAd.getText().trim().equals("")&&
                        postAd.getText().trim().equals("")&&
                        occ.getText().trim().equals(""))
                    {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                   alert.setTitle("Warning");
                   alert.setHeaderText(null);
                   
                   alert.setContentText("Ensure you've entered all fields");
                   alert.show();
                }
                else{
                    
                    Patient pat= new Patient("", firstname.getText().trim(), lastname.getText().trim(), cl.getText().trim(), em.getText().trim(), gen.getValue(),pyAd.getText().trim(), postAd.getText().trim(), occ.getText().trim());
                    MysqlConnector connector = new MysqlConnector();
                    if(connector.insert(pat)== true){

                         //this is an dialog alert the patient record has been successfully added
                      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText(null);
                        alert.setContentText("Patient has been added successfully");
                        alert.show();
                        
                        //the following line of code are for clearing the text field when you submit
                        firstname.clear();
                        lastname.clear();
                        cl.clear();
                        em.clear();
                        pyAd.clear();
                        postAd.clear();
                        occ.clear();
                     }
                     else{
                         //this is an dialog alert if the patient record is not successfully added
                      Alert alert = new Alert(Alert.AlertType.ERROR);//setting the alert type to error
                        alert.setTitle("Error");//alert heading
                        alert.setHeaderText(null);
                        alert.setContentText("Error occured while trying to add the patient");//setting the content of the error message
                        alert.show();
                     }
                }            
                    
            }
        });
        
        
        
        HBox hb = new HBox();
        root.setBottom(hb);
        
        
        
        
        Button clear= new Button("clear");
        hb.setAlignment(Pos.CENTER_RIGHT);//aligning elements in my hbox to the right of the hbox
        hb.getChildren().addAll(clear,sub);//adding the buttons on to the hbox
        
        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
           
                
                //the following line of code is for clearing the text fields with the button clear
               firstname.clear();
               lastname.clear();
               cl.clear();
               em.clear();
               pyAd.clear();
               postAd.clear();
               occ.clear();
                
                
            }
        });
        
       
        
        
        //------End of Content---------
        
        
        
  
        Scene scene = new Scene(root, 400, 300);
        setScene(scene);
        setResizable(false);//setting the screen not to be resizable
        initModality(Modality.APPLICATION_MODAL);
        show();
    }
   
    
}