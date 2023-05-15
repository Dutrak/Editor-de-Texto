package leituraarquivo;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class LeituraDados {

    //Declaração de variáveis globais
    public static Entrada e = new Entrada();
    public static Path arquivo = null;
    public static String mensagem = "";
    public static int cont = 0;
    public static boolean erro = false;
    public static String[] dados = new String[200];

    public static void main(String[] args) throws Exception {

        //criação do arquivo de texto
        arquivo = Paths.get("C:\\_dev\\Java\\ExercicioLeitura\\src\\arquivo.txt");

        //verifica se o arquivo existe, se existir ele é deletado para ser criado novamente
        if (Files.exists(arquivo)) {
            Files.delete(arquivo);
        }

        //Cria o cabeçalho do exercicio
        System.out.println();
        System.out.println("CRIADOR DE ARQUIVOS DE TEXTO");
        System.out.println("COMANDOS: //FIM e //EDITAR");
        System.out.println();

        // Rotulo para o loop do programa
        Looping:

        do {
            for (int i = 0; i < dados.length; i++) {
                try {
                    //leitura dos dados
                    dados[i] = e.entradaTexto(i + ": ");
                    cont++;

                    // verifica se o dado digitado é //FIM
                    if (dados[i].contains("//FIM")) {
                        // sai do loop utilizando o rotulo looping definido acima
                        break Looping;
                    } 
                    
                    // verifica se o dado digitado é //EDITAR
                    else if (dados[i].contains("//EDITAR")) {
                        int posicao = Integer.parseInt(dados[i].substring(9));

                        //Verifica se a linha possui ja passou no looping de entrada
                        //caso a linha exista o conteudo é substituido pelo novo dado
                        if (posicao >= cont - 1) {
                            System.out.println("Linha não existe no registro");
                            erro = true;
                            i--;
                        } else {
                            print(dados);
                            dados[posicao] = e.entradaTexto("Digite o novo dado: ");
                            print(dados);
                        }
                    } 

                //Trata o erro caso o a linha digitada não seja um numero
                } catch (NumberFormatException ex) {
                    System.out.println("O comando editar exige um valor numérico para a linha (ex: //EDITAR 1))");
                    erro = true;
                    i--;
                } 
                //Trata o erro caso não seja digitada nenhuma linha
                catch (StringIndexOutOfBoundsException ex) {
                    System.out.println("O comando editar exige o numero da linha (ex: //EDITAR 1))");
                    erro = true;
                    i--;
                }
            }
        } while (erro = false);

        //Etapa final do programa, chamada das funções de atruibuição, print e gravação do arquivo
        atribuir();
        print(dados);
        Files.write(arquivo, mensagem.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE_NEW);
    }

    // atribui os dados digitados a variavel mensagem
    public static void atribuir(){
        for (int i = 0; i < cont; i++) {
            if (dados[i] != null && !(dados[i].contains("//FIM")) && !(dados[i].contains("//EDITAR"))) {
                mensagem += dados[i] + "\n";
            }
        }
    }

    // imprime os dados digitados
    public static void print(String[] vetor) {
        System.out.println();
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] != null && !(vetor[i].contains("//FIM")) && !(vetor[i].contains("//EDITAR"))) {
                System.out.println(i + ": " + vetor[i]);
            }
        }
        System.out.println();
    }
}
