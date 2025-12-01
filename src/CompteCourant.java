public class CompteCourant extends CompteBancaire implements Transferable{
    private int decouvertAutorise;
    public CompteCourant(String titulaire, double solde, int decouvertAutorise){
        super(titulaire,solde);
        this.decouvertAutorise=decouvertAutorise;
    }
    @Override
    public void retirer(double montantRetire) throws SoldeInsuffisantException,MontantInvalideException{
        // On calcule ce qu'il resterait après le retrait
        if(montantRetire>0){
        double soldeApresRetrait = this.solde - montantRetire;

        // Si j'ai un découvert de 500, je peux descendre jusqu'à -500.
        // Donc il faut que le solde final soit supérieur ou égal à -500.
        if (soldeApresRetrait >= -decouvertAutorise) {
            this.solde = soldeApresRetrait; // ACCÈS DIRECT AUTORISÉ GRÂCE À PROTECTED
            System.out.println("Retrait effectué. Nouveau solde : " + this.solde);
        } else {
            throw new SoldeInsuffisantException("Retrait refusé : Limite de découvert atteinte !");
        }}else{
            throw new MontantInvalideException("Le montant doit etre superieur a 0");
        }
    };

    @Override
    public void effectuerVirement(CompteBancaire destinataire, double montant){
        try {
            this.retirer(montant);
            destinataire.deposer(montant);
            System.out.println("Virement de " + montant + " effectué vers " + destinataire.getTitulaire());

        }catch (SoldeInsuffisantException e){
           System.out.println(e.getMessage());
        }

    }

    public int getDecouvert() {
        return decouvertAutorise;
    }
}
