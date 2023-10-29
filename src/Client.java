import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        final int SERVER_PORT = 1234;
        final String SERVER_IP = "localhost"; // Adresse IP du serveur (ou adresse IP de la machine serveur)

        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(SERVER_IP);

            String message = "Prénom Nom";
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
            socket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            String welcomeMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println("Message reçu du serveur : " + welcomeMessage);
            System.out.println("Adresse du serveur : " + receivePacket.getAddress().getHostAddress());
            System.out.println("Port du serveur : " + receivePacket.getPort());
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}