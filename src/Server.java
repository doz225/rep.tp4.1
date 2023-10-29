import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) {
        final int SERVER_PORT = 1234;
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(SERVER_PORT);

            byte[] receiveData = new byte[1024];

            System.out.println("Serveur UDP démarré sur le port " + SERVER_PORT);

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                System.out.println("Message reçu du client : " + clientMessage);

                String welcomeMessage = "Bienvenu " + clientMessage;
                byte[] sendData = welcomeMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            
                socket.close();
            
        }
    }
}

            