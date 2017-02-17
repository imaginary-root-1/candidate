import java.util.regex.Pattern;

/**
 * Class to parse and store command line.
 */
public class CommandLine {
	private String command;
	// can't use package, that's a reserved word
	private String thePackage;
	private String[] depends;
	private static Pattern commandSplit = Pattern.compile("\\s*[|]\\s*");
	private static Pattern dependsSplit = Pattern.compile("\\s*[,]\\s*");

	/**
	 * Parse a raw command line into its component parts.
	 *
	 * @param line The command line.
	 * @exception IllegalArgumentException line is not a legal
	 *   command line.
	 */
	public CommandLine(String line) {
		String[] commandArray = commandSplit.split(line.trim(), 3);

		if (commandArray.length < 2 || commandArray[0].length() == 0 || commandArray[1].length() == 0) {
			throw new IllegalArgumentException("Illegal command line");
		} else {
			command = commandArray[0];
			thePackage = commandArray[1];
			if (commandArray.length == 2) {
				depends = new String[0];
			} else {
				String dependsLine = commandArray[2].trim();

				depends = (dependsLine.length() == 0) ? new String[0] : dependsSplit.split(commandArray[2].trim());
			}
		}
	}

	/**
	 * Return the command.
	 *
	 * @return The command.
	 */
	public String getCommand() { return command; }

	/**
	 * Return the package.
	 *
	 * @return The package.
	 */
	public String getPackage() { return thePackage; }

	/**
	 * Return the depends.
	 *
	 * @return The depends.
	 */
	public String[] getDepends() { return depends; }
}
