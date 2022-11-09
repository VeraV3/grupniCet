import java.io.*;
import java.net.Socket;

public class KorisnickaNit extends Thread{
    Socket klijent;
    Serveric servercic;
    PrintWriter pw ;
    String ime;


    public KorisnickaNit(Socket klijent, Serveric servercic){
        this.klijent = klijent;
        this.servercic = servercic;
    }
    public void run() {
        try {
            System.out.println("Server pokrece korisnicku nit");
            pw = new PrintWriter(new OutputStreamWriter(klijent.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(klijent.getInputStream()));
            //System.out.println("Otvoreni su privnt Writer i BufeeredReader");
            this.ime = br.readLine();
            System.out.println("Procitano je ime  " + this.ime);
            this.posaljiPoruku("Trenutno kontektovani korisnici su: " + servercic.dajImena());
            this.servercic.dobrodoslica("Novi konektovani korisnik:"+this.ime, this);
            String porukaKorisnika;
            do{
                porukaKorisnika=br.readLine();
                if(porukaKorisnika==null)
                    break;
                this.servercic.dobrodoslica("["+this.ime+"]"+porukaKorisnika, this);
            }while(!(porukaKorisnika.equalsIgnoreCase("bye")));
            this.servercic.ukloni(this);
            this.klijent.close();
            servercic.dobrodoslica(this.ime+"je napustio cet\n", this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

      public void posaljiPoruku(String s){
        if(pw!=null){
            pw.println(s);
        }

      }

      public String getIme(){
        return this.ime;
      }
}
