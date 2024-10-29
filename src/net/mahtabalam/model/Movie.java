package net.mahtabalam.model;

public class Movie {
	private  String id;
    private  String title;
    private  int duration; // in minutes
    private  String genre;

    public Movie(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }
    
    public Movie(String title, int duration, String genre) {
        this.title = title;
        this.duration = duration;
        this.genre = genre;
    }
    
    public Movie(String id, String title, int duration, String genre) {
    	this.id = id;
        this.title = title;
        this.duration = duration;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
		return id;
	}

	public String getGenre() {
		return genre;
	}

	public int getDuration() {
        return duration;
    }
}