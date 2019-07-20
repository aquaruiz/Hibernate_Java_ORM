package mostwanted.domain.dtos.raceentries;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryImportRootDto {
    @XmlElement(name = "race-entry")
    private Set<RaceEntryImportDto> raceEntries;

    public RaceEntryImportRootDto() {
    }

    public Set<RaceEntryImportDto> getRaceEntries() {
        return raceEntries;
    }

    public void setRaceEntries(Set<RaceEntryImportDto> raceEntries) {
        this.raceEntries = raceEntries;
    }
}