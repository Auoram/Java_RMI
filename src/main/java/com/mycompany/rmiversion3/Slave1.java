/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rmiversion3;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Slave1 {
    public static void main(String[] args) {
        int port = 5001;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Slave1 ready on port " + port);

            while (true) {
                try (Socket socket = serverSocket.accept();
                     ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                     ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

                    int[][] A = (int[][]) in.readObject();
                    int[][] B = (int[][]) in.readObject();

                    int size = A.length;
                    int[][] R = new int[size][size];

                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            R[i][j] = A[i][j] + B[i][j];
                        }
                    }

                    System.out.println("Slave1 - Result Matrix R:");
                    printMatrix(R);
                    out.writeObject(R);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int elem : row) {
                System.out.print(elem + " ");
            }
            System.out.println();
        }
    }
}




