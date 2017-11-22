/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bits.bee.plugintraining.bl;

import com.bits.bee.bl.BLUtil;
import com.bits.bee.bl.Reg;
import com.bits.bee.plugintraining.sp.spSale_New_Tugas;
import com.bits.bee.plugintraining.sp.spSale_Void_Tugas;
import com.bits.lib.BHelp;
import com.bits.lib.dx.BDM;
import com.bits.lib.dx.BTrans;
import com.borland.dx.dataset.Column;
import com.borland.dx.dataset.ColumnChangeListener;
import com.borland.dx.dataset.DataChangeEvent;
import com.borland.dx.dataset.DataChangeListener;
import com.borland.dx.dataset.DataSet;
import com.borland.dx.dataset.DataSetException;
import com.borland.dx.dataset.Variant;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;

/**
 *
 * @author Sigit Sukarman
 */
public class SaleTransTugas extends BTrans {

    private SaleTugas sale = new SaleTugas(); // sebagai master
    private SaleDTugas saled = new SaleDTugas(); // sebagai detailnya
    private SaleAdjAdapter adapter = new SaleAdjAdapter();
    private spSale_New_Tugas spSaleNew = new spSale_New_Tugas();
    private spSale_Void_Tugas spSaleVoid = new spSale_Void_Tugas();

    public SaleTransTugas() {
        super(BDM.getDefault(), "SALE", "saledate", "branchid", "saleno", "Sale");

        setMaster(sale);
        addDetail(saled);

        setspNew(spSaleNew);
        setspVoid(spSaleVoid);

        getDataSetDetail().addDataChangeListener(adapter);

        saled.addPropertyChangeListener("qty", adapter);
        saled.addPropertyChangeListener("listprice", adapter);
        saled.addPropertyChangeListener("subtotal", adapter);
    }

    @Override
    public void New() {
        super.New();
        getDataSetMaster().setString("saleno", "AUTO");
        getDataSetMaster().setDate("saledate", BHelp.getCurrentDate_SQL());
//        getDataSetMaster().setDate("crcid", BPAccList.getInstance().get
        getDataSetMaster().setString("saletype", "S");
    }

    @Override
    public void Save() throws Exception {
        BLUtil.renumberDetail(this, "saledno");
        super.Save();
    }

    private class SaleAdjAdapter implements DataChangeListener, ColumnChangeListener, PropertyChangeListener {

        @Override
        public void postRow(DataChangeEvent dce) throws Exception {
        }

        @Override
        public void dataChanged(DataChangeEvent evt) {
            if (evt.getID() == DataChangeEvent.ROW_ADDED && getDataSetDetail().isNull("saleno")) {
                getDetail().setString("saleno", getMaster().getString("saleno"));
                getDetail().setShort("saledno", (short) (getDataSetDetail().getRow() + 1));
                getDetail().setString("whid", Reg.getInstance().getValueString("WHSTOCK"));

            }
        }

        @Override
        public void changed(DataSet ds, Column column, Variant vrnt) {
            if (column.getColumnName().equalsIgnoreCase("listprice") || (column.getColumnName().equalsIgnoreCase("qty"))) {
                int jumBrs = getDataSetDetail().getRowCount();
                for (int i = 0; i < jumBrs; i++) {
                    getDataSetDetail().goToRow(i);
                    BigDecimal total = getDataSetMaster().getBigDecimal("total");
                    BigDecimal sub = getDataSetDetail().getBigDecimal("subtotal");
                    total = total.add(sub);
                    getDataSetMaster().setBigDecimal("total", total);
//                    getDataSetMaster().setBigDecimal("txtTotal", total);
                }
                ds.post();
            }

        }

        @Override
        public void validate(DataSet ds, Column column, Variant vrnt) throws Exception, DataSetException {

        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            String propertyName = evt.getPropertyName();
            if ("subtotal".equalsIgnoreCase(propertyName)
                    || "qty".equalsIgnoreCase(propertyName)
                    || "listprice".equalsIgnoreCase(propertyName)) {
                calcTotal();
            }
        }
    }

    private void calcTotal() {
        getMaster().setBigDecimal("total", BigDecimal.ZERO);
        int rowActive = getDataSetDetail().getRow();
        for (int i = 0; i < getDetail().getRowCount(); i++) {
            getDataSetDetail().goToRow(i);
            getMaster().setBigDecimal("total", getMaster().getBigDecimal("total").add(getDataSetDetail().getBigDecimal("subtotal")));
        }
        getDataSetDetail().goToRow(rowActive);
    }

}
