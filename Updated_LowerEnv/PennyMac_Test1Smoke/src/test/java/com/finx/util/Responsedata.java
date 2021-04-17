package com.finx.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Responsedata
{
    private Voiresponse voiresponse;

    public Voiresponse getVoiresponse ()
    {
        return voiresponse;
    }

    public void setVoiresponse (Voiresponse voiresponse)
    {
        this.voiresponse = voiresponse;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [voiresponse = "+voiresponse+"]";
    }
}
