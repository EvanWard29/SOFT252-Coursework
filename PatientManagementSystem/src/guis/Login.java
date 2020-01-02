/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;
import system.*;
import users.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;
import java.lang.Thread;
import java.lang.Runtime;


/**
 *
 * @author LoL-1
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        
        //User.setUsers();
        //User.saveUsers();
        User.getUsers();
        
        Appointment.setAppointments();
        Appointment.saveAppointments();
        Appointment.getAppointments();
        
        Prescription.setPrescriptions();
        Prescription.savePrescriptions();
        Prescription.getPrescriptions();
        
        PastAppointment.setPastAppointments();
        PastAppointment.savePastAppointments();
        PastAppointment.getPastAppointments();
        
        //Medicine.setMedicine();
        //Medicine.saveMedicine();
        Medicine.getMedicine();
        
        //PrescriptionRequest.setPrescriptionRequests();
        //PrescriptionRequest.savePrescriptionRequests();
        PrescriptionRequest.getPrescriptionRequests();
        
        //MedicineRequest.setMedicineRequests();
        //MedicineRequest.saveMedicineRequests();
        MedicineRequest.getMedicineRequests();
        
        AppointmentRequest.setAppointmentRequests();
        AppointmentRequest.saveAppointmentRequests();
        AppointmentRequest.getAppointmentRequests();
        
        Feedback.setFeedback();
        Feedback.saveFeedback();
        Feedback.getFeedback();
        
        TerminationRequest.setTerminationRequests();
        TerminationRequest.saveTerminationRequests();
        TerminationRequest.getTerminationRequests();
    }
    
    public static String hashPassword(String password)
    {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        return generatedPassword;
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
        txtUserID = new javax.swing.JTextField();
        lblUserID = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblMain.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblMain.setName(""); // NOI18N
        lblMain.setText("Patient Management System");

        txtUserID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblUserID.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblUserID.setText("User ID:");

        lblPassword.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblPassword.setText("Password:");

        txtPassword.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        btnLogin.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnLogin.setText("LOGIN");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 325, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblPassword)
                    .addComponent(lblUserID))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPassword)
                    .addComponent(txtUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(252, 252, 252))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(446, 446, 446))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(327, 327, 327))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUserID))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String id = this.txtUserID.getText();    
        String password = hashPassword(this.txtPassword.getText());
        
        boolean login = false;
        
        for(User user : User.users)
        {   
            if((user.getID().equals(id) && (user.getPassword().equals(password))))
            {
                if(user.getID().charAt(0) == 'P')
                {
                    //SHOW PATIENT MENU
                    User.loggedUser = user;
                    
                    PatientMenu patientMenu = new PatientMenu();
                    patientMenu.setVisible(true);
                }
                else if(user.getID().charAt(0) == 'D')
                {
                    //SHOW DOCTOR MENU
                    User.loggedUser = user;
                    
                    DoctorMenu doctorMenu = new DoctorMenu();
                    doctorMenu.setVisible(true);
                }
                else if(user.getID().charAt(0) == 'A')
                {
                    //SHOW ADMIN MENU
                    User.loggedUser = user;
                    
                    AdminMenu adminMenu = new AdminMenu();
                    adminMenu.setVisible(true);
                }
                else if(user.getID().charAt(0) == 'S')
                {
                    //SHOW SECRETARY MENU
                    User.loggedUser = user;
                    
                    SecretaryMenu secretaryMenu = new SecretaryMenu();
                    secretaryMenu.setVisible(true);    
                }
                this.setVisible(false);
                login = true; 
                break;
            }
        }
        
        if(login != true)
        {
            JOptionPane.showMessageDialog(null, "Incorrect User ID or Password", "ERROR", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
        
        ShutDownTask shutDownTask = new ShutDownTask();
        Runtime.getRuntime().addShutdownHook(shutDownTask);
    }
    
    private static class ShutDownTask extends Thread
    {
        @Override
	public void run() 
        {
            User.saveUsers();
            Appointment.saveAppointments();
            Prescription.savePrescriptions();
            PastAppointment.savePastAppointments();
            Medicine.saveMedicine();
            PrescriptionRequest.savePrescriptionRequests();
            MedicineRequest.saveMedicineRequests();
            AppointmentRequest.saveAppointmentRequests();
            Feedback.saveFeedback();
	}
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private java.awt.Label lblMain;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUserID;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserID;
    // End of variables declaration//GEN-END:variables
}


