package controleur;

import javax.swing.JOptionPane;

public class MyLibrary {

    public static String saisieString(String msg) {
        return JOptionPane.showInputDialog(msg);
    }

    public static int saisieInt(String msg) {
        return Integer.parseInt(JOptionPane.showInputDialog(msg));
    }

    public static double saisieDouble(String msg) {
        return Double.parseDouble(JOptionPane.showInputDialog(msg));
    }

    public static void afficher(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}
