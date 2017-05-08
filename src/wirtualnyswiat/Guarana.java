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
public class Guarana extends Roslina {
    public Guarana(int x, int y, Swiat swiat){
	sila = 0;
	inicjatywa = 0;
        polozenie = new Point(x,y);
	this.swiat = swiat;
	gatunek = 'G';
        ikona = new ImageIcon("guarana.png");
    }
    @Override
    public void kolizja (Zwierze napastnik){
        napastnik.setSila(napastnik.getSila() + 3);
        super.kolizja(napastnik);
    }
}
