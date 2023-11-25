package castaway_chronicles.view;

import castaway_chronicles.gui.GUI;

import java.io.IOException;

public abstract class Viewer<T> {
    private final T model;

    protected Viewer(T model) {
        this.model = model;
    }
    public T getModel() {return model;}
    public void draw(GUI gui) throws IOException {
        gui.clear();
        drawElements(gui);
        gui.refresh();
    }
    protected abstract void drawElements(GUI gui);
}