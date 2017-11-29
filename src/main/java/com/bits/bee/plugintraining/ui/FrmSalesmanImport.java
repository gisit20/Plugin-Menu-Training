/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bits.bee.plugintraining.ui;

import com.bits.bee.plugintraining.bl.Salesman2;
import com.bits.bee.ui.ScreenManager;
import com.bits.bee.ui.UIMgr;
import com.bits.bee.xls.XLSReader;
import com.bits.bee.xls.XLSUtils;
import com.bits.lib.BHelp;
import com.bits.lib.BUtil;
import com.bits.lib.dbswing.JBdbComboBox;
import com.bits.lib.dx.BDM;
import com.bits.lib.dx.BProcSimple;
import com.bits.lib.dx.provider.BTableProvider;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Sigit Sukarman
 */
public class FrmSalesmanImport extends javax.swing.JInternalFrame implements PropertyChangeListener {

    private final int OPTIONAL = -1;

    private int srepid;
    private int srepname;
    private int addr;
    private int zipcode;
    private int city;
    private int mobile;
    private int email;

    XLSReader reader;

    ArrayList<JBdbComboBox> alCombo = new ArrayList();

    ArrayList<String> columnModel;
    String[] value;

    private Salesman2 srep;
    private int succes = 0;
    private int error = 0;
    //init spReset
    BProcSimple spReset = new BProcSimple(BDM.getDefault(), "spSrepReset", "srepid");

    /**
     * Creates new form FrmSalesmanImport
     */
    public FrmSalesmanImport() {
        initComponents();
        reader = new XLSReader();

        alCombo.add(cboSrepID);
        alCombo.add(cboSrepName);
        alCombo.add(cboAlamat);
        alCombo.add(cboZipcode);
        alCombo.add(cboCity);
        alCombo.add(cboPhone);
        alCombo.add(cboEmail);

        btnProcess.setEnabled(false);
        btnRefresh.setEnabled(false);
        BUtil.setEnabledJTabbedPane(jTabbedPane1, false);

        jPanelChooser1.addPropertyChangeListener("fileName", this);
    }

    private void readFile() {
        try {
            reader.readFile(jPanelChooser1.getFilePath());
        } catch (Exception ex) {
            UIMgr.showErrorDialog("Gagal Membaca File Excel", ex);
        }
        //mengambil niai dari ecxel
        columnModel = reader.getWBHeader();
        value = new String[columnModel.size()];
        for (int i = 0; i < columnModel.size(); i++) {
            value[i] = columnModel.get(i);
        }
        //set model
        for (int i = 0; i < alCombo.size(); i++) {
            ((JBdbComboBox) alCombo.get(i)).setModel(new DefaultComboBoxModel(value));
            ((JBdbComboBox) alCombo.get(i)).setSelectedIndex(i);
        }
    }
    
    private void refreshForm() {
        Component[] compPanel = jPanel2.getComponents();
        
        if (jPanelChooser1.getFileName() != null) {
            btnProcess.setEnabled(true);
        } else {
            btnProcess.setEnabled(false);
        }
        for (Component compPane : compPanel) {
            if (compPane instanceof JBdbComboBox) {
                ((JBdbComboBox) compPane).setSelectedIndex(-1);
            }
        }
        jTabbedPane1.setSelectedIndex(0);
        txtLog.setText("");
        
        succes=0;
        error=0;
    }

    //cek apakah ada kesalahan dalam pengisian form
    private void validateForm() throws Exception {
        if (cekCombo()) {
            throw new Exception("Ada kolom terpilih yang sama ");
        }
        if (cboSrepID.getSelectedIndex() < 0) {
            throw new Exception("Kolom Kode Pegawai harus diisi !");
        }
        if (cboSrepName.getSelectedIndex() < 0) {
            throw new Exception("Kolom Nama Pegawai harus diisi !");
        }
        if (cboAlamat.getSelectedIndex() < 0) {
            throw new Exception("Kolom Alamat harus diisi !");
        }
    }

