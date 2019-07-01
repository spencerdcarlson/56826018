package com.hnb;

import java.util.Map;
import java.util.Scanner;

public class Menu {
    private int exitSelection;
    private Map<String, ?> options;

    public Menu(Map<String, ?> options) {
        this.options = options;
    }

    private String text() {
        final StringBuilder string = new StringBuilder("Please select what you would like to monitor:").append("\n");
        int i = 1;
        for(final String option: getOptions().keySet()){
            string.append(i).append(".) ").append(option).append("\n");
            i++;
        }
        setExitSelection(i);
        string.append(i).append(".) ").append("Exit").append("\n");
        return string.toString();
    }

    public Selection display() {
        final Scanner scanner = new Scanner(System.in);
        int selection = -1;
        do try {
            System.out.println(text());
            selection = Integer.parseInt(scanner.nextLine());
            return new Selection(SelectionType.VALID, select(selection));
        } catch (NumberFormatException | InvalidOptionException e) {
            if (selection != getExitSelection()) {
                System.out.println("Invalid selection. Please make another selection");
            }
        } while((selection != getExitSelection()));
        return new Selection(SelectionType.EXIT);
    }

    public MenuSelection displaySubMenu() {
        return new MenuSelection(display());
    }

    public Object select(final int i) {
        int j = 1;
        for(final String key: getOptions().keySet()){
            if (i == j) {
                return getOptions().get(key);
            }
            j++;
        }
        throw new InvalidOptionException();
    }

    public Map<String, ?> getOptions() {
        return options;
    }

    public int getExitSelection() {
        return exitSelection;
    }

    public void setExitSelection(int exitSelection) {
        this.exitSelection = exitSelection;
    }
}
