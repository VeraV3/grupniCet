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
            //System.out.println("Otvoreni su privnt Writer i BufeeredReader");
            this.ime = br.readLine();
            System.out.println("Procitano je ime  " + this.ime);
            this.posaljiPoruku("Trenutno kontektovani korisnici su: " + server.dajImena());
           // this.servercic.dobrodoslica("Novi konektovani korisnik:"+this.ime, this);
            String porukaKorisnika;
            do{
                porukaKorisnika=br.readLine();
                if(porukaKorisnika==null)
                    break;
                //this.servercic.dobrodoslica("["+this.ime+"]"+porukaKorisnika, this);
            }while(!(porukaKorisnika.equalsIgnoreCase("bye")));
            this.server.ukloni(this);
            this.klijentSoket.close();
           // servercic.dobrodoslica(this.ime+"je napustio cet\n", this);
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
            System.err.println("U korisnickoj niti print writer je null");
        }

      }

      public String getIme(){
        return this.ime;
      }
}
