import landscape.Landscape;
import window.Window;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = null;

        try {
            scanner = new Scanner(new File("data/config/labels.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<String> labelList = new ArrayList<>();
        List<Color> colorList = new ArrayList<>();
        List<String> substancesList = new ArrayList<>();
        List<Boolean> useFogList = new ArrayList<>();

        while(scanner.hasNextLine()) {
            String[] content = scanner.nextLine().split(" ");

            labelList.add(content[0]);

            colorList.add(new Color(Integer.parseInt(content[1]),
                    Integer.parseInt(content[2]),
                    Integer.parseInt(content[3])));

            substancesList.add(content[4]);

            useFogList.add(content[5].equals("1"));
        }

        String[] labels = new String[labelList.size()];
        Color[] colors = new Color[colorList.size()];
        String[] substances = new String[substancesList.size()];
        boolean[] useFog = new boolean[useFogList.size()];

        for(int i = 0; i < labels.length; i ++) {
            labels[i] = labelList.get(i);
            colors[i] = colorList.get(i);
            substances[i] = substancesList.get(i);
            useFog[i] = useFogList.get(i);
        }

        Landscape landscape = new Landscape(labels);

        Window window = new Window(landscape);

        window.textures = landscape.textureImages;
        window.colors = colors;
        window.substances = substances;
        window.useFog = useFog;

        Scanner inScanner = null;

        try {
            inScanner = new Scanner(new File("data/config/in_labels.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<Color> inColorsList = new ArrayList<>();

        while(inScanner.hasNextLine()) {
            String[] content = inScanner.nextLine().split(" ");

            inColorsList.add(new Color(Integer.parseInt(content[0]),
                    Integer.parseInt(content[1]),
                    Integer.parseInt(content[2])));
        }

        Color[] inColors = new Color[inColorsList.size()];

        for(int i = 0; i < inColors.length; i ++) {
            inColors[i] = inColorsList.get(i);
        }

        window.in_colors = inColors;

        Scanner fogScanner = null;

        try {
            fogScanner = new Scanner(new File("data/config/fog.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String[] fogContent = fogScanner.nextLine().split(" ");

        window.fog = new Color(Integer.parseInt(fogContent[0]), Integer.parseInt(fogContent[1]), Integer.parseInt(fogContent[2]));
    }
}
