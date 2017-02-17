import java.io.*;
import java.net.*;

public class PackageServer {
	PackageManager manager;

	public static void main(String[] args) {
		try {
			PackageServer server = new PackageServer();
			server.serve();
		} catch (Exception e) {
			System.out.println("Exception in server: " + e);
		}
	}

	PackageServer() {
		manager = new PackageManager();
	}

	public void serve() throws IOException {
		System.out.println("Package server starting");

		ServerSocket ss = new ServerSocket(8080);

		System.out.println("Package server started");
		for (;;) {
			try {
				new Thread(new ClientHandler(ss.accept(), manager)).start();
			} catch (Exception e) {
				System.out.println("Exception while accepting: " + e);
			}
		}
	}
}
