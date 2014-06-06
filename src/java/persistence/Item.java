package persistence;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.Temporal;

/**
 * Entita Item / Polozka skladu
 * @author Bc. Vojtěch Svoboda <svobovo3@fel.cvut.cz>
 */
@PersistenceCapable
public class Item {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key klic;

    /* FK */
    @Persistent
    int id_order;

    /* FK */
    @Persistent
    int id_category;

    @Persistent
    String manufacturer;
    @Persistent
    String caption;
    @Persistent
    String pn;
    @Persistent
    String sn;
    @Persistent
    int price;
    @Persistent
    @Temporal(javax.persistence.TemporalType.DATE)
    Date delivered;
    @Persistent
    @Temporal(javax.persistence.TemporalType.DATE)
    Date invoiced;
    @Persistent
    int warranty_years;
    @Persistent
    int end_warranty;
    @Persistent
    String note;
    @Persistent
    String other_supply;

    public Item() {
    }

    public Item(int id_order, int id_category, String manufacturer, String caption, String pn, String sn, int price, Date delivered, Date invoiced, int warranty_years, int end_warranty, String note, String other_supply) {
        this.id_order = id_order;
        this.id_category = id_category;
        this.manufacturer = manufacturer;
        this.caption = caption;
        this.pn = pn;
        this.sn = sn;
        this.price = price;
        this.delivered = delivered;
        this.invoiced = invoiced;
        this.warranty_years = warranty_years;
        this.end_warranty = end_warranty;
        this.note = note;
        this.other_supply = other_supply;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Date getDelivered() {
        return delivered;
    }

    public void setDelivered(Date delivered) {
        this.delivered = delivered;
    }

    public int getEnd_warranty() {
        return end_warranty;
    }

    public void setEnd_warranty(int end_warranty) {
        this.end_warranty = end_warranty;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public Date getInvoiced() {
        return invoiced;
    }

    public void setInvoiced(Date invoiced) {
        this.invoiced = invoiced;
    }

    public Key getKlic() {
        return klic;
    }

    public String getKeyWeb() {
        return KeyFactory.keyToString(this.klic);
    }

    public long getId() {
        return klic.getId();
    }

    public void setKlic(Key klic) {
        this.klic = klic;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOther_supply() {
        return other_supply;
    }

    public void setOther_supply(String other_supply) {
        this.other_supply = other_supply;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public int getWarranty_years() {
        return warranty_years;
    }

    public void setWarranty_years(int warranty_years) {
        this.warranty_years = warranty_years;
    }

}
