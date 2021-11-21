package com.example.tabelle_listadapter;

public class Ausgabe {

private String name_Ausgabe;
private String wert;
private String datum;

public Ausgabe(String Ausgabe, String Wert,  String Datum) {
        this.name_Ausgabe = Ausgabe;
        this.wert = Wert;
        this.datum = Datum;
        }

public String getWert() {
        return wert;
        }

public void setWert(String birthday) {
        this.wert = birthday;
        }

public String getAusgabe() {
        return name_Ausgabe;
        }

public void setAusgabe(String name) {
        this.name_Ausgabe = name;
        }

public String getDatum() {
        return datum;
        }

public void setDatum(String sex){
        this.datum =sex;
        }
}