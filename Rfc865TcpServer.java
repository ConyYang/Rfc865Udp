import java.net.*;
import java.io.*;

public class Rfc865TcpServer {
    static ServerSocket parentSocket;
    public static void main(String[] argv) {
        // 1. Open TCP socket at well-known port
        try {
            parentSocket = new ServerSocket(17);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        while (true) {
            try {
                // 2. Listen to establish TCP connection with clnt
                Socket childSocket = parentSocket.accept();
                // 3. Create new thread to handle client connection
                ClientHandler client =
                        new ClientHandler(childSocket);
                Thread thread = new Thread(client);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } }

class ClientHandler implements Runnable {
    private Socket socket;

    ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Client accepted");

        // takes input from the client socket
        try {
            // 4. Receive TCP request from client
            byte[] request = new byte[512];
            InputStream is = socket.getInputStream();
            System.out.println("Waiting for request...");
            is.read(request);
            String requestString = new String(request);
            System.out.println(requestString);

            // 5. Send TCP reply to client
            OutputStream os = socket.getOutputStream();
            byte[] quoteByte = "Yang Yubei".getBytes();
            os.write(quoteByte);

        } catch (IOException e) {
            e.printStackTrace();
        }


        String line = "";

    } }