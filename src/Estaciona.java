

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Estaciona implements Estacionamento{
    private int   numeroDeVagas = 10;
    private final float valorPorHora = 2.5F;
    private final ArrayList<RegistroEntrada> registrosEntrada;


    public Estaciona(){
        registrosEntrada = new ArrayList<>();
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
        private record RegistroEntrada(Veiculo veiculo, LocalTime horaEntrada) {
    }
    @Override
    public void mostrarVeiculosEstacionados(){
        System.out.println("   VEÍCULOS ESTACIONADOS   ");
        for(RegistroEntrada carro : registrosEntrada){
            System.out.println("MODELO:          " + carro.veiculo.modelo());
            System.out.println("PLACA:           " + carro.veiculo.placa());
            System.out.println("HORA DE ENTRADA: " + carro.horaEntrada + "\n");
        }
    }
    @Override
    public void removerVeiculo(){
        Scanner teclado = new Scanner(System.in);
        System.out.print("Digite a placa do veículo: ");
        String placaPesquisa = teclado.nextLine().toUpperCase();

        boolean encontrado = false;

        HashMap<String, RegistroEntrada> removerVeiculos = new HashMap<>();
        LocalTime horaSaida = registrarHoraAtual();

        for(RegistroEntrada registro : registrosEntrada){
            if(registro.veiculo.placa().equals(placaPesquisa)){
                encontrado = true;
                System.out.println("VEÍCULO ENCONTRADO ");
                System.out.println("MODELO:            " + registro.veiculo.modelo());
                System.out.println("PLACA:             " + registro.veiculo.placa());
                System.out.println("HORA DE ENTRADA:   " + registro.horaEntrada);
                System.out.println("HORA DE SÁIDA:     " + horaSaida + "\n");

                System.out.print("Deseja remover este véiculo ? [SIM/NÃO]");
                String removerVeiculo = teclado.nextLine().toUpperCase();
                if(removerVeiculo.equals("SIM")){
                    removerVeiculos.put(registro.veiculo.placa(), registro);
                }
                break;
            }
        }
        if(!encontrado){
            System.out.println("VEÍCULO NÃO ENCONTRADO");
        }
        for(RegistroEntrada veiculoRemover : removerVeiculos.values()){
            registrosEntrada.remove(veiculoRemover);
        }
        this.numeroDeVagas ++;
        System.out.println("VEICULO REMOVIDO");
    }
    @Override
    public int vagasDisponiveis(){
        return numeroDeVagas;
    }

}
