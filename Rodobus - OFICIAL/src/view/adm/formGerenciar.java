/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.adm;

import controller.conectarDao;
import controller.motoristaDao;
import controller.passageiroDao;
import controller.onibusDao;
import java.util.ArrayList;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import model.Motorista;
import model.Passageiro;
import model.Onibus;
import controller.rotaDao;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Rota;
import java.util.ArrayList;
import controller.conectarDao;
import controller.motoristaDao;
import controller.reservaDao;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JFrame;
import javax.swing.text.MaskFormatter;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import model.Motorista;
import model.Reserva;
import view.clientes.formCadastroPassageiro;
import view.clientes.formLogin;

/**
 *
 * @author matheus.rafael
 */
public class formGerenciar extends javax.swing.JFrame {

    public void CaregarPassageiro(){
        passageiroDao passageiroDao = new passageiroDao();
        ArrayList<Passageiro> passageiros = passageiroDao.selecionarPassageiros();
        DefaultTableModel passageiroModel = (DefaultTableModel) tblPASSAGEIRO.getModel();
        passageiroModel.setRowCount(0);

        for (Passageiro passageiro : passageiros) {
            passageiroModel.addRow(new Object[]{passageiro.getIdPassageiro(), passageiro.getNome(), passageiro.getEmail(), passageiro.getCpf(), passageiro.getIdade(),passageiro.getTelefone(),passageiro.getSenha()});
        }

    }
    
    public void CaregarMotorista(){
        motoristaDao motoristaDao = new motoristaDao();
        ArrayList<Motorista> motoristas = motoristaDao.selecionarMotoristas();
        
        DefaultTableModel motoristaModel = (DefaultTableModel) tblMOTORISTAS.getModel();
        motoristaModel.setRowCount(0);

        for (Motorista motorista : motoristas) {
            motoristaModel.addRow(new Object[]{motorista.getIdMotorista(), motorista.getNome(), motorista.getIdade(), motorista.getCpf(), motorista.getTelefone()});
        }
    }
    

    public void CarregarMotoristas() {
        DefaultComboBoxModel<String> mymodel = (DefaultComboBoxModel<String>) this.cmbMOTORISTA4.getModel();

        if (mymodel != null) {
            mymodel.removeAllElements();
        }

        motoristaDao motoristaDao = new motoristaDao();
        motoristaDao.criarBanco();
        ArrayList<Motorista> motoristas = motoristaDao.selecionarMotoristas();

        mymodel.addElement("");
        for (Motorista motorista : motoristas) {
            mymodel.addElement(motorista.getIdMotorista() + " - " + motorista.getNome());
        }
    }
    public void CarregarReservas(){
        reservaDao reservaDao = new reservaDao();
       ArrayList<Reserva> reservas = reservaDao.selecionarReservas();
        
        DefaultTableModel reservaModel = (DefaultTableModel) tblRESERVAS.getModel();
        reservaModel.setRowCount(0);

        for (Reserva reserva: reservas) {
            reservaModel.addRow(new Object[]{reserva.getIdReserva(), reserva.getIdRota(), reserva.getIdOnibus(), reserva.getIdMotorista(), reserva.getIdPassageiro(), reserva.getDataReserva(), reserva.getStatus()});
        }
    }
    
    
    public void CaregarOnibus() {
        onibusDao onibusDao = new onibusDao();
        ArrayList<Onibus> onibuss = onibusDao.selecionarOnibus();
        DefaultTableModel onibusModel = (DefaultTableModel) tblOnibus.getModel();
        onibusModel.setRowCount(0);

        for (Onibus onibus : onibuss) {
            onibusModel.addRow(new Object[]{onibus.getIdOnibus(), onibus.getModelo(), onibus.getPlaca(), onibus.getCapacidade(), onibus.getAnoFabricacao()});
        }
    }
     MaskFormatter mascara;

    MaskFormatter mascaraValor;
    public formGerenciar() {
        initComponents();
        try {
            mascara = new MaskFormatter("##/##/#### ##:##:##");
            mascaraValor = new MaskFormatter("###,###.##");
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        conectarDao oDao = new conectarDao();
        CaregarMotorista();
        CaregarOnibus();
        CaregarPassageiro();
        CarregarReservas();
        CarregarMotoristas();
    }
public void CarregarRotas() {

        rotaDao rota = new rotaDao();
        rota.criarBanco();
        ArrayList<Rota> rotas = rota.selecionarRotas();
        DefaultTableModel model = (DefaultTableModel) tblROTA.getModel();
        model.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for (Rota rota2 : rotas) {
            model.addRow(new Object[]{rota2.getIdRota(), rota2.getIdMotorista() + " - " + rota2.getNomeMotorista(), rota2.getVlPreco(), rota2.getOrigem(), rota2.getDestino(), sdf.format(rota2.getDtSaida()), sdf.format(rota2.getDtChegada())});
        }
    }
  public void verificarData() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        String inputText = txtCHEGADA4.getText().trim();
        Date date = parseDate(inputText, sdf1);

        if (date == null) {
            date = parseDate(inputText, sdf2);
        }

        if (date != null) {
            txtCHEGADA4.setValue(sdf1.format(date));
        } else {
            showError("Formato de data de saída é inválido. Use o formato dd/MM/yyyy HH:mm:ss");
            txtCHEGADA4.setFocusLostBehavior(JFormattedTextField.PERSIST);
        }

        String inputText2 = txtSAIDA4.getText().trim();
        Date date2 = parseDate(inputText2, sdf2);

        if (date2 == null) {
            date = parseDate(inputText, sdf2);
        }

        if (date2 != null) {
            txtSAIDA4.setValue(sdf1.format(date2));
            txtSAIDA4.setFocusLostBehavior(JFormattedTextField.PERSIST);
        } else {
            showError("Formato de data de saída é inválido. Use o formato dd/MM/yyyy HH:mm:ss");
            txtSAIDA4.setFocusLostBehavior(JFormattedTextField.PERSIST);
        }
    }

