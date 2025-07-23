/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rmiversion3;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 2022);
            Hello server = (Hello) registry.lookup("Test");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the size of the matrices n (pair): ");
            int size = scanner.nextInt();

            int[][] A = new int[size][size];
            int[][] B = new int[size][size];

            System.out.println("Enter elements of Matrix A:");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print("A[" + i + "][" + j + "]: ");
                    A[i][j] = scanner.nextInt();
                }
            }

            System.out.println("Enter elements of Matrix B:");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print("B[" + i + "][" + j + "]: ");
                    B[i][j] = scanner.nextInt();
                }
            }

            int[][] result = server.addMatrices(A, B);

            System.out.println("Result from Server:");
            printMatrix(result);

        } catch (RemoteException | NotBoundException e) {
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



