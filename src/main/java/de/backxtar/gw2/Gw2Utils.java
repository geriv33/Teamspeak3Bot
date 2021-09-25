package de.backxtar.gw2;

import de.backxtar.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Gw2Utils {
    private static final Config.Colors colors = Config.getColors();

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
        httpCon.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
        return httpCon.getInputStream();
    }

    public static String currency(int id, long amount) {
        //https://api.guildwars2.com/v2/currencies?ids=all
        String currency = "";

        switch (id) {
            case 1 : long[] coins = getCoins(amount);
                currency = "╰ [color=" + colors.mainColor + "][b]Coins:[/b][/color] " + (coins[2] > 0 ? "[b]" + coins[2] + "[/b] Gold, " : "") +
                        (coins[1] > 0 ? "[b]" + coins[1] + "[/b] Silber, " : "") +
                        (coins[0] > 0 ? "[b]" + coins[0] + "[/b] Kupfer" : "");
                break;
            case 2 : currency = "╰ [color=" + colors.mainColor + "][b]Karma:[/b][/color] " + amount;
                break;
            case 3 : currency = "╰ [color=" + colors.mainColor + "][b]Lorbeeren:[/b][/color] " + amount;
                break;
            case 4 : currency = "╰ [color=" + colors.mainColor + "][b]Edelsteine:[/b][/color] " + amount;
                break;
            case 5 : currency = "╰ [color=" + colors.mainColor + "][b]Ascalonische Tränen:[/b][/color] " + amount;
                break;
            case 6 : currency = "╰ [color=" + colors.mainColor + "][b]Scherben des Zhaitan:[/b][/color] " + amount;
                break;
            case 7 : currency = "╰ [color=" + colors.mainColor + "][b]Fraktal-Relikte:[/b][/color] " + amount;
                break;
            case 9 : currency = "╰ [color=" + colors.mainColor + "][b]Beetletuns Siegel:[/b][/color] " + amount;
                break;
            case 10 : currency = "╰ [color=" + colors.mainColor + "][b]Manifeste des Maulwetariats:[/b][/color] " + amount;
                break;
            case 11 : currency = "╰ [color=" + colors.mainColor + "][b]Tödliche Blüten:[/b][/color] " + amount;
                break;
            case 12 : currency = "╰ [color=" + colors.mainColor + "][b]Symbole Kodas:[/b][/color] " + amount;
                break;
            case 13 : currency = "╰ [color=" + colors.mainColor + "][b]Flammen-Legion-Charr-Schnitzereien:[/b][/color] " + amount;
                break;
            case 14 : currency = "╰ [color=" + colors.mainColor + "][b]Wissenskristalle:[/b][/color] " + amount;
                break;
            case 15 : currency = "╰ [color=" + colors.mainColor + "][b]Ehrenabzeichen:[/b][/color] " + amount;
                break;
            case 16 : currency = "╰ [color=" + colors.mainColor + "][b]Gilden-Belobigungen:[/b][/color] " + amount;
                break;
            case 18 : currency = "╰ [color=" + colors.mainColor + "][b]Transmutation-Ladungen:[/b][/color] " + amount;
                break;
            case 19 : currency = "╰ [color=" + colors.mainColor + "][b]Luftschiff-Teile:[/b][/color] " + amount;
                break;
            case 20 : currency = "╰ [color=" + colors.mainColor + "][b]Ley-Linien-Kristalle:[/b][/color] " + amount;
                break;
            case 22 : currency = "╰ [color=" + colors.mainColor + "][b]Aurilliumklumpen:[/b][/color] " + amount;
                break;
            case 23 : currency = "╰ [color=" + colors.mainColor + "][b]Geister-Scherben:[/b][/color] " + amount;
                break;
            case 24 : currency = "╰ [color=" + colors.mainColor + "][b]Makelloses Fraktal-Relikte:[/b][/color] " + amount;
                break;
            case 25 : currency = "╰ [color=" + colors.mainColor + "][b]Geoden:[/b][/color] " + amount;
                break;
            case 26 : currency = "╰ [color=" + colors.mainColor + "][b]WvW-Gefecht-Tickets:[/b][/color] " + amount;
                break;
            case 27 : currency = "╰ [color=" + colors.mainColor + "][b]Banditen-Wappen:[/b][/color] " + amount;
                break;
            case 28 : currency = "╰ [color=" + colors.mainColor + "][b]Magnetitscherben:[/b][/color] " + amount;
                break;
            case 29 : currency = "╰ [color=" + colors.mainColor + "][b]Versorger-Marken:[/b][/color] " + amount;
                break;
            case 30 : currency = "╰ [color=" + colors.mainColor + "][b]PvP-Liga-Tickets:[/b][/color] " + amount;
                break;
            case 31 : currency = "╰ [color=" + colors.mainColor + "][b]Beweise der Heldentaten:[/b][/color] " + amount;
                break;
            case 32 : currency = "╰ [color=" + colors.mainColor + "][b]Aufgestiegene Scherben des Ruhms:[/b][/color] " + amount;
                break;
            case 33 : currency = "╰ [color=" + colors.mainColor + "][b]Entfesselte Magie:[/b][/color] " + amount;
                break;
            case 34 : currency = "╰ [color=" + colors.mainColor + "][b]Handelsverträge:[/b][/color] " + amount;
                break;
            case 35 : currency = "╰ [color=" + colors.mainColor + "][b]Elegie-Mosaike:[/b][/color] " + amount;
                break;
            case 36 : currency = "╰ [color=" + colors.mainColor + "][b]Zeugnisse von Heldentaten:[/b][/color] " + amount;
                break;
            case 37 : currency = "╰ [color=" + colors.mainColor + "][b]Erhabene-Schlüssel:[/b][/color] " + amount;
                break;
            case 38 : currency = "╰ [color=" + colors.mainColor + "][b]Macheten:[/b][/color] " + amount;
                break;
            case 39 : currency = "╰ [color=" + colors.mainColor + "][b]Gaets-Kristalle:[/b][/color] " + amount;
                break;
            case 40 : currency = "╰ [color=" + colors.mainColor + "][b]Banditen-Dietriche:[/b][/color] " + amount;
                break;
            case 41 : currency = "╰ [color=" + colors.mainColor + "][b]Pakt-Brechstangen:[/b][/color] " + amount;
                break;
            case 42 : currency = "╰ [color=" + colors.mainColor + "][b]Phiolen Chak-Säure:[/b][/color] " + amount;
                break;
            case 43 : currency = "╰ [color=" + colors.mainColor + "][b]Zephyriten-Dietriche:[/b][/color] " + amount;
                break;
            case 44 : currency = "╰ [color=" + colors.mainColor + "][b]Schlüssel des Händlers:[/b][/color] " + amount;
                break;
            case 45 : currency = "╰ [color=" + colors.mainColor + "][b]Flüchtige Magie:[/b][/color] " + amount;
                break;
            case 46 : currency = "╰ [color=" + colors.mainColor + "][b]PvP-Turnier-Gutscheine:[/b][/color] " + amount;
                break;
            case 47 : currency = "╰ [color=" + colors.mainColor + "][b]Renn-Medaillen:[/b][/color] " + amount;
                break;
            case 49 : currency = "╰ [color=" + colors.mainColor + "][b]Nebelgeborenere Schlüssel:[/b][/color] " + amount;
                break;
            case 50 : currency = "╰ [color=" + colors.mainColor + "][b]Festmarken:[/b][/color] " + amount;
                break;
            case 51 : currency = "╰ [color=" + colors.mainColor + "][b]Lager-Schlüssel:[/b][/color] " + amount;
                break;
            case 52 : currency = "╰ [color=" + colors.mainColor + "][b]Rote Propheten-Scherben:[/b][/color] " + amount;
                break;
            case 53 : currency = "╰ [color=" + colors.mainColor + "][b]Grüne Propheten-Scherben:[/b][/color] " + amount;
                break;
            case 54 : currency = "╰ [color=" + colors.mainColor + "][b]Blauer Propheten-Kristalle:[/b][/color] " + amount;
                break;
            case 55 : currency = "╰ [color=" + colors.mainColor + "][b]Grüner Propheten-Kristalle:[/b][/color] " + amount;
                break;
            case 56 : currency = "╰ [color=" + colors.mainColor + "][b]Roter Propheten-Kristalle:[/b][/color] " + amount;
                break;
            case 57 : currency = "╰ [color=" + colors.mainColor + "][b]Blaue Propheten-Scherben:[/b][/color] " + amount;
                break;
            case 58 : currency = "╰ [color=" + colors.mainColor + "][b]Kriegs-Vorräte:[/b][/color] " + amount;
                break;
            case 59 : currency = "╰ [color=" + colors.mainColor + "][b]Instabile Fraktal-Essenzen:[/b][/color] " + amount;
                break;
            case 60 : currency = "╰ [color=" + colors.mainColor + "][b]Tyrianische Verteidigungssiegel:[/b][/color] " + amount;
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

    public static String formatRecFractals(String input) {
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

    public static String formatDailyFractals(String input) {
        String formatted;

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
            case "Solid Ocean" :
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
            default: formatted = input;
        }
        return formatted;
    }

    public static String formatDailyStrike(String input) {
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
            case "whisper_of_jormag":
                formatted = "Geflüster des Jormag";
                break;
            case "cold_war":
                formatted = "Kalter Krieg";
                break;
            default:
        }
        return formatted;
    }

    public static String formatDaily(String input) {
        String formatted;

        switch (input) {
            case "Daily Ascalonian Catacombs":
                formatted = "Tägliche Erforschung: Katakomben von Ascalon";
                break;
            case "Daily Caudecus's Manor":
                formatted = "Tägliche Erforschung: Caudecus' Anwesen";
                break;
            case "Daily Twilight Arbor":
                formatted = "Tägliche Erforschung: Zwielichtgarten";
                break;
            case "Daily Sorrow's Embrace":
                formatted = "Tägliche Erforschung: Umarmung der Betrübnis";
                break;
            case "Daily Citadel of Flame":
                formatted = "Tägliche Erforschung: Flammenzitadelle";
                break;
            case "Daily Honor of the Waves":
                formatted = "Tägliche Erforschung: Zierde der Wogen";
                break;
            case "Daily Crucible of Eternity":
                formatted = "Tägliche Erforschung: Schmelztiegel der Ewigkeit";
                break;
            case "Daily Ruined City of Arah":
                formatted = "Tägliche Erforschung: Ruinenstadt Arah";
                break;
            case "Daily Ascalon Forager":
                formatted = "Täglicher Erntearbeiter von Ascalon";
                break;
            case "Daily Ascalon Lumberer":
                formatted = "Täglicher Holzfäller von Ascalon";
                break;
            case "Daily Ascalon Miner":
                formatted = "Täglicher Bergarbeiter von Ascalon";
                break;
            case "Daily Kryta Forager":
                formatted = "Täglicher Erntearbeiter von Kryta";
                break;
            case "Daily Kryta Lumberer":
                formatted = "Täglicher Holzfäller von Kryta";
                break;
            case "Daily Kryta Miner":
                formatted = "Täglicher Bergarbeiter von Kryta";
                break;
            case "Daily Maguuma Jungle Forager":
                formatted = "Täglicher Erntearbeiter des Maguuma-Dschungels";
                break;
            case "Daily Maguuma Jungle Lumberer":
                formatted = "Täglicher Holzfäller des Maguuma-Dschungels";
                break;
            case "Daily Maguuma Jungle Miner":
                formatted = "Täglicher Bergarbeiter des Maguuma-Dschungels";
                break;
            case "Daily Maguuma Wastes Forager":
                formatted = "Täglicher Erntearbeiter der Maguuma-Einöde";
                break;
            case "Daily Maguuma Wastes Lumberer":
                formatted = "Täglicher Holzfäller der Maguuma-Einöde";
                break;
            case "Daily Maguuma Wastes Miner":
                formatted = "Täglicher Bergarbeiter der Maguuma-Einöde";
                break;
            case "Daily Orr Forager":
                formatted = "Täglicher Erntearbeiter von Orr";
                break;
            case "Daily Orr Lumberer":
                formatted = "Täglicher Holzfäller von Orr";
                break;
            case "Daily Orr Miner":
                formatted = "Täglicher Bergarbeiter von Orr";
                break;
            case "Daily Shiverpeaks Forager":
                formatted = "Täglicher Erntearbeiter der Zittergipfel";
                break;
            case "Daily Shiverpeaks Lumberer":
                formatted = "Täglicher Holzfäller der Zittergipfel";
                break;
            case "Daily Shiverpeaks Miner":
                formatted = "Täglicher Bergarbeiter der Zittergipfel";
                break;
            case "Daily Heart of Maguuma Forager":
                formatted = "Täglicher Sammler: Herz von Maguuma";
                break;
            case "Daily Heart of Maguuma Lumberer":
                formatted = "Täglicher Holzfäller: Herz von Maguuma";
                break;
            case "Daily Heart of Maguuma Miner":
                formatted = "Täglicher Bergarbeiter: Herz von Maguuma";
                break;
            case "Daily Desert Forager":
                formatted = "Täglicher Erntearbeiter der Wüste";
                break;
            case "Daily Desert Lumberer":
                formatted = "Täglicher Holzfäller der Wüste";
                break;
            case "Daily Desert Miner":
                formatted = "Täglicher Bergarbeiter der Wüste";
                break;
            // Renown Hearts
            case "Daily Elon Riverlands Taskmaster":
                formatted = "Täglicher Meister der Aufgaben der Elon-Flusslande";
                break;
            case "Daily Desolation Taskmaster":
                formatted = "Täglicher Meister der Aufgaben des Ödlandes";
                break;
            // World Bosses
            case "Daily Claw of Jormag":
                formatted = "Tägliche Klaue von Jormag";
                break;
            case "Daily Demolisher":
                formatted = "Täglicher Zerstörer";
                break;
            case "Daily Fire Elemental":
                formatted = "Täglicher Feuer-Elementar";
                break;
            case "Daily Frozen Maw":
                formatted = "Täglicher Gefrorener Schlund";
                break;
            case "Daily Great Jungle Wurm":
                formatted = "Täglicher Großer Dschungelwurm";
                break;
            case "Daily Hound Master":
                formatted = "Täglicher Meister der Hunde";
                break;
            case "Daily Inquest Golem Mark II":
                formatted = "Täglicher Inquestur-Golem Typ II";
                break;
            case "Daily Megadestroyer":
                formatted = "Täglicher Mega-Zerstörer";
                break;
            case "Daily Shadow Behemoth":
                formatted = "Täglicher Schatten-Behemoth";
                break;
            case "Daily Shatterer":
                formatted = "Täglicher Zerschmetterer";
                break;
            // Other
            case "Daily Activity Participation":
                formatted = "Täglicher Aktivitäts-Teilnehmer";
                break;
            case "Daily Mystic Forger":
                formatted = "Täglicher mystischer Schmied";
                break;
            // Adventures
            case "Daily Adventure: A Fungus Among Us":
                formatted = "Tägliches Abenteuer: Pöse Pilze!";
                break;
            case "Daily Adventure: Beetle Feast":
                formatted = "Tägliches Abenteuer: Käferschmaus";
                break;
            case "Daily Adventure: Bugs in the Branches":
                formatted = "Tägliches Abenteuer: Käfer im Geäst";
                break;
            case "Daily Adventure: Drone Race":
                formatted = "Tägliches Abenteuer: Drohnen-Rennen";
                break;
            case "Daily Adventure: Fallen Masks":
                formatted = "Tägliches Abenteuer: Gefallenen-Masken";
                break;
            case "Daily Adventure: Flying Circus":
                formatted = "Tägliches Abenteuer: Schauflug";
                break;
            case "Daily Adventure: Haywire Punch-o-Matic Battle":
                formatted = "Tägliches Abenteuer: Kampf als durchdrehender Haudrauf-o-Mat";
                break;
            case "Daily Adventure: On Wings of Gold":
                formatted = "Tägliches Abenteuer: Auf goldenen Schwingen";
                break;
            case "Daily Adventure: Salvage Pit":
                formatted = "Tägliches Abenteuer: Bergungsmaterial-Grube";
                break;
            case "Daily Adventure: Sanctum Scramble":
                formatted = "Tägliches Abenteuer: Rauferei am Refugium";
                break;
            case "Daily Adventure: Scrap Rifle Field Test":
                formatted = "Tägliches Abenteuer: Schrottgewehr-Feldversuch";
                break;
            case "Daily Adventure: Shooting Gallery":
                formatted = "Tägliches Abenteuer: Schießbude";
                break;
            case "Daily Adventure: Tendril Torchers":
                formatted = "Tägliches Abenteuer: Rankenbrenner";
                break;
            case "Daily Adventure: The Floor Is Lava?":
                formatted = "Tägliches Abenteuer: Der Boden besteht aus Lava?";
                break;
            case "Daily Adventure: The Ley-Line Run":
                formatted = "Tägliches Abenteuer: Das Ley-Linien-Rennen";
                break;
            case "Daily Desert Adventurer":
                formatted = "Täglicher Abenteurer der Wüste";
                break;
            //Vistas
            case "Daily Ascalon Vista Viewer":
                formatted = "Täglicher Panorama-Genießer von Ascalon";
                break;
            case "Daily Kryta Vista Viewer":
                formatted = "Täglicher Panorama-Genießer von Kryta";
                break;
            case "Daily Maguuma Vista Viewer":
                formatted = "Täglicher Panorama-Genießer von Maguuma";
                break;
            case "Daily Maguuma Wastes Vista Viewer":
                formatted = "Täglicher Panorama-Genießer der Maguuma-Einöde";
                break;
            case "Daily Orr Vista Viewer":
                formatted = "Täglicher Panorama-Genießer von Orr";
                break;
            case "Daily Shiverpeaks Vista Viewer":
                formatted = "Täglicher Panorama-Genießer der Zittergipfel";
                break;
            case "Daily Heart of Maguuma Vista Viewer":
                formatted = "Täglicher Panorama-Genießer: Herz von Maguuma";
                break;
            case "Daily Desert Vista Viewer":
                formatted = "Täglicher Panorama-Genießer der Wüste";
                break;
            // Jumping Puzzle
            case "Daily Antre of Adjournment Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Der Abgrund des Plünderers";
                break;
            case "Daily Behem Gauntlet Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Behem-Spießrutenlauf";
                break;
            case "Daily Branded Mine Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Die Gebrandmarkte Mine";
                break;
            case "Daily Buried Archives Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Verschüttete Archive";
                break;
            case "Daily Chaos Crystal Cavern Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Die Chaoskristallhöhle";
                break;
            case "Daily Coddler's Cove Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Knuddlerbucht";
                break;
            case "Daily Collapsed Observatory Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Das eingestürzte Observatorium";
                break;
            case "Daily Conundrum Cubed Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Zwickmühle hoch drei";
                break;
            case "Daily Crash Site Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Absturzstelle";
                break;
            case "Daily Craze's Folly Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Crazes Torheit";
                break;
            case "Daily Crimson Plateau Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Purpur-Plateau";
                break;
            case "Daily Dark Reverie Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Finsterer Tagtraum";
                break;
            case "Daily Demongrub Pits Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Dämonenraupen-Gruben";
                break;
            case "Daily Fawcett's Bounty Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Fawcetts Beute";
                break;
            case "Daily Goemm's Lab Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Goemms Labor";
                break;
            case "Daily Grendich Gamble Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Grendich-Spiel";
                break;
            case "Daily Griffonrook Run Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Greifenturmstrecke";
                break;
            case "Daily Hexfoundry Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Fluchgießerei Abgedreht";
                break;
            case "Daily Hidden Garden Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Verborgener Garten";
                break;
            case "Daily King Jalis's Refuge Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: König Jalis' Zuflucht";
                break;
            case "Daily Loreclaw Expanse Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Sagenklauen-Weite";
                break;
            case "Daily Morgan's Leap Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Morgans Sprung";
                break;
            case "Daily Only Zuhl Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Nur Zuhl";
                break;
            case "Daily Pig Iron Quarry Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Die Roheisen-Grube";
                break;
            case "Daily Portmatt's Lab Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Professor Portmatts Labor";
                break;
            case "Daily Scavenger's Chasm Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Der Abgrund des Plünderers";
                break;
            case "Daily Shaman's Rookery Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Schamanen-Kolonie";
                break;
            case "Daily Shattered Ice Ruins Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Die Zerschmetterten Eisruinen";
                break;
            case "Daily Skipping Stones Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Steine hüpfen lassen";
                break;
            case "Daily Spekks's Lab Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Spekks Labor";
                break;
            case "Daily Spelunker's Delve Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Höhlenforscher-Senke";
                break;
            case "Daily Swashbuckler's Cove Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Die Säbelrassler-Bucht";
                break;
            case "Daily Tribulation Caverns Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Trübsalriss-Höhlen";
                break;
            case "Daily Tribulation Rift Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Trübsalriss";
                break;
            case "Daily Under New Management Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Unter neuer Leitung";
                break;
            case "Daily Urmaug's Secret Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Urmaugs Geheimnis";
                break;
            case "Daily Vizier's Tower Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Der Turm des Wesirs";
                break;
            case "Daily Wall Breach Blitz Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Wallbrecherblitz";
                break;
            case "Daily Weyandt's Revenge Jumping Puzzle":
                formatted = "Tägliches Sprungrätsel: Weyandts Rache";
                break;
            // Events
            case "Daily Blazeridge Steppes Event Completer":
                formatted = "Tägliche Event-Koryphäe der Flammenkamm-Steppe";
                break;
            case "Daily Bloodtide Coast Event Completer":
                formatted = "Tägliche Event-Koryphäe der Blutstrom-Küste";
                break;
            case "Daily Brisban Wildlands Event Completer":
                formatted = "Tägliche Event-Koryphäe der Brisban-Wildnis";
                break;
            case "Daily Caledon Forest Event Completer":
                formatted = "Tägliche Event-Koryphäe des Caledon-Walds";
                break;
            case "Daily Cursed Shore Event Completer":
                formatted = "Tägliche Event-Koryphäe der Fluchküste";
                break;
            case "Daily Diessa Plateau Event Completer":
                formatted = "Tägliche Event-Koryphäe des Diessa-Plateaus";
                break;
            case "Daily Dredgehaunt Cliffs Event Completer":
                formatted = "Tägliche Event-Koryphäe der Schauflerschreck-Klippen";
                break;
            case "Daily Dry Top Event Completer":
                formatted = "Tägliche Event-Koryphäe der Trockenkuppe";
                break;
            case "Daily Fields of Ruin Event Completer":
                formatted = "Tägliche Event-Koryphäe der Felder der Verwüstung";
                break;
            case "Daily Fireheart Rise Event Completer":
                formatted = "Tägliche Event-Koryphäe der Feuerherzhügel";
                break;
            case "Daily Frostgorge Sound Event Completer":
                formatted = "Tägliche Event-Koryphäe des Eisklamm-Sunds";
                break;
            case "Daily Gendarran Fields Event Completer":
                formatted = "Tägliche Event-Koryphäe der Gendarran-Felder";
                break;
            case "Daily Harathi Hinterlands Event Completer":
                formatted = "Tägliche Event-Koryphäe des Harathi-Hinterlands";
                break;
            case "Daily Iron Marches Event Completer":
                formatted = "Tägliche Event-Koryphäe der Eisenmark";
                break;
            case "Daily Kessex Hills Event Completer":
                formatted = "Tägliche Event-Koryphäe der Kessex-Hügel";
                break;
            case "Daily Lornar's Pass Event Completer":
                formatted = "Tägliche Event-Koryphäe von Lornars Pass";
                break;
            case "Daily Malchor's Leap Event Completer":
                formatted = "Tägliche Event-Koryphäe von Malchors Sprung";
                break;
            case "Daily Metrica Province Event Completer":
                formatted = "Tägliche Event-Koryphäe der Provinz Metrica";
                break;
            case "Daily Mount Maelstrom Event Completer":
                formatted = "Tägliche Event-Koryphäe des Mahlstromgipfels";
                break;
            case "Daily Plains of Ashford Event Completer":
                formatted = "Tägliche Event-Koryphäe der Ebenen von Aschfurt";
                break;
            case "Daily Queensdale Event Completer":
                formatted = "Tägliche Event-Koryphäe des Königintals";
                break;
            case "Daily Silverwastes Event Completer":
                formatted = "Tägliche Event-Koryphäe der Silberwüste";
                break;
            case "Daily Snowden Drifts Event Completer":
                formatted = "Tägliche Event-Koryphäe der Schneekuhlenhöhen";
                break;
            case "Daily Southsun Cove Event Completer":
                formatted = "Tägliche Event-Koryphäe der Südlicht-Bucht";
                break;
            case "Daily Sparkfly Fen Event Completer":
                formatted = "Tägliche Event-Koryphäe des Funkenschwärmersumpfs";
                break;
            case "Daily Straits of Devastation Event Completer":
                formatted = "Tägliche Event-Koryphäe der Meerenge der Verwüstung";
                break;
            case "Daily Timberline Falls Event Completer":
                formatted = "Tägliche Event-Koryphäe der Baumgrenzen-Fälle";
                break;
            case "Daily Wayfarer Foothills Event Completer":
                formatted = "Tägliche Event-Koryphäe der Wanderer-Hügel";
                break;
            case "Daily Verdant Brink Event Completer":
                formatted = "Tägliche Event-Koryphäe: Grasgrüne Schwelle";
                break;
            case "Daily Auric Basin Event Completer":
                formatted = "Tägliche Event-Koryphäe: Güldener Talkessel";
                break;
            case "Daily Tangled Depths Event Completer":
                formatted = "Tägliche Event-Koryphäe: Verschlungene Tiefen";
                break;
            case "Daily Dragon's Stand Event Completer":
                formatted = "Tägliche Event-Koryphäe: Drachensturz";
                break;
            case "Daily Crystal Oasis Event Completer":
                formatted = "Tägliche Event-Koryphäe: Kristall Oase";
                break;
            case "Daily Desert Highlands Event Completer":
                formatted = "Tägliche Event-Koryphäe: Wüsten-Hochland";
                break;
            case "Daily Elon Riverlands Event Completer":
                formatted = "Tägliche Event-Koryphäe: Elon-Flusslande";
                break;
            case "Daily Desolation Event Completer":
                formatted = "Tägliche Event-Koryphäe: Ödland";
                break;
            case "Daily Vabbi Event Completer":
                formatted = "Tägliche Event-Koryphäe: Vabbi";
                break;
            // Bounties
            case "Daily Crystal Oasis Bounty Hunter":
                formatted = "Tägliche Kopfgeldjäger der Kristalloase";
                break;
            case "Daily Desert Highlands Bounty Hunter":
                formatted = "Täglicher Kopfgeldjäger des Wüsten-Hochlands";
                break;
            case "Daily Elon Riverlands Bounty Hunter":
                formatted = "Täglicher Kopfgeldjäger der Elon-Flusslande";
                break;
            case "Daily Desolation Bounty Hunter":
                formatted = "Täglicher Kopfgeldjäger des Ödlands";
                break;
            case "Daily Vabbian Bounty Hunter":
                formatted = "Täglicher Kopfgeldjäger von Vaabi";
                break;
            // Minidungeons
            case "Daily Bad Neighborhood Minidungeon":
                formatted = "Tägliches Mini-Verlies: Miese Nachbarschaft";
                break;
            case "Daily Don't Touch the Shiny Minidungeon":
                formatted = "Tägliches Mini-Verlies: Finger weg vom Glänzigen";
                break;
            case "Daily Forgotten Stream Minidungeon":
                formatted = "Tägliches Mini-Verlies: Der vergessene Fluss";
                break;
            case "Daily Forsaken Fortune Minidungeon":
                formatted = "Tägliches Mini-Verlies: Verdammte Schätze";
                break;
            case "Daily Goff's Loot Minidungeon":
                formatted = "Tägliches Mini-Verlies: Goffs Beute";
                break;
            case "Daily Grounded Minidungeon":
                formatted = "Tägliches Mini-Verlies: Geerdet";
                break;
            case "Daily Magellan's Memento Minidungeon":
                formatted = "Tägliches Mini-Verlies: Magellans Andenken";
                break;
            case "Daily Rebel's Seclusion Minidungeon":
                formatted = "Tägliches Mini-Verlies: Unterschlupf der Rebellen";
                break;
            case "Daily Ship of Sorrows Minidungeon":
                formatted = "Tägliches Mini-Verlies: Schiff des Leids";
                break;
            case "Daily Tears of Itlaocol Minidungeon":
                formatted = "Tägliches Mini-Verlies: Die Tränen Itlaocols";
                break;
            case "Daily The Long Way Around Minidungeon":
                formatted = "Tägliches Mini-Verlies: Ein großer Umweg";
                break;
            case "Daily Vexa's Lab Minidungeon":
                formatted = "Tägliches Mini-Verlies: Vexas Labor";
                break;
            case "Daily Windy Cave Treasure Minidungeon":
                formatted = "Tägliches Mini-Verlies: Schatz der Zugigen Höhle";
                break;
            default: formatted = input;
        }
        return formatted;
    }

    public static String formatDailiesPvpWvw(String input) {
        String formatted = "";

        switch (input) {
            case "Daily PvP Matches Played in Unranked or Ranked Arena":
                formatted = "Tägliche PvP-Matches in Arena mit oder ohne Rangwertung";
                break;
            case "Daily PvP Player Kills":
                formatted = "Tägliche PvP-Spielersiege";
                break;
            case "Daily PvP Rank Points":
                formatted = "Tägliche PvP-Rangpunkte";
                break;
            case "Daily PvP Rated Game Winner":
                formatted = "Täglicher Gewinner eines bewerteten PvP-Spiels";
                break;
            case "Daily PvP Reward Earner":
                formatted = "Täglicher PvP-Belohnungsmeister";
                break;
            case "Daily PvP Tournament Participator":
                formatted = "Täglicher Teilnehmer an einem PvP-Turnier";
                break;
            case "Daily Top Stats":
                formatted = "Tägliche Top-Stats";
                break;
            // WvW
            case "Daily Mists Guard Killer":
                formatted = "Täglicher Nebelwachen-Schlächter";
                break;
            case "Daily WvW Big Spender":
                formatted = "Täglicher WvW-Spendierer";
                break;
            case "Daily WvW Camp Capturer":
                formatted = "Täglicher WvW-Lager-Eroberer";
                break;
            case "Daily WvW Caravan Disruptor":
                formatted = "Täglicher WvW-Karawanen-Zerstörer";
                break;
            case "Daily WvW Invasion Defender":
                formatted = "Täglicher WvW-Invasions-Abwehrer";
                break;
            case "Daily WvW Keep Capturer":
                formatted = "Täglicher WvW-Festeneroberer";
                break;
            case "Daily WvW Land Claimer":
                formatted = "Täglicher WvW-Land-Beansprucher";
                break;
            case "Daily WvW Objective Defender":
                formatted = "Täglicher WvW-Zielobjekt-Verteidiger";
                break;
            case "Daily WvW Tower Capturer":
                formatted = "Täglicher WvW-Turm-Eroberer";
                break;
            case "Daily WvW World Ranker":
                formatted = "Täglicher WvW-Weltrang-Stürmer";
                break;
            case "Daily WvW Master of Monuments":
                formatted = "Täglicher Meister der Monumente";
                break;
            case "Daily WvW Veteran Creature Slayer":
                formatted = "Täglicher Bezwinger von WvW-Veteran-Kreaturen";
                break;
        }
        return formatted;
    }
}
