package br.com.fabio.estacionamento.estruturaDeDados;

import br.com.fabio.estacionamento.gerenciamento.Estaciona;

public class VeiculoNode {
    public Estaciona.RegistroEntrada registroEntrada;
    public VeiculoNode next;

    public VeiculoNode(Estaciona.RegistroEntrada registro) {
        this.registroEntrada = registro;
        this.next = null;
    }
}
