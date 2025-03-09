package org.example;
import java.util.ArrayList;

public class Actor {
    private final String name;
    private  ArrayList<Starring> history;
    public Actor(String given_name){
        this.name=given_name;
        this.history = new ArrayList<>();

    }
    public void add_history(Starring new_history){
        for (Starring current_history : history){
            if(current_history.Check_overlap(new_history)){
                throw new IllegalArgumentException("This role overlaps with an existing one!");

            }

        }
        history.add(new_history);
    }
    public void print_history() {
        System.out.println("History of " + name + ":");
        for (Starring role : history) {
            System.out.println(role);
        }
    }
    public int get_days_played(String movieName) {
        return history.stream()
                .filter(starring -> starring.get_name().equals(movieName))
                .mapToInt(Starring::calculate_membering_days)
                .sum();
    }
    public int get_total_days_played() {
        return history.stream()
                .mapToInt(Starring::calculate_membering_days)
                .sum();
    }
    public boolean check_name_equal(String other_name){
        return this.name.equals(other_name);
    }

}
