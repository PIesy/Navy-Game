package com.mycompany.data;

import com.mycompany.data.game.GameResponse;

public class GameResponseBuilder
{

    public GameResponseBuilder addSuccess()
    {
        result.setState("success");
        return this;
    }

    public GameResponseBuilder addError(String errorMessage)
    {
        result.setState("error");
        result.setError(errorMessage);
        return this;
    }

    public GameResponseBuilder addPlayerField(int[][] field)
    {
        result.setPlayerField(field);
        return this;
    }

    public GameResponseBuilder addBotField(int[][] field)
    {
        result.setBotField(field);
        return this;
    }

    public GameResponseBuilder addEndGameMessage(String message)
    {
        result.setGameEnd(message);
        return this;
    }

    public GameResponseBuilder addPlayerAndBotFields(int[][] playerField, int[][] botField)
    {
        result.setPlayerField(playerField).setBotField(botField);
        return this;
    }
    
    public GameResponseBuilder addPlayer1Name(String name)
    {
        result.setPlayer1Name(name);
        return this;
    }
    
    public GameResponseBuilder addPlayer2Name(String name)
    {
        result.setPlayer2Name(name);
        return this;
    }

    public GameResponse build()
    {
        return result;
    }

    private GameResponse result = new GameResponse();
}
