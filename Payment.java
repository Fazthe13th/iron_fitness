/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iron_fitness;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author wwwfa
 */
public class Payment {
    private final SimpleStringProperty TID;
    private final SimpleStringProperty Memid;
    private final SimpleStringProperty Pdate;
    private final SimpleStringProperty Exdate;
    private final SimpleStringProperty Ptype;
    private final SimpleStringProperty Amount;
    public Payment(String tid,String memidinfo,String pdate,String exdate,String ptype,String amount)
            {
                this.TID = new SimpleStringProperty(tid);
                this.Memid = new SimpleStringProperty(memidinfo);
                this.Pdate = new SimpleStringProperty(pdate);
                this.Exdate = new SimpleStringProperty(exdate);
                this.Ptype = new SimpleStringProperty(ptype);
                this.Amount = new SimpleStringProperty(amount);
            }
    public String getTid()
    {
        return TID.get();
    }
    public void setTid(String tid)
    {
        TID.set(tid);
    }
    public String getMemidinfo()
    {
        return Memid.get();
    }
    public void setMemidinfo(String memidinfo)
    {
        Memid.set(memidinfo);
    }
    public String getPdate()
    {
        return Pdate.get();
    }
    public void setPdate(String pdate)
    {
        Pdate.set(pdate);
    }
    public String getExdate()
    {
        return Exdate.get();
    }
    public void setExdate(String exdate)
    {
        Exdate.set(exdate);
    }
    public String getPtype()
    {
        return Ptype.get();
    }
    public void setPtype(String ptype)
    {
        Ptype.set(ptype);
    }
    public String getAmount()
    {
        return Amount.get();
    }
    public void setAmount(String amount)
    {
        Amount.set(amount);
    }
}
