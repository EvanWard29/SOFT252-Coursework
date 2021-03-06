/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;
import java.io.*;
import users.*;
/**
 *
 * @author LoL-1
 */
public class PastAppointment extends Appointment{
    public static PastAppointment[] pastAppointments;
    private Prescription pastPrescription;
    
    public PastAppointment(Doctor Doctor, Patient Patient, String Date, Prescription PastPrescription) {
        super(Doctor, Patient, Date);
        this.pastPrescription = PastPrescription;
    }   

    public Prescription getPastPrescription() {
        return pastPrescription;
    }

    public void setPastPrescription(Prescription pastPrescription) {
        this.pastPrescription = pastPrescription;
    }
    
    public void addPastAppointment(PastAppointment newPastAppointment)
    {
        PastAppointment[] temp = new PastAppointment[pastAppointments.length + 1];
        int i;
        
        for(i = 0;i < temp.length - 1; i++)
        {
            temp[i] = pastAppointments[i];
        }
        
        temp[i] = newPastAppointment;
        pastAppointments = temp;
        
        savePastAppointments();
        getPastAppointments();
    }
    
    public static void getPastAppointments()
    {
        PastAppointment[] temp = null;
        String filename = "data/pastAppointments.ser";
        try
        {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            temp = (PastAppointment[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught: " + ex); 
        } 
          
        catch(ClassNotFoundException ex) 
        { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
        
        pastAppointments = temp;
    }
    
    public static void savePastAppointments()
    {
        String filename = "data/pastAppointments.ser"; 
          
        // Serialization  
        try
        {    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(pastAppointments); 
              
            out.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught: " +  ex); 
        } 
    }
    
    public static void setPastAppointments()
    {
        //DEFAULT OBJECTS SET ONLY ONCE FOR PastAppointments
        
        PastAppointment[] temp = {
            new PastAppointment(
                new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null),
                new Patient("P003", "5f4dcc3b5aa765d61d8327deb882cf99", "Linda", "Bennett", "66 Neswick Street,\nPlymouth,\nPL1 5JN", null, "F", "10/08/1992"),
                "27/11/2019 12:45",
                new Prescription(
                    new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null),
                    new Patient("P003", "5f4dcc3b5aa765d61d8327deb882cf99", "Linda", "Bennett", "66 Neswick Street,\nPlymouth,\nPL1 5JN", null, "F", "10/08/1992"),
                    "Health in perfect condition.\nRecommend dosage decrease.", new Medicine("Penicillin", 5), 6, "1 EVERY 48 HOURS")),
            new PastAppointment(
                new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null),
                new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", null, "M", "29/02/2000"),
                "23/11/2019 10:30",
                new Prescription(
                    new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null),
                    new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", null, "M", "29/02/2000"),
                    "Health dramatically decreasing.\nRecommend Dosage Increase", new Medicine("Chlorpromazine", 5), 8, "1 EVERY 24 HOURS")),
            new PastAppointment(
                new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN", null),
                new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", null, "M", "29/02/2000"),
                "24/11/2019 11:00",
                new Prescription(
                    new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN", null),
                    new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", null, "M", "29/02/2000"),
                    "Condition Impproving.\nRecommend dosage decrease.", new Medicine("Tamoxifen", 3), 9, "1 EVERY 48 HOURS")),
            new PastAppointment(
                new Doctor("D001", "5f4dcc3b5aa765d61d8327deb882cf99", "Joe", "Bloggs", "3 Charles Darwin Road,\nPlymouth,\nPL3 4GU", null),
                new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", null, "M", "29/02/2000"),
                "26/11/2019 13:15",
                new Prescription(
                    new Doctor("D001", "5f4dcc3b5aa765d61d8327deb882cf99", "Joe", "Bloggs", "3 Charles Darwin Road,\nPlymouth,\nPL3 4GU", null),
                    new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", null, "M", "29/02/2000"),
                    "Condition Worsening.\nRecommend Dosage Increase.", new Medicine("Beta Blocker", 15), 25, "4 EVERY 12 HOURS"))
        };
        
        pastAppointments = temp;
    }
}
