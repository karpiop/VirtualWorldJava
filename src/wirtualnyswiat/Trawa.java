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
public class Trawa extends Roslina {
    public Trawa(int x, int y, Swiat swiat) {
	sila = 0;
	inicjatywa = 0;
        polozenie = new Point(x,y);
	this.swiat = swiat;
	gatunek = 'T';
        ikona = new ImageIcon("grass.png");
    }
}
