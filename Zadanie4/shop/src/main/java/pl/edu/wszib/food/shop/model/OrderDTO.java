package pl.edu.wszib.food.shop.model;

import pl.edu.wszib.food.shop.model.Order;

import java.time.LocalDateTime;

public class OrderDTO {
    private int id;
    private LocalDateTime date;
    private double price;
    String user;

    public OrderDTO(int id, LocalDateTime date, double price, String user) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.user = user;
    }

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.date = order.getDate();
        this.price = order.getPrice();
        this.user = "localhost:8080/user/" + order.getUser().getId();
    }

    public OrderDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
