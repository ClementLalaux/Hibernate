package org.example.services;

import org.example.entities.Commentaire;
import org.example.entities.Produit;
import org.example.interfaces.Repository;
import org.hibernate.query.Query;

import java.util.List;

public class CommentaireService extends BaseService implements Repository<Commentaire> {



    @Override
    public boolean create(Commentaire o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Commentaire o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Commentaire o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Commentaire findById(int id) {
        Commentaire commentaire = null;
        session = sessionFactory.openSession();
        commentaire = (Commentaire) session.get(Commentaire.class, id);
        session.close();
        return commentaire;
    }

    @Override
    public List<Commentaire> find() {
        session = sessionFactory.openSession();
        Query<Commentaire> commentaireQuery = session.createQuery("from Commentaire ");
        List<Commentaire> commentaires = commentaireQuery.list();
        session.close();
        return commentaires;
    }
}
