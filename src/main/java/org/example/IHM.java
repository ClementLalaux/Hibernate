package org.example;

import org.example.entities.Commentaire;
import org.example.entities.Image;
import org.example.entities.Produit;
import org.example.services.CommentaireService;
import org.example.services.ImageService;
import org.example.services.ProduitService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class IHM {
    Scanner scanner;
    ProduitService produitService = new ProduitService();
    ImageService imageService = new ImageService();
    CommentaireService commentaireService = new CommentaireService();
    String choix;

    public IHM(){
        scanner = new Scanner(System.in);
    }

    public void start(){
        do {
            menu();
            choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    createFonction();
                    break;
                case "2":
                    deleteFonction();
                    break;
                case "3":
                    updateFonction();
                    break;
                case "4":
                    findByIdFonction();
                    break;
                case "5":
                    findFonction();
                    break;
                case "6":
                    findWherePriceSuppFonction();
                    break;
                case "7":
                    findWhereBetweenDateFunction();
                    break;
                case "8":
                    findWhereStockInferieurFonction();
                    break;
                case "9":
                    findPriceByBrandFonction();
                    break;
                case "10":
                    findAveragePriceFonction();
                    break;
                case "11":
                    findProductByBrandFonction();
                    break;
                case "12":
                    deleteByBrand();
                    break;
                case "13":
                    ajouterCommentaireFonction();
                    break;
                case "14":
                    ajouterImageFunction();
                    break;
                case "15":
                    findProductByCommentNoteFonction();
            }
        }while (!choix.equals("0"));
    }

    private void menu(){
        System.out.println("1 - Ajouter un produit");
        System.out.println("2 - Supprimer un produit");
        System.out.println("3 - Modifier un produit");
        System.out.println("4 - Afficher un produit par l'id");
        System.out.println("5 - Afficher tout les produits");
        System.out.println("6 - Afficher les produits ou le prix est sup");
        System.out.println("7 - Afficher les produits compris entre deux dates");
        System.out.println("8 - Afficher les produits ou le stock est inferieur");
        System.out.println("9 - Afficher la valeur du stock des produits d'un marque choisie");
        System.out.println("10 - Afficher la valeur moyenne des prix");
        System.out.println("11 - Afficher les produits d'une marque");
        System.out.println("12 - Supprimer les produits d'une marque");
        System.out.println("13 - Ajouter un commentaire et l'associer a un produit");
        System.out.println("14 - Ajouter une image et l'associer a un produit");
        System.out.println("15 - Chercher les produits avec note supérieur a 4");
        System.out.println("0 - EXIT");
    }

    public void createFonction(){
        System.out.println("Entrez une marque");
        String marque = scanner.nextLine();
        System.out.println("Entrez une reference");
        String reference = scanner.nextLine();
        System.out.println("Entrez une date d'achat format (2016/01/08)");
        String dateAchat = scanner.nextLine();
        System.out.println("Entrez un prix");
        Double prix = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Entrez un stock");
        int stock = scanner.nextInt();
        scanner.nextLine();

        Produit produit = new Produit(marque,reference,new Date(dateAchat),prix,stock);

        produitService.create(produit);
    }

    public void findByIdFonction(){
        System.out.println("Entrez l'id a rechercher");
        int id = scanner.nextInt();
        scanner.nextLine();
        Produit produit = produitService.findById(id);
        if(produit != null){
            System.out.println(produit);
        }
    }

    public void deleteFonction(){
        System.out.println("Entrez l'id a supprimer");
        int id = scanner.nextInt();
        scanner.nextLine();
        Produit produit = produitService.findById(id);
        if(produit != null){
            produitService.delete(produit);
        }
    }

    public void updateFonction(){
        System.out.println("Entrez l'id a modifier");
        int id = scanner.nextInt();
        scanner.nextLine();
        Produit produit = produitService.findById(id);
        if(produit != null){
            produitService.update(produit);
        }
    }

    public void findFonction(){
        List<Produit> produits = produitService.find();
        for(Produit p : produits){
            System.out.println(p.getMarque());
        }
    }

    public void findWherePriceSuppFonction(){
        System.out.println("Entrez un prix");
        int prix = scanner.nextInt();
        scanner.nextLine();
        List<Produit> produits = produitService.findWherePriceSupp(prix);
        for(Produit p : produits){
            System.out.println(p.getMarque());
        }
    }

    public void findWhereBetweenDateFunction(){
        System.out.println("Entrez une première la date (la plus basse) au format : 2015/09/08");
        String dateBasse = scanner.nextLine();
        System.out.println("Entrez une deuxième la date (la plus haute) au format : 2015/09/08");
        String dateHaute = scanner.nextLine();
        List<Produit> produits = produitService.findWhereBetweenDate(new Date(dateBasse),new Date(dateHaute));
        for(Produit p : produits){
            System.out.println(p.getMarque());
        }
    }

    public void findWhereStockInferieurFonction(){
        System.out.println("Entrez un prix");
        int stock = scanner.nextInt();
        scanner.nextLine();
        List<Produit> produitList = produitService.findWhereStockInferieur(stock);
        for (Produit p :produitList) {
            System.out.println(p.getId() + " : " + p.getReference());
        }
    }

    public void findPriceByBrandFonction(){
        System.out.println("Entrez une marque");
        String brand = scanner.nextLine();
        double somme = produitService.findPriceByBrand(brand);
        System.out.println("La somme est de " + somme);
    }

    public void findAveragePriceFonction(){
        System.out.println(produitService.findAveragePrice());
    }

    public void findProductByBrandFonction(){
        System.out.println("Entrez une marque");
        String brand = scanner.nextLine();
        List<Produit> produits = produitService.findProductByBrand(brand);
        for (Produit p :produits) {
            System.out.println(p.getId() + " : " + p.getReference());
        }
    }

    public void deleteByBrand(){
        System.out.println("Entrez une marque");
        String brand = scanner.nextLine();
        if(produitService.deleteByBrand(brand)){
            System.out.println("La marque " + brand + " et ses produits à été supprimé");
        } else {
            System.out.println("Pas marché");
        }
    }

    public void ajouterCommentaireFonction(){
        System.out.println("Entrez l'id du produit à laquelle associer le commentaire");
        int id = scanner.nextInt();
        scanner.nextLine();
        Produit produit = produitService.findById(id);
        if(produit != null){
            System.out.println("Entrez un message");
            String message = scanner.nextLine();
            System.out.println("Entrez une note");
            int note = scanner.nextInt();
            scanner.nextLine();
            Commentaire commentaire = new Commentaire();
            commentaire.setContenu(message);
            commentaire.setNote(note);
            commentaire.setProduit(produit);
            commentaireService.create(commentaire);
        }
    }

    public void ajouterImageFunction(){
        System.out.println("Entrez l'id du produit à laquelle associer l'image");
        int id = scanner.nextInt();
        scanner.nextLine();
        Produit produit = produitService.findById(id);
        if(produit != null){
            System.out.println("Entrez une url");
            String message = scanner.nextLine();
            Image image = new Image();
            image.setUrl(message);
            image.setProduitUn(produit);
            imageService.create(image);
        }
    }

    public void findProductByCommentNoteFonction(){
        List<Produit> produits = produitService.findProductByCommentNote();
        for (Produit i :produits) {
            System.out.println(i);
        }
    }

}