    //untuk cek apakah ada data yg sama
    private boolean cekCombo() {
        boolean duplikat = false;

        srepid = cboSrepID.getSelectedIndex();
        srepname = cboSrepName.getSelectedIndex();
        addr = cboAlamat.getSelectedIndex();
        zipcode = cboZipcode.getSelectedIndex();
        city = cboCity.getSelectedIndex();
        mobile = cboPhone.getSelectedIndex();
        email = cboEmail.getSelectedIndex();

        int[] data = {srepid, srepname, addr, zipcode, city, mobile, email};
        for (int i = 0; i < data.length; i++) {
            int temp = data[i];
            //looping lihat data berikutnya apakah sama dg yg sekarang
            for (int j = i + 1; j < data.length; j++) {
                int e = data[j];
                if ((data[j] != OPTIONAL) && (data[j] == temp) && (temp != OPTIONAL)) {
                    duplikat = true;
                    break;
                }
            }
        }
        return duplikat;
    }

    private void readData() {
        srepid = cboSrepID.getSelectedIndex();
        srepname = cboSrepName.getSelectedIndex();
        addr = cboAlamat.getSelectedIndex();
        zipcode = cboZipcode.getSelectedIndex();
        city = cboCity.getSelectedIndex();
        mobile = cboPhone.getSelectedIndex();
        email = cboEmail.getSelectedIndex();

        ArrayList<ArrayList<String>> data = reader.getDataByIndex();

        for (int i = 1; i < data.size(); i++) {
            //nge get datarow
            ArrayList<String> dataRow = data.get(i);

            int dataRowSize = dataRow.size();

            srep = BTableProvider.createTable(Salesman2.class);
            srep.New();

            srep.getDataSet().setString("srepid", dataRow.get(srepid));
            srep.getDataSet().setString("srepname", dataRow.get(srepname));
            srep.getDataSet().setString("addr", dataRow.get(addr));

            if (zipcode != OPTIONAL && XLSUtils.isNotNullOrEmpty(dataRow.get(zipcode)) && dataRowSize >= zipcode) {
                srep.getDataSet().setString("zipcode", dataRow.get(zipcode));
            }
            if (city != OPTIONAL && XLSUtils.isNotNullOrEmpty(dataRow.get(city)) && dataRowSize >= city) {
                srep.getDataSet().setString("cityid", dataRow.get(city));
            }
            if (mobile != OPTIONAL && XLSUtils.isNotNullOrEmpty(dataRow.get(mobile)) && dataRowSize >= mobile) {
                srep.getDataSet().setString("mobile", dataRow.get(mobile));
            }
            if (email != OPTIONAL && XLSUtils.isNotNullOrEmpty(dataRow.get(email)) && dataRowSize >= email) {
                srep.getDataSet().setString("email", dataRow.get(email));
            }

            try {
                srep.saveChanges();
                txtLog.append("Row ke-" + i + " Berhasil diimport. \n");
                succes++;
            } catch (Exception ex) {
                txtLog.append("Row ke-" + i + " Gagal di import.\nPesan error: " + BHelp.getExceptionDetail(ex));
                error++;
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormLabel1 = new com.bits.bee.ui.myswing.JFormLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanelChooser1 = new com.bits.bee.ui.myswing.JPanelChooser();
        btnRefresh = new javax.swing.JButton();
        btnProcess = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboSrepID = new com.bits.lib.dbswing.JBdbComboBox();
        jLabel2 = new javax.swing.JLabel();
        cboSrepName = new com.bits.lib.dbswing.JBdbComboBox();
        jLabel3 = new javax.swing.JLabel();
        cboAlamat = new com.bits.lib.dbswing.JBdbComboBox();
        jLabel4 = new javax.swing.JLabel();
        cboCity = new com.bits.lib.dbswing.JBdbComboBox();
        jLabel5 = new javax.swing.JLabel();
        cboPhone = new com.bits.lib.dbswing.JBdbComboBox();
        cboEmail = new com.bits.lib.dbswing.JBdbComboBox();
        jLabel6 = new javax.swing.JLabel();
        chkReset = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        cboZipcode = new com.bits.lib.dbswing.JBdbComboBox();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle(org.openide.util.NbBundle.getMessage(FrmSalesmanImport.class, "FrmSalesmanImport.title")); // NOI18N

        jFormLabel1.setText(org.openide.util.NbBundle.getMessage(FrmSalesmanImport.class, "FrmSalesmanImport.jFormLabel1.text")); // NOI18N

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bits/bee/ui/myswing/images/icon/refresh.png"))); // NOI18N
        btnRefresh.setText(org.openide.util.NbBundle.getMessage(FrmSalesmanImport.class, "FrmSalesmanImport.btnRefresh.text")); // NOI18N
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnProcess.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bits/bee/ui/myswing/images/icon/leftarror.png"))); // NOI18N
        btnProcess.setText(org.openide.util.NbBundle.getMessage(FrmSalesmanImport.class, "FrmSalesmanImport.btnProcess.text")); // NOI18N
        btnProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessActionPerformed(evt);
            }
        });

