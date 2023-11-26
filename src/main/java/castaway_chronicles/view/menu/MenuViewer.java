package castaway_chronicles.view.menu;

import castaway_chronicles.gui.GUI;
import castaway_chronicles.model.Position;
import castaway_chronicles.model.menu.Menu;
import castaway_chronicles.view.Images;
import castaway_chronicles.view.Viewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class MenuViewer extends Viewer<Menu> {

    protected MenuViewer(Menu model, Images images) {
        super(model, images);
    }

    @Override
    protected void drawElements(GUI gui) throws IOException, InterruptedException, URISyntaxException {
        gui.drawImage(new Position(0,0), getImages().getImage("Menu"));
        int i = 50;
        for (String entry: getModel().getEntries()) {
            gui.drawText(new Position(20, i), 160, entry);
        }
        gui.drawLine(new Position(10, getModel().getCurrentEntry()*10+50)); //check position in image
    }
}