import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Exo ArrayList
//        ArrayList<CompteBancaire> listeComptes = new ArrayList<>();
//        listeComptes.add(new CompteCourant("Alice",200,300));
//        listeComptes.add(new CompteCourant("Bob",500,300));
//        listeComptes.add(new CompteCourant("Charlie",1000,500));
//
//        for (CompteBancaire compte : listeComptes){
//            compte.deposer(10);
//        }
//        for (CompteBancaire compte : listeComptes){
//            compte.afficherSolde();
//        }
//
        //Banque
        Banque banque = new Banque();
        banque.ajouterCompte(new CompteCourant("Alice",200,300));
        banque.ajouterCompte(new CompteCourant("Bob",500,300));
        banque.ajouterCompte(new CompteCourant("Charlie",1000,500));
        banque.afficherTout();

        //Exo Heritage/Encapsulation
//        CompteEpargne compteEp = new CompteEpargne("Picsou",1000,0.05);
//        compteEp.afficherSolde();
//        compteEp.appliquerInterets();
//        compteEp.afficherSolde();

        //Exo Polymorphisme/Override
        //listeComptes.add(compteEp);

//        for (CompteBancaire compte: listeComptes){
//            compte.afficherSolde();
//        }

//        CompteCourant compteAlice = (CompteCourant) listeComptes.get(0);
//        //compteAlice.retirer(300);
//        compteAlice.afficherSolde();
//        compteEp.afficherSolde();
//        //compteAlice.retirer(300);
//
//        System.out.println("--- Tentative de Virement ---");
//        compteAlice.effectuerVirement(compteEp, 50);
//        compteAlice.afficherSolde();
//        compteEp.afficherSolde();

        //Virement plus propre
        CompteBancaire compteAl = banque.rechercherCompte("1");
        CompteBancaire compteBob = banque.rechercherCompte("2");

        if(compteAl instanceof CompteCourant){
            ((CompteCourant) compteAl).effectuerVirement(compteBob, 40);

        }else{
            System.err.println("Opération impossible pour ce type de compte");
        }
        compteAl.afficherSolde();
        compteBob.afficherSolde();
        //Virement encore mieux
        banque.virer("2","1",100);
        compteAl.afficherSolde();
        compteBob.afficherSolde();
        //Initiation Exceptions

        banque.virer("1","2",10000);
        compteAl.afficherSolde();

        System.out.println("Ajout nouveau compte Alice");

        banque.ajouterCompte(new CompteCourant("Alice",200,300));


        List<CompteBancaire> comptesAlice = banque.rechercherComptesParNom("alice");

        System.out.println("Afficher les comptes Alice");

        banque.afficherComptesSelonListe(comptesAlice);


    }
}