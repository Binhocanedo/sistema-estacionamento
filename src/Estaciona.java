

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static java.lang.System.*;


public class Estaciona implements Estacionamento{
    private int   numeroDeVagas;
    private final float valorPorHora = 2.5F;
    private final LinkedList<RegistroEntrada> registrosEntrada;


    public Estaciona(){
        this.numeroDeVagas = 10;
        registrosEntrada = new LinkedList<>();
    }


    @Override
    public void registrarEntrada(Veiculo veiculo){
        LocalTime horaEntrada = registrarHoraAtual();
        RegistroEntrada registro = new RegistroEntrada(veiculo, horaEntrada);
        registrosEntrada.add(registro);
        this.numeroDeVagas--;
    }


    @Override
    public LocalTime registrarHoraAtual(){
        return LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    //estrutura do ArrayList
    private record RegistroEntrada(Veiculo veiculo, LocalTime horaEntrada) {}
    @Override
    public void mostrarVeiculosEstacionados(){
        out.println("   VEÍCULOS ESTACIONADOS   ");
        for(RegistroEntrada carro : registrosEntrada){
            out.println("MODELO:          " + carro.veiculo.modelo());
            out.println("PLACA:           " + carro.veiculo.placa());
            out.println("HORA DE ENTRADA: " + carro.horaEntrada + "\n");
        }
    }
    @Override
    public void removerVeiculo(){
        Scanner teclado = new Scanner(in);
        out.print("Digite a placa do veículo: ");
        String placaPesquisa = teclado.nextLine().toUpperCase();

        boolean encontrado = false;

        HashMap<String, RegistroEntrada> removerVeiculos = new HashMap<>();
        LocalTime horaSaida = registrarHoraAtual();

        for(RegistroEntrada registro : registrosEntrada){
            if(registro.veiculo.placa().equals(placaPesquisa)){
                encontrado = true;
                out.println("VEÍCULO ENCONTRADO ");
                out.println("MODELO:            " + registro.veiculo.modelo());
                out.println("PLACA:             " + registro.veiculo.placa());
                out.println("HORA DE ENTRADA:   " + registro.horaEntrada);
                out.println("HORA DE SÁIDA:     " + horaSaida + "\n");

                out.print("Deseja remover este véiculo ? [SIM/NÃO]");
                var removerVeiculo = teclado.nextLine().toUpperCase();
                if(removerVeiculo.equals("SIM")){
                    removerVeiculos.put(registro.veiculo.placa(), registro);
                }
                break;
            }
        }
        if(!encontrado){
            out.println("VEÍCULO NÃO ENCONTRADO");
        }
        for(RegistroEntrada i : removerVeiculos.values()){
            registrosEntrada.remove(i);
        }
        this.numeroDeVagas ++;
        out.println("VEICULO REMOVIDO");
    }
    @Override
    public int vagasDisponiveis(){
        return numeroDeVagas;
    }

}
