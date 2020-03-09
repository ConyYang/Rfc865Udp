
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Rfc865TcpServer {
    static Socket socket;
    public static void main(String[] argv) {
        // 1. Establish TCP connection with server
        try {
            //Socket socket = new Socket(“127.0.0.1”, 5000)
            //IP address of Server.   TCP Port.
            socket = new Socket("127.0.0.1", 5000);
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
