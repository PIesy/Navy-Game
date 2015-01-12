package com.mycompany.data.game.automation;

import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import com.mycompany.data.exceptions.AlreadyHitException;
import com.mycompany.data.exceptions.ShipIsKilledException;
import com.mycompany.data.game.GameRules;
import com.mycompany.data.game.Grid;
import com.mycompany.data.game.LocationData;

public class HitAnalyzer
{
    public HitAnalyzer(GameRules rules)
    {
        descriptor = new BotFieldDescriptor(rules);
        filler = new RatingFiller(rules);
    }
    
    public boolean hit(Grid field) throws ShipIsKilledException
    {
        LocationData data;
        boolean success;
        
        updateRatings();
        data = getHitLocation();
        try {
            success = field.hit(data.coordinates[0], data.coordinates[1]);
        } catch (AlreadyHitException | ShipIsKilledException e) {
            updateIfShipDestroyed(data);
            throw (ShipIsKilledException)e;
        }
        if(success) {
            updateIfShipHit(data);
        } else {
            descriptor.setHit(data.coordinates[0], data.coordinates[1]);
        }
        return success;
    }
    
    private void updateRatings()
    {
        analyzePossibleHitLocations(0, descriptor.getDimensions()[0], 0, descriptor.getDimensions()[1]);
        descriptor.resetRating();
        filler.countRating(descriptor, possibleHitLocations);
    }
    
    private void updateIfShipDestroyed(LocationData data)
    {
        shipSize++;
        descriptor.setLethalHit(data.coordinates[0], data.coordinates[1]);
        filler.destroyShip(shipSize);
        shipSize = 0;
        lastSuccessfullHit.clear();
    }
    
    private void updateIfShipHit(LocationData data)
    {
        descriptor.setSuccessfullHit(data.coordinates[0], data.coordinates[1]);
        shipSize++;
        lastSuccessfullHit.add(data.coordinates);
    }
    
    private LocationData getHitLocation()
    {
        if(lastSuccessfullHit.isEmpty()) {
            return getIfNoShipFound();
        } else {
            return getIfShipFound();
        }
        
    }
    
    private LocationData getIfNoShipFound()
    {
        int maxRating = 0;
        LocationData result = possibleHitLocations.get(0);
        
        for(LocationData data: possibleHitLocations){
            if(descriptor.getRating(data.coordinates[0], data.coordinates[1]) > maxRating)
            {
                maxRating = descriptor.getRating(data.coordinates[0], data.coordinates[1]);
                result = data;
            }
        }
        return result;
    }
    
    private LocationData getIfShipFound()
    {
        int[] coordinates;
        
        do {
            coordinates = lastSuccessfullHit.pollLast();
            analyzePossibleHitLocations(coordinates[0] - 1, coordinates[0] + 2, coordinates[1] - 1, coordinates[1] + 2);
        } while (possibleHitLocations.isEmpty());
        lastSuccessfullHit.add(coordinates);
        return possibleHitLocations.get(rand.nextInt(possibleHitLocations.size()));
    }
    
    private void analyzePossibleHitLocations(int startX, int endX, int startY, int endY)
    {
        possibleHitLocations.clear();
        for (int i = startY; i < endY; i++) {
            for (int j = startX; j < endX; j++) 
            {
                if(descriptor.isOutOfBounds(j, i)) continue;
                if (descriptor.isValidTarget(j, i)) 
                {
                    int[] coordinates = { j, i };
                    possibleHitLocations.add(new LocationData(coordinates, null));
                }
            }
        }
    }
        
    private RatingFiller filler;
    private int shipSize = 0;
    private Deque<int[]> lastSuccessfullHit = new LinkedList<>();
    private Random rand = new Random((new Date()).getTime());
    private BotFieldDescriptor descriptor;
    private List<LocationData> possibleHitLocations = new ArrayList<>();
}

