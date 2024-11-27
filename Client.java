package Project01;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    private String clientName;
    private ChatInterface server;
    private ChatInterface targetServer;
    private String host = "localhost";
    private int port = 1099;

    public Client(String clientName, String host, int port) throws Exception {
        this.clientName = clientName;
        Registry registry = LocateRegistry.getRegistry(host, port);
        server = (ChatInterface) registry.lookup(clientName + "Server");
        server.registerClient(clientName, server);
        this.targetServer = null;
    }

    public void sendMessage(String message, String target) throws Exception {
        if(targetServer == null) {
            Registry registry = LocateRegistry.getRegistry(host, port);
            targetServer = (ChatInterface) registry.lookup(target + "Server");
        }
        server.broadcast(this.clientName, message);
        targetServer.broadcast(this.clientName, message);
    }

    public void printMessage(String message){
        System.out.println(message);
    }

    public static void main(String[] args) {
        try {
            String clientName = args[0];
            String host = "localhost"; 
            int port = 1099;           

            Client client = new Client(clientName, host, port);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Who would you like to message: ");
            String targetName;
            targetName = scanner.nextLine();

            System.out.print("Enter message: ");
            while (scanner.hasNextLine()) {
                System.out.print("Enter message: ");
                String message = scanner.nextLine();
                client.sendMessage(message, targetName);
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}