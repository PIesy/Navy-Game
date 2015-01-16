package com.mycompany.data;

import com.mycompany.data.game.GameResponse;
import com.mycompany.data.game.Grid;
import com.mycompany.data.game.GridItemDescriptor;

public class GameResponseFactory
{

    public static GameResponse makeSuccessResponse()
    {
        GameResponseBuilder builder = GameResponse.createBuilder();
        return builder.addSuccess().build();
    }

    public static GameResponse makeErrorResponse(String errorMessage)
    {
        GameResponseBuilder builder = GameResponse.createBuilder();
        return builder.addError(errorMessage).build();
    }
    
    public static GameResponse makeErrorWithFieldResponse(String errorMessage, Grid field)
    {
        GameResponseBuilder builder = GameResponse.createBuilder();
        builder.addPlayerField(getIntegerFieldDescriptor(field));
        return builder.addError(errorMessage).build();
    }

    public static GameResponse makeSuccessWithFieldResponse(Grid field)
    {
        GameResponseBuilder builder = GameResponse.createBuilder();
        int[][] result = getIntegerFieldDescriptor(field);
        
        builder.addSuccess();
        return builder.addPlayerField(result).build();
    }

    public static GameResponse makeSuccessWithBothFieldsResponse(Grid playerField, Grid botField)
    {
        GameResponseBuilder builder = GameResponse.createBuilder();
        int[][] field = getIntegerFieldDescriptor(playerField);
        
        builder.addPlayerField(field);
        field = getIntegerFieldDescriptor(botField);
        builder.addBotField(field);
        return builder.addSuccess().build();
    }

    public static GameResponse makeEndGameResponse(String message)
    {
        GameResponseBuilder builder = GameResponse.createBuilder();
        return builder.addEndGameMessage(message).build();
    }
    
    public static GameResponse makeInfoResponse(String[] names, Grid[] fields)
    {
        GameResponseBuilder builder = GameResponse.createBuilder();
        int[][] field1 = getIntegerFieldDescriptor(fields[0]);
        int[][] field2 = getIntegerFieldDescriptor(fields[1]);
        
        builder.addPlayerAndBotFields(field1, field2);
        builder.addPlayer1Name(names[0]);
        return builder.addPlayer2Name(names[1]).build();
    }

    private static int[][] getIntegerFieldDescriptor(Grid field)
    {
        GridItemDescriptor[][] descriptors = field.getState();
        int[][] result = new int[field.getSizeVertical()][field.getSizeHorizontal()];

        for (int i = 0; i < field.getSizeVertical(); i++) {
            for (int j = 0; j < field.getSizeHorizontal(); j++) {
                result[i][j] = descriptors[i][j].toInt();
            }
        }
        return result;
    }
}
