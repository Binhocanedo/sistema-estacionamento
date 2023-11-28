package br.com.fabio.estacionamento.estruturaDeDados;
import br.com.fabio.estacionamento.gerenciamento.Estaciona;


public class VeiculoLinkedList {
    private VeiculoNode head;

    public VeiculoLinkedList() {
        this.head = null;
    }

    public void adicionar(Estaciona.RegistroEntrada registro) {
        VeiculoNode novoVeiculo = new VeiculoNode(registro);
        if (head != null) {
            novoVeiculo.next = head;
        }
        head = novoVeiculo;
    }

    public void remover(Estaciona.RegistroEntrada registro) {
        if (head == null) {
            System.out.println("Lista vazia. Nada a remover.");
            return;
        }

        VeiculoNode current = head;
        VeiculoNode previous = null;

        while (current != null) {
            if (current.registroEntrada.equals(registro)) {
                if (previous == null) {
                    // O nó a ser removido é o primeiro na lista
                    head = current.next;
                } else {
                    // O nó a ser removido está no meio ou no final da lista
                    previous.next = current.next;
                }
                return;
            }

            previous = current;
            current = current.next;
        }

        System.out.println("Veículo não encontrado. Nada a remover.");
    }

    public void exibir() {
        VeiculoNode current = head;
        while (current != null) {
            System.out.println("Nome do Veículo: " + current.registroEntrada.veiculo().modelo());
            System.out.println("Placa: " + current.registroEntrada.veiculo().placa());
            System.out.println("Hora de Entrada: " + current.registroEntrada.horaEntrada());
            System.out.println("------------------");
            current = current.next;
        }
    }

    public Estaciona.RegistroEntrada pesquisarPorPlaca(String placa) {
        VeiculoNode current = head;
        while (current != null) {
            if (current.registroEntrada.veiculo().placa().equals(placa)) {
                return new Estaciona.RegistroEntrada(current.registroEntrada.veiculo(), current.registroEntrada.horaEntrada());
            }
            current = current.next;
        }
        return null;
    }
}
