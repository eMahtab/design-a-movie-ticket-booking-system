# Design a movie ticket booking system

**A movie ticket booking site lists all the theaters from different theater chains, e.g. PVR, INOX, Cinepolis etc.** 

Implement a Movie Ticket Booking Application for a Theatre. Refer to the following details:

1. A Theatre has Screens that run Shows for different Movies. Each Show has a particular Movie, start time, duration, and is played in a particular Screen in the theatre. Each Screen has an arrangement of Seats that can be booked by Users.
2. Assume all Users are registered, authenticated, and logged in to the Application.
3. Once a User selects a particular show to book tickets for, a UserBookingSession starts. Within this UserBookingSession, a User will be able to get the Available Seats for the show and select the Seats he wishes to book. It is a ‘good to have’ for the Application to have limits on the number of seats a User can book in a Ticket.
4. Once the user has selected a group of seats, these seats should become TEMPORARILY_UNAVAILABLE to all other Users.
5. The User then proceeds to make payment which can either be SUCCESS or FAILURE.
6. If Payment FAILED, user can retry Payment for a maximum number of times. Beyond maximum retries, the seats are made AVAILABLE.
7. If Payment SUCCEEDS, Ticket or Booking Confirmation is generated and made available to the User. The UserBookingSession is closed and the Seats are made PERMANENTLY_UNAVAILABLE.
8. A User can also explicitly close the UserBookingSession after selecting seats and before making payment. In this case, the seats selected are made AVAILABLE once again.



# Movie
```java
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Movie {

    private final String id;
    private final String name;
    private int durationInMins;
    private String genre;

    //Other metadata
}
```
# CinemaHall
```java
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CinemaHall {

    private final String id;
    private final String name;
    private final List<Screen> screens;
    //Other theatre metadata.

    public CinemaHall(@NonNull final String id, @NonNull final String name) {
        this.id = id;
        this.name = name;
        this.screens = new ArrayList<>();
    }

    public void addScreen(@NonNull final  Screen screen) {
        screens.add(screen);
    }
}
```

# Screen
```java
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import javax.management.MXBean;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Screen {

    private final String id;
    private final String name;
    private final CinemaHall theatre;
    //Other screen metadata.

    private final List<Show> shows;

    public Screen(@NonNull final String id, @NonNull final String name, @NonNull final CinemaHall theatre) {
        this.id = id;
        this.name = name;
        this.theatre = theatre;
        this.seats = new ArrayList<>();
    }

    public void addSeat(@NonNull final Seat seat) {
        this.seats.add(seat);
    }
}
```

# Show
```java
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class Show {
    private final String showId;
    private final Movie movie;
    private final Screen screen;
    private final Date startTime;
    private final Integer durationInSeconds;
}
```

# Seat
```java
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Seat {

    private final String id;
    private final int rowNo;
    private final int seatNo;
    private SeatType seatType; // Silver, Gold, Diamond
}
```

# SeatLock
```java
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
@Getter
public class SeatLock {
    private Seat seat;
    private Show show;
    private Integer timeoutInSeconds;
    private Date lockTime;
    private String lockedBy;

    public boolean isLockExpired() {
        final Instant lockInstant = lockTime.toInstant().plusSeconds(timeoutInSeconds);
        final Instant currentInstant = new Date().toInstant();
        return lockInstant.isBefore(currentInstant);
    }
}
```

# Booking and BookingStatus
```java
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
public class Booking {

    private final String bookingId;
    private final String userId;
    private BookingStatus bookingStatus;
    private final List<Seat> seatsBooked;
    private final Show show;
    
    public Booking(@NonNull final String id, @NonNull final Show show, @NonNull final String user,
                   @NonNull final List<Seat> seatsBooked) {
        this.id = id;
        this.show = show;
        this.seatsBooked = seatsBooked;
        this.user = user;
        this.bookingStatus = BookingStatus.Created;
    }

    public boolean isConfirmed() {
        return this.bookingStatus == BookingStatus.Confirmed;
    }

    public void confirmBooking() {
        if (this.bookingStatus != BookingStatus.Created) {
            throw new InvalidStateException();
        }
        this.bookingStatus = BookingStatus.Confirmed;
    }

    public void expireBooking() {
        if (this.bookingStatus != BookingStatus.Created) {
            throw new InvalidStateException();
        }
        this.bookingStatus = BookingStatus.Expired;
    }
}

public enum BookingStatus {
	REQUESTED, PENDING, CONFIRMED, CANCELLED;
}
```



# References :
1. https://github.com/anomaly2104/ticket-booking-low-level-system-design
2. https://www.youtube.com/watch?v=Xny0IdvJ-1M
3. https://www.youtube.com/watch?v=7LaKmNfMCAo
