/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Dialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.text.SimpleDateFormat;  
import java.util.Date;
import users.*;
import system.*;
import javax.swing.*;

/**
 *
 * @author EvanWard
 */
public class PatientMenu extends javax.swing.JFrame {

    /**
     * Creates new form MainMenu
     */
    public PatientMenu() {
        initComponents();
        getUserInfo();
        getDoctors();
        getNextAppointment();
        getPrescription();
        getPastAppointments();
        setRatings();
    }
    
    private void getUserInfo()
    {
        Patient patient = (Patient) User.loggedUser;
        String dob = patient.getDOB();
        String gender = patient.getGender();
        Notification notification = patient.getNotification();
        
        if(notification != null)
        {
            JOptionPane.showMessageDialog(this, notification.getMessage(), "WELCOME", 
                    JOptionPane.INFORMATION_MESSAGE);
            patient.setNotification(null);
            Patient.saveUsers();
        }
        
        this.txtUserAccountType.setText("Patient");
        this.txtUserID.setText(User.loggedUser.getID());
        this.txtUserName.setText(User.loggedUser.getForename() + " " + User.loggedUser.getSurname());
        this.txtUserAddress.setText(User.loggedUser.getAddress());
        this.txtUserAge.setText(dob);
        this.txtUserGender.setText(gender);
    }
    
    private void getDoctors()
    {
        
        String[] doctors = new String[Doctor.doctors.length + 1];
        doctors[0] = "Select Doctor";
        int i = 1;
        
        for(Doctor doctor : Doctor.doctors)
        {
            String name = doctor.getForename() + " " + doctor.getSurname();
            
            doctors[i] = name;
            i++;
        }
        
        DefaultComboBoxModel model = new DefaultComboBoxModel(doctors);
        this.cmbDoctor.setModel(model);
        
        DefaultComboBoxModel doctor = new DefaultComboBoxModel(doctors);
        this.cmbRateDoctors.setModel(doctor);
        
        DefaultComboBoxModel rate = new DefaultComboBoxModel(doctors);
        this.cmbDoctorFeedback.setModel(rate);
    }
   
    
    private void getNextAppointment()
    {
        for(Appointment appointment : Appointment.appointments)
        {
            if(appointment.getPatient().getID().equals(User.loggedUser.getID()))
            {
                this.txtNextDate.setText(appointment.getDate());
                String doctor = appointment.getDoctor().getForename() + " " + appointment.getDoctor().getSurname();
                this.txtNextDoctor.setText(doctor);
                break;
            }
        }
    }
    
    private void getPrescription()
    {
        //Prescription newPrescription = new Prescription(DoctorID, PatientID, Notes, Medicine, Quantity, Dosage);
        
        //FOREACH PRESCRIPTION
            //GET PATIENT NAME FROM ID
            //GET DOCTOR NAME FROM ID
            //GET NOTES
            //GET MEDICINE
                //FROM MEDICINE GET NAME
            //GET QUANTITY
            //GET DOSAGE

            //OUTPUT DATA INTO TABLE
        //END FOREACH
        
        for(Prescription prescription : Prescription.prescriptions)
        {
            if(prescription.getPatient().getID().equals(User.loggedUser.getID()))
            {
                Patient patient = prescription.getPatient();
                
                this.txtPatientName.setText(patient.getForename() + " " + patient.getSurname());
                this.txtPatientAddress.setText(patient.getAddress());
                this.txtPatientGender.setText(patient.getGender());
                this.txtPatientDOB.setText(patient.getDOB());
                
                Doctor doctor = prescription.getDoctor();
                
                this.txtDoctorName.setText(doctor.getForename() + " " + doctor.getSurname());
                this.txtDoctorAddress.setText(doctor.getAddress());
                this.txtPrescriptionNotes.setText(prescription.getNotes());
                
                Medicine medicine = prescription.getMedicine();
                
                String[] medicineTbl = {medicine.getName(), Integer.toString(prescription.getQuantity()),
                    prescription.getDosage()};
                
                DefaultTableModel model = (DefaultTableModel) this.tblMedicine.getModel();
                model.addRow(medicineTbl);
                break;
            }
        }
    }
    
    private void getPastAppointments()
    {
        int i = 0;
        for(PastAppointment pastAppointment : PastAppointment.pastAppointments)
        {
            if(pastAppointment.getPatient().getID().equals(User.loggedUser.getID()))
            {
                i++;
            }
        }
        
        String[][] pastAppointments = new String[i][3];
        i = 0;
        
        for(PastAppointment pastAppointment : PastAppointment.pastAppointments)
        {
            if(pastAppointment.getPatient().getID().equals(User.loggedUser.getID()))
            {
                Doctor doctor = pastAppointment.getDoctor();
                String doctorName = doctor.getForename() + " " + doctor.getSurname();
                String date = pastAppointment.getDate();
                String notes = pastAppointment.getPastPrescription().getNotes();
                
                pastAppointments[i][0] = doctorName;
                pastAppointments[i][1] = date;
                pastAppointments[i][2] = notes;
                
                i++;
            }
        }
        
        DefaultTableModel model = (DefaultTableModel) this.tblPastAppointments.getModel();
        for(String[] appointment : pastAppointments)
        {
            model.addRow(appointment);
        }
    }
    
