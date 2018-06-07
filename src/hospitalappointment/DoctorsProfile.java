
package hospitalappointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
public class DoctorsProfile extends BorderPane{
    
    public DoctorsProfile(){
        
        //creates a database connection..we are now ready to execute queries
        MysqlConnector connector = new MysqlConnector();
       
        
        setStyle("-fx-background-color:#ffe6e6");
        
        //creating an hbox at the top of the borderpane
        HBox hb =new HBox();
        
        ////setting padding to 10
        hb.setPadding(new Insets(10));
        
        //setting spacing between the buttons
        hb.setSpacing(5);
        
        //aligning the contents of the hbox to the center right
        hb.setAlignment(Pos.CENTER_RIGHT);
        
        //setting the background color of the box
        hb.setStyle("-fx-background-color:#ff8080");
        
        //setting the hbox to the top of the layout
        setTop(hb);
        
        //creating the table view
        TableView tableview= new TableView();
        
        
        //creating buttons
        //then handling the event when the button is clicked
        Button addDoctor=new Button("Add Doctor");
        addDoctor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                 //creating an instance of the updatePatient class that pops-up the screen when we press the button
                new UpdateDoctor();
              
            }
        });
        
        
        Button ref=new Button("Refresh");
        ref.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //tableview.setItems(connector.getDoctors());
            }
        });
        
        Label tit=new Label("Doctors Profiles");
        
        //creating a region or space between the buttons and the label patient profiles
        Region reg=new Region();
        
        //setting the region to always occupy the remaining space of the hbox
        HBox.setHgrow(reg,Priority.ALWAYS);
        
        //add children(button in this instance) to the hbox layout
        hb.getChildren().addAll(tit, reg, addDoctor, ref);
        
        //creating the observable object that is not empty
        final ObservableList<Doctor>dt=FXCollections.observableArrayList(
        new Doctor("1","Lesego","Jameson","GP","Female"));
        
        //Creating the table columns, giving them headings and setting the minimum width
        TableColumn firstName = new TableColumn("FirstName");
        firstName.setCellValueFactory(new PropertyValueFactory("fname"));
        firstName.setMinWidth(100);
        
        TableColumn lastName = new TableColumn("LastName");
        lastName.setCellValueFactory(new PropertyValueFactory("lname"));
        lastName.setMinWidth(200);
      
        TableColumn SpecialityCol = new TableColumn("Speciality");
        SpecialityCol.setCellValueFactory(new PropertyValueFactory("speciality"));
        SpecialityCol.setMaxWidth(200);
        
         TableColumn GenderCol = new TableColumn("Gender");
        GenderCol.setCellValueFactory(new PropertyValueFactory("Gender"));
        GenderCol.setMaxWidth(200);
        
        //adding the columns to the table view 
       //then adding the items in the data observableList to the table view 
       //then setting the table view to the center
       tableview.getColumns().addAll(firstName, lastName,SpecialityCol,GenderCol);
       tableview.setItems(dt);
       setCenter(tableview);
        
        
        
    }
    
}
