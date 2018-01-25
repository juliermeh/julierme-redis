/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.objneg;

import br.edu.ifpb.bdnc.bd.Postgres;
import br.edu.ifpb.bdnc.bd.Redis;
import br.edu.ifpb.bdnc.classes.Pedido;
import br.edu.ifpb.bdnc.classes.Json;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import redis.clients.jedis.Jedis;

/**
 *
 * @author Julierme Heinstein
 */
public class Pedidos {
    
    Redis conexaojedis;
    Postgres conexaogres;
    Json json;
    
    public void novoPedido(int codigo, String descricao, float preco) throws SQLException{
        
        Pedido pedido = new Pedido(codigo, descricao, preco);
        String pedidojson = json.novoJson(pedido);
        Jedis jedis = conexaojedis.novaConexao();
        String cod = "" + codigo;
        jedis.append(cod, pedidojson);
        jedis.expire(cod, 5);
        jedis.close();
        
        
        String sql = "INSERT INTO Pedido(codigo,descricao,preco) VALUES ("
                + codigo + "," + descricao + "," + preco + ")";
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
                String sql = "DELETE FROM Pedido WHERE codigo = " + codigo;
                stat.execute(sql);
            }
        }
        catch (SQLException e){ }
    }
    
    public String buscarPedido(int codigo) throws SQLException{
        Jedis jedis = conexaojedis.novaConexao();
        String cod = "" + codigo;
        if(jedis.equals(cod)){
            String pedido = jedis.get(cod);
            return pedido;
        }
        else{
            String sql = "SELECT * FROM Pedido WHERE codigo = " + cod;
            Statement stat = conexaogres.novaConexao();
            ResultSet rs = stat.executeQuery(sql);
            int codpedido = rs.getInt(0);
            String descricao = rs.getString(1);
            float preco = rs.getFloat(2);
            String pedido = "codigo: " + codpedido + " descricao: " + descricao + " preco: " + preco;
            return pedido;
        }
    }
    
}
