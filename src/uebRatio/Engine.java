package uebRatio;
/**
 * @author Florian Nitschmann
 * Email: s0544677@htw-berlin.de
 * @version Eclipse Kepler, JavaSE-1.7
 * @since 2013-12-19
 * Last Change: 2013-12-26
 */
import java.util.ArrayList;
import java.lang.System;
import java.io.*;

public class Engine {
  private ArrayList<String[]> liste = new ArrayList<String[]>();
  
  /** losgehts
   * ----
   * Initiale Methode zur Ausführung der Engine
   * Diese Methode kann und soll beliebig oft durchlaufen werden und dient zur Berechnung von rationalen Zahlen.
   * Diese werden in einem Speicher (ArrayList) in einzelnen Arrays gespeichert, welche auch durch diese Methode initialisiert werden.
   * ----
   */
  public void losgehts() {
    this.initSpeicher();
    this.saveRatioZahl(this.rationaleZahlEinlesen());
    this.saveRatioZahl(this.rationaleZahlEinlesen());
    this.saveOperator(this.operatorEinlesen());
    this.berechneErgebnis();
    this.weiter();
  }
  
  // Speicherverwaltung
  
  /** initSpeicher
   * ----
   * Initialisiert einen neuen Speicherplatz (Array) mit 4 Plätzen in der Speicherliste zum
   * Ablegen von zwei rationalen Zahlen [0,1], deren Ergebnis [2] und dem dazu gehörigen Operator [3] als Strings.
   * Zuvor wird die Speicherliste mit Hilfe von cleanUpSpeicher() aufgeräumt.
   * ---- 
   */ 
  public void initSpeicher() {
    this.cleanUpSpeicher();
    String[] eintrag = new String[4];
    this.liste.add(eintrag);
  }
  
  /** cleanUpSpeicher
   * ----
   * Überprüft ob sich in der Speicherliste noch Arrays befinden, welche leere Felder 
   * (null) beinhalten und bereinigt diese ggf. 
   * ----
   */
  private void cleanUpSpeicher() {
    for(int listIndex = 0; listIndex < this.liste.size(); listIndex++) {
      if(this.isIncompleteEntry(this.liste.get(listIndex))) {
        this.liste.remove(listIndex);
        listIndex -= 1;
      }
    }
  }
  
  /** freiesRatioSpeicherElement
   * ----
   * @param eintrag (String[]): Ein Array als Eintrag in der Speicherliste
   * @return int: Index des freien Feldes im Eintrag
   * ----
   * Liefert den Index des ersten freien Elements in einem Speicherarray aus der Speicherliste
   * zurück, welches für ein Ratio Objekt in String-Form vorgesehen ist
   */
  private int freiesRatioSpeicherElement(String[] eintrag) {
    int element = 0;
    for(int i = 0; i < eintrag.length-1; i++) {
      if(eintrag[i] == null) {
        element = i;
        break;
      }
      else continue;
    }
    return element;
  }
  
  /** freierOperatorSpeicherplatz
   * ----
   * @param eintrag (String[]): Ein Array als Eintrag in der Speicherliste
   * @return boolean
   * ----
   * Überprüft ob im Eintrags Array in der Speicherliste der Speicherplatz für den Operator [Index 3]
   * noch frei (null) und belegbar ist
   */
  private boolean freierOperatorSpeicherplatz(String[] eintrag) {
    boolean frei = false;
    if(eintrag[3] == null) frei = true;
    return frei;
  }
  
  /** updateSpeicherEintrag
   * ----
   * @param eintrag (String[]): Aktuelles Array für den Speicher welches ein Altes überschreiben soll
   * ----
   * Überschreibt das aktuelle, im Engine Durchlauf in der losgehts() Methode initiierte, Speicher Array in der Speicherliste
   * mit einem anderen.
   */
  private void updateSpeicherEintrag(String[] eintrag) {
    this.liste.set(this.liste.size()-1, eintrag);
  }
  
  // Speichermethoden
  
  /** saveRatioZahl
   * ----
   * @param z (Ratio): Zu speichernde rationale Zahl
   * ----
   * Speichert eine rationale Zahl z im Form eines String im letzten Array (dem Aktuellsten) der Speicherliste,
   * wenn dieses noch einen freien Speicherplatz für eine rationale Zahl hat.
   */
  public void saveRatioZahl(Ratio z) {
    String[] eintrag = this.liste.get(this.liste.size()-1);
    try {
      if(this.isIncompleteEntry(eintrag)) {
        eintrag[this.freiesRatioSpeicherElement(eintrag)] = z.toString();
        this.updateSpeicherEintrag(eintrag);
      }
      else throw new Exception("Kein freier Speicherplatz vorhanden!");
    } catch(Exception e) { System.out.println(e); }
  }

  /** saveOperator
   * ----
   * @param op (String): Zu speichernder Operator als String
   * ----
   * Speichert den Operator im letzten Array (dem Aktuellsten) der Speicherliste,
   * wenn dieses noch einen freien Speicherplatz für einen Operator [3] hat.
   */
  public void saveOperator(String op) {
    String[] eintrag = this.liste.get(this.liste.size()-1);
    try {
      if(this.isIncompleteEntry(eintrag) && this.freierOperatorSpeicherplatz(eintrag)) {
        eintrag[3] = op;
        this.updateSpeicherEintrag(eintrag);
      }
      else throw new Exception("Operator kann nicht gespeichert werden!");
    } catch(Exception e) { System.out.println(e); }
  }
  
  // Eingabemethoden
  
