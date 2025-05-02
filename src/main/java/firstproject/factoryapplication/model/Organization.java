package firstproject.factoryapplication.model;

import java.util.List;

public class Organization {
    List<Employee> employees;
    List<Equipment> equipments;

    public Organization() {
    }

    public Organization(List<Employee> employees, List<Equipment> equipments) {
        this.employees = employees;
        this.equipments = equipments;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "employees=" + employees +
                ", equipments=" + equipments +
                '}';
    }
}
