package com.finx.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Voiresponse
{
    private String voiReportType;

    private String voiReportTypeOtherDescription;

    private Borrower[] borrower;

    private String mismoVersionID;

    private String voiResponseID;

    private String voiReportIdentifier;

    private Embeddedfile[] embeddedfile;

    public String getVoiReportType ()
    {
        return voiReportType;
    }

    public void setVoiReportType (String voiReportType)
    {
        this.voiReportType = voiReportType;
    }

    public String getVoiReportTypeOtherDescription ()
    {
        return voiReportTypeOtherDescription;
    }

    public void setVoiReportTypeOtherDescription (String voiReportTypeOtherDescription)
    {
        this.voiReportTypeOtherDescription = voiReportTypeOtherDescription;
    }

    public Borrower[] getBorrower ()
    {
        return borrower;
    }

    public void setBorrower (Borrower[] borrower)
    {
        this.borrower = borrower;
    }

    public String getMismoVersionID ()
    {
        return mismoVersionID;
    }

    public void setMismoVersionID (String mismoVersionID)
    {
        this.mismoVersionID = mismoVersionID;
    }

    public String getVoiResponseID ()
    {
        return voiResponseID;
    }

    public void setVoiResponseID (String voiResponseID)
    {
        this.voiResponseID = voiResponseID;
    }

    public String getVoiReportIdentifier ()
    {
        return voiReportIdentifier;
    }

    public void setVoiReportIdentifier (String voiReportIdentifier)
    {
        this.voiReportIdentifier = voiReportIdentifier;
    }

    public Embeddedfile[] getEmbeddedfile ()
    {
        return embeddedfile;
    }

    public void setEmbeddedfile (Embeddedfile[] embeddedfile)
    {
        this.embeddedfile = embeddedfile;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [voiReportType = "+voiReportType+", voiReportTypeOtherDescription = "+voiReportTypeOtherDescription+", borrower = "+borrower+", mismoVersionID = "+mismoVersionID+", voiResponseID = "+voiResponseID+", voiReportIdentifier = "+voiReportIdentifier+", embeddedfile = "+embeddedfile+"]";
    }
}
