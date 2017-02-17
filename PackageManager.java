import java.util.HashMap;
import java.util.Map;

public class PackageManager {
	private Map<String, Package> packages;

	public PackageManager() {
		packages = new HashMap<String, Package>();
	}

	/**
	 * Add/update a package in the package manager.
	 *
	 * @param packageName The name of the package.
	 * @param depends The packages that this package depends on.
	 *
	 * @return true if the package was successfully added or updated,
	 *   false otherwise.
	 */
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

	/**
	 * Remove a package from the package manager.
	 *
	 * @param packageName The name of the package.
	 *
	 * @return true if the package was removed or did not exist,
	 *   false otherwise.
	 */
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

	/**
	 * Query a package in the package manager.
	 *
	 * @param packageName The name of the package.
	 *
	 * @return true if the package is known by the package manager,
	 *   false otherwise.
	 */
	public synchronized boolean query(String packageName) {
		return packages.containsKey(packageName);
	}
}
