/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bits.bee.plugintraining.ui.dlg;

import com.bits.bee.plugintraining.ui.FrmSalesman2;
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

/**
 *
 * @author Sigit Sukarman
 */
public class DlgSalesman extends JBDialog {
    
    private static DlgSalesman singleton = null;
    private QueryDataSet qds = new QueryDataSet();
    private DataSetView dsv =new DataSetView();
    private String nama = null;
    private String kode = null;
    private String status = null;
    FrmSalesman2 srep = null;
    
    KeyStroke key_F2 =KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0, false);
    
    
    /**
     * Creates new form DlgSalesman
     */
    public DlgSalesman() {
        super(ScreenManager.getParent(), "Daftar Salesman");
        initComponents();
        initKeyStroke();
        
        refresh();
    }
        
     public static synchronized DlgSalesman getInstance() {
        if (singleton == null){
            singleton = new DlgSalesman();
        }
        return singleton;
     }
     
     private void initKeyStroke(){
         Action actF2 = new AbstractAction(){
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
        sql.append("Select srepid, srepname FROM srep");

        if (kode != null) {
            JBSQL.ANDFilter(filter, JBSQL.filterUpperLike("srepid", kode));
        }

        if (nama != null) {
            JBSQL.ANDFilter(filter, JBSQL.filterUpperLike("srepname", nama));
        }
        
        JBSQL.ANDFilterCombo(filter, "active", jCboAktif1);
//        if (jCboAktif1.getKeyValue().equalsIgnoreCase(selected)== true){
//            JBSQL.ANDFilter(filter, JBSQL.filterUpperLike("Aktif", status) );
//        } else {
//            JBSQL.ANDFilter(filter, JBSQL.filterUpperLike("Tidak Aktif", status) );
//        }

        JBSQL.setWHERE(sql, filter);
        JBSQL.setORDERBY(sql, "srepname, srepid");

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
        qds.getColumn("srepid").setCaption("Kode");
        qds.getColumn("srepid").setWidth(6);
        qds.getColumn("srepname").setCaption("Nama");
        qds.getColumn("srepname").setWidth(12);
    }

    @Override
    protected void OK() {
        setSelectedID(dsv.getString("srepid"));
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
    }

    @Override
    protected void f5Action() {
        doF5();
    }
    
    private void doF1() {
        DlgFindKode dlgFindName = DlgFindKode.getInstance();
        dlgFindName.setTitle("Cari Nama");
        dlgFindName.setTxtLabel("Cari Nama Salesman: ");
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
        dlgFindKode.setTxtLabel("Cari Kode Salesman: ");
        dlgFindKode.setVisible(true);

        //filter pencarian
        String retval = dlgFindKode.getSelectedID();
        if (retval != null) {
            kode = retval;
            refresh();
            dlgFindKode.setClear();
        }
    }
    
    private void doF5() {        
        refresh();
    }
       
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCboAktif1 = new com.bits.bee.ui.myswing.JCboAktif();
        jBToolbarDialog1 = new com.bits.lib.dbswing.JBToolbarDialog();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jBdbTable1 = new com.bits.lib.dbswing.JBdbTable();
        btnOK1 = new com.bits.bee.ui.myswing.BtnOK();
        btnCancel1 = new com.bits.bee.ui.myswing.BtnCancel();
        jBStatusbarDialog1 = new com.bits.lib.dbswing.JBStatusbarDialog();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCboAktif1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCboAktif1ActionPerformed(evt);
            }
        });
        getContentPane().add(jCboAktif1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 80, -1));

        jBToolbarDialog1.addJBToolbarListener(new com.bits.lib.dbswing.JBToolbarListener() {
            public void toolbarNewPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
                jBToolbarDialog1ToolbarNewPerformed(evt);
            }
            public void toolbarOpenPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
            }
            public void toolbarEditPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
            }
            public void toolbarSavePerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {
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
        getContentPane().add(jBToolbarDialog1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 438, 26));

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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOK1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOK1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 430, 240));
        getContentPane().add(jBStatusbarDialog1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 450, -1));

        jLabel1.setText(org.openide.util.NbBundle.getMessage(DlgSalesman.class, "DlgSalesman.jLabel1.text")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOK1ActionPerformed
        OK();
    }//GEN-LAST:event_btnOK1ActionPerformed

    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel1ActionPerformed
        Cancel();
    }//GEN-LAST:event_btnCancel1ActionPerformed

    private void jBdbTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBdbTable1MouseClicked
        if (evt.getClickCount()==2){
            OK();
        }
    }//GEN-LAST:event_jBdbTable1MouseClicked

    private void jBToolbarDialog1ToolbarRefreshPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {//GEN-FIRST:event_jBToolbarDialog1ToolbarRefreshPerformed
        refresh();
    }//GEN-LAST:event_jBToolbarDialog1ToolbarRefreshPerformed

    private void jBToolbarDialog1ToolbarNewPerformed(com.bits.lib.dbswing.JBToolbarEvent evt) {//GEN-FIRST:event_jBToolbarDialog1ToolbarNewPerformed
        srep.doNew();
    }//GEN-LAST:event_jBToolbarDialog1ToolbarNewPerformed

    private void jCboAktif1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCboAktif1ActionPerformed
          refresh();        
    }//GEN-LAST:event_jCboAktif1ActionPerformed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bits.bee.ui.myswing.BtnCancel btnCancel1;
    private com.bits.bee.ui.myswing.BtnOK btnOK1;
    private com.bits.lib.dbswing.JBStatusbarDialog jBStatusbarDialog1;
    private com.bits.lib.dbswing.JBToolbarDialog jBToolbarDialog1;
    private com.bits.lib.dbswing.JBdbTable jBdbTable1;
    private com.bits.bee.ui.myswing.JCboAktif jCboAktif1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
