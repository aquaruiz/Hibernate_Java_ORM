package ex.xml.domain.dtos;

import com.google.gson.annotations.Expose;

public class SupplierDto {
    @Expose
    private String name;
    @Expose
    private Boolean isImporter;

    public SupplierDto() {
    }

    public SupplierDto(String name, Boolean isImporter) {
        this.name = name;
        this.isImporter = isImporter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getImporter() {
        return isImporter;
    }

    public void setImporter(Boolean importer) {
        isImporter = importer;
    }
}