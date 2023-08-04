package br.com.fabio.estacionamento.testes;

import br.com.fabio.estacionamento.gerenciamento.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;



public class MainTest {
    @Test
    public void adicionarSoDezVeiculos(){
        Estaciona estaciona = new Estaciona();

        for(int i = 0; i < 10; i++){
            String modelo = "Modelo: " + i;
            String placa  = "PLC:    " + i;
            Veiculo veiculo = new Veiculo(modelo, placa);
            estaciona.registrarEntrada(veiculo);
        }

        int vagasDisponiveis = estaciona.vagasDisponiveis();
        //Após adicionar 10 veiculos, o espero é sair 0 vagas
        Assertions.assertEquals(0, vagasDisponiveis);
    }
    @Test
    public void naoAdicioarMaisQueDezVeiculos(){
        Estaciona estaciona = new Estaciona();

        for(int i = 0; i < 11; i++){
            String modelo = "Modelo: " + i;
            String placa  = "PLC:    " + i;
            Veiculo veiculo = new Veiculo(modelo, placa);
            estaciona.registrarEntrada(veiculo);
        }
        int vagasDisponiveis = estaciona.vagasDisponiveis();
        Assertions.assertEquals(0, vagasDisponiveis);
    }

}
