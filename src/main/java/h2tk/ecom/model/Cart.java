package h2tk.ecom.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int productId;
    private String name;
    private Double price;
    private int quantity = 1;
    private String image;

    public Cart() {
    }

    public Cart(int productId, String name, Double price, int quantity, String image) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double totalMoney() {
        return getPrice() * getQuantity();
    }


}
