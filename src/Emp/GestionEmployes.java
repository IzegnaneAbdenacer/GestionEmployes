package Emp;

import java.util.Scanner;

public class GestionEmployes {
    static Employe[] employes = new Employe[50];

    public static void printMenu() {
        System.out.println("____Gestion des employes____");
        System.out.println("1.Ajouter un employe");
        System.out.println("2.Modifier un employe");
        System.out.println("3.Supprimer un employe");
        System.out.println("4.Afficher la liste des employes");
        System.out.println("5.Rechercher un employe");
        System.out.println("6.Calculer la masse salairiale");
        System.out.println("7.Trier les employes");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix = 0;
        do {
            printMenu();
            System.out.println("Entrer votre choix");
            choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1:
                    Employe emp = saisisEmploye();
                    int i = 0;
                    if (employes[i] != null && emp.getId() == employes[i].getId()) {
                        System.out.println("Impossble d'ajouter cet employe, existe deja");
                    } else
                        ajouterEmploye(emp);
                    break;
                case 2:
                   Scanner sc1 = new Scanner(System.in);
                    System.out.println("Entrer l'ID d'employe à modifier");
                    int id = sc1.nextInt();
                    System.out.println("Entrer le nouveau nom");
                    String nouveauNom = sc1.next();
                    sc1.nextLine();
                    System.out.println("Entrer le nouveau poste");
                    String nouveauPoste = sc1.next();
                    sc1.nextLine();
                    System.out.println("Entrer le nouveau salaire");
                    Double nouveauSalaire = sc1.nextDouble();
                    modifierEmploye(id,nouveauNom,nouveauPoste,nouveauSalaire);
                    break;
                case 3:
                    int id1 = 0;
                    Scanner sc = new Scanner(System.in);
                    System.out.println("entrer l'id d'employe à supprimer");
                    id1 = sc.nextInt();
                    sc.nextLine();
                    supprimerEmployes(id1);
                    System.out.println("Employe " + id1 + " est  supprime avec succes");
                    break;
                case 4:
                    System.out.println("Liste des employes");
                    afficherEmployes();
                    break;
                case 5:
                    rechercherEmploye();
                    break;
                case 6 :
                    calculerMasseSalaire();
                    break;
                case 7:
                    trierEmployeeParSalaire();
                    break;
            }

        } while (choix != 0);
        scanner.close();
    }

     static void trierEmployeeParSalaire() {
        Scanner sc3 = new Scanner(System.in);
         System.out.print("Trier par ordre (1: croissant, 2: décroissant): ");
         int ordre = sc3.nextInt();
         sc3.nextLine();
         trierEmployes(ordre == 1);
         afficherEmployes();
    }

     static void trierEmployes(boolean ordreCroissant) {
         for (int i = 0; i < employes.length - 1; i++) {
             for (int j = i + 1; j < employes.length; j++) {
                 if (employes[i] != null && employes[j] != null) {
                     if ((ordreCroissant && employes[i].getSalaire() > employes[j].getSalaire()) ||
                             (!ordreCroissant && employes[i].getSalaire() < employes[j].getSalaire())) {

                         // Échange des employés
                         Employe temp = employes[i];
                         employes[i] = employes[j];
                         employes[j] = temp;
                     }
                 }
             }
         }
    }

    static void rechercherEmploye() {
        Scanner sc2 = new Scanner(System.in);
         System.out.print("Entrez un nom ou un poste: ");
         String critere = sc2.nextLine().toLowerCase();
         for (Employe employe : employes) {
             if ( employe!=null &&(employe.getNom().toLowerCase().contains(critere) || employe.getPoste().toLowerCase().contains(critere))) {
                 System.out.println(employe);
             }
         }
    }

    static void modifierEmploye(int id, String nouveauNom, String nouveauPoste, Double nouveauSalaire) {
    for (Employe employe : employes){
        if (employe!=null && employe.getId()==id){
            employe.setNom(nouveauNom);
            employe.setPoste(nouveauPoste);
            employe.setSalaire(nouveauSalaire);
        }
    }
    }

    public static void ajouterEmploye(Employe emp) {
        for (Employe empl : employes) {
            if (empl != null && empl.getId() == emp.getId()) {//Verification d'inicite d'employe
                System.out.println("Impossble d'ajouter cet employe, existe deja");
            }
        }
        for (int i = 0; i < employes.length; i++) {
            if (employes[i] == null) {
                employes[i] = emp;
                System.out.println("Employe ajoute avec succes");
                return;
            }
        }
        System.out.println("Tableau est plein");
    }
    static void supprimerEmployes(int id1) {
        for (int i = 0; i < employes.length; i++) {
            if (employes[i] != null && employes[i].getId() == id1) {
                employes[i] = null;
            }
        }
    }

    static void afficherEmployes() {
        for (Employe emp : employes) {
            if (emp != null) {
                System.out.println(emp);
            }
        }
    }

    static Employe saisisEmploye() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter l'ID d'employe");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter le nom d'employe");
        String nom = scanner.nextLine();
        System.out.println("Enter le poste d'employe");
        String poste = scanner.nextLine();
        System.out.println("Enter le salaire d'employe");
        double salaire = scanner.nextDouble();
        scanner.nextLine();
        return new Employe(id, nom, poste, salaire);
    }
    static void calculerMasseSalaire() {
        double somme = 0;
        for (Employe employe : employes) {
            if (employe != null) {
                somme += employe.getSalaire();
            }
        }
        System.out.println("Masse salariale totale: " + somme);
    }
}
