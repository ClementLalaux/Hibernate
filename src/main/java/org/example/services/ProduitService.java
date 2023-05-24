package org.example.services;

import org.example.entities.Produit;
import org.example.interfaces.Repository;
import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class  ProduitService extends BaseService implements Repository<Produit> {



    @Override
    public boolean create(Produit o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Produit o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Produit o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Produit findById(int id) {
        Produit produit = null;
        session = sessionFactory.openSession();
        produit = (Produit) session.get(Produit.class, id);
        session.close();
        return produit;
    }

    @Override
    public List<Produit> find() {
        session = sessionFactory.openSession();
        Query<Produit> produitQuery = session.createQuery("from Produit");
        List<Produit> produits = produitQuery.list();
        session.close();
        return produits;
    }

    public List<Produit> findWherePriceSupp(double prix){
        session = sessionFactory.openSession();
        Query<Produit> produitQuery = session.createQuery("from Produit where prix> ?1");
        produitQuery.setParameter(1,prix);
        List<Produit> produits = produitQuery.list();
        session.close();
        return produits;
    }

    public List<Produit> findWhereBetweenDate(Date dateMin, Date dateMax){
        session = sessionFactory.openSession();
        Query<Produit> produitQuery = session.createQuery("from Produit where dateAchat BETWEEN ?1 and ?2");
        produitQuery.setParameter(1,dateMin);
        produitQuery.setParameter(2,dateMax);
        List<Produit> produits = produitQuery.list();
        session.close();
        return produits;
    }


    public List<Produit> findWhereStockInferieur(int stock) {
        session = sessionFactory.openSession();
        Query<Produit> produitQuery = session.createQuery("SELECT id, reference from Produit as p where p.stock < ?1");
        produitQuery.setParameter(1,stock);
        List result = produitQuery.list();

        List<Produit> produitList = new ArrayList<>();

        for (Object o: result) {
            Object[] res = (Object[]) o;
            Produit pr = new Produit();
            pr.setId((int) res[0]);
            pr.setReference((String) res[1]);
            produitList.add(pr);
        }
        session.close();
        return produitList;
    }

    public double findPriceByBrand(String brand) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("select sum(prix*stock) from Produit where marque = ?1");
        query.setParameter(1,brand);
        double price = (double) query.uniqueResult();
        session.close();
        return price;
    }

    public double findAveragePrice() {
        session = sessionFactory.openSession();
        Query query = session.createQuery("select avg(prix) from Produit");
        double avg = (double) query.uniqueResult();
        session.close();
        return avg;
    }

    public List<Produit> findProductByBrand(String brand){
        session = sessionFactory.openSession();
        Query<Produit> produitQuery = session.createQuery("from Produit where marque = ?1");
        produitQuery.setParameter(1,brand);
        List<Produit> produits = produitQuery.list();
        session.close();
        return produits;
    }


    public boolean deleteByBrand(String brand) {
        session = sessionFactory.openSession();
        String delete_query = "delete Produit where marque=?1";
        Query query = session.createQuery(delete_query);
        query.setParameter(1,brand);
        session.getTransaction().begin();
        int success = query.executeUpdate(); // C'est le nombre de ligne affectée par la requete
        session.getTransaction().commit();
        if(success>0){
            return true;
        } else {
            return false;
        }
    }

    public List<Produit> findProductByCommentNote(){
        session = sessionFactory.openSession();
        Query<Produit> produitQuery = session.createQuery("from Produit as p where Commentaire.note > 4 GROUP BY p.reference");
        List<Produit> produits = produitQuery.list();
        session.close();
        return produits;
    }
}
