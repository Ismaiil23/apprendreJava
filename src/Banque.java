import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Banque {

    private Map<String,CompteBancaire> listeMapComptes;
    private Map<String, List<CompteBancaire>> comptesParNom;

    public Banque() {
        listeMapComptes = new HashMap<String,CompteBancaire>();
        comptesParNom = new HashMap<>();
    }

    public void ajouterCompte(CompteBancaire c){
        listeMapComptes.put(c.getNumeroCompte(),c);
        List<CompteBancaire> lc = comptesParNom.get(c.getTitulaire());
        if(lc == null){
            lc =new ArrayList<>();
            lc.add(c);
            comptesParNom.put(c.getTitulaire(),lc);
       }else{
            lc.add(c);
       }

    }

    public void afficherTout(){
        listeMapComptes.forEach((s, compteBancaire) -> compteBancaire.afficherSolde() );
    }

    public CompteBancaire rechercherCompte(String numeroCompte) throws NumeroCompteInexistant {
        if (listeMapComptes.get(numeroCompte) == null){
            throw new NumeroCompteInexistant("Le numero de compte n'existe pas");
        }
        return listeMapComptes.get(numeroCompte);
    }

    public void virer(String nomDebiteur, String nomCredite, double montant){
        CompteBancaire debite =rechercherCompte(nomDebiteur);
        CompteBancaire credite = rechercherCompte(nomCredite);
        if(debite != null  && credite != null){
            if (debite instanceof CompteCourant){
                ((CompteCourant) debite).effectuerVirement(credite, montant);
            }else{
                System.err.println("Le compte "+ debite.getTitulaire() +" ne peut realiser de virement");
            }
        }else{
            System.err.println("L'un des deux comptes ou les deux sont null");

        }

    }
}
