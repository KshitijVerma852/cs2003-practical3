import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DMBServer {
    private final int port;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public DMBServer(int port) {
        this.port = port;
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            System.out.println("Client has connected to server.");

            bufferedReader = new BufferedReader(new InputStreamReader((clientSocket.getInputStream())));
            printWriter = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputFromClient = bufferedReader.readLine();
            System.out.println("Received " + inputFromClient);
        } catch (IOException ioException) {
            System.out.println("ioException = " + ioException);
        }
    }

    public static void main(String[] args) {
        DMBServer dmbServer = new DMBServer(Integer.parseInt(args[0]));
        dmbServer.startServer();
    }
}
