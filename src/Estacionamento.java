import java.time.LocalTime;

public interface Estacionamento {
    public void      registrarEntrada(Veiculo veiculo);
    public LocalTime registrarHoraAtual();
    public void      mostrarVeiculosEstacionados();
    public void      removerVeiculo();
    public int       vagasDisponiveis();

}
