package pl.project.oop.java;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Save {
    private World world;
    public Save(World world) {
        this.world = world;
    }

    public void saveWorld(World w, String fileName){
        assert fileName.contains(".txt") : "Wrong fileName";

        try {
            File file = new File(fileName);
            file.createNewFile();
            PrintWriter writer = new PrintWriter(file);
            saveWorldData(w, writer);
            for (Organism organism : w.getOrganisms()) {
                writer.print(organism.getOrganismType() + " ");
                writer.print(organism.getPosition().getX() + " ");
                writer.print(organism.getPosition().getY() + " ");
                writer.print(organism.getStrength() + " ");
                writer.print(organism.getAge() + " ");
                writer.print(organism.getIsDead() + " ");
//                writer.print(organisms.get(i).getColor());
                if (organism.getOrganismType() == Type.OrganismType.HUMAN) {
                    writer.print(" " + w.getHuman().getBurning().getDuration() + " ");
                    writer.print(w.getHuman().getBurning().getCooldown() + " ");
                    writer.print(w.getHuman().getBurning().getIsActive() + " ");
                    writer.print(w.getHuman().getBurning().getIsReadyToActivate());
                }
                writer.println();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void saveWorldData(World w, PrintWriter writer) {
        writer.print(w.getWidth() + " ");
        writer.print(w.getHeight() + " ");
        writer.print(w.getIsHexagonal() + " ");
        writer.print(w.getRound() + " ");
        writer.print(w.getIsHumanAlive() + " ");
        writer.print(w.getIsGameOver() + "\n");
    }

    public World loadWorld(String fileName) {
        assert fileName.contains(".txt") : "Wrong fileName";
        try {
            File file = new File(fileName);

            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            String[] properties = line.split(" ");
            int width = Integer.parseInt(properties[0]);
            int height = Integer.parseInt(properties[1]);
            boolean isHexagonal = Boolean.parseBoolean(properties[2]);
            World w = new World(width, height, null, isHexagonal);
            w.setRound(Integer.parseInt(properties[3]));
            w.setIsHumanAlive(Boolean.parseBoolean(properties[4]));
            w.setIsGameOver(Boolean.parseBoolean(properties[5]));
            w.setHuman(null);

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();

                properties = line.split(" ");
                Type.OrganismType type = Type.OrganismType.valueOf(properties[0]);
                int x = Integer.parseInt(properties[1]);
                int y = Integer.parseInt(properties[2]);

                Organism organism = Organism.createOrganism
                        (type, w, new Point(x, y));
                int sila = Integer.parseInt(properties[3]);
                organism.setStrength(sila);
                int age = Integer.parseInt(properties[4]);
                organism.setAge(age);
                boolean isDead = Boolean.parseBoolean(properties[5]);
                organism.setIsDead(isDead);
//                Color color = Color.getColor(properties[6]);
//                organism.setColor(color);

                if (type == Type.OrganismType.HUMAN) {
                    w.setHuman((Human)organism);
                    int activeTime = Integer.parseInt(properties[7]);
                    w.getHuman().getBurning().setDuration(activeTime);
                    int cooldown = Integer.parseInt(properties[8]);
                    w.getHuman().getBurning().setCooldown(cooldown);
                    boolean isActive = Boolean.parseBoolean(properties[9]);
                    w.getHuman().getBurning().setIsActive(isActive);
                    boolean isReadyToUse = Boolean.parseBoolean(properties[10]);
                    w.getHuman().getBurning().setIsReadyToActivate(isReadyToUse);
                }
                w.addOrganism(organism);
            }
            scanner.close();
            return w;
        } catch (
                IOException e) {
            System.out.println(e);
        }
        return null;
    }

}
