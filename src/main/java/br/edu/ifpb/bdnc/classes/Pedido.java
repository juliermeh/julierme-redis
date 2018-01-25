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
public class Pedido{
    private int codigo;
    private String descricao;
    private float preco;
    
    public Pedido(){ }
    
    public Pedido(int codigo, String descricao, float preco){
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
    }
    
    public void setCodigo(int codigo){this.codigo = codigo;}
    public int getCodigo(){return this.codigo;}
    public void setDescricao(String descricao){this.descricao = descricao;}
    public String getDescricao(){return this.descricao;}
    public void setPreco(float preco){this.preco = preco;}
    public float getPreco(){return this.preco;}
}
