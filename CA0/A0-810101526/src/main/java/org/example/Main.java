package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Main {
    private static void processCsvLine(String[] nextLine, Map<String, Actor> actorMap) {
        if (nextLine.length < 8) {
            System.out.println("Invalid CSV row: " + String.join(" ", nextLine));
            return;
        }

        String actorName = nextLine[0];

        int len = nextLine.length;
        Date startDate = new Date(
                Integer.parseInt(nextLine[len - 6]),
                Integer.parseInt(nextLine[len - 5]),
                Integer.parseInt(nextLine[len - 4])
        );
        Date endDate = new Date(
                Integer.parseInt(nextLine[len - 3]),
                Integer.parseInt(nextLine[len - 2]),
                Integer.parseInt(nextLine[len - 1])
        );



        String movie = nextLine[1];
        Actor actor = actorMap.getOrDefault(actorName, new Actor(actorName));

        actor.add_history(new Starring(movie, startDate, endDate));

        actorMap.put(actorName, actor);
    }


    public static void main(String[] args) {
        org.example.Date date = new Date(18, 11, 1402);
        System.out.println("Current Date: " + date);
        org.example.Date next_date = date.nextDay();
        System.out.println("next Date: " + next_date);

        String csvFile = "data.csv";
        CSVReader reader = null;
        Map<String, Actor> actors_list = new HashMap<>();
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                processCsvLine(nextLine, actors_list);
            }
            int sum=0;
            for (Map.Entry<String, Actor> entry : actors_list.entrySet()) {
                String key = entry.getKey();

                if(key.equals("James Stewart")){
                    var actor=entry.getValue();
                    System.out.println(actor.get_total_days_played());
                }

            }


        } catch (IOException | CsvValidationException |IllegalArgumentException e ) {
            System.out.println("Failed to read csv " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("Failed to create starring " + e.getMessage());
            }
        }


    }
}