import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        var console = System.in;
        Scanner scannerConsole= new Scanner(console);

        Banque banque = new Banque();

        var enFonction= true;

        while (enFonction){
            afficherMenu();
            int i = scannerConsole.nextInt();
            scannerConsole.nextLine();

            switch (i){
                case 1 :
                    creationCompte(scannerConsole,banque);
                    break;
                case 2 :
                    break;

                case 3 :
                    break;

                case 4 :
                    break;

                case 5 :
                    enFonction=false;
                    break;

            }

        }


    }

    private static void afficherMenu(){
        System.out.println("\n=== MENU BANQUE ===");
        System.out.println("1. Créer un compte");
        System.out.println("2. Afficher un compte (par numéro)");
        System.out.println("3. Créer un virement");
        System.out.println("4. Rechercher des comptes par nom");
        System.out.println("5. Quitter");
        System.out.print("Votre choix : ");
    }
    public static void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void creationCompte(Scanner scannerConsole,Banque banque){
        clear();
        System.out.println("Quel est votre nom :");
        String titu = scannerConsole.nextLine();
        clear();
        System.out.println("Quel est le montant de votre premier depot : ");
        double solde = scannerConsole.nextDouble();
        scannerConsole.nextLine();
        clear();
        System.out.println("1. Compte courant taper/ 2. Compte epargne");
        System.out.println("Votre choix : ");
        int i1 = scannerConsole.nextInt();
        scannerConsole.nextLine();
        clear();
        if(i1==1){
            System.out.println("Votre decouvert autorise sera de :\n1. 200/ 2. 400");
            int decouvert = scannerConsole.nextInt();
            if(decouvert==1){
                decouvert=200;
            } else if (decouvert==2) {
                decouvert=400;
            }else {
                throw new RuntimeException("Chiffre non valide");
            }
            scannerConsole.nextLine();
            banque.ajouterCompte(new CompteCourant(titu,solde,decouvert));
            afficherUnCompte(banque, titu);

        } else if (i1==2) {
            System.out.println("Taux d'interet (ex: 0.05): ");
            double taux = scannerConsole.nextDouble();
            scannerConsole.nextLine();
            banque.ajouterCompte(new CompteEpargne(titu,solde,taux));
            afficherUnCompte(banque, titu);

        }else{
            throw new RuntimeException("Chiffre non valide");
        }
        System.out.println("Compte cree !");

    }

    private static void afficherUnCompte(Banque banque, String titu) {
        System.out.println("Voici le recap de vos comptes :");
        var compte = banque.rechercherComptesParNom(titu);
        banque.afficherComptesSelonListe(compte);
    }

}