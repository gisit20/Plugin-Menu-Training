/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bits.bee.plugintraining.bl;

import com.bits.bee.bl.BLUtil;
import com.bits.bee.bl.City;
import com.bits.bee.bl.Cmp;
import com.bits.bee.bl.Reg;
import com.bits.bee.plugintraining.sp.spStock_New_Training;
import com.bits.bee.plugintraining.sp.spStock_Void_Training;
import com.bits.lib.BHelp;
import com.bits.lib.dx.BDM;
import com.bits.lib.dx.BTrans;
import com.borland.dx.dataset.DataChangeEvent;
import com.borland.dx.dataset.DataChangeListener;
import com.borland.dx.dataset.DataSet;
import it.businesslogic.ireport.chart.Dataset;

/**
 *
 * @author Sigit Sukarman
 */
public class SAdjTransTraining extends BTrans {

    private SAdjTraining sadj = new SAdjTraining(); // sebagai master
    private SAdjDTraining sadjd = new SAdjDTraining(); // sebagai detailnya
    private SAdjAdapter adapter = new SAdjAdapter();
    private spStock_New_Training spStockNew = new spStock_New_Training();
    private spStock_Void_Training spStockVoid = new spStock_Void_Training();

    public SAdjTransTraining() {
        super(BDM.getDefault(), "SADJTR", "sadjdate", "branchid", "sadjno", "Stock"); //

        setMaster(sadj);
        addDetail(sadjd);
//        setspNew(new BProcSimple(BDM.getDefault(), "spStock_New_Training", "sadjno"));
        setspNew(spStockNew);
        setspVoid(spStockVoid);
        
        getDataSetDetail().addDataChangeListener(adapter);
    }

    @Override
    public void New() {
        super.New();
        getDataSetMaster().setString("sadjno", "AUTO");
        getDataSetMaster().setDate("sadjdate", BHelp.getCurrentDate_SQL());
    }

    @Override
    public void Save() throws Exception {
        BLUtil.renumberDetail(this, "sadjdno");
        super.Save();
    }
    
    public void initPrint(){
        DataSet drcmp = Cmp.getInstance().getDataSet();
        
        getDataSetMaster().setString("cmpname", drcmp.getString("cmpname"));
        getDataSetMaster().setString("cmpownername", drcmp.getString("ownername"));
        getDataSetMaster().setString("cmpaddr", drcmp.getString("cmpaddr"));
        getDataSetMaster().setString("cmpphone", drcmp.getString("phone"));
        getDataSetMaster().setString("cmpcity", City.getInstance().getCityName(drcmp.getString("cityid"))); //AWAS BEDA
        getDataSetMaster().setString("cmptaxregno", drcmp.getString("taxregno"));
        getDataSetMaster().setString("cmpvatregno", drcmp.getString("vatregno"));
        getDataSetMaster().setDate("cmpvatregdate", drcmp.getDate("vatregdate")); //AWAS BEDA
        getDataSetMaster().setString("cmpformserno", drcmp.getString("formserno"));       
        
        
    }

    private class SAdjAdapter implements DataChangeListener {

        @Override
        public void postRow(DataChangeEvent dce) throws Exception {

        }

        @Override
        public void dataChanged(DataChangeEvent evt) {
            if (evt.getID() == DataChangeEvent.ROW_ADDED && getDataSetDetail().isNull("sadjno")) {
                getDetail().setString("sadjno", getMaster().getString("sadjno"));
                getDetail().setShort("sadjdno", (short) (getDataSetDetail().getRow() + 1));
                getDetail().setString("whid", Reg.getInstance().getValueString("WHSTOCK"));
            }
        }

    }

}
