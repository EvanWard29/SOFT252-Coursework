/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import users.*;
import system.*;
import java.text.SimpleDateFormat;  
import java.util.Date;  

/**
 *
 * @author LoL-1
 */
public class DoctorMenu extends javax.swing.JFrame {

    /**
     * Creates new form Doctor
     */
    public DoctorMenu() {
        initComponents();
        getUserInfo();
        getAppointments();
        setAppointments();
        getPastAppointments();
        getPatients();
        setMedicine();
    }
    
    private void getUserInfo()
    {
        Notification notification = User.loggedUser.getNotification();
        
        if(notification != null)
        {
            JOptionPane.showMessageDialog(this, notification.getMessage(), "WELCOME", 
                    JOptionPane.INFORMATION_MESSAGE);
            User.loggedUser.setNotification(null);
            User.saveUsers();
        }
        
        this.txtUserAccountType.setText("Doctor");
        this.txtUserID.setText(User.loggedUser.getID());
        this.txtUserName.setText(User.loggedUser.getForename() + " " + User.loggedUser.getSurname());
        this.txtUserAddress.setText(User.loggedUser.getAddress());
    }
    
    private void getAppointments()
    {
        DefaultTableModel model = (DefaultTableModel) this.tblAppointments.getModel();
        int rows = model.getRowCount();
        if(rows > 0)
        {
            for (int i = rows - 1; i >= 0; i--)
            {
                model.removeRow(i);
            }
        }
        for(Appointment appointment : Appointment.appointments)
        {
            if(appointment.getDoctor().getID().equals(User.loggedUser.getID()))
            {
                String patientName = "";
                
                for(Patient patient : Patient.patients)
                {
                    if(patient.getID().equals(appointment.getPatient().getID()))
                    {
                        patientName = patient.getForename() + " " + patient.getSurname();
                    }
                }          
                String[] data = {
                    appointment.getPatient().getID(),
                    patientName,
                    appointment.getDate()
                };
                model.addRow(data);
            }
        }
    }
    
    private void setAppointments()
    {  
        int i = 0;
        
        for(Appointment appointment : Appointment.appointments)
        {
            if(appointment.getDoctor().getID().equals(User.loggedUser.getID()))
            {
                i++;
            }
        }
        
        Appointment[] doctorAppointments = new Appointment[i];
        i = 0;
        for(Appointment appointment : Appointment.appointments)
        {
            if(appointment.getDoctor().getID().equals(User.loggedUser.getID()))
            {
                doctorAppointments[i] = appointment;
                i++;
            }
        }
        
        String[] appointments = new String[doctorAppointments.length + 1];
        appointments[0] = "Select Appointment";
        i = 1;
        for(Appointment appointment : doctorAppointments)
        {
            for(Patient patient : Patient.patients)
            {   
                if(patient.getID().equals(appointment.getPatient().getID()))
                {
                    appointments[i] = patient.getID();
                    i++;
                    break;
                } 
            }
        }
        
        DefaultComboBoxModel model = new DefaultComboBoxModel(appointments);
        this.cmbSelectAppointment.setModel(model);
    }
    
    private void getPastAppointments()
    {
        String[] patients = new String[Patient.patients.length + 1];
        patients[0] = "Select Patient";
        int i = 1;
        for(Patient patient : Patient.patients)
        {
            patients[i] = patient.getID();
            i++;
        }
        
        DefaultComboBoxModel model = new DefaultComboBoxModel(patients);
        this.cmbSelectPatientID.setModel(model);
    }
    
    private void setPastAppointments(String id)
    {
        DefaultTableModel model = (DefaultTableModel) this.tblPatientHistory.getModel();
        for(PastAppointment pastAppointment : PastAppointment.pastAppointments)
        {
            if(pastAppointment.getPatient().getID().equals(id))
            {   
                String patientName = pastAppointment.getPatient().getForename() + " " + pastAppointment.getPatient().getSurname();
                this.txtPatientName.setText(patientName);
                this.txtPatientDOB.setText(pastAppointment.getPatient().getDOB());
                String[] data = {
                    pastAppointment.getDoctor().getID(),
                    pastAppointment.getDate(),
                    pastAppointment.getPastPrescription().getNotes()
                };
                model.addRow(data);
            }
        }  
    }
    
    private void getPatients()
    {
        String[] patients = new String[Patient.patients.length + 1];
        patients[0] = "Select Patient";
        int i = 1;
        for(Patient patient : Patient.patients)
        {
            patients[i] = patient.getID();
            i++;
        }
        
        DefaultComboBoxModel model = new DefaultComboBoxModel(patients);
        this.cmbSelectPatient.setModel(model);
    }
    
    private void removeAppointment(String id)
    {
        for(Appointment appointment : Appointment.appointments)
        {
            if((appointment.getPatient().getID().equals(id)) && (appointment.getDoctor().getID().equals(User.loggedUser.getID())))
            {
                appointment.removeAppointment(appointment);
            }
        }
        this.txtPrescriptionPatientID.setText("");
        this.txtPrescriptionPatientName.setText("");
        this.txtPrescriptionPatientAddress.setText("");
        this.txtPrescriptionPatientGender.setText("");
        this.txtPrescriptionPatientDOB.setText("");
        this.txtPrescriptionNotes.setText("");
        this.txtSelectedAppointmentDate.setText("");
        
        DefaultTableModel model = (DefaultTableModel) this.tblPrescriptionMedicine.getModel();
        int rows = model.getRowCount();
        if(rows > 0)
        {
            for (int i = rows - 1; i >= 0; i--)
            {
                model.removeRow(i);
            }
        }
    }
    
    private void prescribeMedicine(Prescription prescription)
    {
        this.menuDoctor.setSelectedIndex(5);
        this.txtMedicinePatientID.setText(prescription.getPatient().getID());
        String patientName = prescription.getPatient().getForename() + " " + prescription.getPatient().getSurname();
        this.txtMedicinePatientName.setText(patientName);
    }
    
