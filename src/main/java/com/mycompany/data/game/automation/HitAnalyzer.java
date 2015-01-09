package com.mycompany.data.game.automation;

import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import com.mycompany.data.exceptions.AlreadyHitException;
import com.mycompany.data.exceptions.ShipIsKilledException;
import com.mycompany.data.game.Directions;
import com.mycompany.data.game.GameRules;
import com.mycompany.data.game.Grid;
import com.mycompany.data.game.LocationData;

public class HitAnalyzer
{
    public HitAnalyzer(GameRules rules)
    {
        descriptor = new BotFieldDescriptor(rules);
    }
    
    public boolean hit(Grid field) throws ShipIsKilledException
    {
        LocationData data;
        boolean success;
        
        data = getHitLocation();

        try {
            success = field.hit(data.coordinates[0], data.coordinates[1]);
        } catch (AlreadyHitException | ShipIsKilledException e) {
            descriptor.setLethalHit(data.coordinates[0], data.coordinates[1]);
            lastSuccessfullHit.clear();
            throw (ShipIsKilledException)e;
        }
        if(success) {
            descriptor.setSuccessfullHit(data.coordinates[0], data.coordinates[1]);
            lastSuccessfullHit.add(data.coordinates);
        } else {
            descriptor.setHit(data.coordinates[0], data.coordinates[1]);
        }
        return success;
    }
    
    private LocationData getHitLocation()
    {
        int[] coordinates; 
        if(lastSuccessfullHit.isEmpty()) {
            analyzePossibleHitLocations(0, descriptor.getDimensions()[0], 0, descriptor.getDimensions()[1]);
        } else {
            do {
                coordinates = lastSuccessfullHit.pollLast();
                analyzePossibleHitLocations(coordinates[0] - 1, coordinates[0] + 2, coordinates[1] - 1, coordinates[1] + 2);
            } while (possibleHitLocations.isEmpty());
            lastSuccessfullHit.add(coordinates);
        }
        return possibleHitLocations.get(rand.nextInt(possibleHitLocations.size()));
    }
    
    private void analyzePossibleHitLocations(int startX, int endX, int startY, int endY)
    {
        possibleHitLocations.clear();
        for (int i = startY; i < endY; i++) {
            for (int j = startX; j < endX; j++) 
            {
                if(isOutOfBounds(j, i)) continue;
                if (descriptor.isValidTarget(j, i)) 
                {
                    int[] coordinates = { j, i };
                    possibleHitLocations.add(new LocationData(coordinates, Directions.None));
                }
            }
        }
    }
    
    private boolean isOutOfBounds(int x, int y)
    {
        if((x < 0) || (y < 0)) {
            return true;
        }
        if((x >= descriptor.getDimensions()[0]) || (y >= descriptor.getDimensions()[1])) {
            return true;
        }
        return false;
    }
    
    private Deque<int[]> lastSuccessfullHit = new LinkedList<>();
    private Random rand = new Random((new Date()).getTime());
    private BotFieldDescriptor descriptor;
    private List<LocationData> possibleHitLocations = new ArrayList<>();
}
