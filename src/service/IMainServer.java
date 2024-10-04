package service;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMainServer extends Remote {
    // Multiplie les matrices en répartissant le calcul sur plusieurs serveurs
    int[][] multiplyAndRetrieve(int[][] A, int[][] B) throws RemoteException;
}
