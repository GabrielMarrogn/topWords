import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class index {
    public static void main(String[] args) throws IOException {
        File file = new File("src/Autores");
        File afile[] = file.listFiles();
        List<String> linhas = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        int i = 0;
        int escolha = 0;
        List<String> guardador1 = new ArrayList<String>();
        List<String> guardador2 = new ArrayList<String>();
        List<String> resultado = new ArrayList<String>();

        // foreach que todos os arquivos
        for (File files : afile) {

            String texto = Files
                    .readString(new File("/analise-algoritimo/ProjetoA3/Autores/" + files.getName()).toPath());
            linhas.add(texto);

        }

        // Cria um pequena interface para ver as palavras mais importantes de uma texto
        // basta digitar o numero do texto (do 0 ao 59)
        while (i != 1000) {
            System.out.println("");
            System.out.println("Se dejasa ver as palavras mais importante  de um texto digite 1");
            System.out.println("Se dejasa ver as fazer comparacoes entre textos digite 2");
            System.out.println("ou digite quaquer outro numero para sair");
            // Trycatch para impedir que letra
                try {
                    escolha = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Erro! por favor apenas digite numeros");
                    break;
                }
            if (escolha == 1) {
                System.out.println("");
                System.out.println("Digite o numero do texto que deja ver:");
                System.out.println("ou digite 1000 para sair");
                int numero = 0;
                // Trycatch para impedir que letra
                try {
                    numero = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Erro! por favor apenas digite numeros");
                    break;
                }
                if (numero == 1000) {
                    System.out.println("Saindo...");
                    i = 1000;
                } else if (numero > 59) {
                    System.out.println("Nao texto correspondente a esse numero ");
                    System.out.println("Por fazer digite um numero ente 0 e 59");
                } else {
                    procesamento(linhas.get(numero));
                }
            } else if (escolha == 2) {
                System.out.println("");
                System.out.println("Digite o numero do primeiro texto:");
                System.out.println("ou digite 1000 para sair");
                int numero = sc.nextInt();
                System.out.println("Digite o numero do Segundo texto:");
                System.out.println("ou digite 1000 para sair");
                int numero2 = sc2.nextInt();

                if (numero == 1000 || numero2 == 1000) {
                    System.out.println("Saindo...");
                    i = 1000;
                } else if (numero > 59 || numero2 > 59) {
                    System.out.println("Nao texto correspondente a esse numero ");
                } else if (numero >= 0 && numero < 60) {
                    guardador1 = StoreWords(linhas.get(numero));
                    guardador2 = StoreWords(linhas.get(numero2));
                    resultado = ComparaVetor(guardador1, guardador2);

                    System.out.println("Os dois textos possuem essa palavra(s) em comun :");
                    System.out.println(resultado);

                }
            }
            else if(escolha !=1 && escolha != 2 ){
                System.out.println("Numero ivalido");
                i = 1000;
            }

        }
        sc.close();
        sc2.close();

    }

    // Funcao que faz o pre-processamento e o processamento do texto
    // Mostra quantas vez cada palavra aparece no texto e cria o grafo
    // Usada para mostra as principais palavras do texto
    public static void procesamento(String basico) throws IOException {
        // Usada para transformar acentos e caracteres especiais em palavras normais
        String nfdNormalizedString = Normalizer.normalize(basico, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String semAcento = pattern.matcher(nfdNormalizedString).replaceAll("");
        // Lista de stopwords
        List<String> stopwords = Files.readAllLines(Paths.get("src\\stopwords.txt"));
        // torna todas as palavras em minusculas, retira virgulas, e separa o texto
        String[] allWords = semAcento.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

        // retira as stopwords
        StringBuilder builder = new StringBuilder();
        for (String word : allWords) {
            if (!stopwords.contains(word)) {
                builder.append(word);
                builder.append(' ');
            }
        }
        String resultado = builder.toString().trim();

        // faz a tokenizacao das palavras
        String[] result = resultado.split("\\s");

        // Cria um novo array sem as repeticoes
        List<String> tempResult = new ArrayList<String>();

        for (int k = 0; k < result.length; k++) {
            if (!tempResult.contains(result[k])) {
                tempResult.add(result[k]);
            }

        }

        String[] resultFinal = tempResult.toArray(new String[tempResult.size()]);

        System.out.println("Lista das palavras que mais aparecem no texto");
        System.out.println(" ");
        // loop para imprimir as palavras e conta quantas vezes ela aparece no texto
        for (int i = 0; i < resultFinal.length; i++) {

            // chama o metodo contarRepeticoes
            Object contadorPalavras = contarRepeticoes(result, result[i]);

            // Mostra somente as palavras que aparecem mais de duas vezes
            if (!contadorPalavras.equals(1) && !contadorPalavras.equals(2)) {
                contarRepeticoes(result, result[i]);

                System.out.println(resultFinal[i] + " (" + contadorPalavras + ") ");
            }

        }

        System.out.println("");

        // Cria um grafo

        // Cria os nos do grafo
        Node a = new Node(resultFinal[0]);
        Node b = new Node(resultFinal[1]);
        Node c = new Node(resultFinal[2]);
        Node d = new Node(resultFinal[3]);
        Node e = new Node(resultFinal[4]);
        Node f = new Node(resultFinal[5]);

        ArrayList<Node> list = new ArrayList<Node>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);
        list.add(f);

        Graph g = new Graph(list);

        // cria as vertices do grafo
        g.addEdge(a, b);
        g.addEdge(a, c);
        g.addEdge(c, d);
        g.addEdge(c, e);
        g.addEdge(d, e);
        g.addEdge(d, f);

        System.out.println("Grafo feito usando lista de adjacencia");
        System.out.println("");

        g.printAdjList();

    }

    // Funcao que armazena as palavras mais importantes do texto em uma vetor
    // Para que outra funcao faca a comparacao 
    public static List<String> StoreWords(String basico) throws IOException {
        //
        List<String> importantWords = new ArrayList<>();
        // Usada para transformar acentos e caracteres especiais em palavras normais
        String nfdNormalizedString = Normalizer.normalize(basico, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String semAcento = pattern.matcher(nfdNormalizedString).replaceAll("");
        // Lista de stopwords
        List<String> stopwords = Files.readAllLines(Paths.get("src\\stopwords.txt"));
        // torna todas as palavras em minusculas, retira virgulas, e separa o texto
        String[] allWords = semAcento.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

        // retira as stopwords
        StringBuilder builder = new StringBuilder();
        for (String word : allWords) {
            if (!stopwords.contains(word)) {
                builder.append(word);
                builder.append(' ');
            }
        }
        String resultado = builder.toString().trim();

        // faz a tokenizacao das palavras
        String[] result = resultado.split("\\s");

        // Cria um novo array sem as repeticoes
        List<String> tempResult = new ArrayList<String>();

        for (int k = 0; k < result.length; k++) {
            if (!tempResult.contains(result[k])) {
                tempResult.add(result[k]);
            }

        }

        for (int i = 0; i < tempResult.size(); i++) {

            // chama o metodo contarRepeticoes
            Object contadorPalavras = contarRepeticoes(result, result[i]);

            // Mostra somente as palavras que aparecem mais de duas vezes
            if (!contadorPalavras.equals(1) && !contadorPalavras.equals(2)) {
                importantWords.add(tempResult.get(i));

            }

        }

        return importantWords;

    }

    // Compara os vetores para ver quantos elementos sao iguais
    public static List<String> ComparaVetor(List<String> guardador1, List<String> guardador2) {
        List<String> iguais = new ArrayList<>();
        for (int i = 0; i < guardador1.size(); i++) {
            for (int k = 0; k < guardador2.size(); k++) {
                if (guardador1.get(i).equals(guardador2.get(k))) {
                    iguais.add(guardador2.get(k));
                }
            }
        }
        if(iguais.isEmpty()){
            System.out.println("Nao ha palavras iguais entre os textos");
        }
        return iguais;
    }

    // funcao que conta quantas vezes a palavra aparece no texto
    public static int contarRepeticoes(String[] array, String valor) {
        int contador = 0;

        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null) {
                    if (array[i].equals(valor)) {
                        contador++;
                    }
                }
            }

        }

        else {
            System.out.println("Vetor vazio");
        }
        return contador;

    }

}
