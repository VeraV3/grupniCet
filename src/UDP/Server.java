package UDP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Server {
    public static final int port_server = 12345;

    public static void main(String[] args) {
        Server server = new Server(port_server);
        server.izvrsi();
    }

    private int port;
    private Set<KorisnickaNit> skupKorisnika = new HashSet<>();


    public Server(int port)
    {
        this.port = port;

    }

    public void izvrsi(){
            try(ServerSocket serverSoket = new ServerSocket(this.port)){
                System.out.println("Slusa...");
                while(true){
                    Socket klijent = serverSoket.accept();
                    System.out.println("Konektovan na port  "+ klijent.getPort());
                    KorisnickaNit korisnikNit = new KorisnickaNit(klijent, this);
                    korisnikNit.start();
                    this.skupKorisnika.add(korisnikNit);
                    //System.out.println(this.dajImena());
                    //System.out.println("Servercic:Pokrenuta je korisnicka nit");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


  public List<String> dajImena(){
            return this.skupKorisnika.stream().map(KorisnickaNit::getIme).collect(Collectors.toList());
    }

    public void dobrodoslica(String s, KorisnickaNit korisnickaNit) {
            this.skupKorisnika.stream()
                    .filter(u->u!=korisnickaNit)
                    .forEach(u->u.posaljiPoruku(s));
    }

    public void ukloni(KorisnickaNit korisnickaNit) {
            String ime = korisnickaNit.getIme();
            this.skupKorisnika.remove(korisnickaNit);
            System.err.println(ime + " je napustio\\la cet!\n");
    }
}
