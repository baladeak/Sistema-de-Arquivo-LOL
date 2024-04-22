package sa;

import sa.Interfaces.Entrada;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author SoDeusSalva
 */
public class SA {
  //   public static Scanner teclado = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
       
        
    public static void main(String[] args) {
        // TODO code application logic here

       Entrada entrada = new Entrada();
        entrada.setVisible(true);
       JOptionPane.showMessageDialog(null, "Passei!");
   
      //  Interface inter = new Interface();
      //  inter.setVisible(true);
      /*
      ProgramaLista lista = new ProgramaLista();
     lista.qtd = 0;
    
      
        
        lista.listar(lista); // LISTAR A LISTA VAZIA
        
        lista.addPrograma(lista,11.0,"c:\\","10/10/2010");// ADICIONAR TAMANHO DO PROGRAMA NA LISTA
        lista.addPrograma(lista,15.0,"c:\\a","10/11/2011");// ADICIONAR TAMANHO DO PROGRAMA NA LISTA
        lista.addPrograma(lista,16.0,"c:\\oi","10/12/2012");// ADICIONAR TAMANHO DO PROGRAMA NA LISTA
        
        System.out.println("O primeiro elemento da lista é"+ lista.primeiro.tamanho);
        System.out.println("O ultimo elemento da lista é"+ lista.ultimo.tamanho);
        
        lista.listar(lista);// LISTAR A LISTA
        lista.pesquisar(lista);// PESQUISTAR NA LISTA
        
        lista.removeIni(lista);
        lista.listar(lista);
        lista.removeFim(lista);
        lista.listar(lista);
        System.out.println("\n");
        
 /*File file = new File("c:\\Users\\SoDeusSalva\\Desktop\\1.txt");
//ACHAR TAMANHO DO ARQUIVIO
  if (file.exists()) {

   double bytes = file.length();
   System.out.println("O tamanho do arquivo é: " + bytes +" bytes");

  } else {
   System.out.println("O arquivo não existe");
  }
  //FIM ACHAR TAMANHO DO ARQUIVO
  
  //INIO // EXIBIR UM CONJUNTO DE METADADOS
 System.out.println
(new FileCreationTime().getCreationDetails(new File("c:\\Users\\SoDeusSalva\\Desktop\\1.txt")));
  */

    }
    
    
}