        jLabel1.setText(org.openide.util.NbBundle.getMessage(FrmSalesmanImport.class, "FrmSalesmanImport.jLabel1.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(FrmSalesmanImport.class, "FrmSalesmanImport.jLabel2.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(FrmSalesmanImport.class, "FrmSalesmanImport.jLabel3.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(FrmSalesmanImport.class, "FrmSalesmanImport.jLabel4.text")); // NOI18N

        jLabel5.setText(org.openide.util.NbBundle.getMessage(FrmSalesmanImport.class, "FrmSalesmanImport.jLabel5.text")); // NOI18N

        jLabel6.setText(org.openide.util.NbBundle.getMessage(FrmSalesmanImport.class, "FrmSalesmanImport.jLabel6.text")); // NOI18N

        chkReset.setText(org.openide.util.NbBundle.getMessage(FrmSalesmanImport.class, "FrmSalesmanImport.chkReset.text")); // NOI18N
        chkReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkResetActionPerformed(evt);
            }
        });

        jLabel7.setText(org.openide.util.NbBundle.getMessage(FrmSalesmanImport.class, "FrmSalesmanImport.jLabel7.text")); // NOI18N

        jLabel8.setText(org.openide.util.NbBundle.getMessage(FrmSalesmanImport.class, "FrmSalesmanImport.jLabel8.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboSrepID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSrepName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboZipcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(chkReset))
                .addContainerGap(185, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboSrepID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboSrepName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cboZipcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(FrmSalesmanImport.class, "FrmSalesmanImport.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        txtLog.setColumns(20);
        txtLog.setRows(5);
        jScrollPane1.setViewportView(txtLog);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(FrmSalesmanImport.class, "FrmSalesmanImport.jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanelChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnProcess)
                        .addGap(32, 32, 32))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRefresh)
                        .addComponent(btnProcess))
                    .addComponent(jPanelChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jFormLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFormLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        refreshForm();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessActionPerformed
        try {
            txtLog.setText("");
            ScreenManager.setCursorThinking(this); //ubah cursor menjadi loading

            //cek apakah reset dicentang
            if (chkReset.isSelected()) {
                spReset.execute();
            }

            validateForm();
            readData();

            UIMgr.showMessageDialog("Data Berhasil Diproses\nSukses: " + succes + " Error: " + error);
            jTabbedPane1.setSelectedIndex(1);
        } catch (Exception ex) {
            UIMgr.showErrorDialog("error", ex);
        } finally {
            ScreenManager.setCursorDefault(this);
            txtLog.append("\nSucces: " + succes + ", Error: " + error);

            succes = 0;
            error = 0;
        }
    }//GEN-LAST:event_btnProcessActionPerformed

    private void chkResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkResetActionPerformed
        //unutk konfirmasi apakah benar direset atau tidak
        if (chkReset.isSelected() && (UIMgr.YesNo("Apakah anda yakin untuk mereset pegawai?") == 1)) { //0=yes, 1=no
            chkReset.setSelected(false);
        }
    }//GEN-LAST:event_chkResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProcess;
    private javax.swing.JButton btnRefresh;
    private com.bits.lib.dbswing.JBdbComboBox cboAlamat;
    private com.bits.lib.dbswing.JBdbComboBox cboCity;
    private com.bits.lib.dbswing.JBdbComboBox cboEmail;
    private com.bits.lib.dbswing.JBdbComboBox cboPhone;
    private com.bits.lib.dbswing.JBdbComboBox cboSrepID;
    private com.bits.lib.dbswing.JBdbComboBox cboSrepName;
    private com.bits.lib.dbswing.JBdbComboBox cboZipcode;
    private javax.swing.JCheckBox chkReset;
    private com.bits.bee.ui.myswing.JFormLabel jFormLabel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private com.bits.bee.ui.myswing.JPanelChooser jPanelChooser1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea txtLog;
    // End of variables declaration//GEN-END:variables

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equalsIgnoreCase("fileName")){
            readFile();
            
            btnProcess.setEnabled(true);
            btnRefresh.setEnabled(true);
            BUtil.setEnabledJTabbedPane(jTabbedPane1, true);
        }
    }
}
