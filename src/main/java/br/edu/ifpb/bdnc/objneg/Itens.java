/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.objneg;

import br.edu.ifpb.bdnc.bd.Postgres;
import br.edu.ifpb.bdnc.bd.Redis;
import br.edu.ifpb.bdnc.classes.Json;
import br.edu.ifpb.bdnc.classes.ItemPedido;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import redis.clients.jedis.Jedis;

/**
 *
 * @author Julierme Heinstein
 */
public class Itens {
    Redis conexaojedis;
    Postgres conexaogres;
    Json json;
    
    public void novoPedido(int id, int quantidade) throws SQLException{
        
        ItemPedido item = new ItemPedido(id,quantidade);
        String itemjson = json.novoJson(item);
        Jedis jedis = conexaojedis.novaConexao();
        String cod = "" + id;
        jedis.append(cod, itemjson);
        jedis.expire(cod, 5);
        jedis.close();
        
        
        String sql = "INSERT INTO ItemPedido(id,quantidade) VALUES ("
                + id + "," + quantidade + ")";
        Statement stat = conexaogres.novaConexao();
        stat.execute(sql);
        stat.close();
        
    }
    
    public void excluiPedido(int id) throws SQLException{
        try{
            Jedis jedis = conexaojedis.novaConexao();
            String cod = "" + id;
            if(jedis.exists(cod)){jedis.del(cod);}
            else{
                Statement stat = conexaogres.novaConexao();
                String sql = "DELETE FROM iTemPedido WHERE id = " + id;
                stat.execute(sql);
            }
        }
        catch (SQLException e){ }
    }
    
    public String buscarPedido(int id) throws SQLException{
        Jedis jedis = conexaojedis.novaConexao();
        String cod = "" + id;
        if(jedis.equals(cod)){
            String item = jedis.get(cod);
            return item;
        }
        else{
            String sql = "SELECT * FROM ItemPedido WHERE id = " + id;
            Statement stat = conexaogres.novaConexao();
            ResultSet rs = stat.executeQuery(sql);
            int iditem = rs.getInt(0);
            int quantidade = rs.getInt(1);
            String item = "id: " + iditem + " quantidade: " + quantidade;
            return item;
        }
    }
}
