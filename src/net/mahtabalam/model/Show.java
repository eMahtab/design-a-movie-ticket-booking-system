package net.mahtabalam.model;

import java.util.Date;
import java.util.List;

public class Show {
	private  String id;
    private  Movie movie;
    private  Screen screen;
    private  Date startTime;

    public Show(Movie movie, Screen screen, Date startTime) {
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
    }
    
    public String getId() {
    	return id;
    }
    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public Date getStartTime() {
        return startTime;
    }

    public List<Seat> getAvailableSeats() {
        return screen.getAvailableSeats();
    }
}