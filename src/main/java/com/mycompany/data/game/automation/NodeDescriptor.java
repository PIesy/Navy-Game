package com.mycompany.data.game.automation;

public class NodeDescriptor
{
    public void addRating(int rating)
    {
        hitRating += rating;
    }
    
    public void resetRating()
    {
        hitRating = 0;
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
        previousValidity = validTarget;
        validTarget = false;
    }
    
    public void setValidTarget(boolean value)
    {
        previousValidity = validTarget;
        validTarget = value;
    }
    
    public void setPreviousValidity()
    {
        validTarget = previousValidity;
    }

    private boolean previousValidity = true;
    private boolean validTarget = true;
    private boolean successfullHit = false;
    private int hitRating = 0;
}