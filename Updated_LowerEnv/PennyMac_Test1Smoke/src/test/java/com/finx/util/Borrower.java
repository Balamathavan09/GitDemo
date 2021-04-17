package com.finx.util;

public class Borrower
{
    private String lastName;

    private String borrowerID;

    private String ssn;

    private String firstName;

    public String getLastName ()
    {
        return lastName;
    }

    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }

    public String getBorrowerID ()
    {
        return borrowerID;
    }

    public void setBorrowerID (String borrowerID)
    {
        this.borrowerID = borrowerID;
    }

    public String getSsn ()
    {
        return ssn;
    }

    public void setSsn (String ssn)
    {
        this.ssn = ssn;
    }

    public String getFirstName ()
    {
        return firstName;
    }

    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [lastName = "+lastName+", borrowerID = "+borrowerID+", ssn = "+ssn+", firstName = "+firstName+"]";
    }
}
