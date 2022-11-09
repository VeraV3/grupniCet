import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static int port = 1234;

    public static void main(String[] args) {


        try (ServerSocket serverSoket = new ServerSocket(port)) {

            while(true){
                Socket soket = serverSoket.accept();
                System.out.println("Prihvacen je klijent!");
                BufferedReader br = new BufferedReader(new InputStreamReader(soket.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(soket.getOutputStream()));
                String recenica = br.readLine();
                bw.write(recenica);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}  
