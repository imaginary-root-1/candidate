class TestUnitTest implements IUnitTest {
	public TestUnitTest() {
	}

	public void test() {
		boolean passed = false;
		AssertionError err = null;

		/* string */
		UnitTest.assertEquals("13", "13");
		UnitTest.assertEquals("Custom message", "42", "42");

		try {
			passed = false;
			UnitTest.assertEquals("13", "42");
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
			UnitTest.assertEquals("Custom message", "13", "42");
		} catch (AssertionError e) {
			passed = true;
			err = e;
		}
		UnitTest.assertTrue(passed);
		UnitTest.assertTrue(err.getMessage().contains("13"));
		UnitTest.assertTrue(err.getMessage().contains("42"));
		UnitTest.assertTrue(err.getMessage().contains("Custom message"));


		/* string array */
		UnitTest.assertEquals(new String[]{}, new String[]{});
		UnitTest.assertEquals(new String[]{"13"}, new String[]{"13"});
		UnitTest.assertEquals(new String[]{"13", "42"}, new String[]{"13", "42"});
		UnitTest.assertEquals("Custom message", new String[]{}, new String[]{});
		UnitTest.assertEquals("Custom message", new String[]{"13"}, new String[]{"13"});
		UnitTest.assertEquals("Custom message", new String[]{"13", "42"}, new String[]{"13", "42"});

		try {
			passed = false;
			UnitTest.assertEquals(new String[]{"13"}, new String[]{"42"});
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
			UnitTest.assertEquals(new String[]{"13"}, new String[]{"13", "42"});
		} catch (AssertionError e) {
			passed = true;
			err = e;
		}
		UnitTest.assertTrue(passed);
		UnitTest.assertFalse(err.getMessage().contains("Custom message"));

		try {
			passed = false;
			UnitTest.assertEquals("Custom message", new String[]{"13"}, new String[]{"42"});
		} catch (AssertionError e) {
			passed = true;
			err = e;
		}
		UnitTest.assertTrue(passed);
		UnitTest.assertTrue(err.getMessage().contains("13"));
		UnitTest.assertTrue(err.getMessage().contains("42"));
		UnitTest.assertTrue(err.getMessage().contains("Custom message"));

		try {
			passed = false;
			UnitTest.assertEquals("Custom message", new String[]{"13"}, new String[]{"13", "42"});
		} catch (AssertionError e) {
			passed = true;
			err = e;
		}
		UnitTest.assertTrue(passed);
		UnitTest.assertTrue(err.getMessage().contains("Custom message"));

		/* boolean */
		UnitTest.assertEquals(true, true);
		UnitTest.assertEquals("Custom message", false, false);

		try {
			passed = false;
			UnitTest.assertEquals(true, false);
		} catch (AssertionError e) {
			passed = true;
			err = e;
		}

		UnitTest.assertTrue(passed);
		UnitTest.assertTrue(err.getMessage().contains("true"));
		UnitTest.assertTrue(err.getMessage().contains("false"));
		UnitTest.assertFalse(err.getMessage().contains("Custom message"));

		try {
			passed = false;
			UnitTest.assertEquals("Custom message", true, false);
		} catch (AssertionError e) {
			passed = true;
			err = e;
		}
		UnitTest.assertTrue(passed);
		UnitTest.assertTrue(err.getMessage().contains("true"));
		UnitTest.assertTrue(err.getMessage().contains("false"));
		UnitTest.assertTrue(err.getMessage().contains("Custom message"));

		/* int */
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


		/* true */
		UnitTest.assertTrue(true);
		UnitTest.assertTrue("Custom message", true);

		try {
			passed = false;
			UnitTest.assertTrue(false);
		} catch (AssertionError e) {
			passed = true;
			err = e;
		}

		UnitTest.assertTrue(passed);
		UnitTest.assertFalse(err.getMessage().contains("Custom message"));

		try {
			passed = false;
			UnitTest.assertTrue("Custom message", false);
		} catch (AssertionError e) {
			passed = true;
			err = e;
		}
		UnitTest.assertTrue(passed);
		UnitTest.assertTrue(err.getMessage().contains("Custom message"));

		/* false */
		UnitTest.assertFalse(false);
		UnitTest.assertFalse("Custom message", false);

		try {
			passed = false;
			UnitTest.assertFalse(true);
		} catch (AssertionError e) {
			passed = true;
			err = e;
		}

		UnitTest.assertTrue(passed);
		UnitTest.assertFalse(err.getMessage().contains("Custom message"));

		try {
			passed = false;
			UnitTest.assertFalse("Custom message", true);
		} catch (AssertionError e) {
			passed = true;
			err = e;
		}
		UnitTest.assertTrue(passed);
		UnitTest.assertTrue(err.getMessage().contains("Custom message"));
	}
}