    private Date parseDate(String input, SimpleDateFormat sdf) {
        try {
            sdf.setLenient(false);
            return sdf.parse(input);
        } catch (ParseException e) {
            return null;
        }
    }

    private void showError(String errorMessage) {
        System.err.println(errorMessage);
        JOptionPane.showMessageDialog(null, errorMessage, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        pgROTAS = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMOTORISTAS = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        textTelefone = new javax.swing.JTextField();
        lblID = new javax.swing.JLabel();
        textIdade = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        textCPF = new javax.swing.JTextField();
        btnCadastrar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textNome = new javax.swing.JTextField();
        btnAlterar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnNOVO = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        textEMAIL1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        textNOME1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        textTELEFONE1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        textIDADE1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        textSENHA1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        textCPF1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPASSAGEIRO = new javax.swing.JTable();
        btnDeletar1 = new javax.swing.JButton();
        btnBuscar1 = new javax.swing.JButton();
        btnAlterar1 = new javax.swing.JButton();
        btnCadastrar1 = new javax.swing.JButton();
        lblID1 = new javax.swing.JLabel();
        btnNOVO1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        textModelobus = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        textPlacabus = new javax.swing.JTextField();
        textCapacidadebus = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        textAnofabricacao = new javax.swing.JTextField();
        btnCadastrar2 = new javax.swing.JButton();
        btnCadastrarbus = new javax.swing.JButton();
        btnAlterarbus = new javax.swing.JButton();
        btnDeletarbus = new javax.swing.JButton();
        btnBuscarbus = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblOnibus = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        lblbusID = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        btnReserva = new javax.swing.JButton();
        btnBuscareserva = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblRESERVAS = new javax.swing.JTable();
        jLabel41 = new javax.swing.JLabel();
        lblreservaid = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblROTA = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        btnCADASTRAR4 = new javax.swing.JButton();
        btnNOVO4 = new javax.swing.JButton();
        btnALTERAR4 = new javax.swing.JButton();
        txtDESTINO4 = new javax.swing.JTextField();
        txtORIGEM4 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtCHEGADA4 = new javax.swing.JFormattedTextField(mascara);
        cmbMOTORISTA4 = new javax.swing.JComboBox<>();
        btnDELETAR4 = new javax.swing.JButton();
        txtVALOR4 = new javax.swing.JFormattedTextField(mascaraValor);
        btnBUSCAR4 = new javax.swing.JButton();
        lbID4 = new javax.swing.JLabel();
        txtSAIDA4 = new javax.swing.JFormattedTextField(mascara);
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        mnSAIR = new javax.swing.JMenu();

        jPanel5.setBackground(new java.awt.Color(142, 157, 204));

        jLabel16.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Cadastrar novo Usuário");

        jLabel17.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("EMAIL");

        jLabel18.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("NOME");

        jLabel19.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("TELEFONE");

        jLabel20.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("IDADE");

        jTextField14.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jTextField14.setForeground(new java.awt.Color(180, 180, 180));

        jLabel21.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("SENHA");

        jLabel22.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("CPF");

        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(125, 132, 178));
        jButton3.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("CADASTRAR");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel16)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 912, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pgROTAS.setPreferredSize(new java.awt.Dimension(1100, 462));

