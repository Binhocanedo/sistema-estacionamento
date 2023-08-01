package br.com.fabio.estacionamento.gerenciamento;



import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;

import static java.lang.System.in;
import static java.lang.System.out;


public class Estaciona implements Estacionamento {
    private int numeroDeVagas;
    private final LinkedList<RegistroEntrada> registrosEntrada;


    public Estaciona() {
        this.numeroDeVagas = 10;
        registrosEntrada = new LinkedList<>();
    }


    @Override
    public void registrarEntrada(Veiculo veiculo) {
        LocalTime horaEntrada = registrarHoraAtual();
        RegistroEntrada registro = new RegistroEntrada(veiculo, horaEntrada);
        registrosEntrada.add(registro);

        FileWriter arq = null;
        try {
            arq = new FileWriter("C:\\Users\\Fábio\\Documents\\veiculos.txt", true);
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.println("-----------------------------\n");
            gravarArq.print("Modelo: " + registro.veiculo.modelo()     + '\n');
            gravarArq.print("Placa:  " + registro.veiculo.placa()      + '\n');
            gravarArq.print("Hora de Entrada: " + registro.horaEntrada + '\n');
            arq.close();
        } catch (IOException e) {
            out.println("Acesso negado: " + e.getMessage());
        }
        this.numeroDeVagas--;
    }


    @Override
    public LocalTime registrarHoraAtual() {
        return LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    //estrutura do ArrayList
    private record RegistroEntrada(Veiculo veiculo, LocalTime horaEntrada) {
    }

    @Override
    public void mostrarVeiculosEstacionados() {
        out.println("   VEÍCULOS ESTACIONADOS   ");
        verificarSeTemCarrosEstacionado();
        for (RegistroEntrada carro : registrosEntrada) {
            out.println("MODELO:          " + carro.veiculo.modelo());
            out.println("PLACA:           " + carro.veiculo.placa());
            out.println("HORA DE ENTRADA: " + carro.horaEntrada + "\n");
        }


    }

    @Override
    public void removerVeiculo() {
        Scanner teclado = new Scanner(in);
        if (numeroDeVagas == 10) {
            out.println("NÃO HÁ CARROS ESTACIONADOS");
        } else {
            out.print("Digite a placa do veículo: ");
            String placaPesquisa = teclado.nextLine().toUpperCase();


            HashMap<String, RegistroEntrada> removerVeiculos = new HashMap<>();
            LocalTime horaSaida = registrarHoraAtual();

            for (RegistroEntrada registro : registrosEntrada) {
                if (registro.veiculo.placa().equals(placaPesquisa)) {

                    out.println("VEÍCULO ENCONTRADO ");
                    out.println("MODELO:            " + registro.veiculo.modelo());
                    out.println("PLACA:             " + registro.veiculo.placa());
                    out.println("HORA DE ENTRADA:   " + registro.horaEntrada);
                    out.println("HORA DE SÁIDA:     " + horaSaida + "\n");

                    out.print("Deseja remover este véiculo ? [SIM/NÃO]");
                    var removerVeiculo = teclado.nextLine().toUpperCase();
                    if (removerVeiculo.equals("SIM")) {
                        removerVeiculos.put(registro.veiculo.placa(), registro);
                    }
                    break;
                }
            }
            for (RegistroEntrada i : removerVeiculos.values()) {
                registrosEntrada.remove(i);
            }
            this.numeroDeVagas++;
            out.println("VEICULO REMOVIDO");
        }

    }

    @Override
    public int vagasDisponiveis() {
        return numeroDeVagas;
    }

    public void verificarSeTemCarrosEstacionado() {
        if (numeroDeVagas == 10) {
            out.println("NÃO HÁ CARROS ESTACIONADOS");
        }
    }

}
