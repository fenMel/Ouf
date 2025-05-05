package fonctionaliter;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private List<ConstructeurPersonne> utilisateurs = new ArrayList<>();
    private List<String> historiqueTransactions = new ArrayList<>();
    private List<RendezVous> rendezVousList = new ArrayList<>();
    private List<String> articlesDisponibles = new ArrayList<>();
    private List<String> historiqueAchats = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();

    public UserDatabase() {
        utilisateurs.add(new ConstructeurPersonne("Doe", "John", "user", 1, "1234", false, "jdoe", "password", 50.0));
        utilisateurs.add(new ConstructeurPersonne("Smith", "Alice", "admin", 2, "5678", true, "asmith", "adminpass", 100.0));
        utilisateurs.add(new ConstructeurPersonne("Doe", "Paul", "user", 3, "91011", false, "pdoe", "password", 50.0));

        articlesDisponibles.add("Chargeur de téléphone - 10€");
        articlesDisponibles.add("Tablette graphique - 15€");
        articlesDisponibles.add("Un jeu video - 20€");
    }

    public ConstructeurPersonne getUtilisateur(String login, String mdp) {
        for (ConstructeurPersonne utilisateur : utilisateurs) {
            if (utilisateur.getLogin().equals(login) && utilisateur.getMdp().equals(mdp)) {
                return utilisateur;
            }
        }
        return null;
    }

    public ConstructeurPersonne getUtilisateurByLogin(String login) {
        for (ConstructeurPersonne utilisateur : utilisateurs) {
            if (utilisateur.getLogin().equals(login)) {
                return utilisateur;
            }
        }
        return null;
    }

    public String getPrivilege(String login, String mdp) {
        ConstructeurPersonne utilisateur = getUtilisateur(login, mdp);
        return (utilisateur != null) ? utilisateur.getPrivilege() : null;
    }

    // Transactions
    public void ajouterTransaction(String transaction) {
        historiqueTransactions.add(transaction);
    }

    public List<String> getHistoriqueTransactions() {
        return new ArrayList<>(historiqueTransactions);
    }

    // Consultation Médicale
    public void ajouterRendezVous(RendezVous rendezVous) {
        rendezVousList.add(rendezVous);
    }

    public List<RendezVous> getRendezVousList(String utilisateur) {
        List<RendezVous> result = new ArrayList<>();
        for (RendezVous rdv : rendezVousList) {
            if (rdv.getUtilisateur().equals(utilisateur)) {
                result.add(rdv);
            }
        }
        return result;
    }

    public boolean annulerRendezVous(String utilisateur, String date, String heure) {
        return rendezVousList.removeIf(rdv -> rdv.getUtilisateur().equals(utilisateur) &&
                rdv.getDate().equals(date) && rdv.getHeure().equals(heure));
    }

    // Centre Commercial
    public List<String> getArticlesDisponibles() {
        return new ArrayList<>(articlesDisponibles);
    }

    public boolean acheterArticle(String login, String article) {
        ConstructeurPersonne utilisateur = getUtilisateurByLogin(login);
        if (utilisateur != null) {
            String articleRecherche = article.trim().toLowerCase();
            for (String articleDisponible : articlesDisponibles) {
                String articleNom = articleDisponible.split(" - ")[0].trim().toLowerCase();
                double prix = Double.parseDouble(articleDisponible.split(" - ")[1].replace("€", "").trim());

                if (articleNom.equals(articleRecherche)) {
                    if (utilisateur.getSolde() >= prix) {
                        utilisateur.setSolde(utilisateur.getSolde() - prix);
                        historiqueAchats.add("Achat de " + articleNom + " par " + login);
                        return true;
                    } else {
                        System.out.println("Erreur : solde insuffisant pour l'achat.");
                        return false;
                    }
                }
            }
            System.out.println("Erreur : article introuvable.");
        }
        return false;
    }

    public List<String> getHistoriqueAchats(String login) {
        List<String> achatsUtilisateur = new ArrayList<>();
        for (String achat : historiqueAchats) {
            if (achat.contains("par " + login)) {
                achatsUtilisateur.add(achat);
            }
        }
        return achatsUtilisateur;
    }

    // Gestion administrateur : ajouter un article
    public void ajouterArticle(String article) {
        articlesDisponibles.add(article);
    }

    // Messagerie
    public void ajouterMessage(String utilisateur, String contenu) {
        messages.add(new Message(utilisateur, contenu));
    }

    public List<Message> getMessages() {
        return new ArrayList<>(messages);
    }
}
