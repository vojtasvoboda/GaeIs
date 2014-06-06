package persistence;

import java.util.List;
import javax.xml.bind.annotation.*;

/**
 * Třída čtenáře pro příjem Black Listu
 * @author Bc. Vojtěch Svoboda <svobovo3@fel.cvut.cz>
 */
@XmlRootElement(name = "blacklist")
public class BlackList {

    public List<Reader> list;

    public BlackList() {
    }

    @XmlElement
    public List<Reader> getReaders() {
        return list;
    }

    public void setFirstName(List<Reader> list) {
        this.list = list;
    }

}
