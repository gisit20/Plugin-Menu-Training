/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bits.bee.plugintraining.ui;

import com.bits.bee.bl.Branch;
import com.bits.bee.plugintraining.bl.Gudang;
import com.bits.bee.ui.FrmMasterAbstract;
import com.bits.bee.ui.UIMgr;
import com.bits.lib.dx.provider.BTableProvider;

/**
 *
 * @author Sigit Sukarman
 */
public class FrmMasterBranch extends FrmMasterAbstract {

    public FrmMasterBranch() {
        super(BTableProvider.createTable(Branch.class), "Master Cabang", "PG-TR-TUGAS-01");
        initTable();
    }

    private void initTable() {
        table.getDataSet().getColumn("branchname").setCaption("Nama");
    }

    private boolean validateData() {
        if (table.getDataSet().getString("branchid").isEmpty()) {
            UIMgr.showErrorDialog("Kode Branch tidak boleh kosong");
            return false;
        } else if (table.getDataSet().getString("branchname").isEmpty()) {
            UIMgr.showErrorDialog("Nama Branch tidak boleh kosong");
            return false;
        } 
        return true;
    }

    @Override
    public void doSave() {
        if(validateData()){
            super.doSave();
        }
    }
}
