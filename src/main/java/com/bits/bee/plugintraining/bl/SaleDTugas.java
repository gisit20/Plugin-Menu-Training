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
public class SaleDTugas extends BTable implements ColumnChangeListener, DataChangeListener{

    public SaleDTugas() {

        super(BDM.getDefault(), "saled", "saleno, saledno");
        /**
         * ******** saled *********
         */
        Column[] cols = {
            new Column("saleno", "saleno", Variant.STRING),
            new Column("saledno", "saledno", Variant.SHORT),
            new Column("sono", "sono", Variant.STRING),
            new Column("sodno", "sodno", Variant.SHORT),
            new Column("taxid", "taxid", Variant.STRING),
            new Column("deptid", "deptid", Variant.STRING),
            new Column("deono", "deono", Variant.STRING),
            new Column("deodno", "deodno", Variant.SHORT),
            new Column("srepid", "srepid", Variant.STRING),
            new Column("delino", "delino", Variant.STRING),
            new Column("delidno", "delidno", Variant.SHORT),
            new Column("prjid", "prjid", Variant.STRING),
            new Column("consno", "consno", Variant.STRING),
            new Column("consdno", "consdno", Variant.SHORT),
            
            new Column("whid", "whid", Variant.STRING),
            
            new Column("itemid", "itemid", Variant.STRING),
            
            
            new Column("pid", "pid", Variant.STRING),
            new Column("itemdesc", "itemdesc", Variant.STRING),
            new Column("qty", "qty", Variant.BIGDECIMAL),
            new Column("unit", "unit", Variant.STRING),
            new Column("conv", "conv", Variant.BIGDECIMAL),
            new Column("qtyx", "qtyx", Variant.BIGDECIMAL),
            new Column("cost", "cost", Variant.BIGDECIMAL),
            new Column("listprice", "listprice", Variant.BIGDECIMAL),
            new Column("baseprice", "baseprice", Variant.BIGDECIMAL),
            new Column("discexp", "discexp", Variant.STRING),
            new Column("discamt", "discamt", Variant.BIGDECIMAL),
            new Column("disc2amt", "disc2amt", Variant.BIGDECIMAL),
            new Column("taxamt", "taxamt", Variant.BIGDECIMAL),
            new Column("isbonus", "isbonus", Variant.BOOLEAN),
            new Column("ischprice", "ischprice", Variant.BOOLEAN),
            new Column("salednote", "salednote", Variant.STRING),
            new Column("expdate", "expdate", Variant.DATE),
            new Column("subtotal", "subtotal", Variant.BIGDECIMAL),
            new Column("taxableamt", "taxableamt", Variant.BIGDECIMAL),
            new Column("totaltaxamt", "totaltaxamt", Variant.BIGDECIMAL),
            new Column("totaldiscamt", "totaldiscamt", Variant.BIGDECIMAL),
            new Column("totaldisc2amt", "totaldisc2amt", Variant.BIGDECIMAL),
            new Column("basesubtotal", "basesubtotal", Variant.BIGDECIMAL),
            new Column("basetotaltaxamt", "basetotaltaxamt", Variant.BIGDECIMAL),
            new Column("basftotaltaxamt", "basftotaltaxamt", Variant.BIGDECIMAL),
            new Column("sqno", "sqno", Variant.STRING),
            new Column("sqdno", "sqdno", Variant.SHORT)
        };
	HashMap hm = JBSQL.ColumnsToHashMap(cols);
//	JBSQL.setCalc((Column)hm.get("itemdesc"));
//	((Column)hm.get("duedate")).setFormatter(UIMgr.getDateYMD());
        createDataSet(cols);
        
        BLUtil.setDataPreferredOrdinal(cols); //untuk mengurutkan kolom sesuai urutan kolom diatas
        try {
            dataset.addColumnChangeListener(this);
            dataset.addDataChangeListener(this);
//            dataset.addCalcFieldsListener(this);
        } catch (TooManyListenersException ex) {
            Exceptions.printStackTrace(ex);
        } catch (DataSetException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        dataset.open();
//	try {
//		dataset.addCalcFieldsListener(this);
//		dataset.addColumnChangeListener(this);
//	} catch (DataSetException ex) {
//		ex.printStackTrace();
//	} catch (TooManyListenersException ex) {
//		ex.printStackTrace();
//	}
//        dataset.open();
    }

    @Override
    public void changed(DataSet ds, Column column, Variant vrnt) {
        String columnName = column.getColumnName();
        if(column.getColumnName().equalsIgnoreCase("itemid") && ds.getString("itemid").length()>0){ //mengeset quantyty sama unit saja
            BigDecimal qty = BigDecimal.ONE;
            
            setBigDecimal("qty", qty);
            setString("unit", ItemList.getInstance().getStockUnit(getString("itemid")));
            setString("itemdesc", ItemList.getInstance().getItemDesc(getString("itemid")));
            dataset.post();
            
            firePropertyChange(columnName, null, vrnt.getString());
        }
        
        if(column.getColumnName().equalsIgnoreCase("listprice") 
                || (column.getColumnName().equalsIgnoreCase("qty"))){ //mengeset quantyty sama unit saja
            BigDecimal qty = ds.getBigDecimal("qty");
            BigDecimal listPrice = ds.getBigDecimal("listprice");
            BigDecimal sub = qty.multiply(listPrice);
            
            setBigDecimal("subtotal", sub);
//            setString("unit", ItemList.getInstance().getStockUnit(getString("itemid")));
//            dataset.post();
            
            //mentriger total
            firePropertyChange(columnName, null, vrnt.getBigDecimal());
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
    public void dataChanged(DataChangeEvent evt) {
//        evt.setString("itemdesc", ItemList.getInstance().getItemDesc(evt.getString("itemid")));
    }
    
}
