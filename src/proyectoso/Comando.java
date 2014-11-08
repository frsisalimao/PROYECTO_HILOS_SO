/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author fabricio
 */
public class Comando {

    public static String tiempoEncendio() {
        String line = null;
        try {
            Process p = Runtime.getRuntime().exec("uptime -p");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            line = in.readLine();
        } catch (IOException e) {
        }
        return line.substring(3);
    }

    public static String temperatura() {
        String line = null;
        String texto = "<html>";
        int lineas=0;
        try {
            Process p = Runtime.getRuntime().exec("sensors");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            line = in.readLine();
            while ((line = in.readLine()) != null) {
                if (lineas>5) {
                     texto = texto + line + "<br/>";
                }
                lineas++;
               
            }
        } catch (IOException e) {
        }
        return texto + "</html>";
    }

    public static String memoria() {
        String line = null;
        String texto = "<html>";
        int lineas=0;
        try {
            Process p = Runtime.getRuntime().exec("cat /proc/meminfo");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
         //   line = in.readLine();
            while (((line = in.readLine()) != null)&&(lineas<2)) {
                texto = texto + line + "<br/>";
                lineas++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return texto + "</html>";
    }
}
