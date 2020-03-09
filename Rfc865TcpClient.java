import java.io.*;
import java.net.*;

public class Rfc865TcpClient {
    static Socket socket;
    public static void main(String[] argv) {
        // 1. Establish TCP connection with server
        try {
            //IP address of Server.   TCP Port.
            socket = new Socket(InetAddress.getLocalHost(), 17);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        try {
            // 2. Send TCP request to server
            OutputStream os = socket.getOutputStream();
            byte[] buf = "Yang Yubei, SEP1, 172.0.0.45".getBytes("UTF-8");
            os.write(buf);

            // 3. Receive TCP reply from server
            InputStream is = socket.getInputStream();
            byte[] quoteBuf = new byte[512];
            is.read(quoteBuf);
            String quote = new String(quoteBuf);
            System.out.println(quote);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
