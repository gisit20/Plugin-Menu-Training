/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bits.bee.plugintraining.ui;

import com.bits.bee.plugintraining.bl.SAdjTransTraining;
import com.bits.bee.plugintraining.bl.SaleTransTugas;
import com.bits.bee.plugintraining.bl.SaleTugas;
import com.bits.bee.plugintraining.ui.dlg.DlgSaleTugas;
import com.bits.bee.ui.UIMgr;
import com.bits.bee.ui.myswing.InternalFrameTrans;
import com.bits.lib.BUtil;
import com.bits.lib.dbswing.BdbState;
import com.bits.lib.security.BAuthMgr;
import com.borland.dx.dataset.DataSet;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.openide.util.Exceptions;

/**
 *
 * @author Sigit Sukarman
 */
public class FrmTransaksiPenjualan extends InternalFrameTrans implements PropertyChangeListener {

    private SaleTransTugas trans = new SaleTransTugas();
    private String OBJID = "PG-TR-TUGAS3-03";
    BdbState state = new BdbState();

    /**
     * Creates new form FrmTransaksiPenjualan
     */
    public FrmTransaksiPenjualan() {
        initComponents();
        initForm();
        initTable();
    }

    private void initForm() {
        jBToolbar1.setState(state);
        jBToolbar1.setObjid(OBJID);
        jBToolbar1.setAuthMgr(BAuthMgr.getDefault());

        state.addPropertyChangeListener("state", this);
        state.setState(BdbState.stNONE);
    }

    private void setEnabledPanel(boolean b) {
        BUtil.setEnabledPanel(jPanel1, b);
    }

