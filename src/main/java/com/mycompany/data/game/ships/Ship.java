package com.mycompany.data.game.ships;

import com.mycompany.data.exceptions.ShipIsKilledException;
import com.mycompany.data.game.ships.ShipBuilder.ShipType;

public class Ship
{

    public Ship()
    {
        size = 0;
        shipType = ShipType.Boat;
        hitPoints = 0;
    }

    public Ship(ShipType shipType, int size)
    {
        this.shipType = shipType;
        this.size = size;
        hitPoints = size;
    }

    public ShipType getType()
    {
        return shipType;
    }

    public int getSize()
    {
        return size;
    }

    public void hit() throws ShipIsKilledException
    {
        hitPoints--;
        if (hitPoints == 0) {
            throw new ShipIsKilledException(shipType.name());
        }
    }

    private final int size;
    private final ShipType shipType;
    private int hitPoints;
}
