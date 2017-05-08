/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wirtualnyswiat;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author pawel
 */
public class Antylopa extends Zwierze{
    public Antylopa(int x, int y, Swiat swiat) {
	sila = 4;
	inicjatywa = 4;
        polozenie = new Point(x,y);
        poprzedniePolozenie = new Point(x,y);
	this.swiat = swiat;
	gatunek = 'A';
        ikona = new ImageIcon("deer.png");
    } 
    @Override
    public void akcja(){
        super.akcja();
        wiek--;
        super.akcja();
    }    
    @Override
    public void kolizja(Zwierze napastnik) {
        Random rand = new Random();
        ArrayList<Point> sasiedzi = swiat.getSasiedniePola(polozenie);
        boolean wolnePole = false;
        for(Point p : sasiedzi)
            if(swiat.getOrganizmPoPolozeniu(p) == null){
                wolnePole = true;
            }
    	if (gatunek != napastnik.getGatunek() && rand.nextBoolean() && wolnePole) {
            Point p;
            do {
                p = sasiedzi.get(rand.nextInt(sasiedzi.size())).getLocation();
            } while (swiat.getOrganizmPoPolozeniu(p) != null);
            polozenie = p.getLocation();
            String log = new String();
            log += "tura ";
            log += swiat.getWiek();
            log += ": A czmycha przed ";
            log += napastnik.getGatunek();
            swiat.dodajLog(log);
	}
	else {
            super.kolizja(napastnik);
	}
    }
}
