/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bits.bee.plugintraining.bl;

import com.bits.lib.dx.BDM;
import com.bits.lib.dx.BTable;
import com.bits.lib.dx.Column;
import com.borland.dx.dataset.Variant;

/**
 *
 * @author Sigit Sukarman
 */
public class Gudang extends BTable {

    public Gudang() {
        super(BDM.getDefault(), "wh", "whid");

        /**
         * ******** wh *********
         */
        Column[] cols = {
            new Column("whid", "whid", Variant.STRING),
            new Column("whtype", "whtype", Variant.STRING),
            new Column("whname", "whname", Variant.STRING),
            new Column("isautoreconcilein", "isautoreconcilein", Variant.BOOLEAN),
            new Column("isautoreconcileout", "isautoreconcileout", Variant.BOOLEAN),
            new Column("active", "active", Variant.BOOLEAN)
        };
        createDataSet(cols);
        dataset.open();

    }

}
