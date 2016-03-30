package logic;

/** Created on 30.03.2016 */

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class ManagementSystem {

        private static Connection con;
        private static ManagementSystem instance;
        private static DataSource dataSource;

        private ManagementSystem() throws Exception{
        //connection ver 2
        /*try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/students";
            con = DriverManager.getConnection(url, "root", "126874539");
        } catch (ClassNotFoundException e) {
            throw new Exception(e);
        } catch (SQLException e) {
            throw new Exception(e);
        }*/
    }

    public static synchronized ManagementSystem getInstance() throws Exception{
        //connection ver 1
        /*if (instance == null) {
            try {
                instance = new ManagementSystem();
                Context ctx = new InitialContext();
                instance.dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/StudentsDS");

                con = dataSource.getConnection("root","126874539");
            } catch (NamingException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }}*/
        //connection ver 2
        /*  if (instance == null) {
            instance = new ManagementSystem();
        }*/
        //connection ver 3
        if(instance == null){
            instance = new ManagementSystem();
            Properties props = new Properties();
            FileInputStream fis = null;
            MysqlDataSource mysqlDS = null;
            try {

                fis = new FileInputStream("db.properties");
                props.load(fis);
                mysqlDS = new MysqlDataSource();
                mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
                mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
                mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
                instance.dataSource = mysqlDS;
                con =  dataSource.getConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (SQLException e) {
                e.printStackTrace();
            }}

        return instance;}

    public static List getDepartments() throws SQLException {
        List departments = new ArrayList();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT dep_id, depName, chief, amount_people FROM department");
        while (rs.next()) {
            Department dep = new Department();
            dep.setDepartmentId(rs.getInt(1));
            dep.setNameDepartment(rs.getString(2));
            dep.setChief(rs.getString(3));
            dep.setAmount_people(rs.getInt(4));
            departments.add(dep);
           // System.out.println(dep);
        }
        rs.close();
        stmt.close();
        return departments;
    }

    public Collection getAllPersons() throws SQLException {
        Collection persons = new ArrayList();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT person_id, firstName, patronymic, surName, "
                + "sex, dayOFbirth, monthOFbirth, yearOFbirth, department_id, position_name, rank FROM person ORDER BY surName, firstName, patronymic");
        while (rs.next()) {
            Person st = new Person(rs);
            persons.add(st);
           // System.out.println(st);
        }
        rs.close();
        stmt.close();
        return persons;
    }


    public Collection getPersonsFromDepartment(Department dep, int year) throws SQLException {
        Collection persons = new ArrayList();
        PreparedStatement stmt = con.prepareStatement("SELECT person_id, firstName, patronymic, surName, "
                + "sex, dayOFbirth, monthOFbirth, yearOFbirth, department_id, position_name, rank FROM person "
                + "WHERE department_id = ? AND  yearOFbirth = ? "
                + "ORDER BY surName, firstName, patronymic");
        stmt.setInt(1, dep.getDepartmentId());
        stmt.setInt(2, year);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Person st = new Person(rs);
            persons.add(st);
          //  System.out.println(st);
        }
        rs.close();
        stmt.close();
        return persons;
    }


    public Person getPersonById(int personId) throws SQLException {
        Person person = null;
        PreparedStatement stmt = con.prepareStatement("SELECT person_id, firstName, patronymic, surName,"
                 + "sex, dayOFbirth, monthOFbirth, yearOFbirth, department_id, position_name, rank FROM person WHERE person_id = ?");
        stmt.setInt(1, personId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            person = new Person(rs);
        }
        rs.close();
        stmt.close();
        return person;
    }

    public void movePersonsToDepartment(Department oldDepartment, int oldYear, Department newDepartment, int newYear) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("UPDATE person SET department_id = ?, yearOFbirth = ? "
                + "WHERE department_id =  ? AND  yearOFbirth = ?");
        stmt.setInt(1, newDepartment.getDepartmentId());
        stmt.setInt(2, newYear);
        stmt.setInt(3, oldDepartment.getDepartmentId());
        stmt.setInt(4, oldYear);
        stmt.execute();
    }

    public void removePersonsFromDepartment(Department dep, int year) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM person WHERE department_id = ? AND yearOFbirth = ?");
        stmt.setInt(1, dep.getDepartmentId());
        stmt.setInt(2, year);
        stmt.execute();
    }

    public void insertPerson(Person person) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO person "
                + "(person_id, firstName, patronymic, surName,"
                + "sex, dayOFbirth, monthOFbirth, yearOFbirth, department_id, position_name, rank)"
                + "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setInt(1, person.getPersonId());
        stmt.setString(2, person.getFirstName());
        stmt.setString(3, person.getPatronymic());
        stmt.setString(4, person.getSurName());
        stmt.setString(5, new String(new char[]{person.getSex()}));
        stmt.setInt(6, person.getDateOfBirth().getDay());
        stmt.setInt(7, person.getDateOfBirth().getMonth());
        stmt.setInt(8, person.getDateOfBirth().getYear());
        stmt.setInt(9, person.getDepartmentId());
        stmt.setString(10, person.getPosition());
        stmt.setString(11, person.getRank());
        stmt.execute();
    }

    public void updatePerson(Person person) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("UPDATE person "
                + "SET firstName=?, patronymic=?, surName=?,"
                + "sex=?, dayOFbirth=?, monthOFbirth=?, yearOFbirth=?, department_id=?, position_name=?, rank=? WHERE person_id=?");

        stmt.setString(1, person.getFirstName());
        stmt.setString(2, person.getPatronymic());
        stmt.setString(3, person.getSurName());
        stmt.setString(4, new String(new char[]{person.getSex()}));
        stmt.setInt(5, person.getDateOfBirth().getDay());
        stmt.setInt(6, person.getDateOfBirth().getMonth());
        stmt.setInt(7, person.getDateOfBirth().getYear());
        stmt.setInt(8, person.getDepartmentId());
        stmt.setString(9, person.getPosition());
        stmt.setString(10, person.getRank());
        stmt.setInt(11, person.getPersonId());
        stmt.execute();
    }

    public void deletePerson(Person person) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM person WHERE person_id =  ?");
        stmt.setInt(1, person.getPersonId());
        stmt.execute();
    }
/*
    public static void main(String[] args) throws Exception {
        ManagementSystem ms = new ManagementSystem();
        ms.getInstance();
        //ms.getDepartments();
        Department d = new Department();
        d.setDepartmentId(1);
        Department d2 = new Department();
        d.setDepartmentId(1);
        //ms.getPersonsFromDepartment(d,1991);
        //System.out.println(ms.getPersonById(2));
        //ms.movePersonsToDepartment(d,1992,d,1991);
        //ms.removePersonsFromDepartment(d,1991);
        *//*Person p = new Person();
        p.setFirstName("Григорій");
        p.setSurName("Чаплій");
        p.setPatronymic("Володимирович");
        p.setRank("старший лейтенант");
        p.setSex('Ч');
        p.setDateOfBirth(11,11,1992);
        p.setDepartmentId(1);
        p.setPersonId(7);
        p.setPosition("інженер відділення інформаційного забезпечення відділу програмного забезпечення ЦІС");
        //ms.updatePerson(p);
        ms.getAllPersons();
        Person p2 = new Person();
        p2.setPersonId(7);
        ms.deletePerson(p2);*//*
        ms.getAllPersons();
    }*/
}
