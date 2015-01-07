package com.mycompany.data.game;

import java.util.ArrayList;
import java.util.Random;

import com.mycompany.data.exceptions.AlreadyHitException;
import com.mycompany.data.exceptions.ShipIsKilledException;
import com.mycompany.data.game.ships.Ship;

class NodeDescriptor
{
    public NodeDescriptor(int[] coordinates, Directions direction)
    {
        this.coordinates = coordinates;
        this.direction = direction;
    }

    public int[] coordinates = new int[2];
    Directions direction = Directions.None;
}

public class Bot extends LocalPlayer
{

    public Bot(GameRules rules)
    {
        super(rules);
    }

    public void setShips(Grid field)
    {
        Random rand = new Random();
        ArrayList<NodeDescriptor> possibleShipLocations;
        int num;
        int[] coordinates;
        Ship ship;

        while (true)
        {
            if ((ship = getShip()) == null) {
                return;
            }
            possibleShipLocations = analyzePossibleShipLocations(ship, field);
            num = rand.nextInt(possibleShipLocations.size());
            coordinates = possibleShipLocations.get(num).coordinates;
            field.setShip(ship, coordinates[0], coordinates[1], possibleShipLocations.get(num).direction);
        }
    }

    public boolean hit(Grid enemyField) throws ShipIsKilledException
    {
        Random rand = new Random();
        ArrayList<NodeDescriptor> possibleHitLocations = analyzePossibleHitLocations(enemyField);
        int num = rand.nextInt(possibleHitLocations.size());
        int[] coordinates;

        try {
            coordinates = possibleHitLocations.get(num).coordinates;
            return enemyField.hit(coordinates[0], coordinates[1]);
        } catch (AlreadyHitException e) {
            return false;
        }
    }

    private ArrayList<NodeDescriptor> analyzePossibleShipLocations(Ship ship, Grid field)
    {
        ArrayList<NodeDescriptor> result = new ArrayList<>();

        for (int i = 0; i < field.getSizeVertical(); i++) {
            for (int j = 0; j < field.getSizeHorizontal(); j++) {
                for (Directions d : Directions.values()) {
                    if (field.tryToSetShip(ship, j, i, d))
                    {
                        int[] coordinates = { j, i };
                        result.add(new NodeDescriptor(coordinates, d));
                    }
                }
            }
        }
        return result;
    }

    private ArrayList<NodeDescriptor> analyzePossibleHitLocations(Grid enemyField)
    {
        ArrayList<NodeDescriptor> result = new ArrayList<>();

        for (int i = 0; i < enemyField.getSizeVertical(); i++) {
            for (int j = 0; j < enemyField.getSizeHorizontal(); j++) {
                if (enemyField.tryToHit(j, i)) {
                    int[] coordinates = { j, i };
                    result.add(new NodeDescriptor(coordinates, Directions.None));
                }
            }
        }
        return result;
    }
}
