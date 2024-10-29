package net.mahtabalam.service;

import java.util.List;

import net.mahtabalam.booking.SeatLockManager;
import net.mahtabalam.model.Seat;
import net.mahtabalam.model.SeatStatus;
import net.mahtabalam.model.Show;
import net.mahtabalam.model.User;

public class BookingService {
    private  SeatLockManager seatLockManager;
    private  int maxRetries;

    public BookingService(long lockDuration, int maxRetries) {
        this.seatLockManager = new SeatLockManager(lockDuration);
        this.maxRetries = maxRetries;
    }

    public boolean selectSeatsForBooking(User user, Show show, List<Seat> seats) {
        for (Seat seat : seats) {
            if (!seatLockManager.lockSeat(seat, user.getUserId())) {
                System.out.println("Seat " + seat.getRowNumber() + "-" + seat.getSeatNumber() + " is already locked.");
                return false;
            }
        }
        return true;
    }

    public boolean confirmBooking(User user, Show show, List<Seat> seats) {
        for (Seat seat : seats) {
            seat.setSeatStatus(SeatStatus.UNAVAILABLE);
            seatLockManager.unlockSeat(seat);
        }
        System.out.println("Booking confirmed for user: " + user.getUserId());
        return true;
    }

    public void cancelBooking(User user, Show show, List<Seat> seats) {
        for (Seat seat : seats) {
            seatLockManager.unlockSeat(seat);
        }
        System.out.println("Booking cancelled and seats released for user: " + user.getUserId());
    }
}
