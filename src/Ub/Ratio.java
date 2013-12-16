package Ub;
/**
 * @author Florian Nitschmann
 * Email: s0544677@htw-berlin.de
 * @version Eclipse Kepler, JavaSE-1.7
 * @since 2013-12-16
 * Last Change: 2013-12-16
 */

public class Ratio {
	
	private int zaehler = 0;
	private int nenner = 1;

	public Ratio(int zaehler, int nenner) {
		this.setZaehler(zaehler);
		this.setNenner(nenner);
	}
	
	private void setZaehler(int zaehler) {
		this.zaehler = zaehler;
	}
	
	private void setNenner(int nenner) {
		this.nenner = nenner;
	}
	
}
