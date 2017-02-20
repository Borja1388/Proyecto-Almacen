package Negocio;

import Modelo.Cliente;
import Modelo.Errordni;
import Modelo.FormatoFechaErroneo;
import Modelo.Lavadora;
import Modelo.Mayorista;
import Modelo.Mueble;
import Modelo.Particular;
import Modelo.Producto;
import Modelo.Televisor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

public class MenuPrincipal {

    NegociosService servicio;

    MenuPrincipal() {
        servicio = new NegociosService();
    }

    public void inciarAplicacion() {
        try {
            // menu Principal
            int opcion = -1;
            do {
                System.out.println("1.Productos");
                System.out.println("2.Clientes");
                System.out.println("3.Ventas");
                System.out.println("0. Para Salir");
                System.out.println("Introduzca la opcion");
                Scanner sc = new Scanner(System.in);
                opcion = sc.nextInt();
                if (opcion == 1) {
                    menuProductos();
                }
                if (opcion == 2) {
                    menuClientes();
                }
                if (opcion == 3) {
                    menuVentas();
                }

            } while (opcion != 0);

            System.out.println("Gracias por usar nuestra aplicacion");
            System.out.println("Hasta la proxima");

        } catch (Exception e) {
            System.out.println("Opcion no valida");
            this.inciarAplicacion();
        }
    }

    private void menuProductos() {
        try {
            int opcionProductos = -1;
            do {
                System.out.println("1.Introducir Producto");
                System.out.println("2.Dar de baja un producto");
                System.out.println("3.Imprimir los datos de un producto");
                System.out.println("4.Imprimir todos los productos");
                System.out.println("0. Salir del menu");
                Scanner sc = new Scanner(System.in);
                opcionProductos = sc.nextInt();

                if (opcionProductos == 1) {
                    Producto p = datosProducto();
                    servicio.introducirProducto(p);
                }
                if (opcionProductos == 2) {
                    System.out.println("Introduzca el número de producto: ");
                    int num = sc.nextInt();
                    servicio.elimninarProducto(num);
                }
                if (opcionProductos == 3) {
                    System.out.println("Introduzca el número de producto: ");
                    int nprod = sc.nextInt();
                    System.out.println(servicio.buscarProducto(nprod));
                }
                if (opcionProductos == 4) {
                    System.out.println(servicio.imprimirTodosProductos());
                }

            } while (opcionProductos != 0);
        } catch (Exception e) {
            System.out.println("Opcion no valida" + e.getMessage());
            this.menuProductos();
        }
    }

    public Producto datosProducto() throws Exception {
        Scanner sc = new Scanner(System.in);
        Producto producto = null;
        String nombre;
        double precio;
        int opcion = -1;
        do {
            System.out.println("Introduzca el nombre: ");
            nombre = sc.nextLine();

            System.out.println("Introduzca precio base: ");
            precio = Double.parseDouble(sc.nextLine());

            System.out.println("Introduzca el tipo de producto: ");
            System.out.println("1. Mueble");
            System.out.println("2. Lavadora");
            System.out.println("3. Televisor");
            opcion = sc.nextInt();
            if (opcion == 1) {
                producto = pedirMueble();
            }
            if (opcion == 2) {
                producto = pedirLavadora();
            }
            if (opcion == 3) {
                producto = pedirTelevisor();
            }
            if (producto != null) {
                producto.setNombre(nombre);
                producto.setPrecio(precio);
            }

        } while (opcion != 1 && opcion != 2 && opcion != 3);

        return producto;
    }

    public Mueble pedirMueble() {
        Mueble m = new Mueble();
        Scanner sc = new Scanner(System.in);
        boolean repetir = false;

        m.setTipoMadera(pedirMadera());

        System.out.println("Introduzca el estilo:");
        m.setEstilo(sc.nextLine());
        do {
            try {
                System.out.println("Introduzca la fecha (dd-MMMM-yyyy): ");
                m.setAnyoFab(this.validarFecha(sc.nextLine()));
                repetir = true;
            } catch (FormatoFechaErroneo e) {
                System.out.println(e.getMessage());
            }
        } while (repetir == false);

        return m;

    }

