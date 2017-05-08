/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wirtualnyswiat;

import java.awt.Point;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author pawel
 */
public class Zolw extends Zwierze{
    public Zolw(int x, int y, Swiat swiat){
	sila = 2;
	inicjatywa = 1;
        polozenie = new Point(x,y);
        poprzedniePolozenie = new Point(x,y);
	this.swiat = swiat;
	gatunek = 'Z';
        ikona = new ImageIcon("turtle.png");
    }
    @Override
    public void akcja(){
        Random rand = new Random();
	if (rand.nextInt(4) < 3)
            wiek++;
	else 
            super.akcja();
    }
    @Override
    public void kolizja(Zwierze napastnik){        
	if (napastnik.getGatunek() != gatunek && napastnik.getSila() < 5) {
            napastnik.wroc();
            String log = "tura " + swiat.getWiek() + ": ";
            log += "Z odgania ";
            log += napastnik.getGatunek();
            swiat.dodajLog(log);
	}
	else
            super.kolizja(napastnik);
    }
}
