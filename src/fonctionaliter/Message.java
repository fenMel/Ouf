package fonctionaliter;

public class Message {
    private String utilisateur;
    private String contenu;

    public Message(String utilisateur, String contenu) {
        this.utilisateur = utilisateur;
        this.contenu = contenu;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public String getContenu() {
        return contenu;
    }

    @Override
    public String toString() {
        return "De: " + utilisateur + "\nMessage: " + contenu;
    }
}
