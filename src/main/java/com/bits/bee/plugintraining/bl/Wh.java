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
public class Wh extends BTable {

    public Wh(){
        super(BDM.getDefault(), "wh", "whid");  
    
    	
	/********** wh **********/
	Column[] cols = {
		new Column("whid",			 "whid",			 Variant.STRING),
		new Column("whtype",			 "whtype",			 Variant.STRING),
		new Column("whname",			 "whname",			 Variant.STRING),
		new Column("isautoreconcilein",			 "isautoreconcilein",			 Variant.BOOLEAN),
		new Column("isautoreconcileout",			 "isautoreconcileout",			 Variant.BOOLEAN),
		new Column("active",			 "active",			 Variant.BOOLEAN)
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
//    public Wh(BDM bdm, String tableName, String idNames) {
//        super(bdm, tableName, idNames);
//    }
//    
}
