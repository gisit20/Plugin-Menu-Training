/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bits.bee.plugintraining.ui.dlg;

import com.bits.bee.ui.DlgFindKode;
import com.bits.bee.ui.ScreenManager;
import com.bits.bee.ui.UIMgr;
import com.bits.bee.ui.myswing.JCboBranch;
import com.bits.lib.dbswing.JBDialog;
import com.bits.lib.dx.BDM;
import com.bits.lib.dx.JBSQL;
import com.borland.dx.dataset.DataSetView;
import com.borland.dx.sql.dataset.QueryDataSet;
import com.borland.dx.sql.dataset.QueryDescriptor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import static java.time.zone.ZoneRulesProvider.refresh;

/**
 *
 * @author Sigit Sukarman
 */
public class DlgSAdjTraining extends JBDialog implements PropertyChangeListener {

    private static DlgSAdjTraining dlg = null;
    private QueryDataSet qds = new QueryDataSet();
    private DataSetView dsv = new DataSetView();
    private String kode = null;

    /**
     * Creates new form DlgTransaksiStock
     */
    public DlgSAdjTraining() {
        super(ScreenManager.getParent(), "Dialog Stock");
        initComponents();
        UIMgr.setPeriode(jBOSPeriode1, 0);
    }

    public static synchronized DlgSAdjTraining getInstance() {
        if (dlg == null) {
            dlg = new DlgSAdjTraining();
        }
        return dlg;
    }

    private void initTable() {
        qds.getColumn("sadjno").setCaption("Nomor");
        qds.getColumn("sadjno").setWidth(10);
        qds.getColumn("sadjdate").setCaption("Tanggal");
        qds.getColumn("sadjdate").setWidth(8);
        qds.getColumn("empname").setCaption("Pegawai");
        qds.getColumn("empname").setWidth(8);
        qds.getColumn("branchname").setCaption("Cabang");
        qds.getColumn("branchname").setWidth(8);
    }

    private void refresh() {
        StringBuffer sql = new StringBuffer();
        StringBuffer filter = new StringBuffer();
        sql.append("Select sadjno, sadjdate, emp.empname, br.branchname FROM sadj "
                + "LEFT JOIN emp On emp.empid=sadj.empid "
                + "LEFT JOIN branch br On br.branchid=sadj.branchid ");

        JBSQL.ANDFilterPeriode(filter, "sadjdate", jBOSPeriode1);
        JBSQL.ANDFilterPicker(filter, "sadj.empid", pikPegawai1);
        JBSQL.ANDFilterCombo(filter, "sadj.branchid", jCboBranch1);
        if (kode != null) {
            JBSQL.ANDFilter(filter, JBSQL.filterUpperLike("sadjno", kode));
        }

        JBSQL.setWHERE(sql, filter);
        JBSQL.setORDERBY(sql, "sadjno, sadjdate");

        if (qds.isOpen()) {
            qds.close();
        }
        qds.setQuery(new QueryDescriptor(BDM.getDefault().getDatabase(), sql.toString()));
        qds.open();

        initTable();
        if (dsv.isOpen()) {
            dsv.close();
        }
        dsv.setStorageDataSet(qds.getStorageDataSet());
        dsv.open();
    }

