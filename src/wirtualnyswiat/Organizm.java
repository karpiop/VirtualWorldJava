/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wirtualnyswiat;
import java.awt.Point;
import java.io.Serializable;
import javax.swing.*;
/**
 *
 * @author pawel
 */
public abstract class Organizm implements Comparable<Organizm>, Serializable {
    protected int sila;
    protected int inicjatywa;
    protected Point polozenie;
    protected transient Swiat swiat;
    protected int wiek;
    protected char gatunek;
    protected ImageIcon ikona;
    protected boolean zywy = true;
    public abstract void akcja();
    public abstract void kolizja(Zwierze napastnik);
    public void rysowanie(){
        swiat.setIkona(polozenie.getLocation(), ikona);
    }
    public int getSila(){
        return sila;
    }
    public int getInicjatywa(){
        return inicjatywa;
    }
    public Point getPolozenie(){
        return polozenie;
    }
    public int getWiek(){
        return wiek;
    }
    public void setSila(int sila){
        this.sila = sila;
    }
    public char getGatunek(){
        return gatunek;
    }
    public boolean isZywy(){
        return zywy;
    }
    public void setSwiat(Swiat swiat){
        this.swiat = swiat;
    }

    @Override
    public int compareTo(Organizm o) {
        if(inicjatywa == o.getInicjatywa())
            return - wiek + o.getWiek();
        return - inicjatywa + o.getInicjatywa();
    }
    
}
