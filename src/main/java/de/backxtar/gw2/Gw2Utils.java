package de.backxtar.gw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Gw2Utils {

    public static String getJson(String urlString) throws IOException {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(read(url)));
            StringBuilder stringBuilder = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                stringBuilder.append(chars, 0, read);
            }
            return stringBuilder.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    private static InputStream read(URL url) throws IOException {
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.addRequestProperty("User-Agent", "Mozilla/4.0");
        return httpCon.getInputStream();
    }

    public static String currency(int id, long amount) {
        //https://api.guildwars2.com/v2/currencies?ids=all
        String currency = "";

        switch (id) {
            case 1 : long[] coins = getCoins(amount);
                currency = "╰ [color=orange][b]Coins:[/b][/color] " + (coins[2] > 0 ? "[b]" + coins[2] + "[/b] Gold, " : "") +
                        (coins[1] > 0 ? "[b]" + coins[1] + "[/b] Silber, " : "") +
                        (coins[0] > 0 ? "[b]" + coins[0] + "[/b] Kupfer" : "");
                break;
            case 2 : currency = "╰ [color=orange][b]Karma:[/b][/color] " + amount;
                break;
            case 3 : currency = "╰ [color=orange][b]Lorbeeren:[/b][/color] " + amount;
                break;
            case 4 : currency = "╰ [color=orange][b]Edelsteine:[/b][/color] " + amount;
                break;
            case 5 : currency = "╰ [color=orange][b]Ascalonische Tränen:[/b][/color] " + amount;
                break;
            case 6 : currency = "╰ [color=orange][b]Scherben des Zhaitan:[/b][/color] " + amount;
                break;
            case 7 : currency = "╰ [color=orange][b]Fraktal-Relikte:[/b][/color] " + amount;
                break;
            case 9 : currency = "╰ [color=orange][b]Beetletuns Siegel:[/b][/color] " + amount;
                break;
            case 10 : currency = "╰ [color=orange][b]Manifeste des Maulwetariats:[/b][/color] " + amount;
                break;
            case 11 : currency = "╰ [color=orange][b]Tödliche Blüten:[/b][/color] " + amount;
                break;
            case 12 : currency = "╰ [color=orange][b]Symbole Kodas:[/b][/color] " + amount;
                break;
            case 13 : currency = "╰ [color=orange][b]Flammen-Legion-Charr-Schnitzereien:[/b][/color] " + amount;
                break;
            case 14 : currency = "╰ [color=orange][b]Wissenskristalle:[/b][/color] " + amount;
                break;
            case 15 : currency = "╰ [color=orange][b]Ehrenabzeichen:[/b][/color] " + amount;
                break;
            case 16 : currency = "╰ [color=orange][b]Gilden-Belobigungen:[/b][/color] " + amount;
                break;
            case 18 : currency = "╰ [color=orange][b]Transmutation-Ladungen:[/b][/color] " + amount;
                break;
            case 19 : currency = "╰ [color=orange][b]Luftschiff-Teile:[/b][/color] " + amount;
                break;
            case 20 : currency = "╰ [color=orange][b]Ley-Linien-Kristalle:[/b][/color] " + amount;
                break;
            case 22 : currency = "╰ [color=orange][b]Aurilliumklumpen:[/b][/color] " + amount;
                break;
            case 23 : currency = "╰ [color=orange][b]Geister-Scherben:[/b][/color] " + amount;
                break;
            case 24 : currency = "╰ [color=orange][b]Makelloses Fraktal-Relikte:[/b][/color] " + amount;
                break;
            case 25 : currency = "╰ [color=orange][b]Geoden:[/b][/color] " + amount;
                break;
            case 26 : currency = "╰ [color=orange][b]WvW-Gefecht-Tickets:[/b][/color] " + amount;
                break;
            case 27 : currency = "╰ [color=orange][b]Banditen-Wappen:[/b][/color] " + amount;
                break;
            case 28 : currency = "╰ [color=orange][b]Magnetitscherben:[/b][/color] " + amount;
                break;
            case 29 : currency = "╰ [color=orange][b]Versorger-Marken:[/b][/color] " + amount;
                break;
            case 30 : currency = "╰ [color=orange][b]PvP-Liga-Tickets:[/b][/color] " + amount;
                break;
            case 31 : currency = "╰ [color=orange][b]Beweise der Heldentaten:[/b][/color] " + amount;
                break;
            case 32 : currency = "╰ [color=orange][b]Aufgestiegene Scherben des Ruhms:[/b][/color] " + amount;
                break;
            case 33 : currency = "╰ [color=orange][b]Entfesselte Magie:[/b][/color] " + amount;
                break;
            case 34 : currency = "╰ [color=orange][b]Handelsverträge:[/b][/color] " + amount;
                break;
            case 35 : currency = "╰ [color=orange][b]Elegie-Mosaike:[/b][/color] " + amount;
                break;
            case 36 : currency = "╰ [color=orange][b]Zeugnisse von Heldentaten:[/b][/color] " + amount;
                break;
            case 37 : currency = "╰ [color=orange][b]Erhabene-Schlüssel:[/b][/color] " + amount;
                break;
            case 38 : currency = "╰ [color=orange][b]Macheten:[/b][/color] " + amount;
                break;
            case 39 : currency = "╰ [color=orange][b]Gaets-Kristalle:[/b][/color] " + amount;
                break;
            case 40 : currency = "╰ [color=orange][b]Banditen-Dietriche:[/b][/color] " + amount;
                break;
            case 41 : currency = "╰ [color=orange][b]Pakt-Brechstangen:[/b][/color] " + amount;
                break;
            case 42 : currency = "╰ [color=orange][b]Phiolen Chak-Säure:[/b][/color] " + amount;
                break;
            case 43 : currency = "╰ [color=orange][b]Zephyriten-Dietriche:[/b][/color] " + amount;
                break;
            case 44 : currency = "╰ [color=orange][b]Schlüssel des Händlers:[/b][/color] " + amount;
                break;
            case 45 : currency = "╰ [color=orange][b]Flüchtige Magie:[/b][/color] " + amount;
                break;
            case 46 : currency = "╰ [color=orange][b]PvP-Turnier-Gutscheine:[/b][/color] " + amount;
                break;
            case 47 : currency = "╰ [color=orange][b]Renn-Medaillen:[/b][/color] " + amount;
                break;
            case 49 : currency = "╰ [color=orange][b]Nebelgeborenere Schlüssel:[/b][/color] " + amount;
                break;
            case 50 : currency = "╰ [color=orange][b]Festmarken:[/b][/color] " + amount;
                break;
            case 51 : currency = "╰ [color=orange][b]Lager-Schlüssel:[/b][/color] " + amount;
                break;
            case 52 : currency = "╰ [color=orange][b]Rote Propheten-Scherben:[/b][/color] " + amount;
                break;
            case 53 : currency = "╰ [color=orange][b]Grüne Propheten-Scherben:[/b][/color] " + amount;
                break;
            case 54 : currency = "╰ [color=orange][b]Blauer Propheten-Kristalle:[/b][/color] " + amount;
                break;
            case 55 : currency = "╰ [color=orange][b]Grüner Propheten-Kristalle:[/b][/color] " + amount;
                break;
            case 56 : currency = "╰ [color=orange][b]Roter Propheten-Kristalle:[/b][/color] " + amount;
                break;
            case 57 : currency = "╰ [color=orange][b]Blaue Propheten-Scherben:[/b][/color] " + amount;
                break;
            case 58 : currency = "╰ [color=orange][b]Kriegs-Vorräte:[/b][/color] " + amount;
                break;
            case 59 : currency = "╰ [color=orange][b]Instabile Fraktal-Essenzen:[/b][/color] " + amount;
                break;
            case 60 : currency = "╰ [color=orange][b]Tyrianische Verteidigungssiegel:[/b][/color] " + amount;
                break;
        }
        return currency;
    }

    public static long[] getCoins(long amount) {
        long[] coins = new long[3];

        long copper = amount;
        long silver = copper / 100;
        long gold   = silver / 100;
        copper %= 100;
        silver %= 100;

        coins[0] = copper;
        coins[1] = silver;
        coins[2] = gold;
        return coins;
    }

    public static String formatRecs(String input) {
        String formatted = "";

        switch (input.substring(32)) {
            case "1" :
            case "19":
            case "28":
            case "52":
                formatted = input.substring(32) + " - Vulkanisch";
                break;
            case "2" :
            case "36":
            case "44":
            case "62":
                formatted = input.substring(32) + " - Nicht kategorisiert";
                break;
            case "3" :
            case "27":
            case "51":
            case "68":
                formatted = input.substring(32) + " - Schneeblind";
                break;
            case "4" :
            case "31":
            case "57":
            case "66":
                formatted = input.substring(32) + " - Urbanes Schlachtfeld";
                break;
            case "5" :
            case "17":
            case "32":
            case "56":
                formatted = input.substring(32) + " - Sumpfland";
                break;
            case "6" :
            case "21":
            case "47":
            case "69":
                formatted = input.substring(32) + " - Felswand";
                break;
            case "7" :
            case "26":
            case "61":
                formatted = input.substring(32) + " - Unterwasserruinen";
                break;
            case "8" :
            case "29":
            case "53":
                formatted = input.substring(32) + " - Untergrundeinrichtung";
                break;
            case "9" :
            case "22":
            case "39":
            case "58":
                formatted = input.substring(32) + " - Feuriger Hochofen";
                break;
            case "10":
            case "40":
            case "70":
                formatted = input.substring(32) + " - Feuriger Boss";
                break;
            case "11":
            case "33":
            case "67":
                formatted = input.substring(32) + " - Tiefenstein";
                break;
            case "12":
            case "37":
            case "54":
                formatted = input.substring(32) + " - Riff der Sirene";
                break;
            case "13":
            case "30":
            case "38":
            case "63":
                formatted = input.substring(32) + " - Chaos";
                break;
            case "14":
            case "46":
            case "65":
            case "71":
                formatted = input.substring(32) + " - Ätherklinge";
                break;
            case "15":
            case "34":
            case "43":
            case "55":
            case "64":
                formatted = input.substring(32) + " - Thaumanova-Reaktor";
                break;
            case "16":
            case "41":
            case "59":
                formatted = input.substring(32) + " - Zwielichtoase";
                break;
            case "18":
            case "42":
            case "72":
                formatted = input.substring(32) + " - Kapitän Mai Trin Boss";
                break;
            case "20":
            case "35":
            case "45":
            case "60":
                formatted = input.substring(32) + " - Solider Ozean";
                break;
            case "23":
            case "48":
            case "73":
                formatted = input.substring(32) + " - Albtraum";
                break;
            case "24":
            case "49":
            case "74":
                formatted = input.substring(32) + " - Zerschmettertes Observatorium";
                break;
            case "25":
            case "50":
            case "75":
                formatted = input.substring(32) + " - Sunqua-Gipfel";
                break;
            default :
        }
        return formatted;
    }

    public static String formatDaily(String input) {
        String formatted = "";

        switch (input.substring(13)) {
            case "Aquatic Ruins" :
                formatted = "76 - Unterwasserruinen";
                break;
            case "Swampland" :
                formatted = "89 - Sumpfland";
                break;
            case "Siren's Reef" :
                formatted = "78 - Riff der Sirene";
                break;
            case "Uncategorized" :
                formatted = "91 - Nicht kategorisiert";
                break;
            case "Solider Ozean" :
                formatted = "80 - Solider Ozean";
                break;
            case "Underground Facility" :
                formatted = "81 - Untergrundeinrichtung";
                break;
            case "Thaumanova Reactor" :
                formatted = "82 - Thaumanova-Reaktor";
                break;
            case "Molten Furnace" :
                formatted = "83 - Feuriger Hochofen";
                break;
            case "Deepstone" :
                formatted = "84 - Tiefenstein";
                break;
            case "Urban Battleground" :
                formatted = "85 - Urbanes Schlachtfeld";
                break;
            case "Snowblind" :
                formatted = "93 - Schneeblind";
                break;
            case "Twilight Oasis" :
                formatted = "87 - Zwielichtoase";
                break;
            case "Chaos" :
                formatted = "97 - Chaos";
                break;
            case "Molten Boss" :
                formatted = "90 - Feuriger Boss";
                break;
            case "Volcanic" :
                formatted = "92 - Vulkanisch";
                break;
            case "Cliffside" :
                formatted = "94 - Felswand";
                break;
            case "Captain Mai Trin Boss" :
                formatted = "95 - Kapitän Mai Trin Boss";
                break;
            case "Aetherblade" :
                formatted = "96 - Ätherklinge";
                break;
            case "Nightmare" :
                formatted = "98 - Albtraum";
                break;
            case "Shattered Observatory" :
                formatted = "99 - Zerschmettertes Observatorium";
                break;
            case "Sunqua Peak" :
                formatted = "100 - Sunqua-Gipfel";
                break;
            default:
        }
        return formatted;
    }

    public static String formatStrike(String input) {
        String formatted = "";

        switch (input) {
            case "boneskinner":
                formatted = "Knochenhäuter";
                break;
            case "fraenir_of_jormag":
                formatted = "Fraenir Jormags";
                break;
            case "icebrood_construct":
                formatted = "Zittergipfel-Pass";
                break;
            case "voice_of_the_fallen":
                formatted = "Stimme der Gefallenen und Klaue der Gefallenen";
                break;
            case "voice_in_the_frozen_deep":
                formatted = "Geflüster des Jormag";
                break;
            case "cold_war":
                formatted = "Kalter Krieg";
                break;
            default:
        }
        return formatted;
    }
}
