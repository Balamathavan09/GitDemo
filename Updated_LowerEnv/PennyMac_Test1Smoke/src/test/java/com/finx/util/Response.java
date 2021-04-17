package com.finx.util;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response
{
    private Responsedata[] responsedata;

    private String responseDateTime;

    private String internalAccountIdentifier;

    public Responsedata[] getResponsedata ()
    {
        return responsedata;
    }

    public void setResponsedata (Responsedata[] responsedata)
    {
        this.responsedata = responsedata;
    }

    public String getResponseDateTime ()
    {
        return responseDateTime;
    }

    public void setResponseDateTime (String responseDateTime)
    {
        this.responseDateTime = responseDateTime;
    }

    public String getInternalAccountIdentifier ()
    {
        return internalAccountIdentifier;
    }

    public void setInternalAccountIdentifier (String internalAccountIdentifier)
    {
        this.internalAccountIdentifier = internalAccountIdentifier;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [responsedata = "+responsedata+", responseDateTime = "+responseDateTime+", internalAccountIdentifier = "+internalAccountIdentifier+"]";
    }
}
		
