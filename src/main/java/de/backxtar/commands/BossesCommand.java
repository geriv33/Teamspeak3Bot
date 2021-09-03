package de.backxtar.commands;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.gw2.CallBosses;
import de.backxtar.gw2.CallToken;
import de.backxtar.managers.CommandInterface;

import java.util.ArrayList;
import java.util.List;

public class BossesCommand implements CommandInterface {
    private final String[] bosses = {"vale_guardian", "gorseval", "sabetha", "slothasor", "bandit_trio", "matthias", "keep_construct", "xera", "cairn", "mursaat_overseer", "samarog", "deimos", "soulless_horror", "statues_of_grenth", "voice_in_the_void", "conjured_amalgamate", "twin_largos", "qadim", "adina", "sabir", "qadim_the_peerless"};
    private final String[] events = {"spirit_woods", "escort", "twisted_castle", "river_of_souls", "gate"};
    private final String[] bossTranslations = {"Tal-Wächter", "Gorseval", "Sabetha", "Faultierion", "Banditen-Trio", "Matthias", "Festenkonstrukt", "Xera", "Cairn", "Mursaat-Aufseher", "Samarog", "Deimos", "Desmina", "Statuen des Grenth", "Dhuum", "Beschworene Verschmelzung", "Zwillings-Largos", "Qadim", "Kardinal Adina", "Kardinal Sabir", "Qadim der Unvergleichliche"};
    private final String[] eventTranslations = {"Geisterlauf", "Belagert die Festung", "Verdrehtes Schloss", "Fluss der Seelen", "Tore von Ahdashim"};

    @Override
    public void run(String cmdValue, TS3Api api, TextMessageEvent event, Client client) {
        String[] gw2Values = CallToken.isValid(client);
        if (gw2Values == null) return;

        List<String> raidBosses = CallBosses.getRaidBosses(gw2Values[0]);
        int[] counters = {21, 5};
        String[] wings = new String[8];
        List<String> raidBoss = new ArrayList<>();
        List<String> raidEvent = new ArrayList<>();

        for (int i = 0; i < 21; i++) {
            if (raidBosses.contains(bosses[i])) {
                raidBoss.add(i, "[size=10][color=green]✔[/color] " + bossTranslations[i]);
                counters[0]--;
            } else raidBoss.add(i, "[size=10][color=red]✘[/color] " + bossTranslations[i]);
        }

        for (int i = 0; i < 5; i++) {
            if (raidBosses.contains(events[i])) {
                raidEvent.add(i, "[size=10][color=green]✔[/color] " + eventTranslations[i]);
                counters[1]--;
            } else raidEvent.add(i, "[size=10][color=red]✘[/color] " + eventTranslations[i]);
        }

        wings[0] = (raidBosses.contains(bosses[0]) && raidBosses.contains(events[0])
                && raidBosses.contains(bosses[1]) && raidBosses.contains(bosses[2])) ?
                "[size=10][color=green]✔[/color] Geistertal:[/size]" :
                "[size=10][color=red]✘[/color] Geistertal:[/size]";
        wings[1] = (raidBosses.contains(bosses[3]) && raidBosses.contains(bosses[4])
                && raidBosses.contains(bosses[5])) ?
                "[size=10][color=green]✔[/color] Erlösungspass:[/size] " :
                "[size=10][color=red]✘[/color] Erlösungspass:[/size]";
    }
}
