package app

import org.junit.*
import static org.junit.Assert.*

class CalculatorTest{

  @Test
  public void testSum(){
    assertEquals 5, new Calculator().sum(2,3)
  }
}
