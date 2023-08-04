package br.com.fabio.estacionamento.gerenciamento;

import java.time.LocalTime;

public interface Estacionamento {
    public abstract void      registrarEntrada(Veiculo veiculo);
    public abstract LocalTime registrarHoraAtual();
    public abstract void      mostrarVeiculosEstacionados();
    public abstract void      removerVeiculo();
    public abstract int       vagasDisponiveis();

}
