package com.mycompany.data.game;

public enum Directions
{
    North(0, -1), East(1, 0), South(0, 1), West(-1, 0), None(5, 5);

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
            return None;
        }
        return result;
    }

    private int[] offset = { 0, 0 };
}
