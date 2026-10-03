/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculodeareas;
import java.util.Scanner;

// --- DOMINIO / MODELO POO ---

abstract class FiguraGeometrica {
    private final String nombre;

    protected FiguraGeometrica(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract double calcularArea();
}

class Circulo extends FiguraGeometrica {
    private final double radio;

    public Circulo(double radio) {
        super("Círculo");
        this.radio = radio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * radio * radio;
    }
}

class Rectangulo extends FiguraGeometrica {
    private final double base;
    private final double altura;

    public Rectangulo(double base, double altura) {
        super("Rectángulo");
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return base * altura;
    }
}

class Triangulo extends FiguraGeometrica {
    private final double base;
    private final double altura;

    public Triangulo(double base, double altura) {
        super("Triángulo");
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return (base * altura) / 2.0;
    }
}

// --- SERVICIO DE ENTRADA / SALIDA (MODULARIDAD) ---

class ConsolaInput {
    private final Scanner scanner;

    public ConsolaInput(Scanner scanner) {
        this.scanner = scanner;
    }

    public int solicitarOpcionMenu() {
        System.out.print("Opción: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public double solicitarDimension(String mensaje) {
        double valor;
        do {
            System.out.print(mensaje);
            while (!scanner.hasNextDouble()) {
                System.out.print("Entrada inválida. Ingrese un número: ");
                scanner.next();
            }
            valor = scanner.nextDouble();
            if (valor <= 0) {
                System.out.println("El valor debe ser mayor a 0.");
            }
        } while (valor <= 0);
        return valor;
    }
}

// --- CLASE PRINCIPAL ---

public class CALCULOdeAREAS {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsolaInput input = new ConsolaInput(scanner);
        boolean continuarEjecucion = true;

        mostrarEncabezado();

        while (continuarEjecucion) {
            mostrarMenu();
            int opcion = input.solicitarOpcionMenu();

            if (opcion == 4) {
                continuarEjecucion = false;
                System.out.println("\n¡Gracias por usar el programa!");
                break;
            }

            FiguraGeometrica figura = crearFiguraSegunOpcion(opcion, input);
            if (figura != null) {
                imprimirResultado(figura);
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close();
    }

    private static void mostrarEncabezado() {
        System.out.println("=================================");
        System.out.println("    CÁLCULO DE ÁREAS - POO      ");
        System.out.println("=================================");
    }

    private static void mostrarMenu() {
        System.out.println("\nSeleccione la figura geométrica:");
        System.out.println("1. Círculo");
        System.out.println("2. Rectángulo");
        System.out.println("3. Triángulo");
        System.out.println("4. Salir");
    }

    private static FiguraGeometrica crearFiguraSegunOpcion(int opcion, ConsolaInput input) {
        return switch (opcion) {
            case 1 -> new Circulo(
                input.solicitarDimension("Ingrese el radio del círculo: ")
            );
            case 2 -> new Rectangulo(
                input.solicitarDimension("Ingrese la base del rectángulo: "),
                input.solicitarDimension("Ingrese la altura del rectángulo: ")
            );
            case 3 -> new Triangulo(
                input.solicitarDimension("Ingrese la base del triángulo: "),
                input.solicitarDimension("Ingrese la altura del triángulo: ")
            );
            default -> null;
        };
    }

    private static void imprimirResultado(FiguraGeometrica figura) {
        System.out.printf("-> El área del %s es: %.2f\n", figura.getNombre(), figura.calcularArea());
    }
}
