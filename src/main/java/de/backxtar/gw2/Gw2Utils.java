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
        httpCon.setConnectTimeout(10000);
        httpCon.setReadTimeout(10000);
        return httpCon.getInputStream();
    }

    public static String currency(int id, long amount) {
        //https://api.guildwars2.com/v2/currencies?ids=all
        String currency = "";

        switch (id) {
            case 1 : long[] coins = getCoins(amount);
                currency = "╰ [color=" + colors.mainColor + "][b]Coins:[/b][/color] " + (coins[2] > 0 ? "[b]" + coins[2] + "[/b] Gold, " : "") +
                        (coins[1] > 0 ? "[b]" + coins[1] + "[/b] Silver, " : "") +
                        (coins[0] > 0 ? "[b]" + coins[0] + "[/b] Copper" : "");
                break;
            case 2 : currency = "╰ [color=" + colors.mainColor + "][b]Karma:[/b][/color] " + amount;
                break;
            case 3 : currency = "╰ [color=" + colors.mainColor + "][b]Laurels:[/b][/color] " + amount;
                break;
            case 4 : currency = "╰ [color=" + colors.mainColor + "][b]Gems:[/b][/color] " + amount;
                break;
            case 5 : currency = "╰ [color=" + colors.mainColor + "][b]Ascalonian Tears:[/b][/color] " + amount;
                break;
            case 6 : currency = "╰ [color=" + colors.mainColor + "][b]Shard of Zhaitan:[/b][/color] " + amount;
                break;
            case 7 : currency = "╰ [color=" + colors.mainColor + "][b]Fractal Relics:[/b][/color] " + amount;
                break;
            case 9 : currency = "╰ [color=" + colors.mainColor + "][b]Seal of Beetletun:[/b][/color] " + amount;
                break;
            case 10 : currency = "╰ [color=" + colors.mainColor + "][b]Manifesto of the Moletariate:[/b][/color] " + amount;
                break;
            case 11 : currency = "╰ [color=" + colors.mainColor + "][b]Deadly Blooms:[/b][/color] " + amount;
                break;
            case 12 : currency = "╰ [color=" + colors.mainColor + "][b]Symbols of Koda:[/b][/color] " + amount;
                break;
            case 13 : currency = "╰ [color=" + colors.mainColor + "][b]Flame Legion Charr Carving:[/b][/color] " + amount;
                break;
            case 14 : currency = "╰ [color=" + colors.mainColor + "][b]Knowledge Crystals:[/b][/color] " + amount;
                break;
            case 15 : currency = "╰ [color=" + colors.mainColor + "][b]Badge of Honor:[/b][/color] " + amount;
                break;
            case 16 : currency = "╰ [color=" + colors.mainColor + "][b]Guild Commendation:[/b][/color] " + amount;
                break;
            case 18 : currency = "╰ [color=" + colors.mainColor + "][b]Transmutation Charges:[/b][/color] " + amount;
                break;
            case 19 : currency = "╰ [color=" + colors.mainColor + "][b]Airship Parts:[/b][/color] " + amount;
                break;
            case 20 : currency = "╰ [color=" + colors.mainColor + "][b]Ley-Line Crystals:[/b][/color] " + amount;
                break;
            case 22 : currency = "╰ [color=" + colors.mainColor + "][b]Lumps of Aurillium:[/b][/color] " + amount;
                break;
            case 23 : currency = "╰ [color=" + colors.mainColor + "][b]Spirit Shards:[/b][/color] " + amount;
                break;
            case 24 : currency = "╰ [color=" + colors.mainColor + "][b]Pristine Fractal Relic:[/b][/color] " + amount;
                break;
            case 25 : currency = "╰ [color=" + colors.mainColor + "][b]Geodes:[/b][/color] " + amount;
                break;
            case 26 : currency = "╰ [color=" + colors.mainColor + "][b]WvW Skirmish Claim Tickets:[/b][/color] " + amount;
                break;
            case 27 : currency = "╰ [color=" + colors.mainColor + "][b]Bandit Crest:[/b][/color] " + amount;
                break;
            case 28 : currency = "╰ [color=" + colors.mainColor + "][b]Magnetite Shard:[/b][/color] " + amount;
                break;
            case 29 : currency = "╰ [color=" + colors.mainColor + "][b]Provisioner Token:[/b][/color] " + amount;
                break;
            case 30 : currency = "╰ [color=" + colors.mainColor + "][b]PvP-Leauge-Tickets:[/b][/color] " + amount;
                break;
            case 31 : currency = "╰ [color=" + colors.mainColor + "][b]Proof of Heroics:[/b][/color] " + amount;
                break;
            case 32 : currency = "╰ [color=" + colors.mainColor + "][b]Ascended Shard of Glory:[/b][/color] " + amount;
                break;
            case 33 : currency = "╰ [color=" + colors.mainColor + "][b]Unbound Magic:[/b][/color] " + amount;
                break;
            case 34 : currency = "╰ [color=" + colors.mainColor + "][b]Trade Contract:[/b][/color] " + amount;
                break;
            case 35 : currency = "╰ [color=" + colors.mainColor + "][b]Elegy Mosaic:[/b][/color] " + amount;
                break;
            case 36 : currency = "╰ [color=" + colors.mainColor + "][b]Testimony of Heroics:[/b][/color] " + amount;
                break;
            case 37 : currency = "╰ [color=" + colors.mainColor + "][b]Exalted Key:[/b][/color] " + amount;
                break;
            case 38 : currency = "╰ [color=" + colors.mainColor + "][b]Machetes:[/b][/color] " + amount;
                break;
            case 39 : currency = "╰ [color=" + colors.mainColor + "][b]Gaeting Crystal:[/b][/color] " + amount;
                break;
            case 40 : currency = "╰ [color=" + colors.mainColor + "][b]Bandit Skeleton Key:[/b][/color] " + amount;
                break;
            case 41 : currency = "╰ [color=" + colors.mainColor + "][b]Pact Crowbar:[/b][/color] " + amount;
                break;
            case 42 : currency = "╰ [color=" + colors.mainColor + "][b]Vial of Chak Acid:[/b][/color] " + amount;
                break;
            case 43 : currency = "╰ [color=" + colors.mainColor + "][b]Zephyrite Lockpick:[/b][/color] " + amount;
                break;
            case 44 : currency = "╰ [color=" + colors.mainColor + "][b]Trader's Key:[/b][/color] " + amount;
                break;
            case 45 : currency = "╰ [color=" + colors.mainColor + "][b]Volatile Magic:[/b][/color] " + amount;
                break;
            case 46 : currency = "╰ [color=" + colors.mainColor + "][b]PvP Tournament Voucher:[/b][/color] " + amount;
                break;
            case 47 : currency = "╰ [color=" + colors.mainColor + "][b]Racing Medallion:[/b][/color] " + amount;
                break;
            case 49 : currency = "╰ [color=" + colors.mainColor + "][b]Mistborn Key:[/b][/color] " + amount;
                break;
            case 50 : currency = "╰ [color=" + colors.mainColor + "][b]Festival Token:[/b][/color] " + amount;
                break;
            case 51 : currency = "╰ [color=" + colors.mainColor + "][b]Cache Key:[/b][/color] " + amount;
                break;
            case 52 : currency = "╰ [color=" + colors.mainColor + "][b]Red Prophet Shard:[/b][/color] " + amount;
                break;
            case 53 : currency = "╰ [color=" + colors.mainColor + "][b]Green Prophet Shard:[/b][/color] " + amount;
                break;
            case 54 : currency = "╰ [color=" + colors.mainColor + "][b]Blue Prophet Crystal:[/b][/color] " + amount;
                break;
            case 55 : currency = "╰ [color=" + colors.mainColor + "][b]Green Prophet Crystal:[/b][/color] " + amount;
                break;
            case 56 : currency = "╰ [color=" + colors.mainColor + "][b]Red Prophet Crystal:[/b][/color] " + amount;
                break;
            case 57 : currency = "╰ [color=" + colors.mainColor + "][b]Blue Prophet Shard:[/b][/color] " + amount;
                break;
            case 58 : currency = "╰ [color=" + colors.mainColor + "][b]War Supplies:[/b][/color] " + amount;
                break;
            case 59 : currency = "╰ [color=" + colors.mainColor + "][b]Unstable Fractal Essence:[/b][/color] " + amount;
                break;
            case 60 : currency = "╰ [color=" + colors.mainColor + "][b]Tyrian Defense Seal:[/b][/color] " + amount;
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
                formatted = input.substring(32) + " - Volcanic";
                break;
            case "2" :
            case "36":
            case "44":
            case "62":
                formatted = input.substring(32) + " - Un-Catagorized";
                break;
            case "3" :
            case "27":
            case "51":
            case "68":
                formatted = input.substring(32) + " - Snowblind Fractal";
                break;
            case "4" :
            case "31":
            case "57":
            case "66":
                formatted = input.substring(32) + " - Urban Battleground ";
                break;
            case "5" :
            case "17":
            case "32":
            case "56":
                formatted = input.substring(32) + " - Swampland";
                break;
            case "6" :
            case "21":
            case "47":
            case "69":
                formatted = input.substring(32) + " - Cliffside";
                break;
            case "7" :
            case "26":
            case "61":
                formatted = input.substring(32) + " - Aquatic Ruins";
                break;
            case "8" :
            case "29":
            case "53":
                formatted = input.substring(32) + " - Underground Facility ";
                break;
            case "9" :
            case "22":
            case "39":
            case "58":
                formatted = input.substring(32) + " - Molten Furnace ";
                break;
            case "10":
            case "40":
            case "70":
                formatted = input.substring(32) + " - Molten Boss ";
                break;
            case "11":
            case "33":
            case "67":
                formatted = input.substring(32) + " - Deepstone";
                break;
            case "12":
            case "37":
            case "54":
                formatted = input.substring(32) + " - Siren's Reef";
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
                formatted = input.substring(32) + " - Aetherblade";
                break;
            case "15":
            case "34":
            case "43":
            case "55":
            case "64":
                formatted = input.substring(32) + " - Thaumanova-Reactor";
                break;
            case "16":
            case "41":
            case "59":
                formatted = input.substring(32) + " - Twilight Oasis ";
                break;
            case "18":
            case "42":
            case "72":
                formatted = input.substring(32) + " - Captain Mai Trin Boss";
                break;
            case "20":
            case "35":
            case "45":
            case "60":
                formatted = input.substring(32) + " - Solid Ocean";
                break;
            case "23":
            case "48":
            case "73":
                formatted = input.substring(32) + " - Nightmare";
                break;
            case "24":
            case "49":
            case "74":
                formatted = input.substring(32) + " - Shattered Observatory";
                break;
            case "25":
            case "50":
            case "75":
                formatted = input.substring(32) + " - Sunqua-Peak";
                break;
            default :
        }
        return formatted;
    }

    public static String formatDailyFractals(String input) {
        String formatted;

        switch (input.substring(13)) {
            case "Aquatic Ruins" :
                formatted = "76 - Aquatic Ruins";
                break;
            case "Swampland" :
                formatted = "89 - Swampland";
                break;
            case "Siren's Reef" :
                formatted = "78 - Siren's Reefe";
                break;
            case "Uncategorized" :
                formatted = "91 - Uncategorized";
                break;
            case "Solid Ocean" :
                formatted = "80 - Solid Ocean";
                break;
            case "Underground Facility" :
                formatted = "81 - Underground Facility";
                break;
            case "Thaumanova Reactor" :
                formatted = "82 - Thaumanova Reactor";
                break;
            case "Molten Furnace" :
                formatted = "83 - Molten Furnace";
                break;
            case "Deepstone" :
                formatted = "84 - Deepstone";
                break;
            case "Urban Battleground" :
                formatted = "85 - Urban Battleground";
                break;
            case "Snowblind" :
                formatted = "93 - Snowblind";
                break;
            case "Twilight Oasis" :
                formatted = "87 - Twilight Oasis";
                break;
            case "Chaos" :
                formatted = "97 - Chaos";
                break;
            case "Molten Boss" :
                formatted = "90 - Molten Boss";
                break;
            case "Volcanic" :
                formatted = "92 - Volcanic";
                break;
            case "Cliffside" :
                formatted = "94 - Cliffside";
                break;
            case "Captain Mai Trin Boss" :
                formatted = "95 - Captain Mai Trin Boss";
                break;
            case "Aetherblade" :
                formatted = "96 - Aetherblade";
                break;
            case "Nightmare" :
                formatted = "98 - Nightmare";
                break;
            case "Shattered Observatory" :
                formatted = "99 - Shattered Observatory\"";
                break;
            case "Sunqua Peak" :
                formatted = "100 - Sunqua Peak";
                break;
            default: formatted = input;
        }
        return formatted;
    }

    public static String formatDailyStrike(String input) {
        String formatted = "";

        switch (input) {
            case "boneskinner":
                formatted = "boneskinner";
                break;
            case "fraenir_of_jormag":
                formatted = "fraenir_of_jormag";
                break;
            case "icebrood_construct":
                formatted = "icebrood_construct";
                break;
            case "voice_of_the_fallen":
                formatted = "voice_of_the_fallen";
                break;
            case "whisper_of_jormag":
                formatted = "whisper_of_jormag";
                break;
            case "cold_war":
                formatted = "cold_war";
                break;
            default:
        }
        return formatted;
    }

    public static String formatDaily(String input) {
        String formatted;

        switch (input) {
            case "Daily Ascalonian Catacombs":
                formatted = "Daily Ascalonian Catacombs";
                break;
            case "Daily Caudecus's Manor":
                formatted = "Daily Caudecus's Manor";
                break;
            case "Daily Twilight Arbor":
                formatted = "Daily Twilight Arbor";
                break;
            case "Daily Sorrow's Embrace":
                formatted = "Daily Sorrow's Embrace";
                break;
            case "Daily Citadel of Flame":
                formatted = "Daily Citadel of Flame";
                break;
            case "Daily Honor of the Waves":
                formatted = "Daily Honor of the Waves";
                break;
            case "Daily Crucible of Eternity":
                formatted = "TDaily Crucible of Eternity";
                break;
            case "Daily Ruined City of Arah":
                formatted = "Daily Ruined City of Arah";
                break;
            case "Daily Ascalon Forager":
                formatted = "Daily Ascalon Forager";
                break;
            case "Daily Ascalon Lumberer":
                formatted = "Daily Ascalon Lumberer";
                break;
            case "Daily Ascalon Miner":
                formatted = "Daily Ascalon Miner";
                break;
            case "Daily Kryta Forager":
                formatted = "Daily Kryta Forager";
                break;
            case "Daily Kryta Lumberer":
                formatted = "Daily Kryta Lumberer";
                break;
            case "Daily Kryta Miner":
                formatted = "Daily Kryta Miner";
                break;
            case "Daily Maguuma Jungle Forager":
                formatted = "Daily Maguuma Jungle Forager";
                break;
            case "Daily Maguuma Jungle Lumberer":
                formatted = "Daily Maguuma Jungle Lumberer";
                break;
            case "Daily Maguuma Jungle Miner":
                formatted = "Daily Maguuma Jungle Miner";
                break;
            case "Daily Maguuma Wastes Forager":
                formatted = "Daily Maguuma Wastes Forager";
                break;
            case "Daily Maguuma Wastes Lumberer":
                formatted = "Daily Maguuma Wastes Lumberer";
                break;
            case "Daily Maguuma Wastes Miner":
                formatted = "Daily Maguuma Wastes Miner";
                break;
            case "Daily Orr Forager":
                formatted = "Daily Orr Forager";
                break;
            case "Daily Orr Lumberer":
                formatted = "Daily Orr Lumberer";
                break;
            case "Daily Orr Miner":
                formatted = "Daily Orr Miner";
                break;
            case "Daily Shiverpeaks Forager":
                formatted = "Daily Shiverpeaks Forager";
                break;
            case "Daily Shiverpeaks Lumberer":
                formatted = "Daily Shiverpeaks Lumberer";
                break;
            case "Daily Shiverpeaks Miner":
                formatted = "Daily Shiverpeaks Miner";
                break;
            case "Daily Heart of Maguuma Forager":
                formatted = "Daily Heart of Maguuma Forager";
                break;
            case "Daily Heart of Maguuma Lumberer":
                formatted = "Daily Heart of Maguuma Lumberer";
                break;
            case "Daily Heart of Maguuma Miner":
                formatted = "Daily Heart of Maguuma Miner";
                break;
            case "Daily Desert Forager":
                formatted = "Daily Desert Forager";
                break;
            case "Daily Desert Lumberer":
                formatted = "Daily Desert Lumberer";
                break;
            case "Daily Desert Miner":
                formatted = "Daily Desert Miner";
                break;
            // Renown Hearts
            case "Daily Elon Riverlands Taskmaster":
                formatted = "Daily Elon Riverlands Taskmaster";
                break;
            case "Daily Desolation Taskmaster":
                formatted = "Täglicher Meister der Aufgaben des Ödlandes";
                break;
            // World Bosses
            case "Daily Claw of Jormag":
                formatted = "Daily Claw of Jormag";
                break;
            case "Daily Demolisher":
                formatted = "Daily Demolisher";
                break;
            case "Daily Fire Elemental":
                formatted = "Daily Fire Elemental";
                break;
            case "Daily Frozen Maw":
                formatted = "Daily Frozen Maw";
                break;
            case "Daily Great Jungle Wurm":
                formatted = "Daily Great Jungle Wurm";
                break;
            case "Daily Hound Master":
                formatted = "Daily Hound Master";
                break;
            case "Daily Inquest Golem Mark II":
                formatted = "Daily Inquest Golem Mark II";
                break;
            case "Daily Megadestroyer":
                formatted = "Daily Megadestroyer";
                break;
            case "Daily Shadow Behemoth":
                formatted = "Daily Shadow Behemoth";
                break;
            case "Daily Shatterer":
                formatted = "Daily Shatterer";
                break;
            // Other
            case "Daily Activity Participation":
                formatted = "Daily Activity Participation";
                break;
            case "Daily Mystic Forger":
                formatted = "Daily Mystic Forger";
                break;
            // Adventures
            case "Daily Adventure: A Fungus Among Us":
                formatted = "Daily Adventure: A Fungus Among Us";
                break;
            case "Daily Adventure: Beetle Feast":
                formatted = "Daily Adventure: Beetle Feast";
                break;
            case "Daily Adventure: Bugs in the Branches":
                formatted = "Daily Adventure: Bugs in the Branches";
                break;
            case "Daily Adventure: Drone Race":
                formatted = "Daily Adventure: Drone Race";
                break;
            case "Daily Adventure: Fallen Masks":
                formatted = "Daily Adventure: Fallen Masks";
                break;
            case "Daily Adventure: Flying Circus":
                formatted = "Daily Adventure: Flying Circus";
                break;
            case "Daily Adventure: Haywire Punch-o-Matic Battle":
                formatted = "Daily Adventure: Haywire Punch-o-Matic Battle";
                break;
            case "Daily Adventure: On Wings of Gold":
                formatted = "Daily Adventure: On Wings of Gold";
                break;
            case "Daily Adventure: Salvage Pit":
                formatted = "Daily Adventure: Salvage Pit";
                break;
            case "Daily Adventure: Sanctum Scramble":
                formatted = "Daily Adventure: Sanctum Scramble";
                break;
            case "Daily Adventure: Scrap Rifle Field Test":
                formatted = "Daily Adventure: Scrap Rifle Field Test";
                break;
            case "Daily Adventure: Shooting Gallery":
                formatted = "Daily Adventure: Shooting Gallery";
                break;
            case "Daily Adventure: Tendril Torchers":
                formatted = "Daily Adventure: Tendril Torchers";
                break;
            case "Daily Adventure: The Floor Is Lava?":
                formatted = "Daily Adventure: The Floor Is Lava?";
                break;
            case "Daily Adventure: The Ley-Line Run":
                formatted = "Daily Adventure: The Ley-Line Run\"";
                break;
            case "Daily Desert Adventurer":
                formatted = "Daily Desert Adventurer";
                break;
            //Vistas
            case "Daily Ascalon Vista Viewer":
                formatted = "Daily Ascalon Vista Viewer";
                break;
            case "Daily Kryta Vista Viewer":
                formatted = "Daily Kryta Vista Viewer";
                break;
            case "Daily Maguuma Vista Viewer":
                formatted = "Daily Maguuma Vista Viewer";
                break;
            case "Daily Maguuma Wastes Vista Viewer":
                formatted = "Daily Maguuma Wastes Vista Viewer";
                break;
            case "Daily Orr Vista Viewer":
                formatted = "Daily Orr Vista Viewer";
                break;
            case "Daily Shiverpeaks Vista Viewer":
                formatted = "Daily Shiverpeaks Vista Viewer";
                break;
            case "Daily Heart of Maguuma Vista Viewer":
                formatted = "Daily Heart of Maguuma Vista Viewer";
                break;
            case "Daily Desert Vista Viewer":
                formatted = "Daily Desert Vista Viewer";
                break;
            // Jumping Puzzle
            case "Daily Antre of Adjournment Jumping Puzzle":
                formatted = "Daily Antre of Adjournment Jumping Puzzle";
                break;
            case "Daily Behem Gauntlet Jumping Puzzle":
                formatted = "Daily Behem Gauntlet Jumping Puzzle";
                break;
            case "Daily Branded Mine Jumping Puzzle":
                formatted = "Daily Branded Mine Jumping Puzzle";
                break;
            case "Daily Buried Archives Jumping Puzzle":
                formatted = "Daily Buried Archives Jumping Puzzle";
                break;
            case "Daily Chaos Crystal Cavern Jumping Puzzle":
                formatted = "Daily Chaos Crystal Cavern Jumping Puzzle";
                break;
            case "Daily Coddler's Cove Jumping Puzzle":
                formatted = "Daily Coddler's Cove Jumping Puzzle";
                break;
            case "Daily Collapsed Observatory Jumping Puzzle":
                formatted = "Daily Collapsed Observatory Jumping Puzzle";
                break;
            case "Daily Conundrum Cubed Jumping Puzzle":
                formatted = "Daily Conundrum Cubed Jumping Puzzl";
                break;
            case "Daily Crash Site Jumping Puzzle":
                formatted = "Daily Crash Site Jumping Puzzle";
                break;
            case "Daily Craze's Folly Jumping Puzzle":
                formatted = "Daily Craze's Folly Jumping Puzzle";
                break;
            case "Daily Crimson Plateau Jumping Puzzle":
                formatted = "Daily Crimson Plateau Jumping Puzzle";
                break;
            case "Daily Dark Reverie Jumping Puzzle":
                formatted = "Daily Dark Reverie Jumping Puzzle";
                break;
            case "Daily Demongrub Pits Jumping Puzzle":
                formatted = "Daily Demongrub Pits Jumping Puzzle";
                break;
            case "Daily Fawcett's Bounty Jumping Puzzle":
                formatted = "Daily Fawcett's Bounty Jumping Puzzle";
                break;
            case "Daily Goemm's Lab Jumping Puzzle":
                formatted = "Daily Goemm's Lab Jumping Puzzle";
                break;
            case "Daily Grendich Gamble Jumping Puzzle":
                formatted = "Daily Grendich Gamble Jumping Puzzle";
                break;
            case "Daily Griffonrook Run Jumping Puzzle":
                formatted = "Daily Griffonrook Run Jumping Puzzle";
                break;
            case "Daily Hexfoundry Jumping Puzzle":
                formatted = "Daily Hexfoundry Jumping Puzzle";
                break;
            case "Daily Hidden Garden Jumping Puzzle":
                formatted = "Daily Hidden Garden Jumping Puzzle";
                break;
            case "Daily King Jalis's Refuge Jumping Puzzle":
                formatted = "Daily King Jalis's Refuge Jumping Puzzle";
                break;
            case "Daily Loreclaw Expanse Jumping Puzzle":
                formatted = "Daily Loreclaw Expanse Jumping Puzzle";
                break;
            case "Daily Morgan's Leap Jumping Puzzle":
                formatted = "Daily Morgan's Leap Jumping Puzzle";
                break;
            case "Daily Only Zuhl Jumping Puzzle":
                formatted = "Daily Only Zuhl Jumping Puzzle";
                break;
            case "Daily Pig Iron Quarry Jumping Puzzle":
                formatted = "Daily Pig Iron Quarry Jumping Puzzle";
                break;
            case "Daily Portmatt's Lab Jumping Puzzle":
                formatted = "Daily Portmatt's Lab Jumping Puzzle";
                break;
            case "Daily Scavenger's Chasm Jumping Puzzle":
                formatted = "Daily Scavenger's Chasm Jumping Puzzle";
                break;
            case "Daily Shaman's Rookery Jumping Puzzle":
                formatted = "Daily Shaman's Rookery Jumping Puzzle";
                break;
            case "Daily Shattered Ice Ruins Jumping Puzzle":
                formatted = "Daily Shattered Ice Ruins Jumping Puzzle";
                break;
            case "Daily Skipping Stones Jumping Puzzle":
                formatted = "Daily Skipping Stones Jumping Puzzle";
                break;
            case "Daily Spekks's Lab Jumping Puzzle":
                formatted = "Daily Spekks's Lab Jumping Puzzle";
                break;
            case "Daily Spelunker's Delve Jumping Puzzle":
                formatted = "Daily Spelunker's Delve Jumping Puzzlee";
                break;
            case "Daily Swashbuckler's Cove Jumping Puzzle":
                formatted = "Daily Swashbuckler's Cove Jumping Puzzlet";
                break;
            case "Daily Tribulation Caverns Jumping Puzzle":
                formatted = "Daily Tribulation Caverns Jumping Puzzle";
                break;
            case "Daily Tribulation Rift Jumping Puzzle":
                formatted = "Daily Tribulation Rift Jumping Puzzle";
                break;
            case "Daily Under New Management Jumping Puzzle":
                formatted = "Daily Under New Management Jumping Puzzle";
                break;
            case "Daily Urmaug's Secret Jumping Puzzle":
                formatted = "Daily Urmaug's Secret Jumping Puzzle";
                break;
            case "Daily Vizier's Tower Jumping Puzzle":
                formatted = "Daily Vizier's Tower Jumping Puzzle";
                break;
            case "Daily Wall Breach Blitz Jumping Puzzle":
                formatted = "Daily Wall Breach Blitz Jumping Puzzle";
                break;
            case "Daily Weyandt's Revenge Jumping Puzzle":
                formatted = "Daily Weyandt's Revenge Jumping Puzzle";
                break;
            // Events
            case "Daily Blazeridge Steppes Event Completer":
                formatted = "Daily Blazeridge Steppes Event Completer";
                break;
            case "Daily Bloodtide Coast Event Completer":
                formatted = "Daily Bloodtide Coast Event Completer";
                break;
            case "Daily Brisban Wildlands Event Completer":
                formatted = "Daily Brisban Wildlands Event Completer";
                break;
            case "Daily Caledon Forest Event Completer":
                formatted = "Daily Caledon Forest Event Completer";
                break;
            case "Daily Cursed Shore Event Completer":
                formatted = "Daily Cursed Shore Event Completer";
                break;
            case "Daily Diessa Plateau Event Completer":
                formatted = "Daily Diessa Plateau Event Completer";
                break;
            case "Daily Dredgehaunt Cliffs Event Completer":
                formatted = "Daily Dredgehaunt Cliffs Event Completer";
                break;
            case "Daily Dry Top Event Completer":
                formatted = "Daily Dry Top Event Completer";
                break;
            case "Daily Fields of Ruin Event Completer":
                formatted = "Daily Fields of Ruin Event Completer";
                break;
            case "Daily Fireheart Rise Event Completer":
                formatted = "Daily Fireheart Rise Event Completer";
                break;
            case "Daily Frostgorge Sound Event Completer":
                formatted = "Daily Frostgorge Sound Event Completer";
                break;
            case "Daily Gendarran Fields Event Completer":
                formatted = "Daily Gendarran Fields Event Completer";
                break;
            case "Daily Harathi Hinterlands Event Completer":
                formatted = "Daily Harathi Hinterlands Event Completer";
                break;
            case "Daily Iron Marches Event Completer":
                formatted = "Daily Iron Marches Event Completer";
                break;
            case "Daily Kessex Hills Event Completer":
                formatted = "Daily Kessex Hills Event Completer";
                break;
            case "Daily Lornar's Pass Event Completer":
                formatted = "Daily Lornar's Pass Event Completer";
                break;
            case "Daily Malchor's Leap Event Completer":
                formatted = "Daily Malchor's Leap Event Completer";
                break;
            case "Daily Metrica Province Event Completer":
                formatted = "Daily Metrica Province Event Completer";
                break;
            case "Daily Mount Maelstrom Event Completer":
                formatted = "Daily Mount Maelstrom Event Completer";
                break;
            case "Daily Plains of Ashford Event Completer":
                formatted = "Daily Plains of Ashford Event Completer";
                break;
            case "Daily Queensdale Event Completer":
                formatted = "Daily Queensdale Event Completer";
                break;
            case "Daily Silverwastes Event Completer":
                formatted = "Daily Silverwastes Event Completer";
                break;
            case "Daily Snowden Drifts Event Completer":
                formatted = "Daily Snowden Drifts Event Completer";
                break;
            case "Daily Southsun Cove Event Completer":
                formatted = "Daily Southsun Cove Event Completer";
                break;
            case "Daily Sparkfly Fen Event Completer":
                formatted = "Daily Sparkfly Fen Event Completer";
                break;
            case "Daily Straits of Devastation Event Completer":
                formatted = "Daily Straits of Devastation Event Completer";
                break;
            case "Daily Timberline Falls Event Completer":
                formatted = "Daily Timberline Falls Event Completer";
                break;
            case "Daily Wayfarer Foothills Event Completer":
                formatted = "Daily Wayfarer Foothills Event Completer";
                break;
            case "Daily Verdant Brink Event Completer":
                formatted = "Daily Verdant Brink Event Completer";
                break;
            case "Daily Auric Basin Event Completer":
                formatted = "Daily Auric Basin Event Completer";
                break;
            case "Daily Tangled Depths Event Completer":
                formatted = "Daily Tangled Depths Event Completer";
                break;
            case "Daily Dragon's Stand Event Completer":
                formatted = "Daily Dragon's Stand Event Completer";
                break;
            case "Daily Crystal Oasis Event Completer":
                formatted = "Daily Crystal Oasis Event Completer";
                break;
            case "Daily Desert Highlands Event Completer":
                formatted = "Daily Desert Highlands Event Completer";
                break;
            case "Daily Elon Riverlands Event Completer":
                formatted = "Daily Elon Riverlands Event Completer\"";
                break;
            case "Daily Desolation Event Completer":
                formatted = "Daily Desolation Event Completer";
                break;
            case "Daily Vabbi Event Completer":
                formatted = "Daily Vabbi Event Completer";
                break;
            // Bounties
            case "Daily Crystal Oasis Bounty Hunter":
                formatted = "Daily Crystal Oasis Bounty Hunter";
                break;
            case "Daily Desert Highlands Bounty Hunter":
                formatted = "Daily Desert Highlands Bounty Hunter";
                break;
            case "Daily Elon Riverlands Bounty Hunter":
                formatted = "Daily Elon Riverlands Bounty Hunter";
                break;
            case "Daily Desolation Bounty Hunter":
                formatted = "Daily Desolation Bounty Hunter";
                break;
            case "Daily Vabbian Bounty Hunter":
                formatted = "Daily Vabbian Bounty Hunter";
                break;
            // Minidungeons
            case "Daily Bad Neighborhood Minidungeon":
                formatted = "Daily Bad Neighborhood Minidungeon";
                break;
            case "Daily Don't Touch the Shiny Minidungeon":
                formatted = "Daily Don't Touch the Shiny Minidungeon";
                break;
            case "Daily Forgotten Stream Minidungeon":
                formatted = "Daily Forgotten Stream Minidungeon";
                break;
            case "Daily Forsaken Fortune Minidungeon":
                formatted = "Daily Forsaken Fortune Minidungeon";
                break;
            case "Daily Goff's Loot Minidungeon":
                formatted = "Daily Goff's Loot Minidungeon";
                break;
            case "Daily Grounded Minidungeon":
                formatted = "Daily Grounded Minidungeon";
                break;
            case "Daily Magellan's Memento Minidungeon":
                formatted = "Daily Magellan's Memento Minidungeon";
                break;
            case "Daily Rebel's Seclusion Minidungeon":
                formatted = "Daily Rebel's Seclusion Minidungeon";
                break;
            case "Daily Ship of Sorrows Minidungeon":
                formatted = "Daily Ship of Sorrows Minidungeon";
                break;
            case "Daily Tears of Itlaocol Minidungeon":
                formatted = "Daily Tears of Itlaocol Minidungeon";
                break;
            case "Daily The Long Way Around Minidungeon":
                formatted = "Daily The Long Way Around Minidungeon";
                break;
            case "Daily Vexa's Lab Minidungeon":
                formatted = "Daily Vexa's Lab Minidungeon";
                break;
            case "Daily Windy Cave Treasure Minidungeon":
                formatted = "Daily Windy Cave Treasure Minidungeon";
                break;
            default: formatted = input;
        }
        return formatted;
    }

    public static String formatDailiesPvpWvw(String input) {
        String formatted = "";

        switch (input) {
            case "Daily PvP Matches Played in Unranked or Ranked Arena":
                formatted = "Daily PvP Matches Played in Unranked or Ranked Arena";
                break;
            case "Daily PvP Player Kills":
                formatted = "Daily PvP Player Kills";
                break;
            case "Daily PvP Rank Points":
                formatted = "Daily PvP Rank Points";
                break;
            case "Daily PvP Rated Game Winner":
                formatted = "Daily PvP Rated Game Winner";
                break;
            case "Daily PvP Reward Earner":
                formatted = "Daily PvP Reward Earnerr";
                break;
            case "Daily PvP Tournament Participator":
                formatted = "Daily PvP Tournament Participator";
                break;
            case "Daily Top Stats":
                formatted = "Daily Top Stats";
                break;
            // WvW
            case "Daily Mists Guard Killer":
                formatted = "Daily Mists Guard Killer";
                break;
            case "Daily WvW Big Spender":
                formatted = "Daily WvW Big Spender";
                break;
            case "Daily WvW Camp Capturer":
                formatted = "Daily WvW Camp Capturer";
                break;
            case "Daily WvW Caravan Disruptor":
                formatted = "Daily WvW Caravan Disruptor";
                break;
            case "Daily WvW Invasion Defender":
                formatted = "Daily WvW Invasion Defender";
                break;
            case "Daily WvW Keep Capturer":
                formatted = "Daily WvW Keep Capturer";
                break;
            case "Daily WvW Land Claimer":
                formatted = "Daily WvW Land Claimer";
                break;
            case "Daily WvW Objective Defender":
                formatted = "Daily WvW Objective Defenderr";
                break;
            case "Daily WvW Tower Capturer":
                formatted = "Daily WvW Tower Capturer";
                break;
            case "Daily WvW World Ranker":
                formatted = "Daily WvW World Ranker";
                break;
            case "Daily WvW Master of Monuments":
                formatted = "Daily WvW Master of Monuments";
                break;
            case "Daily WvW Veteran Creature Slayer":
                formatted = "Daily WvW Veteran Creature Slayer";
                break;
        }
        return formatted;
    }
}
