import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Class to parse and store command line.
 */
public class CommandLine {
	private String command;
	/* can't use package, that's a reserved word */
	private String thePackage;
	private String[] depends;

	private static Pattern commandSplit = Pattern.compile("[|]");
	private static Pattern dependsSplit = Pattern.compile("[,]");
	private static Pattern alphaNumericDash = Pattern.compile("^[a-zA-Z0-9_+=-]+$");
	private static String[] commands = new String[] {"INDEX", "QUERY", "REMOVE"};
	private static Set<String> commandSet;

	static {
		commandSet = new HashSet<String>();
		for (String s : commands) {
			commandSet.add(s);
		}
	}

	/**
	 * Parse a raw command line into its component parts.
	 *
	 * @param line The command line.
	 * @exception IllegalArgumentException line is not a legal
	 *   command line.
	 */
	public CommandLine(String line) {
		String[] commandArray = commandSplit.split(line, 3);

		if (commandArray.length != 3) {
			throw new IllegalArgumentException("Illegal command line, needs 3 fields and has " + commandArray.length);
		}
		command = commandArray[0];
		thePackage = commandArray[1];
		if (commandArray[2].length() == 0) {
			depends = new String[0];
		} else {
			depends = dependsSplit.split(commandArray[2]);
			/*
			 * Special case:
			 *
			 * "," returns String[0], should be illegal
			 */
			if (depends.length == 0) {
				throw new IllegalArgumentException("Illegal dependencies \"" + commandArray[2] + "\"");
			}
		}
		checkValidCommand(command);
		checkValidPackageName(thePackage);
		for (String s : depends) {
			checkValidPackageName(s);
		}
	}

	/**
	 * Validate a command.
	 *
	 * @param The command to validate.
	 */
	private void checkValidCommand(String command) {
		if (!commandSet.contains(command)) {
			throw new IllegalArgumentException("Illegal command \"" + command + "\"");
		}
	}

	/**
	 * Validate a package name.
	 *
	 * @param The package name to validate.
	 */
	private void checkValidPackageName(String packageName) {
		if (!alphaNumericDash.matcher(packageName).matches()) {
			throw new IllegalArgumentException("Illegal package name \"" + packageName + "\"");
		}
	}

	/**
	 * Return the command.
	 *
	 * @return The command.
	 */
	public String getCommand() { return command; }

	/**
	 * Return the package name.
	 *
	 * @return The package name.
	 */
	public String getPackage() { return thePackage; }

	/**
	 * Return the depends packages.
	 *
	 * @return The depends packages.
	 */
	public String[] getDepends() { return depends; }
}
