package Project01;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatServer extends UnicastRemoteObject implements ChatInterface {
    private String clientName;
    private List<ChatInterface> clients = new ArrayList<>();

    protected ChatServer(String name) throws RemoteException {
        this.clientName = name;
    }

    @Override
    public void sendMessage(String message, String sender) throws RemoteException {
        System.out.println("[" + sender + "]: " + message);
    }

    @Override
    public void registerClient(String clientName, ChatInterface clientRef) throws RemoteException {
        clients.add(clientRef);
        System.out.println(clientName + " registered with the server.");
    }

    @Override
    public void broadcastMessage(String message, String sender) throws RemoteException {
        for (ChatInterface client : clients) {
            client.sendMessage(message, sender);
        }
    }
}

interface ChatInterface extends Remote {
    void sendMessage(String message, String sender) throws RemoteException;
    void registerClient(String clientName, ChatInterface clientRef) throws RemoteException;
    void broadcastMessage(String message, String sender) throws RemoteException;
}