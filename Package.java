import java.util.*;

public class Package {
	private String name;
	private String[] dependsOn;
	private Set<Package> referencedBy;

	public Package(String name, String[] dependsOn) {
		this.name = name;
		this.dependsOn = dependsOn;
		referencedBy = new HashSet<Package>();
	}

	public String getName() {
		return name;
	}

	public String[] getDependsOn() {
		return dependsOn;
	}

	public void setDependsOn(String[] dependsOn) {
		this.dependsOn = dependsOn;
	}

	public void addReferencedBy(Package thePackage) {
		/* don't allow self-referencing */
		if (thePackage != this) {
			referencedBy.add(thePackage);
		}
	}

	public void removeReferencedBy(Package thePackage) {
		referencedBy.remove(thePackage);
	}

	public Set<Package> getReferencedBy() {
		return referencedBy;
	}
}
