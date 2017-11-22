/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bits.bee.plugintraining.bl;

import com.bits.lib.dx.BDM;
import com.bits.lib.dx.BTable;
import com.bits.lib.dx.JBSQL;
import com.borland.dx.dataset.Column;
import com.borland.dx.dataset.Variant;
import java.util.HashMap;

/**
 *
 * @author Sigit Sukarman
 */
public class SAdjTraining extends BTable {

    public SAdjTraining() {
        super(BDM.getDefault(), "sadj", "sadjno");

        /**
         * ******** sadj *********
         */
        Column[] cols = {
            new Column("sadjno", "sadjno", Variant.STRING),
            new Column("empid", "empid", Variant.STRING),
            new Column("perid", "perid", Variant.STRING),
            new Column("sadjdate", "sadjdate", Variant.DATE),
            new Column("sadjtype", "sadjtype", Variant.STRING),
            new Column("sadjmtd", "sadjmtd", Variant.STRING),
            new Column("reftype", "reftype", Variant.STRING),
            new Column("refno", "refno", Variant.STRING),
            new Column("sadjaccno", "sadjaccno", Variant.STRING),
            new Column("bpid", "bpid", Variant.STRING),
            new Column("branchid", "branchid", Variant.STRING),
            new Column("sadjstatus", "sadjstatus", Variant.STRING),
            new Column("sadjnote", "sadjnote", Variant.STRING),
            new Column("isautogen", "isautogen", Variant.BOOLEAN),
            new Column("islocked", "islocked", Variant.BOOLEAN),
            new Column("isposted", "isposted", Variant.BOOLEAN),
            new Column("crtby", "crtby", Variant.STRING),
            new Column("crtdate", "crtdate", Variant.TIMESTAMP),
            new Column("updby", "updby", Variant.STRING),
            new Column("upddate", "upddate", Variant.TIMESTAMP),
            new Column("_xt", "_xt", Variant.BOOLEAN),
            new Column("isdraft", "isdraft", Variant.BOOLEAN),
            new Column("version", "version", Variant.LONG),
            new Column("ismemorized", "ismemorized", Variant.BOOLEAN),
            new Column("memorizednote", "memorizednote", Variant.STRING),
            new Column("printcount", "printcount", Variant.LONG),
            /**
             * Kolom tambahan untuk text report*
             */
            new Column("cmpname", "cmpname", Variant.STRING),
            new Column("cmpownername", "cmpownername", Variant.STRING),
            new Column("cmpaddr", "cmpaddr", Variant.STRING),
            new Column("cmpphone", "cmpphone", Variant.STRING),
            new Column("cmpcity", "cmpcity", Variant.STRING),
            new Column("cmptaxregno", "cmptaxregno", Variant.STRING),
            new Column("cmpvatregdate", "cmpvatregdate", Variant.DATE),
            new Column("cmpformserno", "cmpformserno", Variant.STRING),
            new Column("cmpvatregno", "cmpvatregno", Variant.STRING),
        };
        HashMap hm = JBSQL.ColumnsToHashMap(cols);

        ((Column) hm.get("cmpname")).setResolvable(false);
        ((Column) hm.get("cmpownername")).setResolvable(false);
        ((Column) hm.get("cmpaddr")).setResolvable(false);
        ((Column) hm.get("cmpphone")).setResolvable(false);
        ((Column) hm.get("cmpcity")).setResolvable(false);
        ((Column) hm.get("cmptaxregno")).setResolvable(false);
        ((Column) hm.get("cmpvatregdate")).setResolvable(false);
        ((Column) hm.get("cmpformserno")).setResolvable(false);
        ((Column) hm.get("cmpvatregno")).setResolvable(false);

        createDataSet(cols);

        dataset.open();

    }
}
