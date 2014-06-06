/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalability;

/**
 *
 * @author dispo
 */
public class Measure {
   private double items;
   private double time;

   public Measure(){
       items = 0;
       time = 0;
   }
   public void clearCounter(){
        items = 0;
        time = 0;
    }

    public double getItems() {
        return items;
    }

    public double getTime() {
        return time;
    }

    public double getAvg(){

        return time / items;
    }
    public void incCounter(){
        items = items + 1;
    }
    public void addTime(double inputTime){
        time = time + inputTime;
    }
}
