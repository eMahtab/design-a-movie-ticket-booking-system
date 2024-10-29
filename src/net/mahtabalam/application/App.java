package net.mahtabalam.application;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import net.mahtabalam.model.Movie;
import net.mahtabalam.model.Screen;
import net.mahtabalam.model.Seat;
import net.mahtabalam.model.SeatType;
import net.mahtabalam.model.Show;
import net.mahtabalam.model.Theatre;
import net.mahtabalam.model.User;
import net.mahtabalam.service.BookingService;
import net.mahtabalam.service.PaymentService;

public class App {

	public static void main(String[] args) {
		 // Create sample data
        Theatre theatre = new Theatre("PVR Cinemas");
        Seat seat = new Seat(5, 2, SeatType.GOLD);
        Screen screen1 = new Screen("Screen 1", Arrays.asList(seat));
        Movie movie = new Movie("Inception", 148);
        Show show = new Show(movie, screen1, new Date());
        theatre.addScreen(screen1);

        BookingService bookingService = new BookingService(50000, 3); // 50 seconds lock duration, max retries = 3
        PaymentService paymentService = new PaymentService(bookingService, 3);
        List<Seat> selectedSeats = Arrays.asList(seat);
        
        User user = new User("user123");
        if (bookingService.selectSeatsForBooking(user, show, selectedSeats)) {
            boolean bookingSuccess = paymentService.processPayment(user, show, selectedSeats);
            if (bookingSuccess) {
                System.out.println("Booking confirmed.");
            } else {
                System.out.println("Booking failed after max retries.");
            }
        } else {
            System.out.println("Seats could not be selected.");
        }
	}
	
}
