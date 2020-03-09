
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.*;

public class ServerSocket {
    static DatagramSocket socket; static int PORT = 9876;
    static int READ_BUFFER_SIZE = 512;
    static byte[] receiveData = new byte[READ_BUFFER_SIZE];
    static byte[] sendData;
    //= new byte[READ_BUFFER_SIZE];

    public static void main(String[] argv) {
        // 1. Open UDP socket at well-known port
        try {
            socket = new DatagramSocket(PORT);
        } catch (SocketException e) {
            System.err.println("Failed to create Datagram Socket.");
            e.printStackTrace();
        }
        while (true) {
            System.out.println("Waiting for message...");
            try {
                // 2. Listen for UDP request from client
                DatagramPacket request = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(request);
                String sentence = new String(request.getData());
                System.out.println("RECEIVED: " + sentence);
                InetAddress IPAddress = request.getAddress();
                int port = request.getPort();
                sendData = sentence.getBytes();
                // 3. Send UDP reply to client
                DatagramPacket reply = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                socket.send(reply);
            } catch (IOException e) {
                System.err.println("Failed to receive/send packet.");
                e.printStackTrace();
            }
        }
    } }
