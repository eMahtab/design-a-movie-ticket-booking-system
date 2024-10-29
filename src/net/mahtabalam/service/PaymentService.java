package net.mahtabalam.service;

import java.util.List;
import java.util.Random;

import net.mahtabalam.model.Seat;
import net.mahtabalam.model.Show;
import net.mahtabalam.model.User;

public class PaymentService {
    private final BookingService bookingService;
    private final int maxRetries;

    public PaymentService(BookingService bookingService, int maxRetries) {
        this.bookingService = bookingService;
        this.maxRetries = maxRetries;
    }

    public boolean processPayment(User user, Show show, List<Seat> seats) {
        int retryCount = 0;

        while (retryCount < maxRetries) {
            boolean paymentSuccess = attemptPayment(user, seats);

            if (paymentSuccess) {
                // On successful payment, confirm booking and make seats permanently unavailable
                bookingService.confirmBooking(user, show, seats);
                System.out.println("Payment successful. Booking confirmed for user: " + user.getUserId());
                return true;
            } else {
                retryCount++;
                System.out.println("Payment failed. Retry attempt " + retryCount + " for user: " + user.getUserId());
            }
        }

        // If retries are exhausted, release seats
        System.out.println("Maximum retries reached. Releasing seats for user: " + user.getUserId());
        bookingService.cancelBooking(user, show, seats);
        return false;
    }

    private boolean attemptPayment(User user, List<Seat> seats) {
        // Mock payment processing logic
        // Here, we simulate a payment attempt, which would normally involve external payment APIs
        Random random = new Random();
        return random.nextBoolean(); // Simulates a 50% chance of payment success
    }
}
