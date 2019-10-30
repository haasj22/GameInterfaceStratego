package com.example.myapplication.Stratego;

import android.view.View;

public class XMLObject implements View.OnClickListener{
    private View xmlObject;

    public XMLObject(){

    }

    public XMLObject(View v){
        setXMLObject(v);
    }

    public View getXmlObject(){
        return this.xmlObject;
    }
    public void setXMLObject(View xmlObject){
        this.xmlObject = xmlObject;
    }
    @Override
    public void onClick(View v){

    }
}
