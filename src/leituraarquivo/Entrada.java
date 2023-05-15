package leituraarquivo;

import java.util.Scanner;

class Entrada {
    Scanner sc;

    Entrada() {
        sc = new Scanner(System.in);
    }

    public String entradaTexto(String texto) {
        System.out.print(texto);
        texto = sc.nextLine();
        return texto;
    }
}
