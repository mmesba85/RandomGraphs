import javax.swing.*;
import java.awt.*;

public class Interface extends JFrame{
    Graphic gr;

    public Interface(Graphic _gr){
	super();
	gr = _gr;
	this.setTitle("Graphique");
	this.setSize(1500,1000);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setContentPane(gr);
	this.setVisible(true);
    }

    public void paintComponent(){
	this.gr.paintComponent(this.gr.getGraphics());
    }

}
