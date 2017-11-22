/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bits.bee.plugintraining.ui.dlg;

import com.bits.bee.ui.DlgFindKode;
import com.bits.bee.ui.ScreenManager;
import com.bits.lib.dbswing.JBDialog;
import com.bits.lib.dx.BDM;
import com.bits.lib.dx.JBSQL;
import com.borland.dx.dataset.DataSetView;
import com.borland.dx.sql.dataset.QueryDataSet;
import com.borland.dx.sql.dataset.QueryDescriptor;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import static org.openide.util.Enumerations.filter;

/**
 *
 * @author Sigit Sukarman
 */
public class DlgPegawai extends JBDialog {

    private static DlgPegawai singleton = null;
    private QueryDataSet qds = new QueryDataSet();
    private DataSetView dsv = new DataSetView();
    private String nama = null;
    private String kode = null;

    KeyStroke key_F2 = KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0, false);

    /**
     * Creates new form DlgPegawai
     */
    public DlgPegawai() {
        super(ScreenManager.getParent(), "Daftar Pegawai");
        initComponents();
        initKeyStroke();

        refresh();
    }

    public static synchronized DlgPegawai getInstance() {
        if (singleton == null) {
            singleton = new DlgPegawai();
        }
        return singleton;
    }

    private void initKeyStroke() {
        Action actF2 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doF2();
            }
        };

        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key_F2, "f2");
        this.getRootPane().getActionMap().put("f2", actF2);
    }

    private void refresh() {
        StringBuffer sql = new StringBuffer();
        StringBuffer filter = new StringBuffer();
        sql.append("Select empid, empname FROM emp");

        if (kode != null) {
            JBSQL.ANDFilter(filter, JBSQL.filterUpperLike("empid", kode));
        }

        if (nama != null) {
            JBSQL.ANDFilter(filter, JBSQL.filterUpperLike("empname", nama));
        }

        JBSQL.setWHERE(sql, filter);
        JBSQL.setORDERBY(sql, "empname, empid");

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
        
        kode = null;
        nama = null;

    }

    private void initTable() {
        qds.getColumn("empid").setCaption("Kode");
        qds.getColumn("empid").setWidth(6);
        qds.getColumn("empname").setCaption("Nama");
        qds.getColumn("empname").setWidth(12);
    }

    @Override
    protected void OK() {
        setSelectedID(dsv.getString("empid"));
        super.OK();
    }

    public void setVisible(boolean visible) {
        if (visible) {
            refresh();
        }
        super.setVisible(visible);
    }

    @Override
    protected void f1Action() {
        doF1();
//        nama = JOptionPane.showInputDialog(this, "Cara Kode Pegawai: ");
//        if(nama !=null){
//            refresh();
//        }
//        nama =null;        
    }

    @Override
    protected void f5Action() {
        doF5();
    }

    private void doF1() {
        DlgFindKode dlgFindName = DlgFindKode.getInstance();
        dlgFindName.setTitle("Cari Nama");
        dlgFindName.setTxtLabel("Cari Nama Pegawai: ");
        dlgFindName.setVisible(true);

        String retval = dlgFindName.getSelectedID();
        if (retval != null) {
            nama = retval;
            refresh();
            //reset dialog
            dlgFindName.setClear();
        }
    }

    private void doF2() {
        DlgFindKode dlgFindKode = DlgFindKode.getInstance();
        dlgFindKode.setTitle("Cari Kode");
        dlgFindKode.setTxtLabel("Cari Kode Pegawai: ");
        dlgFindKode.setVisible(true);

        //filter pencarian
        String retval = dlgFindKode.getSelectedID();
        if (retval != null) {
            kode = retval;
            refresh();
            //reset dialog
            dlgFindKode.setClear();
        }

    }

    private void doF5() {        
        refresh();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBToolbarDialog1 = new com.bits.lib.dbswing.JBToolbarDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jBdbTable1 = new com.bits.lib.dbswing.JBdbTable();
        jBStatusbarDialog1 = new com.bits.lib.dbswing.JBStatusbarDialog();
        jPanel1 = new javax.swing.JPanel();
        btnOK1 = new com.bits.bee.ui.myswing.BtnOK();
        btnCancel1 = new com.bits.bee.ui.myswing.BtnCancel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jBToolbarDialog1.addJBToolbarListener(new com.bits.lib.dbswing.JBToolbarListener() {
            public void toolbarNewPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
            }
            public void toolbarOpenPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
            }
            public void toolbarEditPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
            }
            public void toolbarSavePerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
                jBToolbarDialog1ToolbarSavePerformed(evt);
            }
            public void toolbarCancelPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
            }
            public void toolbarDeletePerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
            }
            public void toolbarVoidPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
            }
            public void toolbarPrintPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
            }
            public void toolbarRefreshPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
                jBToolbarDialog1ToolbarRefreshPerformed(evt);
            }
        });

        jBdbTable1.setDataSet(dsv);
        jBdbTable1.setEditable(false);
        jBdbTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBdbTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jBdbTable1);

        btnOK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOK1ActionPerformed(evt);
            }
        });

        btnCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnOK1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOK1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBStatusbarDialog1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jBToolbarDialog1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jBToolbarDialog1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBStatusbarDialog1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBToolbarDialog1ToolbarRefreshPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {//GEN-FIRST:event_jBToolbarDialog1ToolbarRefreshPerformed
        refresh();
    }//GEN-LAST:event_jBToolbarDialog1ToolbarRefreshPerformed

    private void jBToolbarDialog1ToolbarSavePerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {//GEN-FIRST:event_jBToolbarDialog1ToolbarSavePerformed
        OK();
    }//GEN-LAST:event_jBToolbarDialog1ToolbarSavePerformed

    private void jBdbTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBdbTable1MouseClicked
        if (evt.getClickCount() == 2) {
            OK();
        }
    }//GEN-LAST:event_jBdbTable1MouseClicked

    private void btnOK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOK1ActionPerformed
        OK();
    }//GEN-LAST:event_btnOK1ActionPerformed

    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel1ActionPerformed
        Cancel();
    }//GEN-LAST:event_btnCancel1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bits.bee.ui.myswing.BtnCancel btnCancel1;
    private com.bits.bee.ui.myswing.BtnOK btnOK1;
    private com.bits.lib.dbswing.JBStatusbarDialog jBStatusbarDialog1;
    private com.bits.lib.dbswing.JBToolbarDialog jBToolbarDialog1;
    private com.bits.lib.dbswing.JBdbTable jBdbTable1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
