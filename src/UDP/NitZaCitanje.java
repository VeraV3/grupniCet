package UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class NitZaCitanje extends Thread{
    private String ime;
    private BufferedReader br;


    public NitZaCitanje(String ime, Socket soket) {
        this.ime = ime;
        //radiSystem.out.println("Postavljeno je ime u niti za citanje i ono je " + this.ime);
        try {
            this.br = new BufferedReader(new InputStreamReader(soket.getInputStream()));
            //radiSystem.out.println("otvoren je buffered reader");
        } catch (IOException e) {
            System.out.println("U niti za citanje nije uspelo otvaranje buffer reader-a");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
              //radi  System.out.println("uslo i while petlju" );
                //ne radi System.out.println("ovooooooooo"+this.br.readLine());
               String recenica = this.br.readLine();
                System.out.println("procitalo recenicu");

                if (recenica == null) {
                    System.out.println("Konekcija je prekinuta.\n");
                    return;
                }

                System.out.println("\r" + recenica);
                System.out.printf("\r[%s]:", this.ime);

            }
        }catch (IOException e){
            System.out.println("nije uspelo citanje buffer readera u niti za citanje");
        }
    }
}
