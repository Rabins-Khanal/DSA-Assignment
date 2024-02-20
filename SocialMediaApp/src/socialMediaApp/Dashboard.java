package socialMediaApp;
import database.DbConnection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.Connection;
import java.util.Set;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class Dashboard extends javax.swing.JFrame {
String username;
    /**
     * Creating new form Dashboard
     */
    public Dashboard(String username) {
        initComponents();
        this.username=username;
        setRecordsToTable(userTable,username);
    }
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        suser = new javax.swing.JTextField();
        sfollowbtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        postlbl = new javax.swing.JTextField();
        post = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Username"
            }
        ));
        jScrollPane1.setViewportView(userTable);

        suser.setText("search user");

        sfollowbtn.setText("follow");
        sfollowbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sfollowbtnActionPerformed(evt);
            }
        });

        jLabel1.setText("WE RECOMMEND YOU FOLLOW");

        postlbl.setText("POST HERE");
        postlbl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postlblActionPerformed(evt);
            }
        });

        post.setText("POST");
        post.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(158, 158, 158)
                .addComponent(postlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(post)
                .addGap(72, 72, 72))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(suser, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(sfollowbtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(suser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sfollowbtn)
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(postlbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(post))))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        pack();
    }

    private void sfollowbtnActionPerformed(java.awt.event.ActionEvent evt) {
    String to_follow = suser.getText();
    String currentUser = username; 
// Checking if the user to follow is not the same as the current user
    if (!to_follow.equals(currentUser)) {
        // Saving the follow relationship to the database
        saveFollowRelationship(currentUser, to_follow); // Implement this method
    } else {
        // Handling the case where the user tries to follow themselves
        System.out.println("You cannot follow yourself.");
    }
    }

    private void postActionPerformed(java.awt.event.ActionEvent evt) {
    String postContent = postlbl.getText();
    String posterUsername = username; 

    // Call the method to save the post to the database
    savePostToDatabase(posterUsername, postContent);
    }

    private void postlblActionPerformed(java.awt.event.ActionEvent evt) {
    }

    // Method to save the post to the database
private void savePostToDatabase(String posterUsername, String postContent) {
    // Define your SQL query to insert the post
    String query = "INSERT INTO Posts (username, content) VALUES (?, ?)";

    try (Connection conn = DbConnection.dbConnect();
         PreparedStatement statement = conn.prepareStatement(query)) {
        // Set the parameters for the SQL query
        statement.setString(1, posterUsername);
        statement.setString(2, postContent);

        // Execute the SQL query to insert the post
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Post saved successfully.");
            JOptionPane.showMessageDialog(null, "posetd successfully");
        } else {
            System.out.println("Failed to save the post.");
        }
    } catch (Exception e) {
        System.out.println("Error saving the post: " + e.getMessage());
    }
}

    // Method to save the follow relationship to the database
private void saveFollowRelationship(String followerUsername, String followeeUsername) {


// Define your SQL query to insert the follow relationship
String query = "INSERT INTO Connections (follower_username, followee_username) VALUES (?, ?)";

try (Connection conn = DbConnection.dbConnect();
     PreparedStatement statement = conn.prepareStatement(query)) {
    // Set the parameters for the SQL query
    statement.setString(1, followerUsername);
    statement.setString(2, followeeUsername);

    // Execute the SQL query to insert the follow relationship
    int rowsInserted = statement.executeUpdate();
    if (rowsInserted > 0) {
        System.out.println("Follow relationship saved successfully.");
        JOptionPane.showMessageDialog(null, "You now follow"+ followeeUsername);
    } else {
        System.out.println("Failed to save follow relationship.");
    }
} catch (Exception e) {
    System.out.println("Error saving follow relationship: " + e.getMessage());
    JOptionPane.showMessageDialog(null, "You already follow"+ followeeUsername);
}


}
    /**
     * @param args the command line arguments
     */
    
 public void setRecordsToTable(JTable userTable, String username) {
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        model.setRowCount(0); // Clear existing table data

        Set<String> recommendedFollowers = getRecommendedFollowers(username);
        if (recommendedFollowers != null) {
            for (String follower : recommendedFollowers) {
                model.addRow(new Object[]{follower});
            }
        }
    }
    
public static Set<String> getRecommendedFollowers(String username) {
        Set<String> recommendedFollowers = null;
        try (Connection conn = DbConnection.dbConnect();) {
            // Create an instance of UserGraph
            UserGraph userGraph = new UserGraph();

            // Load data from the database and fill the graph
            userGraph.loadDataFromDatabase(conn);

            // Get recommended followers for the specified user
            recommendedFollowers = userGraph.getReccomend(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recommendedFollowers;
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard("test user").setVisible(true);
            }
        });
    }
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton post;
    private javax.swing.JTextField postlbl;
    private javax.swing.JButton sfollowbtn;
    private javax.swing.JTextField suser;
    private javax.swing.JTable userTable;
}
