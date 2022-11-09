import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class NitZaPisanje extends Thread{
    String ime;
    PrintWriter pw;

    public NitZaPisanje(String ime, Socket socket){
        this.ime = ime;
        //System.out.println("U niti za pisanje postavljeno je ime i ono je " + this.ime);
        try {
            this.pw = new PrintWriter((socket.getOutputStream()), true);
        } catch (IOException e) {
            System.out.println("NitZaPisanje: problem u otvaranju PrintWritera!");
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
       // System.out.println("U metodu run() u NitiZaPisanje ispisujem ime ---> " + this.ime);
        this.pw.println(this.ime);
        //System.out.println("U metodu run() u NitiZaPisanje uspelo je ispisivanje imena u pw.");
        String recenica;
        try {
            Scanner sc = new Scanner(System.in);
            //System.out.println("NitZaPisanje:Skener je uspesno otvoren!");
            do{
                recenica = sc.nextLine();
                //System.out.println("Otvoren je skener i privatam ono sto je uneto ==> " + recenica);
                System.out.printf("\r[%s]:", this.ime);
                pw.println(recenica);
               // System.out.println("NitZaPisanje: u pw je poslata uneta recenica.");
                /*this.bw.write(recenica);
                this.bw.newLine();
                this.bw.flush();*/
            }while(!recenica.equalsIgnoreCase("bye"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
