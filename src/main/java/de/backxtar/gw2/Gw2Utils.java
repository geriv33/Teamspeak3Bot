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

    public static String formatDailies(String input) {
        String formatted = "";

        switch (input) {
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
            // Dungeons
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
            // Gathering
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
                formatted = "Täglicher Holzfäller des Maguuma-Dschungels";
                break;
            case "Daily Maguuma Wastes Miner":
                formatted = "Täglicher Bergarbeiter des Maguuma-Dschungels";
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
            default:
                formatted = input;
        }
        return formatted;
    }
}