    public Lavadora pedirLavadora() {
        Scanner sc = new Scanner(System.in);
        Lavadora l = new Lavadora();

        System.out.println("Introduzca las revoluciones(rpm): ");
        int rev = Integer.parseInt(sc.nextLine());
        l.setRevoluciones(rev);

        System.out.println("Introduzca la capacidad (kg): ");
        int c = Integer.parseInt(sc.nextLine());
        l.setCarga(c);

        return l;
    }

    public Televisor pedirTelevisor() {
        Televisor tv = new Televisor();
        boolean repetir = false;
        Televisor.tele t = null;
        String opcion;
        int contador = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduzca las pulgadas: ");
        double pulgadas = Double.parseDouble(sc.nextLine());
        tv.setPulgadas(pulgadas);

        do {
            try {
                System.out.println("Introduzca el tipo de Televisor");
                for (Televisor.tele t1 : Televisor.tele.values()) {
                    contador++;
                    System.out.println(t1.toString());
                }
                opcion = sc.nextLine();

                t = Televisor.tele.valueOf(opcion);
                repetir = true;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                repetir = false;
            }
        } while (repetir == false);
        tv.setTipo(t);

        return tv;

    }

    private Mueble.Madera pedirMadera() {
        Mueble.Madera m = null;
        String opcion;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Introduzca el tipo de Madera");
            int contador = 0;
            for (Mueble.Madera m2 : Mueble.Madera.values()) {
                contador++;
                System.out.println(contador + " " + m2.toString());
            }

            opcion = sc.nextLine();

        } while (!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3"));

        if (opcion.equals("1")) {
            m = Mueble.Madera.PINO;
        }
        if (opcion.equals("2")) {
            m = Mueble.Madera.ROBLE;
        }
        if (opcion.equals("3")) {
            m = Mueble.Madera.HAYA;
        }
        return m;
    }

    private void menuClientes() {
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        String opcion;
        Mayorista.TipoMayorista t = null;
        boolean repetir = false;
        Particular p = new Particular();
        Mayorista m = new Mayorista();

        try {
            int opcionClientes = -1;
            do {
                System.out.println("1.Introducir Cliente");
                System.out.println("2.Dar de baja un cliente");
                System.out.println("3.Imprimir los datos de un cliente");
                System.out.println("4.Imprimir todos los clientes");
                System.out.println("0. Salir del menu");

                opcionClientes = sc.nextInt();
                if (opcionClientes == 1) {
                    if (servicio.introducirCliente(this.datosCliente())) {
                        System.out.println("El cliente se ha introducido con exito");
                    } else {
                        System.out.println("Error al introducir el cliente");
                    }

                }
                if (opcionClientes == 2) {
                    System.out.println("Introduzca el numero de cliente");
                    int numcliente = sc.nextInt();
                    if (servicio.eliminarCliente(numcliente)) {
                        System.out.println("El cliente ha sido borrado");
                    } else {
                        System.out.println("Error al borrar el cliente");
                    }

                }
                if (opcionClientes == 3) {
                    System.out.println("Introduzca el numero de cliente");
                    int numcliente = sc.nextInt();
                    System.out.println(servicio.imprimirCliente(numcliente));

                }
                if (opcionClientes == 4) {
                    System.out.println(servicio.imprimirTodosClientes());
                }

            } while (opcionClientes != 0);
        } catch (Exception e) {
            System.out.println("Opcion no valida");
            this.menuClientes();
        }

    }

