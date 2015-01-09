package com.mycompany.data.game.automation;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.data.game.GameRules;

public class BotFieldDescriptor
{
    
    public BotFieldDescriptor(GameRules rules)
    {
        nodes = new NodeDescriptor[rules.getFieldDimensions()[1]][rules.getFieldDimensions()[0]];   
        dimensions = rules.getFieldDimensions();
        for (int i = 0; i < dimensions[1]; i++) {
            for(int j = 0; j < dimensions[0]; j++) {
                nodes[i][j] = new NodeDescriptor();
            }
        }
    }
    
    public void setHit(int x, int y)
    {
        nodes[y][x].setInValidTarget();
    }
    
    public void setSuccessfullHit(int x, int y)
    {
        nodes[y][x].setSuccessfullHit();
        nodes[y][x].setInValidTarget();
        updateNodes(x, y);
    }
    
    public void setLethalHit(int x, int y)
    {
        nodes[y][x].setSuccessfullHit();
        nodes[y][x].setInValidTarget();
        updateIfLethal(x, y);
    }
    
    public boolean isValidTarget(int x, int y)
    {
        return nodes[y][x].isValidTarget();
    }
    
    public int[] getDimensions()
    {
        return dimensions;
    }
    
    public void printDescriptor()
    {
        for(int i = 0; i < dimensions[1]; i++) 
        {
            for(int j = 0; j < dimensions[0]; j++){
                if(nodes[i][j].isSuccessfullHit()){
                    System.out.print(" S ");
                } else if(nodes[i][j].isValidTarget()) {
                    System.out.print(" - ");
                } else {
                    System.out.print(" V ");
                }
            }
            System.out.println();
        }
    }
    
    private List<int[]> searchSuccsessfullyHitNodesAround(int x, int y)
    {
        List<int[]> result = new ArrayList<>();
        
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++)
            {
                if ((i < 0) || (j < 0) || (i > dimensions[1] - 1) || (j > dimensions[0] - 1)) {
                    continue;
                }
                if((i == y) && (j == x)) {
                    continue;
                }
                if (nodes[i][j].isSuccessfullHit()) {
                    result.add(new int[]{ j, i });
                }
            }
        }
        return result;
    }
    
    private void updateIfLethal(int x, int y) 
    {
        List<int[]> hitNodes = searchSuccsessfullyHitNodesAround(x, y);
        hitNodes.add(new int[] { x , y });
        
        for(int[] coordinates : hitNodes) {
            invalidateNodesAround(coordinates[0], coordinates[1], 1, 1);
        }
    }
    
    private void updateNodes(int x, int y)
    {
        List<int[]> hitNodes = searchSuccsessfullyHitNodesAround(x, y);
        int rangeX = 1, rangeY = 0;
        
        if(hitNodes.isEmpty()) {
            return;
        }
        hitNodes.add(new int[] { x , y });
        if(isHorizontallyShifted(x, hitNodes)) 
        {
            rangeX = 0;
            rangeY = 1;
        }
        for(int[] coordinates : hitNodes) {
            invalidateNodesAround(coordinates[0], coordinates[1], rangeX, rangeY);
        }
    }
    
    private void invalidateNodesAround(int x, int y, int rangeX, int rangeY)
    {
        for(int i = y - rangeY; i <= y + rangeY; i++) {
            for(int j = x - rangeX; j <= x + rangeX; j++) 
            {
                if ((i < 0) || (j < 0) || (i > dimensions[1] - 1) || (j > dimensions[0] - 1)) {
                    continue;
                }
                nodes[i][j].setInValidTarget();
            }
        }
    }
    
    private boolean isHorizontallyShifted(int startNodeX, List<int[]> hitNodes)
    {
        if((startNodeX - hitNodes.get(0)[0]) != 0) {
            return true;
        }
        return false;
    }
    
    private int[] dimensions;
    private NodeDescriptor[][] nodes;
}