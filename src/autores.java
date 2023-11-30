import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import java.util.List;

public class autores {
    public static void main(String[] args) throws IOException {

        // Pegao os arquivos da pasta
        File file = new File("src\\Autores");
        File afile[] = file.listFiles();
        List<String> listaAutores = new ArrayList<String>();
        List<String> listaAutor = new ArrayList<String>();
        String texto = "";

        // ForEach usado para ler os textos
        // Pegar a ultima linha e colocar dentro do arraylist
        // Os textos nao estao em ordem
        for (File a : afile) {

            List<String> linhas = Files
                    .readAllLines(new File("src\\Autores/" + a.getName()).toPath());
            String autores = linhas.get(linhas.size() - 1).toLowerCase();
            listaAutores.add(autores);

        }

        // Junta todos os autores em um so texto
        // por que assim é mais facil de transforma em arrays
        for (String textoAutores : listaAutores) {
            texto += textoAutores + ",";
        }

        // cria um array com o doblo do tamanho da listaAutores
        // porque todos cada um dos elementos da listaAutores vai ser dividido em dois
        String[] arrayAutor = new String[listaAutores.size() * 2];

        // Coloca elementos dentro do array
        for (int i = 0; i < arrayAutor.length; i++) {
            arrayAutor = texto.split(",");
        }

        // Transforma o array em arraylist
        // Retira as repeticoes
        for (int i = 0; i < arrayAutor.length; i++) {
            if (!listaAutor.contains(arrayAutor[i])) {
                listaAutor.add(arrayAutor[i]);
            }

        }

        // Printa o arraylist
        // for(int i = 0; i <listaAutores.size(); i++){
        // System.out.println(listaAutor.get(i));
        // }

        //Um for dentro de um for que pega o nome de um autor
        //compara com as lista que tem o nome de todos os autores
        // E se o nome do autor foi encontrado e printa esse autor
        for (int i = 0; i < listaAutor.size(); i++) {
            System.out.println(" ");
            System.out.println(listaAutor.get(i) + " Tambem escreveu com : ");
            for (int x = 0; x < listaAutores.size(); x++) {
                if (listaAutores.get(x).contains(listaAutor.get(i))) {
                    System.out.println(listaAutores.get(x));
                }
            }
        }

    }

}
