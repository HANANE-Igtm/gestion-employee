package DAO;

import java.util.List;

import Model.Employe;

public interface EmployeDAOI<T>{
    public void add(T emp);
    public void update(T emp);
    public void delete(int id);
    public List<T> employes();
}
