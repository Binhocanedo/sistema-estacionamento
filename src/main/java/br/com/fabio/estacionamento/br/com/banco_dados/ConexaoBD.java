package br.com.fabio.estacionamento.br.com.banco_dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    public static void main(String... x){
        String conexao =
                "jdbc:sqlserver://localhost.database.windows.net:1433;"
                        + "database=ESTACIONAMENTO;"
                        + "user=root;"
                        + "password=root;"
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";

        try{
            Connection connection = DriverManager.getConnection(conexao);
            System.out.println("Recuperei a conexação");

        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }
}
