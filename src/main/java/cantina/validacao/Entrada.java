package cantina.validacao;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Entrada implements AutoCloseable {
    private Scanner scan;

    public Entrada() {
        this.scan = new Scanner(System.in);
    }

    public String lerString(String msg, String errorMessage) {
        var scan = System.console();
        String palavra;
        while (true) {
            System.out.print(msg);
            palavra = scan.readLine().strip().toLowerCase();
            if (!Pattern.matches("^[a-zÀ-ÿ\s]+$", palavra)) {
                System.out.println(errorMessage + "\n");
                continue;
            }
            return palavra;
        }
    }

    public String lerEmail(String msg) {
        var scan = System.console();
        String email;
        while (true) {
            System.out.print(msg);
            email = scan.readLine().strip().toLowerCase();
            if (!Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email)) {
                System.out.println("Formato de Email inválido" + "\n");
                continue;
            }
            return email;
        }
    }

    public String lerSenha(String msg) {
        var scan = System.console();
        while (true) {
            System.out.print(msg);
            var senha = scan.readLine().strip();
            return senha;
        }
    }

    public int lerInt(String msg) {
        while (true) {
            System.out.print(msg);
            var num = this.scan.nextLine();
            try {
                return Integer.parseInt(num);
            } catch (NumberFormatException e) {
                System.out.printf("Número inteiro inválido. %s%n%n", e.getMessage());
            }
        }
    }

    public double lerDouble(String msg) {
        while (true) {
            System.out.print(msg);
            var num = this.scan.nextLine();
            try {
                return Double.parseDouble(num);
            } catch (NumberFormatException e) {
                System.out.printf("Número decimal inválido. %s%n%n", e.getMessage());
            }
        }
    }

    public int lerOption(String msg, int min, int max, String erroMessage) {
        int num;
        while (true) {
            num = this.lerInt(msg);
            try {
                if (num < min || num > max) {
                    throw new IllegalArgumentException(erroMessage + "\n");
                }
                return num;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void close() {
        this.scan.close();
    }
}