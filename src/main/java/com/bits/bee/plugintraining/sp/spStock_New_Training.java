/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bits.bee.plugintraining.sp;

import com.bits.lib.dx.BDM;
import com.bits.lib.dx.BProcSimple;

/**
 *
 * @author Sigit Sukarman
 */
public class spStock_New_Training extends BProcSimple {
    
    public spStock_New_Training(){
        super(BDM.getDefault(), "spStock_New_Training", "sadjno");
        
        //binding ke BProcsimple
        initParams();
    }
}
