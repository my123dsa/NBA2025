package controller.depth.level1;

import lombok.Getter;
import controller.CustomController;
import util.ScanUtil;
import util.View;

public class EnterController implements CustomController {
    @Getter
    private final String name;

    public EnterController(String name) {
        this.name = name;
    }

    @Override
    public String handle() {
        View.printEnter();
        int next= ScanUtil.readInt();
        switch (next){
            case 1:
                return "team";
            case 2:
                return "playerEvent";
            default:
                View.printValidNum();
                return "enter";
        }
    }
}
