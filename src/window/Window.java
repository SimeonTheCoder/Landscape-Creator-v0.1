package window;

import contol.Handler;
import contol.Keyboard;
import contol.Mouse;
import generator.Generator;
import kernels.BlurPass;
import kernels.ShadowPass;
import landscape.Landscape;
import math.Convert;
import math.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Window extends JPanel {
    public Color fog;

    public Color[] colors = {
            Color.RED,
            Color.GREEN,
            Color.PINK,
            Color.YELLOW,
            Color.BLUE,
            Color.CYAN,
            Color.MAGENTA
    };

    public Color[] in_colors = {
            Color.RED,
            Color.GREEN,
            Color.PINK,
            Color.YELLOW,
            Color.BLUE,
            Color.CYAN,
            Color.MAGENTA
    };

    public String[] substances = {
            "GRASS",
            "ROCK",
            "WATER",
            "SAND"
    };

    public boolean[] useFog = {
            true,
            true,
            true,
            true
    };

    public Landscape landscape;
    public BufferedImage[] textures;

    public static int KEY_BUFFER = 0;
    public int SAMPLE_DENSITY = 10;
    public int VIEW_MODE = 1;
    public int CURR_SUBSTANCE = 0;
    public int BRUSH_SIZE = 50;

    public double CURSOR_DEPTH = 20;
    public Boolean SHADOWS = true;

    public BufferedImage lastFrame = null;

    private JFrame frame;

    public Window(Landscape landscape) {
        this.landscape = landscape;

        frame = new JFrame();

        frame.add(this);

        frame.addKeyListener(new Keyboard());
        frame.addMouseListener(new Mouse(this));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setTitle("Landscape Synthesizer v0.1");

        frame.setSize(1020, 550);
        frame.setResizable(false);

        frame.setVisible(true);

        fog = new Color(0, 0, 0);
    }

    @Override
    public void paint(Graphics gr) {
        SAMPLE_DENSITY = Math.max(1, SAMPLE_DENSITY);
        CURR_SUBSTANCE = CURR_SUBSTANCE % substances.length;

        super.paint(gr);

        BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);

        image.createGraphics();

        Graphics g = image.getGraphics();

        landscape.uv = Generator.generateUv(500, 500, landscape.depthMap);

        for(int i = 0; i < 500; i += SAMPLE_DENSITY) {
            for(int j = 0; j < 500; j += SAMPLE_DENSITY) {
                //g.setColor(new Color((int) landscape.depthMap[i][j], 0, 0));
                Vec2 uv = Convert.to1Vector(landscape.uv[i][j]);

                int x = (int) Math.max(0, Math.min(499, uv.x * 500));
                int y = (int) Math.max(0, Math.min(499, uv.y * 500));

                g.setColor(new Color(textures[landscape.textureMap[i][j]].getRGB(x, y)));

                g.fillRect(j, i, SAMPLE_DENSITY, SAMPLE_DENSITY);
            }
        }

        ShadowPass shadowPass = new ShadowPass();
        BlurPass blurPass = new BlurPass();

        double[][] shadows = shadowPass.pass(landscape.depthMap);

        for(int i = 0; i < 50; i ++) {
            shadows = blurPass.pass(shadows);
        }

        for(int i = 0; i < 500; i += SAMPLE_DENSITY) {
            for(int j = 0; j < 500; j += SAMPLE_DENSITY) {
                //g.setColor(new Color((int) landscape.depthMap[i][j], 0, 0));
                if(VIEW_MODE == 1) {
                    gr.setColor(new Color((int) landscape.depthMap[i][j], 0, 0));

                    gr.fillRect(j, i, SAMPLE_DENSITY, SAMPLE_DENSITY);
                }else if(VIEW_MODE == 2) {
                    gr.setColor(new Color(0, 0, 0, (int) Math.max(0, Math.min(255, shadows[i][j]))));

                    gr.fillRect(j, i, SAMPLE_DENSITY, SAMPLE_DENSITY);
                }else if(VIEW_MODE == 3) {
                    gr.setColor(colors[landscape.textureMap[i][j]]);

                    gr.fillRect(j, i, SAMPLE_DENSITY, SAMPLE_DENSITY);

                    gr.setColor(new Color((int) landscape.depthMap[i][j], 0, 0, 125));

                    gr.fillRect(j, i, SAMPLE_DENSITY, SAMPLE_DENSITY);
                }
            }
        }

        if(SHADOWS) {
            for (int i = 0; i < 500; i += SAMPLE_DENSITY) {
                for (int j = 0; j < 500; j += SAMPLE_DENSITY) {
                    //g.setColor(new Color((int) landscape.depthMap[i][j], 0, 0));
                    g.setColor(new Color(0, 0, 0, (int) Math.max(0, Math.min(255, shadows[i][j]))));

                    g.fillRect(j, i, SAMPLE_DENSITY, SAMPLE_DENSITY);
                }
            }
        }

        gr.setColor(new Color(0, 255, 0, 75));

        gr.fillRect(MouseInfo.getPointerInfo().getLocation().x - frame.getLocation().x, MouseInfo.getPointerInfo().getLocation().y - frame.getLocation().y, BRUSH_SIZE, BRUSH_SIZE);

        for(int i = 0; i < 500; i += SAMPLE_DENSITY) {
            for(int j = 0; j < 500; j += SAMPLE_DENSITY) {
                if(useFog[landscape.textureMap[i][j]]) {
                    g.setColor(new Color(fog.getRed(), fog.getGreen(), fog.getBlue(), (int) landscape.depthMap[i][j]));

                    g.fillRect(j, i, SAMPLE_DENSITY, SAMPLE_DENSITY);
                }
            }
        }

        lastFrame = image;

        gr.setColor(new Color(0, 0, 0));

        gr.drawString(substances[CURR_SUBSTANCE], 10, 10);

        gr.drawImage(image, 500, 0, null);

        Handler.handle(KEY_BUFFER, this, frame);

        if(KEY_BUFFER != 1 && KEY_BUFFER != 15) {
            KEY_BUFFER = 0;
        }

        repaint();
    }
}
