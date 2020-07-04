package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Optional;

@XmlRootElement(name = "ValCurs")
public class CurrencyRate {
    private String date;
    private String name;
    private List<Currency> currencies;

    public String getDate() {
        return date;
    }

    @XmlAttribute(name = "Date")
    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }


    public List<Currency> getCurrencies() {
        return currencies;
    }

    @XmlElement(name = "Valute")
    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public Optional<Currency> getCurrencyByCode(String charCode) {
        if (charCode == null || charCode.isEmpty()) {
            return Optional.empty();
        }
        return getCurrencies().stream()
                .filter(i -> charCode.equalsIgnoreCase(i.getCharCode()))
                .findAny();
    }

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "date='" + date + '\'' +
                ", name='" + name + '\'' +
                ",\ncurrencies=" + currencies +
                '}';
    }
}
