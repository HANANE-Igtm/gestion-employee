package Controller;
import Model.*;
import View.EmployeView;
public class EmployeController {
    private EmployeView view;
    private EmployeModel model;

    public EmployeController(EmployeView view, EmployeModel model) {
        this.view = view;
        this.model = model;

        this.view.getAjouterButton().addActionListener(e -> add());
        this.view.getModifierButton().addActionListener(e -> update());
        this.view.getSupprimerButton().addActionListener(e -> delete());
        this.view.getAfficherButton().addActionListener(e -> employes());
    }

    // Méthode pour ajouter un employé
    private void add() {
        String nom = view.getNom().trim();
        String prenom = view.getPrenom().trim();
        String email = view.getEmail().trim();
        String telephone = view.getTelephone().trim();
        Role role = view.getRole();
        Poste poste = view.getPoste();
        Double salaire;
        try {

            salaire = view.getSalaire();

        } catch (NumberFormatException e) {
            view.afficherMessageErreur("Le salaire doit être un nombre valide.");
            return;
        }
        // Appel au modèle pour ajouter l'employé
        boolean ajoutReussi = model.add( nom, prenom, email, telephone, salaire, role, poste);
        if (ajoutReussi) {
            view.afficherMessageSucces("Employé ajouté avec succès.");
            // Actualiser la liste des employés
        } else {
            view.afficherMessageErreur("Échec de l'ajout de l'employé.");
        }


    }

    // Méthode pour mettre à jour un employé
    private void update() {
        int selectedRow = view.table.getSelectedRow();
        if (selectedRow == -1) {
            view.afficherMessageErreur("Veuillez sélectionner un employé à modifier.");
            return;
        }
        String nom = view.getNom().trim();
        String prenom = view.getPrenom().trim();
        String email = view.getEmail().trim();
        String telephone = view.getTelephone().trim();
        Double salaire ;
        Role role = view.getRole();
        Poste poste = view.getPoste();
        int id = (int) view.model.getValueAt(selectedRow, 0);
        try {

            salaire = view.getSalaire();

        } catch (NumberFormatException e) {
            view.afficherMessageErreur("Le salaire doit être un nombre valide.");
            return;
        }
        boolean miseAJourReussie = model.update(id, nom, prenom, email, telephone, salaire, role, poste);
        if (miseAJourReussie) {
            view.afficherMessageSucces("Employé modifié avec succès.");
            employes();
        } else {
            view.afficherMessageErreur("Échec de la modification de l'employé.");
        }
    }

    // Méthode pour supprimer un employé
    private void delete() {

            int selectedRow = view.table.getSelectedRow();
            if (selectedRow == -1) {
                view.afficherMessageErreur("Veuillez sélectionner un employé à supprimer.");
                return;
            }
            int id = (int) view.model.getValueAt(selectedRow, 0);

            boolean suppressionReussie = model.delete(id);
            if (suppressionReussie) {
                view.afficherMessageSucces("Employé supprimé avec succès.");
                employes();

            } else {
                view.afficherMessageErreur("Échec de la suppression de l'employé.");
            }
    }

    // Méthode pour afficher tous les employés
    private void employes() {
            Object[][] employes = model.employes();
            view.model.setRowCount(0); // Vider la table avant d'ajouter les données
            for (Object[] emp : employes) {
                view.model.addRow(emp);

            }
            System.out.println("bien affiche");

    }
}