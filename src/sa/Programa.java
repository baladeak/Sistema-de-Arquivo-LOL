/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa;

import java.io.File;

/**
 *
 * @author SoDeusSalva
 */
public class Programa {

    public int id;
    public String nomeArq = "";
    public long tamanho;
    public String diretorio = "";
    public String dataCriacao = "";
    public byte[] arrayBytes;
    public long inicioCelula;
    public long byteFinal;
    public long celulaFinal;
    public boolean isDeletado = false;
    public String texto = "";





    public long getByteFinal() {
        return byteFinal;
    }

    public void setByteFinal(long byteFinal) {
        this.byteFinal = byteFinal;
    }

    public long getCelulaFinal() {
        return celulaFinal;
    }

    public void setCelulaFinal(long celulaFinal) {
        this.celulaFinal = celulaFinal;
    }
    
  
    public Programa proximo;
    


     public void setNomeArq(String nomeArq) {
        
     this.nomeArq = nomeArq;
    }
    public String getNomeArq() {
        return nomeArq;
    }
    public long getTamanho() {
        return tamanho;
    }

    public void setTamanho(long tamanho) {
        this.tamanho = tamanho;
    }

    public Programa getProximo() {
        return proximo;
    }

    public void setProximo(Programa proximo) {
        this.proximo = proximo;
    }

    public byte[] getArrayBytes() {
        return arrayBytes;
    }

    public void setArrayBytes(byte[] arrayBytes) {
        this.arrayBytes = arrayBytes;
    }
    
    
    
}
