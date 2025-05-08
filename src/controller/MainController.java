package controller;

import controller.depth.level1.EnterController;
import controller.depth.level2.PlayerEventController;
import controller.depth.level2.TeamController;
import controller.depth.level3.GameController;
import controller.depth.level3.ManagementController;
import controller.depth.level3.PlayerController;
import domain.oner.Oner;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


public class MainController {
    @Getter
    private static Oner oner;
    private final Map<String, CustomController> controllerMap = new HashMap<>();

    public MainController(Oner oner,
                          EnterController enterController,
                          TeamController teamController,
                          PlayerEventController playerEventController,
                          PlayerController playerController,
                          ManagementController managementController,
                          GameController gameController) {

        // 모든 컨트롤러를 map에 등록
        MainController.oner = oner;
        controllerMap.put(enterController.getName(), enterController);
        controllerMap.put(teamController.getName(), teamController);
        controllerMap.put(playerEventController.getName(), playerEventController);
        controllerMap.put(playerController.getName(), playerController);
        controllerMap.put(managementController.getName(), managementController);
        controllerMap.put(gameController.getName(), gameController);
    }

    public void play(String screen) {
        while (true) {
            CustomController controller = controllerMap.get(screen); // 예: initController.getName()이 "init"이라면
            if (controller != null) {
                screen = controller.handle();
            }
        }
    }
}