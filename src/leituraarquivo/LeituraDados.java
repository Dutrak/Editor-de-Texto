/*
 * Autores: Fernando Dutra de Oliveira, Heitor Gonçalez Bovi
 * Data: 17/05/2023
 */

package leituraarquivo;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class LeituraDados {

    // Declaração de variáveis globais
    public static Entrada e = new Entrada();
    public static Path arquivo = null;
    public static String mensagem = "";
    public static int cont = 0;
    public static boolean erro = false;
    public static String[] dados = new String[200];
    public static int index;
    public static List<String> dadosold = new ArrayList<String>();

    public static void main(String[] args) throws Exception {

        // criação do arquivo de texto
        arquivo = Paths.get("arquivo.txt");

        // printa o cabeçalho do programa
        System.out.println();
        System.out.println("CRIADOR DE ARQUIVOS DE TEXTO");
        System.out.println("COMANDOS: //FIM e //EDITAR");
        System.out.println();

        // verifica se o arquivo existe
        if (Files.exists(arquivo)) {
            // se o arquivo existir, ele é lido e seus dados são guardados em uma lista auxiliar
            dadosold = Files.readAllLines(arquivo);
            // o index recebe o tamanho da lista auxiliar
            index = dadosold.size();
            // printa os dados da lista auxiliar e atribui ao vetor principal
                for (int i = 0; i < dadosold.size(); i++) {
                    System.out.println(i + ": " + dadosold.get(i));
                    cont++; // aumenta o cont para cada elemento ja existente no arquivo
                    dados[i] = dadosold.get(i);
                }
            System.out.println();
        }
        // Rotulo para o loop do programa
        Looping:

        do

        {
            for (int i = index; i < 200; i++) {
                try {
                    // leitura dos dados
                    dados[i] = e.entradaTexto("Entra o dado da linha " + i + ": ");
                    

                    // verifica se o dado digitado é //FIM
                    if (dados[i].equals("//FIM")) {
                        // sai do loop utilizando o rotulo looping definido acima
                        break Looping;
                    }

                    // verifica se o dado digitado é //EDITAR
                    else if (dados[i].substring(0, 8).equals("//EDITAR")) {
                        // pega o numero da linha digitada
                        int posicao = Integer.parseInt(dados[i].substring(9, dados[i].length()));
                        System.out.println(posicao);

                        // Verifica se a linha possui ja passou no looping de entrada
                        // caso a linha exista o conteudo é substituido pelo novo dado
                        if (i - 1 < posicao) {
                            System.out.println("Linha não existe no registro");
                            erro = true;
                            i--;
                        } else {
                            // adiciona um * no final da linha para indicar que a linha será editada e
                            // printa
                            dados[posicao] = dados[posicao] + " *";
                            print(dados);
                            // Editar o dado da linha e printa
                            dados[posicao] = e.entradaTexto("Digite o novo dado: ");
                            print(dados);
                            i--;
                        }
                    }

                    // Trata o erro caso o a linha digitada não seja um numero
                } catch (NumberFormatException ex) {
                    System.out.println("O comando editar exige um valor numérico para a linha (ex: //EDITAR 1))");
                    erro = true;
                    i--;
                }
                // Trata o erro caso não seja digitada nenhuma linha
                catch (StringIndexOutOfBoundsException ex) {
                    System.out.println("O comando editar exige o numero da linha (ex: //EDITAR 1))");
                    erro = true;
                    i--;
                }

                // Printa os dados digitados ao final de cada digitação, com o titulo do arquivo
                System.out.println("\n\t\t\tArquivo.txt");
                cont++;
                print(dados);

            }
        } while (erro = false);

        // Etapa final do programa, chamada das funções de atruibuição, print e gravação
        // do arquivo
        atribuir();

        print(dados);
        System.out.println(mensagem);
        System.out.println(cont);
        Files.write(arquivo, mensagem.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
    }

    // atribui os dados digitados a variavel mensagem
    public static void atribuir() {
        for (int i = 0; i < cont; i++) {
            if (dados[i] != null && !(dados[i].equals("//FIM")) && !(dados[i].startsWith("//EDITAR"))) {
                mensagem += dados[i] + "\n";
            }
        }
    }

    // imprime os dados digitados
    public static void print(String[] vetor) {
        System.out.println();
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] != null && !(vetor[i].equals("//FIM")) && !(vetor[i].startsWith("//EDITAR"))){
                System.out.println(i + ": " + vetor[i]);
            }
        }
        System.out.println();
    }
}
