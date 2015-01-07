package com.mycompany.data.game;

import java.util.Arrays;

public class GameRequest
{

    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }

    public Directions getDirection()
    {
        return direction;
    }

    public void setDirection(Directions direction)
    {
        this.direction = direction;
    }

    public void setCoordinates(int[] coordinates)
    {
        this.coordinates = Arrays.copyOf(coordinates, 2);
    }

    public int[] getCoordinates()
    {
        return coordinates;
    }

    public void setGameId(int gameId)
    {
        this.gameId = gameId;
    }

    public int getGameId()
    {
        return gameId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    private String type = null;
    private Directions direction = null;
    private String name = null;
    private int[] coordinates = null;
    private int gameId = 0;
}