    private void menuVentas() {
        Scanner sc = new Scanner(System.in);

        try {
            String opcionVentas = "-1";
            do {
                System.out.println("1.Introducir Venta");
                System.out.println("2.Dar de baja una venta");
                System.out.println("3.Imprimir los datos de una venta");
                System.out.println("4.Imprimir todas las ventas");
                System.out.println("0. Salir del menu");
                opcionVentas = sc.nextLine();

                if (opcionVentas.equals("1")) {
                    System.out.println("Introduce el número de cliente.");
                    int nv = Integer.parseInt(sc.nextLine());
                    if (servicio.buscarCliente(nv) != null) {
                        System.out.println("El Cliente ha sido encontrado");
                    } else {
                        System.out.println("El cliente no existe");
                    }
                    System.out.println("Introduce el número de producto.");
                    int np = Integer.parseInt(sc.nextLine());
                    if (servicio.buscarProducto(np) != null) {
                        System.out.println("El producto ha sido encontrado");
                    } else {
                        System.out.println("El producto no existe");
                    }
                    System.out.println("Introduce el nombre del vendedor: ");
                    String v = sc.nextLine();
                    servicio.introducirVenta(nv, np, v);
                }
                if (opcionVentas.equals("2")) {
                    System.out.println("Introduzca número de venta: ");
                    int nv = Integer.parseInt(sc.nextLine());
                    servicio.eliminarVenta(nv);
                }
                if (opcionVentas.equals("3")) {
                    System.out.println("Introduzca número de venta: ");
                    int nv = Integer.parseInt(sc.nextLine());
                    System.out.println(servicio.buscarVenta(nv));
                }
                if (opcionVentas.equals("4")) {
                    System.out.println(servicio.imprimirtodasVentas());
                }

            } while (!opcionVentas.equals("0"));

        } catch (Exception e) {
            System.out.println("Opcion no valida");
            this.menuVentas();
        }

    }

    private LocalDate validarFecha(String fecha) {
        LocalDate fechaFor = null;
        FormatoFechaErroneo a;
        String fecha1;
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MMMM-yy", Locale.ENGLISH);
        try {
            fechaFor = LocalDate.parse(fecha, formateador);
        } catch (DateTimeParseException e) {
            throw new FormatoFechaErroneo(fecha);

        }

        return fechaFor;
    }

    private Cliente datosCliente() {
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        Particular p = new Particular();
        Mayorista m = new Mayorista();
        Cliente cliente = null;
        String opcion;
        boolean repetir = false;
        Mayorista.TipoMayorista t = null;
        System.out.println("Que tipo de cliente desea introducir?(Pârticular o Mayorista)");
        String tipo = sc1.nextLine();
        if (tipo.equalsIgnoreCase("Particular")) {
            System.out.println("Introduce el nombre del cliente");
            String nombre = sc1.nextLine();
            p.setNombre(nombre);
            System.out.println("Introduce la razon social del cliente");
            String rs = sc1.nextLine();
            p.setRazonSocial(rs);
            do {
                try {
                    System.out.println("Introduzca el DNI del cliente");
                    String dni = sc1.nextLine();
                    p.setDni(dni);
                    repetir = true;
                } catch (Errordni e) {
                    System.out.println(e.getMessage());
                }
            } while (repetir == false);
            cliente = p;

        } else if (tipo.equalsIgnoreCase("Mayorista")) {
            System.out.println("Introduce el nombre del cliente");
            String nombre = sc1.nextLine();
            m.setNombre(nombre);
            System.out.println("Introduce la razon social del cliente");
            String rs = sc1.nextLine();
            m.setRazonSocial(rs);
            System.out.println("Introduzca el CIF del cliente");
            String cif = sc1.nextLine();
            m.setCif(cif);
            do {

                System.out.println("Introduzca el tipo de Mayorista");
                System.out.println("1.Gran superficie");
                System.out.println("2.tienda");

                opcion = sc.nextLine();

            } while (!opcion.equals("1") && !opcion.equals("2"));

            if (opcion.equals("1")) {
                t = Mayorista.TipoMayorista.Gransuperficie;
            }
            if (opcion.equals("2")) {
                t = Mayorista.TipoMayorista.tienda;
            }
            m.setTipoMayorista(t);
            System.out.println("Introduzca el descuento");
            double descuento = sc.nextDouble();
            m.setDescuento(descuento);
            cliente = m;

        }
        return cliente;
    }

}
