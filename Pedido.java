package com.mycompany.main;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int id;
    private int numeroMesa; // <--- NUEVO CAMPO
    private List<ItemPedido> items;
    private EstadoPedido estado;

    private String metodoPago;
    private String tipoComprobante;

    // Modificamos el constructor para pedir el numero de mesa
    public Pedido(int id, int numeroMesa) {
        this.id = id;
        this.numeroMesa = numeroMesa;
        this.items = new ArrayList<>();
        this.estado = EstadoPedido.PENDIENTE;
        this.metodoPago = "Pendiente de pago"; // Valor inicial
        this.tipoComprobante = "Sin emitir";   // Valor inicial
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    // ... (El resto de getters y setters sigue igual que antes)
    public int getId() { return id; }
    public List<ItemPedido> getItems() { return items; }
    public void agregarItem(ItemPedido item) { items.add(item); }
    public EstadoPedido getEstado() { return estado; }
    public void setEstado(EstadoPedido estado) { this.estado = estado; }

    public double calcularTotal() {
        double total = 0;
        for (ItemPedido item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public String getTipoComprobante() { return tipoComprobante; }
    public void setTipoComprobante(String tipoComprobante) { this.tipoComprobante = tipoComprobante; }
}


