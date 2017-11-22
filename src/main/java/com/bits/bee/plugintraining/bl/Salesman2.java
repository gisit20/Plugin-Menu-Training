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
public class Salesman2 extends BTable {

    public Salesman2() {
        super(BDM.getDefault(), "srep", "srepid");

        /**
         * ******** srep *********
         */
        Column[] cols = {
            new Column("srepid", "srepid", Variant.STRING),
            new Column("stateid", "stateid", Variant.STRING),
            new Column("countryid", "countryid", Variant.STRING),
            new Column("cityid", "cityid", Variant.STRING),
            new Column("srepname", "srepname", Variant.STRING),
            new Column("sreptype", "sreptype", Variant.STRING),
            new Column("addr", "addr", Variant.STRING),
            new Column("zipcode", "zipcode", Variant.STRING),
            new Column("phone", "phone", Variant.STRING),
            new Column("mobile", "mobile", Variant.STRING),
            new Column("email", "email", Variant.STRING),
            new Column("status", "status", Variant.STRING),
            new Column("arlimitamt", "arlimitamt", Variant.BIGDECIMAL),
            new Column("passwd", "passwd", Variant.STRING),
            new Column("active", "active", Variant.BOOLEAN)
        };

        createDataSet(cols);

        dataset.open();

    }
}

