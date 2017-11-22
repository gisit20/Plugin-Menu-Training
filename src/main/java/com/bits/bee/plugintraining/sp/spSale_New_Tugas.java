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
public class spSale_New_Tugas extends BProcSimple {

    public spSale_New_Tugas() {
        super(BDM.getDefault(), "spSale_New_Tugas", "saleno");

        //binding ke BProcsimple
        initParams();
    }

}
