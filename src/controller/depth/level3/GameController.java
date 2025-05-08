package controller.depth.level3;

import controller.CustomController;
import domain.Game;
import dto.QuarterLog;
import dto.RankDTO;
import lombok.Getter;
import service.GameService;
import util.ScanUtil;
import util.View;

import java.util.List;

public class GameController implements CustomController {
    @Getter
    private final String name;
    private final GameService gameService;

    public GameController(String name, GameService gameService) {
        this.name = name;
        this.gameService = gameService;
    }

    @Override
    public String handle() {
        View.printGame();
        int next = ScanUtil.readInt();
        switch (next) {
            case 1:
                return rank();
            case 2:
                return gameList();
            case 3:
                return doGame();
            case 0:
                return "team";
            default:
                View.printValidNum();
                return "game";
        }
    }

    private String rank() {
        List<RankDTO> teams = gameService.getRank();
        View.printGetRank(teams);
        return "game";
    }

    private String gameList() {
        List<Game> gameList = gameService.getGameList();
        View.printGetGameList(gameList);
        return "game";
    }

    private String doGame() {
        List<QuarterLog> logs= gameService.doGame();
        View.printQuarterScore(logs);
        return "game";
    }
}
