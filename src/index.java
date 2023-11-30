import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class index {
    public static void main(String[] args) throws IOException {
        File file = new File("src\\Autores");
        File afile[] = file.listFiles();
        List<String> linhas = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        int i = 0;

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
            System.out.println("Digite o numero do texto que deja ver:");
            System.out.println("ou digite 1000 para sair");
            int numero = 0;
            //Trycatch para impedir que letra 
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

        }
        sc.close();

    }

    // Funcao que faz o pre-processamento e o processamento do texto
    public static void procesamento(String basico) throws IOException {
        // Lista de stopwords
        List<String> stopwords = Files.readAllLines(Paths.get("src\\stopwords.txt"));
        // torna todas as palavras em minusculas, retira virgulas, e separa o texto
        String[] allWords = basico.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

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