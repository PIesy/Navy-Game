package com.mycompany.data.game;

import javax.xml.bind.annotation.XmlRootElement;

import com.mycompany.data.GameResponseBuilder;

@XmlRootElement(name = "GameResponse")
public class GameResponse
{

    public static GameResponseBuilder createBuilder()
    {
        return new GameResponseBuilder();
    }
    
    public GameResponse setState(String state)
    {
        this.state = state;
        return this;
    }

    public String getState()
    {
        return state;
    }

    public GameResponse setError(String error)
    {
        this.error = error;
        return this;
    }

    public String getError()
    {
        return error;
    }

    public GameResponse setPlayerField(int[][] field)
    {
        this.playerField = field;
        return this;
    }

    public int[][] getPlayerField()
    {
        return playerField;
    }

    public GameResponse setBotField(int[][] field)
    {
        this.botField = field;
        return this;
    }

    public int[][] getBotField()
    {
        return botField;
    }

    public GameResponse setGameEnd(String gameEnd)
    {
        this.gameEnd = gameEnd;
        return this;
    }

    public String getGameEnd()
    {
        return gameEnd;
    }

    private String state = null;
    private String error = null;
    private int[][] playerField = null;
    private int[][] botField = null;
    private String gameEnd = null;
}
