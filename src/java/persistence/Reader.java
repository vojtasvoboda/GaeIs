package persistence;

import javax.xml.bind.annotation.*;

/**
 * Třída čtenáře pro příjem Black Listu
 * @author Bc. Vojtěch Svoboda <svobovo3@fel.cvut.cz>
 */
@XmlRootElement(name = "reader")
public class Reader {

    public String id;
    public String FirstName;
    public String SurName;
    public String email;

    public Reader(String id, String FirstName, String SurName, String email) {
        this.id = id;
        this.FirstName = FirstName;
        this.SurName = SurName;
        this.email = email;
    }

    public Reader() {
    }

    @XmlElement
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    @XmlElement
    public String getSurName() {
        return SurName;
    }

    public void setSurName(String SurName) {
        this.SurName = SurName;
    }

    @XmlElement
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
