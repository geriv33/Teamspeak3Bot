package de.backxtar.commands.info;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.Config;
import de.backxtar.gw2.CallBosses;
import de.backxtar.gw2.CallToken;
import de.backxtar.managers.CommandInterface;

import java.util.ArrayList;
import java.util.List;

public class BossesCommand implements CommandInterface {
    private final String[] bosses = {"vale_guardian", "gorseval", "sabetha", "slothasor", "bandit_trio", "matthias", "keep_construct", "xera", "cairn", "mursaat_overseer", "samarog", "deimos", "soulless_horror", "statues_of_grenth", "voice_in_the_void", "conjured_amalgamate", "twin_largos", "qadim", "adina", "sabir", "qadim_the_peerless"};
    private final String[] events = {"spirit_woods", "escort", "twisted_castle", "river_of_souls", "gate"};
    private final String[] bossTranslations = {"vale_guardian", "gorseval", "sabetha", "slothasor", "bandit_trio", "matthias", "keep_construct", "xera", "cairn", "mursaat_overseer", "samarog", "deimos", "soulless_horror", "statues_of_grenth", "voice_in_the_void", "conjured_amalgamate", "twin_largos", "qadim", "adina", "sabir", "qadim_the_peerless"};
    private final String[] eventTranslations = {"spirit_woods", "escort", "twisted_castle", "river_of_souls", "gate"};
    private Config.Colors colors = Config.getColors();

    @Override
    public void run(String[] cmdValues, TS3Api api, TextMessageEvent event, Client client) {
        String[] gw2Values = CallToken.isValid(client);
        if (gw2Values == null) return;

        List<String> raidBosses = CallBosses.getRaidBosses(gw2Values[0], client);

        if (raidBosses == null) return;
        int[] counters = {21, 5};
        String[] conditions = new String[8];
        List<String> raidBoss = new ArrayList<>();
        List<String> raidEvent = new ArrayList<>();

        for (int i = 0; i < 21; i++) {
            if (raidBosses.contains(bosses[i])) {
                raidBoss.add(i, "[color=green]✔[/color] " + bossTranslations[i]);
                counters[0]--;
            } else raidBoss.add(i, "[color=red]✘[/color] " + bossTranslations[i]);
        }

        for (int i = 0; i < 5; i++) {
            if (raidBosses.contains(events[i])) {
                raidEvent.add(i, "[color=green]✔[/color] " + eventTranslations[i]);
                counters[1]--;
            } else raidEvent.add(i, "[color=red]✘[/color] " + eventTranslations[i]);
        }

        conditions[0] = (raidBosses.contains(bosses[0]) && raidBosses.contains(events[0])
                && raidBosses.contains(bosses[1]) && raidBosses.contains(bosses[2])) ?
                "[color=green]✔[/color] Spirit Vale:" :
                "[color=red]✘[/color] Spirit Vale:";
        conditions[1] = (raidBosses.contains(bosses[3]) && raidBosses.contains(bosses[4])
                && raidBosses.contains(bosses[5])) ?
                "[color=green]✔[/color] Salvation Pass:" :
                "[color=red]✘[/color] Salvation Pass:";
        conditions[2] = (raidBosses.contains(events[1]) && raidBosses.contains(bosses[6])
                && raidBosses.contains(events[2]) && raidBosses.contains(bosses[7])) ?
                "[color=green]✔[/color] Stronghold of the Faithful:" :
                "[color=red]✘[/color] Stronghold of the Faithful:";
        conditions[3] = (raidBosses.contains(bosses[8]) && raidBosses.contains(bosses[9])
                && raidBosses.contains(bosses[10]) && raidBosses.contains(bosses[11])) ?
                "[color=green]✔[/color] Bastion of the Penitent:" :
                "[color=red]✘[/color] Bastion of the Penitent:";
        conditions[4] = (raidBosses.contains(bosses[12]) && raidBosses.contains(events[3])
                && raidBosses.contains(bosses[13]) && raidBosses.contains(bosses[14])) ?
                "[color=green]✔[/color] Hall of Chains:" :
                "[color=red]✘[/color] Hall of Chains:";
        conditions[5] = (raidBosses.contains(bosses[15]) && raidBosses.contains(bosses[16])
                && raidBosses.contains(bosses[17])) ?
                "[color=green]✔[/color] Mythwright Gambit:" :
                "[color=red]✘[/color] Mythwright Gambit:";
        conditions[6] = (raidBosses.contains(events[4]) && raidBosses.contains(bosses[18])
                && raidBosses.contains(bosses[19]) && raidBosses.contains(bosses[20])) ?
                "[color=green]✔[/color] The Key of Ahdashim:" :
                "[color=red]✘[/color] The Key of Ahdashim:";

        boolean singleBoss = counters[0] <= 1;
        boolean singleEvent = counters[1] <= 1;
        
        if (counters[0] != 0 && counters[1] != 0) {
            conditions[7] = "[b]" + client.getNickname() + "[/b], Dir " + (singleBoss ? "missing" : "miss") + " " +
                    "[b][color=" + colors.mainColor + "]" + counters[0] + " " + (singleBoss ? "Boss" : "Bosses") + "[/color][/b] " +
                    "und [b][color=" + colors.mainColor + "]" + counters[1] + " " + (singleEvent ? "Event" : "Events") + "[/color][/b] für die Woche.";
        } else if (counters[0] != 0 && counters[1] == 0) {
            conditions[7] = "[b]" + client.getNickname() + "[/b], Dir " + (singleBoss ? "missing" : "miss") + " " +
                    "[b][color=" + colors.mainColor + "]" + counters[0] + " " + (singleBoss ? "Boss" : "Bosses") + "[/color][/b] " +
                    "für die Woche.";
        } else if (counters[0] == 0 && counters[1] != 0) {
            conditions[7] = "[b]" + client.getNickname() + "[/b], Dir " + (singleEvent ? "missing" : "miss") + " " +
                    "[b][color=" + colors.mainColor + "]" + counters[1] + " " + (singleEvent ? "Event" : "Events") + "[/color][/b] " +
                    "für die Woche.";
        } else if (counters[0] == 0 && counters[1] == 0) {
            conditions[7] = "[b]" + client.getNickname() + "[/b], You have a [b][color=" + colors.mainColor + "]Fullclear[/color][/b]!";
        }
        api.sendPrivateMessage(client.getId(), buildMessage(gw2Values[1], conditions, raidBoss, raidEvent));
    }

