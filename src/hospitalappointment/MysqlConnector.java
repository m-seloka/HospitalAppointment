package hospitalappointment;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Mseloka
 */
public class MysqlConnector {
    
    public static Connection CONNECTION = null;
    public static Statement STATEMENT = null;

    public MysqlConnector() {
        
        try{
            //-- Load the database class driver --
            Class.forName("com.mysql.jdbc.Driver");
            
            //this is the database url, port and the name of the database
            String dbURL = "jdbc:mysql://192.168.100.77:3306/mabel";

            //-- Get database connection handler -- with the username and password
            CONNECTION  =  DriverManager.getConnection(dbURL, "sms", "sms12345");
            if(CONNECTION != null){ 
                //-- create a Statement object for sending SQL statements to the database. --
               STATEMENT = CONNECTION.createStatement();
                System.out.println("Connection to database server has been established");
            }
            else{
                
                System.out.println("Connection failed");
            }
        }catch(Exception error ){
            //-- Print a user friendly error --
            System.err.println(error.getMessage());
        }
    }
    
    /**
     * inserts a record into the database
     * @return 
     */
    public boolean insert(Patient patient){
        
        String query= "insert into `patient` (`id`,`fname`,`lname`,`cell`,`email`,`gender`,`physicalAddress`,`postalAddress`,`occupation`)\n" +
            "VALUES('0',"
                + "'"+patient.getFname()+"',"
                + "'"+patient.getLname()+"',"
                + "'"+patient.getCell()+"',"
                + "'"+patient.getEmail()+"',"
                + "'"+patient.getGender()+"',"
                + "'"+patient.getPhysicalAddress()+"',"
                + "'"+patient.getPostalAddress()+"',"
                + "'"+patient.getOccupation()+"')";
        
        try{
        int result = STATEMENT.executeUpdate(query);
         if(result>0){
             
             return true;
         }
         else{
             
             return false;
         }
        
        
    }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
       
    public ObservableList<Patient>getPatients(){
        ObservableList<Patient>patients=FXCollections.observableArrayList();
        
        String query = "SELECT 'id',fname','lname','cell','email','gender','postalAddress','physicalAddress','occupation'\n"+
                "FROM 'patient'";   
    
        try{
                ResultSet result=STATEMENT.executeQuery(query);
            
                while(result.next()){

                    Patient p = new Patient(result.getString("id"), result.getString("fname"),result.getString("lname"),
                    result.getString("cell"),result.getString("email"),result.getString("gender"),
                    result.getString("postalAddress"),result.getString("physicalAddress"),result.getString("occupation"));


                    patients.add(p);

                }
                return patients;
                
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
                return patients;
                
            }
    }
    
    public boolean insertDoc(Doctor doctor){
        
        String query= "insert into `doctor` (`id`,`fname`,`lname`,`speciality`,`gender`)\n" +
            "VALUES('0',"
                + "'"+doctor.getFname()+"',"
                + "'"+doctor.getLname()+"',"
                + "'"+doctor.getSpeciality()+"',"
                + "'"+doctor.getGender()+"')";
        
        try{
        int result = STATEMENT.executeUpdate(query);
         if(result>0){
             
             return true;
         }
         else{
             
             return false;
         }
        
        
    }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
    
    /*public ObservableList<Patient>getPatients(){
        ObservableList<Patient>patients=FXCollections.observableArrayList();
        
        String query = "SELECT 'id',fname','lname','cell','email','gender','postalAddress','physicalAddress','occupation'\n"+
                "FROM 'patient'";   
    
        try{
                ResultSet result=STATEMENT.executeQuery(query);
            
                while(result.next()){

                    Patient p = new Patient(result.getString("id"), result.getString("fname"),result.getString("lname"),
                    result.getString("cell"),result.getString("email"),result.getString("gender"),
                    result.getString("postalAddress"),result.getString("physicalAddress"),result.getString("occupation"));


                    patients.add(p);

                }
                return patients;
                
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
                return patients;
                
            }
    }*/
}
        
    
    
            
            
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   /**
    * 
    * @param patient
    * @return 
    */
    /*public boolean updatePatient(Patient patient){
        try{
            String query = "INSERT INTO `patients` (`id`, `fname`, `lname`,`cell`, `email`,"
                    + "`postalAddress`, `physicalAddress`, `occupation`, `gender`)"
                    + " VALUES('0', '"+patient.getFname()+"', '"+patient.getLname()+"',"
                    + " '"+patient.getCell()+"' , '"+patient.getEmail()+"', "
                    + " '"+patient.getPostalAddress()+"', '"+patient.getPhysicalAddress()+"',"
                    + " '"+patient.getOccupation()+"', '"+patient.getGender()+"')";
            
            if(!patient.getId().equalsIgnoreCase("")){
                query = "UPDATE `patients` SET "
                    + " `fname`='"+patient.getFname()+"', `cell`='"+patient.getCell()+"',"
                    + " `lname`='"+patient.getLname()+"', `occupation`='"+patient.getOccupation()+"',"
                    + " `email`='"+patient.getEmail()+"',`postalAddress`='"+patient.getPostalAddress()+"',"
                    + " `physicalAddress`='"+patient.getPhysicalAddress()+"',"
                    + " `gender`='"+patient.getGender()+"'"
                    + " WHERE `id`='"+patient.getId()+"'";
            }
            
            return (STATEMENT.executeUpdate(query) > 0)? true : false;
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }*/
    
    
    /**
     * 
     * @param ID
     * @return 
     */
    /*public boolean updateAppointment(Patient patient ){
        try{
            String query = "INSERT INTO `appointment` (`id`, `patientID`, `doctorID`,`description`, `appdate`, `status`)"
                    + " VALUES('0', '"+appointment.getPatientID()+"', '"+appointment.getDoctorID()+"',"
                    + " '"+appointment.getDescription()+"' , '"+appointment.getDate()+"', '"+appointment.getStatus()+"')";
            
            if(!appointment.getId().equalsIgnoreCase("")){
                query = "UPDATE `appointment` SET "
                    + " `patientID`='"+appointment.getPatientID()+"', `description`='"+appointment.getDescription()+"',"
                    + " `appdate`='"+appointment.getDate()+"', `doctorID`='"+appointment.getDoctorID()+"',"
                    + " `status`='"+appointment.getStatus()+"'"
                    + " WHERE `id`='"+appointment.getId()+"'";
            }
            
            return (STATEMENT.executeUpdate(query) > 0)? true : false;
            
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }*/
    
    /*public ObservableList<Appointment> getAppointmentsFor(String ID){
        
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        try{
            String query = " SELECT `id`, `patientID`, `doctorID`, `description`, `appdate`, "
                         + " `status`"
                         + " FROM `appointment` WHERE `doctorID` = '"+ID+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                appointments.add(new Appointment(result.getString("id"), result.getString("patientID"),
                result.getString("doctorID"), result.getString("appdate"), result.getString("description"),
                result.getString("status")));
            }
            return appointments;
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return appointments;
        }
    }*/
    
    
    /**
     * Get patient by ID
     * @return 
     */
    /*public Patient getPatient(){
        
        
            String query = " SELECT `id`, `fname`, `lname`, `cell`, `email`, "
                         + " `physicalAddress`, `postalAddress`, `occupation`, `gender` "
                         + " FROM `patient`" ;
            
            try{ ResultSet result = STATEMENT.executeQuery(query);
            
           if(result.next()){
                return new Patient(result.getString("id"), result.getString("fname"),
                    result.getString("lname"), result.getString("cell"), result.getString("email"),
                    result.getString("gender"), result.getString("physicalAddress"), 
                    result.getString("postalAddress"), result.getString("occupation"));
            }
            return new Patient();
        
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Patient();
        }
    }*/
    
    
    /**
     * Get all patients
     * @return 
     */
    /*public ObservableList<Patient> getPatients(){
        
        ObservableList<Patient> patients = FXCollections.observableArrayList();
        try{
            String query = " SELECT `id`, `fname`, `lname`, `cell`, `email`, "
                         + " `physicalAddress`, `postalAddress`, `occupation`, `gender` "
                         + " FROM `patients`";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                patients.add(new Patient(result.getString("id"), result.getString("fname"),
                result.getString("lname"), result.getString("cell"), result.getString("email"),
                result.getString("gender"), result.getString("physicalAddress"), 
                result.getString("postalAddress"), result.getString("occupation")));
            }
            return patients;
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return patients;
        }
    }*/
    
    /**
     * Get all patients
     * @return 
     */
    /*public ObservableList<String> getPatientNames(){
        
        ObservableList<String> patients = FXCollections.observableArrayList();
        try{
            String query = " SELECT CONCAT_WS(' ',`fname`,`lname`) AS `fullname` FROM `patients`";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                patients.add(result.getString("fullname"));
            }
            return patients;
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return patients;
        }
    }*/
    
    
    /**
     * Get patient profile by name
     * @param name
     * @return 
     */
   /* public Patient getPatientByName(String name){
        try{
            String query = " SELECT `id`, `fname`, `lname`, `cell`, `email`, "
                         + " `physicalAddress`, `postalAddress`, `occupation`, `gender` "
                         + " FROM `patients` WHERE CONCAT_WS(' ',`fname`,`lname`) = '"+name+"'";
            
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Patient(result.getString("id"), result.getString("fname"),
                result.getString("lname"), result.getString("cell"), result.getString("email"),
                result.getString("gender"), result.getString("physicalAddress"), 
                result.getString("postalAddress"), result.getString("occupation"));
            }
            return new Patient();
        } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Patient();
        }
    }*/
   

