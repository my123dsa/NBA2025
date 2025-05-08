package controller.depth.level3;

import controller.CustomController;
import controller.MainController;
import domain.Player;
import domain.Stats;
import domain.Team;
import lombok.Getter;
import service.PlayerService;
import service.TeamService;
import util.ScanUtil;
import util.View;

import java.util.List;

public class PlayerController implements CustomController {
    @Getter
    private final String name;
    private final PlayerService playerService;
    private final TeamService teamService;

    public PlayerController(String name, PlayerService playerService, TeamService teamService) {
        this.name = name;
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @Override
    public String handle() {
        View.printPlayer();
        int next = ScanUtil.readInt();
        switch (next) {
            case 1:
                return getPlayerList();
            case 2:
                return getPlayerStats();
            case 0:
                return "team";
            default:
                View.printValidNum();
                return "player";
        }
    }

    private String getPlayerList() {
        Team team = teamService.findTeamById(MainController.getOner().getTeamId());
        List<Player> playerList = playerService.findAllPlayerByTeam(team);
        View.printPlayerList(playerList);
        return "player";
    }

    private String getPlayerStats() {
        View.printGetStatsList();

        String playerName = ScanUtil.readLine().trim();
        Stats stats = playerService.getStats(playerName);
        if(stats != null)
            View.printPlayerStats(stats);
        return "player";
    }
}