        jPanel2.setBackground(new java.awt.Color(242, 147, 4));
        jPanel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                jPanel2ComponentMoved(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Consulta de motorista");

        tblMOTORISTAS.setAutoCreateRowSorter(true);
        tblMOTORISTAS.setForeground(new java.awt.Color(60, 63, 65));
        tblMOTORISTAS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "Idade", "CPF", "Telefone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMOTORISTAS.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tblMOTORISTAS.setSelectionForeground(new java.awt.Color(60, 63, 65));
        tblMOTORISTAS.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tblMOTORISTASFocusGained(evt);
            }
        });
        tblMOTORISTAS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMOTORISTASMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMOTORISTAS);

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Telefone");

        textTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textTelefoneActionPerformed(evt);
            }
        });

        lblID.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        lblID.setForeground(new java.awt.Color(255, 255, 255));
        lblID.setText("0");

        textIdade.setInheritsPopupMenu(true);
        textIdade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textIdadeActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial Black", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("CPF");

        btnCadastrar.setBackground(new java.awt.Color(69, 73, 74));
        btnCadastrar.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnCadastrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCadastrarMouseClicked(evt);
            }
        });
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial Black", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cadastrar motorista");

        jLabel6.setFont(new java.awt.Font("Arial Black", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nome");

        btnAlterar.setBackground(new java.awt.Color(69, 73, 74));
        btnAlterar.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnAlterar.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterar.setText("Alterar");
        btnAlterar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAlterarMouseClicked(evt);
            }
        });
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(69, 73, 74));
        btnBuscar.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarMouseClicked(evt);
            }
        });
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnDeletar.setBackground(new java.awt.Color(69, 73, 74));
        btnDeletar.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnDeletar.setForeground(new java.awt.Color(255, 255, 255));
        btnDeletar.setText("Deletar");
        btnDeletar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeletarMouseClicked(evt);
            }
        });
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial Black", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Idade");

        btnNOVO.setBackground(new java.awt.Color(69, 73, 74));
        btnNOVO.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnNOVO.setForeground(new java.awt.Color(255, 255, 255));
        btnNOVO.setText("Novo Cadastro");
        btnNOVO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNOVOMouseClicked(evt);
            }
        });
        btnNOVO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNOVOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(textTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(31, 31, 31)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(textIdade)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addComponent(textNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(textCPF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnNOVO, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(352, 352, 352)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(33, 33, 33))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAlterar)
                            .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNOVO, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDeletar)
                            .addComponent(btnBuscar))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(lblID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pgROTAS.addTab("Motorista", jPanel1);

        jPanel4.setBackground(new java.awt.Color(242, 147, 4));

        jLabel9.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Cadastrar novo usuário/passageiro");

        jLabel10.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Email");

        jLabel11.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Nome");

        jLabel12.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Telefone");

        jLabel13.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Idade");

        jLabel14.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Senha");

        jLabel15.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("CPF");

        textCPF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCPF1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Consulta de passageiros");

        tblPASSAGEIRO.setForeground(new java.awt.Color(69, 73, 74));
        tblPASSAGEIRO.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "Email", "CPF", "Idade", "Telefone", "Senha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPASSAGEIRO.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tblPASSAGEIROFocusGained(evt);
            }
        });
        tblPASSAGEIRO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPASSAGEIROMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPASSAGEIRO);

        btnDeletar1.setBackground(new java.awt.Color(69, 73, 74));
        btnDeletar1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnDeletar1.setForeground(new java.awt.Color(255, 255, 255));
        btnDeletar1.setText("Deletar");
        btnDeletar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeletar1MouseClicked(evt);
            }
        });
        btnDeletar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletar1ActionPerformed(evt);
            }
        });

        btnBuscar1.setBackground(new java.awt.Color(69, 73, 74));
        btnBuscar1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnBuscar1.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar1.setText("Buscar");
        btnBuscar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscar1MouseClicked(evt);
            }
        });
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        btnAlterar1.setBackground(new java.awt.Color(69, 73, 74));
        btnAlterar1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnAlterar1.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterar1.setText("Alterar");
        btnAlterar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAlterar1MouseClicked(evt);
            }
        });
        btnAlterar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterar1ActionPerformed(evt);
            }
        });

        btnCadastrar1.setBackground(new java.awt.Color(69, 73, 74));
        btnCadastrar1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnCadastrar1.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastrar1.setText("Cadastrar");
        btnCadastrar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCadastrar1MouseClicked(evt);
            }
        });
        btnCadastrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrar1ActionPerformed(evt);
            }
        });

        lblID1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        lblID1.setForeground(new java.awt.Color(255, 255, 255));
        lblID1.setText("0");

        btnNOVO1.setBackground(new java.awt.Color(69, 73, 74));
        btnNOVO1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnNOVO1.setForeground(new java.awt.Color(255, 255, 255));
        btnNOVO1.setText("Novo Cadastro");
        btnNOVO1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNOVO1MouseClicked(evt);
            }
        });
        btnNOVO1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNOVO1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblID1)
                        .addContainerGap(969, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel11)
                                .addComponent(jLabel9)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12)
                                        .addComponent(textTELEFONE1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel15)
                                        .addComponent(textCPF1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10))
                                    .addGap(27, 27, 27)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(textIDADE1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel13)))
                                .addComponent(jLabel14)
                                .addComponent(textSENHA1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textEMAIL1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textNOME1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(btnCadastrar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(95, 95, 95)
                                    .addComponent(btnAlterar1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnNOVO1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(btnDeletar1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(352, 352, 352)
                                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(290, 290, 290)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textNOME1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textTELEFONE1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textIDADE1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textCPF1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textEMAIL1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(textSENHA1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCadastrar1)
                            .addComponent(btnAlterar1))
                        .addGap(10, 10, 10)
                        .addComponent(btnNOVO1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDeletar1)
                            .addComponent(btnBuscar1))))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(lblID1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pgROTAS.addTab("Passageiro", jPanel3);

        jPanel8.setBackground(new java.awt.Color(242, 147, 4));

        jLabel8.setFont(new java.awt.Font("Arial Black", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Modelo");

        jLabel23.setFont(new java.awt.Font("Arial Black", 0, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Cadastrar ônibus");

        jLabel24.setFont(new java.awt.Font("Arial Black", 0, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Placa");

        textPlacabus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPlacabusActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Arial Black", 0, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Capacidade");

        jLabel26.setFont(new java.awt.Font("Arial Black", 0, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Ano Fabricação");

        textAnofabricacao.setInheritsPopupMenu(true);
        textAnofabricacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textAnofabricacaoActionPerformed(evt);
            }
        });

        btnCadastrar2.setBackground(new java.awt.Color(69, 73, 74));
        btnCadastrar2.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnCadastrar2.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastrar2.setText("Cadastrar");
        btnCadastrar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCadastrar2MouseClicked(evt);
            }
        });
        btnCadastrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrar2ActionPerformed(evt);
            }
        });

        btnCadastrarbus.setBackground(new java.awt.Color(69, 73, 74));
        btnCadastrarbus.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnCadastrarbus.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastrarbus.setText("Novo Cadastro");
        btnCadastrarbus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCadastrarbusMouseClicked(evt);
            }
        });
        btnCadastrarbus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarbusActionPerformed(evt);
            }
        });

        btnAlterarbus.setBackground(new java.awt.Color(69, 73, 74));
        btnAlterarbus.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnAlterarbus.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterarbus.setText("Alterar");
        btnAlterarbus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAlterarbusMouseClicked(evt);
            }
        });
        btnAlterarbus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarbusActionPerformed(evt);
            }
        });

        btnDeletarbus.setBackground(new java.awt.Color(69, 73, 74));
        btnDeletarbus.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnDeletarbus.setForeground(new java.awt.Color(255, 255, 255));
        btnDeletarbus.setText("Deletar");
        btnDeletarbus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeletarbusMouseClicked(evt);
            }
        });
        btnDeletarbus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarbusActionPerformed(evt);
            }
        });

        btnBuscarbus.setBackground(new java.awt.Color(69, 73, 74));
        btnBuscarbus.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnBuscarbus.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarbus.setText("Buscar");
        btnBuscarbus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarbusMouseClicked(evt);
            }
        });
        btnBuscarbus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarbusActionPerformed(evt);
            }
        });

        tblOnibus.setForeground(new java.awt.Color(60, 63, 65));
        tblOnibus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Modelo", "Placa", "Capacidade", "Ano Fabr"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOnibus.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tblOnibus.setSelectionForeground(new java.awt.Color(60, 63, 65));
        tblOnibus.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tblOnibusFocusGained(evt);
            }
        });
        tblOnibus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOnibusMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblOnibus);

        jLabel27.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Consulta de ônibus");

        lblbusID.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        lblbusID.setForeground(new java.awt.Color(255, 255, 255));
        lblbusID.setText("0");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textModelobus)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel8)
                            .addComponent(lblbusID)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(textPlacabus, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(textCapacidadebus, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(textAnofabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnCadastrar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCadastrarbus, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                                .addGap(51, 51, 51)
                                .addComponent(btnAlterarbus, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(btnDeletarbus, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(352, 352, 352)
                        .addComponent(btnBuscarbus, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel27)))
                .addGap(33, 33, 33))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addGap(3, 3, 3)
                        .addComponent(textModelobus, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textPlacabus, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textCapacidadebus, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textAnofabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCadastrar2)
                            .addComponent(btnAlterarbus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCadastrarbus, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDeletarbus)
                            .addComponent(btnBuscarbus))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(lblbusID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pgROTAS.addTab("Ônibus", jPanel7);

        jPanel11.setBackground(new java.awt.Color(242, 147, 4));

        btnReserva.setBackground(new java.awt.Color(69, 73, 74));
        btnReserva.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnReserva.setForeground(new java.awt.Color(255, 255, 255));
        btnReserva.setText("Deletar");
        btnReserva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReservaMouseClicked(evt);
            }
        });
        btnReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservaActionPerformed(evt);
            }
        });

        btnBuscareserva.setBackground(new java.awt.Color(69, 73, 74));
        btnBuscareserva.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnBuscareserva.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscareserva.setText("Buscar");
        btnBuscareserva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscareservaMouseClicked(evt);
            }
        });
        btnBuscareserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscareservaActionPerformed(evt);
            }
        });

        tblRESERVAS.setForeground(new java.awt.Color(60, 63, 65));
        tblRESERVAS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID reserva", "Destino rota", "Modelo bus", "Motorista", "Dt reserva", "Status reserva"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRESERVAS.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tblRESERVAS.setSelectionForeground(new java.awt.Color(60, 63, 65));
        tblRESERVAS.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tblRESERVASFocusGained(evt);
            }
        });
        tblRESERVAS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRESERVASMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblRESERVAS);

        jLabel41.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Consulta de reservas");

        lblreservaid.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        lblreservaid.setForeground(new java.awt.Color(255, 255, 255));
        lblreservaid.setText("0");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel41)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(lblreservaid))
                            .addComponent(btnReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscareserva, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 811, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(136, Short.MAX_VALUE))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel41)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReserva)
                    .addComponent(btnBuscareserva))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(lblreservaid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pgROTAS.addTab("Reservas", jPanel9);

        jPanel10.setBackground(new java.awt.Color(242, 147, 4));

        tblROTA.setForeground(new java.awt.Color(60, 63, 65));
        tblROTA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Motorista", "Valor", "Filial Saida", "Filial Chegada", "Data saída", "Data chegada"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblROTA.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tblROTA.setSelectionForeground(new java.awt.Color(60, 63, 65));
        tblROTA.getTableHeader().setReorderingAllowed(false);
        tblROTA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblROTAMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblROTA);

        jLabel28.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Cadastro de rotas");

        jLabel29.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Filial de destino");

        jLabel30.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Filial de origem");

        jLabel31.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Data chegada");

        jLabel32.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Data saída");

        jLabel33.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Valor");

        jLabel34.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Motorista");

        btnCADASTRAR4.setBackground(new java.awt.Color(69, 73, 74));
        btnCADASTRAR4.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        btnCADASTRAR4.setForeground(new java.awt.Color(255, 255, 255));
        btnCADASTRAR4.setText("Cadastro");
        btnCADASTRAR4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCADASTRAR4MouseClicked(evt);
            }
        });
        btnCADASTRAR4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCADASTRAR4ActionPerformed(evt);
            }
        });

        btnNOVO4.setBackground(new java.awt.Color(69, 73, 74));
        btnNOVO4.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        btnNOVO4.setForeground(new java.awt.Color(255, 255, 255));
        btnNOVO4.setText("Novo Cadastro");
        btnNOVO4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNOVO4MouseClicked(evt);
            }
        });
        btnNOVO4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNOVO4ActionPerformed(evt);
            }
        });

        btnALTERAR4.setBackground(new java.awt.Color(69, 73, 74));
        btnALTERAR4.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        btnALTERAR4.setForeground(new java.awt.Color(255, 255, 255));
        btnALTERAR4.setText("Alterar");
        btnALTERAR4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnALTERAR4MouseClicked(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Cadastro de rotas");

        txtCHEGADA4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCHEGADA4FocusLost(evt);
            }
        });

        cmbMOTORISTA4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbMOTORISTA4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMOTORISTA4ActionPerformed(evt);
            }
        });

        btnDELETAR4.setBackground(new java.awt.Color(69, 73, 74));
        btnDELETAR4.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        btnDELETAR4.setForeground(new java.awt.Color(255, 255, 255));
        btnDELETAR4.setText("Deletar");
        btnDELETAR4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDELETAR4MouseClicked(evt);
            }
        });
        btnDELETAR4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDELETAR4ActionPerformed(evt);
            }
        });

        txtVALOR4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVALOR4ActionPerformed(evt);
            }
        });

        btnBUSCAR4.setBackground(new java.awt.Color(69, 73, 74));
        btnBUSCAR4.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        btnBUSCAR4.setForeground(new java.awt.Color(255, 255, 255));
        btnBUSCAR4.setText("Buscar");
        btnBUSCAR4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBUSCAR4MouseClicked(evt);
            }
        });
        btnBUSCAR4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBUSCAR4ActionPerformed(evt);
            }
        });

        lbID4.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        lbID4.setForeground(new java.awt.Color(255, 255, 255));
        lbID4.setText("0");

        txtSAIDA4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSAIDA4FocusLost(evt);
            }
        });
        txtSAIDA4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSAIDA4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(cmbMOTORISTA4, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)
                            .addComponent(txtORIGEM4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)
                            .addComponent(txtDESTINO4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCADASTRAR4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel32)
                                .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtCHEGADA4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtVALOR4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnALTERAR4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSAIDA4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbID4)
                    .addComponent(btnNOVO4))
                .addGap(57, 57, 57)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(btnDELETAR4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBUSCAR4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE))
                        .addGap(20, 20, 20))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDELETAR4)
                            .addComponent(btnBUSCAR4))
                        .addGap(26, 26, 26))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(jLabel30))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtORIGEM4)
                            .addComponent(txtSAIDA4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDESTINO4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCHEGADA4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbMOTORISTA4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtVALOR4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnALTERAR4)
                            .addComponent(btnCADASTRAR4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNOVO4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                        .addComponent(lbID4)
                        .addContainerGap())))
        );

        pgROTAS.addTab("Rotas", jPanel10);

        jMenu3.setText("RodoBus");
        jMenuBar2.add(jMenu3);

        mnSAIR.setText("Sair");
        mnSAIR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnSAIRMouseClicked(evt);
            }
        });
        jMenuBar2.add(mnSAIR);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pgROTAS, javax.swing.GroupLayout.DEFAULT_SIZE, 1005, Short.MAX_VALUE)
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pgROTAS, javax.swing.GroupLayout.PREFERRED_SIZE, 516, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void mnSAIRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnSAIRMouseClicked
        formLogin login = new formLogin();
        login.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(false);
        login.setVisible(true);
    }//GEN-LAST:event_mnSAIRMouseClicked

    private void txtSAIDA4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSAIDA4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSAIDA4ActionPerformed

    private void txtSAIDA4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSAIDA4FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSAIDA4FocusLost

    private void btnBUSCAR4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBUSCAR4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBUSCAR4ActionPerformed

    private void btnBUSCAR4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBUSCAR4MouseClicked
        CarregarRotas();
        CaregarMotorista();
    }//GEN-LAST:event_btnBUSCAR4MouseClicked

    private void txtVALOR4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVALOR4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVALOR4ActionPerformed

    private void btnDELETAR4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDELETAR4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDELETAR4ActionPerformed

    private void btnDELETAR4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDELETAR4MouseClicked
        rotaDao rota = new rotaDao();
        rota.excluir(Integer.parseInt(this.lbID4.getText()));
        CarregarRotas();
    }//GEN-LAST:event_btnDELETAR4MouseClicked

    private void cmbMOTORISTA4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMOTORISTA4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMOTORISTA4ActionPerformed

    private void txtCHEGADA4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCHEGADA4FocusLost

    }//GEN-LAST:event_txtCHEGADA4FocusLost

    private void btnALTERAR4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnALTERAR4MouseClicked
        rotaDao rota = new rotaDao();
    
        String IdMoto = (String)cmbMOTORISTA4.getSelectedItem();
        String numeroComoString = IdMoto.replaceAll("\\D+", "");
        int motorista = Integer.parseInt(numeroComoString);
     verificarData(); 
       String saidaText = txtSAIDA4.getText();
        String chegadaText = txtCHEGADA4.getText();
        Date saida = null;
        Date chegada = null;

        try {
            saida = (Date) new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(saidaText);
            chegada = (Date) new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(chegadaText);
        } catch (ParseException ex) {

        }
        DefaultTableModel model = (DefaultTableModel) tblROTA.getModel();
        Rota rotass = new Rota();
        rotass.setOrigem(this.txtORIGEM4.getText());
        rotass.setDestino(this.txtDESTINO4.getText());
        rotass.setDtChegada(chegada);
        rotass.setDtSaida(saida);
        rotass.setIdMotorista(motorista);
        rotass.setIdRota(Integer.parseInt(this.lbID4.getText()));
     
        rota.alterar(rotass);
            txtSAIDA4.setFocusLostBehavior(JFormattedTextField.PERSIST);
        txtCHEGADA4.setFocusLostBehavior(JFormattedTextField.PERSIST);
        CarregarRotas();
    }//GEN-LAST:event_btnALTERAR4MouseClicked

    private void btnNOVO4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNOVO4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNOVO4ActionPerformed

    private void btnNOVO4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNOVO4MouseClicked
        lbID4.setText("0");
        txtORIGEM4.setText("");
        txtDESTINO4.setText("");
        txtCHEGADA4.setText("");
        txtSAIDA4.setText("");

        txtSAIDA4.setFocusLostBehavior(JFormattedTextField.PERSIST);
        txtCHEGADA4.setFocusLostBehavior(JFormattedTextField.PERSIST);

        txtVALOR4.setText("");
        cmbMOTORISTA4.setSelectedIndex(0);
    }//GEN-LAST:event_btnNOVO4MouseClicked

    private void btnCADASTRAR4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCADASTRAR4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCADASTRAR4ActionPerformed

    private void btnCADASTRAR4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCADASTRAR4MouseClicked
        verificarData();

        rotaDao r = new rotaDao();
        Rota rota = new Rota();

        String origem = txtORIGEM4.getText();
        String destino = txtDESTINO4.getText();

        String textFromTextField = txtVALOR4.getText();

        String IdMoto = (String)cmbMOTORISTA4.getSelectedItem();
        String numeroComoString = IdMoto.replaceAll("\\D+", "");
        int motorista = Integer.parseInt(numeroComoString);

        double valor = 0;
        String cleanedText = textFromTextField.replaceAll("[^0-9.]", "");
        if (!cleanedText.isEmpty() && !cleanedText.equals(".")) {
            valor = Double.parseDouble(cleanedText);
        } else {
        }

        String saidaText = txtSAIDA4.getText();
        String chegadaText = txtCHEGADA4.getText();

        Date saida = null;
        Date chegada = null;

        try {
            saida = (Date) new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(saidaText);
            chegada = (Date) new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(chegadaText);
        } catch (ParseException ex) {

        }

        rota.setDestino(destino);
        rota.setOrigem(origem);
        rota.setDtChegada(chegada);
        rota.setDtSaida(saida);
        rota.setVlPreco(valor);
        rota.setIdMotorista(motorista);
        r.incluirRota(rota);

        txtSAIDA4.setFocusLostBehavior(JFormattedTextField.PERSIST);
        txtCHEGADA4.setFocusLostBehavior(JFormattedTextField.PERSIST);

        CarregarRotas();
    }//GEN-LAST:event_btnCADASTRAR4MouseClicked

    private void tblROTAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblROTAMouseClicked
        DefaultTableModel model = (DefaultTableModel) tblROTA.getModel();

        int selectedRow = tblROTA.getSelectedRow();

        if (selectedRow != -1) {
            lbID4.setText(model.getValueAt(selectedRow, 0).toString());

            txtVALOR4.setText(model.getValueAt(selectedRow, 2).toString());
            txtORIGEM4.setText(model.getValueAt(selectedRow, 3).toString());
            txtDESTINO4.setText(model.getValueAt(selectedRow, 4).toString());

            String MOTORISTA = model.getValueAt(selectedRow, 1).toString();
            int index = -1;
            for (int i = 0; i < cmbMOTORISTA4.getItemCount(); i++) {
                if (MOTORISTA.equals(cmbMOTORISTA4.getItemAt(i))) {
                    index = i;
                    break;
                }
            }

            if (index != -1) {
                cmbMOTORISTA4.setSelectedIndex(index);
            } else {
                cmbMOTORISTA4.setSelectedIndex(0);
            }

            txtSAIDA4.setFormatterFactory(null);
            txtSAIDA4.setText(model.getValueAt(selectedRow, 5).toString());

            AbstractFormatterFactory formatterFactory = new DefaultFormatterFactory(mascara);
            txtSAIDA4.setFormatterFactory(formatterFactory);

            // txtCHEGADA4.setFormatterFactory(null);
            txtCHEGADA4.setText(model.getValueAt(selectedRow, 6).toString());

        } else {
            lbID4.setText("0");
            txtORIGEM4.setText("");
            txtDESTINO4.setText("");
            txtSAIDA4.setText("");
            txtCHEGADA4.setText("");
            txtVALOR4.setText("");
            cmbMOTORISTA4.setSelectedIndex(0);
        }

        txtSAIDA4.setFocusLostBehavior(JFormattedTextField.PERSIST);
        txtCHEGADA4.setFocusLostBehavior(JFormattedTextField.PERSIST);
    }//GEN-LAST:event_tblROTAMouseClicked

    private void tblOnibusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOnibusMouseClicked
        DefaultTableModel model = (DefaultTableModel) tblOnibus.getModel();

        int selectedRow = tblOnibus.getSelectedRow();

        if (selectedRow != -1) {
            lblbusID.setText(tblOnibus.getValueAt(selectedRow, 0).toString());
            textModelobus.setText(tblOnibus.getValueAt(selectedRow, 1).toString());
            textPlacabus.setText(tblOnibus.getValueAt(selectedRow, 2).toString());
            textCapacidadebus.setText(tblOnibus.getValueAt(selectedRow, 3).toString());
            textAnofabricacao.setText(tblOnibus.getValueAt(selectedRow, 4).toString());

        } else {
            lblbusID.setText("0");
            textModelobus.setText("");
            textAnofabricacao.setText("");
            textCapacidadebus.setText("");
            textPlacabus.setText("");
        }
    }//GEN-LAST:event_tblOnibusMouseClicked

    private void tblOnibusFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblOnibusFocusGained

    }//GEN-LAST:event_tblOnibusFocusGained

    private void btnBuscarbusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarbusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarbusActionPerformed

    private void btnBuscarbusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarbusMouseClicked
        CaregarOnibus();
    }//GEN-LAST:event_btnBuscarbusMouseClicked

    private void btnDeletarbusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarbusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeletarbusActionPerformed

    private void btnDeletarbusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeletarbusMouseClicked
        onibusDao bus = new onibusDao();
        bus.excluir(Integer.parseInt(this.lblbusID.getText()));

        CaregarOnibus();

        this.textCapacidadebus.setText("");
        this.textModelobus.setText("");
        this.textPlacabus.setText("");
        this.textAnofabricacao.setText("");
        this.lblbusID.setText("");
    }//GEN-LAST:event_btnDeletarbusMouseClicked

    private void btnAlterarbusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarbusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAlterarbusActionPerformed

    private void btnAlterarbusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterarbusMouseClicked
        onibusDao bus = new onibusDao();

        DefaultTableModel model = (DefaultTableModel) tblOnibus.getModel();

        Onibus Cadbus = new Onibus();

        Cadbus.setModelo(this.textModelobus.getText());
        Cadbus.setPlaca(this.textPlacabus.getText());
        Cadbus.setAnoFabricacao(Integer.parseInt(this.textAnofabricacao.getText()));
        Cadbus.setCapacidade(Integer.parseInt(this.textCapacidadebus.getText()));
        Cadbus.setIdOnibus(Integer.parseInt(this.lblbusID.getText()));

        bus.alterar(Cadbus);

        CaregarOnibus();
    }//GEN-LAST:event_btnAlterarbusMouseClicked

    private void btnCadastrarbusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarbusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCadastrarbusActionPerformed

    private void btnCadastrarbusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarbusMouseClicked
        this.textCapacidadebus.setText("");
        this.textModelobus.setText("");
        this.textPlacabus.setText("");
        this.textAnofabricacao.setText("");
        this.lblbusID.setText("");
    }//GEN-LAST:event_btnCadastrarbusMouseClicked

    private void btnCadastrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrar2ActionPerformed

    }//GEN-LAST:event_btnCadastrar2ActionPerformed

    private void btnCadastrar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrar2MouseClicked

        Onibus Cadbus = new Onibus();

        Cadbus.setModelo(this.textModelobus.getText());
        Cadbus.setPlaca(this.textPlacabus.getText());
        Cadbus.setAnoFabricacao(Integer.parseInt(this.textAnofabricacao.getText()));
        Cadbus.setCapacidade(Integer.parseInt(this.textCapacidadebus.getText()));
        Cadbus.setIdOnibus(0);

        onibusDao bus = new onibusDao();

        bus.incluir(Cadbus);

        //CaregarMotorista();
    }//GEN-LAST:event_btnCadastrar2MouseClicked

    private void textAnofabricacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAnofabricacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textAnofabricacaoActionPerformed

    private void textPlacabusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPlacabusActionPerformed

    }//GEN-LAST:event_textPlacabusActionPerformed

    private void btnNOVO1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNOVO1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNOVO1ActionPerformed

    private void btnNOVO1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNOVO1MouseClicked
        lblID1.setText("0");
        textNOME1.setText("");
        textIDADE1.setText("");
        textCPF1.setText("");
        textTELEFONE1.setText("");
        textEMAIL1.setText("");
        textSENHA1.setText("");
    }//GEN-LAST:event_btnNOVO1MouseClicked

    private void btnCadastrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCadastrar1ActionPerformed

    private void btnCadastrar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrar1MouseClicked
        Passageiro novoPassageiro = new Passageiro();

        novoPassageiro.setCpf(this.textCPF1.getText());
        novoPassageiro.setNome(this.textNOME1.getText());
        novoPassageiro.setTelefone(this.textTELEFONE1.getText());
        novoPassageiro.setEmail(this.textEMAIL1.getText());
        novoPassageiro.setIdade(Integer.parseInt(this.textIDADE1.getText()));
        novoPassageiro.setSenha(this.textSENHA1.getText());
        novoPassageiro.setIdPassageiro(0);

        passageiroDao passageiroDao = new passageiroDao();
        passageiroDao.Incluir(novoPassageiro);

        CaregarPassageiro();
    }//GEN-LAST:event_btnCadastrar1MouseClicked

    private void btnAlterar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAlterar1ActionPerformed

    private void btnAlterar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterar1MouseClicked
        Passageiro passageiroParaAtualizar = new Passageiro();

        passageiroParaAtualizar.setIdPassageiro(Integer.parseInt(this.lblID1.getText()));
        passageiroParaAtualizar.setCpf(this.textCPF1.getText());
        passageiroParaAtualizar.setNome(this.textNOME1.getText());
        passageiroParaAtualizar.setTelefone(this.textTELEFONE1.getText());
        passageiroParaAtualizar.setIdade(Integer.parseInt(this.textIDADE1.getText()));
        passageiroParaAtualizar.setEmail(this.textEMAIL1.getText());
        passageiroParaAtualizar.setSenha(this.textSENHA1.getText());

        passageiroDao passageiroDao = new passageiroDao();
        passageiroDao.Alterar(passageiroParaAtualizar);

        CaregarPassageiro();
    }//GEN-LAST:event_btnAlterar1MouseClicked

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void btnBuscar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscar1MouseClicked
        CaregarPassageiro();
    }//GEN-LAST:event_btnBuscar1MouseClicked

    private void btnDeletar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeletar1ActionPerformed

    private void btnDeletar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeletar1MouseClicked
        passageiroDao passageiroDao = new passageiroDao();
        passageiroDao.Excluir(Integer.parseInt(this.lblID1.getText()));

        CaregarPassageiro();

        this.textCPF1.setText("");
        this.textNOME1.setText("");
        this.textTELEFONE1.setText("");
        this.textIDADE1.setText("");
        this.textSENHA1.setText("");
        this.textEMAIL1.setText("");
        this.lblID1.setText("");
    }//GEN-LAST:event_btnDeletar1MouseClicked

    private void tblPASSAGEIROMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPASSAGEIROMouseClicked
        DefaultTableModel passageiroModel = (DefaultTableModel) tblPASSAGEIRO.getModel();

        int selectedRow = tblPASSAGEIRO.getSelectedRow();

        if (selectedRow != -1) {
            lblID1.setText(tblPASSAGEIRO.getValueAt(selectedRow, 0).toString());
            textNOME1.setText(tblPASSAGEIRO.getValueAt(selectedRow, 1).toString());
            textEMAIL1.setText(tblPASSAGEIRO.getValueAt(selectedRow, 2).toString());
            textCPF1.setText(tblPASSAGEIRO.getValueAt(selectedRow, 3).toString());
            textIDADE1.setText(tblPASSAGEIRO.getValueAt(selectedRow, 4).toString());
            textTELEFONE1.setText(tblPASSAGEIRO.getValueAt(selectedRow, 5).toString());
            textSENHA1.setText(tblPASSAGEIRO.getValueAt(selectedRow, 6).toString());
        } else {
            lblID1.setText("0");
            textNOME1.setText("");
            textIDADE1.setText("");
            textCPF1.setText("");
            textTELEFONE1.setText("");
            textEMAIL1.setText("");
            textSENHA1.setText("");
        }
    }//GEN-LAST:event_tblPASSAGEIROMouseClicked

    private void tblPASSAGEIROFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblPASSAGEIROFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPASSAGEIROFocusGained

    private void textCPF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCPF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCPF1ActionPerformed

    private void jPanel2ComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel2ComponentMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2ComponentMoved

    private void btnNOVOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNOVOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNOVOActionPerformed

    private void btnNOVOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNOVOMouseClicked
        this.textCPF.setText("");
        this.textNome.setText("");
        this.textTelefone.setText("");
        this.textIdade.setText("");
        this.lblID.setText("");
    }//GEN-LAST:event_btnNOVOMouseClicked

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeletarActionPerformed

    private void btnDeletarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeletarMouseClicked
        motoristaDao m = new motoristaDao();
        m.excluir(Integer.parseInt(this.lblID.getText()));

        CaregarMotorista();

        this.textCPF.setText("");
        this.textNome.setText("");
        this.textTelefone.setText("");
        this.textIdade.setText("");
        this.lblID.setText("");
    }//GEN-LAST:event_btnDeletarMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseClicked
        CaregarMotorista();
    }//GEN-LAST:event_btnBuscarMouseClicked

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnAlterarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlterarMouseClicked
        motoristaDao m = new motoristaDao();

        DefaultTableModel model = (DefaultTableModel) tblMOTORISTAS.getModel();

        Motorista Cadmoto = new Motorista();

        Cadmoto.setCpf(this.textCPF.getText());
        Cadmoto.setNome(this.textNome.getText());
        Cadmoto.setTelefone(this.textTelefone.getText());
        Cadmoto.setIdade(Integer.parseInt(this.textIdade.getText()));
        Cadmoto.setIdMotorista(Integer.parseInt(this.lblID.getText()));

        m.alterar(Cadmoto);

        CaregarMotorista();
    }//GEN-LAST:event_btnAlterarMouseClicked

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed

    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnCadastrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCadastrarMouseClicked

        Motorista Cadmoto = new Motorista();

        Cadmoto.setCpf(this.textCPF.getText());
        Cadmoto.setNome(this.textNome.getText());
        Cadmoto.setTelefone(this.textTelefone.getText());
        Cadmoto.setIdade(Integer.parseInt(this.textIdade.getText()));
        Cadmoto.setIdMotorista(0);

        motoristaDao m = new motoristaDao();

        m.incluir(Cadmoto);

        CaregarMotorista();
    }//GEN-LAST:event_btnCadastrarMouseClicked

    private void textIdadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textIdadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textIdadeActionPerformed

    private void textTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTelefoneActionPerformed

    }//GEN-LAST:event_textTelefoneActionPerformed

    private void tblMOTORISTASMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMOTORISTASMouseClicked
        DefaultTableModel model = (DefaultTableModel) tblMOTORISTAS.getModel();

        int selectedRow = tblMOTORISTAS.getSelectedRow();

        if (selectedRow != -1) {
            lblID.setText(tblMOTORISTAS.getValueAt(selectedRow, 0).toString());
            textNome.setText(tblMOTORISTAS.getValueAt(selectedRow, 1).toString());
            textIdade.setText(tblMOTORISTAS.getValueAt(selectedRow, 2).toString());
            textCPF.setText(tblMOTORISTAS.getValueAt(selectedRow, 3).toString());
            textTelefone.setText(tblMOTORISTAS.getValueAt(selectedRow, 4).toString());

        } else {
            lblID.setText("0");
            textNome.setText("");
            textIdade.setText("");
            textCPF.setText("");
            textTelefone.setText("");
        }
    }//GEN-LAST:event_tblMOTORISTASMouseClicked

    private void tblMOTORISTASFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblMOTORISTASFocusGained

    }//GEN-LAST:event_tblMOTORISTASFocusGained

    private void btnReservaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReservaMouseClicked
        // TODO add your handling code here:
        
       reservaDao reservaDao = new reservaDao();
       reservaDao.excluirReserva(Integer.parseInt(this.lblreservaid.getText()));

    }//GEN-LAST:event_btnReservaMouseClicked

    private void btnReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReservaActionPerformed

    private void btnBuscareservaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscareservaMouseClicked
         CarregarRotas();
        CaregarMotorista();
    }//GEN-LAST:event_btnBuscareservaMouseClicked

    private void btnBuscareservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscareservaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscareservaActionPerformed

    private void tblRESERVASFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblRESERVASFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tblRESERVASFocusGained

    private void tblRESERVASMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRESERVASMouseClicked
        // TODO add your handling code here:
                DefaultTableModel model = (DefaultTableModel) tblRESERVAS.getModel();
                        int selectedRow = tblRESERVAS.getSelectedRow();
             lblreservaid.setText(tblRESERVAS.getValueAt(selectedRow, 0).toString());  
        
    }//GEN-LAST:event_tblRESERVASMouseClicked

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
            java.util.logging.Logger.getLogger(formGerenciar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formGerenciar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formGerenciar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formGerenciar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formGerenciar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnALTERAR4;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnAlterar1;
    private javax.swing.JButton btnAlterarbus;
    private javax.swing.JButton btnBUSCAR4;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnBuscarbus;
    private javax.swing.JButton btnBuscareserva;
    private javax.swing.JButton btnCADASTRAR4;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnCadastrar1;
    private javax.swing.JButton btnCadastrar2;
    private javax.swing.JButton btnCadastrarbus;
    private javax.swing.JButton btnDELETAR4;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnDeletar1;
    private javax.swing.JButton btnDeletarbus;
    private javax.swing.JButton btnNOVO;
    private javax.swing.JButton btnNOVO1;
    private javax.swing.JButton btnNOVO4;
    private javax.swing.JButton btnReserva;
    private javax.swing.JComboBox<String> cmbMOTORISTA4;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lbID4;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblID1;
    private javax.swing.JLabel lblbusID;
    private javax.swing.JLabel lblreservaid;
    private javax.swing.JMenu mnSAIR;
    private javax.swing.JTabbedPane pgROTAS;
    private javax.swing.JTable tblMOTORISTAS;
    private javax.swing.JTable tblOnibus;
    private javax.swing.JTable tblPASSAGEIRO;
    private javax.swing.JTable tblRESERVAS;
    private javax.swing.JTable tblROTA;
    private javax.swing.JTextField textAnofabricacao;
    private javax.swing.JTextField textCPF;
    private javax.swing.JTextField textCPF1;
    private javax.swing.JTextField textCapacidadebus;
    private javax.swing.JTextField textEMAIL1;
    private javax.swing.JTextField textIDADE1;
    private javax.swing.JTextField textIdade;
    private javax.swing.JTextField textModelobus;
    private javax.swing.JTextField textNOME1;
    private javax.swing.JTextField textNome;
    private javax.swing.JTextField textPlacabus;
    private javax.swing.JTextField textSENHA1;
    private javax.swing.JTextField textTELEFONE1;
    private javax.swing.JTextField textTelefone;
    private javax.swing.JFormattedTextField txtCHEGADA4;
    private javax.swing.JTextField txtDESTINO4;
    private javax.swing.JTextField txtORIGEM4;
    private javax.swing.JFormattedTextField txtSAIDA4;
    private javax.swing.JFormattedTextField txtVALOR4;
    // End of variables declaration//GEN-END:variables
}
