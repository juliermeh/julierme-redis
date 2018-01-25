/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.classes;

import java.time.LocalDate;

/**
 *
 * @author Julierme Heinstein
 */
public class Produto {
    
    private int codigo;
    private LocalDate data;
    
    public Produto(){  }
    
    public Produto(int codigo, LocalDate data){
        this.codigo = codigo;
        this.data = data;
    }
    
    public void setCodigo(int codigo){this.codigo = codigo;}
    public int getCodigo(){return this.codigo;}
    public void setData(LocalDate data){this.data = data;}
    public LocalDate getData(){return this.data;}
    
}
