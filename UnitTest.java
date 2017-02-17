/**
 * The unit testing framework.
 * Run this class, and it will run all the unit tests.
 *
 * I would normally use junit, but the rules say I'm only allowed to
 * use standard libraries. I'm reinventing the wheel, but this one is
 * from the stone age.
 */

import java.util.Arrays;

public class UnitTest {
	/**
	 * Run all tests.
	 *
	 * @param args Ignored.
	 */
	public static void main(String[] args) {
		/* I could write a custom classloader that 
		 * finds all classes that implement IUnitTest, but that's
		 * way too complex for this assignment.
		 */
		test(new TestUnitTest());
		test(new TestCommandLine());
		test(new TestPackageManager());
		System.out.println("Unit tests passed");
		System.exit(0);
	}

	private static void test(IUnitTest unit) {
		try {
			System.out.println("testing " + unit.getClass().getName());
			unit.test();
		} catch (AssertionError e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.out.println("Unit tests failed");
			System.exit(1);
		}
	}

	/**
	 * Assert that two strings are equal.
	 *
	 * @param expect The expected value.
	 * @param actual The actual value.
	 *
	 * @exception AssertionError The assertion failed.
	 */
	public static void assertEquals(String expect, String actual) {
		assertEquals(null, expect, actual);
	}

	/**
	 * Assert that two strings are equal.
	 *
	 * @param msg A custom message, prepended to the error message.
	 * @param expect The expected value.
	 * @param actual The actual value.
	 *
	 * @exception AssertionError The assertion failed.
	 */
	public static void assertEquals(String msg, String expect, String actual) {
		boolean result = true;

		if (expect == null) {
			result = (actual == null);
		} else {
			result = expect.equals(actual);
		}
		if (!result) {
			throwError(msg, String.valueOf(expect), String.valueOf(actual));
		}
	}

	/**
	 * Assert that two string arrays are equal.
	 *
	 * @param expect The expected value.
	 * @param actual The actual value.
	 *
	 * @exception AssertionError The assertion failed.
	 */
	public static void assertEquals(String[] expect, String[] actual) {
		assertEquals(null, expect, actual);
	}

	/**
	 * Assert that two string arrays are equal.
	 *
	 * @param msg A custom message, prepended to the error message.
	 * @param expect The expected value.
	 * @param actual The actual value.
	 *
	 * @exception AssertionError The assertion failed.
	 */
	public static void assertEquals(String msg, String[] expect, String[] actual) {
		if (expect == null) {
			throwError(msg, "is null", "is not null");
		} else if (actual == null) {
			throwError(msg, "is not null", "is null");
		} else if (expect.length != actual.length) {
			throwError(msg, "has length " + expect.length, "has length " + actual.length);
		} else {
			for (int i = 0; i < expect.length; ++i) {
				assertEquals(msg, expect[i], actual[i]);
			}
		}
	}

	/**
	 * Assert that two booleans are equal.
	 *
	 * @param expect The expected value.
	 * @param actual The actual value.
	 *
	 * @exception AssertionError The assertion failed.
	 */
	public static void assertEquals(boolean expect, boolean actual) {
		assertEquals(null, expect, actual);
	}

	/**
	 * Assert that two booleans are equal.
	 *
	 * @param msg A custom message, prepended to the error message.
	 * @param expect The expected value.
	 * @param actual The actual value.
	 *
	 * @exception AssertionError The assertion failed.
	 */
	public static void assertEquals(String msg, boolean expect, boolean actual) {
		if (actual != expect) {
			throwError(msg, String.valueOf(expect), String.valueOf(actual));
		}
	}

	/**
	 * Assert that two integers are equal.
	 *
	 * @param expect The expected value.
	 * @param actual The actual value.
	 *
	 * @exception AssertionError The assertion failed.
	 */
	public static void assertEquals(int expect, int actual) {
		assertEquals(null, expect, actual);
	}

	/**
	 * Assert that two integers are equal.
	 *
	 * @param msg A custom message, prepended to the error message.
	 * @param expect The expected value.
	 * @param actual The actual value.
	 *
	 * @exception AssertionError The assertion failed.
	 */
	public static void assertEquals(String msg, int expect, int actual) {
		if (actual != expect) {
			throwError(msg, String.valueOf(expect), String.valueOf(actual));
		}
	}

	/**
	 * Assert that the result is true.
	 *
	 * @param actual The actual value.
	 *
	 * @exception AssertionError The assertion failed.
	 */
	public static void assertTrue(boolean actual) {
		assertTrue(null, actual);
	}

	/**
	 * Assert that the result is true.
	 *
	 * @param msg A custom message, prepended to the error message.
	 * @param actual The actual value.
	 *
	 * @exception AssertionError The assertion failed.
	 */
	public static void assertTrue(String msg, boolean actual) {
		if (!actual) {
			throwError(msg, String.valueOf(true), String.valueOf(actual));
		}
	}

	/**
	 * Assert that the result is false.
	 *
	 * @param actual The actual value.
	 *
	 * @exception AssertionError The assertion failed.
	 */
	public static void assertFalse(boolean actual) {
		assertFalse(null, actual);
	}

	/**
	 * Assert that the result is false.
	 *
	 * @param msg A custom message, prepended to the error message.
	 * @param actual The actual value.
	 *
	 * @exception AssertionError The assertion failed.
	 */
	public static void assertFalse(String msg, boolean actual) {
		if (actual) {
			throwError(msg, String.valueOf(false), String.valueOf(actual));
		}
	}

	/**
	 * Throw an exception
	 *
	 * @param msg A custom message, prepended to the error message.
	 * @param expect The expected value.
	 * @param actual The actual value.
	 *
	 * @exception AssertionError The exception created by this method.
	 */
	private static void throwError(String msg, String expect, String actual) {
		StringBuilder sb = new StringBuilder();

		if (msg != null) {
			sb.append(msg).append(": ");
		}
		sb.append("expecting " + expect + ", actual " + actual);
		throw new AssertionError(sb.toString());
	}
}
