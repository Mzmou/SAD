package org.example;

public class Starring {
    private final String name;
    private final Date start_filming_date;
    private final Date end_filming_date;
    public Starring(String given_name,Date given_start_filming_date,Date given_end_filming_date){

        if(given_start_filming_date.compareTo(given_end_filming_date)>=0)
            throw new IllegalArgumentException("start date is after end date!");
        this.name=given_name;
        this.start_filming_date=given_start_filming_date;
        this.end_filming_date=given_end_filming_date;
    }
    public String get_name(){return this.name;}
    public Date get_start_filming_date(){return start_filming_date;}
    public Date get_end_filming_date(){return end_filming_date;}
    public boolean Check_overlap(Starring other){
        var other_start_date=other.get_start_filming_date();
        var other_end_date=other.get_end_filming_date();
        return other_start_date.compareTo(this.end_filming_date) <= 0 && other_end_date.compareTo(this.start_filming_date) >= 0;
    }
    public int calculate_membering_days(){
        return this.end_filming_date.calculate_range_days(this.start_filming_date);
    }
    @Override
    public String toString() {
        return "Starring in " + name + " from " + this.start_filming_date + " to " + this.end_filming_date;}



}
