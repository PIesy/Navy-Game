package com.mycompany.data.game.automation;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.mycompany.data.game.Directions;
import com.mycompany.data.game.Grid;
import com.mycompany.data.game.LocalPlayer;
import com.mycompany.data.game.LocationData;
import com.mycompany.data.game.ships.Ship;

public class ComplexFieldFiller implements FieldFiller
{

    @Override
    public void fill(Grid field, LocalPlayer player)
    {
        Ship ship;
        Random rand = new Random((new Date()).getTime());

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
        Map<Integer, List<LocationData>> data = new HashMap<>();
        List<LocationData> mapItem = null;
        int index;
        
        fillPositionerData(data, freeNodes, ship, field);
        Collections.sort(freeNodes);
        Collections.reverse(freeNodes);
        index = rand.nextInt(freeNodes.size() / 2);
        mapItem = data.get(freeNodes.get(freeNodes.size() / 2 - index));
        index = rand.nextInt(mapItem.size());
        return mapItem.get(index);
    }
    
    private void fillPositionerData(Map<Integer, List<LocationData>> map, ArrayList<Integer> list, Ship ship, Grid field)
    {
        int index = 0;
        List<LocationData> mapItem = null;
        
        for(LocationData loc : possibleShipLocations) 
        {
            setShip(loc, ship, field);
            index = countFreeNodes(field);
            list.add(index);
            mapItem = map.get(index);
            if(mapItem == null) 
            {
                mapItem = new ArrayList<>();
                map.put(index, mapItem);
            }
            mapItem.add(loc);
            field.unsetLastShip();
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

    ArrayList<LocationData> possibleShipLocations = new ArrayList<> ();
}
