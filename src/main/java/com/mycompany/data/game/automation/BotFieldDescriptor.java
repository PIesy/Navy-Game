package com.mycompany.data.game.automation;

import java.util.HashSet;
import java.util.Set;

import com.mycompany.data.game.Directions;
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
        updateSingle(x, y);
    }
    
    public void setLethalHit(int x, int y)
    {
        nodes[y][x].setSuccessfullHit();
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
    
    public int getRating(int x, int y)
    {
        return nodes[y][x].getHitRating();
    }
    
    public void resetRating()
    {
        for (int i = 0; i < dimensions[1]; i++) {
            for(int j = 0; j < dimensions[0]; j++) {
                nodes[i][j].resetRating();
            }
        }
    }
    
    public void printDescriptor()
    {
        for(int i = 0; i < dimensions[1]; i++) 
        {
            for(int j = 0; j < dimensions[0]; j++){
                if(nodes[i][j].isSuccessfullHit()){
                    System.out.print(" S ");
                } else if(nodes[i][j].isValidTarget()) {
                    System.out.print(" " + nodes[i][j].getHitRating() + " ");
                } else {
                    System.out.print(" V ");
                }
            }
            System.out.println();
        }
    }
    
    public boolean isOutOfBounds(int x, int y)
    {
        if((x < 0) || (y < 0)) {
            return true;
        }
        if((x >= dimensions[0]) || (y >= dimensions[1])) {
            return true;
        }
        return false;
    }
    
    public boolean doesShipFitInLocation(int size, int x, int y, Directions direction)
    {
        int[][] coordinates = Directions.convertToCoordinatesPair(x, y, size, direction);
        if(isOutOfBounds(coordinates[0][0], coordinates[0][1]) || isOutOfBounds(coordinates[1][0], coordinates[1][1])) { 
            return false;
        }
        return isNodesRangeValid(coordinates[0], coordinates[1]);
    }
    
    public void updateNodesRating(int[] startCoordinates, int[] endCoordinates, int ratingAddition)
    {
        for(int i = startCoordinates[1]; i <= endCoordinates[1]; i++) {
            for(int j = startCoordinates[0]; j <= endCoordinates[0]; j++) {
                nodes[i][j].addRating(ratingAddition);
            }
        }
    }
    
    private boolean isNodesRangeValid(int[] startCoordinates, int[] endCoordinates)
    {
        for(int i = startCoordinates[1]; i <= endCoordinates[1]; i++) {
            for(int j = startCoordinates[0]; j <= endCoordinates[0]; j++) {
                if(!nodes[i][j].isValidTarget()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private Set<int[]> searchSuccsessfullyHitNodesAround(int x, int y)
    {
        Set<int[]> result = new HashSet<>();
        
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++)
            {
                if (isOutOfBounds(j, i)) { continue; }
                if((i == y) && (j == x)) { continue; }
                if (nodes[i][j].isSuccessfullHit()) {
                    result.add(new int[]{ j, i });
                }
            }
        }
        return result;
    }
    
    private void updateIfLethal(int x, int y) 
    {
        Set<int[]> hitNodes = searchSuccsessfullyHitNodesAround(x, y);
        Set<int[]> temp = new HashSet<>();
        hitNodes.add(new int[] { x , y });
        
        for(int[] coordinates: hitNodes) {
            temp.addAll(searchSuccsessfullyHitNodesAround(coordinates[0], coordinates[1]));
        }
        hitNodes.addAll(temp);
        for(int[] coordinates : hitNodes) {
            validateNodesAround(coordinates[0], coordinates[1], 1, 1, false);
        }
    }
        
    private void updateSingle(int x, int y)
    {
        validateNodesAround(x, y, 1, 1, false);
        restoreNodesAround(x, y, 0, 1);
        restoreNodesAround(x, y, 1, 0);
    }
    
    private void validateNodesAround(int x, int y, int rangeX, int rangeY, boolean isValid)
    {
        for(int i = y - rangeY; i <= y + rangeY; i++) {
            for(int j = x - rangeX; j <= x + rangeX; j++) 
            {
                if (isOutOfBounds(j, i)) { continue; }
                nodes[i][j].setValidTarget(isValid);
            }
        }
    }
    
    private void restoreNodesAround(int x, int y, int rangeX, int rangeY)
    {
        for(int i = y - rangeY; i <= y + rangeY; i++) {
            for(int j = x - rangeX; j <= x + rangeX; j++) 
            {
                if (isOutOfBounds(j, i)) { continue; }
                nodes[i][j].setPreviousValidity();
            }
        }
    }
        
    private int[] dimensions;
    private NodeDescriptor[][] nodes;
}