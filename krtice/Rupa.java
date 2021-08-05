package krtice;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Rupa extends Canvas implements Runnable {

	Basta basta;
	private Zivotinja zivotinja = null;
	private int brojKoraka = 10;
	Thread nit = null;
	private boolean pokrenuta = false;
	private boolean proslo = false;
	
	public Rupa(Basta basta) {
		super();
		this.basta = basta;
		setBackground(new Color(102, 51, 00));
		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if(zivotinja != null)
				    zivotinja.udarenaZivotinja();
			}
			
		});
	}

	public Zivotinja getZivotinja() {
		return zivotinja;
	}

	public void setZivotinja(Zivotinja zivotinja) {
		this.zivotinja = zivotinja;
	}

	public int getBrojKoraka() {
		return brojKoraka;
	}

	public void setBrojKoraka(int brojKoraka) {
		this.brojKoraka = brojKoraka;
	}

	@Override
	public void run() {
		int sirina = 0;
		int visina = 0;
		int x = getWidth() / 2;
		int y = getHeight() / 2;
		int korakVisina = getHeight() / brojKoraka;
		int korakSirina = getWidth() / brojKoraka;
		int korakX = korakSirina / 2;
		int korakY = korakVisina / 2;
		
		try {
			for(int i = 0; i < brojKoraka; i++) {
				Thread.sleep(100);
				sirina += korakSirina;
				visina += korakVisina;
				x -= korakX;
				y -= korakY;
				if(i == brojKoraka - 1) {
					x = y = 0;
					sirina = getWidth();
					visina = getHeight();
				}
				zivotinja.iscrtaj(x, y, sirina, visina);
				revalidate();
			}
			Thread.sleep(2000);
			proslo = true;
			zaustaviNit();
		} catch (InterruptedException e) {}
 	}
	
	public void napraviNit() {
		nit = new Thread(this);
	}
	
	public synchronized void pokreniNit() {
		pokrenuta = true;
		proslo = false;
		nit.start();
	}
	
	public synchronized void zaustaviNit() {
		pokrenuta = false;
		nit.interrupt();
		if(!zivotinja.udarena() && proslo)
			zivotinja.pobeglaZivotinja();
		zivotinja = null;
		Graphics g = getGraphics();
		g.setColor(new Color(102, 51, 0));
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	public boolean nitPokrenuta() {
		return pokrenuta;
	}
	
}
