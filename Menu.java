package com.mycompany.main;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<Producto> productos;

    public Menu() {
        productos = new ArrayList<>();

         Producto mostrito = new Producto("Mostrito");
        mostrito.agregarOpcion("1/8 de Pollo + papas medianas + arroz chaufa", 13);
        mostrito.agregarOpcion("1/4 Pollo + papas grandes + arroz chaufa", 18);
        
        Producto pollo = new Producto("Pollo a la brasa");
        pollo.agregarOpcion("1/4 de Pollo", 15);
        pollo.agregarOpcion("1/2 Pollo", 25);
        pollo.agregarOpcion("Pollo entero", 45);

        Producto papas = new Producto("Papas fritas");
        papas.agregarOpcion("Pequeno", 7);
        papas.agregarOpcion("Mediano", 18);
        papas.agregarOpcion("Familiar", 25);

        Producto bebida = new Producto("Gaseosa");
        bebida.agregarOpcion("Personal", 3);
        bebida.agregarOpcion("1 Litro", 6);
        bebida.agregarOpcion("1.5 litros", 10);

        agregarProducto(mostrito);
        agregarProducto(pollo);
        agregarProducto(papas);
        agregarProducto(bebida);
    }

    public void mostrarMenu() {
        System.out.println("------ MENU ------");
        for (Producto p : productos) {
            System.out.println(p.getId() + ". " + p.getNombre());
        }
    }

    public Producto buscarPorId(int id) {
        return productos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }
}
