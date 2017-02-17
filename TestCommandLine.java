/**
 * Class to parse and store command line.
 */
public class TestCommandLine implements IUnitTest {
	public void test() {
		test("", null, null, null);
		test("|", null, null, null);
		test("||", null, null, null);
		test("|||", null, null, null);
		test("INDEX", null, null, null);
		test("INDEX|", null, null, null);
		test("INDEX||", null, null, null);
		test("INDEX|  |", null, null, null);
		test("INDEX|component", null, null, null);
		test("INDEX|component|", "INDEX", "component", new String[] {});
		test("INDEX|component|,,", null, null, null);
		test("   INDEX  |   component  |", null, null, null);
		test("INDEX|component-x|", "INDEX", "component-x", new String[] {});
		test("INDEX|component x|", null, null, null);
		test("INDEX|component|a-x,b-x,c-x", "INDEX", "component", new String[] {"a-x", "b-x", "c-x"});
		test("INDEX|component|a x,b x,c x", null, null, null);
		test("INDEX|component|a,,,b,c", null, null, null);
		test("   INDEX  |   component  |   a , ,  b ,c  ", null, null, null);
	}

	private void test(String line, String command, String thePackage, String[] depends) {
		CommandLine cl = null;
		IllegalArgumentException ex = null;

		try {
			cl = new CommandLine(line);
		} catch (IllegalArgumentException e) {
			ex = e;
		}
		if (command == null) {
			// expecting exception
			UnitTest.assertFalse(ex == null);
			UnitTest.assertTrue(cl == null);
		} else {
			UnitTest.assertTrue(ex == null);
			UnitTest.assertFalse(cl == null);
			UnitTest.assertEquals(command, cl.getCommand());
			UnitTest.assertEquals(thePackage, cl.getPackage());
			UnitTest.assertEquals(depends, cl.getDepends());
		}
	}
}
