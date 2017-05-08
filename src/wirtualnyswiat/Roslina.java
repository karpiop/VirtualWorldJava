/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wirtualnyswiat;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author pawel
 */
public class Roslina extends Organizm {

    @Override
    public void akcja() {
        Random rand = new Random();
        ArrayList<Point> sasiedzi = swiat.getSasiedniePola(polozenie);
        Point p = sasiedzi.get(rand.nextInt(sasiedzi.size())).getLocation();
        if (rand.nextInt(100) < 5
            && swiat.getOrganizmPoPolozeniu(p) == null){
            int x = p.x;
            int y = p.y;
            switch(gatunek){
                case 'T':
                    swiat.dodajOrganizm(new Trawa(x, y, swiat));
                    break;
                case 'G':
                    swiat.dodajOrganizm(new Guarana(x, y, swiat));
                    break;
                case 'M':
                    swiat.dodajOrganizm(new Mlecz(x, y, swiat));
                    break;
                case 'J':
                    swiat.dodajOrganizm(new WilczeJagody(x, y, swiat));
                    break;
            }
            swiat.dodajLog("tura " + swiat.getWiek() + ": " + "rozmnazanie " + gatunek);
        }            
        wiek++;
    }

    @Override
    public void kolizja(Zwierze napastnik) {
	String log = new String();
	log += "tura " + swiat.getWiek() + ": ";
	log += napastnik.getGatunek();
	log += " zjada ";
	log += this.getGatunek();
	swiat.dodajLog(log);
	swiat.usunOrganizm(this);
    }
    
}
