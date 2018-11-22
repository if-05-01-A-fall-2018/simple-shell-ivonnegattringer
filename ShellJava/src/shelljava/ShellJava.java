/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shelljava;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivonne
 */
public class ShellJava {

    /**
     * @param args the command line arguments
     */
    private static Thread t;
    public static void main(String[] args) {
        Scanner scan;
        String input;
        
        while(true){
            System.out.print("Text: ");
            scan = new Scanner(System.in);
            input = scan.nextLine();
            String[] commands = input.split("&&");
            String output = "";
            for (String command : commands) {
                //output += UseCmd(Cmd[i]) ;
                if (command.contains("exit")) {
                    return;
                }
                CommandLine(command);
            }
            try {
                t.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(ShellJava.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private static void CommandLine(String arg){
        Runnable r = new RunCommand(arg);
        t = new Thread(r);
        t.start();        
    }
}

class RunCommand implements Runnable{
    private final String commandLine;
    public RunCommand(String commandLine) {
        this.commandLine = commandLine;
    }

    @Override
    public void run() {
        StringBuilder output = new StringBuilder();
        try {
            Process p =  Runtime.getRuntime().exec(this.commandLine);

            InputStream s = p.getInputStream();
            int b = s.read();
            while (b != -1) {
                System.out.print((char)b);
                b = s.read();
            }
        } catch (Exception e) {
            System.out.print(e.getMessage()+"\n");
        }
       
    }
    
}
