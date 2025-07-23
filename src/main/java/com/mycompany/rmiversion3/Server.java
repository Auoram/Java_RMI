/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.rmiversion3;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;

public class Server {
    public static void main(String[] args) {
        try {
            Hello obj = new RmiImplHelloInterface();
            Registry registry = LocateRegistry.createRegistry(2022);
            registry.rebind("Test", obj);
            System.out.println("Server ready...");

            obj.addMatrices(null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int[][] addMatrices(int[][] A, int[][] B) throws RemoteException {
        int size = A.length;
        int subSize = size / 2;

        int[][][] subMatricesA = splitMatrix(A, subSize);
        int[][][] subMatricesB = splitMatrix(B, subSize);

        int[][][] results = new int[4][][];

        for (int i = 0; i < 4; i++) {
            try (Socket socket = new Socket("localhost", 5001 + i);
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                out.writeObject(subMatricesA[i]);
                out.writeObject(subMatricesB[i]);
                results[i] = (int[][]) in.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int[][] finalResult = mergeMatrices(results, subSize);

        System.out.println("Final Result:");
        printMatrix(finalResult);

        return finalResult;
    }

    private int[][][] splitMatrix(int[][] matrix, int subSize) {
        return new int[4][][];
    }

    private int[][] mergeMatrices(int[][][] subMatrices, int subSize) {
        return new int[subSize*2][subSize*2];
    }

    private void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int elem : row) {
                System.out.print(elem + " ");
            }
            System.out.println();
        }
    }
}




