package Model;
import java.util.List;
import DAO.EmployeDAOImp;
public class EmployeModel {
    private EmployeDAOImp dao;

    public EmployeModel(EmployeDAOImp dao) {
        this.dao=dao;
    }
    public boolean add(String nom, String prenom, String email, String telephone, double salaire, Role role, Poste poste) {
        if (!email.contains("@")) {
            System.err.println("L'email que vous avez entré est invalide.");
            return false;
        }

        if (telephone.length() != 10) {
            System.err.println("The phone number must contain exactly 10 digits.");
            return false;
        }

        Employe empl = new Employe(nom, prenom, email, telephone, salaire, role, poste);
        dao.add(empl);
        return true;
    }
    public boolean update(int id,String nom, String prenom, String email, String telephone, double salaire, Role role, Poste poste) {
        if (!email.contains("@")) {  // Vérifie si '@' est manquant
            System.err.println("L'email que vous avez entré est invalide.");
            return false; // Email invalide
        }
        // Méthode pour vérifier NumeroTl
        if (telephone.length() != 10) {
            System.err.println("The phone number must contain exactly 10 digits.");
            return false;
        }

        Employe emp = new Employe(id,nom, prenom, email, telephone, salaire, role, poste);
        dao.update(emp);
        return true;
    }
    public boolean delete(int id) {
        dao.delete(id);
        return true;
    }
    public Object[][] employes(){
        List<Employe>emp=dao.employes();
        Object[][] tab =new Object[emp.size()][8];
        for (int i = 0; i < emp.size(); i++) {
            Employe empl = emp.get(i);
            tab[i][0] = empl.getId();
            tab[i][1] = empl.getNom();
            tab[i][2] = empl.getPrenom();
            tab[i][3] = empl.getEmail();
            tab[i][4] = empl.getTelephone();
            tab[i][5] = empl.getSalaire();
            tab[i][6] = empl.getRole();
            tab[i][7] = empl.getPoste();
        }
        return tab;
    }
}
