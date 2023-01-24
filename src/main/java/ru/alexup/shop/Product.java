package ru.alexup.shop;

import ru.alexup.client.Client;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "goods")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "title")
    protected String title;

    @Column(name = "price")
    protected int price;

    @ManyToMany
    @JoinTable(
            name = "goods_clients",
            joinColumns = @JoinColumn(name = "goods_id"),
            inverseJoinColumns = @JoinColumn(name = "clients_id")
    )
    protected List<Client> clients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Product() {
    }

    public Product(String title, int price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product: " + id + ". " + title + ", " + price + " cr." ;
    }
}
