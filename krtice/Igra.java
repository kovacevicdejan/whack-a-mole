package krtice;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Igra extends Frame {

	Button dugme = new Button("Start");
	Label povrce = new Label();
	Basta basta = new Basta(4, 4);
	private static Igra jedina = new Igra();
	boolean kreni = true;

	private Igra() {
		setBounds(400, 100, 600, 400);
		setResizable(false);
		setTitle("Whack-A-Mole");
		namestiProzor();
		pack();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(basta.pokrenuta())
				    basta.zaustavi();
				dispose();
			}
		});
		
		setVisible(true);
	}
	
	public void namestiProzor() {
		Panel zapadni = new Panel(new GridLayout(2, 1));
		Panel gornji = new Panel(new GridLayout(5, 1));
		Panel donji = new Panel(new BorderLayout());
		
		Panel tezina = new Panel();
		Label t = new Label("Difficulty: ");
		t.setFont(new Font("Arial", Font.BOLD, 12));
		tezina.add(t);
		gornji.add(tezina);
		
		CheckboxGroup tezine = new CheckboxGroup();
		Checkbox lako = new Checkbox("Easy", true, tezine);
		Checkbox srednje = new Checkbox("Middle", false, tezine);
		Checkbox tesko = new Checkbox("Hard", false, tezine);
		
		lako.addItemListener((ie) -> {
			if(ie.getStateChange() == ItemEvent.SELECTED && !basta.pokrenuta()) {
				basta.setBrojKoraka(10);
				basta.setIntervalCekanja(1000);
			}
		});
		
		srednje.addItemListener((ie) -> {
			if(ie.getStateChange() == ItemEvent.SELECTED && !basta.pokrenuta()) {
				basta.setBrojKoraka(8);
				basta.setIntervalCekanja(750);
			}
		});
		
		tesko.addItemListener((ie) -> {
			if(ie.getStateChange() == ItemEvent.SELECTED && !basta.pokrenuta()) {
				basta.setBrojKoraka(6);
				basta.setIntervalCekanja(500);
			}
		});
		
		gornji.add(lako);
		gornji.add(srednje);
		gornji.add(tesko);
		
		dugme.addActionListener((ae) -> {
			if(kreni) {
				kreni = false;
			    dugme.setLabel("Stop");
			    povrce.setText("Vegetables: " + 100);
			    basta.postaviKolicinu(100);
				basta.pokreni();
			}
			else {
				kreni = true;
				dugme.setLabel("Start");
				basta.zaustavi();
			}
		
		});
		gornji.add(dugme);
		zapadni.add(gornji);
		
		povrce.setAlignment(Label.CENTER);
		povrce.setFont(new Font("Arial", Font.BOLD, 12));
		povrce.setText("Vegetables: " + basta.kolicina);
		donji.add(povrce, BorderLayout.CENTER);
		
		zapadni.add(donji);
		zapadni.setPreferredSize(new Dimension(100, 300));
		add(zapadni, BorderLayout.EAST);
		basta.postaviVlasnika(this);
		add(basta, BorderLayout.CENTER);
		
	}
	
	public static Igra dohvatiIgru() {
		return jedina;
	}
	
	public static void main(String[] args) {
	    
	}
	
}
