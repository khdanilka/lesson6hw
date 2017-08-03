
import java.sql.*;

public class SQLDataBase implements BDConnection {

    private Connection connection;
    private Statement statement;


    public void init() {
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:bdTest.db");
            statement = connection.createStatement();
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }


    public void dispose()  {
        try{
            connection.close();
        } catch (SQLException e){
            throw  new RuntimeException(e);
        }

    }

    public void connectionAutoCommit(boolean b) throws SQLException{
        connection.setAutoCommit(b);
    }


    public int updateDataBase(Student student) throws SQLException{
        PreparedStatement ps = null;
        ps = connection.prepareStatement
                ("UPDATE Students SET Mark = ? WHERE name = ?");
        ps.setInt(1, student.getMark());
        ps.setString(2, student.getName());
        return ps.executeUpdate();
    }

    public Student getStudentWith(String name) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = connection.prepareStatement ("SELECT * FROM Students where name = ?");
        ps.setString(1,name);
        rs = ps.executeQuery();
        if (!rs.next()) return null;
        return new Student(rs.getString(2),rs.getInt(3));
    }

    public boolean addToDataBase(Student student) throws SQLException{

        if (getStudentWith(student.getName())!= null) return false;

        PreparedStatement ps = null;
        ps = connection.prepareStatement
                ("INSERT INTO Students (Name, Mark) VALUES(?, ?);");
        ps.setString(1, student.getName());
        ps.setInt(2, student.getMark());
        ps.executeUpdate();
        return true;
    }

    public boolean getDataBase() throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = connection.prepareStatement ("SELECT * FROM Students");
        rs = ps.executeQuery();

        if (!rs.next()) return false;
        while (rs.next()) {
            if ((rs.getString(2).equals(""))
                    || (rs.getInt(3) < 0))
                return false;
        }

        return true;
    }


//
//    public void deleteFromDataBase(int primaryKey) throws SQLException{
//        DOMParsing.logger.debug("Удаление элемента из БД по ключу" + primaryKey);
//        PreparedStatement ps = null;
//        ps = connection.prepareStatement
//                ("DELETE FROM Persons WHERE id = ?");
//        ps.setInt(1, primaryKey);
//        ps.executeUpdate();
//    }
//

//
//
//    public void addDataToDB(String title) throws SQLException {
//
//        connection.setAutoCommit(false);
//        PreparedStatement ps = connection.prepareStatement
//                ("INSERT INTO persons (DepCode, DepJob, Description) VALUES(?, ?, ?);");
//        for (int i = 1; i < 10001; i ++){
//                ps.setString(1,"12" + i);
//                ps.setString(2, title + i);
//                ps.setString(3,"всем одинаковое описание");
//                //ps.setInt(4, i);
//                ps.addBatch();
//        }
//        ps.executeBatch();
//        connection.commit();
//        connection.setAutoCommit(true);
//
//    }
//


}
