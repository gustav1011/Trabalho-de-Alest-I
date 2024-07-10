import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.*;
/**
 *
 * @author Gustavo Munoz Dutra
 */
public class arvoreGenealogica {

    private Node raiz;

    public arvoreGenealogica(String elem) {// construtor da classe arvoreGenealogica
        raiz = new Node(elem);
    }

    private static class Node {// classe Nodo
        String Elem;
        Node Pai;
        List<Node> Filhos;

        public Node(String valor) {// construtor da classe Node
            Elem = valor;
            Pai = null;
            Filhos = new ArrayList<Node>();
        }
    }

    public void inserir(String elem, String paiStr) {
        Node pai = pesquisa(paiStr, raiz);
        if (pai != null) {
            Node novo = new Node(elem);
            novo.Pai = pai;
            pai.Filhos.add(novo);
        }
    }

    private Node pesquisa(String e, Node r) {
        if (r.Elem.equals(e))
            return r;
        for (Node f : r.Filhos) {
            Node aux = pesquisa(e, f);
            if (aux != null)
                return aux;
        }
        return null;
    }

    public void preordem() {
        preordem(raiz);
    }

    private void preordem(Node r) {
        System.out.print(r.Elem + " - ");
        for (Node f : r.Filhos) {
            preordem(f);
        }
    }

    public void largura() {
        Queue<Node> q = new LinkedList<Node>();
        q.add(raiz);
        while (!q.isEmpty()) {
            Node aux = q.remove();
            System.out.print(aux.Elem + " - ");
            for (Node f : aux.Filhos) {
                q.add(f);
            }
        }
    }
    public ArrayList<String> ascendentes(String Elem) {
        ArrayList<String> ancestry = new ArrayList<String>();
        Node aux = pesquisa(Elem, raiz);
        if(aux!=null) aux=aux.Pai;
        while (aux != null) { // ainda estamos percorrendo nós válidos na árvore.
            ancestry.add(aux.Elem); // Adiciona o elemento do nó atual à lista de ancestrais.
            aux = aux.Pai; // Move-se para o nó pai do nó atual.
        }
        Collections.reverse(ancestry);
        return ancestry; // Retorna a lista de ancestrais.
    }

    public int contarDescendentes(String Elem) {
        int count = 0;
        Node aux = pesquisa(Elem, raiz);
        return contafilhos(aux);
    }
    private int contafilhos(Node ref){
        if(ref==null) return 0;
        else{
            int outros=0;
            for(Node f: ref.Filhos)
                outros+=contafilhos(f);
            return outros+ref.Filhos.size();
        }
    }

    public ArrayList<String> listarFilhos(String Elem) {
        ArrayList<String> listaFilhos = new ArrayList<String>();// Cria uma nova lista para armazenar os elementos dos filhos
        Node aux = pesquisa(Elem, raiz);// Pesquisa o nó que contém o elemento 'Elem' começando pela raiz da árvore
        if (aux != null) {// Verifica se o nó foi encontrado (se aux não for nulo)
            for (Node filho : aux.Filhos) {// Itera sobre cada filho do nó encontrado (aux)
                listaFilhos.add(filho.Elem); // Adiciona o elemento do nó filho à lista de filhos
            }
        }
        return listaFilhos; // Retorna a lista contendo os elementos dos filhos
    }


    public ArrayList<String> listarNetos(String Elem) {
        ArrayList<String> listarNetos = new ArrayList<String>();
        Node aux = pesquisa(Elem, raiz);
        if (aux != null) {
            for (Node filhos : aux.Filhos) {
                for (Node netos : filhos.Filhos) {
                    listarNetos.add(netos.Elem);
                }
            }
        }
        return listarNetos;
    }

    public ArrayList<String> Tios(String Elem) {
        ArrayList<String> tios = new ArrayList<String>();
        Node elemento = pesquisa(Elem, raiz); // Encontra o nó correspondente ao elemento
        if (elemento != null) {
            Node pai = elemento.Pai; // Obtém o pai do elemento
            if (pai != null && pai.Pai != null) { // Verifica se o elemento tem um pai e se o pai tem um pai (avô do elemento)
                Node avo = pai.Pai; // Obtém o avô do elemento
                for (Node tio : avo.Filhos) { // Itera sobre os filhos do avô para encontrar os tios
                    if (!tio.equals(pai)) {  // se o tio for diferente do pai adicionamos na lista
                        tios.add(tio.Elem);
                    }
                }
            }
        }
        return tios;
    }

    public ArrayList<String> primos(String Elem) {
        ArrayList<String> primos = new ArrayList<String>();
        Node elemento = pesquisa(Elem, raiz);// vai procurar o nodo na árvore!
        if (elemento != null) {
            Node pai = elemento.Pai;
            if (pai != null && pai.Pai != null) { // Verifica se o elemento tem um pai e se o pai tem um pai (avô do elemento)
                Node avo = pai.Pai; // Obtém o avô do elemento
                for (Node tio : avo.Filhos) { // Itera sobre os filhos do avô para encontrar os tios
                    for (Node primo : tio.Filhos) {
                        primos.add(primo.Elem);
                    }
                }
            }
        }
        return primos;
    }

    public void hierarchicalStructure(String Elem) {
        Node elemento = pesquisa(Elem, raiz); // Encontra o nó correspondente ao elemento
        if (elemento != null) {
            preordem(elemento); // Chama o método preordem para percorrer em pré-ordem a partir do nó encontrado
            System.out.println(" "); // Pula para uma nova linha após exibir a estrutura hierárquica
        } else {
            System.out.println("Árvore: ");
        }
    }

    }

