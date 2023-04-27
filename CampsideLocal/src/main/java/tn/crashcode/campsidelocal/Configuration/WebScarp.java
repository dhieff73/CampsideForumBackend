package tn.crashcode.campsidelocal.Configuration;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebScarp {

    public static void main(String[] args) {
        String GearsFile="CampingGears.txt";
        try {
            FileWriter GearWriter=new FileWriter(GearsFile);

            Document document= Jsoup.connect("https://backpackinglight.com/yama-mountain-gear-1p-cirriform-min-tarp-review/").get();
            for (Element e:document.select("li.bbp-body")){
                final String tricker=e.select("div.bbp-reply-content p").text();
                GearWriter.append(tricker);
                GearWriter.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(WebScarp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
