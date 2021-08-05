package krtice;

import java.awt.Color;
import java.awt.Graphics;

public class Krtica extends Zivotinja {

	public Krtica(Rupa rupa) {
		super(rupa);
	}
	
	@Override
	public void iscrtaj(int x, int y, int sirina, int visina) {
        Graphics g = rupa.getGraphics();
        g.setColor(Color.DARK_GRAY);
        g.fillOval(x, y, sirina, visina);
	}

	@Override
	public void udarenaZivotinja() {
		udarena = true;
		rupa.zaustaviNit();
	}

	@Override
	public void pobeglaZivotinja() {
		rupa.basta.smanjiKolicinu();
	}

}
