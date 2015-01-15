package com.mycompany.data.game;

import com.mycompany.data.game.ships.ShipBuilder.ShipType;

public class LocationData
{
    
    public LocationData(){}
    
    public LocationData(int[] coordinates, Directions direction)
    {
        this.coordinates = coordinates;
        this.direction = direction;
    }
    
    public int[] coordinates = new int[2];
    public Directions direction = null;
    public ShipType shipType = null;
}