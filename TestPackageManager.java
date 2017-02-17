/**
 * Class to parse and store command line.
 */
public class TestPackageManager implements IUnitTest {
	PackageManager manager;
	
	TestPackageManager() {
		manager = new PackageManager();
	}

	public void test() {
		/* no packages installed */
		testQuery("A", false);
		testQuery("B", false);
		testQuery("C", false);

		/* remove non-existant package should pass */
		testRemove("A", true);
		testRemove("B", true);
		testRemove("C", true);

		/* self referencing */
		testIndex("A", new String[] {"A"}, false);
		testQuery("A", false);

		/* dependencies not installed */
		testIndex("A", new String[] {"B", "C"}, false);
		testQuery("A", false);
		testQuery("B", false);
		testQuery("C", false);

		/* No dependency */
		testIndex("A", new String[] {}, true);
		testQuery("A", true);
		testRemove("A", true);
		testQuery("A", false);

		/* Two dependencies */
		testIndex("C", new String[] {}, true);
		testQuery("A", false);
		testQuery("B", false);
		testQuery("C", true);
		testIndex("B", new String[] {"C"}, true);
		testQuery("A", false);
		testQuery("B", true);
		testQuery("C", true);
		testIndex("A", new String[] {"B", "C"}, true);
		testQuery("A", true);
		testQuery("B", true);
		testQuery("C", true);

		/* Remove package referenced by another */
		testRemove("B", false);
		testQuery("A", true);
		testQuery("B", true);
		testQuery("C", true);

		/* Remove package referenced by another */
		testRemove("C", false);
		testQuery("A", true);
		testQuery("B", true);
		testQuery("C", true);

		/* Remove package not referenced by another */
		testRemove("A", true);
		testQuery("A", false);
		testQuery("B", true);
		testQuery("C", true);

		/* Remove package not referenced by another */
		testRemove("B", true);
		testQuery("A", false);
		testQuery("B", false);
		testQuery("C", true);

		/* Remove package not referenced by another */
		testRemove("C", true);
		testQuery("A", false);
		testQuery("B", false);
		testQuery("C", false);
	}

	private void testIndex(String packageName, String[] depends, boolean expect) {
		boolean result = manager.index(packageName, depends);

		if (expect != result) {
			StringBuilder sb = new StringBuilder();

			sb.append("index(\"");
			sb.append(packageName);
			sb.append("\", [");
			for (int i = 0; i < depends.length; ++i) {
				if (i != 0) {
					sb.append(", ");
				}
				sb.append("\"");
				sb.append(depends[i]);
				sb.append("\"");
			}
			sb.append("])");
			UnitTest.assertEquals(sb.toString(), expect, result);
		}
	}

	private void testQuery(String packageName, boolean expect) {
		boolean result = manager.query(packageName);

		if (expect != result) {
			UnitTest.assertEquals("query(\"" + packageName + "\")", expect, result);
		}
	}

	private void testRemove(String packageName, boolean expect) {
		boolean result = manager.remove(packageName);

		if (expect != result) {
			UnitTest.assertEquals("remove(\"" + packageName + "\")", expect, result);
		}
	}
}
