package service;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements IMatrixMultiplication {

    protected Server() throws RemoteException {
        super();
    }

    // Méthode qui multiplie une sous-partie de la matrice
    @Override
    public int[][] multiplyMatrices(int[][] A, int[][] B) throws RemoteException {
        int n = A.length;
        int m = B[0].length;
        int[][] result = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < B.length; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            Server server = new Server();
            Naming.rebind("rmi://localhost:1099/Server", server);
            System.out.println("Serveur de calcul prêt...");
        } catch (Exception e) {
            System.err.println("Erreur sur le serveur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
