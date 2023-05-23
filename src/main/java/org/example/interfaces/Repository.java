package org.example.interfaces;

import org.example.entities.Produit;

import java.util.Date;
import java.util.List;

public interface Repository<T> {

    boolean create(T o);

    boolean update(T o);

    boolean delete(T o);

    T findById(int id);

    List<T> find();
    List<T> findWherePriceSupp(double prix);

    List<T> findWhereBetweenDate(Date dateMin, Date dateMax);

    List<T> findWhereStockInferieur(int stock);

    double findPriceByBrand(String brand);

    double findAveragePrice();

    List<T> findProductByBrand(String brand);
}
