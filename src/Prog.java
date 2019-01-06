import java.util.*;
import java.io.*;

public class Prog{

    static Interface i;
    static Controleur crtl;
    static int nb_test =5; //nombre de fois ou on va créer un graph avec les mêmes paramètres

    public static double incrK(double paramK){
	if (paramK <= 9) return paramK+2;
	return paramK+8;
    }

    public static boolean make(Graph g, String[] args){
	if(args.length == 0) return g.make();
        if(args[0].equals("m")) return g.make();
	else return g.makeBis();
    }

    public static void main(String[] args){
	crtl = new Controleur(new Graphic());

	double paramT;
	double paramK;
	for( paramT = 1 ; paramT < 3 ; paramT+=0.5){
	    crtl.addLine(paramT);
	    try{
		DataOutputStream bis = new DataOutputStream
		    (new BufferedOutputStream
		     (new FileOutputStream
		      (new File("t"+((int)paramT)+","+ ((int) (paramT-((int)paramT))*10)  +".txt" ))));
		for( paramK = 1 ; paramK<101 ; paramK=incrK(paramK)){
		    int somme = 0;
		    for (int i = 0; i < nb_test ; i++){
			Graph g = new Graph(paramT,paramK);
			if(make(g,args)) somme +=g.gcc_size();
			else i--;
		    }	        
		    int cmp_size = somme/nb_test;
		    bis.writeBytes(paramK+" "+cmp_size+"\n");
		    System.out.println("paramK="+paramK+" paramT="+paramT+" taille cmp="+cmp_size);	
		    Thread refresh = new Thread(new Refresh(crtl,cmp_size,paramK));
		    refresh.start();		
		    refresh.join();
	        
		}
		bis.flush();
		bis.close();	    
	    }catch(Exception e){
		System.out.println("PLANTAGE !!");
	    }
	}
    }
}

/**
 *Permet de refresh le graphique de l'interface tout en continuant les calculs
 */
class Refresh implements Runnable{

    int cmp_size;
    double paramK;
    Controleur crtl;

    public Refresh(Controleur _crtl, int _cmp_size, double _paramK){
	cmp_size = _cmp_size;
	paramK = _paramK;
	crtl = _crtl;
    }

    public void run(){
	this.crtl.addResult(cmp_size,paramK);
    }
}
