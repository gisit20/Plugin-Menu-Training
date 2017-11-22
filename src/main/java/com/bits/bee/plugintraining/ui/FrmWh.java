/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bits.bee.plugintraining.ui;

import com.bits.bee.plugintraining.bl.Wh;
import com.bits.bee.ui.FrmMasterAbstract;

/**
 *
 * @author Sigit Sukarman
 */
public class FrmWh extends FrmMasterAbstract{
    
    Wh wh = new Wh();
    
    public FrmWh(){
        super(new Wh(), "Master Gudang", "PG-TR-01");
    }
}
