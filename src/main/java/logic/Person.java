package logic;

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
    private GregorianCalendar birthDay = new GregorianCalendar();
    private char sex;
    private int departmentId;
    private String position;
    private String rank;

    public Person(ResultSet rs) throws SQLException {
        setPersonId(rs.getInt(1));
        setFirstName(rs.getString(2));
        setPatronymic(rs.getString(3));
        setSurName(rs.getString(4));
        setSex(rs.getString(5).charAt(0));
        setDateOfBirth(rs.getDate(6));
        setDepartmentId(rs.getInt(7));
        setPosition(rs.getString(8));
        setRank(rs.getString(9));
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

    public void setDateOfBirth(java.sql.Date date) { this.birthDay.setTime(date); }

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

    public GregorianCalendar getDateOfBirth() { return birthDay; }

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

    public String toString() {
        return personId + " " + surName + " " + firstName + " " + patronymic + " " +
                "" + sex + " " + position + " " + rank + " "
                + new SimpleDateFormat("yyyy-MM-dd").format(birthDay.getTime())
                + " " + departmentId;
    }

    public int compareTo(Object obj) {
        return this.toString().compareTo(obj.toString());
    }
}
