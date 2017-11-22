/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bits.bee.plugintraining.bl;

import com.bits.bee.bl.BLUtil;
import com.bits.bee.bl.ItemList;
import com.bits.lib.dx.BDM;
import com.bits.lib.dx.BTable;
import com.bits.lib.dx.JBSQL;
import com.borland.dx.dataset.CalcFieldsListener;
import com.borland.dx.dataset.Column;
import com.borland.dx.dataset.ColumnChangeListener;
import com.borland.dx.dataset.DataChangeEvent;
import com.borland.dx.dataset.DataChangeListener;
import com.borland.dx.dataset.DataRow;
import com.borland.dx.dataset.DataSet;
import com.borland.dx.dataset.DataSetException;
import com.borland.dx.dataset.ReadRow;
import com.borland.dx.dataset.Variant;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.TooManyListenersException;
import org.openide.util.Exceptions;

/**
 *
 * @author Sigit Sukarman
 */
public class SAdjDTraining extends BTable implements ColumnChangeListener, DataChangeListener, CalcFieldsListener {

    public SAdjDTraining() {
        super(BDM.getDefault(), "sadjd", "sadjno, sadjdno");
        /**
         * ******** sadjd *********
         */
        Column[] cols = {
            new Column("sadjno", "sadjno", Variant.STRING),
            new Column("sadjdno", "sadjdno", Variant.SHORT),
            
            new Column("whid", "whid", Variant.STRING),
            new Column("itemid", "itemid", Variant.STRING),
            new Column("itemdesc", "itemdesc", Variant.STRING),
            
            new Column("pid", "pid", Variant.STRING),
            new Column("qty", "qty", Variant.BIGDECIMAL),
            new Column("unit", "unit", Variant.STRING),
            new Column("conv", "conv", Variant.BIGDECIMAL),
            new Column("qtyx", "qtyx", Variant.BIGDECIMAL),
            new Column("oldcost", "oldcost", Variant.BIGDECIMAL),
            new Column("cost", "cost", Variant.BIGDECIMAL),
            new Column("_xt", "_xt", Variant.BOOLEAN),
            new Column("subtotal", "subtotal", Variant.BIGDECIMAL),
            new Column("prjid", "prjid", Variant.STRING),
            new Column("deptid", "deptid", Variant.STRING),
            
            
        };
        HashMap hm = JBSQL.ColumnsToHashMap(cols);
        JBSQL.setCalc((Column)hm.get("itemdesc"));
        
       
        
        createDataSet(cols);

        BLUtil.setDataPreferredOrdinal(cols); //untuk mengurutkan kolom sesuai urutan kolom diatas
        try {
            dataset.addColumnChangeListener(this);
            dataset.addDataChangeListener(this);
            dataset.addCalcFieldsListener(this);
        } catch (TooManyListenersException ex) {
            Exceptions.printStackTrace(ex);
        } catch (DataSetException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        dataset.open();

    }

    @Override
    public void changed(DataSet ds, Column column, Variant vrnt) {
        if(column.getColumnName().equalsIgnoreCase("itemid") && ds.getString("itemid").length()>0){ //mengeset quantyty sama unit saja
            BigDecimal qty = BigDecimal.ONE;
            
            setBigDecimal("qty", qty);
            setString("unit", ItemList.getInstance().getStockUnit(getString("itemid")));
            dataset.post();            
        }
        
        
    }

    @Override
    public void validate(DataSet ds, Column column, Variant vrnt) throws Exception, DataSetException {
        
    }

    @Override
    public void postRow(DataChangeEvent dce) throws Exception {
        if(getDataSet().isNull("itemid")){
            getDataSet().emptyRow();
        }
        
    }

    @Override
    public void dataChanged(DataChangeEvent dce) {
        
    }

    @Override
    public void calcFields(ReadRow rr, DataRow dr, boolean bln) {
        dr.setString("itemdesc", ItemList.getInstance().getItemDesc(rr.getString("itemid")));
    }
}
