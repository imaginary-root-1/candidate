import java.util.HashMap;
import java.util.Map;

public class PackageManager {
	private Map<String, Package> packages;

	public PackageManager() {
		packages = new HashMap<String, Package>();
	}

	public synchronized boolean index(String packageName, String[] depends) {
		Package thePackage = packages.get(packageName);

		/* Check dependencies */
		for (String s : depends) {
			if (s.length() > 0 && !packages.containsKey(s)) {
				return false;
			}
		}

		/* Remove old dependency references */
		if (thePackage != null) {
			for (String s : thePackage.getDependsOn()) {
				if (s.length() > 0) {
					packages.get(s).removeReferencedBy(thePackage);
				}
			}
		}

		if (thePackage == null) {
			/* new package */
			thePackage = new Package(packageName, depends);
			packages.put(packageName, thePackage);
		} else {
			thePackage.setDependsOn(depends);
		}

		/* Add new dependency references */
		for (String s : depends) {
			if (s.length() > 0) {
				packages.get(s).addReferencedBy(thePackage);
			}
		}
		return true;
	}

	public synchronized boolean remove(String packageName) {
		Package thePackage = packages.get(packageName);

		if (thePackage == null) {
			return true;
		} else if (thePackage.getReferencedBy().size() == 0) {
			for (String s : thePackage.getDependsOn()) {
				if (s.length() > 0) {
					packages.get(s).removeReferencedBy(thePackage);
				}
			}
			packages.remove(packageName);
			return true;
		} else {
			return false;
		}
	}

	public synchronized boolean query(String packageName) {
		return packages.containsKey(packageName);
	}
}
