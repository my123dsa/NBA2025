package config;

import controller.MainController;
import controller.depth.level1.EnterController;
import controller.depth.level2.PlayerEventController;
import controller.depth.level2.TeamController;
import controller.depth.level3.GameController;
import controller.depth.level3.ManagementController;
import controller.depth.level3.PlayerController;
import domain.oner.Oner;
import repository.*;
import service.GameService;
import service.ManagementService;
import service.PlayerService;
import service.TeamService;
import util.ConnectionFactory;

import java.util.ArrayList;

public class AppConfig {
    public MainController initMainController() {
        ConnectionFactory factory= new ConnectionFactory();
        Init init = new Init();
        init.initByDB();
        boolean result = init.check();
        if (!result) init.init();

        Oner oner = init.createOner();

        GameRepository gameRepository = new GameRepository(new ArrayList<>(),factory);
        HeadCoachRepository headCoachRepository = new HeadCoachRepository(result, init.getHeadCoaches(),factory);
        PlayerRepository playerRepository = new PlayerRepository(result, init.getPlayers(),factory);
        TeamRepository teamRepository = new TeamRepository(result, init.getTeams(),factory);
        StatsRepository statsRepository = new StatsRepository(result, init.getStats(),factory);

        PlayerService playerService = new PlayerService(playerRepository, teamRepository);
        GameService gameService = new GameService(teamRepository, gameRepository);
        ManagementService managementService = new ManagementService(teamRepository, playerRepository);
        TeamService teamService = new TeamService(teamRepository);

        PlayerController playerController = new PlayerController("player", playerService, teamService);
        ManagementController managementController = new ManagementController("management", managementService, teamService, playerService);
        GameController gameController = new GameController("game", gameService);

        TeamController teamController = new TeamController("team");
        PlayerEventController playerEventController = new PlayerEventController("playerEvent", playerService);
        EnterController enterController = new EnterController("enter");

        return new MainController(
                oner,
                enterController,
                teamController,
                playerEventController,
                playerController,
                managementController,
                gameController
        );
    }
}