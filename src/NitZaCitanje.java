import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.channels.SocketChannel;

public class NitZaCitanje extends Thread{
    String ime;
    BufferedReader br;


    public NitZaCitanje(String ime, Socket soket) {
        this.ime = ime;
       // System.out.println("Postavljeno je ime u niti za citanje i ono je " + this.ime);
        try {
            this.br = new BufferedReader(new InputStreamReader(soket.getInputStream()));
          //  System.out.println("otvoren je buffered reader");
        } catch (IOException e) {
            System.out.println("U niti za citanje nije uspelo otvaranje buffer readera");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
               // System.out.println("uslo i while petlju" );
               // System.out.println("ovooooooooo"+this.br.readLine());
               String recenica = this.br.readLine();
                System.out.println("procitalo recenicu");

                if (recenica == null) {
                    System.out.println("Konekcija je izgubljena.\n");
                    return;
                }

                System.out.println("\r" + recenica);
                System.out.printf("\r[%s]:", this.ime);

                //System.out.println(recenica);
            }
        }catch (IOException e){
            System.out.println("nije uspelo citanje i buffer readera u niti za citanje");
        }
    }
}
