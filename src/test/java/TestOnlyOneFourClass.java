import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

@RunWith(Parameterized.class)
public class TestOnlyOneFourClass {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        int[] a1 = {1, 4, 1, 1, 1};
        int[] b1 = {1, 4, 4, 4, 1};
        int[] c1 = {1, 4, 1, 5, 1};
        int[] d1 = {4, 4, 4, 4, 4};

        Object[][] data = new Object[][]{
                {a1, true},
                {b1, true},
                {c1, false},
                {d1, false}
        };

        return Arrays.asList(data);
    }

    int[] arr;
    boolean b;

    public TestOnlyOneFourClass(int[] arr, boolean b) {
        this.arr = arr;
        this.b = b;
    }

    MainClass mainClass;

    @Before
    public void init() {
        mainClass = new MainClass();
    }

    @Test
    public void TestMethod() {
        Assert.assertEquals(b, mainClass.onlyOneOrFourInArray(arr));
    }


}
