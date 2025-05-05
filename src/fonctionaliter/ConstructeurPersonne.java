package fonctionaliter;

public class ConstructeurPersonne {

    private String nom;
    private String prenom;
    private String privilege;
    private int id;
    private String numeroCarte;
    private Boolean carteGold;
    private String login;
    private String mdp;
    private double solde;

    public ConstructeurPersonne(String nom, String prenom, String privilege, int id, String numeroCarte,
                                Boolean carteGold, String login, String mdp, double solde) {
        this.nom = nom;
        this.prenom = prenom;
        this.privilege = privilege;
        this.id = id;
        this.numeroCarte = numeroCarte;
        this.carteGold = carteGold;
        this.login = login;
        this.mdp = mdp;
        this.solde = solde;
    }

    // Getters et Setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getPrivilege() { return privilege; }
    public void setPrivilege(String privilege) { this.privilege = privilege; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNumeroCarte() { return numeroCarte; }
    public void setNumeroCarte(String numeroCarte) { this.numeroCarte = numeroCarte; }
    public Boolean getCarteGold() { return carteGold; }
    public void setCarteGold(Boolean carteGold) { this.carteGold = carteGold; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getMdp() { return mdp; }
    public void setMdp(String mdp) { this.mdp = mdp; }
    public double getSolde() { return solde; }
    public void setSolde(double solde) { this.solde = solde; }
}
