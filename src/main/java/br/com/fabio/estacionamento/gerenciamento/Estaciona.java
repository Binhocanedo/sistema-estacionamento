package br.com.fabio.estacionamento.gerenciamento;
import br.com.fabio.estacionamento.estruturaDeDados.VeiculoLinkedList;
import br.com.fabio.estacionamento.valores.CalcularValores;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

import static java.lang.System.in;
import static java.lang.System.out;


public class Estaciona implements Estacionamento {
    private int numeroDeVagas;
    //private final LinkedList<RegistroEntrada> registrosEntrada;
    private final VeiculoLinkedList registroEntrada;

    private double valorTotal;
    private int numerosDeCarros;


    public Estaciona() {
        this.numeroDeVagas = 10;
        //registrosEntrada = new LinkedList<>();
        registroEntrada = new VeiculoLinkedList();
    }

    public void adicionarVeiculo(){
        Scanner teclado = new Scanner(System.in);
        if(vagasDisponiveis() == 0){
            out.println("ESTACIONAMENTO LOTADO !!!");
        }else{
            out.println("   DADOS DO VEÍCULO   ");
            out.print("MODELO: ");
            String modelo = teclado.nextLine().toUpperCase();
            out.print("PLACA: ");
            String placa = teclado.nextLine().toUpperCase();
            Veiculo veiculo = new Veiculo(modelo, placa);
            out.print("HORA DE ENTRADA: " + registrarHoraAtual());
            registrarEntrada(veiculo);
            out.print("\nVAGAS DISPONIVEIS: " + vagasDisponiveis());
        }
    }
    @Override
    public void registrarEntrada(Veiculo veiculo) {
        LocalTime horaEntrada = registrarHoraAtual();
        RegistroEntrada registro = new RegistroEntrada(veiculo, horaEntrada);

        registroEntrada.adicionar(registro);
        numerosDeCarros ++;
        CalcularValores calcularValores = new CalcularValores();
        LocalTime horaSaida = registrarHoraAtual();

        double valor = calcularValores.calcularValor(registro, horaSaida);
        valorTotal += valor;

        FileWriter arq;
        try {
            arq = new FileWriter("C:\\Users\\Fábio\\Documents\\relatorio.txt", true);
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.println("----------------------------\n");
            gravarArq.print("      VEÍCULOS REGISTRADO     \n");
            gravarArq.print("MODELO: " + registro.veiculo.modelo()     + '\n');
            gravarArq.print("PLACA:  " + registro.veiculo.placa()      + '\n');
            gravarArq.print("HORA DE ENTRADA: " + registro.horaEntrada + '\n');
            gravarArq.print("VALOR R$: " + calcularValores.calcularValor(registro, horaSaida ) + "\n");
            arq.close();
        } catch (IOException e) {
            out.println("Acesso negado: " + e.getMessage());
        }
        this.numeroDeVagas--;
    }
    public void criarRelatorio(){
        FileWriter arq;
        try{
            arq = new FileWriter("C:\\Users\\Fábio\\Documents\\relatorio.txt", true);
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.println("-------------------------------\n");
            gravarArq.print("     RELATÓRIO FINANCEIRO        \n");
            gravarArq.print("TOTAL DE CARROS ESTACIONADOS: " + numerosDeCarros + '\n');
            gravarArq.print("VALOR TOTAL DE GANHO:         " + valorTotal + '\n');
            arq.close();
        }catch (IOException e) {
            out.println("Acesso negado: " + e.getMessage());
        }
        out.println("RELATORIO CRIADO COM SUCESSO");
    }


    @Override
    public LocalTime registrarHoraAtual() {
        return LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    //estrutura do ArrayList
    public record RegistroEntrada(Veiculo veiculo, LocalTime horaEntrada) {
    }

    @Override
    public void mostrarVeiculosEstacionados() {
        out.println("   VEÍCULOS ESTACIONADOS   ");
        verificarSeTemCarrosEstacionado();
        registroEntrada.exibir();


    }

    @Override
    public void removerVeiculo() {
        Scanner teclado = new Scanner(in);
        if (numeroDeVagas == 10) {
            out.println("NÃO HÁ CARROS ESTACIONADOS");
        } else {
            out.print("DIGITE A PLACA DO VEÍCULO: ");
            String placaPesquisa = teclado.nextLine().toUpperCase();

            RegistroEntrada registro = registroEntrada.pesquisarPorPlaca(placaPesquisa);

            if(registro != null){
                out.println("VEÍCULO ENCONTRADO ");
                out.println("MODELO:            " + registro.veiculo.modelo());
                out.println("PLACA:             " + registro.veiculo.placa());
                out.println("HORA DE ENTRADA:   " + registro.horaEntrada);

                LocalTime horaSaida = registrarHoraAtual();
                CalcularValores calcularValores = new CalcularValores();

                out.println("HORA DE SÁIDA:     " + horaSaida);
                out.println("TOTAL A PAGAR R$: " + calcularValores.calcularValor(registro, horaSaida) + "\n");

                out.print("Deseja remover este veículo? [SIM/NÃO]");
                String removerVeiculo = teclado.nextLine().toUpperCase();

                if (removerVeiculo.equals("SIM")) {
                    registroEntrada.remover(registro);
                    this.numeroDeVagas++;
                    out.println("VEÍCULO REMOVIDO");
                } else {
                    out.println("VEÍCULO NÃO FOI REMOVIDO");
                }
            }else{
                out.println("VEICULO NÃO ENCONTRADO");
            }
        }
    }

    @Override
    public int vagasDisponiveis() {
        return numeroDeVagas;
    }

    public void verificarSeTemCarrosEstacionado() {
        if (this.numeroDeVagas == 10) {
            out.println("NÃO HÁ CARROS ESTACIONADOS");
        }
    }

}
