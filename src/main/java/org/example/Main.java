package org.example;

import org.example.entities.Produit;
import org.example.services.ProduitService;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        // Exercice 1

        // Creation des produits
        ProduitService ps = new ProduitService();
        ps.create(new Produit("TOSHIBA","zzaa123",new Date("2016/01/08"),6000,100));
        ps.create(new Produit("HP","EER678",new Date("2016/02/09"),2000,150));
        ps.create(new Produit("SONY","AQWZSX",new Date("2016/09/23"),6000,200));
        ps.create(new Produit("DELL","AZERTY",new Date("2016/02/12"),6000,300));
        ps.create(new Produit("SONY","qsdERT",new Date("2016/02/02"),6000,400));

        // Informations produit id = 2

        Produit p = ps.findById(2);
        System.out.println(p);

        // Supprimer le produit id = 3
        ps.delete(ps.findById(3));

        // Modifier produit id = 1

        p = ps.findById(1);
        if(p != null){
            p.setMarque("HP");
            p.setReference("MMMMPPP");
            p.setDateAchat(new Date("2015/09/08"));
            p.setPrix(5000);
            ps.update(p);
        }

        //ps.find();

        //ps.findWherePriceSupp(5001);

        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez une première date au format : 2015/09/08");
        String dateUn = sc.nextLine();
        System.out.println("Entrez une deuxième date au format : 2015/09/08");
        String dateDeux = sc.nextLine();

        ps.findWhereBetweenDate(new Date(dateUn),new Date(dateDeux));


        System.out.println("Entrez une valeur de stock");
        int stock = sc.nextInt();
        sc.nextLine();

        ps.findWhereStockInferieur(stock);


    }
}