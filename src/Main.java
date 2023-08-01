import java.util.InputMismatchException;
import java.util.Scanner;


import br.com.fabio.estacionamento.gerenciamento.Estaciona;
import br.com.fabio.estacionamento.gerenciamento.Veiculo;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Estaciona estacionamento = new Estaciona();

        int opcao;

        String menu = """
                \n1 - CADASTRAR VEÍCULO
                2 - VEÍCULOS ESTACIONADOS
                3 - SAÍDA DE VEÍCULO
                4 - SAIR
                OPÇÃO:""";


        try {
            do {
                System.out.print(menu);
                while(!teclado.hasNextInt()){
                    System.out.println("Por favor, digite um número válido.");
                    teclado.next();
                    System.out.print(menu);
                }
                opcao = teclado.nextInt();
                switch (opcao) {
                    case 1 -> {
                        teclado.nextLine();
                        System.out.println("   DADOS DO VEÍCULO   ");
                        System.out.print("MODELO: ");
                        String modelo = teclado.nextLine().toUpperCase();
                        System.out.print("PLACA:  ");
                        String placa = teclado.nextLine().toUpperCase();
                        Veiculo veiculo = new Veiculo(modelo, placa);
                        estacionamento.registrarHoraAtual();
                        System.out.print("HORA DE ENTRADA: " + estacionamento.registrarHoraAtual());
                        System.out.println("\nVEICULO CADASTRADO COM SUCESSO !!!");
                        estacionamento.registrarEntrada(veiculo);
                        System.out.println("VAGAS DISPONIVEIS: " + estacionamento.vagasDisponiveis());
                    }
                    case 2 -> estacionamento.mostrarVeiculosEstacionados();
                    case 3 -> estacionamento.removerVeiculo();
                    case 4 -> System.out.println("OBRIGADO POR USAR NOSSO SOFTWARE...");
                }
            } while (opcao != 4);
        } catch (InputMismatchException e) {
            System.out.print("ERRO: " + e.getMessage());
            System.out.println("Por favor, reinicie o sistema");
        }


    }
}