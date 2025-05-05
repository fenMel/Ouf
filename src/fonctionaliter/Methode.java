package fonctionaliter;

import java.util.ArrayList;
import java.util.List;

public class Methode {
    private UserDatabase userDatabase;

    public Methode() {
        userDatabase = new UserDatabase();
    }

    // Validation de la connexion
    public boolean validationConnexion(String login, String mdp) {
        return userDatabase.getUtilisateur(login, mdp) != null;
    }

    public String getPrivilege(String login, String mdp) {
        return userDatabase.getPrivilege(login, mdp);
    }

    // Gestion des Cartes
    public double getSolde(String login) {
        ConstructeurPersonne utilisateur = userDatabase.getUtilisateurByLogin(login);
        return (utilisateur != null) ? utilisateur.getSolde() : 0;
    }

    public String afficherCarte(String login) {
        ConstructeurPersonne utilisateur = userDatabase.getUtilisateurByLogin(login);
        if (utilisateur != null) {
            return "Carte de " + utilisateur.getNom() + " " + utilisateur.getPrenom() +
                    "\nNuméro: " + utilisateur.getNumeroCarte() +
                    "\nCarte Gold: " + (utilisateur.getCarteGold() ? "Oui" : "Non") +
                    "\nSolde: " + utilisateur.getSolde() + " €";
        }
        return "Utilisateur non trouvé.";
    }

    public boolean ajouterCarteGold(String login) {
        ConstructeurPersonne utilisateur = userDatabase.getUtilisateurByLogin(login);
        if (utilisateur != null && !utilisateur.getCarteGold()) {
            utilisateur.setCarteGold(true);
            return true;
        }
        return false;
    }

    // Transactions
    public boolean effectuerVirement(String loginSource, String loginDestination, double montant) {
        ConstructeurPersonne source = userDatabase.getUtilisateurByLogin(loginSource);
        ConstructeurPersonne destination = userDatabase.getUtilisateurByLogin(loginDestination);

        if (source == null) {
            System.out.println("Erreur : utilisateur source introuvable.");
            return false;
        }

        if (destination == null) {
            System.out.println("Erreur : utilisateur destinataire introuvable.");
            return false;
        }

        if (source.getSolde() < montant) {
            System.out.println("Erreur : solde insuffisant pour effectuer le virement.");
            return false;
        }

        if (montant <= 0) {
            System.out.println("Erreur : montant invalide pour le virement.");
            return false;
        }

        source.setSolde(source.getSolde() - montant);
        destination.setSolde(destination.getSolde() + montant);
        userDatabase.ajouterTransaction("Virement de " + montant + " € de " + loginSource + " à " + loginDestination);

        return true;
    }

    public List<String> obtenirHistoriqueTransactions() {
        return userDatabase.getHistoriqueTransactions();
    }

    // Consultation Médicale
    public void prendreRendezVous(String utilisateur, String date, String heure) {
        RendezVous rdv = new RendezVous(utilisateur, date, heure);
        userDatabase.ajouterRendezVous(rdv);
    }

    public List<RendezVous> afficherRendezVous(String utilisateur) {
        return userDatabase.getRendezVousList(utilisateur);
    }

    public boolean annulerRendezVous(String utilisateur, String date, String heure) {
        return userDatabase.annulerRendezVous(utilisateur, date, heure);
    }

    // Centre Commercial
    public List<String> voirArticlesDisponibles() {
        return userDatabase.getArticlesDisponibles();
    }

    public boolean acheterArticle(String login, String article) {
        return userDatabase.acheterArticle(login, article);
    }

    public List<String> obtenirHistoriqueAchats(String login) {
        return userDatabase.getHistoriqueAchats(login);
    }

    // Gestion administrateur : ajouter/retirer du solde
    public boolean ajouterRetirerSolde(String login, double montant) {
        ConstructeurPersonne utilisateur = userDatabase.getUtilisateurByLogin(login);
        if (utilisateur != null) {
            utilisateur.setSolde(utilisateur.getSolde() + montant);
            return true;
        }
        return false;
    }

    // Gestion administrateur : ajouter un article
    public boolean ajouterArticle(String nomArticle, double prix) {
        if (prix > 0) {
            String article = nomArticle + " - " + prix + "€";
            userDatabase.ajouterArticle(article);
            return true;
        }
        return false;
    }

    // Messagerie
    public void envoyerMessage(String utilisateur, String contenu) {
        userDatabase.ajouterMessage(utilisateur, contenu);
    }

    public List<Message> obtenirMessages() {
        return userDatabase.getMessages();
    }
}
