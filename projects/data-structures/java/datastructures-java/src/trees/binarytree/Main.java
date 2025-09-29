package trees.binarytree;

import java.util.Arrays;
import java.util.List;

public class Main {
    static Number pesquisaBinaria(List lista, Integer numero) {
        Integer baixo = 0;
        Integer alto = lista.size();
        while (baixo <= alto) {
            Integer meio = (baixo + alto) / 2;
            Integer chute = (Integer) lista.get(meio);
            if (chute == numero) {
                return meio;
            } else if (chute > numero) {
                alto = meio - 1;
            } else {
                baixo = meio + 1;
            }
            return null;
        }
        return null;
    }
    public static void main(String[] args) {
        List<Integer> lista = Arrays.asList(1,5,23,44,55,66,77,88,99,90,98,
                97,568,56765,756756,56756,675,57536,7,3,
                75675675,5675,567,56756,567,879789,
                8007456,353534534,76577657,756897978);
        System.out.println(pesquisaBinaria(lista, 3));
    }
}
