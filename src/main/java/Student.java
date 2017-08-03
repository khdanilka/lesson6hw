/**
 * Created by android on 8/3/17.
 */
public class Student {

    private int index;
    private String name;
    private int mark;

    public Student(String name, int mark) {
        this.name = name;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public int getMark() {
        return mark;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (mark != student.mark) return false;
        return name != null ? name.equals(student.name) : student.name == null;
    }

}
