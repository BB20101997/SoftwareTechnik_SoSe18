package abgabe03.caller ;
import abgabe03.callee.Callee ;
  
public class Caller {
  public static void main(String [] args) {
   Callee callee = new Callee ();
   callee.callMe ();
  }
}