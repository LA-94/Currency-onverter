package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlRootElement
@XmlType(propOrder = {"numCode", "charCode", "nominal", "name", "value"})
public class Currency {
    private String id;
    private String numCode;
    private String charCode;
    private int nominal;
    private String name;
    private String value;


    public String getId() {
        return id;
    }

    public String getNumCode() {
        return numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public int getNominal() {
        return nominal;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @XmlElement(name = "Value")
    public void setValue(String value) {
        this.value = value;
    }

    @XmlAttribute(name = "ID")
    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "NumCode")
    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    @XmlElement(name = "CharCode")
    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    @XmlElement(name = "Nominal")
    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    @XmlElement(name = "Name")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != Currency.class) return false;
        Currency currency = (Currency) o;
        return Objects.equals(getId(), currency.getId()) &&
                getNominal() == currency.getNominal() &&
                Objects.equals(currency.getValue(), getValue()) &&
                Objects.equals(getNumCode(), currency.getNumCode()) &&
                Objects.equals(getCharCode(), currency.getCharCode()) &&
                Objects.equals(getName(), currency.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumCode(), getCharCode(), getNominal(), getName(), getValue());
    }

    @Override
    public String toString() {
        return "\nCurrency{" +
                "id='" + id + '\'' +
                ", numCode='" + numCode + '\'' +
                ", charCode='" + charCode + '\'' +
                ", nominal=" + nominal +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
