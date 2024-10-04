package service;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class MainServer extends UnicastRemoteObject implements IMainServer {

    protected MainServer() throws RemoteException {
        super();
    }
    @Override
    public int[][] multiplyAndRetrieve(int[][] A, int[][] B) throws RemoteException {
        try {
            IMatrixMultiplication matrixServer = (IMatrixMultiplication) Naming.lookup("rmi://localhost:1099/Server");
            return matrixServer.multiplyMatrices(A, B);
        } catch (Exception e) {
            throw new RemoteException("Erreur pendant la multiplication des matrices", e);
        }
    }
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1999);
            MainServer mainServer = new MainServer();
            Naming.rebind("rmi://localhost:1999/MainServer", mainServer);
            System.out.println("Serveur principal prêt pour la répartition de calcul...");
        } catch (Exception e) {
            System.err.println("Erreur sur le serveur principal : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
