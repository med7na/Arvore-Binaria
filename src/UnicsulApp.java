import javax.swing.*;
import java.util.*;

public class UnicsulApp {

    // ================== CLASSE DA ÁRVORE ==================
    static class ArvoreBinaria {
        // Nó interno
        static class No {
            String nome;
            No esquerda, direita;

            No(String nome) {
                this.nome = nome;
                esquerda = direita = null;
            }
        }

        No raiz;

        // Inserir aluno
        void inserir(String nome) {
            raiz = inserirRecursivo(raiz, nome);
        }

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

        // Buscar aluno
        boolean buscar(String nome) {
            return buscarRecursivo(raiz, nome);
        }

        boolean buscarRecursivo(No atual, String nome) {
            if (atual == null) return false;
            if (nome.equalsIgnoreCase(atual.nome)) return true;
            if (nome.compareToIgnoreCase(atual.nome) < 0) {
                return buscarRecursivo(atual.esquerda, nome);
            } else {
                return buscarRecursivo(atual.direita, nome);
            }
        }

        // Listar em ordem
        void emOrdem(StringBuilder sb) {
            emOrdemRecursivo(raiz, sb);
        }

        void emOrdemRecursivo(No atual, StringBuilder sb) {
            if (atual != null) {
                emOrdemRecursivo(atual.esquerda, sb);
                sb.append(atual.nome).append("\n");
                emOrdemRecursivo(atual.direita, sb);
            }
        }
    }

    // ================== PROGRAMA PRINCIPAL ==================
    public static void main(String[] args) {
        // Cria os campi
        Map<String, ArvoreBinaria> campi = new HashMap<>();
        campi.put("Anália Franco", new ArvoreBinaria());
        campi.put("Guarulhos", new ArvoreBinaria());
        campi.put("Liberdade", new ArvoreBinaria());
        campi.put("Paulista", new ArvoreBinaria());
        campi.put("São Miguel", new ArvoreBinaria());
        campi.put("Santo Amaro", new ArvoreBinaria());
        campi.put("Villa Lobos", new ArvoreBinaria());

        String[] opcoes = {"Cadastrar Aluno", "Localizar Aluno", "Listar Alunos", "Sair"};

        while (true) {
            int opcao = JOptionPane.showOptionDialog(
                    null,
                    "Sistema Unicsul - Escolha uma opção",
                    "Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            switch (opcao) {
                case 1:
                    String campus = JOptionPane.showInputDialog(null,"Digite o campus");
                    String nome = JOptionPane.showInputDialog(null, "Digite o nome do aluno: ");

                    // Verificar se já existe em algum campus
                    boolean existe = false;
                    for (ArvoreBinaria arv : campi.values()) {
                        if (arv.buscar(nome)) {
                            existe = true;
                            break;
                        }
                    }

                    if (existe) {
                        JOptionPane.showMessageDialog(null, "Aluno já existe em algum campus!");
                    } else {
                        ArvoreBinaria arv = campi.get(campus);
                        if (arv != null) {
                            arv.inserir(nome);
                            JOptionPane.showMessageDialog(null,"Aluno cadastrado com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(null,"Campus inválido!");
                        }
                    }
                    break;

                case 2:
                    String nomeBusca = JOptionPane.showInputDialog(null, "Digite o nome do aluno: ");
                    boolean encontrado = false;
                    for (Map.Entry<String, ArvoreBinaria> entry : campi.entrySet()) {
                        if (entry.getValue().buscar(nomeBusca)) {
                            JOptionPane.showMessageDialog(null,"Aluno encontrado no campus " + entry.getKey());
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        JOptionPane.showMessageDialog(null,"Aluno não localizado em nenhum campus.");
                    }
                    break;

                case 3:
                    String campusListar = JOptionPane.showInputDialog(null, "Digite o campus:");
                    ArvoreBinaria arv = campi.get(campusListar);
                    if (arv != null) {
                        StringBuilder sb = new StringBuilder();
                        arv.emOrdem(sb);
                        if (sb.length() == 0) {
                            sb.append("Nenhum aluno cadastrado.");
                        }
                        JOptionPane.showMessageDialog(null,
                                "Alunos do campus " + campusListar + ":\n" + sb.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Campus inválido!");
                    } break;


                case 0:
                    JOptionPane.showMessageDialog(null,"Encerrando...");
                    return;

            }
        }


    }
}
