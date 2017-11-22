/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bits.bee.plugintraining.ui.component;

import com.bits.bee.bl.BranchList;
import com.bits.bee.ui.myswing.BCboComboBox;

/**
 *
 * @author Sigit Sukarman
 */
public class CboCabang extends BCboComboBox {
    public CboCabang(){
        super();
        setListDataSet (BranchList.getInstance().getDataSet());
        setListKeyName("branchid");
        setListDisplayName("branchname");
        
        setEnabled(isEnabled());
    }
}
