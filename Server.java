package Project01;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        try {
            String clientName = args[0]; // Pass unique name for each server
            int port = 1099;

            ChatServer server = new ChatServer(clientName);
            Registry registry = LocateRegistry.getRegistry("localhost", port);
            registry.bind(clientName + "Server", server);

            System.out.println(clientName + " Server is ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
