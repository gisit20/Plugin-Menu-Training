/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bits.bee.plugintraining.ui.component;

import com.bits.bee.plugintraining.bl.PegawaiList;
import com.bits.bee.plugintraining.ui.dlg.DlgPegawai;
import com.bits.lib.dbswing.JBDialog;
import com.bits.lib.dbswing.JBPicker;

/**
 *
 * @author Sigit Sukarman
 */
public class PikPegawai extends JBPicker {

    private DlgPegawai dlg;
    
    @Override
    public JBDialog getDefaultDialog() {
        if(dlg ==null){
            dlg=DlgPegawai.getInstance();
        }
        return dlg;
    }

    @Override
    protected String getDescription(String id) { //supaya nge-get namanya
        return PegawaiList.getInstance().getPegawaiName(id);
    }
    
}
