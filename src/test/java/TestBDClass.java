import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class TestBDClass {

    private static SQLDataBase sql;

    @BeforeClass
    public static void initBd() {
        sql = new SQLDataBase();
        sql.init();
        try {
            sql.connectionAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void disposeBd() {
        sql.dispose();
    }


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {"vasya",100},
                {"zoya",120},
                {"ira",140},
                {"kira",160},
                {"petya",16}
        };

        return Arrays.asList(data);
    }

    Student student;

    public TestBDClass(String name, int mark) {
        student = new Student(name,mark);
    }

    MainClass mainClass;

    @Before
    public void init(){
        mainClass = new MainClass();
    }

    @Test
    public void testAddingToBD(){
        try {
            if (!sql.addToDataBase(student)) Assert.fail(); // нельзя добавлять студентов с одинаковыми фамилиями
            Student st = sql.getStudentWith(student.getName());
            Assert.assertEquals(student,st);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readingFromBD(){
        try {
            Assert.assertTrue(sql.getDataBase());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdatingBD(){
        try {
            if (sql.updateDataBase(student) == 0) Assert.fail(); // нельзя обновить студента которого нет в бд
            //if (sql.updateDataBase(student) == 0) return; // нет студента нет проблем, просто пойдем дальше
            Student st = sql.getStudentWith(student.getName());
            Assert.assertEquals(student,st);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
