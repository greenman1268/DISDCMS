package logic;

/** Created on 30.03.2016 */

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
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

    }

    public static synchronized ManagementSystem getInstance() throws Exception{

        if(instance == null){
            instance = new ManagementSystem();
            Properties props = new Properties();
            FileInputStream fis = null;
            MysqlDataSource mysqlDS = null;
            try {

                mysqlDS = new MysqlDataSource();
                mysqlDS.setURL("jdbc:mysql://localhost:3306/staff");
                mysqlDS.setUser("root");
                mysqlDS.setPassword("126874539");
                instance.dataSource = mysqlDS;
                con =  dataSource.getConnection();

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
          //  System.out.println(dep);
        }
        rs.close();
        stmt.close();
        return departments;
    }

    public Collection getAllPersons() throws SQLException {
        Collection persons = new ArrayList();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT person_id, firstName, patronymic, surName, "
                + "sex, birthDay, department_id, position_name, rank FROM person ORDER BY surName, firstName, patronymic");
        while (rs.next()) {
            Person st = new Person(rs);
            persons.add(st);
           System.out.println(st);
        }
        rs.close();
        stmt.close();
        return persons;
    }


    public Collection getPersonsFromDepartment(Department dep) throws SQLException {
        Collection persons = new ArrayList();
        PreparedStatement stmt = con.prepareStatement("SELECT person_id, firstName, patronymic, surName, "
                + "sex, birthDay, department_id, position_name, rank FROM person "
                + "WHERE department_id = ? "
                + "ORDER BY surName, firstName, patronymic");
        stmt.setInt(1, dep.getDepartmentId());

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Person st = new Person(rs);
            persons.add(st);
           // System.out.println(st);
        }
        rs.close();
        stmt.close();
        return persons;
    }


    public Person getPersonById(int personId) throws SQLException {
        Person person = null;
        PreparedStatement stmt = con.prepareStatement("SELECT person_id, firstName, patronymic, surName,"
                 + "sex, birthDay, department_id, position_name, rank FROM person WHERE person_id = ?");
        stmt.setInt(1, personId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            person = new Person(rs);
            //System.out.println(person);
        }
        rs.close();
        stmt.close();
        return person;
    }

    public void movePersonsToDepartment(Department oldDepartment, Department newDepartment, String year) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("UPDATE person SET department_id = ? "
                + "WHERE department_id = ? AND (YEAR(birthDay))= ? ");
        stmt.setInt(1, newDepartment.getDepartmentId());
        stmt.setInt(2, oldDepartment.getDepartmentId());
        stmt.setString(3, year);
        stmt.execute();
    }

    public void removePersonsFromDepartment(Department dep, String year) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM person WHERE department_id = ? AND (YEAR(birthDay)) = ?");
        stmt.setInt(1, dep.getDepartmentId());
        stmt.setString(2, year);
        stmt.execute();
    }

    public void insertPerson(Person person) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO person "
                + "(person_id, firstName, patronymic, surName,"
                + "sex, birthDay, department_id, position_name, rank)"
                + "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setInt(1, person.getPersonId());
        stmt.setString(2, person.getFirstName());
        stmt.setString(3, person.getPatronymic());
        stmt.setString(4, person.getSurName());
        stmt.setString(5, new String(new char[]{person.getSex()}));
        stmt.setDate(6, new java.sql.Date(person.getDateOfBirth().getTimeInMillis()));
        stmt.setInt(7, person.getDepartmentId());
        stmt.setString(8, person.getPosition());
        stmt.setString(9, person.getRank());
        stmt.execute();
    }

    public void updatePerson(Person person) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("UPDATE person "
                + "SET firstName=?, patronymic=?, surName=?,"
                + "sex=?, birthDay=?, department_id=?, position_name=?, rank=? WHERE person_id=?");

        stmt.setString(1, person.getFirstName());
        stmt.setString(2, person.getPatronymic());
        stmt.setString(3, person.getSurName());
        stmt.setString(4, new String(new char[]{person.getSex()}));
        stmt.setDate(5, new java.sql.Date(person.getDateOfBirth().getTimeInMillis()));
        stmt.setInt(6, person.getDepartmentId());
        stmt.setString(7, person.getPosition());
        stmt.setString(8, person.getRank());
        stmt.setInt(9, person.getPersonId());
        stmt.execute();
    }

    public void deletePerson(Person person) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DELETE FROM person WHERE person_id =  ?");
        stmt.setInt(1, person.getPersonId());
        stmt.execute();
    }
  /* public static void main(String[] args) throws Exception {
       ManagementSystem ms = new ManagementSystem();
       ms.getInstance();

       Department dep = new Department();
       dep.setDepartmentId(1);
       Department dep2 = new Department();
       dep2.setDepartmentId(2);
      // ms.movePersonsToDepartment(dep2,dep,"1992");
      // ms.removePersonsFromDepartment(dep,"1992");
     //ms.getPersonsFromDepartment(dep2,"1992");
      // ms.getPersonById(1);
   }*/

}

