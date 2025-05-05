package fonctionaliter;

public class RendezVous {
    private String utilisateur;
    private String date;
    private String heure;

    public RendezVous(String utilisateur, String date, String heure) {
        this.utilisateur = utilisateur;
        this.date = date;
        this.heure = heure;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public String getDate() {
        return date;
    }

    public String getHeure() {
        return heure;
    }

    @Override
    public String toString() {
        return "Rendez-vous pour " + utilisateur + " le " + date + " à " + heure;
    }
}
