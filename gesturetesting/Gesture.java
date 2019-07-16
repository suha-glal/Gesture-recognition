/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gesturetesting;
//import java.lang.Math;
/**
 *
 * @author Suha
 */

public class Gesture {
    String gestureName;
    int accX[];
    int accY[];
    int accZ[];

    int distance;
    String ActiveAcc;
    /*Gesture(Gesture gg){
        accX=gg.accX;
        accY=gg.accY;
        accZ=gg.accZ;
        gestureName=gg.gestureName;
    }*/

    Gesture(String n,int[]x,int[]y,int[]z,String c ){

      ActiveAcc=c;
       gestureName=n;
       accX=x;
       accY=y;
       accZ=z;
    
      }//Gesture

    void setScore(int dis){
        distance=dis;

}// setScores

    void Clone(Gesture a){

    gestureName=a.gestureName;
     distance=a.distance;
     
    
}//Clone

}//end of class gesture