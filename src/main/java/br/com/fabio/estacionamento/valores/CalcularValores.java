package br.com.fabio.estacionamento.valores;
import br.com.fabio.estacionamento.gerenciamento.Estaciona;
import java.time.LocalTime;
public class CalcularValores{
    public double calcularValor(Estaciona.RegistroEntrada registroEntrada, LocalTime horaSaida) {

        LocalTime horaEntrada = registroEntrada.horaEntrada();


        int seconds = horaSaida.toSecondOfDay()  - horaEntrada.toSecondOfDay();

        double minutes = (double) seconds /60;
        double hours = minutes/60;

        double value = 0;
        if(hours <= 1) {
            if(minutes <= 15){
                return 3;
            } else if (minutes <= 30) {
                return 5;
            }
        } else {
            int fullHours = (int)hours;
            value = fullHours*7;
            if(fullHours < hours) {
                value = value + 3;
            }
            return value;
        }
        return value;
    }
}