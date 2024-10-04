package service;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMatrixMultiplication extends Remote {
    // Multiplie une partie des matrices A et B
    int[][] multiplyMatrices(int[][] A, int[][] B) throws RemoteException;
}
