package net.mahtabalam.model;

import java.util.ArrayList;
import java.util.List;

public class Theatre {
	private String id;
    private final String name;
    private final List<Screen> screens;
    
    public Theatre(String id, String name) {
    	this.id = id;
        this.name = name;
        this.screens = new ArrayList<>();
    }

    public Theatre(String name) {
        this.name = name;
        this.screens = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Screen> getScreens() {
        return screens;
    }
    
    public String getId() {
    	return id;
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }
}