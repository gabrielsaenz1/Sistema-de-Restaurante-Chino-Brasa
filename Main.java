package com.mycompany.main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        SistemaPedidos sistema = new SistemaPedidos();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n--- Restaurante Chino Brasa ---");
            System.out.println("1. Tomar pedido");
            System.out.println("2. Ver pedidos");
            System.out.println("3. Actualizar estado");
            System.out.println("4. Salir");

            System.out.print("Seleccione opcion: ");
            String op = sc.nextLine();

            switch (op) {
                case "1" -> sistema.tomarPedido(sc);
                case "2" -> sistema.verPedidos();
                case "3" -> sistema.actualizarEstado(sc);
                case "4" -> {
                    System.out.println("Hasta luego.");
                    return;
                }
                default -> System.out.println("Opcion no valida.");
            }
        }
    }
}
