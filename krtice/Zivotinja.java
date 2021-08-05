package krtice;

public abstract class Zivotinja {

	protected Rupa rupa;
	protected boolean udarena = false;

	public Zivotinja(Rupa rupa) {
		this.rupa = rupa;
	}
	
	public abstract void iscrtaj(int x, int y, int sirina, int visina);
	
	public abstract void udarenaZivotinja();
	
	public abstract void pobeglaZivotinja();
	
	public boolean udarena() {
		return udarena;
	}
	
	public void setUdarena() {
		udarena = true;
	}
	
}
