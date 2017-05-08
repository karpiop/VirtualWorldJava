/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wirtualnyswiat;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author pawel
 */
public class Czlowiek extends Zwierze implements KeyListener{
    private int niesmiertelnosc;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(swiat.getClass() == Swiat.class){
            switch(e.getKeyCode()){
                case KeyEvent.VK_UP:
                    kierunek = Kierunek.WGORE;
                    break;
                case KeyEvent.VK_DOWN:
                    kierunek = Kierunek.WDOL;
                    break;
                case KeyEvent.VK_LEFT:
                    kierunek = Kierunek.WLEWO;
                    break;
                case KeyEvent.VK_RIGHT:
                    kierunek = Kierunek.WPRAWO;
                    break;
                default:
                    break;
            }
        }
        else if(swiat.getClass() == HexSwiat.class){
            switch(e.getKeyCode()){
                case KeyEvent.VK_NUMPAD1:
                    kierunek = Kierunek.HEX1;
                    break;
                case KeyEvent.VK_NUMPAD3:
                    kierunek = Kierunek.HEX3;
                    break;
                case KeyEvent.VK_NUMPAD7:
                    kierunek = Kierunek.HEX7;
                    break;
                case KeyEvent.VK_NUMPAD9:
                    kierunek = Kierunek.HEX9;
                    break;
                case KeyEvent.VK_NUMPAD4:
                    kierunek = Kierunek.WLEWO;
                    break;
                case KeyEvent.VK_NUMPAD6:
                    kierunek = Kierunek.WPRAWO;
                    break;
                default:
                    break;
            }
        }
        switch(e.getKeyCode()){
            case KeyEvent.VK_SPACE:
                if (niesmiertelnosc == 0) {
                        niesmiertelnosc = 9;
                        swiat.dodajLog("tura " + swiat.getWiek() + ": " + "niesmiertelnosc aktywowana");
                }
                else {
                        swiat.dodajLog("tura " + swiat.getWiek() + ": " + "niesmiertelnosc niedostepna");
                }
                swiat.rysujSwiat();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private enum Kierunek{
        WGORE, WDOL, WPRAWO, WLEWO, HEX7, HEX1, HEX3, HEX9 
    }
    private Kierunek kierunek;
    public Czlowiek(int x, int y, Swiat swiat) {
	sila = 5;
	inicjatywa = 4;
        polozenie = new Point(x,y);
        poprzedniePolozenie = new Point(x,y);
	this.swiat = swiat;
        niesmiertelnosc = 0;
	gatunek = 'C';
        ikona = new ImageIcon("man.png");
        kierunek = Kierunek.WPRAWO;
    }       
    @Override
    public void akcja() {
	poprzedniePolozenie.x = polozenie.x;
	poprzedniePolozenie.y = polozenie.y;
	Point p = polozenie.getLocation();
	switch (kierunek) {
            case WGORE:
                if (polozenie.y > 0)
                    p.y--;
                break;
            case WDOL:
                if (polozenie.y + 1 < swiat.getWysokosc())
                    p.y++;
                break;
            case WLEWO:
                if (polozenie.x > 0)
                    p.x--;
                break;
            case WPRAWO:
                if (polozenie.x + 1 < swiat.getSzerokosc())
                    p.x++;
                break;
            case HEX1:
                if(polozenie.y % 2 == 0){
                    if(polozenie.x-1 >=0 && polozenie.y + 1 < swiat.getWysokosc()){
                        p.x--;
                        p.y++;
                    }
                }
                else{
                    if(polozenie.y + 1 < swiat.getWysokosc())
                        p.y++;
                }
                break;
            case HEX7:
                if(polozenie.y % 2 == 0){
                    if(polozenie.x - 1 >=0 && polozenie.y - 1 >= 0){
                        p.x--;
                        p.y--;
                    }
                }
                else {
                    if(polozenie.y - 1 >=0)
                        p.y--;
                }
            break;
            case HEX3:
                if(polozenie.y % 2 == 1){
                    if(polozenie.x+1 <swiat.getSzerokosc() && polozenie.y + 1 < swiat.getWysokosc()){
                        p.x++;
                        p.y++;
                    }
                }
                else{
                    if(polozenie.y + 1 < swiat.getWysokosc())
                        p.y++;
                }
                break;
            case HEX9:
                if(polozenie.y % 2 == 1){
                    if(polozenie.x + 1 < swiat.getSzerokosc() && polozenie.y - 1 >= 0){
                        p.x++;
                        p.y--;
                    }
                }
                else {
                    if(polozenie.y - 1 >=0)
                        p.y--;
                }
            break;
            default:
                break;
	}
	Organizm o = swiat.getOrganizmPoPolozeniu(p);
	polozenie = p.getLocation();
	if (o != null  && o != this)
		o.kolizja(this);
	wiek++;
	if (niesmiertelnosc > 0)
		niesmiertelnosc--;
    }
    @Override
    public void kolizja(Zwierze o) {
	String log = new String();
	log += "tura " + swiat.getWiek() + ": ";
	int x, y;
        Random rand = new Random();
        ArrayList<Point> sasiedzi = swiat.getSasiedniePola(polozenie);
        boolean wolnePole = false;
        for(Point p : sasiedzi)
            if(swiat.getOrganizmPoPolozeniu(p) == null){
                wolnePole = true;
            }
	if (sila <= o.getSila() && niesmiertelnosc >= 5 
		&& wolnePole){		
            Point p;
            do {
                p = sasiedzi.get(rand.nextInt(sasiedzi.size())).getLocation();
            } while (swiat.getOrganizmPoPolozeniu(p) != null);
            polozenie = p.getLocation();
            log += gatunek;
            log += " ucieka przed ";
            log += o.getGatunek();
            swiat.dodajLog(log);
	}
	else
            super.kolizja(o);
    }
}
