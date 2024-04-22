/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa;

import sa.Interfaces.Interface;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.print.attribute.Size2DSyntax.MM;
import java.nio.file.Files;
import javax.swing.JOptionPane;
import static sa.Interfaces.Interface.byteFinal;
import static sa.Interfaces.Interface.celulaFinal;
import static sa.Interfaces.Interface.inicioCelula;

/**
 *
 * @author SoDeusSalva
 */
public class Arquivo  {

    private static void JOptionPane(String nullo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    Interface inter;
    Programa prg;
    ProgramaLista lista;
 
    public static String Read(String Caminho, ProgramaLista lista, byte[] bytes) throws FileNotFoundException, IOException{
        long tamanho;
        String diretorio = "";
        String datac = "";
        String nome = "";

            FileReader arq = new FileReader(Caminho);
           BufferedReader lerArq = new BufferedReader(arq);
            String linha="";
            
            int i=0;

                      linha = lerArq.readLine();
                      tamanho = Long.parseLong(linha);
                      System.out.println(""+tamanho);
                      diretorio = linha = lerArq.readLine();
                      System.out.println(diretorio);
                      datac = linha = lerArq.readLine();
                      System.out.println(datac);
                      nome = linha = lerArq.readLine();
                      System.out.println(nome);
                                          
//                linha =  lerArq.readLine();
//                  System.out.println(linha);
                  
                     
             
             
                      
                   lista.addPrograma(lista, tamanho, diretorio, datac, nome,
                           inicioCelula, celulaFinal, byteFinal, bytes );
                      

                             
                        
                        /*
			while (linha != null) {
				i++;
				System.out.println(linha);

				linha = lerArq.readLine(); 
			}
			System.out.println("valor de I - " + i);
	
			// TODO Auto-generated catch block
		
*/

         //       System.out.println("Lendo arquivo "+ Caminho);
          //     linha = lerArq.readLine();
               
  //           File file = new File(Caminho); // carrega o arquivo
//             byte[] bytes = Files.readAllBytes(file.toPath());
               
              /*
               if(file.getName().endsWith(".txt")){
                   System.out.println("Arquivo do tipo texto detectado"
                           + "\nNome: "+file.getName()+" \nTexto :");
               String textoDoArquivo = new String(bytes, "UTF-8");

               System.out.println(textoDoArquivo);
                   System.out.println("--------------------");3
              */
        return null;
}
           
              // Files.write

                //WriteAllBytes("C:\\users\\ceitelabinfo\\desktop\\chuva.jpg",bytes); // escrevew o arquivo
               /*        
                 while(linha!=null){//acharPulo = linha.toCharArray();
                   
                    for(int i = 0; i < linha.length();i++){
                        
                        if('\n' == acharPulo[i]){
                            Write("C:\\ChoraProPQD\\Temporario.txt", acharPulo[i]);
                        } 
                    }

                   Write("C:\\ChoraProPQD\\Temporario.bin", linha);
                   
                    //System.out.println(linha);
                    conteudo += linha+"\n";
                    linha = lerArq.readLine();
                    
                }
               
                arq.close();
                
                System.out.println("Arquivo lido.\n");
                
                
                return conteudo;
                
            } catch (IOException ex) {
                System.out.println("Erro: Não foi possível ler o arquivo!");
                return "";
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado!");
            return "";
        }
        */

    public static byte[] ReadAllBytes(String Caminho, ProgramaLista lista) throws IOException
    {
        
          File file = new File(Caminho); // carrega o arquivo
          byte[] bytes = Files.readAllBytes(file.toPath());
        return bytes;
        
    }
    
    public static boolean Write(String Caminho,String Texto){
        try {
            FileWriter arq = new FileWriter(Caminho,true);
            PrintWriter gravarArq = new PrintWriter(arq);
       //     gravarArq.println(Texto);
             gravarArq.append(Texto);
             gravarArq.flush();
       
            gravarArq.close();
            return true;
        }catch(IOException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static void WriteAllBytes(String Caminho,byte[] byteArray) throws IOException
    {
        
     	java.io.File files = new java.io.File(Caminho);
        try (FileOutputStream in = new FileOutputStream(files,true)) {
            System.out.println("tamanho array"+byteArray);
            in.write(byteArray);
            
            in.flush();
            in.close();
          //  Write(Caminho, "&&&\n");
        }catch(NullPointerException ex)
        {
            JOptionPane("nullo");   
        }
   
    }
    

   public static void WriteAllMetaDados(long tamanho, String diretorioArq, String datac, String nome, 
                                        String CaminhoMetadados,long inicioCelula, long celulaFinal,long byteFinal )
   {
       //O Write pula 4 linhas para inserir os metadados por enquanto.
        try {
		// O parametro é que indica se deve sobrescrever ou continua no
		// arquivo.
		FileWriter fw = new FileWriter(CaminhoMetadados, true);
		BufferedWriter conexao = new BufferedWriter(fw);
                
		conexao.write(""+tamanho);
		conexao.newLine();
                conexao.write(diretorioArq);
		conexao.newLine();
                conexao.write(datac);
		conexao.newLine();
                conexao.write(nome);
		conexao.newLine();
                conexao.write(""+inicioCelula);
		conexao.newLine();
                conexao.write(""+celulaFinal);
		conexao.newLine();   
                conexao.write(""+byteFinal);
		conexao.newLine();                   
                conexao.flush();
		conexao.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
       
       
   }

}   