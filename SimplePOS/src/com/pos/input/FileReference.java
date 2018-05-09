package com.pos.input;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class FileReference {

    public LinkedHashMap<Integer, Item> readItems() {

        LinkedHashMap<Integer, Item> items = new LinkedHashMap();
        ArrayList<String> itemLine = read();

        for (int i = 0; i < itemLine.size(); i++) {
            String[] fields = itemLine.get(i).split(" ");
            int itemId = Integer.parseInt(fields[0]);
            items.put(itemId, new Item(itemLine.get(i)));
        }

        return items;
    }

    public ArrayList<String> read() {
        ArrayList<String> lines = new ArrayList<String>();

        try {
            String line;
            FileReader fileReader = new FileReader("Items.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("File was not found");
        }
        catch(IOException ex) {
            System.out.println("Error reading file");
        }

        return lines;
    }

    public void writeItems(HashMap<Integer, Item> items) {
        try {
            FileWriter fileWriter = new FileWriter("Items.txt");
            BufferedWriter writer = new BufferedWriter(fileWriter);

            for (Item item : items.values()) {
                writer.write(item.getItemId() + " ");
                writer.write(item.getDescription() + " ");
                writer.write(item.getPrice() + " ");
                writer.write(item.getQuantity() + " ");
                writer.write(item.getThreshold() + " ");
                writer.write(item.getSupplier() + " ");
                writer.write(item.getQuantityOrderPlaced() + " ");
                writer.write(item.getPlaceDate() + "\n");
                
            }

            writer.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to items.txt");
        }
    }
}