    private void setRatings()
    {
        String[] ratings = {"Select Rating","1","2","3","4","5","6","7","8","9","10"};
        for(String i : ratings)
        {
            DefaultComboBoxModel model = new DefaultComboBoxModel(ratings);
            this.cmbRating.setModel(model);
        }
    }
    
    private void checkPassword(String id, String password)
    {
        
    }
        

    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMain = new java.awt.Label();
        menuPatient = new javax.swing.JTabbedPane();
        tabUserInfo = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        lblUserInfo1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblAccountType1 = new javax.swing.JLabel();
        txtUserAccountType = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        lblUserID1 = new javax.swing.JLabel();
        txtUserID = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        lblUserName1 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        lblUserName2 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtUserAddress = new javax.swing.JTextArea();
        jPanel9 = new javax.swing.JPanel();
        lblUserAge1 = new javax.swing.JLabel();
        txtUserAge = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        lblUserGender1 = new javax.swing.JLabel();
        txtUserGender = new javax.swing.JTextField();
        tabRequestAccount = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        lblRequestAppointment = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblAccountType2 = new javax.swing.JLabel();
        cmbDoctor = new javax.swing.JComboBox<>();
        lblSelectDate = new javax.swing.JLabel();
        lblSelectTime = new javax.swing.JLabel();
        txtEnterDate = new javax.swing.JTextField();
        txtEnterTime = new javax.swing.JTextField();
        lblAppointmentInfo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblConfirmAppointment = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblDoctor = new javax.swing.JLabel();
        txtDoctor = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        lblDate = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        lblTime = new javax.swing.JLabel();
        txtTime = new javax.swing.JTextField();
        btnConfirm = new javax.swing.JButton();
        tabViewAppointment = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        lblViewAppointment = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        lblDoctor1 = new javax.swing.JLabel();
        txtNextDoctor = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        lblDate3 = new javax.swing.JLabel();
        txtNextDate = new javax.swing.JTextField();
        lblViewAppointment1 = new javax.swing.JLabel();
        tabViewPrescription = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        lblViewPrescription = new javax.swing.JLabel();
        pnlPrescription = new javax.swing.JPanel();
        lblCurrentPrescription = new javax.swing.JLabel();
        pnlPatientInfo = new javax.swing.JPanel();
        lblPatientInfo = new javax.swing.JLabel();
        lblPatientName = new javax.swing.JLabel();
        lblPatientAddr = new javax.swing.JLabel();
        lblSex = new javax.swing.JLabel();
        lblDOB = new javax.swing.JLabel();
        txtPatientName = new javax.swing.JTextField();
        txtPatientGender = new javax.swing.JTextField();
        txtPatientDOB = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPatientAddress = new javax.swing.JTextArea();
        pnlDoctorInfo = new javax.swing.JPanel();
        lblDoctorInfo = new javax.swing.JLabel();
        lblDoctorName = new javax.swing.JLabel();
        txtDoctorName = new javax.swing.JTextField();
        lblDoctorAddr = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDoctorAddress = new javax.swing.JTextArea();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMedicine = new javax.swing.JTable();
        pnlNotes = new javax.swing.JPanel();
        lblNotes = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtPrescriptionNotes = new javax.swing.JTextArea();
        tabViewHistory = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        lblViewPrescription1 = new javax.swing.JLabel();
        pnlPrescription1 = new javax.swing.JPanel();
        lblCurrentPrescription1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblPastAppointments = new javax.swing.JTable();
        jPanel25 = new javax.swing.JPanel();
        lblPastDoctorName = new javax.swing.JLabel();
        txtPastDoctor = new javax.swing.JTextField();
        lblPastDate = new javax.swing.JLabel();
        txtPastDate = new javax.swing.JTextField();
        lblPastNote = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtPastNote = new javax.swing.JTextArea();
        tabDoctorFeedback = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        lblRateDoctorName = new javax.swing.JLabel();
        txtRateDoctorName = new javax.swing.JTextField();
        jPanel31 = new javax.swing.JPanel();
        lblOverallRating = new javax.swing.JLabel();
        txtOverallRating = new javax.swing.JTextField();
        lblViewAppointment3 = new javax.swing.JLabel();
        cmbRateDoctors = new javax.swing.JComboBox<>();
        lblViewAppointment5 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        lblAddFeedback = new javax.swing.JLabel();
        lblFeedbackDoctor = new javax.swing.JLabel();
        cmbDoctorFeedback = new javax.swing.JComboBox<>();
        jPanel32 = new javax.swing.JPanel();
        lblRateDoctorName1 = new javax.swing.JLabel();
        cmbRating = new javax.swing.JComboBox<>();
        jPanel33 = new javax.swing.JPanel();
        lblRateNote = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtFeedbackNote = new javax.swing.JTextArea();
        btnConfirmFeedback = new javax.swing.JButton();
        tabRequestTermination = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        lblAccountTermination = new javax.swing.JLabel();
        btnTerminate = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTerminationID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTerminationPassword = new javax.swing.JPasswordField();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Patient Management System");
        setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        setName("MainMenu"); // NOI18N

        lblMain.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblMain.setName(""); // NOI18N
        lblMain.setText("Patient Management System");

        menuPatient.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblUserInfo1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblUserInfo1.setText("User Information");

        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblAccountType1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblAccountType1.setText("Account Type:");

