package com.mycompany.data.game.automation;

import java.util.ArrayList;
import java.util.Random;

import com.mycompany.data.game.Directions;
import com.mycompany.data.game.Grid;
import com.mycompany.data.game.LocalPlayer;
import com.mycompany.data.game.LocationData;
import com.mycompany.data.game.ships.Ship;

public class SimpleFieldFiller implements FieldFiller
{

    @Override
    public void fill(Grid field, LocalPlayer player)
    {
        Random rand = new Random();
        int num;
        int[] coordinates;
        Ship ship;

        while (true)
        {
            if ((ship = player.getShip()) == null) {
                return;
            }
            analyzePossibleShipLocations(ship, field);
            num = rand.nextInt(possibleShipLocations.size());
            coordinates = possibleShipLocations.get(num).coordinates;
            field.setShip(ship, coordinates[0], coordinates[1], possibleShipLocations.get(num).direction);
        }
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

    ArrayList<LocationData> possibleShipLocations = new ArrayList<> ();
}
