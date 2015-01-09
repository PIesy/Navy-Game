package com.mycompany.data.game;

import com.mycompany.data.exceptions.ShipIsKilledException;
import com.mycompany.data.game.automation.HitAnalyzer;

class NodeDescriptor
{
    public NodeDescriptor(int[] coordinates, Directions direction)
    {
        this.coordinates = coordinates;
        this.direction = direction;
    }

    public int[] coordinates = new int[2];
    public Directions direction = Directions.None;
}

public class Bot extends LocalPlayer
{

    public Bot(GameRules rules)
    {
        super(rules);
        analyzer = new HitAnalyzer(rules);
    }

    public void setShips(Grid field)
    {
        filler.fill(field, this);
    }

    public boolean hit(Grid enemyField) throws ShipIsKilledException
    {
        return analyzer.hit(enemyField);
    }
    
    private HitAnalyzer analyzer;
    private FieldFiller filler = new ComplexFieldFiller();
}
