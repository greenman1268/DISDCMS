package servlet.forms;

/**
 * Created on 31.03.2016
 */
import logic.organization.Person;

import java.text.SimpleDateFormat;
import java.util.Collection;

public class PersonForm
{
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    private int personId;
    private String firstName;
    private String surName;
    private String patronymic;
    private String birthDay;
    private char sex;
    private int departmentId;
    private String position;
    private String rank;
    private Collection departments;

    public void initFromPerson(Person p) {
        this.personId = p.getPersonId();
        this.firstName = p.getFirstName();
        this.surName = p.getSurName();
        this.patronymic = p.getPatronymic();
        this.birthDay = sdf.format(p.getBirthDay().getTime());
        if (p.getSex() == '×') {
            this.sex = 0;
        } else {
            this.sex = 1;
        }
        this.departmentId = p.getDepartmentId();
        this.position = p.getPosition();
        this.rank = p.getRank();
    }

    public static SimpleDateFormat getSdf() {
        return sdf;
    }

    public int getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public char getSex() {
        return sex;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getPosition() {
        return position;
    }

    public String getRank() {
        return rank;
    }

    public Collection getDepartments() {
        return departments;
    }

    public static void setSdf(SimpleDateFormat sdf) {
        PersonForm.sdf = sdf;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setDepartments(Collection departments) {
        this.departments = departments;
    }
}
