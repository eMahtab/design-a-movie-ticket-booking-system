package net.mahtabalam.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.mahtabalam.model.Seat;
import net.mahtabalam.model.SeatStatus;

public class SeatLockManager {
    private final Map<Seat, SeatLock> locks = new ConcurrentHashMap<>();
    private final long lockDuration;

    public SeatLockManager(long lockDuration) {
        this.lockDuration = lockDuration;
        startLockExpiryTask();
    }

    public boolean lockSeat(Seat seat, String userId) {
        synchronized (seat) {
        	// if not already locked OR already locked but now the lock for that seat is expired
            if (!locks.containsKey(seat) || locks.get(seat).isLockExpired()) {
                locks.put(seat, new SeatLock(seat, userId, lockDuration));
                seat.setSeatStatus(SeatStatus.TEMPORARILY_UNAVAILABLE);
                return true;
            }
            return false; // Seat is already locked
        }
    }

    public void unlockSeat(Seat seat) {
        synchronized (seat) {
            locks.remove(seat);
            seat.setSeatStatus(SeatStatus.AVAILABLE);
        }
    }

    public void unlockSeatsLockedByUser(String userId) {
    	// Temporary list to store seats to unlock
        List<Seat> seatsToUnlock = new ArrayList<>();
    	for(Map.Entry<Seat,SeatLock> entry : locks.entrySet()) {
    		Seat seat = entry.getKey();
    		SeatLock seatLock = entry.getValue();
    		if(seatLock.getUserId().equals(userId)) {
    			seat.setSeatStatus(SeatStatus.AVAILABLE);
    			seatsToUnlock.add(seat);
    		}
    	}
    	for (Seat seat : seatsToUnlock) {
            locks.remove(seat);
        }
    }

    private void startLockExpiryTask() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::releaseExpiredLocks, lockDuration, lockDuration, TimeUnit.MILLISECONDS);
    }

    private void releaseExpiredLocks() {
        for (Map.Entry<Seat, SeatLock> entry : locks.entrySet()) {
        	Seat seat = entry.getKey();
            SeatLock lock = entry.getValue();
            if (lock.isLockExpired()) {
                unlockSeat(seat);
            }
        }
    }
}