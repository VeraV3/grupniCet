package UDP;

import java.io.*;
import java.net.Socket;

public class KorisnickaNit extends Thread{
    Socket klijentSoket;
    Server server;
    PrintWriter pw ;
    String ime;


    public KorisnickaNit(Socket klijent, Server server){
        this.klijentSoket = klijent;
        this.server = server;
    }
    public void run() {
        try {

            this.pw = new PrintWriter(new OutputStreamWriter(klijentSoket.getOutputStream()), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(klijentSoket.getInputStream()));
            this.ime = br.readLine();
            System.out.println("Konektovan/na je:  " + this.ime);
            this.posaljiPoruku("Trenutno kontektovani korisnici su: " + server.dajImena());
            this.server.porukaSvima("Novi konektovani korisnik:"+this.ime, this);
            String porukaKorisnika = br.readLine();

            while(!(porukaKorisnika.equalsIgnoreCase("bye")) ){
                this.server.porukaSvima("["+this.ime+"]:" + porukaKorisnika, this);
                porukaKorisnika = br.readLine();//ovde je blokiralo kad treba da dobije bye
                if(porukaKorisnika==null) {
                    System.out.println(porukaKorisnika + " poruka korisnika je null ");
                    break;
                }
            }
            this.server.ukloni(this);
            this.klijentSoket.close();
            server.porukaSvima(this.ime+" je napustio\\la cet\n", this);
        } catch (IOException e) {
            System.out.println("U korisnickoj niti doslo je do izuzetka");
            e.printStackTrace();
        }
    }

      public void posaljiPoruku(String s){
        if(this.pw!=null){
            this.pw.println(s);
            pw.flush();
        }else {
            System.err.println("U korisnickoj niti PrintWriter je null");
        }

      }

      public String getIme(){
        return this.ime;
      }
}