    private void initTable() {
        DataSet ds = trans.getDataSetDetail();
//buat piker dalam table
//        SPikItem pikItem = new SPikItem();
        for (int i = 0; i < ds.getColumnCount(); i++) {
            ds.getColumn(i).setVisible(0);
        }

//        ds.getColumn("itemid").setVisible(1);
//        ds.getColumn("itemid").setItemEditor(new BCellEditor(pikItem));
//        
        UIMgr.setDataSetDetailTrans(ds);
        ds.getColumn("saledno").setCaption("No");
        ds.getColumn("saledno").setWidth(2);
        ds.getColumn("saledno").setVisible(1);
        ds.getColumn("whid").setWidth(3);
        ds.getColumn("itemdesc").setWidth(15);
        
        ds.getColumn("taxid").setVisible(0);
        ds.getColumn("discexp").setVisible(0);
        ds.getColumn("discamt").setVisible(0);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBToolbar1 = new com.bits.lib.dbswing.JBToolbar();
        jFormLabel1 = new com.bits.bee.ui.myswing.JFormLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSaleno = new com.bits.bee.ui.myswing.BTextIDField();
        jBDatePicker1 = new com.bits.lib.dbswing.JBDatePicker();
        jLabel4 = new javax.swing.JLabel();
        jCboCash1 = new com.bits.bee.ui.myswing.JCboCash();
        jLabel5 = new javax.swing.JLabel();
        jCurrency1 = new com.bits.bee.ui.myswing.JCurrency();
        pikCust1 = new com.bits.bee.ui.myswing.PikCust();
        jScrollPane1 = new javax.swing.JScrollPane();
        jBdbTable1 = new com.bits.lib.dbswing.JBdbTable();
        jBStatusbar1 = new com.bits.lib.dbswing.JBStatusbar();
        txtTotal = new com.borland.dbswing.JdbLabel();
        jLabel6 = new javax.swing.JLabel();

        setResizable(true);
        setTitle(org.openide.util.NbBundle.getMessage(FrmTransaksiPenjualan.class, "FrmTransaksiPenjualan.title")); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBToolbar1.setEnablePrint(false);
        jBToolbar1.setEnableRefresh(false);
        jBToolbar1.addJBToolbarListener(new com.bits.lib.dbswing.JBToolbarListener() {
            public void toolbarNewPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
                jBToolbar1ToolbarNewPerformed(evt);
            }
            public void toolbarOpenPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
                jBToolbar1ToolbarOpenPerformed(evt);
            }
            public void toolbarEditPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
                jBToolbar1ToolbarEditPerformed(evt);
            }
            public void toolbarSavePerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
                jBToolbar1ToolbarSavePerformed(evt);
            }
            public void toolbarCancelPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
                jBToolbar1ToolbarCancelPerformed(evt);
            }
            public void toolbarDeletePerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
                jBToolbar1ToolbarDeletePerformed(evt);
            }
            public void toolbarVoidPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
                jBToolbar1ToolbarVoidPerformed(evt);
            }
            public void toolbarPrintPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
            }
            public void toolbarRefreshPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
            }
        });
        getContentPane().add(jBToolbar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 733, 25));

        jFormLabel1.setText(org.openide.util.NbBundle.getMessage(FrmTransaksiPenjualan.class, "FrmTransaksiPenjualan.jFormLabel1.text")); // NOI18N
        getContentPane().add(jFormLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(566, 36, -1, -1));

        jLabel1.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 11)); // NOI18N
        jLabel1.setText(org.openide.util.NbBundle.getMessage(FrmTransaksiPenjualan.class, "FrmTransaksiPenjualan.jLabel1.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 11)); // NOI18N
        jLabel2.setText(org.openide.util.NbBundle.getMessage(FrmTransaksiPenjualan.class, "FrmTransaksiPenjualan.jLabel2.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 11)); // NOI18N
        jLabel3.setText(org.openide.util.NbBundle.getMessage(FrmTransaksiPenjualan.class, "FrmTransaksiPenjualan.jLabel3.text")); // NOI18N

        txtSaleno.setText(org.openide.util.NbBundle.getMessage(FrmTransaksiPenjualan.class, "FrmTransaksiPenjualan.txtSaleno.text")); // NOI18N
        txtSaleno.setColumnName(org.openide.util.NbBundle.getMessage(FrmTransaksiPenjualan.class, "FrmTransaksiPenjualan.txtSaleno.columnName")); // NOI18N
        txtSaleno.setDataSet(trans.getDataSetMaster());

        jBDatePicker1.setColumnName(org.openide.util.NbBundle.getMessage(FrmTransaksiPenjualan.class, "FrmTransaksiPenjualan.jBDatePicker1.columnName")); // NOI18N
        jBDatePicker1.setDataSet(trans.getDataSetMaster());

        jLabel4.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 11)); // NOI18N
        jLabel4.setText(org.openide.util.NbBundle.getMessage(FrmTransaksiPenjualan.class, "FrmTransaksiPenjualan.jLabel4.text")); // NOI18N

        jCboCash1.setColumnName(org.openide.util.NbBundle.getMessage(FrmTransaksiPenjualan.class, "FrmTransaksiPenjualan.jCboCash1.columnName")); // NOI18N
        jCboCash1.setDataSet(trans.getDataSetMaster());

        jLabel5.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 11)); // NOI18N
        jLabel5.setText(org.openide.util.NbBundle.getMessage(FrmTransaksiPenjualan.class, "FrmTransaksiPenjualan.jLabel5.text")); // NOI18N

        jCurrency1.setDataSet(trans.getDataSetMaster());

        pikCust1.setColumnName(org.openide.util.NbBundle.getMessage(FrmTransaksiPenjualan.class, "FrmTransaksiPenjualan.pikCust1.columnName")); // NOI18N
        pikCust1.setDataSet(trans.getDataSetMaster());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jBDatePicker1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSaleno, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCurrency1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCboCash1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(pikCust1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(174, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSaleno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jCboCash1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jBDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jCurrency1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(pikCust1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jBDatePicker1, jLabel2, jLabel3});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jCboCash1, jCurrency1, jLabel4, jLabel5});

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 57, 723, -1));

        jBdbTable1.setDataSet(trans.getDataSetDetail());
        jBdbTable1.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 11)); // NOI18N
        jBdbTable1.setOpaque(false);
        jScrollPane1.setViewportView(jBdbTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 146, 713, 180));
        getContentPane().add(jBStatusbar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 429, 733, -1));

        txtTotal.setText(org.openide.util.NbBundle.getMessage(FrmTransaksiPenjualan.class, "FrmTransaksiPenjualan.txtTotal.text")); // NOI18N
        txtTotal.setColumnName(org.openide.util.NbBundle.getMessage(FrmTransaksiPenjualan.class, "FrmTransaksiPenjualan.txtTotal.columnName")); // NOI18N
        txtTotal.setDataSet(trans.getDataSetMaster());
        txtTotal.setFont(new java.awt.Font("Bitstream Vera Sans", 1, 11)); // NOI18N
        getContentPane().add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(624, 409, 89, 14));

        jLabel6.setFont(new java.awt.Font("Bitstream Vera Sans", 1, 11)); // NOI18N
        jLabel6.setText(org.openide.util.NbBundle.getMessage(FrmTransaksiPenjualan.class, "FrmTransaksiPenjualan.jLabel6.text")); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(577, 409, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBToolbar1ToolbarCancelPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {//GEN-FIRST:event_jBToolbar1ToolbarCancelPerformed
        doCancel();
    }//GEN-LAST:event_jBToolbar1ToolbarCancelPerformed

    private void jBToolbar1ToolbarDeletePerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {//GEN-FIRST:event_jBToolbar1ToolbarDeletePerformed
        doDelete();
    }//GEN-LAST:event_jBToolbar1ToolbarDeletePerformed

    private void jBToolbar1ToolbarEditPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {//GEN-FIRST:event_jBToolbar1ToolbarEditPerformed
        doEdit();
    }//GEN-LAST:event_jBToolbar1ToolbarEditPerformed

    private void jBToolbar1ToolbarNewPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {//GEN-FIRST:event_jBToolbar1ToolbarNewPerformed
        doNew();
    }//GEN-LAST:event_jBToolbar1ToolbarNewPerformed

    private void jBToolbar1ToolbarOpenPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {//GEN-FIRST:event_jBToolbar1ToolbarOpenPerformed
        doOpen();
    }//GEN-LAST:event_jBToolbar1ToolbarOpenPerformed

    private void jBToolbar1ToolbarSavePerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {//GEN-FIRST:event_jBToolbar1ToolbarSavePerformed
        doSave();
    }//GEN-LAST:event_jBToolbar1ToolbarSavePerformed

    private void jBToolbar1ToolbarVoidPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {//GEN-FIRST:event_jBToolbar1ToolbarVoidPerformed
        doVoid();
    }//GEN-LAST:event_jBToolbar1ToolbarVoidPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bits.lib.dbswing.JBDatePicker jBDatePicker1;
    private com.bits.lib.dbswing.JBStatusbar jBStatusbar1;
    private com.bits.lib.dbswing.JBToolbar jBToolbar1;
    private com.bits.lib.dbswing.JBdbTable jBdbTable1;
    private com.bits.bee.ui.myswing.JCboCash jCboCash1;
    private com.bits.bee.ui.myswing.JCurrency jCurrency1;
    private com.bits.bee.ui.myswing.JFormLabel jFormLabel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.bits.bee.ui.myswing.PikCust pikCust1;
    private com.bits.bee.ui.myswing.BTextIDField txtSaleno;
    private com.borland.dbswing.JdbLabel txtTotal;
    // End of variables declaration//GEN-END:variables

    @Override
    public void doNew() {
        trans.emptyAllRows();
        trans.New();
        state.setState(BdbState.stNEW);
    }

    @Override
    public void doOpen() {
        DlgSaleTugas dlg = DlgSaleTugas.getInstance();
        dlg.setVisible(true);
        String id = dlg.getSelectedID();
        if (id != null) {
            try {
                trans.LoadID(id);
                state.setState(BdbState.stEDIT);
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }

    @Override
    public void doEdit() {
        try {
            trans.LoadID(txtSaleno.getText());
            state.setState(BdbState.stEDIT);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    public void doEdit(String string) {
        state.setState(BdbState.stEDIT);
    }

    @Override
    public void doSave() {
        try {
            trans.Save();
            UIMgr.showMessageDialog("Data berhasil disimpan!");
            state.setState(BdbState.stNONE);
        } catch (Exception ex) {
            UIMgr.showErrorDialog("Gagal simpan data", ex);
            Exceptions.printStackTrace(ex);
        }
        state.setState(BdbState.stNONE);
    }

    @Override
    public void doCancel() {
        trans.Cancel();
        state.setState(BdbState.stNONE);
    }

    @Override
    public void doDelete() {
        state.setState(BdbState.stNONE);
    }

    @Override
    public void doVoid() {
        try {
            trans.Void();
            UIMgr.showMessageDialog("Data berhasil divoid");
            state.setState(BdbState.stNONE);
        } catch (Exception ex) {
            UIMgr.showErrorDialog("Gagal void data", ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void doPrint() {
    }

    @Override
    public void doRefresh() {
    }

    @Override
    public void setTransState(int i) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {        
        if (evt.getPropertyName().equalsIgnoreCase("state")){
            if(state.getState() == BdbState.stNEW 
                    || state.getState() == BdbState.stEDIT){
                setEnabledPanel(true);
            } else {
                setEnabledPanel(false);
            }
            jBToolbar1.setEnableDelete(false);                
        }
    }
}
