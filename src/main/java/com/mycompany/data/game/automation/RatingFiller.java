package com.mycompany.data.game.automation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        
        for(Ship ship: ships) {
            if(ship.getSize() == size)
            {
                temp = ship;
                break;
            }
        }
        ships.remove(temp);
    }
    
    private void initShips(ShipType type, int count)
    {
        for(int i = 0; i < count; i++) {
            ships.add(ShipBuilder.buildShip(type));
        }
    }
    
    private List<Ship> ships = new ArrayList<>();
}
