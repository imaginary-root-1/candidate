import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
	private Socket sock;
	private PackageManager manager;
	private static byte[] ok = "OK\n".getBytes();
	private static byte[] fail = "FAIL\n".getBytes();
	private static byte[] error = "ERROR\n".getBytes();

	public ClientHandler(Socket sock, PackageManager manager) {
		this.sock = sock;
		this.manager = manager;
	}

	public void run() {
		BufferedReader in = null;
		OutputStream out = null;

		try {
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = sock.getOutputStream();
			String commandLine;

			while ((commandLine = in.readLine()) != null) {
				try {
					boolean response = process(commandLine);
					out.write(response ? ok : fail);
				} catch (Exception e) {
					System.out.println("ClientHandler.run(): Caught exception handling line \"" + commandLine + "\": " + e);
					out.write(error);
				}
			}
		} catch (EOFException e) {
			/* Other side closed, we're done */
		} catch (Exception e) {
			System.out.println("ClientHandler.run(): Caught exception: " + e);
		}
	}

	private boolean process(String line) throws IllegalArgumentException {
		CommandLine cl = new CommandLine(line);
		String command = cl.getCommand();

		if (command.equals("INDEX")) {
			return manager.index(cl.getPackage(), cl.getDepends());
		} else if (command.equals("REMOVE")) {
			return manager.remove(cl.getPackage());
		} else if (command.equals("QUERY")) {
			return manager.query(cl.getPackage());
		} else {
			/* Should never happen, checked in command line parser */
			throw new IllegalArgumentException("Unknown command \"" + command + "\"");
		}
	}
}
