package View;

import fonctionaliter.RendezVous;
import fonctionaliter.Methode;
import fonctionaliter.Message;
import controleur.MyLibrary;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        Methode methode = new Methode();
        boolean sortie = false;

        while (!sortie) {
            String msgPrincipal = "Bienvenue dans l'application de gestion intégrée de l'entreprise OUF!\n"
                    + "Cette application vous permettra de gérer vos activités quotidiennes :\n\n "
                    + "- Consultez et gérez le solde de votre carte,\n"
                    + "- Effectuez des achats, réservez vos repas, et prenez rendez-vous médical \n"
                    + "- Profitez des offres spéciales réservées aux utilisateurs gold.\n"
                    + "Nous vous souhaitons une excellente expérience avec notre application !\n\n"
                    + "1-Connexion";
            String choixString = MyLibrary.saisieString(msgPrincipal);

            if (choixString != null) {
                int choix = Integer.parseInt(choixString);

                switch (choix) {
                    case 1 -> {
                        String login = MyLibrary.saisieString("Entrez votre login:");
                        String mdp = MyLibrary.saisieString("Entrez votre mot de passe:");
                        String privilege = methode.getPrivilege(login, mdp);

                        if (methode.validationConnexion(login, mdp)) {
                            if ("user".equals(privilege)) {
                                // Menu pour l'utilisateur
                                boolean menuSecondaire = true;
                                while (menuSecondaire) {
                                    String msgSecondaire = "Menu Utilisateur:\n" +
                                            "1- Gestion des Cartes\n" +
                                            "2- Transactions\n" +
                                            "3- Consultation Médicale\n" +
                                            "4- Centre Commercial\n" +
                                            "5- Contact\n" +
                                            "6- Déconnexion\n";
                                    String choixString1 = MyLibrary.saisieString(msgSecondaire);

                                    if (choixString1 != null) {
                                        int choix1 = Integer.parseInt(choixString1);
                                        switch (choix1) {
                                            case 1 -> { // Gestion des Cartes
                                                boolean menuCartes = true;
                                                while (menuCartes) {
                                                    String msgCartes = "Gestion des Cartes:\n" +
                                                            "1- Solde\n" +
                                                            "2- Afficher la carte\n" +
                                                            "3- Ajouter une carte Gold\n" +
                                                            "4- Sortie\n";
                                                    String choixStringCartes = MyLibrary.saisieString(msgCartes);
                                                    if (choixStringCartes != null) {
                                                        int choixCartes = Integer.parseInt(choixStringCartes);
                                                        switch (choixCartes) {
                                                            case 1 -> MyLibrary.afficher("Votre solde est de " + methode.getSolde(login) + " €");
                                                            case 2 -> MyLibrary.afficher(methode.afficherCarte(login));
                                                            case 3 -> {
                                                                if (methode.ajouterCarteGold(login)) {
                                                                    MyLibrary.afficher("Votre carte est maintenant une carte Gold.");
                                                                } else {
                                                                    MyLibrary.afficher("Erreur : Vous possédez déjà une carte Gold.");
                                                                }
                                                            }
                                                            case 4 -> menuCartes = false;
                                                        }
                                                    }
                                                }
                                            }
                                            case 2 -> { // Transactions
                                                boolean menuTransactions = true;
                                                while (menuTransactions) {
                                                    String msgTransactions = "Transactions:\n" +
                                                            "1- Faire un virement\n" +
                                                            "2- Historique\n" +
                                                            "3- Sortie\n";
                                                    String choixStringTransactions = MyLibrary.saisieString(msgTransactions);
                                                    if (choixStringTransactions != null) {
                                                        int choixTransactions = Integer.parseInt(choixStringTransactions);
                                                        switch (choixTransactions) {
                                                            case 1 -> {
                                                                String destinataire = MyLibrary.saisieString("Login du destinataire:");
                                                                double montant = MyLibrary.saisieDouble("Montant à transférer:");
                                                                if (methode.effectuerVirement(login, destinataire, montant)) {
                                                                    MyLibrary.afficher("Virement effectué.");
                                                                } else {
                                                                    MyLibrary.afficher("Erreur : Virement échoué.");
                                                                }
                                                            }
                                                            case 2 -> {
                                                                List<String> historique = methode.obtenirHistoriqueTransactions();
                                                                StringBuilder historiqueMsg = new StringBuilder("Historique des transactions :\n");
                                                                for (String transaction : historique) {
                                                                    historiqueMsg.append(transaction).append("\n");
                                                                }
                                                                MyLibrary.afficher(historiqueMsg.toString());
                                                            }
                                                            case 3 -> menuTransactions = false;
                                                        }
                                                    }
                                                }
                                            }
                                            case 3 -> { // Consultation Médicale
                                                boolean menuMedical = true;
                                                while (menuMedical) {
                                                    String msgMedical = "Consultation Médicale:\n" +
                                                            "1- Prendre rendez-vous\n" +
                                                            "2- Mes rendez-vous\n" +
                                                            "3- Annuler un rendez-vous\n" +
                                                            "4- Sortie\n";
                                                    String choixStringMedical = MyLibrary.saisieString(msgMedical);
                                                    if (choixStringMedical != null) {
                                                        int choixMedical = Integer.parseInt(choixStringMedical);
                                                        switch (choixMedical) {
                                                            case 1 -> {
                                                                String date = MyLibrary.saisieString("Date du rendez-vous (JJ/MM/AAAA):");
                                                                String heure = MyLibrary.saisieString("Heure du rendez-vous (HH:MM):");
                                                                methode.prendreRendezVous(login, date, heure);
                                                                MyLibrary.afficher("Rendez-vous pris pour le " + date + " à " + heure);
                                                            }
                                                            case 2 -> {
                                                                List<RendezVous> rdvs = methode.afficherRendezVous(login);
                                                                StringBuilder rdvMsg = new StringBuilder("Vos rendez-vous :\n");
                                                                for (RendezVous rdv : rdvs) {
                                                                    rdvMsg.append(rdv.toString()).append("\n");
                                                                }
                                                                MyLibrary.afficher(rdvMsg.toString());
                                                            }
                                                            case 3 -> {
                                                                String date = MyLibrary.saisieString("Date du rendez-vous à annuler (JJ/MM/AAAA):");
                                                                String heure = MyLibrary.saisieString("Heure du rendez-vous à annuler (HH:MM):");
                                                                if (methode.annulerRendezVous(login, date, heure)) {
                                                                    MyLibrary.afficher("Rendez-vous annulé.");
                                                                } else {
                                                                    MyLibrary.afficher("Erreur : Aucun rendez-vous trouvé.");
                                                                }
                                                            }
                                                            case 4 -> menuMedical = false;
                                                        }
                                                    }
                                                }
                                            }
                                            case 4 -> { // Centre Commercial
                                                boolean menuCentre = true;
                                                while (menuCentre) {
                                                    String msgCentre = "Centre Commercial:\n" +
                                                            "1- Voir les articles disponibles\n" +
                                                            "2- Acheter un article\n" +
                                                            "3- Historique d'achat\n" +
                                                            "4- Sortie\n";
                                                    String choixStringCentre = MyLibrary.saisieString(msgCentre);
                                                    if (choixStringCentre != null) {
                                                        int choixCentre = Integer.parseInt(choixStringCentre);
                                                        switch (choixCentre) {
                                                            case 1 -> { // Voir les articles disponibles
                                                                List<String> articles = methode.voirArticlesDisponibles();
                                                                StringBuilder articlesMsg = new StringBuilder("Articles disponibles :\n");
                                                                for (String item : articles) {
                                                                    articlesMsg.append(item).append("\n");
                                                                }
                                                                MyLibrary.afficher(articlesMsg.toString());
                                                            }
                                                            case 2 -> { // Acheter un article
                                                                String article = MyLibrary.saisieString("Nom de l'article à acheter:");
                                                                if (methode.acheterArticle(login, article)) {
                                                                    MyLibrary.afficher("Achat effectué.");
                                                                } else {
                                                                    MyLibrary.afficher("Erreur : Achat échoué.");
                                                                }
                                                            }
                                                            case 3 -> { // Historique d'achat
                                                                List<String> historiqueAchats = methode.obtenirHistoriqueAchats(login);
                                                                StringBuilder historiqueMsg = new StringBuilder("Historique d'achats :\n");
                                                                for (String achat : historiqueAchats) {
                                                                    historiqueMsg.append(achat).append("\n");
                                                                }
                                                                MyLibrary.afficher(historiqueMsg.toString());
                                                            }
                                                            case 4 -> menuCentre = false; // Sortie
                                                        }
                                                    }
                                                }
                                            }
                                            case 5 -> { // Contact
                                                String contenu = MyLibrary.saisieString("Écrivez votre message:");
                                                if (contenu != null && !contenu.trim().isEmpty()) {
                                                    methode.envoyerMessage(login, contenu);
                                                    MyLibrary.afficher("Message envoyé avec succès.");
                                                } else {
                                                    MyLibrary.afficher("Message vide. Envoi annulé.");
                                                }
                                            }
                                            case 6 -> menuSecondaire = false; // Déconnexion
                                            default -> MyLibrary.afficher("Choix invalide.");
                                        }
                                    }
                                }
                            } else if ("admin".equals(privilege)) {
                                // Menu pour l'administrateur
                                boolean menuAdmin = true;
                                while (menuAdmin) {
                                    String msgAdmin = "Menu Administrateur:\n" +
                                            "1- Gérer Solde Utilisateurs\n" +
                                            "2- Ajouter un Article\n" +
                                            "3- Ajouter Carte Gold\n" +
                                            "4- Messages\n" +
                                            "5- Déconnexion\n";
                                    String choixStringAdmin = MyLibrary.saisieString(msgAdmin);

                                    if (choixStringAdmin != null) {
                                        int choixAdmin = Integer.parseInt(choixStringAdmin);
                                        switch (choixAdmin) {
                                            case 1 -> { // Ajouter/retirer solde
                                                String utilisateur = MyLibrary.saisieString("Login de l'utilisateur cible:");
                                                double montant = MyLibrary.saisieDouble("Montant à ajouter ou retirer (utilisez des valeurs négatives pour retirer):");
                                                if (methode.ajouterRetirerSolde(utilisateur, montant)) {
                                                    MyLibrary.afficher("Le solde a été mis à jour avec succès.");
                                                } else {
                                                    MyLibrary.afficher("Erreur : mise à jour du solde échouée.");
                                                }
                                            }
                                            case 2 -> { // Ajouter un article
                                                String article = MyLibrary.saisieString("Nom de l'article à ajouter:");
                                                double prix = MyLibrary.saisieDouble("Prix de l'article:");
                                                if (methode.ajouterArticle(article, prix)) {
                                                    MyLibrary.afficher("Article ajouté avec succès.");
                                                } else {
                                                    MyLibrary.afficher("Erreur : ajout de l'article échoué.");
                                                }
                                            }
                                            case 3 -> { // Ajouter carte gold
                                                String utilisateur = MyLibrary.saisieString("Login de l'utilisateur pour la carte Gold:");
                                                if (methode.ajouterCarteGold(utilisateur)) {
                                                    MyLibrary.afficher("Carte Gold ajoutée avec succès.");
                                                } else {
                                                    MyLibrary.afficher("Erreur : L'utilisateur possède déjà une carte Gold ou n'existe pas.");
                                                }
                                            }
                                            case 4 -> { // Voir les messages
                                                List<Message> messages = methode.obtenirMessages();
                                                StringBuilder msgAffichage = new StringBuilder("Messages reçus :\n\n");
                                                for (Message message : messages) {
                                                    msgAffichage.append(message.toString()).append("\n\n");
                                                }
                                                MyLibrary.afficher(msgAffichage.toString());
                                            }
                                            case 5 -> menuAdmin = false; // Déconnexion
                                            default -> MyLibrary.afficher("Choix invalide.");
                                        }
                                    }
                                }
                            }
                        } else {
                            MyLibrary.afficher("Identifiants incorrects.");
                        }
                    }
                }
            }
        }
    }
}
