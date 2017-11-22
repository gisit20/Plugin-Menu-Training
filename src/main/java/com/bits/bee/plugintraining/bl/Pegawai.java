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
public class Pegawai extends BTable {

    public Pegawai() {
        super(BDM.getDefault(), "emp", "empid");

        /**
         * ******** emp *********
         */
        Column[] cols = {
            new Column("empid", "empid", Variant.STRING),
            new Column("bankid", "bankid", Variant.STRING),
            new Column("empgrp3id", "empgrp3id", Variant.STRING),
            new Column("majorid", "majorid", Variant.STRING),
            new Column("empitype", "empitype", Variant.STRING),
            new Column("jobfid", "jobfid", Variant.STRING),
            new Column("countryid", "countryid", Variant.STRING),
            new Column("empgrp4id", "empgrp4id", Variant.STRING),
            new Column("stateid", "stateid", Variant.STRING),
            new Column("eduid", "eduid", Variant.STRING),
            new Column("sexid", "sexid", Variant.STRING),
            new Column("joblevid", "joblevid", Variant.STRING),
            new Column("empgrpid", "empgrpid", Variant.STRING),
            new Column("emptype", "emptype", Variant.STRING),
            new Column("cityid", "cityid", Variant.STRING),
            new Column("reliid", "reliid", Variant.STRING),
            new Column("birthcityid", "birthcityid", Variant.STRING),
            new Column("marryid", "marryid", Variant.STRING),
            new Column("empgrp2id", "empgrp2id", Variant.STRING),
            new Column("branchid", "branchid", Variant.STRING),
            new Column("deptid", "deptid", Variant.STRING),
            new Column("empno", "empno", Variant.STRING),
            new Column("empno2", "empno2", Variant.STRING),
            new Column("empname", "empname", Variant.STRING),
            new Column("emptitle", "emptitle", Variant.STRING),
            new Column("birthdate", "birthdate", Variant.DATE),
            new Column("addr", "addr", Variant.STRING),
            new Column("zipcode", "zipcode", Variant.STRING),
            new Column("phone", "phone", Variant.STRING),
            new Column("mobile", "mobile", Variant.STRING),
            new Column("email", "email", Variant.STRING),
            new Column("status", "status", Variant.STRING),
            new Column("taxfamcount", "taxfamcount", Variant.BIGDECIMAL),
            new Column("taxregno", "taxregno", Variant.STRING),
            new Column("bankname", "bankname", Variant.STRING),
            new Column("bankaccno", "bankaccno", Variant.STRING),
            new Column("bankaccname", "bankaccname", Variant.STRING),
            new Column("crtby", "crtby", Variant.STRING),
            new Column("crtdate", "crtdate", Variant.TIMESTAMP),
            new Column("updby", "updby", Variant.STRING),
            new Column("upddate", "upddate", Variant.TIMESTAMP),
            new Column("regdate", "regdate", Variant.DATE),
            new Column("joindate", "joindate", Variant.DATE),
            new Column("retireat", "retireat", Variant.BIGDECIMAL),
            new Column("retiredate", "retiredate", Variant.DATE),
            new Column("active", "active", Variant.BOOLEAN)
        };
//	HashMap hm = JBSQL.ColumnsToHashMap(cols);
//	JBSQL.setCalc((Column)hm.get("qtydesc"));
//	((Column)hm.get("duedate")).setFormatter(UIMgr.getDateYMD());
        createDataSet(cols);
//	try {
//		dataset.addCalcFieldsListener(this);
//		dataset.addColumnChangeListener(this);
//	} catch (DataSetException ex) {
//		ex.printStackTrace();
//	} catch (TooManyListenersException ex) {
//		ex.printStackTrace();
//	}
        dataset.open();

    }
}
