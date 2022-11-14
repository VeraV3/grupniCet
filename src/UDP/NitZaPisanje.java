package UDP;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class NitZaPisanje extends Thread{
    String ime;
    PrintWriter pw;

    public NitZaPisanje(String ime, Socket socket){
        this.ime = ime;
        // radi System.out.println("U niti za pisanje postavljeno je ime i ono je " + this.ime);
        try {
            this.pw = new PrintWriter((socket.getOutputStream()), true);
        } catch (IOException e) {
            System.out.println("UDP.NitZaPisanje: problem u otvaranju PrintWritera!");
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        //radi System.out.println("U metodu run() u NitiZaPisanje ispisujem ime ---> " + this.ime);
        this.pw.println(this.ime);
        //radi System.out.println("U metodu run() u NitiZaPisanje uspelo je ispisivanje imena u pw.");
        String recenica;
        try {
            Scanner sc = new Scanner(System.in);
            recenica = sc.nextLine();
            //radi System.out.println("UDP.NitZaPisanje:Skener je uspesno otvoren!");
            while(!recenica.equalsIgnoreCase("bye")){

                //radiSystem.out.println("Otvoren je skener i privatam ono sto je uneto ==> " + recenica);
                System.out.printf("\r[%s]:", this.ime);
                this.pw.println(recenica);
                recenica = sc.nextLine();
               //ovo mi ispisuje posle bye System.out.println("UDP.NitZaPisanje: u pw je poslata uneta recenica.");
                /*this.bw.write(recenica);
                this.bw.newLine();
                this.bw.flush();*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
