public class CompteCourant extends CompteBancaire implements Transferable{
    private double decouvertAutorise;
    public CompteCourant(String titulaire, double solde, int decouvertAutorise){
        super(titulaire,solde);
        this.decouvertAutorise=decouvertAutorise;
    }
    @Override
    public void retirer(double montantRetire){
        // On calcule ce qu'il resterait après le retrait
        double soldeApresRetrait = this.solde - montantRetire;

        // Si j'ai un découvert de 500, je peux descendre jusqu'à -500.
        // Donc il faut que le solde final soit supérieur ou égal à -500.
        if (soldeApresRetrait >= -decouvertAutorise) {
            this.solde = soldeApresRetrait; // ACCÈS DIRECT AUTORISÉ GRÂCE À PROTECTED
            System.out.println("Retrait effectué. Nouveau solde : " + this.solde);
        } else {
            System.err.println("Retrait refusé : Limite de découvert atteinte !");
        }
    };

    @Override
    public void effectuerVirement(CompteBancaire destinataire, double montant) {
        this.retirer(montant);
        destinataire.deposer(montant);
        System.out.println("Virement de " + montant + " effectué vers " + destinataire.getTitulaire());
    }
}
