package controller.depth.level2;

import lombok.Getter;
import controller.CustomController;
import service.PlayerService;
import util.ScanUtil;
import util.View;

public class PlayerEventController implements CustomController {
    @Getter
    private final String name;
    private final PlayerService playerService;

    public PlayerEventController(String name, PlayerService playerService) {
        this.name = name;
        this.playerService = playerService;
    }

    @Override
    public String handle() {
        View.printTeamEvent();
        int next= ScanUtil.readInt();
        switch (next){
            case 1:
                return createPlayer();
            case 2:
                return retirePlayer();
            case 0:
                return "enter";
            default:
                View.printValidNum();
                return "playerEvent";
        }
    }

    private String createPlayer(){
        View.printCreatePlayer();

        String[] data = ScanUtil.readLine().trim().split(",");
        if(data.length != 6) {
            View.printFormat();
            return "playerEvent";
        }
        View.printCreatePlayerStats();

        String[] statsList = ScanUtil.readLine().trim().split(",");
        if(statsList.length != 6) {
            View.printFormat();
            return "playerEvent";
        }

        playerService.createPlayer(data,statsList);

        return "playerEvent";
    }

    private String retirePlayer(){
        View.printRetirePlayer();

        String[] TP=ScanUtil.readLine().trim().split(",");
        if(TP.length != 2) {
            View.printFormat();
            return "playerEvent";
        }

        String result =  playerService.retirePlayer(TP);
        View.printRetirePlayerResult(result);
        return "playerEvent";
    }
}
