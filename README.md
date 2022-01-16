# Design a movie ticket booking system

Implement a Movie Ticket Booking Application for a Theatre. Refer to the following details:

1. A Theatre has Screens that run Shows for different Movies. Each Show has a particular Movie, start time, duration, and is played in a particular Screen in the theatre. Each Screen has an arrangement of Seats that can be booked by Users.
2. Assume all Users are registered, authenticated, and logged in to the Application.
3. Once a User selects a particular show to book tickets for, a UserBookingSession starts. Within this UserBookingSession, a User will be able to get the Available Seats for the show and select the Seats he wishes to book. It is a ‘good to have’ for the Application to have limits on the number of seats a User can book in a Ticket.
4. Once the user has selected a group of seats, these seats should become TEMPORARILY_UNAVAILABLE to all other Users.
5. The User then proceeds to make payment which can either be SUCCESS or FAILURE.
6. If Payment FAILED, user can retry Payment for a maximum number of times. Beyond maximum retries, the seats are made AVAILABLE.
7. If Payment SUCCEEDS, Ticket or Booking Confirmation is generated and made available to the User. The UserBookingSession is closed and the Seats are made PERMANENTLY_UNAVAILABLE.
8. A User can also explicitly close the UserBookingSession after selecting seats and before making payment. In this case, the seats selected are made AVAILABLE once again.



