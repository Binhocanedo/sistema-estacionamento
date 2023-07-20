import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Estacionamento estacionamento = new Estacionamento();

        int opcao;

        String menu = """
                1 - CADASTRAR VEÍCULO
                2 - VEÍCULOS ESTACIONADOS
                3 - SAÍDA DE VEÍCULO
                4 - SAIR
                OPÇÃO:""";

        do{
            System.out.print(menu);
            opcao = teclado.nextInt();
            switch (opcao){
                case 1 ->{
                    teclado.nextLine();
                    System.out.println("   DADOS DO VEÍCULO   ");
                    System.out.print("MODELO: ");
                    String modelo = teclado.nextLine().toUpperCase();
                    System.out.print("PLACA:  ");
                    String placa = teclado.nextLine().toUpperCase();
                    Veiculo veiculo = new Veiculo(modelo, placa);
                    estacionamento.registrarDeHoraEntrada();
                    System.out.print("HORA DE ENTRADA: " + estacionamento.registrarDeHoraEntrada());
                    System.out.println("\nVEICULO CADASTRADO COM SUCESSO !!!");
                    estacionamento.registrarEntrada(veiculo);
                    System.out.println("VAGAS DISPONIVEIS: " + estacionamento.vagasDisponiveis());
                }
                case 2 -> estacionamento.mostrarVeiculosEstacionados();
                case 3 -> estacionamento.removerVeiculo();
                case 4 -> System.out.println("OBRIGADO POR USAR NOSSO SOFTWARE...");
            }
        }while(opcao != 4);
    }
}