/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculodeareas;
import java.util.Scanner;

// Clase abstracta base
abstract class Figura {
    private String nombre;

    public Figura(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    // Método abstracto que cada figura debe implementar
    public abstract double calcularArea();
}

// Subclase Círculo
class Circulo extends Figura {
    private double radio;

    public Circulo(double radio) {
        super("Círculo");
        this.radio = radio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * Math.pow(radio, 2);
    }
}

// Subclase Rectángulo
class Rectangulo extends Figura {
    private double base;
    private double altura;

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

// Subclase Triángulo
class Triangulo extends Figura {
    private double base;
    private double altura;

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

// Clase Principal
public class CALCULOdeAREAS {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        System.out.println("=== PROGRAMA CÁLCULO DE ÁREAS ===");

        while (!salir) {
            System.out.println("\nSeleccione la figura geométrica:");
            System.out.println("1. Círculo");
            System.out.println("2. Rectángulo");
            System.out.println("3. Triángulo");
            System.out.println("4. Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();
            Figura figura = null;

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el radio del círculo: ");
                    double radio = scanner.nextDouble();
                    figura = new Circulo(radio);
                    break;

                case 2:
                    System.out.print("Ingrese la base del rectángulo: ");
                    double baseR = scanner.nextDouble();
                    System.out.print("Ingrese la altura del rectángulo: ");
                    double alturaR = scanner.nextDouble();
                    figura = new Rectangulo(baseR, alturaR);
                    break;

                case 3:
                    System.out.print("Ingrese la base del triángulo: ");
                    double baseT = scanner.nextDouble();
                    System.out.print("Ingrese la altura del triángulo: ");
                    double alturaT = scanner.nextDouble();
                    figura = new Triangulo(baseT, alturaT);
                    break;

                case 4:
                    salir = true;
                    System.out.println("¡Gracias por usar el programa!");
                    continue;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    continue;
            }

            // Polimorfismo en acción: llama al método de la instancia concreta
            mostrarResultado(figura);
        }

        scanner.close();
    }

    private static void mostrarResultado(Figura figura) {
        System.out.printf("El área del %s es: %.2f\n", figura.getNombre(), figura.calcularArea());
    }
}