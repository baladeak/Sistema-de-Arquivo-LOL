        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa;

import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author SoDeusSalva
 */
public class Diretorio {
    public void criarDiretorioMacro(File diretorioAtual, String NomePasta) {
        try {
            File diretorio = new File(diretorioAtual+"//"+NomePasta);
            //diretorio.mkdir();
            diretorio.mkdir();    
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar o diretorio");
            System.out.println(ex);
        }
    }
    
}
