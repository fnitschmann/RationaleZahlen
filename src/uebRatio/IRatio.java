package uebRatio;
/**
 * @author Florian Nitschmann
 * Email: s0544677@htw-berlin.de
 * @version Eclipse Kepler, JavaSE-1.7
 * @since 2013-12-16
 * Last Change: 2013-12-16
 */

public interface IRatio {
  public Ratio addiere(Ratio zahl2);

  public Ratio subtrahiere(Ratio zahl2);

  public Ratio multipliziere(Ratio zahl2);

  public Ratio dividiere(Ratio zahl2) throws ArithmeticException;
  
  public Ratio kuerze();
} // end of interface
