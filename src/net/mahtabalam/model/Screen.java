package net.mahtabalam.model;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private final String screenName;
    private final List<Seat> seats;
    private List<Show> shows;

    public Screen(String screenName) {
        this.screenName = screenName;
        this.seats = new ArrayList<>();
    }
    public Screen(String screenName, List<Seat> seats) {
        this.screenName = screenName;
        this.seats = seats;
    }

    public String getScreenName() {
        return screenName;
    }

    public List<Seat> getSeats() {
        return seats;
    }
    
    public List<Show> getShows() {
		return shows;
	}

    public List<Seat> getAvailableSeats() {
        List<Seat> availableSeats = new ArrayList<>();
        for (Seat seat : seats) {
            if (seat.getSeatStatus().equals(SeatStatus.AVAILABLE)) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }
}