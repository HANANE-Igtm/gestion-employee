package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import Model.Poste;
import Model.Role;


public class EmployeView extends JFrame {
    // Déclaration des panels
    JPanel pan1 = new JPanel();
    JPanel pan2 = new JPanel();
    JPanel pan4 = new JPanel();


    // Boutons
    JButton ajouter = new JButton("Ajouter");
    JButton modifier = new JButton("Modifier");
    JButton supprimer = new JButton("Supprimer");
    JButton afficher = new JButton("Afficher");

    // Labels
    JLabel nomLabel = new JLabel("Nom:");
    JLabel prenomLabel = new JLabel("Prénom:");
    JLabel emailLabel = new JLabel("Email:");
    JLabel telephoneLabel = new JLabel("Téléphone:");
    JLabel salaireLabel = new JLabel("Salaire:");
    JLabel roleLabel = new JLabel("Rôle:");
    JLabel posteLabel = new JLabel("Poste:");


    // Champs de texte
    JTextField nomField = new JTextField(20);
    JTextField prenomField = new JTextField(20);
    JTextField emailField = new JTextField(20);
    JTextField telephoneField = new JTextField(20);
    JTextField salaireField = new JTextField(20);

    public JTable table;
    public DefaultTableModel model;
    public JScrollPane scrollPane;


    public JComboBox<Role> roleComboBox = new JComboBox<>(Role.values());
    public JComboBox<Poste> posteComboBox = new JComboBox<>(Poste.values());

    public EmployeView() {
        // Configuration de la fenêtre principale
        setTitle("Exemple de MySQL");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configuration du panel principal
        pan1.setLayout(new BorderLayout());
        pan1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajout d'un espace intérieur

        // Configuration du panel contenant les champs
        pan2.setLayout(new GridLayout(7, 2, 2, 2)); // Réduction de l'espacement horizontal et vertical
        pan2.add(nomLabel);
        pan2.add(nomField);
        pan2.add(prenomLabel);
        pan2.add(prenomField);
        pan2.add(emailLabel);
        pan2.add(emailField);
        pan2.add(telephoneLabel);
        pan2.add(telephoneField);
        pan2.add(salaireLabel);
        pan2.add(salaireField);
        pan2.add(roleLabel);
        pan2.add(roleComboBox);
        pan2.add(posteLabel);
        pan2.add(posteComboBox);

        // Configuration du panel pour les boutons
        pan4.setLayout(new GridLayout(1, 4, 8, 8));
        pan4.add(ajouter);
        pan4.add(modifier);
        pan4.add(supprimer);
        pan4.add(afficher);

        String[] columnNames = {"Id","Nom", "Prenom", "Telephone", "Email", "Salaire", "Role", "Poste"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);


        pan1.add(pan2, BorderLayout.NORTH);
        pan1.add(pan4, BorderLayout.SOUTH);
        pan1.add(scrollPane, BorderLayout.CENTER);   // Table en bas

        add(pan1);

        // Rendre la fenêtre visible
        setVisible(true);
    }

    // Getters for buttons
    public JButton getAjouterButton() {
        return ajouter;
    }

    public JButton getModifierButton() {
        return modifier;
    }

    public JButton getSupprimerButton() {
        return supprimer;
    }

    public JButton getAfficherButton() {
        return afficher;
    }

    // Getters for fields
    public String getNom() {
        return nomField.getText();
    }


    public String getPrenom() {
        return prenomField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getTelephone() {
        return telephoneField.getText();
    }

    public double getSalaire() {
        return Double.parseDouble(salaireField.getText());
    }

    public Role getRole() {
        return (Role) roleComboBox.getSelectedItem();
    }

    public Poste getPoste() {
        return (Poste) posteComboBox.getSelectedItem();
    }

    public void afficherMessageErreur(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    public void afficherMessageSucces(String message) {
        JOptionPane.showMessageDialog(this, message, "Succès", JOptionPane.INFORMATION_MESSAGE);
    }


}
