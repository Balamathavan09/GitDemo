package com.finx.util;

public class Embeddedfile
{
    private Document document;

    private String fileextension;

    private String encodingType;

    private String name;

    private String type;

    private String mimeType;

    public Document getDocument ()
    {
        return document;
    }

    public void setDocument (Document document)
    {
        this.document = document;
    }

    public String getFileextension ()
    {
        return fileextension;
    }

    public void setFileextension (String fileextension)
    {
        this.fileextension = fileextension;
    }

    public String getEncodingType ()
    {
        return encodingType;
    }

    public void setEncodingType (String encodingType)
    {
        this.encodingType = encodingType;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getMimeType ()
    {
        return mimeType;
    }

    public void setMimeType (String mimeType)
    {
        this.mimeType = mimeType;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [document = "+document+", fileextension = "+fileextension+", encodingType = "+encodingType+", name = "+name+", type = "+type+", mimeType = "+mimeType+"]";
    }
}
