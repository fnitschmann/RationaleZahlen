package uebRatio;
/**
 * @author Florian Nitschmann
 * Email: s0544677@htw-berlin.de
 * @version Eclipse Kepler, JavaSE-1.7
 * @since 2013-12-16
 * Last Change: 2013-12-17
 */

public class Ratio implements IRatio {
  // Attribute
  private int zaehler;
  private int nenner;
  
  // Getter & Setter
  
  /** getNenner
   * ----
   * @return (int): Nenner der rationalen Zahl
   * ----
   * Getter des aktuellen Nenners der rationalen Zahl
   */
  public int getNenner() {
    return this.nenner;
  }
  
  /** setNenner
   * ----
   * @param (int): Neuer Nenner
   * ----
   * Setter zum Überschreiben des Nenners der rationalen Zahl
   */
  public void setNenner(int nenner) {
    this.nenner = nenner;
  }
  
  /** getZaehler
   * ----
   * @return (int): Zähler der rationalen Zahl
   * ----
   * Getter des aktuellen Zählers der rationalen Zahl
   */
  public int getZaehler() {
    return this.zaehler;
  }
  
  /** setZaehler
   * ----
   * @param zaehler (int): Neuer Zähler
   * ----
   * Setter zum Überschreiben des Zählers der rationalen Zahl
   */
  public void setZaehler(int zaehler) {
    this.zaehler = zaehler;
  }
  
  /** toString
   * ----
   * @return (String): Rationale Zahl als String geschrieben
   * ----
   * Umwandler der rationalen Zahl in einen String im Format 'Zähler/Nenner'
   */
  public String toString() {
    return String.valueOf(this.zaehler) + "/" + String.valueOf(this.nenner);
  }
  
  // Konstruktoren
  
  /** Konstruktor mit Parametern
   * ----
   * @param zaehler (int): Zähler der neuen rationalen Zahl
   * @param nenner (int): Nenner der neuen rationalen Zahl
   * ----
   * Initilisiert eine neue rationale Zahl mit den gegebenen Parametern 
   */
  public Ratio(int zaehler, int nenner) {
    this.setZaehler(zaehler);
    this.setNenner(nenner);
  }
  
  /** Default Konstruktor ohne Parameter
   * Initilisiert eine neue rationale Zahl mit 0 als Zähler und 1 als Nenner
   */
  public Ratio() {
    this.setZaehler(0);
    this.setNenner(1);
  }
  
  // mathematische Operationen
  
  /** addiere
   * ----
   * @param zahl2 (Ratio): Summand 2
   * @return (Ratio): Summe als neue rationale Zahl
   * ----
   * Errechnet die Summe aus zwei rationalen Zahlen
   */
  public Ratio addiere(Ratio zahl2) {
    int zaehler = (this.zaehler*zahl2.getNenner())+(this.nenner*zahl2.getZaehler());
    int nenner = this.nenner*zahl2.getNenner();
    return new Ratio(zaehler, nenner);
  }
  
  /** subtrahiere 
   * ----
   * @param zahl2 (Ratio): Subtrahend
   * @return (Ratio): Differenz als neue rationale Zahl
   * ----
   * Errechnet die Differenz aus zwei rationalen Zahlen
   */
  public Ratio subtrahiere(Ratio zahl2) {
    int zaehler = (this.zaehler*zahl2.getNenner())-(this.nenner*zahl2.getZaehler());
    int nenner = this.nenner*zahl2.getNenner();
    return new Ratio(zaehler, nenner);
  }
  
  /** multipliziere
   * ----
   * @param zahl2 (Ratio): Faktor
   * @return (Ratio): Produkt als neue rationale Zahl
   * -----
   * Errechnet den Faktor aus zwei rationalen Zahlen
   */
  public Ratio multipliziere(Ratio zahl2) {
    int zaehler = this.zaehler*zahl2.getZaehler();
    int nenner = this.nenner*zahl2.getNenner();
    return new Ratio(zaehler, nenner);
  }
  
  /** dividiere
   * ----
   * @param zahl2 (Ratio): Divisor
   * @return (Ratio): Quotient als neue rationale Zahl
   * ----
   * Errechnet den Quotient aus zwei rationalen Zahlen
   * 
   */
  public Ratio dividiere(Ratio zahl2) {
    int zaehler = this.zaehler*zahl2.getNenner();
    int nenner = this.nenner*zahl2.getZaehler();
    return new Ratio(zaehler, nenner);
  }
  
  /** kuerze
   * ----
   * @return (Ratio): Gekürzte rationale Zahl
   * ----
   * Kürzt eine rationale Zahl mit Hilfe des euklidischen Algorithmus
   */
  public Ratio kuerze() {
    int i;
    int n = Math.abs(nenner);
    int z = Math.abs(zaehler);
      
    while(z > 0) {
      if(z < n) {
        i = n;
        n = z;
        z = i;
      }
      z = z-n;
    }    
    nenner = nenner/n;
    zaehler = zaehler/n;
    return this;
  }
  
  // Vergleichsmethode

  /** equals
   * ----
   * @param obj
   * @return boolean
   * ----
   * Überprüft ob Nenner & Zähler von zwei rationalen Zahlen identisch sind 
   */
  public boolean equals(Ratio obj) {
    Ratio zahl1 = this.kuerze();
    Ratio zahl2 = obj.kuerze();
    if(zahl1.getZaehler() == zahl2.getZaehler() && zahl2.getNenner() == zahl2.getNenner()) return true;
    else return false;
  }
} // end of class
