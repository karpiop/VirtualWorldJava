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
public class Owca extends Zwierze{
    public Owca(int x, int y, Swiat swiat) {
	sila = 4;
	inicjatywa = 4;
        polozenie = new Point(x,y);
        poprzedniePolozenie = new Point(x,y);
	this.swiat = swiat;
	gatunek = 'O';
        ikona = new ImageIcon("sheep.png");
    }    
}
