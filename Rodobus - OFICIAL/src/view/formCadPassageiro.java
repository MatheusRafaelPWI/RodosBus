/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.passageiroDao;
import javax.swing.table.DefaultTableModel;
import model.Passageiro;

/**
 *
 * @author thiag
 */
public class formCadPassageiro extends javax.swing.JFrame {

    /**
     * Creates new form formCadas
     */
    public formCadPassageiro() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        ds_email = new javax.swing.JTextField();
        ds_nome = new javax.swing.JTextField();
        ds_telefone = new javax.swing.JTextField();
        ds_senha = new javax.swing.JTextField();
        ds_cpf = new javax.swing.JTextField();
        ds_dia = new javax.swing.JTextField();
        ds_mes = new javax.swing.JTextField();
        ds_idade = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnCadastra = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro");
        setBackground(new java.awt.Color(153, 153, 255));
        getContentPane().setLayout(null);

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("CRIAR CONTA");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 10, 140, 83);

        ds_email.setName("txtEmail"); // NOI18N
        getContentPane().add(ds_email);
        ds_email.setBounds(30, 120, 742, 30);
        getContentPane().add(ds_nome);
        ds_nome.setBounds(30, 180, 742, 30);

        ds_telefone.setName("txtFone"); // NOI18N
        getContentPane().add(ds_telefone);
        ds_telefone.setBounds(30, 270, 270, 30);

        ds_senha.setName("txtSenha"); // NOI18N
        getContentPane().add(ds_senha);
        ds_senha.setBounds(30, 350, 270, 30);

        ds_cpf.setName("txtCpf"); // NOI18N
        getContentPane().add(ds_cpf);
        ds_cpf.setBounds(340, 350, 250, 30);

        ds_dia.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        ds_dia.setForeground(new java.awt.Color(204, 204, 204));
        ds_dia.setText("DD");
        ds_dia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ds_diaActionPerformed(evt);
            }
        });
        getContentPane().add(ds_dia);
        ds_dia.setBounds(340, 270, 99, 30);

        ds_mes.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        ds_mes.setForeground(new java.awt.Color(204, 204, 204));
        ds_mes.setText("MM");
        ds_mes.setName("txtMes"); // NOI18N
        getContentPane().add(ds_mes);
        ds_mes.setBounds(460, 270, 99, 30);

        ds_idade.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        ds_idade.setForeground(new java.awt.Color(204, 204, 204));
        ds_idade.setText("YY");
        ds_idade.setName("txtAno"); // NOI18N
        getContentPane().add(ds_idade);
        ds_idade.setBounds(580, 270, 99, 30);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SENHA ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 330, 100, 19);

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("EMAIL");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 100, 60, 19);

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("NOME");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 160, 60, 19);

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("IDADE");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(340, 250, 100, 19);

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("CPF");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(340, 330, 100, 19);

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("TELEFONE");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(30, 250, 100, 19);

        btnCadastra.setBackground(new java.awt.Color(51, 51, 51));
        btnCadastra.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        btnCadastra.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastra.setText("CADASTRAR ");
        btnCadastra.setName("btnCadastra"); // NOI18N
        btnCadastra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCadastraMouseClicked(evt);
            }
        });
        getContentPane().add(btnCadastra);
        btnCadastra.setBounds(340, 440, 140, 33);

        jLabel3.setBackground(new java.awt.Color(125, 132, 178));
        jLabel3.setForeground(new java.awt.Color(125, 132, 178));
        jLabel3.setText("fundo");
        jLabel3.setOpaque(true);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(-250, -20, 1200, 580);

        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 0, 21)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("DD");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(350, 300, 33, 45);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastraMouseClicked
        // TODO add your handling code here:
       passageiroDao u = new passageiroDao();
       Passageiro passageiro = new Passageiro();
       
       String cpf = ds_cpf.getText();
       String nome = ds_nome.getText();
       String telefone = ds_telefone.getText();
       String email = ds_email.getText();
       String senha = ds_senha.getText();
       int idade = Integer.parseInt(ds_idade.getText());
       
       passageiro.setCpf(cpf);
       passageiro.setEmail(email);
       passageiro.setNome(nome);
       passageiro.setIdade(idade);
       passageiro.setTelefone(telefone);
       passageiro.setSenha(senha);
       
       u.cadastrar(passageiro);
    }//GEN-LAST:event_btnCadastraMouseClicked

    private void ds_diaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ds_diaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ds_diaActionPerformed

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
            java.util.logging.Logger.getLogger(formCadPassageiro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formCadPassageiro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formCadPassageiro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formCadPassageiro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formCadPassageiro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastra;
    private javax.swing.JTextField ds_cpf;
    private javax.swing.JTextField ds_dia;
    private javax.swing.JTextField ds_email;
    private javax.swing.JTextField ds_idade;
    private javax.swing.JTextField ds_mes;
    private javax.swing.JTextField ds_nome;
    private javax.swing.JTextField ds_senha;
    private javax.swing.JTextField ds_telefone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
