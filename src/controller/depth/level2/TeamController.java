package controller.depth.level2;

import lombok.Getter;
import controller.CustomController;
import util.ScanUtil;
import util.View;

public class TeamController implements CustomController {
    @Getter
    private final String name;

    public TeamController(String name) {
        this.name = name;
    }

    @Override
    public String handle() {
        View.printTeam();
        int next= ScanUtil.readInt();
        switch (next){
            case 1:
                return "player";
            case 2:
                return "management";
            case 3:
                return "game";
            case 0:
                return "enter";
            default:
                View.printValidNum();
                return "team";
        }
    }
}
