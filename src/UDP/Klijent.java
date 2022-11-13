package UDP;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Klijent {
    public static void main(String[] args) {
        Klijent klijent = new Klijent("localhost", Server.port_server);
        klijent.izvrsi();

    }

    private String hostname;
    private int port;
    private String ime;

    public Klijent (String hostname, int port){
        this.hostname = hostname;
        this.port = port;

    }

    public void izvrsi(){
        try{
         this.postaviIme();

          Socket soket = new Socket(this.hostname, this.port);

          Thread n2 = new NitZaPisanje(this.ime, soket);
          n2.start();
          Thread n1 = new NitZaCitanje(this.ime, soket);
          n1.start();

        } catch (UnknownHostException e) {
            System.out.println("Nesto nije u redu s hostom.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("problem u try bloku u klasi Klijent");
            e.printStackTrace();
        }
    }
    public void postaviIme(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Unesi svoje ime:");
            this.ime = sc.nextLine();
        } catch (Exception e) {
            System.out.println("Nije uspelo otvaranje skenera i postavljanje imena u klasi Klijent!");
            e.printStackTrace();
        }

    }

    public String dajIme(){
        System.out.println("Poziva se metod dajIme() i ime je " + this.ime);
        return this.ime;
    }

    @Override
    public String toString() {
        return "UDP.Klijent{" +
                "hostname='" + hostname + '\'' +
                ", port=" + port +
                ", ime='" + ime + '\'' +
                '}';
    }
}
