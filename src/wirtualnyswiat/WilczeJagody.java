/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wirtualnyswiat;

import java.awt.Point;
import javax.swing.ImageIcon;

/**
 *
 * @author pawel
 */
public class WilczeJagody extends Roslina {
    WilczeJagody(int x, int y, Swiat swiat){
	sila = 99;
	inicjatywa = 0;
        polozenie = new Point(x,y);
	this.swiat = swiat;
	gatunek = 'J';        
        ikona = new ImageIcon("jagoda.jpg");
    }
    @Override
    public void kolizja(Zwierze napastnik){        
	String log = "tura " + swiat.getWiek() + ": ";
	log += napastnik.getGatunek();
	log += " zjada J oba gina\n";
	swiat.dodajLog(log);
	swiat.usunOrganizm(napastnik);
	swiat.usunOrganizm(this);
    }
}
