package com.mycompany.data.game;

import java.util.Deque;
import java.util.LinkedList;

import com.mycompany.data.exceptions.GameOverException;
import com.mycompany.data.game.ships.Ship;
import com.mycompany.data.game.ships.ShipBuilder;
import com.mycompany.data.game.ships.ShipBuilder.ShipType;

public class LocalPlayer
{
    public LocalPlayer()
    {
        this(new GameRules());
    }

    public LocalPlayer(GameRules rules)
    {
        buildShips(rules.getBoatsCount(), ShipType.Boat);
        buildShips(rules.getSchoonersCount(), ShipType.Schooner);
        buildShips(rules.getDestroyersCount(), ShipType.Destroyer);
        buildShips(rules.getCarriersCount(), ShipType.Carrier);
        remainingShips = ships.size();
        unsetShipCount = remainingShips;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public Ship getShip()
    {
        Ship ship = ships.pollLast();

        return ship;
    }
    
    public int getRemainingShipsCount()
    {
        return remainingShips;
    }

    public void destroyShip() throws GameOverException
    {
        remainingShips--;
        if (remainingShips == 0) {
            throw new GameOverException();
        }
    }

    public void unsetShip()
    {
        if (lastSetShip != null)
        {
            ships.addLast(lastSetShip);
            lastSetShip = null;
            unsetShipCount++;
        }
    }

    private void buildShips(int count, ShipType type)
    {
        for (int i = 0; i < count; i++) {
            ships.add(ShipBuilder.buildShip(type));
        }
    }

    protected Ship lastSetShip = null;
    protected int remainingShips = 0;
    protected int unsetShipCount = 0;
    protected Deque<Ship> ships = new LinkedList<>();
    protected String name = "";
}