import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Line{
    ArrayList<Point> points; // couples (paramK , cmp_size)
    final double name; // le paramètre T
    final Color color;


    public Line(double _name){
	points = new ArrayList<Point>();
	name = _name;
	color = setRandomColor();
    }

    /**
     *Génère une couleur pour notre ligne
     *@return : couleur de notre ligne
     */
    public Color setRandomColor(){
	int r =(int)((Math.random()+0.0)*256);
	int g = (int)((Math.random()+0.0)*256);
	int b = (int)((Math.random()+0.0)*256);
	return new Color(r,b,g);
    }

    /**
     *Ajoute un point à ceux de notre ligne
     *@param x abscisse du point
     *@param y ordonnée du point
     */
    public void addPoint(int y, int x){
	this.points.add(new Point(x,y));
    }



}

class Point{
    int x;
    int y;

    public Point(int _x,int _y){
	x=_x;
	y=_y;
    }
}
