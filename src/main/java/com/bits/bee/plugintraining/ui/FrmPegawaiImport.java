/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bits.bee.plugintraining.ui;

import com.bits.bee.bl.Emp;
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
import org.openide.util.Exceptions;

/**
 *
 * @author Sigit Sukarman
 */
public class FrmPegawaiImport extends javax.swing.JInternalFrame implements PropertyChangeListener {
    
    private final int OPTIONAL = -1;
    
    private int empid;
    private int empname;
    private int addr;
    private int city;
    private int phone;
    private int email;
    
    XLSReader reader;
//buat array list untuk menyimpan index jbcombobox-nya
    ArrayList<JBdbComboBox> alCombo = new ArrayList();
    
    ArrayList<String> columnModel;
    String[] value;
    
    Emp emp;
    
    private int succes=0;
    private int error=0;
    //init spReset
    BProcSimple spReset = new BProcSimple(BDM.getDefault(), "spEmpReset", "empid");
    
    public FrmPegawaiImport() {
        initComponents();
        reader = new XLSReader();

//daftarkan semua combo”nya
        alCombo.add(cboEmpID);
        alCombo.add(cboEmpName);
        alCombo.add(cboAlamat);
        alCombo.add(cboCity);
        alCombo.add(cboPhone);
        alCombo.add(cboEmail);

//disable button
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
        Component[] compPanel = jPanel3.getComponents();
        
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
            throw new Exception("ada kolom terpilih yang sama ");
        }
        if (cboEmpID.getSelectedIndex() < 0) {
            throw new Exception("Kolom Kode Pegawai harus diisi !");
        }
        if (cboEmpName.getSelectedIndex() < 0) {
            throw new Exception("Kolom Nama Pegawai harus diisi !");
        }
    }

    //untuk cek apakah ada data yg sama
    private boolean cekCombo() {
        boolean duplikat = false;
        
        empid = cboEmpID.getSelectedIndex();
        empname = cboEmpName.getSelectedIndex();
        addr = cboAlamat.getSelectedIndex();
        city = cboCity.getSelectedIndex();
        phone = cboPhone.getSelectedIndex();
        email = cboEmail.getSelectedIndex();
        
        int[] data = {empid, empname, addr, city, phone, email};
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
        empid = cboEmpID.getSelectedIndex();
        empname = cboEmpName.getSelectedIndex();
        addr = cboAlamat.getSelectedIndex();
        city = cboCity.getSelectedIndex();
        phone = cboPhone.getSelectedIndex();
        email = cboEmail.getSelectedIndex();
        
        ArrayList<ArrayList<String>> data = reader.getDataByIndex();
        
        for (int i = 1; i < data.size(); i++) {
            //nge get datarow
            ArrayList<String> dataRow = data.get(i);
            
            int dataRowSize = dataRow.size();
            
            emp = BTableProvider.createTable(Emp.class);
            emp.New();
            
            emp.getDataSet().setString("empid", dataRow.get(empid));
            emp.getDataSet().setString("empname", dataRow.get(empname));
            
            if (addr != OPTIONAL && XLSUtils.isNotNullOrEmpty(dataRow.get(addr)) && dataRowSize >= addr) {
                emp.getDataSet().setString("addr", dataRow.get(addr));
            }
            if (city != OPTIONAL && XLSUtils.isNotNullOrEmpty(dataRow.get(city)) && dataRowSize >= city) {
                emp.getDataSet().setString("cityid", dataRow.get(city));
            }
            if (phone != OPTIONAL && XLSUtils.isNotNullOrEmpty(dataRow.get(phone)) && dataRowSize >= phone) {
                emp.getDataSet().setString("phone", dataRow.get(phone));
            }
            if (email != OPTIONAL && XLSUtils.isNotNullOrEmpty(dataRow.get(email)) && dataRowSize >= email) {
                emp.getDataSet().setString("email", dataRow.get(email));
            }
            
            try {
                emp.saveChanges();
                txtLog.append("Row ke-" + i + "Berhasil diimport.\n");
                succes++;
            } catch (Exception ex) {
                txtLog.append("Row ke-" + i + "gagal di import.\nPesan error: " + BHelp.getExceptionDetail(ex));
                error++;
            }
            
//            txtLog.append("Data ke-"+dataRow.get(i));
            //            txtLog.append("Kode Pegawai: "+dataRow.get(empid));
            //            txtLog.append("Nama Pegawai: "+dataRow.get(empname));
            //            txtLog.append("Alamat: "+dataRow.get(addr));
            //            txtLog.append("Kota: "+dataRow.get(city));
            //            txtLog.append("Phone: "+dataRow.get(phone));
            //            txtLog.append("Email: "+dataRow.get(email));            
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboEmpID = new com.bits.lib.dbswing.JBdbComboBox();
        cboEmpName = new com.bits.lib.dbswing.JBdbComboBox();
        cboAlamat = new com.bits.lib.dbswing.JBdbComboBox();
        cboCity = new com.bits.lib.dbswing.JBdbComboBox();
        cboPhone = new com.bits.lib.dbswing.JBdbComboBox();
        cboEmail = new com.bits.lib.dbswing.JBdbComboBox();
        jLabel7 = new javax.swing.JLabel();
        chkReset = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        jPanelChooser1 = new com.bits.bee.ui.myswing.JPanelChooser();
        btnRefresh = new javax.swing.JButton();
        btnProcess = new javax.swing.JButton();

        setTitle(org.openide.util.NbBundle.getMessage(FrmPegawaiImport.class, "FrmPegawaiImport.title")); // NOI18N

        jFormLabel1.setText(org.openide.util.NbBundle.getMessage(FrmPegawaiImport.class, "FrmPegawaiImport.jFormLabel1.text")); // NOI18N

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setText(org.openide.util.NbBundle.getMessage(FrmPegawaiImport.class, "FrmPegawaiImport.jLabel1.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(FrmPegawaiImport.class, "FrmPegawaiImport.jLabel2.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(FrmPegawaiImport.class, "FrmPegawaiImport.jLabel3.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(FrmPegawaiImport.class, "FrmPegawaiImport.jLabel4.text")); // NOI18N

        jLabel5.setText(org.openide.util.NbBundle.getMessage(FrmPegawaiImport.class, "FrmPegawaiImport.jLabel5.text")); // NOI18N

        jLabel6.setText(org.openide.util.NbBundle.getMessage(FrmPegawaiImport.class, "FrmPegawaiImport.jLabel6.text")); // NOI18N

        jLabel7.setText(org.openide.util.NbBundle.getMessage(FrmPegawaiImport.class, "FrmPegawaiImport.jLabel7.text")); // NOI18N

        chkReset.setText(org.openide.util.NbBundle.getMessage(FrmPegawaiImport.class, "FrmPegawaiImport.chkReset.text")); // NOI18N
        chkReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cboEmpID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cboEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chkReset))
                    .addComponent(cboAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(165, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboEmpID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboEmpID, jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6});

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(FrmPegawaiImport.class, "FrmPegawaiImport.jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        txtLog.setColumns(20);
        txtLog.setRows(5);
        jScrollPane1.setViewportView(txtLog);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(FrmPegawaiImport.class, "FrmPegawaiImport.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bits/bee/ui/myswing/images/icon/refresh.png"))); // NOI18N
        btnRefresh.setText(org.openide.util.NbBundle.getMessage(FrmPegawaiImport.class, "FrmPegawaiImport.btnRefresh.text")); // NOI18N
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnProcess.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bits/bee/ui/myswing/images/icon/leftarror.png"))); // NOI18N
        btnProcess.setText(org.openide.util.NbBundle.getMessage(FrmPegawaiImport.class, "FrmPegawaiImport.btnProcess.text")); // NOI18N
        btnProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanelChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(btnRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnProcess)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRefresh)
                        .addComponent(btnProcess)))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnRefresh, jPanelChooser1});

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
            ScreenManager.setCursorThinking(this); //ubah cursor menjadi loading
            
            //cek apakah reset dicentang
            if(chkReset.isSelected()){
                spReset.execute();
            }
            
            validateForm();
            readData();
            
            UIMgr.showMessageDialog("Data Berhasil Diproses\nSukses: "+succes+" Error: "+error);
            jTabbedPane1.setSelectedIndex(1);
        } catch (Exception ex) {
            UIMgr.showErrorDialog("error", ex);
        } finally{
            ScreenManager.setCursorDefault(this);
            txtLog.append("Succes: "+succes+ ", Error: "+error);
            
            succes=0;
            error=0;
        }
    }//GEN-LAST:event_btnProcessActionPerformed

    private void chkResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkResetActionPerformed
        //unutk konfirmasi apakah benar direset atau tidak
        if(chkReset.isSelected() && (UIMgr.YesNo("Apakah anda yakin untuk mereset pegawai?")==1)){ //0=yes, 1=no
            chkReset.setSelected(false);
        }
    }//GEN-LAST:event_chkResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProcess;
    private javax.swing.JButton btnRefresh;
    private com.bits.lib.dbswing.JBdbComboBox cboAlamat;
    private com.bits.lib.dbswing.JBdbComboBox cboCity;
    private com.bits.lib.dbswing.JBdbComboBox cboEmail;
    private com.bits.lib.dbswing.JBdbComboBox cboEmpID;
    private com.bits.lib.dbswing.JBdbComboBox cboEmpName;
    private com.bits.lib.dbswing.JBdbComboBox cboPhone;
    private javax.swing.JCheckBox chkReset;
    private com.bits.bee.ui.myswing.JFormLabel jFormLabel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
        if (evt.getPropertyName().equalsIgnoreCase("fileName")) {
            readFile();
            
            btnProcess.setEnabled(true);
            btnRefresh.setEnabled(true);
            BUtil.setEnabledJTabbedPane(jTabbedPane1, true);
        }
    }
}
