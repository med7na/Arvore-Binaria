public class ArvoreBinaria { // Árvore Binária Recursiva

    // Classe interna para representar um nó da árvore
    static class No {
        String nome;
        No esquerda, direita;

        No(String nome) {
            this.nome = nome;
            esquerda = direita = null;
        }
    }

    No raiz;

    // Método para inserir um nome na árvore
    void inserir(String nome) {
        raiz = inserirRecursivo(raiz, nome);
    }

    // Inserção recursiva seguindo as regras da BST
    No inserirRecursivo(No atual, String nome) {
        if (atual == null) {
            return new No(nome);
        }

        if (nome.compareToIgnoreCase(atual.nome) < 0) {
            atual.esquerda = inserirRecursivo(atual.esquerda, nome);
        } else if (nome.compareToIgnoreCase(atual.nome) > 0) {
            atual.direita = inserirRecursivo(atual.direita, nome);
        }

        return atual;
    }

    // Impressão in-order (alfabética)
    void emOrdem() {
        System.out.println("Nomes em ordem alfabética:");
        emOrdemRecursivo(raiz);
    }

    void emOrdemRecursivo(No atual) {
        if (atual != null) {
            emOrdemRecursivo(atual.esquerda);
            System.out.println(atual.nome);
            emOrdemRecursivo(atual.direita);
        }
    }
    
     // ========== NOVOS MÉTODOS DE TRAVESSIA ==========

    // Travessia em Pré-Ordem (raiz -> esquerda -> direita)
    void preOrdem() {
        System.out.println("Nomes em Pré-Ordem:");
        preOrdemRecursivo(raiz);
        System.out.println();
    }

    void preOrdemRecursivo(No atual) {
        if (atual != null) {
            System.out.print(atual.nome + " "); // 1. Visita o nó atual primeiro
            preOrdemRecursivo(atual.esquerda); // 2. Depois percorre a subárvore esquerda
            preOrdemRecursivo(atual.direita);  // 3. Por último percorre a subárvore direita
        }
    }

    // Travessia em Pós-Ordem (esquerda -> direita -> raiz)
    void posOrdem() {
        System.out.println("Nomes em Pós-Ordem:");
        posOrdemRecursivo(raiz);
        System.out.println();
    }

    void posOrdemRecursivo(No atual) {
        if (atual != null) {
            posOrdemRecursivo(atual.esquerda); // 1. Percorre a subárvore esquerda
            posOrdemRecursivo(atual.direita);  // 2. Depois percorre a subárvore direita
            System.out.print(atual.nome + " "); // 3. Exibe o nó atual por último
        }
    }

    
    // Método público para buscar um nome
    boolean buscar(String nome) {
        return buscarRecursivo(raiz, nome);
    }
    
    // Método recursivo para busca
    boolean buscarRecursivo(No atual, String nome) {
        if (atual == null) {
            return false; // Nó não encontrado
        }
    
        if (nome.equalsIgnoreCase(atual.nome)) {
            return true; // Nome encontrado
        }
    
        if (nome.compareToIgnoreCase(atual.nome) < 0) {
            return buscarRecursivo(atual.esquerda, nome); // Buscar na subárvore esquerda
        } else {
            return buscarRecursivo(atual.direita, nome); // Buscar na subárvore direita
        }
    }

    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();

        // Lista de 10 nomes aleatórios
        String[] nomes = {
            "Lucas", "Amanda", "Bruno", "Carla", "Eduardo",
            "Fernanda", "Gustavo", "Helena", "Igor", "Beatriz"
        };

        for (String nome : nomes) {
            arvore.inserir(nome);
        }

        // Exibir os nomes em ordem alfabética
        arvore.emOrdem();
        arvore.preOrdem();
        arvore.posOrdem();
        
        String nomeParaBuscar = "Helena";
        
        if (arvore.buscar(nomeParaBuscar)) {
            System.out.println(nomeParaBuscar + " foi encontrado na árvore."); 
        } else {
            System.out.println(nomeParaBuscar + " não está na árvore.");
        }

    }
}
