/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bits.bee.plugintraining.ui.component;

import com.bits.bee.ui.DlgItem;
import com.bits.lib.dbswing.JBSPicker;
import java.awt.Dimension;

/**
 *
 * @author Sigit Sukarman
 */
public class SPikItem extends JBSPicker {
    
    public SPikItem(){
        setDialog(DlgItem.getInstance());
        setPreferredSize(new Dimension(180, 19));
        setToolTipText("Pilih Item");
        
    }
    
}
