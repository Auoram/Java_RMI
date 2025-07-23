/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rmiversion3;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StartRegistry {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(2022);
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            System.out.println("RMI Registry started on port 2022");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
