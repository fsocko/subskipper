package xmlParser;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "employees")
@XmlAccessorType (XmlAccessType.FIELD)
public class Ships
{
    @XmlElement(name = "employee")
    private List<Ship> employees = null;
 
    public List<Ship> getEmployees() {
        return employees;
    }
 
    public void setEmployees(List<Ship> employees) {
        this.employees = employees;
    }
}
