/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bits.bee.plugintraining.bl;

import com.bits.bee.bl.InstanceObserver;
import com.bits.bee.conf.InstanceMgr;
import com.bits.lib.dx.BDM;
import com.bits.lib.dx.BQuerySimple;
import org.openide.util.Exceptions;

/**
 *
 * @author Sigit Sukarman
 */
public class PegawaiList extends BQuerySimple implements InstanceObserver {

    private static PegawaiList pegawaiList = null;

    public PegawaiList() {
        super(BDM.getDefault(), "emp", "empid", "empname, empname");
    }

    public static synchronized PegawaiList getInstance() {
        if (pegawaiList == null) {
            pegawaiList = new PegawaiList();
            try {
                pegawaiList.Load();
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
            InstanceMgr.getInstance().addObserver(pegawaiList);
        }

        return pegawaiList;
    }

    public String getPegawaiName(String id){
        return find("empid", id, "empname");
    }

    @Override
    public void doUpdate() {
        pegawaiList =null;
    }
}
