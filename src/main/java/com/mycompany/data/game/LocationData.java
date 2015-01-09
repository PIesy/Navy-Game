package com.mycompany.data.game;

public class LocationData
{
    public LocationData(int[] coordinates, Directions direction)
    {
        this.coordinates = coordinates;
        this.direction = direction;
    }
    
    public int[] coordinates = new int[2];
    public Directions direction = Directions.None;
}