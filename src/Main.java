import java.util.Locale;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        var console = System.in;
        Scanner scannerConsole= new Scanner(console);
        scannerConsole.useLocale(Locale.US);

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
                    afficherCompte(scannerConsole,banque);

                    break;

                case 3 :
                    faireVirement(scannerConsole,banque);
                    break;

                case 4 :
                    enFonction=false;
                    break;

            }

        }


    }

    private static void afficherMenu(){
        System.out.println("\n=== MENU BANQUE ===");
        System.out.println("1. Créer un compte");
        System.out.println("2. Afficher un compte ou des comptes");
        System.out.println("3. Créer un virement");
        System.out.println("4. Quitter");
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
        scannerConsole.nextLine();
        clear();
        int i1 =0;
        while(i1 !=1 && i1!=2){
            i1 = scannerConsole.nextInt();

            if(i1==1){

            System.out.println("Votre decouvert autorise sera de :\n1. 200/ 2. 400");
            int decouvert = scannerConsole.nextInt();
            while (decouvert !=1 && decouvert!=2){
                scannerConsole.nextLine();
                System.out.println("Chiffre non valide");
                decouvert=scannerConsole.nextInt();
            }
            scannerConsole.nextLine();
            if(decouvert==1){
                decouvert=200;
            } else if (decouvert==2) {
                decouvert=400;
            }
            banque.ajouterCompte(new CompteCourant(titu,solde,decouvert));
            afficherlesComptes(banque, titu);

        } else if (i1==2) {
            System.out.println("Taux d'interet (ex: 0.05): ");
            double taux = scannerConsole.nextDouble();
            while(taux<0.0 || taux>1.0){
                scannerConsole.nextLine();
                System.out.println("Chiffre non valide");
                taux = scannerConsole.nextDouble();
            }
            scannerConsole.nextLine();
            banque.ajouterCompte(new CompteEpargne(titu,solde,taux));
            afficherlesComptes(banque, titu);
        }else{
            System.err.println("Chiffre non valide");
        }}

        System.out.println("Compte cree !");

    }

    private static void afficherCompte(Scanner scannerConsole, Banque banque){
        System.out.println("1. J'ai mon numero de compte / 2. Je n'ai pas mon numero de compte");
        int i = scannerConsole.nextInt();
        scannerConsole.nextLine();
        if(i==1){
            System.out.println("Entrez votre numero de compte :");
            var numCompte = scannerConsole.nextLine();
            try{
            afficherUnCompte(banque,numCompte);
            }catch (CompteInexistant e){
                System.err.println(e.getMessage());
            }

        } else if (i==2) {
            System.out.println("Entrez votre nom :");
            var nomCompte = scannerConsole.nextLine();
            try{
                afficherlesComptes(banque,nomCompte);
            }catch(CompteInexistant e){
                System.err.println(e.getMessage());
            }


        }else{
            System.out.println("Chiffre non valide");
        }

    }

    private static void afficherlesComptes(Banque banque, String titu) {
        System.out.println("Voici le recap de vos comptes :");
        var comptes = banque.rechercherComptesParNom(titu);
        banque.afficherComptesSelonListe(comptes);
    }
    private static void afficherUnCompte(Banque banque, String numeroCompte) {
        System.out.println("Voici le detail de votre compte: ");
        var compte = banque.rechercherCompte(numeroCompte);
        compte.afficherSolde();
    }


    private static void faireVirement(Scanner scannerConsole,Banque banque){
        System.out.println("A quel compte souhaitez vous faire le virement ? [numero de compte]");
        var numeroCompteCredite= scannerConsole.nextLine();
        System.out.println("Depuis quel compte souhaitez vous faire le virement ? [numero de compte]");
        var numeroCompteDebite= scannerConsole.nextLine();
        System.out.println("Quel est le montant que vous souhaitez virer");
        var montant = scannerConsole.nextDouble();
        scannerConsole.nextLine();
        try {
            banque.virer(numeroCompteDebite,numeroCompteCredite,montant);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }


    }