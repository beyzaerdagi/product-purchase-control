package com.mycompany.finalproje;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Exit extends javax.swing.JFrame {

    SimpleAttributeSet attributeSet;
    int customerId;
    String text = "";

    public Exit(int customerId) throws SQLException {
        initComponents();
        this.customerId = customerId;
        attributeSet = new SimpleAttributeSet();
        ClearStuff.clearBasket();
    }
    ProductPayment frame;

    private Exit() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup2 = new javax.swing.ButtonGroup();
        popUp_menu = new javax.swing.JPopupMenu();
        mnItm_note = new javax.swing.JMenuItem();
        mnItm_delete = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPn_note = new javax.swing.JTextPane();
        btn_addImage = new com.mycompany.finalproje.MakeButtonEllipse(){};

        ;
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnItm_writeNote = new javax.swing.JMenuItem();
        mnItm_deleteNote = new javax.swing.JMenuItem();
        rdBtnMnItm_red = new javax.swing.JRadioButtonMenuItem();
        rdBtnMnItm_green = new javax.swing.JRadioButtonMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnItm_account = new javax.swing.JMenuItem();
        mnItm_exit = new javax.swing.JMenuItem();

        mnItm_note.setText("WriteNote");
        mnItm_note.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnItm_noteActionPerformed(evt);
            }
        });
        popUp_menu.add(mnItm_note);

        mnItm_delete.setText("DeleteNote");
        mnItm_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnItm_deleteActionPerformed(evt);
            }
        });
        popUp_menu.add(mnItm_delete);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(221, 176, 250));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ORDER NOTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 1, 16), new java.awt.Color(0, 0, 0))); // NOI18N

        txtPn_note.setEditable(false);
        txtPn_note.setOpaque(false);
        txtPn_note.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtPn_noteMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(txtPn_note);

        btn_addImage.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        btn_addImage.setForeground(new java.awt.Color(0, 0, 0));
        btn_addImage.setText("Add Image");
        btn_addImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_addImage)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_addImage)
                .addGap(0, 18, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);

        jMenu1.setBackground(new java.awt.Color(51, 51, 51));
        jMenu1.setText("Menu");

        mnItm_writeNote.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnItm_writeNote.setText("Write Note");
        mnItm_writeNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnItm_noteActionPerformed(evt);
            }
        });
        jMenu1.add(mnItm_writeNote);

        mnItm_deleteNote.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnItm_deleteNote.setText("Delete Note");
        mnItm_deleteNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnItm_deleteActionPerformed(evt);
            }
        });
        jMenu1.add(mnItm_deleteNote);

        rdBtnMnItm_red.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        buttonGroup2.add(rdBtnMnItm_red);
        rdBtnMnItm_red.setText("Red");
        rdBtnMnItm_red.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdBtnMnItm_redActionPerformed(evt);
            }
        });
        jMenu1.add(rdBtnMnItm_red);

        rdBtnMnItm_green.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        buttonGroup2.add(rdBtnMnItm_green);
        rdBtnMnItm_green.setText("Green");
        rdBtnMnItm_green.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdBtnMnItm_redActionPerformed(evt);
            }
        });
        jMenu1.add(rdBtnMnItm_green);
        jMenu1.add(jSeparator1);

        mnItm_account.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnItm_account.setText("Account");
        mnItm_account.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnItm_accountActionPerformed(evt);
            }
        });
        jMenu1.add(mnItm_account);

        mnItm_exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnItm_exit.setText("Exit");
        mnItm_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnItm_exitActionPerformed(evt);
            }
        });
        jMenu1.add(mnItm_exit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnItm_noteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnItm_noteActionPerformed

        String note = JOptionPane.showInputDialog(rootPane, "Write a order note");
        if (note == null) {
            text = "";
        } else {
            text += note + " ";
        }
        //the entered note is written to the textpane
        txtPn_note.setText(text);
    }//GEN-LAST:event_mnItm_noteActionPerformed

    private void txtPn_noteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPn_noteMouseReleased
        //if it right-clicks the mouse, the popup is displayed
        if (SwingUtilities.isRightMouseButton(evt)) {
            showPopUp(evt);
        }
    }//GEN-LAST:event_txtPn_noteMouseReleased
    private void rdBtnMnItm_redActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdBtnMnItm_redActionPerformed
        //change the color of the note according to the selected color
        if (rdBtnMnItm_red.isSelected()) {
            StyleConstants.setForeground(attributeSet, Color.RED);
        } else if (rdBtnMnItm_green.isSelected()) {
            StyleConstants.setForeground(attributeSet, Color.GREEN);
        }
        txtPn_note.setCharacterAttributes(attributeSet, true);
    }//GEN-LAST:event_rdBtnMnItm_redActionPerformed

    private void mnItm_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnItm_exitActionPerformed
        //close frame
        dispose();
    }//GEN-LAST:event_mnItm_exitActionPerformed

    private void mnItm_accountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnItm_accountActionPerformed
        try {
            //Transferring the user's id to the next frame
            frame = new ProductPayment(customerId);
            //closing this frame
            dispose();
            //switching to another frame
            frame.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Exit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mnItm_accountActionPerformed

    //checks the type of file selected
    public boolean isImage(File file) {
        String name = file.getName();
        return name.endsWith(".jpg") || name.endsWith(".jfif");
    }

    private void btn_addImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addImageActionPerformed

        JFileChooser jf = new JFileChooser();
        int option = jf.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = jf.getSelectedFile();
            if (isImage(file)) {
                //adds the selected image to the textpane
                txtPn_note.setText(text);
                txtPn_note.insertIcon(new ImageIcon(file.getAbsolutePath()));
            }
        }
    }//GEN-LAST:event_btn_addImageActionPerformed

    private void mnItm_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnItm_deleteActionPerformed

        String delete = txtPn_note.getSelectedText();
        //only the location chosen by the customer is deleted
        if (delete != null) {
            String newText = text.replaceAll(delete, "");
            txtPn_note.setText(newText);
            text = newText;
            //if the customer has not selected a string, all are deleted
        } else {
            text = "";
            txtPn_note.setText(text);
        }
    }//GEN-LAST:event_mnItm_deleteActionPerformed
    private void showPopUp(MouseEvent evt) {

        if (evt.isPopupTrigger()) {
            popUp_menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }

    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Exit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_addImage;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem mnItm_account;
    private javax.swing.JMenuItem mnItm_delete;
    private javax.swing.JMenuItem mnItm_deleteNote;
    private javax.swing.JMenuItem mnItm_exit;
    private javax.swing.JMenuItem mnItm_note;
    private javax.swing.JMenuItem mnItm_writeNote;
    private javax.swing.JPopupMenu popUp_menu;
    private javax.swing.JRadioButtonMenuItem rdBtnMnItm_green;
    private javax.swing.JRadioButtonMenuItem rdBtnMnItm_red;
    private javax.swing.JTextPane txtPn_note;
    // End of variables declaration//GEN-END:variables
}
