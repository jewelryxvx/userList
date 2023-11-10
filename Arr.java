package Works;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class Arr {
    public static void main(String[] args) {
    String str = null;
    String str1 = null;
    try{
        URL url = new URL("https://yandex.by/");
        InputStream inputStream = url.openStream();
        byte[] buffer = inputStream.readAllBytes();
        str = new String(buffer);
    }catch (Exception e){
        e.printStackTrace();
    }
    try{
        URL url = new URL("https://yandex.by/pogoda/10274?utm_campaign=informer&utm_content=main_informer&utm_medium=web&utm_source=home");
        InputStream inputStream = url.openStream();
        byte[] buffer = inputStream.readAllBytes();
        str1 = new String(buffer);
    }catch (Exception e){
        e.printStackTrace();
    }
    int index = str.indexOf("<div class='informers3'><a aria-label=\"");
    int length ="<div class='informers3'><a aria-label=\"".length();
    int index2 = str.indexOf("\"",index+length);
    String Pogoda = str.substring(index+length,index2);
        System.out.println(Pogoda);
        int index3 = str1.indexOf("Вчера в это время:");
        int length1 = "Вчера в это время:".length();
        int index4 = str1.indexOf("<",index3+length1);
        String Pogoda2 = str1.substring(index3,index4);
        System.out.println(Pogoda2);
    }
}