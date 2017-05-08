/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wirtualnyswiat;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author pawel
 */
public class HexSwiat extends Swiat {

    public HexSwiat(int sz, int w, int poIle) {
        super(sz,w,poIle);
        for(int i=0; i<sz; i++)
            for(int j=0; j<w; j++){
                frame.remove(buttons[i][j]);
                buttons[i][j] = new HexButton();
                if(j%2==0)
                    buttons[i][j].setBounds(32*i, 24*2*j+ 64, 32, 2*32);
                else
                    buttons[i][j].setBounds(32*i+16, 24*2*j + 64, 32, 2*32);
                final Swiat swiat = this;
                final int x = i;
                final int y = j;
                buttons[i][j].addActionListener((ActionEvent e) -> {
                    JButton button = (JButton) e.getSource();
                    if(button.getIcon() == null){
                        for(int k=0; k<9; k++)
                            if(radioButtons[k].isSelected()){
                                switch(k){
                                    case 0:
                                        dodajOrganizm(new Wilk(x,y,swiat));
                                        break;
                                    case 1:
                                        dodajOrganizm(new Owca(x,y,swiat));
                                        break;
                                    case 2:
                                        dodajOrganizm(new Lis(x,y,swiat));
                                        break;
                                    case 3:
                                        dodajOrganizm(new Zolw(x,y,swiat));
                                        break;
                                    case 4:
                                        dodajOrganizm(new Antylopa(x,y,swiat));
                                        break;
                                    case 5:
                                        dodajOrganizm(new Trawa(x,y,swiat));
                                        break;
                                    case 6:
                                        dodajOrganizm(new Mlecz(x,y,swiat));
                                        break;
                                    case 7:
                                        dodajOrganizm(new Guarana(x,y,swiat));
                                        break;
                                    case 8:
                                        dodajOrganizm(new WilczeJagody(x,y,swiat));
                                        break;
                                }
                                break;
                            }
                    }
                    rysujSwiat();
                    frame.setVisible(true);
                    frame.toFront();
                    frame.requestFocus();
                });
                frame.add(buttons[i][j]);
            }
        rysujSwiat();
        frame.setVisible(true);
        frame.toFront();
        frame.requestFocus();
    }
    @Override    
    public ArrayList<Point> getSasiedniePola(Point p){
        ArrayList<Point> lista = new ArrayList<>();
        int x = p.x;
        int y = p.y;
        if(x - 1 >= 0)
            lista.add(new Point(x - 1, y));
        if(x + 1 < getSzerokosc())
            lista.add(new Point(x + 1, y));
        if(y - 1 >= 0)
            lista.add(new Point(x, y - 1));
        if(y + 1 < getWysokosc())
            lista.add(new Point(x, y + 1));
        if(y % 2 == 0){
            if(x - 1 >= 0 && y - 1 >= 0){
                lista.add(new Point(x - 1, y - 1));
            }
            if(x - 1 >=0 && y + 1 < getWysokosc()){
                lista.add(new Point(x - 1, y + 1));
            }
        }
        else {
            if(x + 1 < getSzerokosc() && y - 1 >= 0){
                lista.add(new Point(x + 1, y - 1));
            }
            if(x + 1 < getSzerokosc() && y + 1 < getWysokosc()){
                lista.add(new Point(x + 1, y + 1));
            }
        }
        return lista;
    }
            
    
}
