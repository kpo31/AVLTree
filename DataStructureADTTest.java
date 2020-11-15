//////////////////////////////////////////////////////////////

// Title: (DataStructureADTTest.java)
// Course: (CS 400 Fall 2019)
//
// Author: (Mihir Khatri)
// Email: (mkhatri@wisc.edu)
// Lecturer's Name: (Debra Deppeler)
//////////////////////////////////////////////////////////////
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {

  private T dataStructureInstance;

  protected abstract T createInstance();

  @BeforeAll
  static void setUpBeforeClass() throws Exception {}

  @AfterAll
  static void tearDownAfterClass() throws Exception {}

  @BeforeEach
  void setUp() throws Exception {
    dataStructureInstance = createInstance();
  }

  @AfterEach
  void tearDown() throws Exception {
    dataStructureInstance = null;
  }


  @Test
  /**
   * Given test method
   */
  void test00_empty_ds_size() {
    if (dataStructureInstance.size() != 0)
      fail("data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
  }


  @Test
  /**
   * Tests if the size increases after one value is inserted
   */
  void test01_after_insert_one_size_is_one() {
    dataStructureInstance.insert("a", "Value1");

    if (dataStructureInstance.size() != 1) {// checks if insert is working
      fail("test01_after_insert_one_size_is_one() failed");
    }
  }

  @Test
  /**
   * Tests if the remove method functions correctly
   */
  void test02_after_insert_one_remove_one_size_is_0() {
    dataStructureInstance.insert("a", "Value1");
    dataStructureInstance.remove("a");// size should be two after removing a
    if (dataStructureInstance.size() != 0) {
      fail("test02_after_insert_one_remove_one_size_is_0() failed");
    }
  }

  @Test
  /**
   * Tests if duplicate exception is thrown
   */
  void test03_duplicate_exception_is_thrown() {
    try {
      dataStructureInstance.insert("a", "Value1");
      dataStructureInstance.insert("c", "Value2");
      dataStructureInstance.insert("a", "Value3");// there already is a key so an error should be
                                                  // thrown
      fail("test03_duplicate_exception_is_thrown() failed");
    } catch (RuntimeException ex) {

    }

  }

  @Test
  /**
   * Tests if false is returned when key is not present for remove
   */
  void test04_remove_returns_false_when_key_not_present() {
    dataStructureInstance.insert("a", "Value1");
    dataStructureInstance.insert("b", "Value2");
    dataStructureInstance.insert("c", "Value3");
    if (dataStructureInstance.remove("d") == true) {// there is no d key
      fail("test04_remove_returns_false_when_key_not_present() failed");
    }
  }

  @Test
  /**
   * Check if Illegal Argument is Exception is thrown with get
   */
  void test05_illegal_argument_exception_is_thrown() {
    try {
      dataStructureInstance.insert("a", "Value1");
      dataStructureInstance.insert("b", "Value2");
      dataStructureInstance.insert("c", "Value3");
      dataStructureInstance.insert("d", "Value4");
      dataStructureInstance.get(null);// there is no key null, error should be thrown
      fail("test05_illegal_argument_exception_is_thrown() failed");
    } catch (IllegalArgumentException ex) {
      /* expected */
    } catch (Exception e) {
      fail("test05 failed. An unexpected exception was thrown.");
    }
  }

  @Test
  /**
   * Tests if we can find a certain key
   */
  void test06_contains_returns_true_when_found() {
    dataStructureInstance.insert("a", "Value1");
    dataStructureInstance.insert("b", "Value2");
    dataStructureInstance.insert("c", "Value3");
    dataStructureInstance.insert("d", "Value4");
    if (dataStructureInstance.contains("a") != true && dataStructureInstance.contains("b") != true
        && dataStructureInstance.contains("d") != true) {// finds if there is an a, b, d key in the
                                                         // data structure
      fail("test06_contains_returns_true_when_found()");
    }
  }
}
