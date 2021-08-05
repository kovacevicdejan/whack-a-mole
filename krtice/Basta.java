package krtice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;

public class Basta extends Panel implements Runnable {

	private Rupa[][] rupe;
	private int brojVrsta;
	private int brojKolona;
	int kolicina = 100;
	private int intervalCekanja = 1000;
	private int brojKoraka = 10;
	Thread nit = null;
	private boolean pokrenuta = false;
	private Igra vlasnik;
	
	Basta(int brojVrsta, int brojKolona) {
		this.brojVrsta = brojVrsta;
		this.brojKolona = brojKolona;
		
		setBackground(Color.GREEN);
		setLayout(new GridLayout(4, 4, 15, 15));
		setPreferredSize(new Dimension(400, 400));
		
		rupe = new Rupa[brojVrsta][brojKolona];
		for(int i = 0; i < brojVrsta; i++) {
			for(int j = 0; j < brojKolona; j++) {
				rupe[i][j] = new Rupa(this);
				add(rupe[i][j]);
			}
		}
	}

	public int getBrojKoraka() {
		return brojKoraka;
	}

	public void setBrojKoraka(int brojKoraka) {
		this.brojKoraka = brojKoraka;
		for(int i = 0; i < brojVrsta; i++) {
			for(int j = 0; j < brojKolona; j++) {
				rupe[i][j].setBrojKoraka(brojKoraka);
			}
		}
	}

	public void setIntervalCekanja(int intervalCekanja) {
		this.intervalCekanja = intervalCekanja;
	}
	
	public void smanjiKolicinu() {
		if(kolicina == 0) {
			if(pokrenuta)
			    zaustavi();
		}
		else {
			kolicina--;
			vlasnik.povrce.setText("Povrce: " + kolicina);
		}
	}

	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Thread.sleep(intervalCekanja);
				int i = (int)(Math.random() * 4);
				int j = (int)(Math.random() * 4);
				if(!rupe[i][j].nitPokrenuta()) {
					rupe[i][j].setZivotinja(new Krtica(rupe[i][j]));
					rupe[i][j].napraviNit();
					rupe[i][j].pokreniNit();
					intervalCekanja = (int)(intervalCekanja * 0.99);
				}
			}
		} catch (InterruptedException e) {}
	}
	
	public synchronized void pokreni() {
		pokrenuta = true;
		nit = new Thread(this);
		nit.start();
	}
	
	public void zaustavi() {
		pokrenuta = false;
		for(int i = 0; i < brojVrsta; i++) {
			for(int j = 0; j < brojKolona; j++) {
				if(rupe[i][j].nitPokrenuta())
				    rupe[i][j].zaustaviNit();
			}
		}
		nit.interrupt();
		nit = null;
		vlasnik.kreni = true;
		vlasnik.dugme.setLabel("Kreni");
	}
	
	public boolean pokrenuta() {
		return pokrenuta;
	}
	
	public void postaviVlasnika(Igra i) {
		vlasnik = i;
	}
	
	public void postaviKolicinu(int k) {
		kolicina = k;
	}
	
}
