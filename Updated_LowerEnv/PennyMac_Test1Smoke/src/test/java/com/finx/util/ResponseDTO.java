package com.finx.util;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDTO
{
    private Response[] response;

    private Respondingparty respondingparty;

    private TransactionDetailExtn transactionDetailExtn;

    private String mismoVersionID;

    public Response[] getResponse ()
    {
        return response;
    }

    public void setResponse (Response[] response)
    {
        this.response = response;
    }

    public Respondingparty getRespondingparty ()
    {
        return respondingparty;
    }

    public void setRespondingparty (Respondingparty respondingparty)
    {
        this.respondingparty = respondingparty;
    }

    public TransactionDetailExtn getTransactionDetailExtn ()
    {
        return transactionDetailExtn;
    }

    public void setTransactionDetailExtn (TransactionDetailExtn transactionDetailExtn)
    {
        this.transactionDetailExtn = transactionDetailExtn;
    }

    public String getMismoVersionID ()
    {
        return mismoVersionID;
    }

    public void setMismoVersionID (String mismoVersionID)
    {
        this.mismoVersionID = mismoVersionID;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [response = "+response+", respondingparty = "+respondingparty+", transactionDetailExtn = "+transactionDetailExtn+", mismoVersionID = "+mismoVersionID+"]";
    }
}
	


