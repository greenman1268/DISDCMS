package logic.organization;

/**Created on 30.03.2016 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Person implements Comparable

{
    private int personId;
    private String firstName;
    private String surName;
    private String patronymic;
    private java.util.Calendar birthDay = new GregorianCalendar();
    private char sex;
    private String position;
    private String rank;
    private int subdivisionId;
    private int departmentId;
    private int brunchId;

    public Person(ResultSet rs) throws SQLException {
        setPersonId(rs.getInt(1));
        setFirstName(rs.getString(2));
        setSurName(rs.getString(3));
        setPatronymic(rs.getString(4));
        setBirthDay(rs.getDate(5));
        setSex(rs.getString(6).charAt(0));
        setPosition(rs.getString(7));
        setRank(rs.getString(8));
        setSubdivisionId(rs.getInt(9));
        setDepartmentId(rs.getInt(10));
        setBrunchId(rs.getInt(11));
    }

    public Person() {}

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

    public void setBirthDay(java.sql.Date date) { this.birthDay.setTime(date); }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }


    public void setSubdivisionId(int subdivisionId) { this.subdivisionId = subdivisionId; }

    public void setBrunchId(int brunchId) { this.brunchId = brunchId; }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setRank(String rank) {
        this.rank = rank;
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

    public java.util.Calendar getBirthDay() { return birthDay; }

    public char getSex() {
        return sex;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public int getSubdivisionId() { return subdivisionId; }

    public int getBrunchId() { return brunchId;}

    public String getPosition() {
        return position;
    }

    public String getRank() {
        return rank;
    }

    public String btoS(){
        SimpleDateFormat smt = new SimpleDateFormat("dd.MM.YYYY");
        smt.setCalendar(birthDay);
        return smt.format(birthDay.getTime());}

    public String toString() {
        return "subdivisionId: " + subdivisionId +
                "\ndepartmentId: " + departmentId +
                "\nbrunchId: " + brunchId +
                "\npersonId: " + personId +
                "\nsurName: " + surName +
                "\nfirstName: " + firstName +
                "\npatronymic: " + patronymic +
                "\nsex: " + sex +
                "\nposition: " + position +
                "\nrank: " + rank +
                "\nbirthDay: " + btoS();
    }

    public int compareTo(Object obj) {
        return this.toString().compareTo(obj.toString());
    }
}
