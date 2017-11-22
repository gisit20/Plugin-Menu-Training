/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bits.bee.plugintraining.ui;

import com.bits.bee.bl.SRep;
import com.bits.bee.ui.FrmMasterAbstract;
import com.bits.lib.dx.BTable;
import com.bits.lib.dx.provider.BTableProvider;

/**
 *
 * @author Sigit Sukarman
 */
public class FrmSalesman extends FrmMasterAbstract {

    public FrmSalesman() {
        super(BTableProvider.createTable(SRep.class), "Master Salesman", "PG-TR-TUGAS-02");
    }
    
}
