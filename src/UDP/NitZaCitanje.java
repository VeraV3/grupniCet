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
        try {
            this.br = new BufferedReader(new InputStreamReader(soket.getInputStream()));
        } catch (IOException e) {
            System.out.println("U niti za citanje nije uspelo otvaranje buffer reader-a");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
               String recenica = this.br.readLine();

                if (recenica == null) {
                    System.out.println("Konekcija je prekinuta.\n");
                    return;
                }

                System.out.println("\r" + recenica);
                System.out.printf("\r[%s]:", this.ime);
            }
        }catch (IOException e){
            System.out.println("nije uspelo citanje u niti za citanje");
        }
    }
}
