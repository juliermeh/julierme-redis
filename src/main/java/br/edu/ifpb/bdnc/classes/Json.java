/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.classes;

import com.google.gson.Gson;

/**
 *
 * @author Julierme Heinstein
 */
public class Json {
    
    public String novoJson(Object obj){
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        return json;
    }
}
