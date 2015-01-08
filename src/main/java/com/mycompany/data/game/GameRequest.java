package com.mycompany.data.game;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GameRequest")
public class GameRequest
{

    public enum GameRequestType 
    {
        SetShip, SetName, Hit, GetInfo
    }
    
    public GameRequestType getType()
    {
        return type;
    }

    public void setType(GameRequestType type)
    {
        this.type = type;
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
 
    private GameRequestType type = null;
    private Directions direction = null;
    private String name = null;
    private int[] coordinates = null;
    private int gameId = 0;
}
