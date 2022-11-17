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
        try {
            this.pw = new PrintWriter((socket.getOutputStream()), true);
        } catch (IOException e) {
            System.out.println("UDP.NitZaPisanje: problem u otvaranju PrintWritera!");
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        this.pw.println(this.ime);
        String recenica;
        try {
            Scanner sc = new Scanner(System.in);
            recenica = sc.nextLine();
            while(!recenica.equalsIgnoreCase("bye")){
                System.out.printf("\r[%s]:", this.ime);
                this.pw.println(recenica);
                recenica = sc.nextLine();
            }
            this.pw.println(recenica);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
