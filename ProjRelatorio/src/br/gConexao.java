/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Anderson
 */
public class gConexao {

    private static Connection con;

    public static Connection getConexao() throws ExcRepositorio {
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/exemplorelatorio";
        String login = "postgres";
        String senha = "postgres";
        try {
            Class.forName(driver);//Método estático que carrega o driver
            //Abre a conexão com o bando de dados passado parametros necessários
            con = DriverManager.getConnection(url, login, senha);
        } catch (Exception e) {
            e.printStackTrace();
            //Caso não consiga se conectar ao banco exibe a mensagem
            System.out.println("Não foi possivel conectar ao bd!");
        }
        return con;
    }
}
