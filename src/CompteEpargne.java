public class CompteEpargne extends CompteBancaire{
    private double tauxInteret;
    public CompteEpargne(String titulaire, double solde,double tauxInteret){
        super(titulaire,solde);
        this.tauxInteret = tauxInteret;
    }
    public void appliquerInterets(){
        double montantTauxInteret =this.getSolde() * this.tauxInteret;
        this.deposer(montantTauxInteret);
    }

    @Override
    public void afficherSolde(){
        System.out.println("Livret Epargne de " + getTitulaire() + " : " + getSolde() + " euros");
    }

}
