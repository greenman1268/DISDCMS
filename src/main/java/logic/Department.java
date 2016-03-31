package logic;

/** Created on 30.03.2016 */

public class Department {

        private int departmentId;
        private String nameDepartment;
        private String chief;
        private int amount_people;


    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public void setChief(String chief) {
        this.chief = chief;
    }

    public void setAmount_people(int amount_people) {
        this.amount_people = amount_people;
    }

    public int getDepartmentId() { return departmentId;}

    public String getNameDepartment() {
        return nameDepartment;
    }

    public String getChief() {
        return chief;
    }

    public int getAmount_people() {
        return amount_people;
    }

    @Override
    public String toString() {
        return departmentId + " " + nameDepartment + " " + chief + " " + amount_people;
    }
}

