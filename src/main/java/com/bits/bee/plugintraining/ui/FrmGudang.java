/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bits.bee.plugintraining.ui;

import com.bits.bee.plugintraining.bl.Gudang;
import com.bits.bee.ui.FrmMasterAbstract;
import com.bits.bee.ui.UIMgr;
import com.bits.lib.dx.provider.BTableProvider;

/**
 *
 * @author Sigit Sukarman
 */
public class FrmGudang extends FrmMasterAbstract {

    public FrmGudang() {
        super(BTableProvider.createTable(Gudang.class), "Master Gudang", "PG-TR-01");
        initTable();
    }

    private void initTable() {
        table.getDataSet().getColumn("whid").setCaption("Kode");
        table.getDataSet().getColumn("whid").setWidth(5);
        table.getDataSet().getColumn("whname").setCaption("Name");
        table.getDataSet().getColumn("whname").setWidth(20);
        table.getDataSet().getColumn("whtype").setVisible(0);
        table.getDataSet().getColumn("active").setVisible(0);
        table.getDataSet().getColumn("isautoreconcilein").setVisible(0);
        table.getDataSet().getColumn("isautoreconcileout").setVisible(0);
    }

    private boolean validateData() {
        if(table.getDataSet().getString("whname").isEmpty()){
            UIMgr.showErrorDialog("Nama gudang tidak boleh kosong");
            return false;
        }
        return true;
    }
    
    @Override
    public void doSave(){
        validateData();
        super.doSave();
    }
}
