package h2tk.ecom.model;

import java.sql.Date;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "paid")
    private boolean pain;

    @Column(name = "delivered")
    private boolean delivered;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "delivery_date")
    private Date deliveryDate;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    Users User;

    @OneToMany(mappedBy = "order")
    Set<OrderProduct> orderProduct;  

    public Orders() {
    }

    public Orders(int id, boolean pain, boolean delivered, Date orderDate, Date deliveryDate, Users user) {
        this.id = id;
        this.pain = pain;
        this.delivered = delivered;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        User = user;
    }

    public Users getUser() {
        return User;
    }

    public void setUser(Users user) {
        User = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPain() {
        return pain;
    }

    public void setPain(boolean pain) {
        this.pain = pain;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Set<OrderProduct> getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(Set<OrderProduct> orderProduct) {
        this.orderProduct = orderProduct;
    }
}
