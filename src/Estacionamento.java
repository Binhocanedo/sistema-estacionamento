
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Estacionamento {
    private int numeroDeVagas = 10;
    private final float valorPorHora = 2.5F;
    private final ArrayList<RegistroEntrada> registrosEntrada;

    public Estacionamento(){
        registrosEntrada = new ArrayList<>();

    }

    public void registrarEntrada(Veiculo veiculo){
        LocalTime horaEntrada1 = registrarDeHoraEntrada();
        RegistroEntrada registro = new RegistroEntrada(veiculo, horaEntrada1);
        registrosEntrada.add(registro);
    }

    public LocalTime registrarDeHoraEntrada(){
        var horaEntrada = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        return horaEntrada;
    }

    private static class RegistroEntrada{
        private Veiculo veiculo;
        private LocalTime horaEntrada;

        public RegistroEntrada(Veiculo veiculo, LocalTime horaEntrada){
            this.veiculo = veiculo;
            this.horaEntrada = horaEntrada;
        }
    }

    public void mostrarVeiculosEstacionados(){
        System.out.println("   VEÍCULOS ESTACIONADOS   ");
        for(RegistroEntrada carro : registrosEntrada){
            System.out.println("MODELO:          " + carro.veiculo.getModelo());
            System.out.println("PLACA:           " + carro.veiculo.getPlaca());
            System.out.println("HORA DE ENTRADA: " + carro.horaEntrada + "\n");
        }
    }

    public int vagasDisponiveis(){
        return numeroDeVagas--;
    }
}