    private void setMedicine()
    {
        String[] medicines = new String[Medicine.medicines.length + 1];
        medicines[0] = "Select Medicine";
        int i = 1;
        for(Medicine medicine : Medicine.medicines)
        {
            medicines[i] = medicine.getName();
            i++;
        }
        
        DefaultComboBoxModel model = new DefaultComboBoxModel(medicines);
        this.cmbMedicineName.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMain = new java.awt.Label();
        menuDoctor = new javax.swing.JTabbedPane();
        tabUserInfo = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        lblAccountType1 = new javax.swing.JLabel();
        txtUserAccountType = new javax.swing.JTextField();
        lblUserInfo1 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        lblUserID1 = new javax.swing.JLabel();
        txtUserID = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        lblUserName2 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtUserAddress = new javax.swing.JTextArea();
        jPanel16 = new javax.swing.JPanel();
        lblUserName1 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        tabViewAppointments = new javax.swing.JPanel();
        lblAppointments = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAppointments = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        lblNextAppointment = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblNextDate = new javax.swing.JLabel();
        txtAppointmentDate = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lblNextPatientID = new javax.swing.JLabel();
        txtAppointmentPatientID = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        lblNextPatientName = new javax.swing.JLabel();
        txtAppointmentPatientName = new javax.swing.JTextField();
        tabMakeNotes = new javax.swing.JPanel();
        lblAppointments1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblSelectAppointment = new javax.swing.JLabel();
        cmbSelectAppointment = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        lblPrescriptionPatientGender = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPrescriptionPatientAddress = new javax.swing.JTextArea();
        lblPrescriptionPatientAddress = new javax.swing.JLabel();
        txtPrescriptionPatientName = new javax.swing.JTextField();
        txtPrescriptionPatientID = new javax.swing.JTextField();
        txtPrescriptionPatientDOB = new javax.swing.JTextField();
        lblPrescriptionPatientName = new javax.swing.JLabel();
        lblPrescriptionPatientGender1 = new javax.swing.JLabel();
        lblPatientID = new javax.swing.JLabel();
        txtPrescriptionPatientGender = new javax.swing.JTextField();
        lblPrescription = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lblPrescriptionNotes = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtPrescriptionNotes = new javax.swing.JTextArea();
        btnSaveNotes = new javax.swing.JButton();
        lblPrescriptionMedicine = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblPrescriptionMedicine = new javax.swing.JTable();
        btnConcludeAppointment = new javax.swing.JButton();
        lblSelectedAppointmentDate = new javax.swing.JLabel();
        txtSelectedAppointmentDate = new javax.swing.JTextField();
        tabPatientHistory = new javax.swing.JPanel();
        lblPatientHistory = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblPatientHistory = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtSearchPatientID = new javax.swing.JTextField();
        cmbSelectPatientID = new javax.swing.JComboBox<>();
        lblPatientName = new javax.swing.JLabel();
        txtPatientName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPatientDOB = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        txtDoctorName = new javax.swing.JTextField();
        txtPastDate = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtPastNotes = new javax.swing.JTextArea();
        lblDoctorName = new javax.swing.JLabel();
        lblPastDate = new javax.swing.JLabel();
        lblPastNotes = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblPastPrescription = new javax.swing.JTable();
        lblPastDate1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        tabCreateAppointment = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        lblRequestAppointment = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblAccountType2 = new javax.swing.JLabel();
        cmbSelectPatient = new javax.swing.JComboBox<>();
        lblSelectDate = new javax.swing.JLabel();
        lblSelectTime = new javax.swing.JLabel();
        txtEnterDate = new javax.swing.JTextField();
        txtEnterTime = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        lblConfirmAppointment = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        lblPatient = new javax.swing.JLabel();
        txtSelectedPatient = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        lblDate = new javax.swing.JLabel();
        txtEnteredDate = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        lblTime = new javax.swing.JLabel();
        txtEnteredTime = new javax.swing.JTextField();
        btnConfirmAppointment = new javax.swing.JButton();
        tabPrescribeMedicine = new javax.swing.JPanel();
        lblPrescribeMedicine = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblMedicinePatientID = new javax.swing.JLabel();
        txtMedicinePatientID = new javax.swing.JTextField();
        lblMedicinePatientName = new javax.swing.JLabel();
        txtMedicinePatientName = new javax.swing.JTextField();
        lblMedicineSelected = new javax.swing.JLabel();
        txtMedicineSelected = new javax.swing.JTextField();
        lblMedicineSelected1 = new javax.swing.JLabel();
        txtMedicineQuantity = new javax.swing.JTextField();
        lblMedicineSelected2 = new javax.swing.JLabel();
        txtMedicineDosage = new javax.swing.JTextField();
        btnPrescribe = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        lblMedicineName = new javax.swing.JLabel();
        cmbMedicineName = new javax.swing.JComboBox<>();
        jPanel22 = new javax.swing.JPanel();
        lblMedicineName2 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        lblMedicineName3 = new javax.swing.JLabel();
        txtDosage = new javax.swing.JTextField();
        lblMedicine = new javax.swing.JLabel();
        tabAddNewMedicine = new javax.swing.JPanel();
        lblPrescribeMedicine1 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        lblAddMedicineName = new javax.swing.JLabel();
        txtAddMedicineName = new javax.swing.JTextField();
        jPanel28 = new javax.swing.JPanel();
        lblAddMedicineQuantity = new javax.swing.JLabel();
        txtAddMedicineQauntity = new javax.swing.JTextField();
        lblMedicine1 = new javax.swing.JLabel();
        btnAddMedicine = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblMain.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblMain.setName(""); // NOI18N
        lblMain.setText("Patient Management System");

        menuDoctor.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        jPanel12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblAccountType1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblAccountType1.setText("Account Type:");

        txtUserAccountType.setEditable(false);
        txtUserAccountType.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAccountType1)
                .addGap(18, 18, 18)
                .addComponent(txtUserAccountType)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblAccountType1)
                    .addComponent(txtUserAccountType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblUserInfo1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblUserInfo1.setText("User Information");

        jPanel13.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblUserID1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblUserID1.setText("User ID:");

        txtUserID.setEditable(false);
        txtUserID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblUserID1)
                .addGap(18, 18, 18)
                .addComponent(txtUserID)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblUserID1)
                    .addComponent(txtUserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel18.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblUserName2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblUserName2.setText("Address:");

        txtUserAddress.setEditable(false);
        txtUserAddress.setColumns(20);
        txtUserAddress.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtUserAddress.setRows(5);
        jScrollPane10.setViewportView(txtUserAddress);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblUserName2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUserName2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblUserName1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblUserName1.setText("User Name:");

        txtUserName.setEditable(false);
        txtUserName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUserName1)
                .addGap(18, 18, 18)
                .addComponent(txtUserName)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblUserName1)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tabUserInfoLayout = new javax.swing.GroupLayout(tabUserInfo);
        tabUserInfo.setLayout(tabUserInfoLayout);
        tabUserInfoLayout.setHorizontalGroup(
            tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabUserInfoLayout.createSequentialGroup()
                .addGroup(tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabUserInfoLayout.createSequentialGroup()
                        .addGap(391, 391, 391)
                        .addComponent(lblUserInfo1)
                        .addGap(0, 519, Short.MAX_VALUE))
                    .addGroup(tabUserInfoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(tabUserInfoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        tabUserInfoLayout.setVerticalGroup(
            tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabUserInfoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblUserInfo1)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(286, Short.MAX_VALUE))
        );

        menuDoctor.addTab("User Information", tabUserInfo);

        lblAppointments.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblAppointments.setText("Appointments");

        tblAppointments.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tblAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient ID", "Patient Name", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
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
        tblAppointments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAppointmentsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAppointments);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNextAppointment.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblNextAppointment.setText("Appointment:");

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblNextDate.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblNextDate.setText("Date:");

        txtAppointmentDate.setEditable(false);
        txtAppointmentDate.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblNextDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtAppointmentDate, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNextDate)
                    .addComponent(txtAppointmentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblNextPatientID.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblNextPatientID.setText("Patient ID:");

        txtAppointmentPatientID.setEditable(false);
        txtAppointmentPatientID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNextPatientID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAppointmentPatientID, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblNextPatientID)
                    .addComponent(txtAppointmentPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblNextPatientName.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblNextPatientName.setText("Patient Name:");

        txtAppointmentPatientName.setEditable(false);
        txtAppointmentPatientName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNextPatientName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtAppointmentPatientName)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNextPatientName)
                    .addComponent(txtAppointmentPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblNextAppointment)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel2, jPanel3});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNextAppointment)
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout tabViewAppointmentsLayout = new javax.swing.GroupLayout(tabViewAppointments);
        tabViewAppointments.setLayout(tabViewAppointmentsLayout);
        tabViewAppointmentsLayout.setHorizontalGroup(
            tabViewAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabViewAppointmentsLayout.createSequentialGroup()
                .addGap(444, 444, 444)
                .addComponent(lblAppointments)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(tabViewAppointmentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabViewAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        tabViewAppointmentsLayout.setVerticalGroup(
            tabViewAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabViewAppointmentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAppointments)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuDoctor.addTab("View Appoitnments", tabViewAppointments);

        lblAppointments1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblAppointments1.setText("Appointment Notes");

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblSelectAppointment.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblSelectAppointment.setText("Select Appointment:");

        cmbSelectAppointment.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        cmbSelectAppointment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSelectAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSelectAppointmentActionPerformed(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblPrescriptionPatientGender.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPrescriptionPatientGender.setText("Gender:");

        txtPrescriptionPatientAddress.setEditable(false);
        txtPrescriptionPatientAddress.setColumns(20);
        txtPrescriptionPatientAddress.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtPrescriptionPatientAddress.setRows(5);
        jScrollPane2.setViewportView(txtPrescriptionPatientAddress);

        lblPrescriptionPatientAddress.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPrescriptionPatientAddress.setText("Address:");

        txtPrescriptionPatientName.setEditable(false);
        txtPrescriptionPatientName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        txtPrescriptionPatientID.setEditable(false);
        txtPrescriptionPatientID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        txtPrescriptionPatientDOB.setEditable(false);
        txtPrescriptionPatientDOB.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblPrescriptionPatientName.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPrescriptionPatientName.setText("Patient Name:");

        lblPrescriptionPatientGender1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPrescriptionPatientGender1.setText("DOB:");

        lblPatientID.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPatientID.setText("Patient ID:");

        txtPrescriptionPatientGender.setEditable(false);
        txtPrescriptionPatientGender.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblPrescription.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblPrescription.setText("Prescription");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPatientID)
                            .addComponent(lblPrescriptionPatientAddress))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(lblPrescriptionPatientGender1)
                                    .addComponent(lblPrescriptionPatientGender)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addComponent(txtPrescriptionPatientID)
                                .addGap(18, 18, 18)
                                .addComponent(lblPrescriptionPatientName)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrescriptionPatientName)
                            .addComponent(txtPrescriptionPatientGender)
                            .addComponent(txtPrescriptionPatientDOB))
                        .addGap(14, 14, 14))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lblPrescription)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(lblPrescriptionPatientAddress))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lblPrescription)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPatientID)
                            .addComponent(txtPrescriptionPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPrescriptionPatientName)
                            .addComponent(txtPrescriptionPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblPrescriptionPatientGender)
                                    .addComponent(txtPrescriptionPatientGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblPrescriptionPatientGender1)
                                    .addComponent(txtPrescriptionPatientDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblPrescriptionNotes.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblPrescriptionNotes.setText("Notes");

        txtPrescriptionNotes.setColumns(20);
        txtPrescriptionNotes.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtPrescriptionNotes.setLineWrap(true);
        txtPrescriptionNotes.setRows(5);
        jScrollPane3.setViewportView(txtPrescriptionNotes);

        btnSaveNotes.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnSaveNotes.setText("SAVE NOTES AND PRESCRIBE MEDICINE");
        btnSaveNotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveNotesActionPerformed(evt);
            }
        });

        lblPrescriptionMedicine.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblPrescriptionMedicine.setText("Medicine");

        tblPrescriptionMedicine.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tblPrescriptionMedicine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine", "Quantity", "Dosage"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblPrescriptionMedicine);

        btnConcludeAppointment.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnConcludeAppointment.setText("CONCLUDE APPOINTMENT");
        btnConcludeAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConcludeAppointmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnConcludeAppointment)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSaveNotes))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPrescriptionNotes)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPrescriptionMedicine))))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrescriptionNotes)
                    .addComponent(lblPrescriptionMedicine))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConcludeAppointment)
                    .addComponent(btnSaveNotes))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblSelectedAppointmentDate.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblSelectedAppointmentDate.setText("Appointment Date:");

        txtSelectedAppointmentDate.setEditable(false);
        txtSelectedAppointmentDate.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lblSelectAppointment)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbSelectAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblSelectedAppointmentDate)
                        .addGap(18, 18, 18)
                        .addComponent(txtSelectedAppointmentDate)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSelectAppointment)
                    .addComponent(cmbSelectAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSelectedAppointmentDate)
                    .addComponent(txtSelectedAppointmentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout tabMakeNotesLayout = new javax.swing.GroupLayout(tabMakeNotes);
        tabMakeNotes.setLayout(tabMakeNotesLayout);
        tabMakeNotesLayout.setHorizontalGroup(
            tabMakeNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabMakeNotesLayout.createSequentialGroup()
                .addGap(436, 436, 436)
                .addComponent(lblAppointments1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(tabMakeNotesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabMakeNotesLayout.setVerticalGroup(
            tabMakeNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabMakeNotesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAppointments1)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuDoctor.addTab("Make Notes", tabMakeNotes);

        lblPatientHistory.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblPatientHistory.setText("Patient History");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel1.setText("Select Patient:");

        tblPatientHistory.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tblPatientHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Doctor ID", "Date", "Notes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPatientHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPatientHistoryMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblPatientHistory);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel2.setText("Search Patient ID:");

        txtSearchPatientID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        cmbSelectPatientID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        cmbSelectPatientID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSelectPatientID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSelectPatientIDActionPerformed(evt);
            }
        });

        lblPatientName.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPatientName.setText("Name:");

        txtPatientName.setEditable(false);
        txtPatientName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel4.setText("DOB:");

        txtPatientDOB.setEditable(false);
        txtPatientDOB.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtDoctorName.setEditable(false);
        txtDoctorName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        txtPastDate.setEditable(false);
        txtPastDate.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        txtPastNotes.setColumns(20);
        txtPastNotes.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtPastNotes.setRows(5);
        jScrollPane6.setViewportView(txtPastNotes);

        lblDoctorName.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblDoctorName.setText("Doctor:");

        lblPastDate.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPastDate.setText("Date:");

        lblPastNotes.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPastNotes.setText("Notes:");

        tblPastPrescription.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tblPastPrescription.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine", "Quantity", "Dosage"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tblPastPrescription);

        lblPastDate1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPastDate1.setText("Prescription:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblDoctorName)
                    .addComponent(lblPastNotes))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                    .addComponent(txtDoctorName))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPastDate1)
                    .addComponent(lblPastDate))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                    .addComponent(txtPastDate))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDoctorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPastDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDoctorName)
                    .addComponent(lblPastDate))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPastNotes)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPastDate1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setText("SEARCH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabPatientHistoryLayout = new javax.swing.GroupLayout(tabPatientHistory);
        tabPatientHistory.setLayout(tabPatientHistoryLayout);
        tabPatientHistoryLayout.setHorizontalGroup(
            tabPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabPatientHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabPatientHistoryLayout.createSequentialGroup()
                        .addGroup(tabPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabPatientHistoryLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbSelectPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tabPatientHistoryLayout.createSequentialGroup()
                                .addComponent(lblPatientName)
                                .addGap(18, 18, 18)
                                .addComponent(txtPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(55, 55, 55)
                        .addGroup(tabPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabPatientHistoryLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(41, 41, 41)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSearchPatientID, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                            .addComponent(txtPatientDOB))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
            .addGroup(tabPatientHistoryLayout.createSequentialGroup()
                .addGap(461, 461, 461)
                .addComponent(lblPatientHistory)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabPatientHistoryLayout.setVerticalGroup(
            tabPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPatientHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPatientHistory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtSearchPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSelectPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(tabPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPatientName)
                    .addComponent(txtPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtPatientDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuDoctor.addTab("View Patient History", tabPatientHistory);

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblRequestAppointment.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblRequestAppointment.setText("Create Appointment");

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblAccountType2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblAccountType2.setText("Select Patient:");

        cmbSelectPatient.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        cmbSelectPatient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSelectPatient.setToolTipText("");
        cmbSelectPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSelectPatientActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblAccountType2)
                    .addComponent(lblSelectDate)
                    .addComponent(lblSelectTime))
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbSelectPatient, 0, 861, Short.MAX_VALUE)
                    .addComponent(txtEnterDate)
                    .addComponent(txtEnterTime))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSelectPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAccountType2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSelectDate)
                    .addComponent(txtEnterDate, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSelectTime)
                    .addComponent(txtEnterTime))
                .addContainerGap())
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblConfirmAppointment.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblConfirmAppointment.setText("Confirm Appointment");

        jPanel15.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblPatient.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPatient.setText("Patient:");

        txtSelectedPatient.setEditable(false);
        txtSelectedPatient.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtSelectedPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSelectedPatientActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPatient, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSelectedPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 901, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPatient)
                    .addComponent(txtSelectedPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblDate.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblDate.setText("Date:");

        txtEnteredDate.setEditable(false);
        txtEnteredDate.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtEnteredDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnteredDateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtEnteredDate, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate)
                    .addComponent(txtEnteredDate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel19.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTime.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblTime.setText("Time:");

        txtEnteredTime.setEditable(false);
        txtEnteredTime.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtEnteredTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnteredTimeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtEnteredTime, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTime)
                    .addComponent(txtEnteredTime, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        btnConfirmAppointment.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnConfirmAppointment.setText("CONFIRM");
        btnConfirmAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmAppointmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(402, 402, 402)
                        .addComponent(lblConfirmAppointment)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(438, 438, 438)
                .addComponent(btnConfirmAppointment)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblConfirmAppointment)
                .addGap(25, 25, 25)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(btnConfirmAppointment)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(399, 399, 399)
                .addComponent(lblRequestAppointment)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRequestAppointment)
                .addGap(29, 29, 29)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout tabCreateAppointmentLayout = new javax.swing.GroupLayout(tabCreateAppointment);
        tabCreateAppointment.setLayout(tabCreateAppointmentLayout);
        tabCreateAppointmentLayout.setHorizontalGroup(
            tabCreateAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabCreateAppointmentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabCreateAppointmentLayout.setVerticalGroup(
            tabCreateAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabCreateAppointmentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuDoctor.addTab("Create Appointment", tabCreateAppointment);

        lblPrescribeMedicine.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblPrescribeMedicine.setText("Prescribe Medicine");

        jPanel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel3.setText("Patient");

        lblMedicinePatientID.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblMedicinePatientID.setText("Patient ID:");

        txtMedicinePatientID.setEditable(false);
        txtMedicinePatientID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblMedicinePatientName.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblMedicinePatientName.setText("Patient Name:");

        txtMedicinePatientName.setEditable(false);
        txtMedicinePatientName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblMedicineSelected.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblMedicineSelected.setText("Selected Medicine:");

        txtMedicineSelected.setEditable(false);
        txtMedicineSelected.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblMedicineSelected1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblMedicineSelected1.setText("Quantity:");

        txtMedicineQuantity.setEditable(false);
        txtMedicineQuantity.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblMedicineSelected2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblMedicineSelected2.setText("Dosage:");

        txtMedicineDosage.setEditable(false);
        txtMedicineDosage.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        btnPrescribe.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnPrescribe.setText("PRESCRIBE");
        btnPrescribe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrescribeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(lblMedicinePatientID)
                                .addGap(18, 18, 18)
                                .addComponent(txtMedicinePatientID, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblMedicinePatientName)
                                .addGap(18, 18, 18)
                                .addComponent(txtMedicinePatientName, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE))
                            .addComponent(jLabel3))
                        .addContainerGap())
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblMedicineSelected)
                            .addComponent(lblMedicineSelected1)
                            .addComponent(lblMedicineSelected2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtMedicineDosage, javax.swing.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE)
                                .addComponent(txtMedicineSelected, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(txtMedicineQuantity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(447, 447, 447)
                .addComponent(btnPrescribe)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicinePatientID)
                    .addComponent(txtMedicinePatientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMedicinePatientName)
                    .addComponent(txtMedicinePatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicineSelected)
                    .addComponent(txtMedicineSelected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicineSelected1)
                    .addComponent(txtMedicineQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicineSelected2)
                    .addComponent(txtMedicineDosage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPrescribe)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel25.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel20.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblMedicineName.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblMedicineName.setText("Select Medicine:");

        cmbMedicineName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        cmbMedicineName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbMedicineName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMedicineNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMedicineName)
                .addGap(18, 18, 18)
                .addComponent(cmbMedicineName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicineName)
                    .addComponent(cmbMedicineName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel22.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblMedicineName2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblMedicineName2.setText("Enter Quantity:");

        txtQuantity.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantityKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMedicineName2)
                .addGap(18, 18, 18)
                .addComponent(txtQuantity)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicineName2)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel23.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblMedicineName3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblMedicineName3.setText("Enter Dosage:");

        txtDosage.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtDosage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDosageKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMedicineName3)
                .addGap(18, 18, 18)
                .addComponent(txtDosage)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicineName3)
                    .addComponent(txtDosage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        lblMedicine.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblMedicine.setText("Medicine");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(lblMedicine)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMedicine)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout tabPrescribeMedicineLayout = new javax.swing.GroupLayout(tabPrescribeMedicine);
        tabPrescribeMedicine.setLayout(tabPrescribeMedicineLayout);
        tabPrescribeMedicineLayout.setHorizontalGroup(
            tabPrescribeMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPrescribeMedicineLayout.createSequentialGroup()
                .addGroup(tabPrescribeMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabPrescribeMedicineLayout.createSequentialGroup()
                        .addGap(444, 444, 444)
                        .addComponent(lblPrescribeMedicine)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabPrescribeMedicineLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(tabPrescribeMedicineLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tabPrescribeMedicineLayout.setVerticalGroup(
            tabPrescribeMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPrescribeMedicineLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPrescribeMedicine)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menuDoctor.addTab("Prescribe Medicine", tabPrescribeMedicine);

        lblPrescribeMedicine1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblPrescribeMedicine1.setText("Add New Medicine");

        jPanel26.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel27.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblAddMedicineName.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblAddMedicineName.setText("Medicine Name:");

        txtAddMedicineName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtAddMedicineName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAddMedicineNameKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAddMedicineName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtAddMedicineName)
                .addGap(5, 5, 5))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddMedicineName)
                    .addComponent(txtAddMedicineName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel28.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblAddMedicineQuantity.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblAddMedicineQuantity.setText("Enter Quantity:");

        txtAddMedicineQauntity.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtAddMedicineQauntity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAddMedicineQauntityKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAddMedicineQuantity)
                .addGap(18, 18, 18)
                .addComponent(txtAddMedicineQauntity)
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddMedicineQuantity)
                    .addComponent(txtAddMedicineQauntity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        lblMedicine1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblMedicine1.setText("Medicine");

        btnAddMedicine.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnAddMedicine.setText("REQUEST MEDICINE");
        btnAddMedicine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMedicineActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(lblMedicine1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(436, 436, 436)
                .addComponent(btnAddMedicine)
                .addContainerGap(411, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMedicine1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAddMedicine)
                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout tabAddNewMedicineLayout = new javax.swing.GroupLayout(tabAddNewMedicine);
        tabAddNewMedicine.setLayout(tabAddNewMedicineLayout);
        tabAddNewMedicineLayout.setHorizontalGroup(
            tabAddNewMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabAddNewMedicineLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPrescribeMedicine1)
                .addGap(440, 440, 440))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabAddNewMedicineLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabAddNewMedicineLayout.setVerticalGroup(
            tabAddNewMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabAddNewMedicineLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPrescribeMedicine1)
                .addGap(28, 28, 28)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(354, Short.MAX_VALUE))
        );

        menuDoctor.addTab("Add New Medicine", tabAddNewMedicine);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(menuDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 376, Short.MAX_VALUE)
                        .addComponent(lblMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(275, 275, 275)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(menuDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void tblAppointmentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAppointmentsMouseClicked
        int row = 0;
        String[] data = new String[3];
        for(int i = 0; i < data.length; i++)
        {
            row = this.tblAppointments.getSelectedRow();
            String value = this.tblAppointments.getModel().getValueAt(row, i).toString();
            data[i] = value;
        }
        this.txtAppointmentDate.setText(data[2]);
        this.txtAppointmentPatientID.setText(data[0]);
        this.txtAppointmentPatientName.setText(data[1]);
    }//GEN-LAST:event_tblAppointmentsMouseClicked

    private void cmbSelectAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSelectAppointmentActionPerformed
        String selectedAppointment = (String) this.cmbSelectAppointment.getSelectedItem();

        if(selectedAppointment != "Select Appointment")
        {
            for(Appointment appointment : Appointment.appointments)
            {
                if(appointment.getPatient().getID().equals(selectedAppointment))
                {
                    Patient patient = appointment.getPatient();
                    
                    this.txtSelectedAppointmentDate.setText(appointment.getDate());
                    this.txtPrescriptionPatientID.setText(patient.getID());
                    String patientName = patient.getForename() + " " + patient.getSurname();
                    this.txtPrescriptionPatientName.setText(patientName);
                    this.txtPrescriptionPatientAddress.setText(patient.getAddress());
                    this.txtPrescriptionPatientGender.setText(patient.getGender());
                    this.txtPrescriptionPatientDOB.setText(patient.getDOB());
                    
                    for(Prescription prescription : Prescription.prescriptions)
                    {
                        //System.out.println(prescription.getNotes());
                        if((patient.getID().equals(prescription.getPatient().getID())) &&
                                (prescription.getDoctor().getID().equals(User.loggedUser.getID())))
                        {
                            this.txtPrescriptionNotes.setText(prescription.getNotes());
                            
                            DefaultTableModel model = (DefaultTableModel) this.tblPrescriptionMedicine.getModel();
                            int rows = model.getRowCount();
                            if(rows > 0)
                            {
                                for (int i = rows - 1; i >= 0; i--)
                                {
                                    model.removeRow(i);
                                }
                            }
                            
                            String[] data = {
                                prescription.getMedicine().getName(),
                                Integer.toString(prescription.getQuantity()),
                                prescription.getDosage()
                            };

                            model.addRow(data); 
                            break;
                        }
                    }
                    
                    break;
                }
            }
        }
        else
        {
            this.txtSelectedAppointmentDate.setText("");
            this.txtPrescriptionPatientID.setText("");
            this.txtPrescriptionPatientName.setText("");
            this.txtPrescriptionPatientAddress.setText("");
            this.txtPrescriptionPatientGender.setText("");
            this.txtPrescriptionPatientDOB.setText("");
            this.txtPrescriptionNotes.setText("");
            
            DefaultTableModel model = (DefaultTableModel) this.tblPrescriptionMedicine.getModel();
            int rows = model.getRowCount();
            if(rows > 0)
            {
                for (int i = rows - 1; i >= 0; i--)
                {
                    model.removeRow(i);
                }
            }
        }
    }//GEN-LAST:event_cmbSelectAppointmentActionPerformed

    private void btnSaveNotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveNotesActionPerformed
        String notes = this.txtPrescriptionNotes.getText();
        String id = this.txtPrescriptionPatientID.getText();
        
        for(Prescription prescription : Prescription.prescriptions)
        {
            if((prescription.getPatient().getID().equals(id)) && 
                    (prescription.getDoctor().getID()).equals(User.loggedUser.getID()))
            {   
                prescription.setNotes(notes);
                Prescription.savePrescriptions();
                JOptionPane.showMessageDialog(this, "Notes have been saved.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                prescribeMedicine(prescription);
                break;
            }
        }
        System.out.println("CANNOT FIND PRESCRIPTION?");
    }//GEN-LAST:event_btnSaveNotesActionPerformed

    private void cmbSelectPatientIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSelectPatientIDActionPerformed
        String id = (String) this.cmbSelectPatientID.getSelectedItem();
        DefaultTableModel model = (DefaultTableModel) this.tblPatientHistory.getModel();
        if(id != "Select Patient")
        {
            int rows = model.getRowCount();
            if(rows > 0)
            {
                for (int i = rows - 1; i >= 0; i--)
                {
                    model.removeRow(i);
                }
            }
            this.txtPatientName.setText("");
            this.txtPatientDOB.setText("");
            setPastAppointments(id);
        }
        else
        {
            int rows = model.getRowCount();
            if(rows > 0)
            {
                for (int i = rows - 1; i >= 0; i--)
                {
                    model.removeRow(i);
                }
            }
            this.txtPatientName.setText("");
            this.txtPatientDOB.setText("");
        }
    }//GEN-LAST:event_cmbSelectPatientIDActionPerformed

    private void tblPatientHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPatientHistoryMouseClicked
        int row = 0;
        String[] data = new String[3];
        for(int i = 0; i < data.length; i++)
        {
            row = this.tblPatientHistory.getSelectedRow();
            String value = this.tblPatientHistory.getModel().getValueAt(row, i).toString();
            data[i] = value;
        }
        String doctorName = "";
        for(Doctor doctor : Doctor.doctors)
        {
            if(doctor.getID().equals(data[0]))
            {
                doctorName = doctor.getForename() + " " + doctor.getSurname();
                DefaultTableModel prescriptionModel = (DefaultTableModel) this.tblPastPrescription.getModel();
                for(PastAppointment pastAppointment : PastAppointment.pastAppointments)
                {   
                    if((pastAppointment.getPastPrescription().getPatient().getID().equals((String) this.cmbSelectPatientID.getSelectedItem())) 
                            && (pastAppointment.getPastPrescription().getDoctor().getID().equals(doctor.getID())))
                    {
                        System.out.println("Not Getting Here");
                        int rows = prescriptionModel.getRowCount();
                        if(rows > 0)
                        {
                            for (int i = rows - 1; i >= 0; i--)
                            {
                                prescriptionModel.removeRow(i);
                            }
                        }
                        String[] prescriptionData = {
                            pastAppointment.getPastPrescription().getMedicine().getName(),
                            Integer.toString(pastAppointment.getPastPrescription().getQuantity()),
                            pastAppointment.getPastPrescription().getDosage()
                        };
                        
                        System.out.println("Name: " + prescriptionData[0]);
                        System.out.println("Quantity: " + prescriptionData[1]);
                        System.out.println("Dosage: " + prescriptionData[2]);
                        prescriptionModel.addRow(prescriptionData);
                    }
                }
            }
        }
        this.txtDoctorName.setText(doctorName);
        this.txtPastDate.setText(data[1]);
        this.txtPastNotes.setText(data[2]);
    }//GEN-LAST:event_tblPatientHistoryMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String id = this.txtSearchPatientID.getText();
        DefaultTableModel model = (DefaultTableModel) this.tblPatientHistory.getModel();
        if(id.equals(""))
        {
            JOptionPane.showMessageDialog(this, "Enter a Patient ID", "ERROR", JOptionPane.ERROR_MESSAGE);
            int rows = model.getRowCount();
            if(rows > 0)
            {
                for (int i = rows - 1; i >= 0; i--)
                {
                    model.removeRow(i);
                }
            }
            this.txtPatientName.setText("");
            this.txtPatientDOB.setText("");
        }
        else
        {
            int rows = model.getRowCount();
            if(rows > 0)
            {
                for (int i = rows - 1; i >= 0; i--)
                {
                    model.removeRow(i);
                }
            }
            this.txtPatientName.setText("");
            this.txtPatientDOB.setText("");
            setPastAppointments(id);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbSelectPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSelectPatientActionPerformed
        String id = (String) this.cmbSelectPatient.getSelectedItem();
        
        for(Patient patient : Patient.patients)
        {
            if(patient.getID().equals(id))
            {
                String name = patient.getForename() + " " + patient.getSurname();
                this.txtSelectedPatient.setText(name);
                break;
            }            
        }
    }//GEN-LAST:event_cmbSelectPatientActionPerformed

    private void txtSelectedPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSelectedPatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSelectedPatientActionPerformed

    private void txtEnteredDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnteredDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnteredDateActionPerformed

    private void txtEnteredTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnteredTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnteredTimeActionPerformed

    private void btnConfirmAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmAppointmentActionPerformed
        String patientID = (String) this.cmbSelectPatient.getSelectedItem();
        
        String date = this.txtEnterDate.getText() + " " + this.txtEnterTime.getText();
        try
        {
            Date date1 = new SimpleDateFormat("dd/MM/yyyy HH:MM").parse(date);
            if(patientID != "Select Patient")
            {
                int confirm = JOptionPane.showConfirmDialog(this, "ARE YOU SURE YOU WISH TO CONFIRM?", "WARNING", 
                        JOptionPane.INFORMATION_MESSAGE);
                if(confirm == 0)
                {
                    Patient selectedPatient = null;
                    for(Patient patient : Patient.patients)
                    {
                        if(patient.getID().equals(patientID))
                        {
                            selectedPatient = patient;
                            patient.setNotification(new Notification("An Appointment has been arranged with you and Dr. "
                                + User.loggedUser.getSurname() + " for " + date));
                            break;
                        }
                    }
                    
                    Appointment newAppointment = new Appointment((Doctor) User.loggedUser, selectedPatient, date);
                    newAppointment.addAppointment(newAppointment);
                    User.saveUsers();
                    
                    this.cmbSelectPatient.setSelectedIndex(0);
                    this.txtEnterDate.setText("");
                    this.txtEnterTime.setText("");
                    this.txtSelectedPatient.setText("");
                    this.txtEnteredDate.setText("");
                    this.txtEnteredTime.setText("");
                    
                    getAppointments();
                    setAppointments();
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "SELECT A PATIENT", "ERROR", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this, "ENTER A CORRECT DATE AND TIME FORMAT (DD/MM/YY HH:MM)", "ERROR", 
                    JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnConfirmAppointmentActionPerformed

    private void txtEnterDateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnterDateKeyReleased
        this.txtEnteredDate.setText(this.txtEnterDate.getText());
    }//GEN-LAST:event_txtEnterDateKeyReleased

    private void txtEnterTimeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnterTimeKeyReleased
        this.txtEnteredTime.setText(this.txtEnterTime.getText());
    }//GEN-LAST:event_txtEnterTimeKeyReleased

    private void btnConcludeAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConcludeAppointmentActionPerformed
        String id = this.txtPrescriptionPatientID.getText();
        
        for(Patient patient : Patient.patients)
        {
            if(patient.getID().equals(id))
            {
                int confirm = JOptionPane.showConfirmDialog(this, "ARE YOU SURE YOU WISH TO CONCLUDE THIS APPOINTMENT?", 
                        "WARNING", JOptionPane.WARNING_MESSAGE);
                
                if(confirm == 0)
                {
                    for(Prescription prescription : Prescription.prescriptions)
                    {
                        if((prescription.getPatient().getID().equals(patient.getID())) && 
                                (prescription.getDoctor().getID().equals(User.loggedUser.getID())))
                        {
                            System.out.println("SAVED");
                            Prescription newPrescription = prescription;
                            PastAppointment pastAppointment = new PastAppointment((Doctor)User.loggedUser, patient, 
                                this.txtSelectedAppointmentDate.getText(), newPrescription);
                            
                            pastAppointment.addPastAppointment(pastAppointment);
                            removeAppointment(id);
                            getPastAppointments();
                            getAppointments();
                            setAppointments();
                        }
                    }
                    
                }
            }
        }
    }//GEN-LAST:event_btnConcludeAppointmentActionPerformed

    private void btnPrescribeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrescribeActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this,"ARE YOU SURE YOU WISH TO SUBMIT THIS PRESCRIPTION?", "WARNIGN",
                JOptionPane.INFORMATION_MESSAGE);
        if(confirm == 0)
        {
            try
            {
                String selectedMedicine = (String) this.cmbMedicineName.getSelectedItem();
                String patientID = this.txtMedicinePatientID.getText();
                String doctorID = User.loggedUser.getID();
                int quantity = Integer.parseInt(this.txtMedicineQuantity.getText());
                String dosage = this.txtMedicineDosage.getText();

                for(Patient patient : Patient.patients)
                {
                    if(patient.getID().equals(patientID))
                    {
                        for(Medicine medicine : Medicine.medicines)
                        {
                            if(medicine.getName().equals(selectedMedicine))
                            {
                                PrescriptionRequest newPrescriptionRequest = new PrescriptionRequest((Doctor)User.loggedUser, patient, "N/A", medicine,
                                    quantity, dosage);

                                newPrescriptionRequest.addPrescriptionRequest(newPrescriptionRequest);
                                
                                for(Secretary secretary : Secretary.secretarys)
                                {
                                    secretary.setNotification(new Notification("You have new Requests:"
                                        + "\nAccount Reqeusts \nAppointment Reqeusts \nMedicine Requests \nTermination Requests"));
                                }
                                User.saveUsers();
                            }
                        }
                    }
                } 
                
            }
            catch(Exception ex)
            {    
                JOptionPane.showMessageDialog(this, "Enter A Valid Quantity", "ERROR", JOptionPane.ERROR_MESSAGE);
                System.out.println("Error: " + ex);
            }
        }
    }//GEN-LAST:event_btnPrescribeActionPerformed

    private void cmbMedicineNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMedicineNameActionPerformed
        String selectedMedicine = (String) this.cmbMedicineName.getSelectedItem();
        
        if(selectedMedicine != "Select Medicine")
        {
            this.txtMedicineSelected.setText(selectedMedicine);
        }
    }//GEN-LAST:event_cmbMedicineNameActionPerformed

    private void txtQuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyReleased
        this.txtMedicineQuantity.setText(this.txtQuantity.getText());
    }//GEN-LAST:event_txtQuantityKeyReleased

    private void txtDosageKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDosageKeyReleased
        this.txtMedicineDosage.setText(this.txtDosage.getText());
    }//GEN-LAST:event_txtDosageKeyReleased

    private void txtAddMedicineQauntityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddMedicineQauntityKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddMedicineQauntityKeyReleased

    private void txtAddMedicineNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddMedicineNameKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddMedicineNameKeyReleased

    private void btnAddMedicineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMedicineActionPerformed
        String name = this.txtAddMedicineName.getText();
        int quantity = Integer.parseInt(this.txtAddMedicineQauntity.getText());
        
        int confirm = JOptionPane.showConfirmDialog(this, "ARE YOU SURE YOU WISH TO REQUEST NEW MEDICINE?", "WARNING", 
                JOptionPane.INFORMATION_MESSAGE);
        
        if(confirm == 0)
        {
            MedicineRequest newMedicine = new MedicineRequest(name, quantity);
            newMedicine.addMedicineRequest(newMedicine);
            
            for(Secretary secretary : Secretary.secretarys)
            {
                secretary.setNotification(new Notification("You have new Requests:"
                    + "\nAccount Reqeusts \nAppointment Reqeusts \nMedicine Requests \nTermination Requests"));
            }
            User.saveUsers();
                                
            this.txtAddMedicineName.setText("");
            this.txtAddMedicineQauntity.setText("");
        }
    }//GEN-LAST:event_btnAddMedicineActionPerformed

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
            java.util.logging.Logger.getLogger(DoctorMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoctorMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoctorMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoctorMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DoctorMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddMedicine;
    private javax.swing.JButton btnConcludeAppointment;
    private javax.swing.JButton btnConfirmAppointment;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPrescribe;
    private javax.swing.JButton btnSaveNotes;
    private javax.swing.JComboBox<String> cmbMedicineName;
    private javax.swing.JComboBox<String> cmbMedicineName1;
    private javax.swing.JComboBox<String> cmbSelectAppointment;
    private javax.swing.JComboBox<String> cmbSelectPatient;
    private javax.swing.JComboBox<String> cmbSelectPatientID;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblAccountType1;
    private javax.swing.JLabel lblAccountType2;
    private javax.swing.JLabel lblAddMedicineName;
    private javax.swing.JLabel lblAddMedicineQuantity;
    private javax.swing.JLabel lblAppointments;
    private javax.swing.JLabel lblAppointments1;
    private javax.swing.JLabel lblConfirmAppointment;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDoctorName;
    private java.awt.Label lblMain;
    private javax.swing.JLabel lblMedicine;
    private javax.swing.JLabel lblMedicine1;
    private javax.swing.JLabel lblMedicineName;
    private javax.swing.JLabel lblMedicineName1;
    private javax.swing.JLabel lblMedicineName2;
    private javax.swing.JLabel lblMedicineName3;
    private javax.swing.JLabel lblMedicinePatientID;
    private javax.swing.JLabel lblMedicinePatientName;
    private javax.swing.JLabel lblMedicineSelected;
    private javax.swing.JLabel lblMedicineSelected1;
    private javax.swing.JLabel lblMedicineSelected2;
    private javax.swing.JLabel lblNextAppointment;
    private javax.swing.JLabel lblNextDate;
    private javax.swing.JLabel lblNextPatientID;
    private javax.swing.JLabel lblNextPatientName;
    private javax.swing.JLabel lblPastDate;
    private javax.swing.JLabel lblPastDate1;
    private javax.swing.JLabel lblPastNotes;
    private javax.swing.JLabel lblPatient;
    private javax.swing.JLabel lblPatientHistory;
    private javax.swing.JLabel lblPatientID;
    private javax.swing.JLabel lblPatientName;
    private javax.swing.JLabel lblPrescribeMedicine;
    private javax.swing.JLabel lblPrescribeMedicine1;
    private javax.swing.JLabel lblPrescription;
    private javax.swing.JLabel lblPrescriptionMedicine;
    private javax.swing.JLabel lblPrescriptionNotes;
    private javax.swing.JLabel lblPrescriptionPatientAddress;
    private javax.swing.JLabel lblPrescriptionPatientGender;
    private javax.swing.JLabel lblPrescriptionPatientGender1;
    private javax.swing.JLabel lblPrescriptionPatientName;
    private javax.swing.JLabel lblRequestAppointment;
    private javax.swing.JLabel lblSelectAppointment;
    private javax.swing.JLabel lblSelectDate;
    private javax.swing.JLabel lblSelectTime;
    private javax.swing.JLabel lblSelectedAppointmentDate;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblUserID1;
    private javax.swing.JLabel lblUserInfo1;
    private javax.swing.JLabel lblUserName1;
    private javax.swing.JLabel lblUserName2;
    private javax.swing.JTabbedPane menuDoctor;
    private javax.swing.JPanel tabAddNewMedicine;
    private javax.swing.JPanel tabCreateAppointment;
    private javax.swing.JPanel tabMakeNotes;
    private javax.swing.JPanel tabPatientHistory;
    private javax.swing.JPanel tabPrescribeMedicine;
    private javax.swing.JPanel tabUserInfo;
    private javax.swing.JPanel tabViewAppointments;
    private javax.swing.JTable tblAppointments;
    private javax.swing.JTable tblPastPrescription;
    private javax.swing.JTable tblPatientHistory;
    private javax.swing.JTable tblPrescriptionMedicine;
    private javax.swing.JTextField txtAddMedicineName;
    private javax.swing.JTextField txtAddMedicineQauntity;
    private javax.swing.JTextField txtAppointmentDate;
    private javax.swing.JTextField txtAppointmentPatientID;
    private javax.swing.JTextField txtAppointmentPatientName;
    private javax.swing.JTextField txtDoctorName;
    private javax.swing.JTextField txtDosage;
    private javax.swing.JTextField txtEnterDate;
    private javax.swing.JTextField txtEnterTime;
    private javax.swing.JTextField txtEnteredDate;
    private javax.swing.JTextField txtEnteredTime;
    private javax.swing.JTextField txtMedicineDosage;
    private javax.swing.JTextField txtMedicinePatientID;
    private javax.swing.JTextField txtMedicinePatientName;
    private javax.swing.JTextField txtMedicineQuantity;
    private javax.swing.JTextField txtMedicineSelected;
    private javax.swing.JTextField txtPastDate;
    private javax.swing.JTextArea txtPastNotes;
    private javax.swing.JTextField txtPatientDOB;
    private javax.swing.JTextField txtPatientName;
    private javax.swing.JTextArea txtPrescriptionNotes;
    private javax.swing.JTextArea txtPrescriptionPatientAddress;
    private javax.swing.JTextField txtPrescriptionPatientDOB;
    private javax.swing.JTextField txtPrescriptionPatientGender;
    private javax.swing.JTextField txtPrescriptionPatientID;
    private javax.swing.JTextField txtPrescriptionPatientName;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSearchPatientID;
    private javax.swing.JTextField txtSelectedAppointmentDate;
    private javax.swing.JTextField txtSelectedPatient;
    private javax.swing.JTextField txtUserAccountType;
    private javax.swing.JTextArea txtUserAddress;
    private javax.swing.JTextField txtUserID;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
