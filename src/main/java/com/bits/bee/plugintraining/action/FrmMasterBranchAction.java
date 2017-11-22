/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bits.bee.plugintraining.action;

import com.bits.bee.plugintraining.ui.FrmMasterBranch;
import com.bits.bee.ui.FrmMasterAbstract;
import com.bits.bee.ui.ScreenManager;
import com.bits.core.ui.action.MenuAction;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author Sigit Sukarman
 */
public class FrmMasterBranchAction extends MenuAction {

    @Override
    public String getObjId() {
        return "PG-TR-TUGAS-01";
    }

    @Override
    public ImageIcon getIcon() {
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ScreenManager.getMainFrame().addInternalFrame(new FrmMasterBranch());
    }
    
}
