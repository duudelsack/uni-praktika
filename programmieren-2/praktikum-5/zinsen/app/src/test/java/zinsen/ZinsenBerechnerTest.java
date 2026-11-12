package zinsen;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;

public class ZinsenBerechnerTest {
  
  @Test
  public void testInvalidConstructorArguments(){
    assertThrows("must throw IllegalArgumentException on negative startKapital",
        IllegalArgumentException.class,
        () -> new ZinsenBerechner(-100d, 1000d, 3));
    assertThrows("must throw IllegalArgumentException on 0 startKapital",
        IllegalArgumentException.class,
        () -> new ZinsenBerechner(0d, 1000d, 3));
    assertThrows("must throw IllegalArgumentException on zielKapital less than startKapital",
        IllegalArgumentException.class,
        () -> new ZinsenBerechner(100d, 50d, 3));
    assertThrows("must throw IllegalArgumentException on zielKapital equal to startKapital",
        IllegalArgumentException.class,
        () -> new ZinsenBerechner(100d, 100d, 3));
    assertThrows("must throw IllegalArgumentException on negative zinssatz",
        IllegalArgumentException.class,
        () -> new ZinsenBerechner(100d, 1000d, -3));
    assertThrows("must throw IllegalArgumentException on 0 zinssatz",
        IllegalArgumentException.class,
        () -> new ZinsenBerechner(100d, 1000d, 0));
    assertThrows("must throw IllegalArgumentException on larger than 100% zinssatz",
        IllegalArgumentException.class,
        () -> new ZinsenBerechner(100d, 1000d, 101d));
    assertThrows("must throw IllegalArgumentException on 100% zinssatz",
        IllegalArgumentException.class,
        () -> new ZinsenBerechner(100d, 1000d, 100d));
  }

  
  @Test 
  public void testZinsBerechnerIterator(){
    ZinsenBerechner zb = new ZinsenBerechner(100d, 300d, 25);

    Iterator<Double> it = zb.iterator();

    assertNotNull("iterator must not return null", it);

    double[] kapital = {100.00, 125.00, 156.25, 195.31, 244.14};

    for (int i = 0; i < kapital.length; i++){
      assertTrue("hasNext must return true if kapital is less then zielKapital",
          it.hasNext());

      assertEquals("next must return correct kapital value", 
          kapital[i], it.next(), 1e-2);
    }

    assertFalse("hasNext must return false when kapital is greater than zielKapital",
        it.hasNext());

    assertThrows("next must throw NoSuchElementException when called if kapital is greater than zielKapital",
        NoSuchElementException.class,
        it::next);

  }
}
