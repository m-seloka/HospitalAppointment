
package hospitalappointment;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Mseloka
 */
public class MainUI extends Stage {

    public MainUI() {
        
        setTitle("Hospital Appointment System");//setting a heading on this stage
        getIcons().add(new Image(MainUI.class.getResourceAsStream("clinic.png")));//setting the image onto this stage
        BorderPane root = new BorderPane();//creating the root layout which is the borderpane
        
       //adding another layout
        TabPane tabpane = new TabPane();
        
       //creating tabs
       Tab dashboard =new Tab("Dashboard");
       dashboard.setClosable(false);//setting the tab so that they shouldn't be able to be exited
       tabpane.getTabs().add(dashboard);//adding tabs to a tab pane
       
       //creating patients tab, setting it not to be closable and adding it to the tab pane
       Tab patients=new Tab("Patients Profile");
       patients.setClosable(false);
       tabpane.getTabs().add(patients);
       
       //creating appointment tab, setting it not to be closable and adding it to the tab pane
       Tab appointments=new Tab("Appointments");
       appointments.setClosable(false);
       tabpane.getTabs().add(appointments);
       
       //creating doctor tab, setting it not to be closable and adding it to the tab pane
       Tab doctor=new Tab("Doctor");
       doctor.setClosable(false);
       tabpane.getTabs().add(doctor);
       
       
       
       root.setCenter(tabpane);//setting the tab on the root layout
       
       //adding content to tabs
       PatientsProfile profile = new PatientsProfile();
       patients.setContent(profile);
       
       DoctorsProfile prof = new DoctorsProfile();
       doctor.setContent(prof);
        
        
        
        //--------------------------------------
        Scene scene = new Scene(root, 800, 500);
        setScene(scene);
        show();
        
        
    }
    
    

}
