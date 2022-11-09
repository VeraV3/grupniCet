import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Klijent {
    public static void main(String[] args) {
        Klijent klijent = new Klijent("localhost", Serveric.port_server);
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



            /* Scanner sc = new Scanner(System.in))*/
      /*   //   System.out.println("Klijent je konektovan na server!\n");
           // System.out.println("Unesi svoje ime:");
            //this.ime  = sc.nextLine();
            //System.out.println(this.ime);*/


            Thread n2 = new NitZaPisanje(this.ime, soket);
            n2.start();
            Thread n1 = new NitZaCitanje(this.ime, soket);
            n1.start();

        } catch (UnknownHostException e) {
            System.out.println("Nesto nije u redu s hostom valjda");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void postaviIme(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Unesi svoje ime:");
            this.ime = sc.nextLine();
        } catch (Exception e) {
            System.out.println("Nije uspelo otvaranje skenera i postavljanje imena!");
            e.printStackTrace();
        }

    }

    public String dajIme(){
        System.out.println("Poziva se metod dajIme() i ime je " + this.ime);
        return this.ime;
    }

    @Override
    public String toString() {
        return "Klijent{" +
                "hostname='" + hostname + '\'' +
                ", port=" + port +
                ", ime='" + ime + '\'' +
                '}';
    }
}
