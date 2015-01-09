package com.mycompany.data.game.automation;

public class NodeDescriptor
{
    public void addRating(int rating)
    {
        hitRating += rating;
    }
    
    public int getHitRating()
    {
        return hitRating;
    }

    public boolean isSuccessfullHit()
    {
        return successfullHit;
    }

    public void setSuccessfullHit()
    {
        successfullHit = true;
    }

    public boolean isValidTarget()
    {
        return validTarget;
    }

    public void setInValidTarget()
    {
        validTarget = false;
    }

    private boolean validTarget = true;
    private boolean successfullHit = false;
    private int hitRating = 0;
}