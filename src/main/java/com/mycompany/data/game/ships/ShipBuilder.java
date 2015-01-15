package com.mycompany.data.game.ships;

public class ShipBuilder
{

    public static Ship buildShip(ShipType type)
    {
        return new Ship(type, type.getSize());
    }

    public enum ShipType
    {
        Boat(1), Schooner(2), Destroyer(3), Carrier(4);

        public int getSize()
        {
            return size;
        }

        private ShipType(int size)
        {
            this.size = size;
        }
        
        public static ShipType parseSize(int size)
        {
            for(ShipType type: ShipType.values()) {
                if(type.getSize() == size) {
                    return type;
                }
            }
            return null;
        }

        private final int size;
    }
}
