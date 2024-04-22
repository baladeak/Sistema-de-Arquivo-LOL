/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import sa.Interfaces.VisualizadorTexto;
import sa.Interfaces.Interface;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static sa.Interfaces.Interface.byteFinal;
import static sa.Interfaces.Interface.celulaFinal;
import static sa.Interfaces.Interface.inicioCelula;
import static sa.Interfaces.Interface.tamanhoDado;
//import static sa.SA.teclado;


/**
 *
 * @author SoDeusSalva
 */
public class ProgramaLista{
  
    Interface inter;
    int qtd;
    Programa primeiro, ultimo;
    VisualizadorTexto ct;

    //iniciando uma lista vazia
    public ProgramaLista(){
        this.qtd = 0;
        this.primeiro = null;
        this.ultimo = null;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Programa getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(Programa primeiro) {
        this.primeiro = primeiro;
    }

    public Programa getUltimo() {
        return ultimo;
    }

    public void setUltimo(Programa ultimo) {
        this.ultimo = ultimo;
    }
    

    
        public static void removeFim(ProgramaLista lista){
        Programa aux = lista.primeiro;
        Programa ant = lista.primeiro;
        
        if(lista.qtd==0){
            System.out.println("Lista vazia!!");
        }else{
            while(aux.proximo!= null){
                ant = aux;
                aux = aux.proximo;
            }
            
            ant.proximo = null;
            lista.ultimo = ant;
            lista.qtd--;
        }
        
    }
    
    public static void removeIni(ProgramaLista lista){
        
        if(lista.qtd==0){
            
            System.out.println("Lista vazia!!");
        }else{
           lista.primeiro = lista.primeiro.proximo;
           lista.qtd--;
           
        }
        
    }
    
public static void removeArqMeio(ProgramaLista lista, String nome)
{
        Programa aux = lista.primeiro;
        Programa ant = lista.primeiro;
        
        int i = 0;
    if(lista.qtd==0)
    {
        System.out.println("Lista vazia!");
    }
    else{
        while(aux.proximo!= null && i <= 0){
            
                if(aux.nomeArq.equals(nome)){
                      ant.proximo = aux.proximo;
                      i++;
                      lista.qtd--;
                      break;
                }
                else{
                ant = aux;
                aux = aux.proximo;
                
                    
                }
            }
        
    }
}
    
 /*  public static void pesquisar(ProgramaLista lista){
        try {
            
     
        
        System.out.println("Qual tamanho do programa que deseja achar?");
       Long numero = teclado.nextLong(System.in);
        


        Programa aux = lista.primeiro;
        
        while(aux!= null && aux.tamanho!= numero)
        {
            aux = aux.proximo;
        }
        
        if(aux==null)
        {
            System.out.println("Tamanho não encontrado!");
        }
        else{
            System.out.println("Elemento encontrado" + aux.tamanho);
            System.out.println(aux.nomeArq);
        }
    }catch (InputMismatchException e) {
            System.out.println("NÃO DIGITE O NUMERO COM PONTOS FLUTUANTES!");
           System.out.println("Qual tamanho do programa que deseja achar?");
//        long numero = teclado.nextLong();
            pesquisar(lista);
            
    }
        
}*/
        public String pesquisarNome(ProgramaLista lista, String nome, VisualizadorTexto ct, String extensao3) throws UnsupportedEncodingException{
        Programa aux = lista.primeiro;
        String textoDoArquivo;
        String soManda = "Nao Encontrado";
        while(aux!= null)
        {
            if(aux.nomeArq.equals(nome))
            {
               if(extensao3.equals(".txt") || extensao3.equals(".TXT")){
                   if(aux.arrayBytes != null){
              //         System.out.println("array de bytes");
                textoDoArquivo = new String(aux.arrayBytes, "UTF-8");
                   }else{
                       
                       textoDoArquivo = aux.texto;
                     //  System.out.println(textoDoArquivo+"3");
                   }
                  
                ct.txtMeta.setText("Tamanho : "+aux.tamanho);
                ct.txtMeta.setText(ct.txtMeta.getText()+"\nArquivo do tipo Texto");
                ct.txtMeta.setText(ct.txtMeta.getText()+"\nData de criação : "+aux.dataCriacao);
                ct.txtMeta.setText(ct.txtMeta.getText()+"\nDiretório : "+aux.diretorio);
                ct.txtMeta.setText(ct.txtMeta.getText()+"\nPosição Inicial : "+aux.inicioCelula);
                ct.txtMeta.setText(ct.txtMeta.getText()+"\nPosição Final da Celula : "+aux.celulaFinal);
                ct.txtMeta.setText(ct.txtMeta.getText()+"\nByte Final : "+aux.byteFinal);
                return textoDoArquivo;
               }
               else{
                JOptionPane.showMessageDialog(null, "Para visualizar o conteudo, somente arquivos .txt");   
                ct.txtMeta.setText("Tamanho : "+aux.tamanho);
                ct.txtMeta.setText(ct.txtMeta.getText()+"\nArquivo do tipo : "+extensao3);
                ct.txtMeta.setText(ct.txtMeta.getText()+"\nData de criação : "+aux.dataCriacao);
                ct.txtMeta.setText(ct.txtMeta.getText()+"\nDiretório : "+aux.diretorio);
                ct.txtMeta.setText(ct.txtMeta.getText()+"\nPosição Inicial : "+aux.inicioCelula);
                ct.txtMeta.setText(ct.txtMeta.getText()+"\nPosição Final da Celula : "+aux.celulaFinal);
                ct.txtMeta.setText(ct.txtMeta.getText()+"\nByte Final : "+aux.byteFinal);
                //   System.out.println("4");
                return "";
               }
            }
            aux = aux.proximo;
        }

        if(aux==null)
        {
          //  System.out.println("5");
           return soManda;
            //System.out.println("Nome não encontrado!");
        }
        
return soManda;
        }

        public static String verificaNome(ProgramaLista lista, String nome) throws UnsupportedEncodingException{
        Programa aux = lista.primeiro;
        String textoDoArquivo;
        String soManda = "Nao Encontrado";
        int i = 0;
        while(aux!= null)
        {
            if(aux.nomeArq.equals(nome))
            {
                i++;
                soManda = aux.nomeArq;
              }
            
            aux = aux.proximo;
        }
        if(i ==1)
            
        {
            return "";
        }
        return"("+(i-1)+")";

        }
        
        public static long getTamanho(ProgramaLista lista, String nome) throws UnsupportedEncodingException{
        Programa aux = lista.primeiro;
        String textoDoArquivo;
        String soManda = "Nao Encontrado";
        while(aux!= null)
        {
            if(aux.nomeArq.equals(nome))
            {
                   
                return aux.tamanho;
            }
            aux = aux.proximo;
        }

        if(aux==null)
        {
           return 0;
            //System.out.println("Nome não encontrado!");
        }
        
        
        
return 0;
        }

        public void getInicioCelula(ProgramaLista lista, String nome) throws UnsupportedEncodingException{
        Programa aux = lista.primeiro;
        String textoDoArquivo;
        String soManda = "Nao Encontrado";
        while(aux!= null)
        {
            if(aux.nomeArq.equals(nome))
            {
               // textoDoArquivo = new String(aux.arrayBytes, "UTF-8");
                Interface.inicioCelula = aux.inicioCelula;
                 Interface.celulaFinal = aux.celulaFinal;
              Interface.byteFinal =  aux.byteFinal;
                 Interface.tamanhoDado = aux.tamanho;
                 aux.isDeletado = true;
            }
            aux = aux.proximo;
        }

        if(aux==null)
        {

            //System.out.println("Nome não encontrado!");
        }

        }
        
        
        public byte[] extrairArq(ProgramaLista lista, String nome) throws UnsupportedEncodingException{
        Programa aux = lista.primeiro;
        String textoDoArquivo;
        String soManda = "Nao Encontrado";
        while(aux!= null)
        {
            if(aux.nomeArq.equals(nome))
            {
                
                return aux.arrayBytes;
            }
            aux = aux.proximo;
        }

        if(aux==null)
        {
            System.out.println(soManda);
           return null;
            //System.out.println("Nome não encontrado!");
        }
               else{
            
            return aux.arrayBytes;
                    
  
        }
        
       }
        

     
    public static void listar(ProgramaLista lista) throws IOException{
        int i = 1;
        Programa aux = lista.primeiro;
        
        if(aux ==  null){
            System.out.println("Lista vazia!!");
        }else{
        
        while(aux!=null)
        {
            System.out.println("Programa "+i);
            System.out.println("Nome :"+aux.nomeArq);
            System.out.println("Tamanho  : "+aux.tamanho);
             System.out.println("Diretório : "+aux.diretorio);
             System.out.println("Data crianção :"+ aux.dataCriacao);
             //Arquivo.Read(aux.diretorio);
                         aux = aux.proximo;
            i++;
            
        }
             

        }
    }
  
    public static void addPrograma(ProgramaLista lista, long tamanhobytes, String path,String datac, String nomeArq, long inicioCelula, long celulaFinal, long byteFinal, byte[] bytes){
            Programa novoPrograma = new Programa();
            Programa progAux = new Programa();
            
            
            if(lista.qtd == 0)
            {
            //   System.out.println("Informe o tamanho do programa: ");
             //  novoPrograma.tamanho = teclado.nextInt();
                novoPrograma.nomeArq = nomeArq;
               novoPrograma.tamanho = tamanhobytes;
               novoPrograma.diretorio = path;
               novoPrograma.dataCriacao = datac;
               novoPrograma.inicioCelula = inicioCelula;
               novoPrograma.celulaFinal = celulaFinal;
               novoPrograma.byteFinal = byteFinal;
               if(bytes != null){
               novoPrograma.arrayBytes = bytes;
               }else
               {
                   //Arquivo carregado não é do tipo texto então só ignora.
               }
                novoPrograma.proximo = null;
                
                lista.primeiro = novoPrograma;
                lista.ultimo = novoPrograma;
                lista.qtd++;
                
            }
            else            {
                
                progAux = lista.primeiro;
                
                while(progAux.proximo!= null)
                {
                    progAux = progAux.proximo;
                }
                
                //System.out.println("Informe o tamanho do prog(else) : ");
                //novoPrograma.tamanho = teclado.nextInt();
                novoPrograma.nomeArq = nomeArq;
                novoPrograma.tamanho = tamanhobytes;
                novoPrograma.diretorio = path;
                novoPrograma.dataCriacao = datac;
                novoPrograma.inicioCelula = inicioCelula;
               novoPrograma.celulaFinal = celulaFinal;
               novoPrograma.byteFinal = byteFinal;
                novoPrograma.arrayBytes = bytes;
                
                novoPrograma.proximo = null;
                
                progAux.proximo = novoPrograma;// 
                
                lista.ultimo = novoPrograma;
                lista.qtd++;
                        
            }
    }
        public void addProgramaArq(ProgramaLista lista, String CaminhoMeta,String CaminhoSA, BufferedReader lerArq, File file, RandomAccessFile raf) throws IOException{
            Programa novoPrograma = new Programa();
            Programa progAux = new Programa();
            
            
            System.out.println("Tamanho do sa"+raf.length());
        //     FileReader arq = new FileReader(CaminhoMeta);
  //         BufferedReader lerArq = new BufferedReader(arq);
            String linha="";
            String texto = "";
            

            try{
                
                   try{
                      linha = lerArq.readLine();
                      
                    long tamanhobytes = Long.parseLong(linha);
                    
                    String path = linha = lerArq.readLine();

                    String datac = linha = lerArq.readLine();

                     String nomeArq = linha = lerArq.readLine();
                     
                     linha = lerArq.readLine();
                     long inicioCelula = Long.parseLong(linha);
                     
                     linha = lerArq.readLine();
                     long byteFinal = Long.parseLong(linha);
                       
                     linha = lerArq.readLine();
                     long celulaFinal = Long.parseLong(linha);
                       System.out.println(nomeArq);
                       
                     String extensao2 = nomeArq.substring(nomeArq.lastIndexOf("."), nomeArq.length());
                     System.out.println(extensao2);
                     if(extensao2.equals(".txt") || extensao2.equals(".TXT")){
                     
                 texto = Interface.restauraArq(CaminhoSA, tamanhobytes, inicioCelula, byteFinal, celulaFinal);
                      System.out.println("2"+texto);
                     }
        byte[] bytes = new byte[((int)raf.length())];
                 
                inter.addFilho(nomeArq);
                
               
           
                  
            if(lista.qtd == 0)
            {  //     System.out.println("tamanho de bytes :"+ tamanhobytes);
           //       System.out.println("i:"+i);
           //     raf.read(bytes, 0, (int) tamanhobytes);
        //        i = (int) tamanhobytes;
                
            //   System.out.println("Informe o tamanho do programa: ");
             //  novoPrograma.tamanho = teclado.nextInt();
                novoPrograma.nomeArq = nomeArq;
               novoPrograma.tamanho = tamanhobytes;
               novoPrograma.diretorio = path;
               novoPrograma.dataCriacao = datac;
               
        
               
             novoPrograma.texto = texto;
               
               novoPrograma.inicioCelula = inicioCelula;
               novoPrograma.celulaFinal = celulaFinal;
               novoPrograma.byteFinal = byteFinal;
               // Arquivo.WriteAllBytes("C:\\Users\\CEITELABINFO\\Desktop\\2.jpg",bytes); 

                novoPrograma.proximo = null;
                
                lista.primeiro = novoPrograma;
                lista.ultimo = novoPrograma;
                lista.qtd++;
                if(lerArq!= null)
                {
                    addProgramaArq(lista, CaminhoMeta, CaminhoSA, lerArq, file, raf);
               }
                
            }
            else            {
                
                progAux = lista.primeiro;
                
                while(progAux.proximo!= null)
                {
                    progAux = progAux.proximo;
                }
                
                
              //       System.out.println("tamanho de bytes :"+ tamanhobytes);
               //   System.out.println("i:"+i);
                
             //   raf.read(bytes, i, (int) tamanhobytes);
                //i = (int) tamanhobytes;
                novoPrograma.nomeArq = nomeArq;
                novoPrograma.tamanho = tamanhobytes;
                novoPrograma.diretorio = path;
                novoPrograma.dataCriacao = datac;

    
             novoPrograma.texto = texto;
               
               novoPrograma.inicioCelula = inicioCelula;
               novoPrograma.celulaFinal = celulaFinal;
               novoPrograma.byteFinal = byteFinal;
                
                novoPrograma.proximo = null;
                
                progAux.proximo = novoPrograma;// 
                
                lista.ultimo = novoPrograma;
                lista.qtd++;
                      
                                if(lerArq!= null)
                {
                    addProgramaArq(lista, CaminhoMeta, CaminhoSA, lerArq, file, raf);
               }
            }
             }catch(NumberFormatException e){
                       System.out.println("Fim do arquivo");
                   }catch(IndexOutOfBoundsException ex2)
                   {
                       System.out.println("Tamanho excedido");
                   }
            }catch(NullPointerException ex)
            {
                
            }
    }
        
    
 
}
