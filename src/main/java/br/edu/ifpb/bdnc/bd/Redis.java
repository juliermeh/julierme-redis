/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.bd;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 *
 * @author Julierme Heinstein
 */
public class Redis {
    
    public Jedis novaConexao(){
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
        return pool.getResource();
    }
}
