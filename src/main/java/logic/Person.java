package logic;

/**Created on 30.03.2016 */

import java.sql.ResultSet;
import java.sql.SQLException;

public class Person implements Comparable

{
    private int personId;
    private String firstName;
    private String surName;
    private String patronymic;
    private BirthDay birthDay = new BirthDay();
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
        setDateOfBirth(rs.getInt(6),rs.getInt(7),rs.getInt(8));
        setDepartmentId(rs.getInt(9));
        setPosition(rs.getString(10));
        setRank(rs.getString(11));
    }

    public Person() {
    }

    public class BirthDay{
        private int day;
        private int month;
        private int year;

        public int getDay() {
            return day;
        }

        public int getMonth() {
            return month;
        }

        public int getYear() {
            return year;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public void setYear(int year) {
            this.year = year;
        }

        @Override
        public String toString() {
            return (day<10?"0"+day:day) + "." + (month<10?"0"+month:month) + "." + year;
        }
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

    public void setDateOfBirth(int day, int month, int year) {
        this.birthDay.day = day;
        this.birthDay.month = month;
        this.birthDay.year = year;
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

    public BirthDay getDateOfBirth() {

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

    public String toString() {
        return personId + " " + surName + " " + firstName + " " + patronymic + " " +
                "" + sex + " " + position + " " + rank + " "
                + birthDay
                + " " + departmentId;
    }

    public int compareTo(Object obj) {
        return this.toString().compareTo(obj.toString());
    }
}
