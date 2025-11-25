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

    public void mostrarMenu() {
        menu.mostrarMenu();
    }

    // ----------------------------
    //   1. TOMAR PEDIDO (SOLO COMIDA)
    // ----------------------------
    public void tomarPedido(Scanner sc) {
        System.out.print("Ingrese Numero de Mesa para este pedido: ");
        int mesa;
        try {
            mesa = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Numero de mesa invalido.");
            return;
        }

        // Creamos el pedido asignado a esa mesa
        Pedido pedido = new Pedido(contadorPedidos++, mesa);
        int productosAgregados = 0;

        while (true) {
            mostrarMenu();
            System.out.print("Ingrese ID del producto (0 para terminar de ordenar): ");
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

            // Mostrar opciones
            System.out.println("Opciones de " + prod.getNombre() + ":");
            List<OpcionProducto> opciones = prod.getOpciones();
            for (int i = 0; i < opciones.size(); i++) {
                System.out.println((i + 1) + ". " + opciones.get(i).getDescripcion() + " - S/" + opciones.get(i).getPrecio());
            }

            System.out.print("Seleccione una opcion: ");
            int opIndice;
            try {
                opIndice = Integer.parseInt(sc.nextLine()) - 1;
            } catch (Exception e) {
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

            pedido.agregarItem(new ItemPedido(prod.getNombre(), opcionSeleccionada.getDescripcion(), opcionSeleccionada.getPrecio(), cantidad));
            productosAgregados++;
            System.out.println(" Producto agregado a la Mesa " + mesa);
        }

        if (productosAgregados > 0) {
            pedidos.add(pedido);
            System.out.println("Pedido registrado para la Mesa " + mesa + ". Estado: PENDIENTE.");
            System.out.println("Puede seguir atendiendo otras mesas.");
        } else {
            contadorPedidos--;
            System.out.println("Pedido cancelado.");
        }
    }

    // ----------------------------
    //   NUEVO: COBRAR MESA (PAGAR)
    // ----------------------------
    public void cobrarMesa(Scanner sc) {
        System.out.print("Ingrese el Numero de Mesa a cobrar: ");
        int mesaBuscada;
        try {
            mesaBuscada = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Dato invalido.");
            return;
        }

        // Buscar pedido de esa mesa que NO esté pagado aun (usamos filtro simple)
        Pedido pedidoEncontrado = null;
        for (Pedido p : pedidos) {
            // Buscamos la mesa y que el metodo de pago sea el pendiente
            if (p.getNumeroMesa() == mesaBuscada && p.getMetodoPago().equals("Pendiente de pago")) {
                pedidoEncontrado = p;
                break;
            }
        }

        if (pedidoEncontrado == null) {
            System.out.println("No hay cuenta pendiente para la Mesa " + mesaBuscada);
            return;
        }

        System.out.println("\n--- DETALLE DE CUENTA MESA " + mesaBuscada + " ---");
        System.out.println("Total a pagar: S/" + pedidoEncontrado.calcularTotal());

        // Aquí ponemos la lógica de pago que antes tenías en tomarPedido
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
        pedidoEncontrado.setMetodoPago(metodo);

        System.out.println("Seleccione tipo de comprobante:");
        System.out.println("1. Boleta");
        System.out.println("2. Factura");
        System.out.print("Opcion: ");

        String comp = switch (sc.nextLine()) {
            case "1" -> "Boleta";
            case "2" -> "Factura";
            default -> "No especificado";
        };
        pedidoEncontrado.setTipoComprobante(comp);

        // Opcional: Cambiar estado a ENTREGADO si ya pagaron
        pedidoEncontrado.setEstado(EstadoPedido.ENTREGADO);

        System.out.println("Cobro realizado con exito. Mesa liberada.");
    }

    // ----------------------------
    //       VER PEDIDOS (ACTUALIZADO)
    // ----------------------------
    public void verPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println(" No hay pedidos registrados.");
            return;
        }
        for (Pedido p : pedidos) {
            System.out.println("\n=========================");
            // Ahora mostramos la mesa
            System.out.println("Pedido #" + p.getId() + " - MESA: " + p.getNumeroMesa());
            System.out.println("=========================");
            for (ItemPedido item : p.getItems()) {
                System.out.println("- " + item.getNombreProducto() + " (" + item.getDescripcionOpcion() + ") x" + item.getCantidad() + " = S/" + item.getSubtotal());
            }
            System.out.println("Estado: " + p.getEstado());
            System.out.println("Pago: " + p.getMetodoPago());
            System.out.println("Total: S/" + p.calcularTotal());
        }
    }

    public void actualizarEstado(Scanner sc) {
        // (Tu código de actualizarEstado se mantiene igual, solo asegúrate de usarlo)
        // Puedes copiar tu código anterior aquí si lo necesitas.
        if (pedidos.isEmpty()) {
            System.out.println(" No hay pedidos.");
            return;
        }
        verPedidos();
        System.out.print("Ingrese ID del pedido a cambiar estado: ");
        int id;
        try { id = Integer.parseInt(sc.nextLine()); } catch (Exception e) { return; }

        Pedido pedido = pedidos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (pedido == null) return;

        System.out.println("1. Pendiente / 2. En cocina / 3. Entregado");
        String op = sc.nextLine();
        switch(op) {
            case "1" -> pedido.setEstado(EstadoPedido.PENDIENTE);
            case "2" -> pedido.setEstado(EstadoPedido.ENCOCINA);
            case "3" -> pedido.setEstado(EstadoPedido.ENTREGADO);
        }
        System.out.println("Estado actualizado.");
    }
}


