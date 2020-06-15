package uz.marokand.market.dto;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public class ProductDto {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    @NumberFormat
    private Double price;
    @NotNull
    private MultipartFile multipartFile;
    @NotNull
    private String category;

    public ProductDto(@NotNull String name, @NotNull String description, @NotNull Double price, @NotNull MultipartFile multipartFile, @NotNull String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.multipartFile = multipartFile;
        this.category = category;
    }

    public ProductDto() {
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
