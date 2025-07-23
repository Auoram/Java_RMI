/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rmiversion3;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RmiImplHelloInterface extends UnicastRemoteObject implements Hello {
    public RmiImplHelloInterface() throws RemoteException {}

    @Override
    public int[][] addMatrices(int[][] A, int[][] B) throws RemoteException {
        int size = A.length;
        int subSize = size / 2;

        int[][][] subMatricesA = splitMatrix(A, subSize);
        int[][][] subMatricesB = splitMatrix(B, subSize);

        int[][][] results = new int[4][][];
        int[] slavePorts = {5001, 5002, 5003, 5004};

        for (int i = 0; i < 4; i++) {
            try (Socket socket = new Socket("localhost", slavePorts[i]);
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                out.writeObject(subMatricesA[i]);
                out.writeObject(subMatricesB[i]);

                results[i] = (int[][]) in.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return mergeMatrices(results, subSize);
    }

    private int[][][] splitMatrix(int[][] matrix, int subSize) {
        int[][][] subMatrices = new int[4][subSize][subSize];

        for (int i = 0; i < subSize; i++) {
            for (int j = 0; j < subSize; j++) {
                subMatrices[0][i][j] = matrix[i][j];
                subMatrices[1][i][j] = matrix[i][j + subSize];
                subMatrices[2][i][j] = matrix[i + subSize][j];
                subMatrices[3][i][j] = matrix[i + subSize][j + subSize];
            }
        }
        return subMatrices;
    }

    private int[][] mergeMatrices(int[][][] subMatrices, int subSize) {
        int size = subSize * 2;
        int[][] mergedMatrix = new int[size][size];

        for (int i = 0; i < subSize; i++) {
            for (int j = 0; j < subSize; j++) {
                mergedMatrix[i][j] = subMatrices[0][i][j];
                mergedMatrix[i][j + subSize] = subMatrices[1][i][j];
                mergedMatrix[i + subSize][j] = subMatrices[2][i][j];
                mergedMatrix[i + subSize][j + subSize] = subMatrices[3][i][j];
            }
        }
        return mergedMatrix;
    }
}

