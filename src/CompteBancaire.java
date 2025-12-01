public abstract class CompteBancaire {

    private String titulaire;
    protected double solde;
    private static int nombreDeComptes = 1; // variable partagé entre les objets avec static
    private String numeroCompte;

    public CompteBancaire (String titulaire, double solde) {
        this.titulaire = titulaire;
        this.solde = solde;
        this.numeroCompte= String.valueOf(nombreDeComptes);
        CompteBancaire.nombreDeComptes +=1;
    }
    public void deposer(double montantDepot) throws MontantInvalideException
    {
        if (montantDepot<0){
            throw new MontantInvalideException("Le montant ne peut etre inferieur a 0");
        }else {
            this.solde = this.solde + montantDepot;
        }
    }
    public void retirer(double montantRetire) throws SoldeInsuffisantException, MontantInvalideException{

        if (montantRetire<0){
            throw new MontantInvalideException("Le montant ne peut etre inferieur a 0");
        }else {
            if (montantRetire > this.solde) {
                throw new SoldeInsuffisantException("Fonds insuffisants");
            } else {
                this.solde = this.solde - montantRetire;
            }
        }
    }
    public void afficherSolde(){
        System.out.println("Compte numero "+this.numeroCompte+" de "+this.titulaire+" : "+this.solde+" euros");
    }

    public double getSolde(){
        return solde;
    }
    public String getTitulaire(){
        return titulaire;
    }
    public String getNumeroCompte(){return numeroCompte;}

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }
}
