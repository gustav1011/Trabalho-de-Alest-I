import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Gustavo Munoz Dutra
 */
public class Main {
    public static void main(String[] args) {
        arvoreGenealogica arvore = readTree("ArvoreTeste_UTF8.txt");

        // conjunto inicial de casos de teste
        // complete-o para os métodos não implementados

        System.out.println("\n2. Ascendentes de Júlia S01 A02");
        List<String> asc = arvore.ascendentes("Júlia S01 A02");
        for (String s : asc) {
            System.out.print(s + "  ");
        }
        System.out.println("\n---");

          System.out.println("\n3. Número de descendentes de Mauro Paulo S01: "+
          arvore.contarDescendentes("Mauro Paulo S01"));
         System.out.println("\n---");

          System.out.println("\n4. Listar Filhos de Mauro Paulo S01 : ");
          List<String> filhos = arvore.listarFilhos("Mauro Paulo S01");
          for(String f : filhos) {
              System.out.print(f+"  ");
          }
            System.out.println("\n---");

          System.out.println("\n5. Netos de Edmundo Antônio S01");
          List<String> netos = arvore.listarNetos("Edmundo Antônio S01");
          for (String s : netos ) {
          System.out.print(s+"  ");
          }
          System.out.println("\n---");

          System.out.println("\n6. Listar Tios de Júlia S01 A02"  );
        List<String> tios = arvore.Tios("Júlia S01 A02");
        for(String s : tios){
            System.out.print(s+"  ");
        }
          System.out.println("\n---");

         System.out.println("\n7. Primos de Júlia S01 A02");
          List<String> primos = arvore.primos("Júlia S01 A02");
          for (String s : primos ) {
          System.out.print(s+"  ");
          }
          System.out.println("\n---");

          System.out.println("\nMostrar...");
          arvore.hierarchicalStructure("AA");
          arvore.hierarchicalStructure("Henrique João S01");


    }

    private static arvoreGenealogica readTree(String arq) {

        arvoreGenealogica arv = null;
        try {
            BufferedReader buff = new BufferedReader(new FileReader(arq));
            String line = null;
            try {
                line = buff.readLine();
                line = line.trim();
                arv = new arvoreGenealogica(line);
                line = buff.readLine();

                while (line != null) {
                    line = line.trim();
                    String[] pessoas = line.split(" \\| ");
                    for (int i = 1; i < pessoas.length; i++) {
                        arv.inserir(pessoas[i], pessoas[0]);
                    }
                    line = buff.readLine();

                }
            } finally {
                buff.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arv;
    }
}
