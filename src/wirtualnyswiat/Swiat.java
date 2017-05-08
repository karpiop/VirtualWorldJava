/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wirtualnyswiat;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;


/**
 *
 * @author pawel
 */
class Swiat implements KeyListener, java.io.Serializable {
    private List<Organizm> kontenerOrganizmow;
    private List<JLabel> logi;
    private int szerokosc;
    private int wysokosc;
    private int wiek;
    protected JFrame frame;
    protected AbstractButton[][] buttons;
    private int licznik;
    protected JRadioButton[] radioButtons;
    protected final String saveFile = "swiat.ser";
    public Swiat(int sz, int w, int poIle){
        szerokosc = sz;
        wysokosc = w;
        kontenerOrganizmow = new ArrayList<>();
        Random rand = new Random();
        logi = new LinkedList<>();
        wiek = 0;
        frame = new JFrame("Paweł Karpinski 155085");
        frame.setIconImage(new ImageIcon("sheep.png").getImage());
        buttons = new JButton[sz][w];
        for(int i=0; i<sz; i++)
            for(int j=0; j<w; j++){
                buttons[i][j] = new JButton();
                buttons[i][j].setBounds(32*i, 32*(j + 2), 32, 32);
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
                        frame.setVisible(true);
                        frame.toFront();
                        frame.requestFocus();
                        rysujSwiat();
                    }
                });
                frame.add(buttons[i][j]);
            }
        frame.setSize(800,700);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.addKeyListener(this);
        frame.setFocusable(true);
        Czlowiek czlowiek = new Czlowiek(rand.nextInt(sz),rand.nextInt(w),this);
        kontenerOrganizmow.add(czlowiek);
        frame.addKeyListener(czlowiek);
        for(int i = 0; i < poIle; i++){
            kontenerOrganizmow.add(new Wilk(rand.nextInt(sz),rand.nextInt(w),this));
            kontenerOrganizmow.add(new Owca(rand.nextInt(sz),rand.nextInt(w),this));
            kontenerOrganizmow.add(new Antylopa(rand.nextInt(sz),rand.nextInt(w),this));
            kontenerOrganizmow.add(new Trawa(rand.nextInt(sz),rand.nextInt(w),this));
            kontenerOrganizmow.add(new Guarana(rand.nextInt(sz),rand.nextInt(w),this));
            kontenerOrganizmow.add(new Lis(rand.nextInt(sz),rand.nextInt(w),this));
            kontenerOrganizmow.add(new Mlecz(rand.nextInt(sz),rand.nextInt(w),this));
            kontenerOrganizmow.add(new WilczeJagody(rand.nextInt(sz),rand.nextInt(w),this));
            kontenerOrganizmow.add(new Zolw(rand.nextInt(sz),rand.nextInt(w),this));
        }        
        JButton nastepnaTuraButton;
        frame.add(nastepnaTuraButton = new JButton("Nastepna tura"));
        nastepnaTuraButton.setBounds(4*32, 0, 128, 2*32);
        nastepnaTuraButton.setSelected(true);
        nastepnaTuraButton.addActionListener((ActionEvent e) -> {
            wykonajTure();
            rysujSwiat();
            frame.setVisible(true);
            frame.toFront();
            frame.requestFocus();
        });        
        JButton zapiszButton;
        frame.add(zapiszButton = new JButton("Zapisz"));
        zapiszButton.setBounds(0, 0, 32*4, 32);
        zapiszButton.setSelected(true);
        zapiszButton.addActionListener((ActionEvent e) -> {
            try {
                FileOutputStream fileOut = new FileOutputStream(saveFile);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(this);
                out.close();
                fileOut.close();
                dodajLog("tura: " + wiek + " zapisano");
            } catch(IOException i) {
                i.printStackTrace();
            }
            frame.setVisible(true);
            frame.toFront();
            frame.requestFocus();
        });
        JButton wczytajButton;
        frame.add(wczytajButton = new JButton("Wczytaj"));
        wczytajButton.setBounds(0, 32, 32*4, 32);
        wczytajButton.setSelected(true);
        final Swiat swiat = this;
        wczytajButton.addActionListener((ActionEvent e) -> {
            try {
                FileInputStream fileIn = new FileInputStream(saveFile);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Swiat sw;
                sw = (Swiat) in.readObject();
                {
                    szerokosc = sw.szerokosc;
                    wysokosc = sw.wysokosc;
                    for(Component[] c : buttons)
                        for(Component button : c)
                            frame.remove(button);
                    for(int i=0; i<szerokosc; i++)
                        for(int j=0; j<wysokosc; j++){
                            frame.add(sw.buttons[i][j]);
                            sw.buttons[i][j].removeAll();
                            final int x = i;
                            final int y = j;
                            sw.buttons[i][j].addActionListener((ActionEvent ae) -> {
                                JButton button = (JButton) ae.getSource();
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
                                    frame.setVisible(true);
                                    frame.toFront();
                                    frame.requestFocus();
                                    rysujSwiat();
                                }
                            });
                        }
                    buttons = sw.buttons;
                    kontenerOrganizmow.clear();
                    kontenerOrganizmow = sw.kontenerOrganizmow;
                    for(Organizm o : kontenerOrganizmow){
                        o.setSwiat(swiat);
                        if(o.getGatunek() == 'C')
                            frame.addKeyListener((Czlowiek)o);
                    }
                    for(Component log : logi)
                        frame.remove(log);
                    logi = sw.logi;
                    wiek = sw.wiek;
                    dodajLog("tura: " + wiek + " wczytano");
                    rysujSwiat();
                }
                in.close();
                fileIn.close();
            }catch(IOException i)
            {
                i.printStackTrace();
                return;
            }catch(ClassNotFoundException c)
            {
                System.out.println("Class not found");
                c.printStackTrace();
                return;
            }
            frame.setVisible(true);
            frame.toFront();
            frame.requestFocus();
        });
        JLabel[] ikonki = new JLabel[9];
        radioButtons = new JRadioButton[9];
        ButtonGroup bg = new ButtonGroup();
        for(int i=0; i<9; i++){
            ikonki[i] = new JLabel();
            radioButtons[i] = new JRadioButton();
            ikonki[i].setBounds((8 + i)*32, 0, 32, 32);
            radioButtons[i].setBounds((8 + i)*32, 32, 32, 32);
            frame.add(ikonki[i]);
            frame.add(radioButtons[i]);
            bg.add(radioButtons[i]);
        }
        ikonki[0].setIcon(new ImageIcon("wolf.png"));
        ikonki[1].setIcon(new ImageIcon("sheep.png"));
        ikonki[2].setIcon(new ImageIcon("fox.png"));
        ikonki[3].setIcon(new ImageIcon("turtle.png"));
        ikonki[4].setIcon(new ImageIcon("deer.png"));
        ikonki[5].setIcon(new ImageIcon("grass.png"));
        ikonki[6].setIcon(new ImageIcon("mlecz.jpg"));
        ikonki[7].setIcon(new ImageIcon("guarana.png"));
        ikonki[8].setIcon(new ImageIcon("jagoda.jpg"));
        wykonajTure();
        rysujSwiat();
        
    }
    public Swiat(Swiat sw){
        //to do
    }
    public final void wykonajTure(){
        kontenerOrganizmow.sort(null);
        for(int licznik = 0; licznik < kontenerOrganizmow.size(); licznik++)
            kontenerOrganizmow.get(licznik).akcja();
        wiek++;
    }
    public final void rysujSwiat(){
        for(int i=0; i<szerokosc; i++)
            for(int j=0; j<wysokosc; j++)
                buttons[i][j].setIcon(null);
        kontenerOrganizmow.stream().forEach((o) -> {
            o.rysowanie();
        });
        frame.repaint();
    }
    public int getSzerokosc(){
        return szerokosc;
    }
    public int getWysokosc(){
        return wysokosc;
    }
    public Organizm getOrganizmPoPolozeniu(Point p){
        for(Organizm o : kontenerOrganizmow)
            if(o.getPolozenie().x == p.x && o.getPolozenie().y == p.y)
                return o;
        return null;
    }
    public int getWiek(){
        return wiek;
    }
    final void dodajLog(String log) {
        JLabel l = new JLabel();
        l.setBounds((szerokosc + 1)*32, (20 + 1)*32, 256, 32);
        l.setText(log);
        for(JLabel label : logi)
            label.setLocation(label.getLocation().x, label.getLocation().y - 32);
        logi.add(l);
        frame.add(l);
        if(logi.size() > 20){
            frame.remove(logi.get(0));
            logi.remove(0);
        }
    }
    void usunOrganizm(Organizm o) {  
        if(kontenerOrganizmow.indexOf(o)<=licznik)
            licznik--;
        kontenerOrganizmow.remove(o);
    }
    final void dodajOrganizm(Organizm o) {
        kontenerOrganizmow.add(o);
    }
    void setIkona(Point p, ImageIcon ikona){
        buttons[p.x][p.y].setIcon(ikona);
    }
    public ArrayList<Point> getSasiedniePola(Point p){
        ArrayList<Point> lista = new ArrayList<>();
        int x = p.x;
        int y = p.y;
        if(x - 1 >= 0)
            lista.add(new Point(x - 1, y));
        if(x + 1 < szerokosc)
            lista.add(new Point(x + 1, y));
        if(y - 1 >= 0)
            lista.add(new Point(x, y - 1));
        if(y + 1 < wysokosc)
            lista.add(new Point(x, y + 1));
        return lista;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            wykonajTure();
            rysujSwiat();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
