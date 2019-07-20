package mostwanted.domain.dtos.races;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceImportDto {
    @XmlElement
    private int laps;

    @XmlElement(name = "district-name")
    private String districtName;

    @XmlElement(name = "entries")
    private EntryImportRootDto entires;

    public RaceImportDto() {
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public EntryImportRootDto getEntires() {
        return entires;
    }

    public void setEntires(EntryImportRootDto entires) {
        this.entires = entires;
    }
}