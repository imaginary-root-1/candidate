import java.util.*;

/**
 * This class stores the attributes of a package.
 */
public class Package {
	private String name;
	private String[] dependsOn;
	private Set<Package> referencedBy;

	/**
	 * Create a new Package.
	 *
	 * @param name The name of the package.
	 * @param dependsOn The packages that this package depends on.
	 */
	public Package(String name, String[] dependsOn) {
		this.name = name;
		this.dependsOn = dependsOn;
		referencedBy = new HashSet<Package>();
	}

	/**
	 * Get the name of the package.
	 *
	 * @return The name of the package.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the packages that this package depends on.
	 *
	 * @return The packages that this package depends on.
	 */
	public String[] getDependsOn() {
		return dependsOn;
	}

	/**
	 * Set the packages that this package depends on.
	 *
	 * @param dependsOn The packages that this package depends on.
	 */
	public void setDependsOn(String[] dependsOn) {
		this.dependsOn = dependsOn;
	}

	/**
	 * Add a package that references this package.
	 *
	 * @param thePackage The packages that references this package.
	 */
	public void addReferencedBy(Package thePackage) {
		/* don't allow self-referencing */
		if (thePackage != this) {
			referencedBy.add(thePackage);
		}
	}

	/**
	 * Remove a package that references this package.
	 *
	 * @param thePackage The packages that references this package.
	 */
	public void removeReferencedBy(Package thePackage) {
		referencedBy.remove(thePackage);
	}

	/**
	 * Get the set of packages that reference this package.
	 *
	 * @return The set of packages that reference this package.
	 */
	public Set<Package> getReferencedBy() {
		return referencedBy;
	}
}
