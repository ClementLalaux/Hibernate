package org.example.services;

import org.example.entities.Commentaire;
import org.example.entities.Image;
import org.example.interfaces.Repository;
import org.hibernate.query.Query;

import java.util.List;

public class ImageService extends BaseService implements Repository<Image> {

    public ImageService() {
        super();
    }

    @Override
    public boolean create(Image o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;    }

    @Override
    public boolean update(Image o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Image o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Image findById(int id) {
        Image image = null;
        session = sessionFactory.openSession();
        image = (Image) session.get(Image.class, id);
        session.close();
        return image;
    }

    @Override
    public List<Image> find() {
        session = sessionFactory.openSession();
        Query<Image> imageQuery = session.createQuery("from Image ");
        List<Image> images = imageQuery.list();
        session.close();
        return images;
    }
}
