package controller.depth.level3;

import controller.CustomController;
import controller.MainController;
import domain.Player;
import domain.Team;
import lombok.Getter;
import service.ManagementService;
import service.PlayerService;
import service.TeamService;
import util.ScanUtil;
import util.View;

import java.util.List;

public class ManagementController implements CustomController {
    @Getter
    private final String name;
    private final ManagementService managementService;
    private final TeamService teamService;
    private final PlayerService playerService;

    public ManagementController(String name, ManagementService managementService, TeamService teamService,PlayerService playerService) {
        this.name = name;
        this.managementService = managementService;
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @Override
    public String handle() {
        View.printManagement();
        int next = ScanUtil.readInt();
        switch (next) {
            case 1:
                return trade();
            case 2:
                return buyPlayer();
            case 3:
                return sellPlayer();
            case 4:
                return changeHead();
            case 0:
                return "team";
            default:
                return "management";
        }
    }

    private String trade() {

        View.printTradeTeamName();

        int otherTeamId = ScanUtil.readInt();

        Team otherTeam = teamService.findTeamById(otherTeamId);
        if (otherTeam == null || otherTeamId ==MainController.getOner().getTeamId()){
            return "management";
        }
        View.printPlayerList(otherTeam.getPlayers());
        int otherPId = ScanUtil.readInt();
        //두개 메소드로 뺄수 있지 않을까?

        int myTeamId = MainController.getOner().getTeamId();

        Team myTeam = teamService.findTeamById(myTeamId);
        View.printPlayerList(myTeam.getPlayers());
        int myPId = ScanUtil.readInt();


        boolean result = managementService.trade(otherTeam, myTeam, otherPId, myPId);
        View.printResult(result);
        return "management";
    }

    private String buyPlayer() {
        List<Player> faPlayerList= playerService.FAPlayerList();
        View.printPlayerList(faPlayerList);

        int playerId = ScanUtil.readInt();

        boolean result= managementService.buyPlayer(faPlayerList,playerId);
        View.printResult(result);
        return "management";
    }

    private String sellPlayer() {
        managementService.sellPlayer();
        return "management";
    }

    private String changeHead() {
        managementService.changeHead();
        return "management";
    }
}
