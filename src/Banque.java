import java.io.*;
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

    public void sauvegarder(String nomFichier) {
        try(BufferedWriter writer =new BufferedWriter(new FileWriter(nomFichier))){
            for(CompteBancaire compte : listeMapComptes.values()){
                String type ="";
                String infoSup="";

                if(compte instanceof CompteCourant){
                    type="COURANT";
                    infoSup = String.valueOf(((CompteCourant) compte).getDecouvert());
                }else if(compte instanceof CompteEpargne){
                    type="EPARGNE";
                    infoSup= String.valueOf(((CompteEpargne) compte).getTaux());
                }

                String ligne = type + ";"+ compte.getNumeroCompte() + ";"+ compte.getTitulaire() + ";"+ compte.getSolde() + ";"+ infoSup;

                writer.write(ligne);
                writer.newLine();
            }
            System.out.println("Sauvegarde done !");
        }catch(IOException e){
            System.err.println("Erreur de sauvegarde : "+ e.getMessage());
        }
    }

    public void charger(String nomFichier){
        try(BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;
            while ((ligne = reader.readLine())!=null){
                CompteBancaire compte = getCompteBancaire(ligne);

                this.ajouterCompte(compte);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static CompteBancaire getCompteBancaire(String ligne) {
        String[] morceaux = ligne.split(";");
        var type = morceaux[0];
        var numCompte = morceaux[1];
        var titu = morceaux[2];
        var solde= Double.parseDouble(morceaux[3]);
        var infoSup= morceaux[4];

        CompteBancaire compte = null;
        if (type.equals("COURANT")){
            compte = new CompteCourant(titu,solde, Integer.parseInt(infoSup));
            compte.setNumeroCompte(numCompte);
        } else {
            compte = new CompteEpargne(titu,solde, Double.parseDouble(infoSup));
            compte.setNumeroCompte(numCompte);
        }
        return compte;
    }


}
