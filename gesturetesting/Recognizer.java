package gesturetesting;

import java.util.*;
import java.util.Vector;
import java.util.Vector;
import java.lang.Math;

public class Recognizer{
   String result="";
   Gesture gSample;
   Vector pgAll;
   
   main mainS;

   public Recognizer(main m,Gesture ge, Vector all){

       gSample= ge;// the testing gestures in main (loop)
       mainS=m;
       pgAll= all;

       while(true)
        {
        if(mainS.set==1)break;
        }//

       //maximum int value
       gSample.setScore(2147483647);

       CalculateScores();

       Recognize();
       /*result+="\n******************\n";
       result+="Gesture: "+gSample.gestureName+"\n";
       result+="Distance:"+gSample.distance+"\n";
       result+="The Active Axis:"+gSample.ActiveAcc+"\n";
       result+="Data length:"+gSample.accX.length+"\n";
               //DisplayRes dre=new DisplayRes(mainS,result);*/
   }

   int DTWDistance(int[]sX,int[]sY,int[]sZ, int[]tX, int[]tY, int[]tZ) {
       int r=sX.length+1;
       int c=tX.length+1;

       int [][]DTW= new int [r][c];
       int i=0, j=0;
       int cost=0;

       for (i=1;i<c;i++)
           DTW[0][i]=2147483647;

       for (i=1;i<r;i++)
           DTW[i][0]=2147483647;

       DTW[0][0]= 0;

       for (i=1;i<r;i++){
           for( j=1;j<c;j++){
               cost= (int)Math.sqrt(squer(sX[i-1]- tX[j-1])+
                       squer(sY[i-1]- tY[j-1])+squer(sZ[i-1]- tZ[j-1]));

               DTW[i][j] =cost +Math.min( Math.min(DTW[i-1][j],DTW[i][j-1]),DTW[i-1][j-1]);       // match
           }//j
       }//i

       return DTW[r-1][c-1];
   }
   
   void CalculateScores(){
       int sco;
       for(int j=0;j<mainS.gTemplate.size();j++){
           Gesture t=(Gesture) mainS.gTemplate.elementAt(j);
           sco=DTWDistance(gSample.accX,gSample.accY,gSample.accZ,t.accX,t.accY,t.accZ);
           t.setScore(sco);
       }//j
   }//Calculateresult

   void Recognize(){
        for(int j=0;j<mainS.gTemplate.size();j++){
           Gesture t=(Gesture)mainS.gTemplate.elementAt(j);
           result+="Gesture: "+t.gestureName+"\n";
           result+="Distance:"+t.distance+"\n";
           result+="The Active Axis:"+t.ActiveAcc+"\n";
           result+="Data length:"+t.accX.length+"\n*****\n";
           if(gSample.distance>t.distance)
               gSample.Clone(t);
       }//j

        /*int r=0; // row = actual gestures
       int c; //column = predicted gestures
       int [][] cMat= new int [8][8];
       for (int i=0; i<gAll.size();i++){
           t=(Gesture) mainS.gTemplate.elementAt(r);
           if(gSample.distance>t.distance)
       }
return r;*/
//       return r;
   }// Recognize

   int squer(int x){
       return (x*x);
   }//squer
}//Recognizer Class