        txtUserAccountType.setEditable(false);
        txtUserAccountType.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAccountType1)
                .addGap(18, 18, 18)
                .addComponent(txtUserAccountType, javax.swing.GroupLayout.PREFERRED_SIZE, 836, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblAccountType1)
                    .addComponent(txtUserAccountType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblUserID1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblUserID1.setText("User ID:");

        txtUserID.setEditable(false);
        txtUserID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblUserID1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblUserID1)
                    .addComponent(txtUserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblUserName1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblUserName1.setText("User Name:");

        txtUserName.setEditable(false);
        txtUserName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUserName1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 867, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblUserName1)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblUserName2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblUserName2.setText("Address:");

        txtUserAddress.setEditable(false);
        txtUserAddress.setColumns(20);
        txtUserAddress.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtUserAddress.setRows(5);
        jScrollPane8.setViewportView(txtUserAddress);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblUserName2)
                .addGap(43, 43, 43)
                .addComponent(jScrollPane8)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUserName2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblUserAge1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblUserAge1.setText("DOB:");

        txtUserAge.setEditable(false);
        txtUserAge.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lblUserAge1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtUserAge, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUserAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUserAge1))
                .addContainerGap())
        );

        jPanel10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblUserGender1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblUserGender1.setText("User Gender:");

        txtUserGender.setEditable(false);
        txtUserGender.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUserGender1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtUserGender, javax.swing.GroupLayout.PREFERRED_SIZE, 866, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblUserGender1)
                    .addComponent(txtUserGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(391, 391, 391)
                        .addComponent(lblUserInfo1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblUserInfo1)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tabUserInfoLayout = new javax.swing.GroupLayout(tabUserInfo);
        tabUserInfo.setLayout(tabUserInfoLayout);
        tabUserInfoLayout.setHorizontalGroup(
            tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabUserInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabUserInfoLayout.setVerticalGroup(
            tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabUserInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuPatient.addTab("User Information", tabUserInfo);

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblRequestAppointment.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblRequestAppointment.setText("Request Appointment");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblAccountType2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblAccountType2.setText("Select Doctor:");

        cmbDoctor.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        cmbDoctor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbDoctor.setSelectedItem(cmbDoctor);
        cmbDoctor.setToolTipText("");
        cmbDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDoctorActionPerformed(evt);
            }
        });

        lblSelectDate.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblSelectDate.setText("Enter Date:");

        lblSelectTime.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblSelectTime.setText("Enter Time:");

        txtEnterDate.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtEnterDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEnterDateKeyReleased(evt);
            }
        });

        txtEnterTime.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtEnterTime.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEnterTimeKeyReleased(evt);
            }
        });

        lblAppointmentInfo.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblAppointmentInfo.setText("Appointment Information");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblAccountType2)
                    .addComponent(lblSelectDate)
                    .addComponent(lblSelectTime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEnterTime, javax.swing.GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE)
                    .addComponent(txtEnterDate)
                    .addComponent(cmbDoctor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblAppointmentInfo)
                .addGap(383, 383, 383))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAppointmentInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAccountType2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSelectDate)
                    .addComponent(txtEnterDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSelectTime)
                    .addComponent(txtEnterTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblConfirmAppointment.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblConfirmAppointment.setText("Confirm Appointment");

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblDoctor.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblDoctor.setText("Doctor:");

        txtDoctor.setEditable(false);
        txtDoctor.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDoctorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDoctor)
                .addGap(18, 18, 18)
                .addComponent(txtDoctor)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDoctor)
                    .addComponent(txtDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblDate.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblDate.setText("Date:");

        txtDate.setEditable(false);
        txtDate.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDate)
                .addGap(18, 18, 18)
                .addComponent(txtDate)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel16.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTime.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblTime.setText("Time:");

        txtTime.setEditable(false);
        txtTime.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTime)
                .addGap(18, 18, 18)
                .addComponent(txtTime)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTime)
                    .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        btnConfirm.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnConfirm.setText("Confirm Request");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(402, 402, 402)
                        .addComponent(lblConfirmAppointment)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(401, 401, 401)
                .addComponent(btnConfirm)
                .addContainerGap(425, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblConfirmAppointment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(btnConfirm)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblRequestAppointment)
                .addGap(398, 398, 398))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRequestAppointment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout tabRequestAccountLayout = new javax.swing.GroupLayout(tabRequestAccount);
        tabRequestAccount.setLayout(tabRequestAccountLayout);
        tabRequestAccountLayout.setHorizontalGroup(
            tabRequestAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabRequestAccountLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabRequestAccountLayout.setVerticalGroup(
            tabRequestAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabRequestAccountLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuPatient.addTab("Request Appointment", tabRequestAccount);

        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblViewAppointment.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblViewAppointment.setText("View Appointment");

        jPanel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel20.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblDoctor1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblDoctor1.setText("Doctor:");

        txtNextDoctor.setEditable(false);
        txtNextDoctor.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtNextDoctor.setText("NO APPOINTMENT SCHEDULED");
        txtNextDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNextDoctorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDoctor1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNextDoctor)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDoctor1)
                    .addComponent(txtNextDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblDate3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblDate3.setText("Date:");

        txtNextDate.setEditable(false);
        txtNextDate.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtNextDate.setText("NO APPOINTMENT SCHEDULED");
        txtNextDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNextDateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDate3)
                .addGap(18, 18, 18)
                .addComponent(txtNextDate)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate3)
                    .addComponent(txtNextDate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        lblViewAppointment1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblViewAppointment1.setText("Your Next Appointment:");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(lblViewAppointment1)
                        .addGap(0, 804, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblViewAppointment1)
                .addGap(18, 18, 18)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(391, 391, 391)
                        .addComponent(lblViewAppointment)
                        .addGap(0, 468, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblViewAppointment)
                .addGap(29, 29, 29)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(398, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tabViewAppointmentLayout = new javax.swing.GroupLayout(tabViewAppointment);
        tabViewAppointment.setLayout(tabViewAppointmentLayout);
        tabViewAppointmentLayout.setHorizontalGroup(
            tabViewAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabViewAppointmentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabViewAppointmentLayout.setVerticalGroup(
            tabViewAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabViewAppointmentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuPatient.addTab("View Appointment", tabViewAppointment);

        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblViewPrescription.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblViewPrescription.setText("View Prescription");

        pnlPrescription.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblCurrentPrescription.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblCurrentPrescription.setText("Your Current Prescription:");

        pnlPatientInfo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblPatientInfo.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblPatientInfo.setText("Patient Info");

        lblPatientName.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPatientName.setText("Name:");

        lblPatientAddr.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPatientAddr.setText("Address:");

        lblSex.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblSex.setText("Sex:");

        lblDOB.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblDOB.setText("Age:");

        txtPatientName.setEditable(false);
        txtPatientName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        txtPatientGender.setEditable(false);
        txtPatientGender.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        txtPatientDOB.setEditable(false);
        txtPatientDOB.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        txtPatientAddress.setEditable(false);
        txtPatientAddress.setColumns(5);
        txtPatientAddress.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtPatientAddress.setRows(5);
        txtPatientAddress.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtPatientAddress);

        javax.swing.GroupLayout pnlPatientInfoLayout = new javax.swing.GroupLayout(pnlPatientInfo);
        pnlPatientInfo.setLayout(pnlPatientInfoLayout);
        pnlPatientInfoLayout.setHorizontalGroup(
            pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblPatientAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPatientName)
                            .addComponent(lblSex, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                            .addComponent(txtPatientName)
                            .addComponent(txtPatientGender)
                            .addComponent(txtPatientDOB)))
                    .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblPatientInfo)))
                .addContainerGap())
        );
        pnlPatientInfoLayout.setVerticalGroup(
            pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPatientInfo)
                .addGap(18, 18, 18)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPatientName)
                    .addComponent(txtPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblPatientAddr))
                    .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSex)
                    .addComponent(txtPatientGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPatientDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDOB))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDoctorInfo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblDoctorInfo.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblDoctorInfo.setText("Doctor Info");

        lblDoctorName.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblDoctorName.setText("Name:");

        txtDoctorName.setEditable(false);
        txtDoctorName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblDoctorAddr.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblDoctorAddr.setText("Address:");

        txtDoctorAddress.setEditable(false);
        txtDoctorAddress.setColumns(5);
        txtDoctorAddress.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtDoctorAddress.setRows(5);
        txtDoctorAddress.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtDoctorAddress);

        javax.swing.GroupLayout pnlDoctorInfoLayout = new javax.swing.GroupLayout(pnlDoctorInfo);
        pnlDoctorInfo.setLayout(pnlDoctorInfoLayout);
        pnlDoctorInfoLayout.setHorizontalGroup(
            pnlDoctorInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoctorInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDoctorInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDoctorInfoLayout.createSequentialGroup()
                        .addComponent(lblDoctorInfo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoctorInfoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlDoctorInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoctorInfoLayout.createSequentialGroup()
                                .addComponent(lblDoctorName)
                                .addGap(32, 32, 32)
                                .addComponent(txtDoctorName, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoctorInfoLayout.createSequentialGroup()
                                .addComponent(lblDoctorAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        pnlDoctorInfoLayout.setVerticalGroup(
            pnlDoctorInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoctorInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDoctorInfo)
                .addGap(18, 18, 18)
                .addGroup(pnlDoctorInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDoctorName)
                    .addComponent(txtDoctorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDoctorInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDoctorInfoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblDoctorAddr))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblMedicine.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        tblMedicine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine", "Quantity", "Dosage"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblMedicine);
        if (tblMedicine.getColumnModel().getColumnCount() > 0) {
            tblMedicine.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblMedicine.getColumnModel().getColumn(1).setPreferredWidth(1);
        }

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlNotes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNotes.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblNotes.setText("Doctor's Notes");

        txtPrescriptionNotes.setEditable(false);
        txtPrescriptionNotes.setColumns(5);
        txtPrescriptionNotes.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtPrescriptionNotes.setRows(5);
        txtPrescriptionNotes.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txtPrescriptionNotes);

        javax.swing.GroupLayout pnlNotesLayout = new javax.swing.GroupLayout(pnlNotes);
        pnlNotes.setLayout(pnlNotesLayout);
        pnlNotesLayout.setHorizontalGroup(
            pnlNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNotesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(pnlNotesLayout.createSequentialGroup()
                        .addComponent(lblNotes)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlNotesLayout.setVerticalGroup(
            pnlNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNotesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNotes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlPrescriptionLayout = new javax.swing.GroupLayout(pnlPrescription);
        pnlPrescription.setLayout(pnlPrescriptionLayout);
        pnlPrescriptionLayout.setHorizontalGroup(
            pnlPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrescriptionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrescriptionLayout.createSequentialGroup()
                        .addComponent(lblCurrentPrescription)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrescriptionLayout.createSequentialGroup()
                        .addGroup(pnlPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlPatientInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlNotes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlDoctorInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pnlPrescriptionLayout.setVerticalGroup(
            pnlPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrescriptionLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblCurrentPrescription)
                .addGap(18, 18, 18)
                .addGroup(pnlPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlDoctorInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlPatientInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlNotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(391, 391, 391)
                        .addComponent(lblViewPrescription)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlPrescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblViewPrescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlPrescription, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(265, 265, 265))
        );

        javax.swing.GroupLayout tabViewPrescriptionLayout = new javax.swing.GroupLayout(tabViewPrescription);
        tabViewPrescription.setLayout(tabViewPrescriptionLayout);
        tabViewPrescriptionLayout.setHorizontalGroup(
            tabViewPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabViewPrescriptionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabViewPrescriptionLayout.setVerticalGroup(
            tabViewPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabViewPrescriptionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuPatient.addTab("View Prescription", tabViewPrescription);

        jPanel23.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblViewPrescription1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblViewPrescription1.setText("View History");

        pnlPrescription1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblCurrentPrescription1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblCurrentPrescription1.setText("Your Past Appointments:");

        tblPastAppointments.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        tblPastAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Doctor", "Date", "Notes"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPastAppointments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPastAppointmentsMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblPastAppointments);
        if (tblPastAppointments.getColumnModel().getColumnCount() > 0) {
            tblPastAppointments.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblPastAppointments.getColumnModel().getColumn(1).setPreferredWidth(10);
            tblPastAppointments.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel25.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblPastDoctorName.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPastDoctorName.setText("Doctor:");

        txtPastDoctor.setEditable(false);
        txtPastDoctor.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblPastDate.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPastDate.setText("Date:");

        txtPastDate.setEditable(false);
        txtPastDate.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblPastNote.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPastNote.setText("Note:");

        txtPastNote.setEditable(false);
        txtPastNote.setColumns(20);
        txtPastNote.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtPastNote.setLineWrap(true);
        txtPastNote.setRows(5);
        jScrollPane6.setViewportView(txtPastNote);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(lblPastDate)
                                .addGap(31, 31, 31)
                                .addComponent(txtPastDate, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(lblPastDoctorName)
                                .addGap(18, 18, 18)
                                .addComponent(txtPastDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblPastNote)
                        .addGap(11, 11, 11)))
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(lblPastNote)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPastDoctorName)
                            .addComponent(txtPastDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPastDate)
                            .addComponent(txtPastDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlPrescription1Layout = new javax.swing.GroupLayout(pnlPrescription1);
        pnlPrescription1.setLayout(pnlPrescription1Layout);
        pnlPrescription1Layout.setHorizontalGroup(
            pnlPrescription1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrescription1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPrescription1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPrescription1Layout.createSequentialGroup()
                        .addComponent(lblCurrentPrescription1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlPrescription1Layout.setVerticalGroup(
            pnlPrescription1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrescription1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblCurrentPrescription1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPrescription1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(427, 427, 427)
                .addComponent(lblViewPrescription1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblViewPrescription1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlPrescription1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(286, 286, 286))
        );

        javax.swing.GroupLayout tabViewHistoryLayout = new javax.swing.GroupLayout(tabViewHistory);
        tabViewHistory.setLayout(tabViewHistoryLayout);
        tabViewHistoryLayout.setHorizontalGroup(
            tabViewHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabViewHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabViewHistoryLayout.setVerticalGroup(
            tabViewHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabViewHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 759, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuPatient.addTab("View History", tabViewHistory);

        jPanel26.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel27.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel30.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblRateDoctorName.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblRateDoctorName.setText("Name:");

        txtRateDoctorName.setEditable(false);
        txtRateDoctorName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtRateDoctorName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRateDoctorNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblRateDoctorName)
                .addGap(18, 18, 18)
                .addComponent(txtRateDoctorName)
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRateDoctorName)
                    .addComponent(txtRateDoctorName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel31.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblOverallRating.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblOverallRating.setText("Overall Rating:");

        txtOverallRating.setEditable(false);
        txtOverallRating.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtOverallRating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOverallRatingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOverallRating)
                .addGap(18, 18, 18)
                .addComponent(txtOverallRating)
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOverallRating)
                    .addComponent(txtOverallRating, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        lblViewAppointment3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblViewAppointment3.setText("Select Doctor:");

        cmbRateDoctors.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        cmbRateDoctors.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbRateDoctors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRateDoctorsActionPerformed(evt);
            }
        });

        lblViewAppointment5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblViewAppointment5.setText("View Doctor Ratings");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addComponent(lblViewAppointment3)
                                .addGap(18, 18, 18)
                                .addComponent(cmbRateDoctors, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblViewAppointment5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblViewAppointment5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblViewAppointment3)
                    .addComponent(cmbRateDoctors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel29.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblAddFeedback.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblAddFeedback.setText("Give Doctor Feedback");

        lblFeedbackDoctor.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblFeedbackDoctor.setText("Select Doctor:");

        cmbDoctorFeedback.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        cmbDoctorFeedback.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbDoctorFeedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDoctorFeedbackActionPerformed(evt);
            }
        });

        jPanel32.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblRateDoctorName1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblRateDoctorName1.setText("Rating:");

        cmbRating.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        cmbRating.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Rating", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        cmbRating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRatingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRateDoctorName1)
                .addGap(18, 18, 18)
                .addComponent(cmbRating, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRateDoctorName1)
                    .addComponent(cmbRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel33.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblRateNote.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblRateNote.setText("Notes:");

        txtFeedbackNote.setColumns(20);
        txtFeedbackNote.setLineWrap(true);
        txtFeedbackNote.setRows(3);
        jScrollPane7.setViewportView(txtFeedbackNote);

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRateNote)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7)
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(lblRateNote)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnConfirmFeedback.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnConfirmFeedback.setText("Confirm Feedback");
        btnConfirmFeedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmFeedbackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAddFeedback)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(lblFeedbackDoctor)
                                .addGap(18, 18, 18)
                                .addComponent(cmbDoctorFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(367, 367, 367)
                .addComponent(btnConfirmFeedback)
                .addContainerGap(447, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAddFeedback)
                .addGap(18, 18, 18)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFeedbackDoctor)
                    .addComponent(cmbDoctorFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConfirmFeedback)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout tabDoctorFeedbackLayout = new javax.swing.GroupLayout(tabDoctorFeedback);
        tabDoctorFeedback.setLayout(tabDoctorFeedbackLayout);
        tabDoctorFeedbackLayout.setHorizontalGroup(
            tabDoctorFeedbackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabDoctorFeedbackLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabDoctorFeedbackLayout.setVerticalGroup(
            tabDoctorFeedbackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabDoctorFeedbackLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuPatient.addTab("Doctor Feedback", tabDoctorFeedback);

        jPanel34.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblAccountTermination.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblAccountTermination.setText("Account Termination");

        btnTerminate.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnTerminate.setText("REQUEST ACCOUNT DELETION");
        btnTerminate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminateActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel1.setText("User ID:");

        txtTerminationID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtTerminationID.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel2.setText("Password:");

        txtTerminationPassword.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel34Layout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTerminationID)
                            .addComponent(txtTerminationPassword)))
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel34Layout.createSequentialGroup()
                            .addGap(386, 386, 386)
                            .addComponent(lblAccountTermination))
                        .addGroup(jPanel34Layout.createSequentialGroup()
                            .addGap(271, 271, 271)
                            .addComponent(btnTerminate))))
                .addGap(359, 359, 359))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblAccountTermination)
                .addGap(58, 58, 58)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTerminationID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTerminationPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(btnTerminate)
                .addContainerGap(398, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tabRequestTerminationLayout = new javax.swing.GroupLayout(tabRequestTermination);
        tabRequestTermination.setLayout(tabRequestTerminationLayout);
        tabRequestTerminationLayout.setHorizontalGroup(
            tabRequestTerminationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabRequestTerminationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabRequestTerminationLayout.setVerticalGroup(
            tabRequestTerminationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabRequestTerminationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuPatient.addTab("Request Account Termination", tabRequestTermination);

        btnLogout.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnLogout.setText("LOGOUT");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(menuPatient))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(315, 315, 315)
                        .addComponent(lblMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogout)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogout))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 739, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDoctorActionPerformed
        String doctor = (String) this.cmbDoctor.getSelectedItem();
        if(doctor != "Select Doctor")
        {
            this.txtDoctor.setText(doctor);
        }
    }//GEN-LAST:event_cmbDoctorActionPerformed

    private void txtDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDoctorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDoctorActionPerformed

    private void txtDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDateActionPerformed

    private void txtTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimeActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        String doctorName = this.txtDoctor.getText();
        String enteredDate = this.txtDate.getText();
        String enteredTime = this.txtTime.getText();
        
        if(!(enteredDate.equals("") || enteredTime.equals("")))
        {
            String date = this.txtDate.getText() + " " + this.txtTime.getText();
        
            try
            {
                Date date1 = new SimpleDateFormat("dd/MM/yyyy HH:MM").parse(date);

                for(Doctor doctor : Doctor.doctors)
                {
                    String doctorForename = doctor.getForename();
                    String doctorSurname = doctor.getSurname();
                    String name = doctorForename + " " + doctorSurname;

                    if(doctorName.equals(name))
                    {
                        AppointmentRequest newRequest = new AppointmentRequest(doctor, (Patient) User.loggedUser, date);
                        
                        newRequest.addAppointmentRequest(newRequest);
                        
                        for(Secretary secretary : Secretary.secretarys)
                        {
                            secretary.setNotification(new Notification("You have new Requests:"
                                + "\nAccount Reqeusts \nAppointment Reqeusts \nMedicine Requests \nTermination Requests"));
                        }
                        User.saveUsers();
                        
                        JOptionPane.showMessageDialog(this, "APPOINTMENT REQUEST SUBMITTED", "SUCCESS", 
                                JOptionPane.INFORMATION_MESSAGE);
                        
                        this.cmbDoctor.setSelectedIndex(0);
                        this.txtEnterDate.setText("");
                        this.txtEnterTime.setText("");
                        this.txtDoctor.setText("");
                        this.txtDate.setText("");
                        this.txtTime.setText("");
                    }
                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(this, "ENTER A CORRECT DATE AND TIME FORMAT (DD/MM/YYYY HH:MM)", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "ENTER A CORRECT DATE AND TIME FORMAT (DD/MM/YYYY HH:MM)", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
        }   
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void txtNextDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNextDoctorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNextDoctorActionPerformed

    private void txtNextDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNextDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNextDateActionPerformed

    private void tblPastAppointmentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPastAppointmentsMouseClicked
        int row = 0;
        String[] data = new String[3];
        for(int i = 0; i < 3; i++)
        {
            row = this.tblPastAppointments.getSelectedRow();
            String value = tblPastAppointments.getModel().getValueAt(row, i).toString();
            data[i] = value;
        }
        this.txtPastDoctor.setText(data[0]);
        this.txtPastDate.setText(data[1]);
        this.txtPastNote.setText(data[2]);
    }//GEN-LAST:event_tblPastAppointmentsMouseClicked

    private void txtRateDoctorNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRateDoctorNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRateDoctorNameActionPerformed

    private void txtOverallRatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOverallRatingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOverallRatingActionPerformed

    private void cmbRateDoctorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRateDoctorsActionPerformed
        String doctorName = (String) this.cmbRateDoctors.getSelectedItem();
        
        for(Doctor doctor : Doctor.doctors)
        {
            String name = doctor.getForename() + " " + doctor.getSurname();
            if(name.equals(doctorName))
            {
                float average = 0;
                float i = 0;
                for(Feedback feedback : Feedback.feedback)
                {
                    if(feedback.getDoctor().getID().equals(doctor.getID()))
                    {
                        average += feedback.getRating();
                        i++;
                    }
                }
                average = average / i;
                this.txtRateDoctorName.setText(doctorName);
                this.txtOverallRating.setText(Float.toString(average));
                break;
            }
            else
            {
                this.txtRateDoctorName.setText("");
                this.txtOverallRating.setText("");
            }
        }
    }//GEN-LAST:event_cmbRateDoctorsActionPerformed

    private void cmbDoctorFeedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDoctorFeedbackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDoctorFeedbackActionPerformed

    private void cmbRatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRatingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRatingActionPerformed

    private void btnConfirmFeedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmFeedbackActionPerformed
        
        String doctorName = (String) this.cmbDoctorFeedback.getSelectedItem();
        String rate = (String) this.cmbRating.getSelectedItem();
        String note = this.txtFeedbackNote.getText();
        if(!((rate.equals("Select Rating")) && (doctorName.equals("Select Doctor"))))
        {
            //Add to doctor's record
            int confirm = JOptionPane.showConfirmDialog(this, "CONFIRM FEEDBACK?", "WARNING",
                    JOptionPane.YES_NO_CANCEL_OPTION);
            
            if(confirm == 0)
            {
                for(Doctor doctor : Doctor.doctors)
                {
                    String name = doctor.getForename() + " " + doctor.getSurname();
                    if(name.equals(doctorName))
                    {
                        Feedback newFeedback = new Feedback(doctor, Integer.parseInt(rate), note);
                        newFeedback.addFeedback(newFeedback);
                        
                        for(Admin admin : Admin.admins)
                        {
                            admin.setNotification(new Notification("You have new Feedback Requests"));
                        }
                        User.saveUsers();
                        
                        this.cmbDoctorFeedback.setSelectedIndex(0);
                        this.cmbRating.setSelectedIndex(0);
                        this.txtFeedbackNote.setText("");
                    }
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Select A Doctor and Rating.","Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnConfirmFeedbackActionPerformed

    private void btnTerminateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminateActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "ARE YOU SURE YOU WISH TO DELETE YOUR ACCOUNT?", "WARNING", 
                JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
        if(confirm == 0)
        {
            String userID = this.txtTerminationID.getText();
            String password = Login.hashPassword(this.txtTerminationPassword.getText());
            
            if((userID.equals(User.loggedUser.getID())) && (password.equals(User.loggedUser.getPassword())))
            {
                TerminationRequest newTerminationRequest = new TerminationRequest((Patient)User.loggedUser);
                newTerminationRequest.addTerminationRequest(newTerminationRequest);
                
                for(Secretary secretary : Secretary.secretarys)
                {
                    secretary.setNotification(new Notification("You have new Requests:"
                        + "\nAccount Reqeusts \nAppointment Reqeusts \nMedicine Requests \nTermination Requests"));
                }
                User.saveUsers();
                
                JOptionPane.showMessageDialog(this, "REQUEST COMPLETE", "SUCCESS", 
                        JOptionPane.INFORMATION_MESSAGE);
                this.txtTerminationID.setText("");
                this.txtTerminationPassword.setText("");
            }
            else
            {
                JOptionPane.showMessageDialog(this, "INCORRECT USERNAME OR PASSWORD", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
    }//GEN-LAST:event_btnTerminateActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you wish to logout?", "WARNING", JOptionPane.WARNING_MESSAGE);

        if(confirm == 0)
        {
            User.loggedUser = null;

            User.saveUsers();

            new Login().setVisible(true);

            this.setVisible(false);
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void txtEnterDateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnterDateKeyReleased
        this.txtDate.setText(this.txtEnterDate.getText());
    }//GEN-LAST:event_txtEnterDateKeyReleased

    private void txtEnterTimeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnterTimeKeyReleased
        this.txtTime.setText(this.txtEnterTime.getText());
    }//GEN-LAST:event_txtEnterTimeKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PatientMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PatientMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PatientMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PatientMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PatientMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnConfirmFeedback;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnTerminate;
    private javax.swing.JComboBox<String> cmbDoctor;
    private javax.swing.JComboBox<String> cmbDoctorFeedback;
    private javax.swing.JComboBox<String> cmbRateDoctors;
    private javax.swing.JComboBox<String> cmbRating;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lblAccountTermination;
    private javax.swing.JLabel lblAccountType1;
    private javax.swing.JLabel lblAccountType2;
    private javax.swing.JLabel lblAddFeedback;
    private javax.swing.JLabel lblAppointmentInfo;
    private javax.swing.JLabel lblConfirmAppointment;
    private javax.swing.JLabel lblCurrentPrescription;
    private javax.swing.JLabel lblCurrentPrescription1;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDate3;
    private javax.swing.JLabel lblDoctor;
    private javax.swing.JLabel lblDoctor1;
    private javax.swing.JLabel lblDoctorAddr;
    private javax.swing.JLabel lblDoctorInfo;
    private javax.swing.JLabel lblDoctorName;
    private javax.swing.JLabel lblFeedbackDoctor;
    private java.awt.Label lblMain;
    private javax.swing.JLabel lblNotes;
    private javax.swing.JLabel lblOverallRating;
    private javax.swing.JLabel lblPastDate;
    private javax.swing.JLabel lblPastDoctorName;
    private javax.swing.JLabel lblPastNote;
    private javax.swing.JLabel lblPatientAddr;
    private javax.swing.JLabel lblPatientInfo;
    private javax.swing.JLabel lblPatientName;
    private javax.swing.JLabel lblRateDoctorName;
    private javax.swing.JLabel lblRateDoctorName1;
    private javax.swing.JLabel lblRateNote;
    private javax.swing.JLabel lblRequestAppointment;
    private javax.swing.JLabel lblSelectDate;
    private javax.swing.JLabel lblSelectTime;
    private javax.swing.JLabel lblSex;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblUserAge1;
    private javax.swing.JLabel lblUserGender1;
    private javax.swing.JLabel lblUserID1;
    private javax.swing.JLabel lblUserInfo1;
    private javax.swing.JLabel lblUserName1;
    private javax.swing.JLabel lblUserName2;
    private javax.swing.JLabel lblViewAppointment;
    private javax.swing.JLabel lblViewAppointment1;
    private javax.swing.JLabel lblViewAppointment3;
    private javax.swing.JLabel lblViewAppointment5;
    private javax.swing.JLabel lblViewPrescription;
    private javax.swing.JLabel lblViewPrescription1;
    private javax.swing.JTabbedPane menuPatient;
    private javax.swing.JPanel pnlDoctorInfo;
    private javax.swing.JPanel pnlNotes;
    private javax.swing.JPanel pnlPatientInfo;
    private javax.swing.JPanel pnlPrescription;
    private javax.swing.JPanel pnlPrescription1;
    private javax.swing.JPanel tabDoctorFeedback;
    private javax.swing.JPanel tabRequestAccount;
    private javax.swing.JPanel tabRequestTermination;
    private javax.swing.JPanel tabUserInfo;
    private javax.swing.JPanel tabViewAppointment;
    private javax.swing.JPanel tabViewHistory;
    private javax.swing.JPanel tabViewPrescription;
    private javax.swing.JTable tblMedicine;
    private javax.swing.JTable tblPastAppointments;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDoctor;
    private javax.swing.JTextArea txtDoctorAddress;
    private javax.swing.JTextField txtDoctorName;
    private javax.swing.JTextField txtEnterDate;
    private javax.swing.JTextField txtEnterTime;
    private javax.swing.JTextArea txtFeedbackNote;
    private javax.swing.JTextField txtNextDate;
    private javax.swing.JTextField txtNextDoctor;
    private javax.swing.JTextField txtOverallRating;
    private javax.swing.JTextField txtPastDate;
    private javax.swing.JTextField txtPastDoctor;
    private javax.swing.JTextArea txtPastNote;
    private javax.swing.JTextArea txtPatientAddress;
    private javax.swing.JTextField txtPatientDOB;
    private javax.swing.JTextField txtPatientGender;
    private javax.swing.JTextField txtPatientName;
    private javax.swing.JTextArea txtPrescriptionNotes;
    private javax.swing.JTextField txtRateDoctorName;
    private javax.swing.JTextField txtTerminationID;
    private javax.swing.JPasswordField txtTerminationPassword;
    private javax.swing.JTextField txtTime;
    private javax.swing.JTextField txtUserAccountType;
    private javax.swing.JTextArea txtUserAddress;
    private javax.swing.JTextField txtUserAge;
    private javax.swing.JTextField txtUserGender;
    private javax.swing.JTextField txtUserID;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
