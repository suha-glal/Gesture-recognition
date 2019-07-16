package gesturetesting;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
//import java.net.ServerSocket;
//import java.util.Vector;
import java.util.*;

/**@author Hiba*/
public class main {
    public static Vector gAll=new Vector();//=new Vector();//vector of all gestures
    public static Vector pgAll=new Vector();//vector of all processed gestures
//    public static Vector gfast=new Vector();
//    public static Vector gmed=new Vector();
//    public static Vector gslow=new Vector();
    public static Vector gTemplate=new Vector();//vector of 1-8 template gestures
    public static Vector gSample=new Vector();//vector of the sample gestures for comparision
    public static GestureProcessing pro;// used to access the gesture processing class
    public Recognizer rec;
    public Recognizer inputg;
    int set =0;
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        System.out.println("Program Started");
        int userNo=10;
        int dayNo=1;
        
        gAll= new Vector();

        for (int u=1; u<=userNo; u++){
            for (int d=1; d<=dayNo;d++){
                filetogesture("u"+u+"d"+d);
            }//day
        }//user
        
        for(int i=0;i<gAll.size();i++){
            Gesture g= (Gesture)gAll.elementAt(i);
            //System.out.println(g.gestureName);//+" "+g.accX[0]+" "+g.accY[0]+" "+g.accZ[0]+" ");//unprocessed gesture

            pro = new GestureProcessing();
            Gesture pg= pro.PreprocessV(g.accX, g.accY, g.accZ, g.gestureName);//create a new processes gesture
            pgAll.addElement(pg);//add new processed gesture to the new vector
            //System.out.println(pg.gestureName);//+" "+pg.accX[0]+" "+pg.accY[0]+" "+pg.accZ[0]+" ");//processes gesture
        }//i
        for(int p=0;p<pgAll.size();p++){
            Gesture tst=(Gesture) pgAll.elementAt(p);
          //System.out.println(tst.gestureName);
        }
int c=48; // template capacity
        for(int a=0;a<=pgAll.size()-(c-6);a++){
            for(int t=a; t<a+c;t+=6){ // 48 = (6 trials) * (8 gestures); t stands for trials
            Gesture temp = (Gesture) pgAll.elementAt(t); // temp is a template gesture
            gTemplate.addElement(temp); //insert template each time
            System.out.println(temp.gestureName);
            
            }
            if(a%6==5)
                a+=c-6;
            System.out.println("********");
        }
             /*for(int b=0;b<pgAll.size();b++){//b is the gSample boundary
                if ((b<t)&&(b>(t+8*6))){
                    Gesture s =(Gesture) pgAll.elementAt(b);
                    gSample.addElement(s);
                    //System.out.println(s.gestureName);
                }
            }
        }*/
            //choose templates and the samples
                //for(int p=0;p<pgAll.size();p++){
                  //  Gesture t=(Gesture) pgAll.elementAt(p);
                    //System.out.println(t.gestureName);
                    //gTemplate.addElement(t);
                    //System.out.println(t.gestureName);
                //}//}
                /*for(int j=x; j<x+8;j++){
                    gTemplate=new Vector();
                    gTemplate.addElement(pgAll.elementAt(j));//add 8 template gestures
                }//j
                for(int k=0;k<pgAll.size();k++){
                    if((k<x)&&(k>x+8)){ //exclude those that are templates
                        gSample=new Vector();
                        gSample.addElement(pgAll.elementAt(k));//add the rest of gestures
                    }//k condition
                        //gSample= new Recognizer(this,(Gesture)gAll.elementAt(k),gAll);
                        //int r;
                        //r=inputg.Recognize();
                }//k
                gTemplate.removeAllElements();
                //gSample.removeAllElements();
            }//x*/
    }//main

 static void filetogesture(String filename)throws IOException{
    String []speedn={"f1","f2","m1","m2","s1","s2"};
      Vector v= new Vector();

      int val=0;
      int c=0;
      int q=0;

     Vector xstr=new Vector();
     Vector ystr=new Vector();
     Vector zstr=new Vector();

    Reader r = new BufferedReader(new FileReader(filename+".txt")); // reading what is inside the file
    StreamTokenizer stok = new StreamTokenizer(r);
    stok.parseNumbers();
    stok.nextToken();
    while (stok.ttype != StreamTokenizer.TT_EOF) {
      if (stok.ttype == StreamTokenizer.TT_NUMBER){
           val=(int)stok.nval;
          if(val==700){
              //send to create file x y z
              String gesName=filename+"g"+(((q)/6)+1)+speedn[(q%6)];
              gAll.addElement(new Gesture(gesName,vectoarr(xstr),vectoarr(ystr),vectoarr(zstr),""));
              //Gesture test=(Gesture) gAll.elementAt(q);
              //System.out.println(test.gestureName);
              c=0;
              xstr.removeAllElements();
              ystr.removeAllElements();
              zstr.removeAllElements();
              q++;
          }
          else{
               if(c%3==0){
                   xstr.addElement(new Point(val));
               }
               else if(c%3==1){
                   ystr.addElement(new Point(val));
               }
               else if(c%3==2){
                   zstr.addElement(new Point(val));
               }//
               c++;
          }
      }//if
      stok.nextToken();
    }//while

 }//arrFile

 static int [] vectoarr(Vector v){
     int [] x= new int[v.size()];
     for(int i=0;i<v.size();i++){
         Point p=(Point)v.elementAt(i);
         x[i]=p.X;
     }//for i
     return x;
 }//vectoarr
}//class
            /*if(pg.gestureName.contains("f")){
                gfast.addElement(pg);
                //System.out.println(pg.gestureName);
            }
            if(pg.gestureName.contains("m")){
               gmed.addElement(pg);
               //System.out.println(pg.gestureName);
            }
            if(pg.gestureName.contains("s")){
                gslow.addElement(pg);
                //System.out.println(pg.gestureName);
            }*/

