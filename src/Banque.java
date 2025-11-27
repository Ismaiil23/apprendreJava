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
        List<CompteBancaire> lc = comptesParNom.get(c.getTitulaire().toLowerCase());
        if(lc == null){
            lc =new ArrayList<>();
            lc.add(c);
            comptesParNom.put(c.getTitulaire().toLowerCase(),lc);
       }else{
            lc.add(c);
       }

    }

    public void afficherTout(){
        listeMapComptes.forEach((s, compteBancaire) -> compteBancaire.afficherSolde());
    }

    public void afficherComptesSelonListe(List<CompteBancaire> lc){
        for(CompteBancaire c : lc){
            c.afficherSolde();
        }
    }
    public CompteBancaire rechercherCompte(String numeroCompte) throws CompteInexistant {
        if (listeMapComptes.get(numeroCompte) == null){
            throw new CompteInexistant("Le numero de compte n'existe pas");
        }
        return listeMapComptes.get(numeroCompte);
    }

    public List<CompteBancaire> rechercherComptesParNom(String nomTitulaire) throws CompteInexistant {
        List<CompteBancaire> lc = comptesParNom.get(nomTitulaire.toLowerCase());
        if(lc == null) {
            throw new CompteInexistant("Aucun compte n'est associe a ce nom");
        }
            return lc;
    }



    public void virer(String numeroDebiteur, String numeroCredite, double montant){
        CompteBancaire debite =rechercherCompte(numeroDebiteur);
        CompteBancaire credite = rechercherCompte(numeroCredite);

            if (debite instanceof CompteCourant){
                ((CompteCourant) debite).effectuerVirement(credite, montant);
            }else{
                System.err.println("Le compte "+ debite.getTitulaire() +" ne peut realiser de virement");
            }

    }
}
