package org.example.interfaces;

import java.util.Date;

public interface Repository<T> {

    boolean create(T o);

    boolean update(T o);

    boolean delete(T o);

    T findById(int id);

     void find();
    void findWherePriceSupp(double prix);

    void findWhereBetweenDate(Date dateMin, Date dateMax);

    void findWhereStockInferieur(int stock);
}
