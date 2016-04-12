package servlet.forms;

/** Created on 31.03.2016 */

import java.util.Collection;

public class MainFrameForm {
    private int departmentId;
    private String nameDepartment;
    private String chief;
    private int amount_people;
    private Collection departments;
    private Collection persons;

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public void setChief(String chief) {
        this.chief = chief;
    }

    public void setAmount_people(int amount_people) {
        this.amount_people = amount_people;
    }

    public String getNameDepartment() { return nameDepartment; }

    public String getChief() {
        return chief;
    }

    public int getAmount_people() {
        return amount_people;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setDepartments(Collection departments) {
        this.departments = departments;
    }

    public void setPersons(Collection persons) {
        this.persons = persons;
    }

    public int getDepartmentId() { return departmentId; }

    public Collection getDepartments() {
        return departments;
    }

    public Collection getPersons() {
        return persons;
    }
}
