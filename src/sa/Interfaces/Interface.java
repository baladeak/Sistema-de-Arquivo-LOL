/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.Interfaces;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import sa.Arquivo;
import sa.Diretorio;
import sa.FileCreationTime;
import sa.Programa;
import sa.ProgramaLista;
import static sun.security.krb5.Confounder.bytes;


/**
 *
 * @author SoDeusSalva
 */
public final class Interface extends javax.swing.JFrame {

    /**
     * Creates new form Interface
     */
    int load = 0;
    public String nomeSA;
    public String pathSA;
    public String pathExSA;
    public String pathExMetadados;
    public String pathMetadados;
    RandomAccessFile saFixo;
    
   public static  long byteFinal;
   public static   long inicioCelula;
   public static   long celulaFinal;
    
    public static long inicio = 0;
    public static long finalarq = 0;
    public static long finaldado = 0;
    public static long tamanhoDado = 0;
    
    public static JTree tree;
    public static DefaultMutableTreeNode noPai;
    
    JTextArea caixaDeTexto = new JTextArea();
    Arquivo arq = new Arquivo();
    ProgramaLista lista = new ProgramaLista();
    
    Programa prog;
    
        
       
       Diretorio diretorio = new Diretorio();
       int teste = 0;
      
       
       
       
   // Diretorio diretorio = new Diretorio();
    public Interface() {
        initComponents();
        btnAddFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/imagens/0781.png")));
        btnExtractFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/imagens/083.png")));
        btnShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/imagens/092.png")));
//        btnAddDirectory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/imagens/folder.png")));
//        btnDesfragmentar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/imagens/068.png")));
URL url = this.getClass().getResource("/sa/imagens/LOLicone.png"); Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url); this.setIconImage(iconeTitulo);