    private String buildMessage(String accountName, String[] conditions, List<String> raidBoss, List<String> raidEvent) {
        return "\nRaid summary for [b]" + accountName + "[/b]:" +
                "\n" + conditions[7] +
                "\n\n" + conditions[0] + "\n╰ " + raidBoss.get(0) + "\n╰ " + raidEvent.get(0) + "\n╰ " + raidBoss.get(1) + "\n╰ " + raidBoss.get(2) +
                "\n\n" + conditions[1] + "\n╰ " + raidBoss.get(3) + "\n╰ " + raidBoss.get(4) + "\n╰ " + raidBoss.get(5) +
                "\n\n" + conditions[2] + "\n╰ " + raidEvent.get(1) + "\n╰ " + raidBoss.get(6) + "\n╰ " + raidEvent.get(2) + "\n╰ " + raidBoss.get(7) +
                "\n\n" + conditions[3] + "\n╰ " + raidBoss.get(8) + "\n╰ " + raidBoss.get(9) + "\n╰ " + raidBoss.get(10) + "\n╰ " + raidBoss.get(11) +
                "\n\n" + conditions[4] + "\n╰ " + raidBoss.get(12) + "\n╰ " + raidEvent.get(3) + "\n╰ " + raidBoss.get(13) + "\n╰ " + raidBoss.get(14) +
                "\n\n" + conditions[5] + "\n╰ " + raidBoss.get(15) + "\n╰ " + raidBoss.get(16) + "\n╰ " + raidBoss.get(17) +
                "\n\n" + conditions[6] + "\n╰ " + raidEvent.get(4) + "\n╰ " + raidBoss.get(18) + "\n╰ " + raidBoss.get(19) + "\n╰ " + raidBoss.get(20);
    }
}
