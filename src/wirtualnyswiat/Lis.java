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
public class Lis extends Zwierze{
    public Lis(int x, int y, Swiat swiat) {
	sila = 3;
	inicjatywa = 7;
        polozenie = new Point(x,y);
        poprzedniePolozenie = new Point(x,y);
	this.swiat = swiat;
	gatunek = 'L';
        ikona = new ImageIcon("fox.png");
    }      
    @Override
    public void akcja(){
	int x, y;
	boolean mozeSieRuszyc = false;
        ArrayList<Point> sasiedzi = swiat.getSasiedniePola(polozenie);
	for (Point p : sasiedzi) {
            if (swiat.getOrganizmPoPolozeniu(p) == null
                    || swiat.getOrganizmPoPolozeniu(p).getSila() <= sila) {
                mozeSieRuszyc = true;
                break;
            }
	}
	wiek++;
	if (!(mozeSieRuszyc))
		return;
	poprzedniePolozenie = polozenie.getLocation();
        Random rand = new Random();
        Point p;
	do {
		p = sasiedzi.get(rand.nextInt(sasiedzi.size()));
	} while (!((swiat.getOrganizmPoPolozeniu(p) == null
                || swiat.getOrganizmPoPolozeniu(p).getSila() <= sila)));
        Organizm o = swiat.getOrganizmPoPolozeniu(p);
	polozenie = p.getLocation();
        if(o != null && o != this)
            o.kolizja(this);
    }
}
