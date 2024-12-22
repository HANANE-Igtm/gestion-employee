package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JOptionPane;

import Model.Employe;
import Model.Poste;
import Model.Role;

public class EmployeDAOImp implements EmployeDAOI<Employe>{
    private static connexion conn;

    public EmployeDAOImp() {
        conn=new connexion();
    }

    @Override
    public void add(Employe emp) {
        String sql = "INSERT INTO Employee (nom, prenom, email, phone, salaire, role, poste) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.getConnexion().prepareStatement(sql)) {
            stmt.setString(1, emp.getNom());
            stmt.setString(2, emp.getPrenom());
            stmt.setString(3, emp.getEmail());
            stmt.setString(4, emp.getTelephone());
            stmt.setDouble(5, emp.getSalaire());
            stmt.setString(6, emp.getRole()); // Convertir en String si Role est une énumération
            stmt.setString(7, emp.getPoste()); // Convertir en String si Poste est une énumération

            stmt.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void update(Employe emp) {
        String sql = "UPDATE Employee SET nom = ? , prenom = ? , email = ?,phone = ?, salaire = ?, role = ?, poste = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.getConnexion().prepareStatement(sql)) {
            stmt.setString(1, emp.getNom());
            stmt.setString(2, emp.getPrenom());
            stmt.setString(3, emp.getEmail());
            stmt.setString(4, emp.getTelephone());
            stmt.setDouble(5, emp.getSalaire());
            stmt.setString(6, emp.getRole());
            stmt.setString(7, emp.getPoste());
            stmt.setInt(8, emp.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Employee WHERE id = ?";
        try (PreparedStatement stmt = conn.getConnexion().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Employe> employes() {
        List<Employe> emp =new ArrayList<>();
        String sql = "SELECT * FROM Employee";
        try (PreparedStatement stmt = connexion.getConnexion().prepareStatement(sql);
             ResultSet rslt=stmt.executeQuery()){
            while(rslt.next()) {
                emp.add(new Employe(
                        rslt.getInt("id"),
                        rslt.getString("nom"),
                        rslt.getString("prenom"),
                        rslt.getString("email"),
                        rslt.getString("phone"),
                        rslt.getDouble("salaire"),
                        Role.valueOf(rslt.getString("role")),
                        Poste.valueOf(rslt.getString("poste"))
                ));
            }
        }catch(SQLException e) {
       e.printStackTrace();
        }
        return emp;
    }
}
