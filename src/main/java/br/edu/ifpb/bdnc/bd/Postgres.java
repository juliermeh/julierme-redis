/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Julierme Heinstein
 */
public class Postgres{

    String url = "jdbc:postgresql://127.0.0.1:5432/atividade";
    String user = "postgres";
    String senha = "avantasia";

    public Statement novaConexao() throws SQLException{
        Connection conexao = DriverManager.getConnection(url, user, senha);
        return conexao.createStatement();
    }
}
