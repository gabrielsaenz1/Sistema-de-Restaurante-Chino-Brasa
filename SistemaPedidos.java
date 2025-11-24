package com.mycompany.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaPedidos {

    private Menu menu;
    private List<Pedido> pedidos;
    private int contadorPedidos;

    public SistemaPedidos() {
        this.menu = new Menu();
        this.pedidos = new ArrayList<>();
        this.contadorPedidos = 1;
    }

    // ----------------------------
    //       MOSTRAR MENU
    // ----------------------------
    public void mostrarMenu() {
        menu.mostrarMenu();
    }

    // ----------------------------
    //        TOMAR PEDIDO
    // ----------------------------
    public void tomarPedido(Scanner sc) {
        Pedido pedido = new Pedido(contadorPedidos++);
        int productosAgregados = 0;

        while (true) {
            mostrarMenu();
            System.out.print("Ingrese ID del producto (0 para terminar): ");
            String entrada = sc.nextLine().trim();

            if (entrada.equals("0")) break;

            int id;
            try {
                id = Integer.parseInt(entrada);
            } catch (Exception e) {
                System.out.println(" ID invalido.");
                continue;
            }

            Producto prod = menu.buscarPorId(id);
            if (prod == null) {
                System.out.println(" Producto no encontrado.");
                continue;
            }

            System.out.println("Opciones de " + prod.getNombre() + ":");
            List<OpcionProducto> opciones = prod.getOpciones();

            for (int i = 0; i < opciones.size(); i++) {
                System.out.println((i + 1) + ". " 
                        + opciones.get(i).getDescripcion() 
                        + " - S/" + opciones.get(i).getPrecio());
            }

            System.out.print("Seleccione una opcion: ");
            int opIndice;

            try {
                opIndice = Integer.parseInt(sc.nextLine()) - 1;
            } catch (Exception e) {
                System.out.println(" Opcion invalida.");
                continue;
            }

            if (opIndice < 0 || opIndice >= opciones.size()) {
                System.out.println(" Opcion fuera de rango.");
                continue;
            }

            OpcionProducto opcionSeleccionada = opciones.get(opIndice);

            System.out.print("Cantidad: ");
            int cantidad;

            try {
                cantidad = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println(" Cantidad invalida.");
                continue;
            }

            pedido.agregarItem(
                    new ItemPedido(
                            prod.getNombre(),
                            opcionSeleccionada.getDescripcion(),
                            opcionSeleccionada.getPrecio(),
                            cantidad
                    )
            );

            productosAgregados++;
            System.out.println(" Producto agregado.");
        }

        if (productosAgregados > 0) {
            // Registrar método de pago
System.out.println("Seleccione metodo de pago:");
System.out.println("1. Efectivo");
System.out.println("2. Yape");
System.out.println("3. Plin");
System.out.println("4. Tarjeta");
System.out.print("Opcion: ");

String metodo = switch (sc.nextLine()) {
    case "1" -> "Efectivo";
    case "2" -> "Yape";
    case "3" -> "Plin";
    case "4" -> "Tarjeta";
    default -> "Desconocido";
};

pedido.setMetodoPago(metodo);

// Registrar tipo de comprobante
System.out.println("Seleccione tipo de comprobante:");
System.out.println("1. Boleta");
System.out.println("2. Factura");
System.out.print("Opcion: ");

String comp = switch (sc.nextLine()) {
    case "1" -> "Boleta";
    case "2" -> "Factura";
    default -> "No especificado";
};

pedido.setTipoComprobante(comp);

            pedidos.add(pedido);
            System.out.println(" Pedido #" + pedido.getId() + " registrado. Total: S/" + pedido.calcularTotal());
        } else {
            contadorPedidos--;
            System.out.println(" Pedido cancelado.");
        }
    }

    // ----------------------------
    //         VER PEDIDOS
    // ----------------------------
    public void verPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println(" No hay pedidos registrados.");
            return;
        }

        for (Pedido p : pedidos) {
            System.out.println("\n=========================");
            System.out.println("Pedido #" + p.getId());
            System.out.println("=========================");

            for (ItemPedido item : p.getItems()) {
                System.out.println("- " + item.getNombreProducto()
                        + " (" + item.getDescripcionOpcion() + ")  x" + item.getCantidad()
                        + "  = S/" + item.getSubtotal());
            }

            System.out.println("Estado: " + p.getEstado());
            System.out.println("Metodo de pago: " + p.getMetodoPago());
System.out.println("Comprobante: " + p.getTipoComprobante());
System.out.println("Total: S/" + p.calcularTotal());

            System.out.println("-------------------------");
        }
    }

    // ----------------------------
    //     ACTUALIZAR ESTADO
    // ----------------------------
    public void actualizarEstado(Scanner sc) {

        if (pedidos.isEmpty()) {
            System.out.println(" No hay pedidos.");
            return;
        }

        verPedidos();

        System.out.print("Ingrese ID del pedido: ");
        int id;

        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println(" ID invalido.");
            return;
        }

        Pedido pedido = pedidos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (pedido == null) {
            System.out.println(" Pedido no encontrado.");
            return;
        }

        System.out.println("Seleccione estado:");
        System.out.println("1. Pendiente");
        System.out.println("2. En cocina");
        System.out.println("3. Entregado");
        System.out.print("Opcion: ");

        int op;
        try {
            op = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println(" Opcion invalida.");
            return;
        }

        switch (op) {
            case 1 -> pedido.setEstado(EstadoPedido.PENDIENTE);
            case 2 -> pedido.setEstado(EstadoPedido.ENCOCINA);
            case 3 -> pedido.setEstado(EstadoPedido.ENTREGADO);
            default -> {
                System.out.println(" Opcion invalida.");
                return;
            }
        }

        System.out.println(" Estado actualizado.");
    }
}

