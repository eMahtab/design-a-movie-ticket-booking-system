package net.mahtabalam.model;

public class Seat {
	private  String id;
    private  int rowNumber;
    private  int seatNumber;
    private SeatStatus seatStatus; // AVAILABLE, TEMPORARILY_UNAVAILABLE, UNAVAILABLE
    private SeatType seatType; // Silver, Gold, Diamond
    
    public Seat(int rowNumber, int seatNumber, SeatType seatType) {
    	this.rowNumber = rowNumber;
    	this.seatNumber = seatNumber;
    	this.seatType = seatType;
    	this.seatStatus = SeatStatus.AVAILABLE;
    }
	public String getId() {
		return id;
	}
	public int getRowNumber() {
		return rowNumber;
	}
	public SeatStatus getSeatStatus() {
		return seatStatus;
	}
	public int getSeatNumber() {
		return seatNumber;
	}
	public SeatType getSeatType() {
		return seatType;
	}
	
	public void setSeatStatus(SeatStatus seatStatus) {
		this.seatStatus = seatStatus;
	}
}
