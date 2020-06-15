package uz.marokand.market.dto;

import uz.marokand.market.entity.OrderDetails;
import uz.marokand.market.entity.Product;

import java.util.List;

public class OrderDto {
    private List<Product> productList;
    private Integer quantity;
    private String username;
    private Long productId;


    public OrderDto() {

    }

    public OrderDto(List<Product> productList, Integer quantity, String username, Long productId) {
        this.productList = productList;
        this.quantity = quantity;
        this.username = username;
        this.productId = productId;
    }

    public OrderDto(List<Product> productList) {
        this.productList = productList;
    }



    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
