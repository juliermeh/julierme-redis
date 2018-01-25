/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.objneg;

import br.edu.ifpb.bdnc.bd.Postgres;
import br.edu.ifpb.bdnc.bd.Redis;
import br.edu.ifpb.bdnc.classes.Json;
import br.edu.ifpb.bdnc.classes.Produto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import redis.clients.jedis.Jedis;
import java.time.LocalDate;

/**
 *
 * @author Julierme Heinstein
 */
public class Produtos {
    
    Redis conexaojedis;
    Postgres conexaogres;
    Json json;
    
    public void novoPedido(int codigo, LocalDate data) throws SQLException{
        
        Produto pedido = new Produto(codigo, data);
        String produtojson = json.novoJson(pedido);
        Jedis jedis = conexaojedis.novaConexao();
        String cod = "" + codigo;
        jedis.append(cod, produtojson);
        jedis.expire(cod, 5);
        jedis.close();
        
        
        String sql = "INSERT INTO Produto(codigo,data) VALUES ("
                + codigo + "," + data + ")";
        Statement stat = conexaogres.novaConexao();
        stat.execute(sql);
        stat.close();
        
    }
    
    public void excluiPedido(int codigo) throws SQLException{
        try{
            Jedis jedis = conexaojedis.novaConexao();
            String cod = "" + codigo;
            if(jedis.exists(cod)){jedis.del(cod);}
            else{
                Statement stat = conexaogres.novaConexao();
                String sql = "DELETE FROM Porduto WHERE codigo = " + codigo;
                stat.execute(sql);
            }
        }
        catch (SQLException e){ }
    }
    
    public String buscarPedido(int codigo) throws SQLException{
        Jedis jedis = conexaojedis.novaConexao();
        String cod = "" + codigo;
        if(jedis.equals(cod)){
            String produto = jedis.get(cod);
            return produto;
        }
        else{
            String sql = "SELECT * FROM Produto WHERE codigo = " + cod;
            Statement stat = conexaogres.novaConexao();
            ResultSet rs = stat.executeQuery(sql);
            int codproduto = rs.getInt(0);
            String data = rs.getString(1);
            String produto = "codigo: " + codproduto + " data: " + data;
            return produto;
        }
    }
}
