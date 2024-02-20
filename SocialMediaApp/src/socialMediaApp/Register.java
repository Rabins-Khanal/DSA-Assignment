package socialMediaApp;
import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
/**
 *
 */
public class Register extends javax.swing.JFrame {

    /**
     * Creates new form Register
     */
    public Register() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    private void initComponents() {

        ulabel = new javax.swing.JTextField();
        plabel = new javax.swing.JTextField();
        rbutton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ulabel.setText("username");

        plabel.setText("password");

        rbutton.setText("Register");
        rbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ulabel, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(plabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(397, Short.MAX_VALUE)
                .addComponent(rbutton)
                .addGap(388, 388, 388))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(ulabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(plabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(rbutton)
                .addContainerGap(275, Short.MAX_VALUE))
        );

        pack();
    }

    private void rbuttonActionPerformed(java.awt.event.ActionEvent evt) {
        // Get username and password from your Swing components
    String username = ulabel.getText();
    String password = plabel.getText();
    
    // Establish a database connection
    Connection conn = DbConnection.dbConnect();
    
    // Check if the connection is valid
    if (conn != null) {
        try {
            // Create a PreparedStatement to insert user information into the database
            String sql = "INSERT INTO Users (username, password) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            // Set parameters for the PreparedStatement
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            // Execute the PreparedStatement to insert user information
            int rowsInserted = pstmt.executeUpdate();
            
            if (rowsInserted > 0) {
                System.out.println("User inserted successfully.");
                JOptionPane.showMessageDialog(null, "User created  ");
                Login lg= new Login();
                lg.setVisible(true);
                dispose();
            } else {
                System.out.println("Failed to insert user.");
            }
            
            // Close the PreparedStatement
            pstmt.close();
        } catch (Exception ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        } finally {
            // Close the database connection
            try {
                conn.close();
            } catch (Exception ex) {
                System.out.println("Failed to close connection: " + ex.getMessage());
            }
        }
    } else {
        System.out.println("Failed to establish database connection.");
    }
        
    }//GEN-LAST:event_rbuttonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }
    private javax.swing.JTextField plabel;
    private javax.swing.JButton rbutton;
    private javax.swing.JTextField ulabel;
}
