/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.classes;

/**
 *
 * @author Julierme Heinstein
 */
public class ItemPedido {
    
    private int id;
    private int quantidade;
    
    public ItemPedido(){ }
    
    public ItemPedido(int id, int quantidade){
        this.id = id;
        this.quantidade = quantidade;
    }
    
    public void setID(int id){this.id = id;}
    public int getID(){return this.id;}
    public void setQuantidade(int quantidade){this.quantidade = quantidade;}
    public int getQuantidade(){return this.quantidade;}
    
}
