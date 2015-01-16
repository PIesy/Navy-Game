package com.mycompany.data.game;

import com.mycompany.data.GameResponseFactory;
import com.mycompany.data.exceptions.AlreadyHitException;
import com.mycompany.data.exceptions.GameOverException;
import com.mycompany.data.exceptions.ShipIsKilledException;
import com.mycompany.data.game.Bot;
import com.mycompany.data.game.Directions;
import com.mycompany.data.game.GameResponse;
import com.mycompany.data.game.GameRules;
import com.mycompany.data.game.Grid;
import com.mycompany.data.game.LocalPlayer;
import com.mycompany.data.game.ships.Ship;

public class Game
{
    public Game()
    {
        this(new GameRules());
    }

    public Game(GameRules rules)
    {
        this(rules, true);
    }
    
    public Game(GameRules rules, boolean initBot)
    {
        fields[0] = new Grid(rules.getFieldDimensions()[0], rules.getFieldDimensions()[1]);
        fields[1] = new Grid(rules.getFieldDimensions()[0], rules.getFieldDimensions()[1]);
        bot = new Bot(rules);
        player = new LocalPlayer(rules);
        if(initBot) {
            initBot();
        }
    }
    
    public Grid getPlayerGrid(int playerNum)
    {
        return fields[playerNum];
    }
    
    public LocalPlayer getPlayer(int playerNum)
    {
        if(playerNum == 0) {
            return player;
        }
        return bot;
    }
    
    public GameResponse setPlayerName(String name)
    {
        GameResponse response = GameResponseFactory.makeSuccessResponse();
        player.setName(name);
        return response;
    }

    public GameResponse hit(int[] coordinates)
    {
        boolean switchTurn = false;

        try {
            switchTurn = !fields[1].hit(coordinates[0], coordinates[1]);
        } catch (AlreadyHitException e) {
            return GameResponseFactory.makeErrorResponse(e.getMessage());
        } catch (ShipIsKilledException e) {
            try {
                bot.destroyShip();
            } catch (GameOverException e1) {
                return GameResponseFactory.makeEndGameResponse("YOU ARE VICTORIOUS!");
            }
        }
        if (switchTurn) {
            return botHit();
        }
        return GameResponseFactory.makeSuccessWithBothFieldsResponse(fields[0], fields[1]);
    }

    public GameResponse setShip(int[] coordinates, Directions direction)
    {
        Ship ship;
        try {
            ship = player.getShip();
            fields[0].setShip(ship, coordinates[0], coordinates[1], direction);
        } catch (IndexOutOfBoundsException e) {
            player.unsetShip();
            return GameResponseFactory.makeErrorWithFieldResponse(e.getMessage(), fields[0]);
        }
        return GameResponseFactory.makeSuccessWithFieldResponse(fields[0]);
    }

    private void initBot()
    {
        bot.setShips(fields[1]);
    }

    private GameResponse botHit()
    {
        boolean end = false;

        while (!end) {
            try {
                end = !bot.hit(fields[0]);
            } catch (ShipIsKilledException e) {
                try {
                    player.destroyShip();
                } catch (GameOverException e1) {
                    return GameResponseFactory.makeEndGameResponse("NOT SO EASY!");
                }
            }
        }
        return GameResponseFactory.makeSuccessWithBothFieldsResponse(fields[0], fields[1]);
    }

    protected final Grid[] fields = new Grid[2];
    protected final LocalPlayer player;
    protected final Bot bot;
}
