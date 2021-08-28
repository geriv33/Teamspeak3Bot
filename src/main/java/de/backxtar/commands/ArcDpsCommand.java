package de.backxtar.commands;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.managers.CommandInterface;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ArcDpsCommand implements CommandInterface {

    @Override
    public void run(String cmdValue, TS3Api api, TextMessageEvent event, Client client) {
        String webpage = "https://www.deltaconnected.com/arcdps/x64/";
        String[] data;
        String d3d9 = "", md5sum = "", d3d9Date = "", md5sumDate = "";

        try {
            String html = Jsoup.connect(webpage).get().html();
            Document document = Jsoup.parse(html);
            Elements elements = document.select("tbody");

            data = elements.get(0).text().split(" ");
            d3d9 = data[8];
            d3d9Date = data[9] + " " + data[10] + " " + data[11];
            md5sum = data[12];
            md5sumDate = data[13] + " " + data[14] + " " + data[15];
        } catch (Exception e) {
            e.printStackTrace();
        }
        api.sendPrivateMessage(client.getId(), "\n[color=red][b]Last modified[/b][/color]" +
                "\n[color=orange][b]" + d3d9 + ":[/b][/color] " + d3d9Date +
                "\n[color=orange][b]" + md5sum + ":[/b][/color] " + md5sumDate +
                "\n[color=orange][b]Download:[/b][/color] [URL=https://www.deltaconnected.com/arcdps/x64/]Index of X64[/URL]");

            /*
            <!doctype html>
<html>
 <head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title></title>
  <link rel="stylesheet" type="text/css" href="/style.css">
  <style>img[src$=".svg"] { width: 16px; }</style>
 </head>
 <body>
  <table id="indexlist">
   <tbody>
    <tr class="indexhead">
     <th class="indexcolicon"><img src="/icons/blank.gif" alt="[ICO]" width="16" height="16"></th>
     <th class="indexcolname"><a href="?C=N;O=D">Name</a></th>
     <th class="indexcollastmod"><a href="?C=M;O=A">Last modified</a></th>
     <th class="indexcolsize"><a href="?C=S;O=A">Size</a></th>
     <th class="indexcoldesc"><a href="?C=D;O=A">Description</a></th>
    </tr>
    <tr class="indexbreakrow">
     <th colspan="5">
      <hr></th>
    </tr>
    <tr class="even">
     <td class="indexcolicon"><img src="/icons/back.gif" alt="[PARENTDIR]" width="16" height="16"></td>
     <td class="indexcolname"><a href="/arcdps/">Parent Directory</a> </td>
     <td class="indexcollastmod">&nbsp;</td>
     <td class="indexcolsize"> - </td>
     <td class="indexcoldesc">&nbsp;</td>
    </tr>
    <tr class="odd">
     <td class="indexcolicon"><img src="/icons/unknown.gif" alt="[   ]" width="16" height="16"></td>
     <td class="indexcolname"><a href="d3d9.dll">d3d9.dll</a> </td>
     <td class="indexcollastmod">2021-08-17 18:28 </td>
     <td class="indexcolsize">1.0M</td>
     <td class="indexcoldesc">&nbsp;</td>
    </tr>
    <tr class="even">
     <td class="indexcolicon"><img src="/icons/unknown.gif" alt="[   ]" width="16" height="16"></td>
     <td class="indexcolname"><a href="d3d9.dll.md5sum">d3d9.dll.md5sum</a> </td>
     <td class="indexcollastmod">2021-08-17 18:32 </td>
     <td class="indexcolsize"> 43 </td>
     <td class="indexcoldesc">&nbsp;</td>
    </tr>
    <tr class="indexbreakrow">
     <th colspan="5">
      <hr></th>
    </tr>
   </tbody>
  </table>
  <script src="/script.js"></script>
 </body>
</html>
             */
    }
}
