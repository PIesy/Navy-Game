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

    public String getPlayer1Name()
    {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name)
    {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name()
    {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name)
    {
        this.player2Name = player2Name;
    }

    private String state = null;
    private String error = null;
    private int[][] playerField = null;
    private int[][] botField = null;
    private String gameEnd = null;
    private String player1Name = null;
    private String player2Name = null;
}
