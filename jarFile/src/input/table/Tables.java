package input.table;

import input.table.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "tables")
@XmlAccessorType(XmlAccessType.FIELD)

public class Tables {

    @XmlElement(name = "table")
    List<Table> tables = new ArrayList<>();

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }


}
