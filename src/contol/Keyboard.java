package contol;

import window.Window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case '.':
                Window.KEY_BUFFER = 8;

                break;

            case ',':
                Window.KEY_BUFFER = 9;

                break;

            case 's':
                Window.KEY_BUFFER = 10;

                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'q':
                Window.KEY_BUFFER = 1;

                break;

            case ']':
                Window.KEY_BUFFER = 2;

                break;

            case '[':
                Window.KEY_BUFFER = 3;

                break;

            case 'i':
                Window.KEY_BUFFER = 4;

                break;

            case '1':
                Window.KEY_BUFFER = 5;

                break;

            case '2':
                Window.KEY_BUFFER = 6;

                break;

            case '3':
                Window.KEY_BUFFER = 7;

                break;

            case 's':
                Window.KEY_BUFFER = 10;

                break;

            case ';':
                Window.KEY_BUFFER = 11;

                break;

            case '\'':
                Window.KEY_BUFFER = 12;

                break;

            case 't':
                Window.KEY_BUFFER = 13;

                break;

            case 'l':
                Window.KEY_BUFFER = 14;

                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
