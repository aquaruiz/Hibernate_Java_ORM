package mostwanted.domain.dtos.races;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntryImportRootDto {
    @XmlElement(name = "entry")
    private Set<EntryImportDto> entires;

    public EntryImportRootDto() {
    }

    public Set<EntryImportDto> getEntires() {
        return entires;
    }

    public void setEntires(Set<EntryImportDto> entires) {
        this.entires = entires;
    }
}