```mermaid
flowchart TD
    A[Inicio] --> B[Mostrar menú principal]

    B --> C1[1. Registrar Producto]
    C1 --> C2[Ingresar nombre, precio y categoría]
    C2 --> C3[Guardar producto en lista]
    C3 --> B

    B --> D1[2. Ver Productos]
    D1 --> D2[Mostrar lista de productos]
    D2 --> B

    B --> E1[3. Tomar Pedido]
    E1 --> E2[Solicitar ID del cliente]
    E2 --> E3[Mostrar productos disponibles]
    E3 --> E4[Seleccionar productos y cantidades]
    E4 --> E5[Calcular subtotal]
    E5 --> E6[Seleccionar método de pago<br/>(Yape / Plin / Efectivo / Tarjeta)]
    E6 --> E7[Seleccionar tipo de comprobante<br/>(Boleta / Factura)]
    E7 --> E8[Calcular total a pagar]
    E8 --> E9[Generar ID único del pedido]
    E9 --> E10[Guardar pedido en la lista de pedidos]
    E10 --> B

    B --> F1[4. Ver Pedidos]
    F1 --> F2[Mostrar historial de pedidos]
    F2 --> B

    B --> G1[5. Buscar Pedido por ID]
    G1 --> G2[Ingresar ID del pedido]
    G2 --> G3{¿Existe?}
    G3 -->|Sí| G4[Mostrar información completa del pedido]
    G3 -->|No| G5[Mostrar mensaje de error]
    G4 --> B
    G5 --> B

    B --> H1[6. Eliminar Pedido]
    H1 --> H2[Ingresar ID del pedido]
    H2 --> H3{¿Existe?}
    H3 -->|Sí| H4[Eliminar de la lista]
    H3 -->|No| H5[Mostrar mensaje de error]
    H4 --> B
    H5 --> B

    B --> I1[7. Salir]
    I1 --> J[Fin]
