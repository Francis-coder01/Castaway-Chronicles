package castaway_chronicles.controller.game;

import castaway_chronicles.Application;
import castaway_chronicles.controller.Controller;
import castaway_chronicles.controller.ControllerState;
import castaway_chronicles.controller.game.Commands.CommandInvoker;
import castaway_chronicles.controller.game.Commands.GenericCommandInvoker;
import castaway_chronicles.controller.game.LocationControllers.*;
import castaway_chronicles.controller.game.scenes.BackpackController;
import castaway_chronicles.controller.game.scenes.LocationController;
import castaway_chronicles.controller.game.scenes.MapController;
import castaway_chronicles.controller.game.scenes.PauseController;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.game.Game;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameController extends Controller<Game> {
    private final ControllerState locationController;
    private final ControllerState backpackController;
    private final ControllerState mapController;
    private final ControllerState dialogController;
    private final ControllerState handController;
    private final ControllerState pauseController;
    private final ControllerState walkingController;
    private ControllerState current;
    private ControllerState previous;
    private final ControllerState narratorController;
    private final GameSaver gameSaver;
    private GenericCommandInvoker commandInvoker;

    public GameController(Game model) {
        super(model);
        gameSaver = new GameSaver(model);
        locationController = new LocationController(this);
        backpackController = new BackpackController(this);
        mapController = new MapController(this);
        dialogController = new DialogController(this);
        handController = new HandController(this);
        pauseController = new PauseController(this);
        walkingController = new WalkingController(this);
        narratorController = new NarratorController(this);
        current = locationController;
        commandInvoker = new CommandInvoker();
    }

    @Override
    public void step(Application application, InputEvent action, long time) throws IOException, InterruptedException, URISyntaxException {
        current.none(time);
        if (action instanceof KeyEvent) {
            current.key(((KeyEvent)action).getKeyCode(), application);
        }
        if (action instanceof MouseEvent) {
            current.click(new Position(((MouseEvent)action).getX()/4, ((MouseEvent)action).getY()/4), application);
        }
    }
    public void setControllerState(ControllerState controllerState) {
        this.previous = this.current;
        this.current = controllerState;
    }

    public ControllerState getPauseController() {
        return pauseController;
    }

    public ControllerState getLocationController() {
        return locationController;
    }
    public ControllerState getBackpackController() {
        return backpackController;
    }

    public ControllerState getMapController() {
        return mapController;
    }

    public ControllerState getDialogController() {
        return dialogController;
    }

    public ControllerState getHandController() {
        return handController;
    }

    public ControllerState getWalkingController() {
        return walkingController;
    }
    public ControllerState getNarratorController() {return narratorController;}
    public ControllerState getCurrent() {
        return current;
    }
    public ControllerState getPrevious() {
        return previous;
    }

    public GenericCommandInvoker getCommandInvoker() {return commandInvoker;}
    public void setCommandInvoker(GenericCommandInvoker commandInvoker) {this.commandInvoker = commandInvoker;}

    public GameSaver getGameSaver() {return gameSaver;}
}
