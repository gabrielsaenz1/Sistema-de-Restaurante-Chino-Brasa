```mermaid
flowchart TD
    A[Inicio] --> B[Menu principal]

    B --> C1[Registrar producto]
    C1 --> C2[Ingresar nombre, precio y categoria]
    C2 --> C3[Guardar producto]
    C3 --> B

    B --> D1[Ver productos]
    D1 --> D2[Mostrar lista de productos]
    D2 --> B

    B --> E1[Tomar pedido]
    E1 --> E2[Ingresar ID del cliente]
    E2 --> E3[Mostrar productos disponibles]
    E3 --> E4[Seleccionar productos y cantidades]
    E4 --> E5[Calcular subtotal]
    E5 --> E6[Elegir metodo de pago Yape Plin Efectivo Tarjeta]
    E6 --> E7[Elegir comprobante Boleta o Factura]
    E7 --> E8[Calcular total]
    E8 --> E9[Generar ID del pedido]
    E9 --> E10[Guardar pedido]
    E10 --> B

    B --> F1[Ver pedidos]
    F1 --> F2[Mostrar historial]
    F2 --> B

    B --> G1[Buscar pedido]
    G1 --> G2[Ingresar ID]
    G2 --> G3{Existe el pedido}
    G3 -->|Si| G4[Mostrar informacion]
    G3 -->|No| G5[Error no encontrado]
    G4 --> B
    G5 --> B

    B --> H1[Eliminar pedido]
    H1 --> H2[Ingresar ID]
    H2 --> H3{Existe el pedido}
    H3 -->|Si| H4[Eliminar]
    H3 -->|No| H5[Error no encontrado]
    H4 --> B
    H5 --> B

    B --> I1[Salir]
    I1 --> J[Fin]
