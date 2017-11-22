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
public class MasterBranch extends BTable {

    public MasterBranch() {
        super(BDM.getDefault(), "branchname", "branchid");
        	
	/********** branch **********/
	Column[] cols = {
		new Column("branchid",			 "branchid",			 Variant.STRING),
		new Column("branchname",			 "branchname",			 Variant.STRING),
		new Column("active",			 "active",			 Variant.BOOLEAN)
	};
	createDataSet(cols);
	dataset.open();
    }

}
