public abstract class CompteBancaire {

    private String titulaire;
    protected double solde;

    public CompteBancaire (String titulaire, double solde) {
        this.titulaire = titulaire;
        this.solde = solde;
    }
    public void deposer(double montantDepot)
    {
        if (montantDepot<0){
            System.err.println("Le montant ne peut etre inferieur a 0");
        }else {
            this.solde = this.solde + montantDepot;
        }
    }
    public void retirer(double montantRetire) {

        if (montantRetire<0){
            System.err.println("Le montant ne peut etre inferieur a 0");
        }else {
            if (montantRetire > this.solde) {
                System.out.println("Fonds insuffisants");
            } else {
                this.solde = this.solde - montantRetire;
            }
        }
    }
    public void afficherSolde(){
        System.out.println("Compte de "+this.titulaire+" : "+this.solde+" euros");
    }

    public double getSolde(){
        return solde;
    }
    public String getTitulaire(){
        return titulaire;
    }

}
