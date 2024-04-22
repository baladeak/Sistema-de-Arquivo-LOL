/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;

/**
 *
 * @author SoDeusSalva
 */
public class FileCreationTime {

    public FileCreationTime() {
        
        
    }
    public  String getCreationDetails(File file)
{
try{
Path p = Paths.get(file.getAbsolutePath());
BasicFileAttributes view
= Files.getFileAttributeView(p, BasicFileAttributeView.class)
.readAttributes();
FileTime fileTime=view.creationTime();
return (""+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((fileTime.toMillis())));
}
catch(IOException e){
e.printStackTrace();
}
return "";
}
    
}
