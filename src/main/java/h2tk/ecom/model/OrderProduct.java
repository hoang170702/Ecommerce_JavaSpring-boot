package h2tk.ecom.model;

import jakarta.persistence.*;

@Entity
@Table(name = "OrderProduct")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Products product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Orders order;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "unit_price")
    private double unitPrice;

    public OrderProduct() {
    }

    public OrderProduct(int id, Products product, Orders order, double quantity, double unitPrice) {
        this.id = id;
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
