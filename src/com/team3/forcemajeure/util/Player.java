package com.team3.forcemajeure.util;

import java.util.*;

    public class Player {

        private String name;
        private String location;
        private int skips = 3;
        private int points;
        private ArrayList<String> inventory = new ArrayList<>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public ArrayList<String> getInventory() {
            return inventory;
        }

        public void setInventory(ArrayList<String> inventory) {
            this.inventory = inventory;
        }

        public int getSkips() {
            return skips;
        }

        public void setSkips(int skips) {
            this.skips = skips;
        }
    }

