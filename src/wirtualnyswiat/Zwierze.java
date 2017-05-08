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
public abstract class Zwierze extends Organizm {
    protected Point poprzedniePolozenie;
    @Override
    public void akcja(){
	Point p = new Point();
        Organizm o;
        Random rand = new Random();
	poprzedniePolozenie = polozenie;
        ArrayList<Point> sasiedzi = swiat.getSasiedniePola(polozenie);
        p = sasiedzi.get(rand.nextInt(sasiedzi.size())).getLocation();
        o = swiat.getOrganizmPoPolozeniu(p.getLocation());
	polozenie = p;
        if(o != null && o != this)
            o.kolizja(this);
	wiek++;        
    }
    @Override
    public void kolizja(Zwierze napastnik){
        if(napastnik.getGatunek() == gatunek){
            napastnik.wroc();
            boolean wolnePole = false;
            ArrayList<Point> sasiedzi = swiat.getSasiedniePola(polozenie);
            sasiedzi.addAll(swiat.getSasiedniePola(napastnik.getPolozenie()));
            for(Point p : sasiedzi)
                if(swiat.getOrganizmPoPolozeniu(p) == null){
                    wolnePole = true;
                    break;
                }
            if(wolnePole && wiek > 10 && napastnik.getWiek() > 10){
                Point p;
                Random rand = new Random();
                do {
                    p = sasiedzi.get(rand.nextInt(sasiedzi.size())).getLocation();
                }while(swiat.getOrganizmPoPolozeniu(p) != null);
                Organizm o = null;
                int x = p.x;
                int y = p.y;
		switch (gatunek) {
                    case 'W':                       
                        o = new Wilk(x, y, swiat);
			break;
                    case 'A':
                        o = new Antylopa(x, y, swiat);
			break;
                    case 'O':
                        o = new Owca(x, y, swiat);
			break;
                    case 'L':
                        o = new Lis(x, y, swiat);
                	break;
                    case 'Z':
                        o = new Zolw(x, y, swiat);
			break;
		}
                if(o != null)
                    swiat.dodajOrganizm(o);
                String log = new String();
                log += "tura " + swiat.getWiek() + ": " + "rozmnazanie " + gatunek;
                swiat.dodajLog(log);
            }
        }
        else {
            String log = new String();
            log += "tura " + swiat.getWiek() + ": ";
            if (sila <= napastnik.getSila()) {
			log += napastnik.getGatunek();
			log += " zjada ";
			log += this.getGatunek();
			swiat.dodajLog(log);
			swiat.usunOrganizm(this);
		}
		else {
			log += this.getGatunek();
			log += " zjada ";
			log += napastnik.getGatunek();
			swiat.dodajLog(log);
			swiat.usunOrganizm(napastnik);
		}
        }
    }
    public void wroc(){
        polozenie = poprzedniePolozenie;
    }
}
