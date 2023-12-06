package entity;

import java.util.Objects;

public class Item {
    private Long id;
    private Long orderId;
    private Long userId;
    private String name;
    private int quantity;
    private Long cost;
    private Long price;

    public Item(Long id, Long orderId, Long userId, String name, int quantity, Long cost, Long price) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
        this.price = price;
    }

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return quantity == item.quantity && Objects.equals(id, item.id) && Objects.equals(orderId, item.orderId) && Objects.equals(userId, item.userId) && Objects.equals(name, item.name) && Objects.equals(cost, item.cost) && Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, userId, name, quantity, cost, price);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", cost=" + cost +
                ", price=" + price +
                '}';
    }
}
