package com.mycompany.data.game;

import java.util.Arrays;

public class GameRules
{

    public GameRules()
    {
        this(new int[]{ 12, 10 }, 1, 2, 3, 4, -1);
    }
    
    public GameRules(int[] dimensions, int carriersCount, int destroyersCount, int schoonersCount, int boatsCount, int gameId)
    {
        fieldDimensions = Arrays.copyOf(dimensions, 2);
        this.carriersCount = carriersCount;
        this.destroyersCount = destroyersCount;
        this.schoonersCount = schoonersCount;
        this.boatsCount = boatsCount;
        this.gameId = gameId;
    }
        
    public int[] getFieldDimensions() {
		return fieldDimensions;
	}

	public void setFieldDimensions(int[] fieldDimensions) {
		this.fieldDimensions = fieldDimensions;
	}

	public int getCarriersCount() {
		return carriersCount;
	}

	public void setCarriersCount(int carriersCount) {
		this.carriersCount = carriersCount;
	}

	public int getDestroyersCount() {
		return destroyersCount;
	}

	public void setDestroyersCount(int destroyersCount) {
		this.destroyersCount = destroyersCount;
	}

	public int getSchoonersCount() {
		return schoonersCount;
	}

	public void setSchoonersCount(int schoonersCount) {
		this.schoonersCount = schoonersCount;
	}

	public int getBoatsCount() {
		return boatsCount;
	}

	public void setBoatsCount(int boatsCount) {
		this.boatsCount = boatsCount;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	private int[] fieldDimensions;
    private int carriersCount;
    private int destroyersCount;
    private int schoonersCount;
    private int boatsCount;
    private int gameId;
}