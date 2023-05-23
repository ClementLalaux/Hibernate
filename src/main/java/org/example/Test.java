package org.example;

import org.example.entity.Produit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        StandardServiceRegistry registre = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registre).buildMetadata().buildSessionFactory();

        // Création de la session
        Session session = sessionFactory.openSession();

        session.getTransaction().begin();
        Produit p = new Produit();
        Produit p2 = new Produit();
        Produit p3 = new Produit();
        Produit p4 = new Produit();
        Produit p5 = new Produit();
        Produit p6 = new Produit();

        p.setStock(100);
        p.setMarque("UHU");
        p.setPrix(25);
        p.setReference("Colle fixation");
        p.setDateAchat(LocalDate.parse("2023-05-05"));

        p2.setStock(2000);
        p2.setMarque("papier");
        p2.setPrix(1);
        p2.setReference("canson");
        p2.setDateAchat(LocalDate.parse("2023-09-09"));

        p3.setStock(3);
        p3.setMarque("UHU");
        p3.setPrix(2500000);
        p3.setReference("Ferrari");
        p3.setDateAchat(LocalDate.parse("2023-10-10"));

        p4.setStock(99);
        p4.setMarque("Pomme");
        p4.setPrix(3);
        p4.setReference("de reinette");
        p4.setDateAchat(LocalDate.parse("2023-04-04"));

        p5.setStock(101);
        p5.setMarque("UH");
        p5.setPrix(21);
        p5.setReference("fixation");
        p5.setDateAchat(LocalDate.parse("2023-06-06"));

        session.save(p);
        session.save(p2);
        session.save(p3);
        session.save(p4);
        session.save(p5);

        session.close();
        sessionFactory.close();

    }

}
