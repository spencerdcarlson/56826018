package com.hnb;

import java.io.InputStream;
import java.util.*;

public class FileParser {
    private Map<String, Item> animals = new HashMap<>();
    private Map<String, Item> habitats = new HashMap<>();
    private Map<String, Map> options = new HashMap<>();

    /**
     * options
     *  "Habitats" => <MAP_OF_HABITATS>
     *  "Animals" => <MAP_OF_ANIMALS>
     *  ...etc
     *
     * <MAP_OF_HABITATS>
     *  "penguin" => <ITEM>
     *  "bird" => <ITEM>
     *  ...etc
     *
     * <ITEM>
     *     [<ATTRIBUTE>]
     *
     * <ATTRIBUTE>
     *     isAsterisk => true
     *     key => NAME
     *     value => Maj
     *
     */

    public FileParser() {
        createItems("animals.txt", this.animals);
        createItems("habitats.txt", this.habitats);
        addItemDetails("animals.txt", this.animals);
        addItemDetails("habitats.txt", this.habitats);
        this.options.put("Animals", animals);
        this.options.put("Habitats", habitats);
    }

    public Map getOptions() {
        return new HashMap(this.options);
    }

    private void createItems(final String filename, Map<String, Item> list) {
        final InputStream is = FileParser.class.getClassLoader().getResourceAsStream(filename);
        final Scanner scanner = new Scanner(is);
        while(scanner.hasNext()) {
            final String line = scanner.nextLine();
            if (line.contains("Details")) {
                final String type = line.split(" on ")[1];
                list.put(type, new Item());
            }
        }
    }

    private void addItemDetails(final String filename, Map<String, Item> items) {
        final InputStream is = FileParser.class.getClassLoader().getResourceAsStream(filename);
        final Scanner scanner = new Scanner(is);
        Item current = null;
        while (scanner.hasNext()){
            final String line = scanner.nextLine();
            if(line.contains("Animal - ")){
                // Starting animal details. Which animal is it?
                final String animal = line.split("Animal - ")[1];
                for (String type: items.keySet()) {
                    if(type.startsWith(animal.toLowerCase())) {
                        current = items.get(type);
                        current.setType(ItemType.ANIMAL);
                    }
                }
            }

            if(line.contains("Habitat - ")){
                // Starting habitat details. Which habitat is it?
                final String habitat = line.split("Habitat - ")[1];
                for (String type: items.keySet()) {
                    if(type.startsWith(habitat.toLowerCase())) {
                        current = items.get(type);
                        current.setType(ItemType.HABITAT);
                    }
                }
            }

            // NOTE if we do not care about types, ie age being an integer vs a string
            // the below code could be a lot more simple

            // Item attributes
            if(current != null && line.contains(AttributeType.NAME.getLabel())) {
                final String name = line.split(": ")[1];
                current.addAttribute(new Attribute<String>(AttributeType.NAME, name));
            }
            if(current != null && line.contains(AttributeType.AGE.getLabel())){
                final int age = Integer.parseInt(line.split(": ")[1]);
                current.addAttribute(new Attribute<Integer>(AttributeType.AGE, age));
            }
            if(current != null && line.contains(AttributeType.HEAlTH_CONCERNS.getLabel())){
                final String concerns = line.split(": ")[1];
                current.addAttribute(new Attribute<String>(AttributeType.HEAlTH_CONCERNS, concerns));
            }
            if(current != null && line.contains(AttributeType.FEEDING_SCHEDULE.getLabel())){
                final String schedule = line.split(": ")[1];
                current.addAttribute(new Attribute<String>(AttributeType.FEEDING_SCHEDULE, schedule));
            }
            // Habitat attributes
            if(current != null && line.contains(AttributeType.TEMPERATURE.getLabel())){
                final String temperature = line.split(": ")[1];
                current.addAttribute(new Attribute<String>(AttributeType.TEMPERATURE, temperature));
            }
            if(current != null && line.contains(AttributeType.FOOD_SOURCE.getLabel())){
                final String temperature = line.split(": ")[1];
                current.addAttribute(new Attribute<String>(AttributeType.FOOD_SOURCE, temperature));
            }
            if(current != null && line.contains(AttributeType.CLEANLINESS.getLabel())){
                final String cleanliness = line.split(": ")[1];
                current.addAttribute(new Attribute<String>(AttributeType.CLEANLINESS, cleanliness));
            }
            // Shared attributes
            if(current != null && line.contains("*****")) {
                final String key = line.split("\\*\\*\\*\\*\\*")[1].split(" ")[0];
                current.setAsterisk(AttributeType.getByLabel(key));
            }

        }
    }
}