  private Ratio rationaleZahlEinlesen() {
    BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("Bitte rationale Zahl eingeben in der Form z/n\n");
    String zeile = null;
    try {
      zeile = console.readLine();
      if(this.isRatioString(zeile)) return this.stringToRatio(zeile);
      else throw new Exception("Fehlerhafte Eingabe!");      
    } catch(Exception e) { System.out.print(e); }
    return null;
  }
  
  private String operatorEinlesen() {
    BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("Bitte Operator eingeben [+-*/]\n");
    String eingabe = null;
    try {
      eingabe = console.readLine();
      if(this.isOperationString(eingabe)) return eingabe; 
      else throw new Exception("Fehlerhafte Eingabe des Operators!");      
    } catch(Exception e) { System.out.println(e); }
    return null;
  }

  private void weiter() {
    BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("weiter?(j/n)\n");
    try {
      String eingabe = console.readLine();
      if(this.isNextString(eingabe.toLowerCase())) {
        if(eingabe.equals("j")) this.losgehts();
        else this.ergebnisListe();
      }
      else throw new Exception("Fehlerhafte Eingabe, bitte j oder n eingeben!");
    } catch(Exception e) { System.out.println(e); }
  }
  
  private Ratio stringToRatio(String str) {
    int teilerZeichenPos = str.indexOf("/");
    int zaehler = Integer.valueOf(str.substring(0, teilerZeichenPos));
    int nenner = Integer.valueOf(str.substring(teilerZeichenPos+1, str.length()));
    return new Ratio(zaehler, nenner);
  }
  
  // Berechnung und Ergebnisse
  
  private void berechneErgebnis() {
    String[] eintrag = this.liste.get(this.liste.size()-1);
    int freiesSpeicherElement = this.freiesRatioSpeicherElement(eintrag);
    try {
      if(freiesSpeicherElement == 2) {
        Ratio zahl1 = this.stringToRatio(eintrag[0]);
        Ratio zahl2 = this.stringToRatio(eintrag[1]);
        String operator = eintrag[3];
        Ratio ergebnis = this.ergebnis(zahl1, zahl2, operator);
        eintrag[2] = ergebnis.toString(); 
        this.updateSpeicherEintrag(eintrag);
        System.out.println(ergebnis.toString());
      }
      else throw new Exception("Ergebnis kann nicht berechnet werden!");
    } catch(Exception e) { System.out.println(e); } 
  }
  
  // Methoden für Ergebnisse
  
  private Ratio ergebnis(Ratio zahl1, Ratio zahl2, String operator) {
    Ratio ergebnis = null;
    switch(operator) {
      case "+":
        ergebnis = zahl1.addiere(zahl2);
        break;
      case "-":
        ergebnis = zahl1.subtrahiere(zahl2);
        break;
      case "*":
        ergebnis = zahl1.multipliziere(zahl2);
        break;
      case "/":
        ergebnis = zahl1.dividiere(zahl2);
        break;
    }
    return ergebnis.kuerze();
  }
  
  private void ergebnisListe() {
    this.cleanUpSpeicher();
    if(this.liste.isEmpty()) {
      System.out.println("Keine Berechnungen bisher durchgeführt.");
      this.losgehts();
    }
    else {
      System.out.println("----berechnet wurden : -----------");
      for(int i = 0; i < this.liste.size(); i++) {
        System.out.println(this.restore(i) + "\n");
      }
    }
  }
  
 // Ausgabe
  
  /** restore
   * ----
   * @param index (int): Index des Eintrags in der Speicherliste
   * @return (String): Der Speichereintrag unter dem Index als zusammengesetzter String
   * ----
   * Setzt einen Speichereintrag unter dem Index in der Form 
   * zahl1 operator zahl2 [=] ergebnis als String zusammen
   */
  public String restore(int index) {
    String eintrag = null;
    if(this.liste.isEmpty()) System.out.println("Keine Ergebnisse bisher vorhanden!");
    else {
      String[] element = this.liste.get(index);
      eintrag = element[0] + " " + element[3] + " " + element[1] + " = " + element[2];
    }
    return eintrag;
  }
  
  // Überprüfungsmethoden
  
  /** isRatioString
   * ----
   * @param str (String): Zu prüfender String
   * @return boolean
   * ----
   * Überprüft ob ein String einer rationalen Zahl in der Form n/z entspricht,
   * wobei nur 
   */
  private boolean isRatioString(String str) {
    return str.matches("(^-?[0-9]{1,})/(-?[0-9]{1,})");
  }
  
  /** isOperationString
   * ----
   * @param str (String): Zu prüfender String
   * @return boolean
   * ----
   * Überprüft ob ein String einem Operator +, -, * oder / entspricht
   */
  private boolean isOperationString(String str) {
    return str.matches("(^(?=.{1,1}$)(?:[/+/\\-/*//]))");
  }
  
  /** isNextString
   * ----
   * @param str (String): Zu prüfender String
   * @return boolean
   * ----
   * Überprüft ob ein String j oder n entspricht 
   * (Benutzereingabe für weiter)
   */
  private boolean isNextString(String str) {
    return str.matches("(^(?=.{1,1}$)(?:[/j/n]))");
  }
  
  private boolean isIncompleteEntry(String[] speicherEintrag) {
    boolean incomplete = false;
    for(int i = 0; i < speicherEintrag.length; i++) {
      String eintrag = speicherEintrag[i];
      if(eintrag == null) {
        incomplete = true;
        break;
      }
    }
    return incomplete;
  }
} // end of class
