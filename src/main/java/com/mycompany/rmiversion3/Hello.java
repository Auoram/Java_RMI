/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rmiversion3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
    int[][] addMatrices(int[][] A, int[][] B) throws RemoteException;
}
