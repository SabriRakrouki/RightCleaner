package com.example.rightcleaner.helper;

public enum ServiceCategory {
    HOUSE_CLEANING("HouseCleaning"),
    GARDENER("Gardener"),
    ELECTRICIAN("Electrician");
    private final String label;
    private ServiceCategory(String label){
        this.label=label;
    }
    public String toString(){
        return this.label;
    }




}