        centralizarComponente();
        
        
    }
    
        public void centralizarComponente() { Dimension ds = Toolkit.getDefaultToolkit().getScreenSize(); Dimension dw = getSize(); setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2); }
    
        
    public static void criarArvore(String Caminho) 
    {
        noPai = new DefaultMutableTreeNode(Caminho);
        tree = new JTree(noPai);
        jScrollPane2.setViewportView(tree);
     //   repaint();
    }
    
    public static void addFilho(String no)
    {
        DefaultMutableTreeNode filho = new DefaultMutableTreeNode(no);
        noPai.add(filho);
        tree = new JTree(noPai);
        jScrollPane2.setViewportView(tree);
       // repaint();
    }
        public static void removeFilho(String no)
    {
        DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
        
        DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
        
        model.removeNodeFromParent(selectNode);
        
        model.reload();
    }
    
    public void addPai(String no)
    {
        DefaultMutableTreeNode filho = new DefaultMutableTreeNode(no);
        try{ ((DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent()).add(filho);
        }catch(NullPointerException ex)
        {
            addFilho(no);
        }
        repaint();
    }
    
   public void MostrarTexto()
   {
      tree.getName();
      
   }
   
   public void loadMap() throws IOException
   {
            
           JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecionar Arquivo");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo .lol", "lol","LOL");

        fileChooser.setFileFilter(filter);

        int retorno =  fileChooser.showOpenDialog(this);

        if (retorno == JFileChooser.APPROVE_OPTION){    
            
            criarArvore(fileChooser.getSelectedFile().getName());
            
            
         //   System.out.println((fileChooser.getCurrentDirectory()+"//Metadados.lol"));
           // int i = 0;
       
            
      //   byte[] bytes = Arquivo.ReadAllBytes((fileChooser.getSelectedFile().toString()), lista);
         
      //     Arquivo.Read(fileChooser.getCurrentDirectory()+"//Metadados.lol",
      //             lista,bytes);
         pathExSA = fileChooser.getSelectedFile().toString();
          RandomAccessFile raf = new RandomAccessFile(fileChooser.getSelectedFile().toString(), "rw");
            System.out.println(fileChooser.getSelectedFile().toString());
          FileReader arq = new FileReader(fileChooser.getCurrentDirectory()+"//Metadados.lol");
           BufferedReader lerArq = new BufferedReader(arq);
            File file = new File(fileChooser.getSelectedFile().toString()); // carrega o arquivo
       //    Files.readAllBytes(file.toPath());
       
           
           load++;
           lista.addProgramaArq(lista, fileChooser.getCurrentDirectory()+"//Metadados.lol",
                                               fileChooser.getSelectedFile().toString(),lerArq, file, raf);
           raf.close();
           
           
//          ProgramaLista.readPrograma((fileChooser.getSelectedFile().toString()), 
//                                        fileChooser.getCurrentDirectory()+"//Metadados.lol",lista);
           
        }
                    if (retorno == JFileChooser.CANCEL_OPTION){
            Entrada entrada = new Entrada();
            entrada.setVisible(true);
            this.dispose();
            
        }

   }
   
   public static void defrag(ProgramaLista lista){
       
        
   } 
   
    public static void restaura(RandomAccessFile raf, String CaminhoNovoArquivo, String nomeArq) throws FileNotFoundException, IOException{
        RandomAccessFile arqNovo = new RandomAccessFile(CaminhoNovoArquivo, "rw");
        
        String saida = "";
        long ponteiro = inicioCelula;
        raf.seek(inicioCelula);
        byte b;
        long i = 0;
        long j = 0;
        long getPointer = raf.getFilePointer();
        while(j < tamanhoDado )
        {
            if(getPointer!= celulaFinal)
            {
                System.out.println(getPointer);
                System.out.println(celulaFinal);
                raf.writeBoolean(true);
                for(i = 0; i < 4087; i++)
                {
                    b = raf.readByte();
                    saida = saida + (char)b;
                    arqNovo.writeByte(b);
                    j++;
                }
            }
            else
            {  
               // System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
                raf.writeBoolean(true);
                for(i = celulaFinal; i < byteFinal; i++){
                    b = raf.readByte();
                    saida = saida + (char)b;
                    arqNovo.writeByte(b);
                
                j++;  
            }
            }
            System.out.println("gpoin " + raf.getFilePointer());
            ponteiro = raf.readLong();
            System.out.println("P " + ponteiro);
            raf.seek(ponteiro);
            getPointer = raf.getFilePointer();
         }
        System.out.println(saida);
        arqNovo.close();
    }
        public static void restauraLoad(String CaminhoSA, String CaminhoNovoArquivo, String nomeArq,long tamanho, long inicioCell, long finalCell, long byteFinal2) throws FileNotFoundException, IOException{
      RandomAccessFile arqNovo = new RandomAccessFile(CaminhoNovoArquivo, "rw");
        RandomAccessFile raf = new RandomAccessFile(CaminhoSA, "rw");
        String saida = "";
        long ponteiro = inicioCell;
        raf.seek(inicioCell);
        byte b;
        long i = 0;
        long j = 0;
        long getPointer = raf.getFilePointer();
        while(j < tamanho )
        {
            if(getPointer!= finalCell)
            {
                System.out.println(getPointer);
                System.out.println(finalCell);
                raf.writeBoolean(true);
                for(i = 0; i < 4087; i++)
                {
                    b = raf.readByte();
                    saida = saida + (char)b;
                    arqNovo.writeByte(b);
                    j++;
                }
            }
            else
            {  
               // System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
                raf.writeBoolean(true);
                for(i = finalCell; i < byteFinal2; i++){
                    b = raf.readByte();
                    saida = saida + (char)b;
                    arqNovo.writeByte(b);
                
                j++;  
            }
            }
           // System.out.println("gpoin " + raf.getFilePointer());
            ponteiro = raf.readLong();
          //  System.out.println("P " + ponteiro);
            raf.seek(ponteiro);
            getPointer = raf.getFilePointer();
         }
    //    System.out.println(saida);
        arqNovo.close();
    }
    
    
        public static String restauraArq(String CaminhoSA, long tamanho, long inicioCell, long finalCell, long byteFinal2) throws FileNotFoundException, IOException{
        
            RandomAccessFile raf = new RandomAccessFile(CaminhoSA, "r");
            
          //  System.out.println("Tamanho sa Restaura "+raf.length());
        String saida = "";
        long ponteiro = inicioCell;
        raf.seek(inicioCell);
        byte b;
        long i = 0;
        long j = 0;
        long getPointer = raf.getFilePointer();
        while(j < tamanho )
        {
            if(getPointer!= finalCell)
            {
           //    System.out.println(getPointer);
            //    System.out.println(finalCell);
                raf.readBoolean();
                for(i = 0; i < 4087; i++)
                {
                    
                    b = raf.readByte();
                    saida = saida+(char)b;
                   // arqNovo.writeByte(b);
                    j++;
                }
            }
            else
            {  
               // System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
                raf.readBoolean();
                for(i = finalCell; i < byteFinal2; i++){
             //   arqNovo.writeByte(
                    b = raf.readByte();
                    saida = saida+(char)b;
                j++;  
            }
            }
   //         System.out.println("gpoin " + raf.getFilePointer());
            ponteiro = raf.readLong();
     //       System.out.println("P " + ponteiro);
            raf.seek(ponteiro);
            getPointer = raf.getFilePointer();
         }
     //       System.out.println("inicio"+ inicioCell);
      //      System.out.println("final"+ finalCell);
     //       System.out.println("byte"+byteFinal2);
     //       System.out.println("a"+saida);
            return saida;
    //    arqNovo.close();
    }
    
  
    public static long acharCelula(RandomAccessFile raf) throws IOException{
        long tam = raf.length();
        long i = 0;
        raf.seek(0);
        while(i < tam)
        {
            if(raf.readBoolean())
            {
                return i;
            }
            else
            {
                i += 4096;
                raf.seek(i);
            }   
        }
        return i;      
    }
   

   
   public static long gravarArq(String Caminho, RandomAccessFile sa) throws FileNotFoundException, IOException
   {
        RandomAccessFile dado = new RandomAccessFile(Caminho, "r");
        sa.seek(0);
        long tamanhoMaximo = 1073741824;
        long tamDado = dado.length();
        tamanhoDado = tamDado;
        long i = 0;
        long ponteiro = 0;
        if((tamDado + sa.length()< tamanhoMaximo )){
            long j = 0;
            ponteiro = acharCelula(sa);
            inicioCelula = ponteiro;
            long fimCelula = 0;
            while(i < tamDado){
                sa.seek(ponteiro);
                sa.writeBoolean(false);// ESTÁ VAGO? TRUE = VAGO  , FALSE = NÃO VAGO
                celulaFinal = ponteiro;
                fimCelula = ponteiro;
                for(j = 0; j < 4087; j++)
                {
                    if(i < tamDado)
                    {
                        sa.writeByte(dado.readByte());
                        i++;
                        fimCelula++;
                        byteFinal = fimCelula;
                    }
                    else
                    {
                        sa.writeBoolean(false);
                        fimCelula++;
                    }
                }
                sa.writeLong(ponteiro);           
                ponteiro = acharCelula(sa);
            //    System.out.println(" ULTIMA CELULA" + ponteiro);
                sa.seek(fimCelula+1);//tem que ser mais um nao lembro o pq, foi o sr que fez, se o sr n sabe, magina eu :C
                sa.writeLong(ponteiro);//bora gravar uns 3 pra ver noque dasssda em sexo
                
            }
        }else{
            JOptionPane.showConfirmDialog(null, "Disco cheio");
        }


        dado.close();


        return ponteiro;

    }
   
       
 
   
   
   
   
   
   
    
    public void saveMap() {
   // String sb = "TEST CONTENT";
    JFileChooser chooser = new JFileChooser();
    chooser.setCurrentDirectory(new File("/home/me/Documents"));
    int retrival = chooser.showSaveDialog(null);
    if (retrival == JFileChooser.APPROVE_OPTION) {
        try {
            pathExSA = chooser.getSelectedFile()+"//"
                    +chooser.getSelectedFile().getName()+".lol";
            
            diretorio.criarDiretorioMacro(chooser.getCurrentDirectory(), chooser.getSelectedFile().getName());
            FileWriter fw = new FileWriter(pathExSA);
            
         //   System.out.println(chooser.getSelectedFile());
            FileWriter fw2 = new FileWriter(chooser.getCurrentDirectory()+"//"
                                            +chooser.getSelectedFile().getName()+"//Metadados"+".lol");
           // fw.write(sb.toString());
        this.pathMetadados = (chooser.getCurrentDirectory()+"//"+chooser.getSelectedFile().getName()+"//Metadados"+".lol");
         
            criarArvore(chooser.getSelectedFile().getName());
            
            
            this.nomeSA = chooser.getSelectedFile().getName()+".lol";
            this.pathSA = chooser.getSelectedFile().toString();
            System.out.println("nomeSA : "+this.nomeSA);
            System.out.println("pathSA " +this.pathSA);
            System.out.println("path metda :"+this.pathMetadados );
            
   //         System.out.println("NOME SELECT FILE"+chooser.getSelectedFile().getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
            if (retrival == JFileChooser.CANCEL_OPTION){
            Entrada entrada = new Entrada();
            entrada.setVisible(true);
            this.dispose();
            
        }

}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        jPanel2 = new javax.swing.JPanel();
        txtFile = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        btnAddFile = new javax.swing.JLabel();
        btnExtractFile = new javax.swing.JLabel();
        btnShow = new javax.swing.JLabel();

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("File System");
        setBackground(new java.awt.Color(51, 51, 51));

        jButton2.setText("Lista Arquivos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFile, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(txtFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );

        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 207, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jLayeredPane1);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnAddFile.setBackground(new java.awt.Color(51, 51, 51));
        btnAddFile.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnAddFile.setForeground(new java.awt.Color(255, 255, 255));
        btnAddFile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAddFile.setText("Inserir Arquivo");
        btnAddFile.setToolTipText("");
        btnAddFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddFile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddFile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAddFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddFileMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddFileMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddFileMouseExited(evt);
            }
        });

        btnExtractFile.setBackground(new java.awt.Color(51, 51, 51));
        btnExtractFile.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnExtractFile.setForeground(new java.awt.Color(255, 255, 255));
        btnExtractFile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnExtractFile.setText("Extrair Arquivo");
        btnExtractFile.setToolTipText("");
        btnExtractFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExtractFile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExtractFile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExtractFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExtractFileMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExtractFileMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExtractFileMouseExited(evt);
            }
        });
        btnExtractFile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnExtractFileKeyTyped(evt);
            }
        });

        btnShow.setBackground(new java.awt.Color(51, 51, 51));
        btnShow.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnShow.setForeground(new java.awt.Color(255, 255, 255));
        btnShow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnShow.setText("Exibir Arquivo");
        btnShow.setToolTipText("");
        btnShow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnShow.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnShow.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnShowMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnShowMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnShowMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddFile, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExtractFile, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnShow, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnShow)
                    .addComponent(btnExtractFile)
                    .addComponent(btnAddFile))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("Sistema .lol");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // TODO add your handling code here:
            ProgramaLista.listar(lista);
        } catch (IOException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:
        
   //    lista.pesquisarNome(lista, selectNode.getUserObject().toString());
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void btnAddFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddFileMouseClicked
        // TODO add your handling code here:
        
        
        
        String datac;
       String texto = txtFile.getText();
        byte[] bytes = null;
       
      
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecionar Arquivo");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Texto", "txt","DOC");

        fileChooser.setFileFilter(filter);


        int retorno =  fileChooser.showOpenDialog(this);

        if (retorno == JFileChooser.APPROVE_OPTION){

           try {
               File file = fileChooser.getSelectedFile();
               txtFile.setText(file.getPath());
          //     pathExMetadados = file.getPath();
               long tamanhobytes = file.length();
               
            try {
                
        //        System.out.println(pathExMetadados);
            RandomAccessFile sa = new RandomAccessFile(pathExSA, "rw");
            this.saFixo = sa;
            
                
                finaldado = gravarArq(file.getPath(), sa);                
                        //    restaura(sa);            
               
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
       
               datac = new FileCreationTime().getCreationDetails(new File(file.getPath()));
               //Arquivo.Read(txtFile.getText());
               texto = fileChooser.getSelectedFile().getName();
               System.out.println("getPath"+fileChooser.getSelectedFile().getPath());
               
               File file2 = new File(fileChooser.getSelectedFile().getPath()); // carrega o arquivo
               
              
                       String extensao2 = texto.substring(texto.lastIndexOf("."), texto.length());
                       System.out.println("extensao"+extensao2);
             //  System.out.println(texto);
              if(extensao2.equals(".txt") || extensao2.equals(".TXT") ){
             bytes = Files.readAllBytes(file2.toPath());
              // String textoDoArquivo = new String(bytes, "UTF-8");

              }
             //  System.out.println(textoDoArquivo);
            
               ProgramaLista.addPrograma(lista, tamanhobytes, txtFile.getText(),datac,file.getName(), 
                                        inicioCelula, celulaFinal, byteFinal, bytes);
               
               addFilho(file.getName()+ProgramaLista.verificaNome(lista, file.getName()));
              // System.out.println(fileChooser.getSelectedFile().toString());
      //         Arquivo.Read(fileChooser.getSelectedFile().toString());
          //     Arquivo.WriteAllBytes(this.pathSA+"//"+this.nomeSA);
               Arquivo.WriteAllMetaDados(tamanhobytes, file.getPath(), datac ,file.getName(), this.pathMetadados,
                       inicioCelula, celulaFinal, byteFinal);
            //   Arquivo.WriteAllBytes2(this.pathMetadados, bytes);
               
            
               //achar tamanho do arquivo
               
               // System.out.println("O tamanho do arquivo é: " + bytes +" bytes");
               
               //FIM ACHAR TAMANHO DO ARQUIVO
           } catch (IOException ex) {
               Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
           }
            
     
     
     
   
        }
        
        
        
    }//GEN-LAST:event_btnAddFileMouseClicked

    private void btnAddFileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddFileMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddFileMouseEntered

    private void btnAddFileMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddFileMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddFileMouseExited

    private void btnExtractFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExtractFileMouseClicked
        // TODO add your handling code here:
  

        if (tree.getSelectionPath() == null || tree.getSelectionPath().getLastPathComponent() == null) {
                            JOptionPane.showMessageDialog(null, "Selecione um arquivo na Arvore de Arquivos/Diretorios para mostra-lo.");
                            return;
                    }else{
      try{

        String teste = "";
       // lista.pesquisar(lista);

      
          teste = tree.getSelectionPath().getLastPathComponent().toString();
              //  byte[] bytes;
       // bytes = lista.extrairArq(lista, teste);
       
        lista.getInicioCelula(lista, teste);

          String extensao = teste.substring(teste.lastIndexOf("."), teste.length());
       //    System.out.println(teste);
    
           
            
            
          
          JFileChooser chooser = new JFileChooser();
    chooser.setCurrentDirectory(new File("/home/me/Documents"));

    int retrival = chooser.showSaveDialog(null);

    if (retrival == JFileChooser.APPROVE_OPTION) {
        try {
            
              long tamanho = ProgramaLista.getTamanho(lista, tree.getSelectionPath().getLastPathComponent().toString());
            
           // if(chooser.getFileFilter()== )
            
 //          Arquivo.WriteAllBytes(chooser.getSelectedFile()+extensao, bytes);
          //  Arquivo.WriteAllBytes(, bytes);
                    if(load==0){
                 restaura(saFixo, chooser.getSelectedFile()+extensao, chooser.getSelectedFile().getName());
                    }
                    else{
                        lista.getInicioCelula(lista,tree.getSelectionPath().getLastPathComponent().toString());
                        restauraLoad(pathExSA, chooser.getSelectedFile()+extensao, chooser.getSelectedFile().getName(),tamanho,inicioCelula,celulaFinal,byteFinal);
                    }
              ProgramaLista.removeArqMeio(lista, teste);
              removeFilho(teste);
            
       //     System.out.println(chooser.getSelectedFile());
            
          
         

            
            
   //         System.out.println("NOME SELECT FILE"+chooser.getSelectedFile().getName());
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }    //NO WRITEALLBYTES
               //FALTA CRIAR A TELA DE SALVAR O ARQUIVO E DEPOIS PEGAR A EXTENSAO DELE E CRIAR 
            //NO WRITEALLBYTES

        }catch(UnsupportedOperationException  ex )
      {
          JOptionPane("Selecione um arquivo na ARVORE");

            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        
    }//GEN-LAST:event_btnExtractFileMouseClicked
    }
    private void btnExtractFileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExtractFileMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExtractFileMouseEntered

    private void btnExtractFileMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExtractFileMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExtractFileMouseExited

    private void btnShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnShowMouseClicked
        // TODO add your handling code here:
        int i = 0;
                          VisualizadorTexto ct = new VisualizadorTexto();
        if(i >= 1)
        {
           // ct.dispose();
        }
        else{
        if (tree.getSelectionPath() == null || tree.getSelectionPath().getLastPathComponent() == null) {
                            JOptionPane.showMessageDialog(null, "Selecione um arquivo na Arvore de Arquivos/Diretorios para mostra-lo.");
                            return;
                    }else{
      try{
          i++;

        String teste = "";
       // lista.pesquisar(lista);
     
      
          teste = tree.getSelectionPath().getLastPathComponent().toString();
           System.out.println(teste);

    
        String extensao3 = teste.substring(teste.lastIndexOf("."), teste.length());
              //     System.out.println(extensao3);
                   
           
                
            ct.setVisible(true);
            ct.setTitle(tree.getSelectionPath().getLastPathComponent().toString());
            teste = lista.pesquisarNome(lista, teste, ct, extensao3);
            //System.out.println("teste3.1"+teste);
             ct.jTextArea2.setText(teste);
           
         
       
            
        
      //  System.out.println("Variavel teste:"+teste);
       
           
              //  ct.dispose();
               //  JOptionPane.showMessageDialog(null, "Para visualizar o conteudo, somente arquivos .txt");
            
      }catch(UnsupportedOperationException ex3){
                          ct.dispose();
                 JOptionPane("Por favor, selecione arquivos do tipo texto.");
          
      }   catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
    }//GEN-LAST:event_btnShowMouseClicked

    private void btnShowMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnShowMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnShowMouseEntered

    private void btnShowMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnShowMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnShowMouseExited

    private void btnExtractFileKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnExtractFileKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExtractFileKeyTyped

        /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws UnsupportedEncodingException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAddFile;
    private javax.swing.JLabel btnExtractFile;
    private javax.swing.JLabel btnShow;
    private javax.swing.JButton jButton2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JScrollPane jScrollPane2;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.MenuBar menuBar1;
    public javax.swing.JTextField txtFile;
    // End of variables declaration//GEN-END:variables

    private void JOptionPane(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

    
