class TestUnitTest implements IUnitTest {
	public TestUnitTest() {
	}

	public void test() {
		boolean passed = false;
		AssertionError err = null;

		UnitTest.assertEquals(13, 13);
		UnitTest.assertEquals("Custom message", 42, 42);

		try {
			passed = false;
			UnitTest.assertEquals(13, 42);
		} catch (AssertionError e) {
			passed = true;
			err = e;
		}
		UnitTest.assertTrue(passed);
		UnitTest.assertTrue(err.getMessage().contains("13"));
		UnitTest.assertTrue(err.getMessage().contains("42"));
		UnitTest.assertFalse(err.getMessage().contains("Custom message"));

		try {
			passed = false;
			UnitTest.assertEquals("Custom message", 13, 42);
		} catch (AssertionError e) {
			passed = true;
			err = e;
		}
		UnitTest.assertTrue(passed);
		UnitTest.assertTrue(err.getMessage().contains("13"));
		UnitTest.assertTrue(err.getMessage().contains("42"));
		UnitTest.assertTrue(err.getMessage().contains("Custom message"));
	}
}
