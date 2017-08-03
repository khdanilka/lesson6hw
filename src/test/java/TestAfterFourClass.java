import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class TestAfterFourClass {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        int[] a1 = {1, 2, 4, 0};
        int[] a2 = {0};

        int[] b1 = {1, 2, 1, 4};
        int[] b2 = {};

        int[] c1 = {1, 2, 4, 0, 1, 2, 3};
        int[] c2 = {0, 1, 2, 3};

        Object[][] data = new Object[][]{
                {a1, a2},
                {b1, b2},
                {c1, c2}
        };

        return Arrays.asList(data);
    }

    private int[] a;
    private int[] b;

    public TestAfterFourClass(int[] a, int[] b) {
        this.a = a;
        this.b = b;
    }

    MainClass mainClass;

    @Before
    public void init() {
        mainClass = new MainClass();
    }

    @Test
    public void after4Test() {
        Assert.assertArrayEquals(mainClass.arrAfter4(a), b);
    }

    @Test(expected = RuntimeException.class)
    public void after4TestException(){
        mainClass.arrAfter4(b);
    }


}
