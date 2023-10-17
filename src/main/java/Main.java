import java.util.InputMismatchException;
import java.util.Scanner;
import br.com.fabio.estacionamento.gerenciamento.Estaciona;


public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Estaciona estacionamento = new Estaciona();

        int opcao;

        String menu = """
                \n1 - CADASTRAR VEÍCULO
                2 - VEÍCULOS ESTACIONADOS
                3 - SAÍDA DE VEÍCULO
                4 - CRIAR RELATORIO DIARIO
                5 - SAIR
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
                    case 1 -> estacionamento.adicionarVeiculo();
                    case 2 -> estacionamento.mostrarVeiculosEstacionados();
                    case 3 -> estacionamento.removerVeiculo();
                    case 4 -> estacionamento.criarRelatorio();
                    case 5 -> System.out.println("OBRIGADO POR USAR NOSSO SOFTWARE...");
                }
            } while (opcao != 5);
        } catch (InputMismatchException e) {
            System.out.print("ERRO: " + e.getMessage());
            System.out.println("Por favor, reinicie o sistema");
        }
    }
}