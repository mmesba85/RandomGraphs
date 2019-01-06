import java.util.*;
import java.lang.*;

public class Vertex{

    private String color;
    ArrayList<Vertex> neibh;
    int nb_neibh;
    int degree;

    public Vertex(double param1, double param2){
	neibh = new ArrayList<Vertex>();
	color = "white";
	nb_neibh = 0;
	degree = setRandomDegree(param1, param2);
    }

    /**
     *Initialise un degré aléatoire pour un sommet
     *@return degré aléatoire
     */
    public static int setRandomDegree(double const_tau, double const_kappa){
        int k = (int)(-(const_kappa*(Math.log(1-Math.random()))));
	k++;
	double p = Math.pow(k,-const_tau);
	if(Math.random() < p) return k;
	return setRandomDegree(const_tau, const_kappa);
    }

    public void setGrey(){
	this.color = "grey";
    }
    public void setBlack(){
	this.color = "black";
    }

    public ArrayList<Vertex> getNeibh(){
	return this.neibh;
    }

    public String getColor(){
	return this.color;
    }
    
    public void addNeighbour(Vertex v){
	this.neibh.add(v);
	this.nb_neibh ++;
    }

    public int priority(){
	return this.degree - this.nb_neibh;
    }
}
