package ru.alexup.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.alexup.client.Client;
import ru.alexup.shop.Product;

import java.util.List;

public class DataBase {

    SessionFactory factory = new Configuration()
            .addAnnotatedClass(Client.class)
            .addAnnotatedClass(Product.class)
            .buildSessionFactory();

    Session session = null;

    public <T> void addToDb(List<T> entities) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        for (T t : entities) {
            System.out.println(t);
            session.save(t);
            System.out.println(t);
        }
        session.getTransaction().commit();
    }

    public void removeClient(String name) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Client clientFromDb = session.createQuery("SELECT i FROM Client i WHERE i.name = '" + name + "'",
                Client.class).getSingleResult();
        session.remove(clientFromDb);
        System.out.println(clientFromDb.getName() + " deleted from DB");
        session.getTransaction().commit();
    }

    public void removeProduct(String title) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Product productFromDb = session.createQuery("SELECT i FROM Product i WHERE i.title = '" + title + "'",
                Product.class).getSingleResult();
        session.remove(productFromDb);
        System.out.println(productFromDb.getTitle() + " deleted from DB");
        session.getTransaction().commit();
    }

    public void insertIntoGoodsClients(String clientsName, String productsTitle) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Client clientFromDb = session.createQuery("SELECT i FROM Client i WHERE i.name = '" + clientsName + "'",
                Client.class).getSingleResult();
        Product productFromDb = session.createQuery("SELECT i FROM Product i WHERE i.title = '" + productsTitle + "'",
                        Product.class).getSingleResult();
        System.out.println(clientFromDb);
        System.out.println(productFromDb);
        clientFromDb.getProducts().add(productFromDb);
        session.getTransaction().commit();
    }

    public void showProductsByClient(String name) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Client clientFromDb = session.createQuery("SELECT i FROM Client i WHERE i.name = '" + name + "'",
                Client.class).getSingleResult();
        if (clientFromDb.getProducts().isEmpty()) {
            System.out.println(name + " has no products yet");
        } else {
            System.out.println(clientFromDb + "'s products:");
            for (Product p : clientFromDb.getProducts())
                System.out.println(p);
        }
        session.getTransaction().commit();
    }

    public void findClientsByProductTitle (String title) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Product productFromDb = session.createQuery("SELECT i FROM Product i WHERE i.title = '" + title + "'",
                        Product.class).getSingleResult();
        if (productFromDb.getClients().isEmpty()) {
            System.out.println(title + " has no sales yet");
        } else {
            System.out.println("Clients who purchased " + title + ":");
            for (Client c : productFromDb.getClients()) {
                System.out.println(c);
            }
        }
        session.getTransaction().commit();
    }

    public void closeFactory () {
        factory.close();
    }
}
