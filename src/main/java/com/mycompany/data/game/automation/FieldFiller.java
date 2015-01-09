package com.mycompany.data.game.automation;

import com.mycompany.data.game.Grid;
import com.mycompany.data.game.LocalPlayer;

public interface FieldFiller
{
    void fill(Grid field, LocalPlayer player);
}
