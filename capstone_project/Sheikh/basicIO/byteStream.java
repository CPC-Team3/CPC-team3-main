package basicIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class byteStream {
    public static void main(String[] args) throws IOException {

        FileInputStream in = null;
        FileOutputStream out = null;
        FileOutputStream out_2 = null;

        try {
            in = new FileInputStream("D:\\OneDrive\\FH Dortmund\\Sem1\\Scientific and Transversal Skills\\Compact Programming Course\\Capestone Project\\CPC-team3-main\\capstone_project\\Sheikh\\basicIO\\in.txt");
            out = new FileOutputStream("D:\\OneDrive\\FH Dortmund\\Sem1\\Scientific and Transversal Skills\\Compact Programming Course\\Capestone Project\\CPC-team3-main\\capstone_project\\Sheikh\\basicIO\\out.txt");
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            
            out_2 = new FileOutputStream("D:\\OneDrive\\FH Dortmund\\Sem1\\Scientific and Transversal Skills\\Compact Programming Course\\Capestone Project\\CPC-team3-main\\capstone_project\\Sheikh\\basicIO\\outstring.txt");
            String message= "Hi from eclipse";
            for(int i = 0; i< message.length();i++ ) {
                out_2.write(message.charAt(i));
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (out_2 != null) {
            	out_2.close();
            }
        }
    }
}