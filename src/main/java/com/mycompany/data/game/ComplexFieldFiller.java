package com.mycompany.data.game;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.mycompany.data.game.ships.Ship;

public class ComplexFieldFiller implements FieldFiller
{

    @Override
    public void fill(Grid field, LocalPlayer player)
    {
        Ship ship;
        Random rand = new Random();

        while (true)
        {
            if ((ship = player.getShip()) == null) {
                return;
            }
            analyzePossibleShipLocations(ship, field);  
            setShip(getOptimalLocation(field, ship, rand), ship, field);
        }
    }
    
    private void setShip(LocationData data, Ship ship, Grid field)
    {
        field.setShip(ship, data.coordinates[0], data.coordinates[1], data.direction);
    }
    
    private LocationData getOptimalLocation(Grid field, Ship ship, Random rand)
    {
        ArrayList<Integer> freeNodes = new ArrayList<>();
        HashMap<Integer, LocationData> data = new HashMap<>();
        int index;
        
        for(LocationData loc : possibleShipLocations) 
        {
            setShip(loc, ship, field);
            index = countFreeNodes(field);
            freeNodes.add(index);
            data.put(index, loc);  
            field.unsetLastShip();
        }
        Collections.sort(freeNodes);
        Collections.reverse(freeNodes);
        index = rand.nextInt(freeNodes.size());
        if(index > upperBound) {
            index = rand.nextInt(upperBound);
        }
        return data.get(freeNodes.get(index));
    }
    
    private void analyzePossibleShipLocations(Ship ship, Grid field)
    {
        possibleShipLocations.clear();
        for (int i = 0; i < field.getSizeVertical(); i++) {
            for (int j = 0; j < field.getSizeHorizontal(); j++) {
                for (Directions d : Directions.values()) {
                    if (field.tryToSetShip(ship, j, i, d))
                    {
                        int[] coordinates = { j, i };
                        possibleShipLocations.add(new LocationData(coordinates, d));
                    }
                }
            }
        }
    }
    
    private int countFreeNodes(Grid field)
    {
        int result = 0;
        
        for(int i = 0; i < field.getSizeVertical(); i++) {
            for(int j = 0; j < field.getSizeHorizontal(); j++) {
                if(!field.searchCircullar(j, i)) {
                    result++;
                }
            }
        }
        return result;
    }

    private static final int upperBound = 1;
    ArrayList<LocationData> possibleShipLocations = new ArrayList<> ();
}
