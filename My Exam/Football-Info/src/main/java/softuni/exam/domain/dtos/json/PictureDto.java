package softuni.exam.domain.dtos.json;

import com.google.gson.annotations.Expose;

public class PictureDto {
    @Expose
    private String url;

    public PictureDto() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}