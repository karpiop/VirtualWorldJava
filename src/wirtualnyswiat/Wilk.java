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
public class Wilk extends Zwierze{
    public Wilk(int x, int y, Swiat swiat) {
	sila = 9;
	inicjatywa = 5;
        polozenie = new Point(x,y);
        poprzedniePolozenie = new Point(x,y);
	this.swiat = swiat;
	gatunek = 'W';
        ikona = new ImageIcon("wolf.png");
    }    
}
