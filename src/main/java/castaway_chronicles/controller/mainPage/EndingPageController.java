package castaway_chronicles.controller.mainPage;

import castaway_chronicles.Application;
import castaway_chronicles.controller.ControllerState;
import castaway_chronicles.model.Ending;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.elements.Interactable;
import castaway_chronicles.model.mainPage.MainPage;
import castaway_chronicles.states.EndState;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class EndingPageController implements ControllerState {
    private final MainPageController mainPageController;
    public EndingPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    @Override
    public void key(int key, Application application) throws IOException, URISyntaxException, InterruptedException {
        if(key == KeyEvent.VK_ESCAPE){
            mainPageController.getModel().setCurrent(MainPage.PAGE.MENU);
            mainPageController.setCurrent(mainPageController.getMainMenuController());
        }
    }
    @Override
    public void click(Position position, Application application) throws IOException, InterruptedException, URISyntaxException {
        for (Interactable e: mainPageController.getModel().getEndingPage().getVisibleInteractables()) {
            if (e.getName().equalsIgnoreCase("reset") && e.contains(position)){
                File achievedEnd = new File(Paths.get("").toAbsolutePath()+"/src/main/resources/achieved_endings.txt");
                achievedEnd.delete();
                mainPageController.getModel().getEndingPage().reset();
                break;
            }
            else if (!e.getName().contains("question") && e.contains(position)) {
                application.setState(new EndState(new Ending(e.getName())));
            }
        }
    }

    @Override
    public void none(long time) throws IOException, InterruptedException, URISyntaxException {

    }
}
