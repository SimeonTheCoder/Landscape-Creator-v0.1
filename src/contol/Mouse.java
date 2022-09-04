package contol;

import window.Window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {
    private Window window;

    public Mouse(Window window) {
        this.window = window;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == 1) {
            Window.KEY_BUFFER = 1;
        }else{
            Window.KEY_BUFFER = 15;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Window.KEY_BUFFER = 999;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
