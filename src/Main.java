import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Exo ArrayList
        ArrayList<CompteBancaire> listeComptes = new ArrayList<>();
        listeComptes.add(new CompteCourant("Alice",200,300));
        listeComptes.add(new CompteCourant("Bob",500,300));
        listeComptes.add(new CompteCourant("Charlie",1000,500));

        for (CompteBancaire compte : listeComptes){
            compte.deposer(10);
        }
        for (CompteBancaire compte : listeComptes){
            compte.afficherSolde();
        }

        //Exo Heritage/Encapsulation
        CompteEpargne compteEp = new CompteEpargne("Picsou",1000,0.05);
        compteEp.afficherSolde();
        compteEp.appliquerInterets();
        compteEp.afficherSolde();

        //Exo Polymorphisme/Override
        listeComptes.add(compteEp);

        for (CompteBancaire compte: listeComptes){
            compte.afficherSolde();
        }

        CompteCourant compteAlice = (CompteCourant) listeComptes.get(0);
        //compteAlice.retirer(300);
        compteAlice.afficherSolde();
        compteEp.afficherSolde();
        //compteAlice.retirer(300);

        System.out.println("--- Tentative de Virement ---");
        compteAlice.effectuerVirement(compteEp, 50);
        compteAlice.afficherSolde();
        compteEp.afficherSolde();



    }
}