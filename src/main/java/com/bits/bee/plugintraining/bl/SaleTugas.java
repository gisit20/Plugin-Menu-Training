/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bits.bee.plugintraining.bl;

import com.bits.lib.dx.BDM;
import com.bits.lib.dx.BTable;
import com.borland.dx.dataset.Column;
import com.borland.dx.dataset.Variant;

/**
 *
 * @author Sigit Sukarman
 */
public class SaleTugas extends BTable{

    public SaleTugas() {
        super(BDM.getDefault(), "sale", "saleno");

        Column[] cols = {
            new Column("saleno", "saleno", Variant.STRING),
            new Column("topid", "topid", Variant.STRING),
            new Column("cashid", "cashid", Variant.STRING),
            new Column("tstatus", "tstatus", Variant.STRING),
            new Column("taxid", "taxid", Variant.STRING),
            new Column("accar", "accar", Variant.STRING),
            new Column("acccash", "acccash", Variant.STRING),
            new Column("termtype", "termtype", Variant.STRING),
            new Column("sono", "sono", Variant.STRING),
            new Column("empid", "empid", Variant.STRING),
            new Column("accfreight", "accfreight", Variant.STRING),
            new Column("branchid", "branchid", Variant.STRING),
            new Column("canvasid", "canvasid", Variant.STRING),
            new Column("srepid", "srepid", Variant.STRING),
            new Column("accsdisc", "accsdisc", Variant.STRING),
            new Column("saletype", "saletype", Variant.STRING),
            new Column("posid", "posid", Variant.STRING),
            new Column("custid", "custid", Variant.STRING),
            new Column("reftype", "reftype", Variant.STRING),
            new Column("whid", "whid", Variant.STRING),
            new Column("refrid", "refrid", Variant.STRING),
            new Column("perid", "perid", Variant.STRING),
            new Column("crcid", "crcid", Variant.STRING),
            new Column("possesno", "possesno", Variant.STRING),
            new Column("fobid", "fobid", Variant.STRING),
            new Column("shipid", "shipid", Variant.STRING),
            new Column("saleno2", "saleno2", Variant.STRING),
            new Column("saledate", "saledate", Variant.DATE),
            new Column("custpono", "custpono", Variant.STRING),
            new Column("docno", "docno", Variant.STRING),
            new Column("taxsaleno", "taxsaleno", Variant.STRING),
            new Column("taxsaledate", "taxsaledate", Variant.DATE),
            new Column("pph23saleno", "pph23saleno", Variant.STRING),
            new Column("pph23saledate", "pph23saledate", Variant.DATE),
            new Column("billto", "billto", Variant.STRING),
            new Column("shipto", "shipto", Variant.STRING),
            new Column("duedays", "duedays", Variant.SHORT),
            new Column("discdays", "discdays", Variant.SHORT),
            new Column("earlydisc", "earlydisc", Variant.STRING),
            new Column("latecharge", "latecharge", Variant.STRING),
            new Column("shipdtime", "shipdtime", Variant.TIMESTAMP),
            new Column("shipstatus", "shipstatus", Variant.STRING),
            new Column("istaxed", "istaxed", Variant.BOOLEAN),
            new Column("taxinc", "taxinc", Variant.BOOLEAN),
            new Column("excrate", "excrate", Variant.BIGDECIMAL),
            new Column("fisrate", "fisrate", Variant.BIGDECIMAL),
            new Column("costtot", "costtot", Variant.BIGDECIMAL),
            new Column("subtotal", "subtotal", Variant.BIGDECIMAL),
            new Column("discexp", "discexp", Variant.STRING),
            new Column("discamt", "discamt", Variant.BIGDECIMAL),
            new Column("taxamt", "taxamt", Variant.BIGDECIMAL),
            new Column("freight", "freight", Variant.BIGDECIMAL),
            new Column("others", "others", Variant.BIGDECIMAL),
            new Column("total", "total", Variant.BIGDECIMAL),
            new Column("dpayment", "dpayment", Variant.BIGDECIMAL),
            new Column("paidamt", "paidamt", Variant.BIGDECIMAL),
            new Column("salenote", "salenote", Variant.STRING),
            new Column("salestatus", "salestatus", Variant.STRING),
            new Column("paystatus", "paystatus", Variant.STRING),
            new Column("isautogen", "isautogen", Variant.BOOLEAN),
            new Column("refno", "refno", Variant.STRING),
            new Column("chkby", "chkby", Variant.STRING),
            new Column("aprby", "aprby", Variant.STRING),
            new Column("crtby", "crtby", Variant.STRING),
            new Column("crtdate", "crtdate", Variant.TIMESTAMP),
            new Column("updby", "updby", Variant.STRING),
            new Column("upddate", "upddate", Variant.TIMESTAMP),
            new Column("syncdate", "syncdate", Variant.TIMESTAMP),
            new Column("_xt", "_xt", Variant.BOOLEAN),
            new Column("islocked", "islocked", Variant.BOOLEAN),
            new Column("consno", "consno", Variant.STRING),
            new Column("isdraft", "isdraft", Variant.BOOLEAN),
            new Column("isposted", "isposted", Variant.BOOLEAN),
            new Column("accloss", "accloss", Variant.STRING),
            new Column("basesubtotal", "basesubtotal", Variant.BIGDECIMAL),
            new Column("basetaxamt", "basetaxamt", Variant.BIGDECIMAL),
            new Column("basediscamt", "basediscamt", Variant.BIGDECIMAL),
            new Column("basetotal", "basetotal", Variant.BIGDECIMAL),
            new Column("basftaxamt", "basftaxamt", Variant.BIGDECIMAL),
            new Column("isimport", "isimport", Variant.BOOLEAN),
            new Column("settledate", "settledate", Variant.DATE),
            new Column("version", "version", Variant.LONG),
            new Column("ismemorized", "ismemorized", Variant.BOOLEAN),
            new Column("isrecurring", "isrecurring", Variant.BOOLEAN),
            new Column("recurno", "recurno", Variant.STRING),
            new Column("recurdno", "recurdno", Variant.SHORT),
            new Column("memorizednote", "memorizednote", Variant.STRING),
            new Column("printcount", "printcount", Variant.LONG),
            new Column("dpamt", "dpamt", Variant.BIGDECIMAL),
            new Column("isautogendp", "isautogendp", Variant.BOOLEAN)
        };
        createDataSet(cols);
        dataset.open();
    }
}

