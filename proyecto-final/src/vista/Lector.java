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

    public int leerEnteroNoNegativo(String mensaje) {
        while (true) {
            int valor = leerEntero(mensaje);
            if (valor >= 0) {
                return valor;
            }
            System.out.println("El valor no puede ser negativo.");
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
            if (esFechaValida(texto)) {
                return texto;
            }
            System.out.println("Fecha no valida. Use AAAA-MM-DD, con mes de 01 a 12 y un dia que exista en ese mes.");
        }
    }

    private boolean esFechaValida(String texto) {
        if (!texto.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            return false;
        }
        int anio = Integer.parseInt(texto.substring(0, 4));
        int mes = Integer.parseInt(texto.substring(5, 7));
        int dia = Integer.parseInt(texto.substring(8, 10));
        if (mes < 1 || mes > 12 || dia < 1) {
            return false;
        }
        int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int maximo = diasPorMes[mes - 1];
        boolean bisiesto = (anio % 4 == 0 && anio % 100 != 0) || anio % 400 == 0;
        if (mes == 2 && bisiesto) {
            maximo = 29;
        }
        return dia <= maximo;
    }

    public boolean confirmar(String mensaje) {
        String respuesta = leerTexto(mensaje + " (s/n): ");
        return respuesta.equalsIgnoreCase("s");
    }
}
