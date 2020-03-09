
import java.io.*;
import java.net.*;
import java.net.InetAddress;

import static java.net.InetAddress.getByName;

public class ClientSocket {
    static DatagramSocket socket;
    static int PORT = 9876;
    static InetAddress IPAddress;
    static byte[] sendData;
    //= new byte[1024];
    static byte[] receiveData = new byte[1024];
    static String responseMessage;
    public static void main(String[] argv) {
        // 1. Open UDP socket
        try {
            IPAddress  = InetAddress.getByName("localhost");
            System.out.println(IPAddress.getHostName());
        } catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            System.err.println("Failed to create Datagram Socket.");
            e.printStackTrace();
        }
        try {
            // 2. Send UDP request to server
            String sentence;
            try (BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in))) {
                sentence = inFromUser.readLine();
            }
            sendData = sentence.getBytes();
            DatagramPacket request = new DatagramPacket (sendData, sendData.length, IPAddress,PORT);
            socket.send(request);
            // 3. Receive UDP reply from server
            DatagramPacket reply =  new DatagramPacket(receiveData, receiveData.length);
            socket.receive(reply);
            responseMessage = new String(reply.getData());
            System.out.println("Received message \"" + responseMessage.trim() + "\".");
        } catch (IOException e) {
            System.err.println("Failed to receive/send packet.");
            e.printStackTrace();
        }
    }
}