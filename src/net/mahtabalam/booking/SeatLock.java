package net.mahtabalam.booking;

import net.mahtabalam.model.Seat;

class SeatLock {
    private final Seat seat;
    private final String userId; // User who locked the seat
    private final long lockedTime; // Time when the seat was locked in milliseconds
    private final long lockDuration; // Lock duration in milliseconds

    public SeatLock(Seat seat, String userId, long lockDuration) {
        this.seat = seat;
        this.userId = userId;
        this.lockedTime = System.currentTimeMillis();
        this.lockDuration = lockDuration;
    }

    public boolean isLockExpired() {
        return System.currentTimeMillis() - lockedTime >= lockDuration;
    }

    public String getUserId() {
        return userId;
    }

    public Seat getSeat() {
        return seat;
    }
}