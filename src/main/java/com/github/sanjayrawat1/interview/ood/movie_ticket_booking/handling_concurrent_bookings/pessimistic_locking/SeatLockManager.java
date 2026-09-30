package com.github.sanjayrawat1.interview.ood.movie_ticket_booking.handling_concurrent_bookings.pessimistic_locking;

import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.location.Seat;
import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.showing.Screening;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author sanjayrawat1
 */
public class SeatLockManager {

    private final Map<String, SeatLock> lockedSeats = new ConcurrentHashMap<>();

    private final Duration lockDuration;

    public SeatLockManager(Duration lockDuration) {
        this.lockDuration = lockDuration;
    }

    public synchronized boolean lockSeat(Screening screening, Seat seat, String userId) {
        String lockKey = generateLockKey(screening, seat);

        // Clean up lock if expired (on-demand cleanup when another process attempts to lock)
        cleanupLockIfExpired(lockKey);
        // Check if a seat is already locked
        if (isLocked(screening, seat)) {
            return false;
        }

        // Create a new lock with expiration time
        SeatLock lock = new SeatLock(userId, LocalDateTime.now().plus(lockDuration));
        lockedSeats.put(lockKey, lock);
        return true;
    }

    public synchronized SeatLock getLock(Screening screening, Seat seat) {
        String lockKey = generateLockKey(screening, seat);
        // clean up before returning
        cleanupLockIfExpired(lockKey);
        return lockedSeats.get(lockKey);
    }

    public synchronized boolean isLocked(Screening screening, Seat seat) {
        return getLock(screening, seat) != null;
    }

    // Method to unlock a seat, verifying the userId
    public synchronized boolean unlockSeat(Screening screening, Seat seat, String userId) {
        String lockKey = generateLockKey(screening, seat);
        SeatLock lock = lockedSeats.get(lockKey);

        if (lock != null && lock.isExpired() && lock.userId().equals(userId)) {
            lockedSeats.remove(lockKey);
            return true;
        }

        // If lock is null, expired, or locked by a different user, we can't unlock
        // (but an expired lock might have been cleaned up already)
        return false;
    }

    private void cleanupLockIfExpired(String lockKey) {
        lockedSeats.computeIfPresent(lockKey, (key, existingLock) -> {
            if (existingLock.isExpired()) {
                // Return null to remove the entry
                return null;
            }
            return existingLock;
        });
    }

    private String generateLockKey(Screening screening, Seat seat) {
        return screening.id() + "-" + seat.getSeatNumber();
    }

    public record SeatLock(
            String userId,
            LocalDateTime expirationTime
    ) {

        public boolean isExpired() {
            return LocalDateTime.now().isAfter(expirationTime);
        }
    }
}
