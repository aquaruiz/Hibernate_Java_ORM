package json.ex.domain.dtos.query3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocalSupplierDto {
    @Expose
    @SerializedName(value = "Id")
    private Integer id;

    @Expose
    @SerializedName(value = "Name")
    private String name;

    @Expose
    private long partsCount;

    public LocalSupplierDto() {
    }

    public LocalSupplierDto(Integer id, String name, long partsCount) {
        this.id = id;
        this.name = name;
        this.partsCount = partsCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(long partsCount) {
        this.partsCount = partsCount;
    }
}