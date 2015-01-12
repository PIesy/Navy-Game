package com.mycompany.data.game;

public enum Directions
{
    North(0, -1), East(1, 0), South(0, 1), West(-1, 0);

    private Directions(int x, int y)
    {
        offset[0] = x;
        offset[1] = y;
    }

    public int[] convertTo2DOffset()
    {
        return offset;
    }

    public static Directions parseString(String str)
    {
        String valueString = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        Directions result;
        
        try {
            result = valueOf(valueString);
        } catch(RuntimeException e) {
            return null;
        }
        return result;
    }
    
    public static int[][] convertToCoordinatesPair(int x, int y, int distance, Directions direction)
    {
        int[] offset = direction.convertTo2DOffset();
        int[] startCoordinates = { x, y };
        int[] endCoordinates = new int[2];
        int[] temp;

        for (int i = 0; i < 2; i++) {
            endCoordinates[i] = startCoordinates[i] + offset[i] * (distance - 1);
        }
        if ((offset[0] < 0) || (offset[1] < 0))
        {
            temp = startCoordinates;
            startCoordinates = endCoordinates;
            endCoordinates = temp;
        }
        return new int[][]{ startCoordinates, endCoordinates };
    }

    private int[] offset = { 0, 0 };
}
