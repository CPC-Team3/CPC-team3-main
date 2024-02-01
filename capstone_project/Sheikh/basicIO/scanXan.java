package basicIO;
import java.io.*;
import java.lang.System.Logger;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
import static java.nio.file.StandardCopyOption.*;

public class scanXan {
    public static void main(String[] args) throws IOException {

        Scanner s = null;
        Path path = Paths.get("D:\\OneDrive\\FH Dortmund\\Sem1\\Scientific and Transversal Skills\\Compact Programming Course\\Capestone Project\\CPC-team3-main\\capstone_project\\Sheikh\\basicIO\\shoulddelete.txt");
        String path_s = "D:\\OneDrive\\FH Dortmund\\Sem1\\Scientific and Transversal Skills\\Compact Programming Course\\Capestone Project\\CPC-team3-main\\capstone_project\\Sheikh\\basicIO\\out.txt";
        Path path_out = Paths.get("D:\\OneDrive\\FH Dortmund\\Sem1\\Scientific and Transversal Skills\\Compact Programming Course\\Capestone Project\\CPC-team3-main\\capstone_project\\Sheikh\\basicIO\\out.txt");

        try {
            s = new Scanner(new BufferedReader(new FileReader(path_s)));

            while (s.hasNext()) {
                System.out.println(s.next());
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
        
        // delete
        if(true) {
        	try {
                Files.delete(path);
            } catch (NoSuchFileException x) {
                System.err.format("%s: no such" + " file or directory%n", path);
            } catch (DirectoryNotEmptyException x) {
                System.err.format("%s not empty%n", path);
            } catch (IOException x) {
                // File permission problems are caught here.
                System.err.println(x);
            }
        }
        
        // copy
        FileOutputStream out_copy = new FileOutputStream("D:\\OneDrive\\FH Dortmund\\Sem1\\Scientific and Transversal Skills\\Compact Programming Course\\Capestone Project\\CPC-team3-main\\capstone_project\\Sheikh\\basicIO\\out_copy.txt");
        Files.copy(path_out, out_copy);
        System.out.println(Files.probeContentType(path_out));
        System.out.println(Files.isSymbolicLink(path_out));
        
        String fileRoot = "D:\\OneDrive\\FH Dortmund\\Sem1\\Scientific and Transversal Skills\\Compact Programming Course\\Capestone Project\\CPC-team3-main\\capstone_project\\Sheikh\\basicIO\\out_copy.txt";
        File file = new File(fileRoot);
        if (file.isFile()) {
            path = Paths.get(fileRoot);
            BasicFileAttributes attr;
            try {
                attr = Files.readAttributes(path, BasicFileAttributes.class);
                System.out.println("creationTime     = " + attr.creationTime());
                System.out.println("lastAccessTime   = " + attr.lastAccessTime());
                System.out.println("lastModifiedTime = " + attr.lastModifiedTime());

                System.out.println("isDirectory      = " + attr.isDirectory());
                System.out.println("isOther          = " + attr.isOther());
                System.out.println("isRegularFile    = " + attr.isRegularFile());
                System.out.println("isSymbolicLink   = " + attr.isSymbolicLink());
                System.out.println("size             = " + attr.size());
            } catch (IOException ex) {

            }
        }
        System.out.println(Files.readAttributes(path, "creationTime"));
        
        
        
        //move(Path, Path, CopyOption...)
        //write(Path, byte[], OpenOption...)
        
    }
}