package firstproject.factoryapplication.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Organization {
    List<Employee> employees;
    List<Equipment> equipments;

    public Organization() {
    }

    public Organization(List<Employee> employees, List<Equipment> equipments) {
        this.employees = employees;
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
