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
public class Mlecz extends Roslina{
    public Mlecz(int x, int y, Swiat swiat){        
	sila = 0;
	inicjatywa = 0;
        polozenie = new Point(x,y);
	this.swiat = swiat;
	gatunek = 'M';
        ikona = new ImageIcon("mlecz.jpg");
    }
    @Override
    public void akcja(){
        super.akcja();
        wiek--;
        super.akcja();
    }
}
