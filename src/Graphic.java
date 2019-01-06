import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Graphic extends JPanel{

    private ArrayList<Line> lines;
    private Line current_line;
    private int nb_lines;
    private int padding = 25;
    private int labelPadding = 25;
    private Color gridColor = new Color(200, 200, 200, 200);
    private int pointWidth = 2;
    private int numberYDivisions = 10;
    private int numberXDivisions = 10;
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);

    public Graphic(){
	super();
	nb_lines = 0;
	lines = new ArrayList<Line>();
    }

    public void addLine(Line l){
	this.lines.add(l);
	current_line = l;
    }

    /**
     *Ajoute les derniers résultats calculés à la ligne courante
     *@param cmp_size la taille de la composante connexe
     */
    public void addResult(int cmp_size, double paramK){
	current_line.addPoint(cmp_size, (int) paramK);
    }

    public void paintComponent(Graphics g){
	super.paintComponent(g);
	Graphics2D g2d = (Graphics2D)g;
	g2d.setColor(Color.WHITE);
	g2d.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
	g2d.setColor(Color.RED);

	drawAxes(g2d);

	//abscisses et ordonnées
	g2d.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
	g2d.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

	drawLines(g2d);
    }

    public void drawAxes(Graphics2D g2d){
	drawVerticalAxes(g2d);
	drawHorizontalAxes(g2d);
    }

    public void drawVerticalAxes(Graphics2D g2d){
	for (int i = 0; i < numberXDivisions; i++) {
	    int x0 = i * (getWidth() - padding * 2 - labelPadding) / (numberXDivisions ) + padding + labelPadding;
	    int x1 = x0;
	    int y0 = getHeight() - padding - labelPadding;
	    int y1 = y0 - pointWidth;
	    if ((i % ((int) ((numberXDivisions / 20.0)) + 1)) == 0) {
		g2d.setColor(gridColor);
		g2d.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
		g2d.setColor(Color.BLACK);
		String xLabel = ((double)(i*100/numberXDivisions)) + "";
		FontMetrics metrics = g2d.getFontMetrics();
		int labelWidth = metrics.stringWidth(xLabel);
		g2d.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
	    }
	    g2d.drawLine(x0, y0, x1, y1);
	}
    }

    public void drawHorizontalAxes(Graphics2D g2d){
	for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            g2d.setColor(gridColor);
            g2d.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
            g2d.setColor(Color.BLACK);
            String yLabel = ((double) 100*i/numberYDivisions) + "";
            FontMetrics metrics = g2d.getFontMetrics();
            int labelWidth = metrics.stringWidth(yLabel);
            g2d.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            g2d.drawLine(x0, y0, x1, y1);
	}
    }

    public void drawLines(Graphics2D g2d){
	Stroke oldStroke = g2d.getStroke();
	g2d.setStroke(GRAPH_STROKE);
	for (Line l : this.lines){
	    int h = getHeight() - padding - labelPadding;
	    int w = getWidth() - padding - labelPadding;
	    g2d.setColor(l.color);
	    int len_max = l.points.size();
	    for (int i = 0; i < len_max - 1; i++) {
	      	int x1=  padding + labelPadding + (l.points.get(i).x * (w-padding) /100);
		int y1= h - (l.points.get(i).y*(h-padding ) / 10000);
		int x2=  (padding + labelPadding + (l.points.get(i+1).x*(w-padding)) /100);
		int y2= h - (l.points.get(i+1).y*(h-padding)) /10000;
	        g2d.drawLine(x1, y1, x2, y2);
	    }
	}
    }

    public void Resize(int n,int l){
	this.setSize(new Dimension (2*n/3,2*l/5));
    }

}
