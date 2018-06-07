
package hospitalappointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;


/**
 *
 * @author Mseloka
 */
public class PatientsProfile extends BorderPane{
    
    //creates a database connection..we are now ready to execute queries
    MysqlConnector connector = new MysqlConnector();
    
    public PatientsProfile(){
        
        setStyle("-fx-background-color: blue");
        //creating hbox and setting it on the top
        HBox toolbar = new HBox();
        
        //setting padding to 10
        toolbar.setPadding(new Insets(10));
        
        //setting spacing between the buttons
        toolbar.setSpacing(5);
        
        //aligning the contents of the hbox to the center right
        toolbar.setAlignment(Pos.CENTER_RIGHT);
        
        //setting the color of the toolbar
        toolbar.setStyle("-fx-background-color:pink");
        
        //setting the toolbar to the top
        setTop(toolbar);
        
         //creating the table view
        TableView tbview= new TableView();
        
        //creating buttons
        //then handling the event when the button is clicked
        Button addPatient=new Button("Add Patient");
        addPatient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                //creating an instance of the updatePatient class that pops-up the screen when we press the button
                new UpdatePatient();
               
               /* if(connector.insert()== true){
                    
                    //this is an dialog alert the patient record has been successfully added
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                   alert.setTitle("Success");
                   alert.setHeaderText(null);
                   alert.setContentText("Patient has been added successfully");
                   alert.show();
                }
                else{
                    //this is an dialog alert if the patient record is not successfully added
                 Alert alert = new Alert(Alert.AlertType.ERROR);//setting the alert type to error
                   alert.setTitle("Error");//alert heading
                   alert.setHeaderText(null);
                   alert.setContentText("Error occured while trying to add the patient");//setting the content of the error message
                   alert.show();
                }*/
               
            }
        });
        
        
        Button refresh=new Button("Refresh");
        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
           tbview.setItems(connector.getPatients());
           
                
            }
        });
        
        
        Label title=new Label("Patient Profiles");
       
        //creating a region or space between the buttons and the label patient 
        
        Region space=new Region();
        
        //setting the region to always occupy the remaining space of the hbox
        HBox.setHgrow(space,Priority.ALWAYS);
        
        //add children(button in this instance) to the hbox layout
        toolbar.getChildren().addAll(title, space, addPatient, refresh);
        
       
        
        //creating the observable object that is not empty
        final ObservableList<Patient>data=FXCollections.observableArrayList(
        new Patient("1","Lesego","Jameson", "71223445","lj@bitri.co.bw","Female",
                "Plot 324 phase 2","P.O. Box 425 Gabz", "Researcher "));
        
        //Creating the table columns, giving them headings and setting the minimum width
        TableColumn firstNameCol = new TableColumn("FirstName");
        firstNameCol.setCellValueFactory(new PropertyValueFactory("fname"));
        firstNameCol.setMinWidth(100);
        
        TableColumn lastNameCol = new TableColumn("LastName");
        lastNameCol.setCellValueFactory(new PropertyValueFactory("lname"));
        lastNameCol.setMinWidth(200);
      
        TableColumn EmailCol = new TableColumn("Email");
        EmailCol.setCellValueFactory(new PropertyValueFactory("email"));
        EmailCol.setMaxWidth(200);
        
       //adding the columns to the table view 
       //then adding the items in the data observableList to the table view 
       //then setting the table view to the center
       tbview.getColumns().addAll(firstNameCol, lastNameCol,EmailCol);
       tbview.setItems(data);
       setCenter(tbview);
       
        
    
    }
    
    }
    
    
    
  