    @Override
    protected void OK() {
        setSelectedID(dsv.getString("sadjno"));
        super.OK();
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            refresh();
        }
        super.setVisible(visible);
    }

    @Override
    protected void f1Action() {
        DlgFindKode dlg = DlgFindKode.getInstance();
        dlg.setTitle("Cari Nomor Transaksi");
        dlg.setTxtLabel("Cari Nomor");
        dlg.setVisible(true);
        String id = dlg.getSelectedID();
        if (id != null) {
            kode = id;
            refresh();
        }
        kode = null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBToolbarDialog1 = new com.bits.lib.dbswing.JBToolbarDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jBdbTable1 = new com.bits.lib.dbswing.JBdbTable();
        jBStatusbarDialog1 = new com.bits.lib.dbswing.JBStatusbarDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jBOSPeriode1 = new com.bits.bee.ui.myswing.JBOSPeriode();
        pikPegawai1 = new com.bits.bee.plugintraining.ui.component.PikPegawai();
        jLabel3 = new javax.swing.JLabel();
        jCboBranch1 = new com.bits.bee.ui.myswing.JCboBranch();
        jPanel2 = new javax.swing.JPanel();
        btnCancel1 = new com.bits.bee.ui.myswing.BtnCancel();
        btnOK1 = new com.bits.bee.ui.myswing.BtnOK();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBToolbarDialog1.setEnableNew(false);
        jBToolbarDialog1.addJBToolbarListener(new com.bits.lib.dbswing.JBToolbarListener() {
            public void toolbarNewPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
            }
            public void toolbarEditPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
            }
            public void toolbarRefreshPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
                jBToolbarDialog1ToolbarRefreshPerformed(evt);
            }
            public void toolbarSavePerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
            }
            public void toolbarOpenPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
            }
            public void toolbarCancelPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
            }
            public void toolbarDeletePerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
            }
            public void toolbarVoidPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
            }
            public void toolbarPrintPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
            }
        });
        getContentPane().add(jBToolbarDialog1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 25));

        jBdbTable1.setCellSelectionEnabled(false);
        jBdbTable1.setDataSet(dsv);
        jBdbTable1.setEditable(false);
        jBdbTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBdbTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jBdbTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 460, 180));
        getContentPane().add(jBStatusbarDialog1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 480, -1));

        jLabel2.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 11)); // NOI18N
        jLabel2.setText(org.openide.util.NbBundle.getMessage(DlgSAdjTraining.class, "DlgSAdjTraining.jLabel2.text")); // NOI18N

        jLabel1.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 11)); // NOI18N
        jLabel1.setText(org.openide.util.NbBundle.getMessage(DlgSAdjTraining.class, "DlgSAdjTraining.jLabel1.text")); // NOI18N

        jBOSPeriode1.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 11)); // NOI18N

        pikPegawai1.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 11)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 11)); // NOI18N
        jLabel3.setText(org.openide.util.NbBundle.getMessage(DlgSAdjTraining.class, "DlgSAdjTraining.jLabel3.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pikPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBOSPeriode1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCboBranch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(143, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jBOSPeriode1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pikPegawai1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jCboBranch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, pikPegawai1});

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 36, 470, -1));

        btnCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel1ActionPerformed(evt);
            }
        });

        btnOK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOK1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(248, Short.MAX_VALUE)
                .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnOK1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOK1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 460, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOK1ActionPerformed
        OK();
    }//GEN-LAST:event_btnOK1ActionPerformed

    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel1ActionPerformed
        Cancel();
    }//GEN-LAST:event_btnCancel1ActionPerformed

    private void jBdbTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBdbTable1MouseClicked
        if (evt.getClickCount() == 2) {
            OK();
        }
    }//GEN-LAST:event_jBdbTable1MouseClicked

    private void jBToolbarDialog1ToolbarRefreshPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {//GEN-FIRST:event_jBToolbarDialog1ToolbarRefreshPerformed
        refresh();
    }//GEN-LAST:event_jBToolbarDialog1ToolbarRefreshPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bits.bee.ui.myswing.BtnCancel btnCancel1;
    private com.bits.bee.ui.myswing.BtnOK btnOK1;
    private com.bits.bee.ui.myswing.JBOSPeriode jBOSPeriode1;
    private com.bits.lib.dbswing.JBStatusbarDialog jBStatusbarDialog1;
    private com.bits.lib.dbswing.JBToolbarDialog jBToolbarDialog1;
    private com.bits.lib.dbswing.JBdbTable jBdbTable1;
    private com.bits.bee.ui.myswing.JCboBranch jCboBranch1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.bits.bee.plugintraining.ui.component.PikPegawai pikPegawai1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
