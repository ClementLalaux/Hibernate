package org.example.services;

import org.example.entities.Produit;
import org.example.interfaces.Repository;
import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProduitService extends BaseService implements Repository<Produit> {

    public ProduitService(){
        super();
    }

    @Override
    public boolean create(Produit o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Produit o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
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
    public void find() {
        session = sessionFactory.openSession();
        Query<Produit> produitQuery = session.createQuery("from Produit");
        List<Produit> produits = produitQuery.list();
        for(Produit p : produits){
            System.out.println(p.getMarque());
        }
        session.close();
    }

    public void findWherePriceSupp(double prix){
        session = sessionFactory.openSession();
        Query<Produit> produitQuery = session.createQuery("from Produit where prix> ?1");
        produitQuery.setParameter(1,prix);
        List<Produit> produits = produitQuery.list();
        for(Produit p : produits){
            System.out.println(p.getMarque());
        }
        session.close();
    }

    public void findWhereBetweenDate(Date dateMin, Date dateMax){
        session = sessionFactory.openSession();
        Query<Produit> produitQuery = session.createQuery("from Produit where dateAchat BETWEEN ?1 and ?2");
        produitQuery.setParameter(1,dateMin);
        produitQuery.setParameter(2,dateMax);
        List<Produit> produits = produitQuery.list();
        for(Produit p : produits){
            System.out.println(p.getMarque());
        }
        session.close();
    }


    @Override
    public void findWhereStockInferieur(int stock) {
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

        for (Produit p :produitList) {
            System.out.println(p.getId() + " : " + p.getReference());
        }
        session.close();

    }
}
