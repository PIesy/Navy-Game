package com.mycompany.data.game.automation;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.mycompany.data.game.Directions;
import com.mycompany.data.game.GameRules;
import com.mycompany.data.game.LocationData;
import com.mycompany.data.game.ships.Ship;
import com.mycompany.data.game.ships.ShipBuilder;
import com.mycompany.data.game.ships.ShipBuilder.ShipType;

public class RatingFiller
{

    public RatingFiller(GameRules rules)
    {
        initShips(ShipType.Boat, rules.getBoatsCount());
        initShips(ShipType.Schooner, rules.getSchoonersCount());
        initShips(ShipType.Destroyer, rules.getDestroyersCount());
        initShips(ShipType.Carrier, rules.getCarriersCount());
    }
    
    public void countRating(BotFieldDescriptor desc, Collection<LocationData> validLocations)
    {
        int[][] coordinates;
        for(LocationData data: validLocations) {
            for(Directions direction : Directions.values()) {
                for(Ship ship: ships) {
                    if(desc.doesShipFitInLocation(ship.getSize(), data.coordinates[0], data.coordinates[1], direction)) {
                        coordinates = Directions.convertToCoordinatesPair(data.coordinates[0], data.coordinates[1], ship.getSize(), direction);
                        desc.updateNodesRating(coordinates[0], coordinates[1], 1);
                    }
                }
            }
        }
    }
    
    public void destroyShip(int size)
    {
        Ship temp = null;
        Integer cnt = shipsCount.get(size);    
        
        cnt--;
        shipsCount.replace(size, cnt);
        if(cnt == 0) 
        {
            for(Ship ship: ships) {
                if(ship.getSize() == size)
                {
                    temp = ship;
                    break;
                }
            }
            ships.remove(temp);
        }
    }
    
    private void initShips(ShipType type, int count)
    {
        ships.add(ShipBuilder.buildShip(type));
        shipsCount.put(type.getSize(), count);
    }
    
    private Set<Ship> ships = new HashSet<>();
    private Map<Integer, Integer> shipsCount = new HashMap<>();
}
