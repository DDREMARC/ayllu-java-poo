package vista;

import java.util.Scanner;

public class Lector {
    private Scanner sc;

    public Lector(Scanner sc) {
        this.sc = sc;
    }

    public int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero entero.");
            }
        }
    }

    public double leerDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim().replace(',', '.');
            try {
                double valor = Double.parseDouble(texto);
                if (valor < 0) {
                    System.out.println("El valor no puede ser negativo.");
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero (ejemplo: 25.50).");
            }
        }
    }

    public String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("El texto no puede estar vacio.");
        }
    }

    public String leerFecha(String mensaje) {
        while (true) {
            String texto = leerTexto(mensaje);
            if (texto.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return texto;
            }
            System.out.println("Formato de fecha incorrecto. Use AAAA-MM-DD (ejemplo: 2026-11-15).");
        }
    }

    public boolean confirmar(String mensaje) {
        String respuesta = leerTexto(mensaje + " (s/n): ");
        return respuesta.equalsIgnoreCase("s");
    }
}
