public class Controleur{

    Interface i;

    public Controleur(Graphic g){
	i = new Interface(g);
    }

    /**
     *Ajoute une ligne au graphique de l'interface
     *@param paramT le nom de la nouvelle ligne
     */
    public void addLine(double paramT){
	this.i.gr.addLine(new Line(paramT));
    }

    /**
     *Ajoute les derniers résultats calculés à la ligne courante de notre graphique et réactualise ce dernier
     *@param cmp_size taille de la composante
     */
    public void addResult(int cmp_size, double paramK){
	this.i.gr.addResult(cmp_size,paramK);
	oneLoop();
    }

    /**
     *Actualise notre graphique (redessine)
     */
    public void oneLoop(){
	this.i.paintComponent();
	this.i.revalidate();
    }
}
