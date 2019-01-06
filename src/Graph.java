import java.util.*;

public class Graph{


    ArrayList <Vertex> vertices;
    ArrayList <Edge> edges;
    int sumDegree;
    private final int nb_vertex = 10000;

    public Graph(double param1, double param2){
	vertices = new ArrayList<Vertex>();
	edges = new ArrayList<Edge>();
	sumDegree = 0;
	this.setVertex(param1,param2);
    }

    /**
     *Initialise les sommets du graph this
     */
    public void setVertex(double param1, double param2){
	for(int i =0; i < nb_vertex ; i++){
	    Vertex v = new Vertex(param1,param2);
	    this.vertices.add(v);
	    this.sumDegree += v.degree;
	}
	if (this.sumDegree%2 != 0){
	    this.vertices = new ArrayList<Vertex>();
	    this.sumDegree = 0;
	    this.setVertex(param1,param2);
	}
    }

    /**
     *Fonction permettant la construction du graphe
     *(cf méthode vue avec M. Carton)
     *@return vrai si on arrive à réaliser notre graphe
     */
    public boolean makeBis(){
	int compteur = 0;
	while(compteur < this.sumDegree/2){
	    Vertex v1 = this.selectBis();
	    Vertex v2 = this.selectBis();
	    if(this.link(v1,v2)) compteur ++;
	}
	return true;
    }

    /**
     *Choisi un sommet aléatoirement dans notre graphe (coefficient en fonction du degré)
     *@return sommet sélectionné
     */
    public Vertex selectBis(){
	int index = (int)(Math.random()*sumDegree);
	int sum = 0;
	for (Vertex v : this.vertices){
	    sum += v.degree;
	    if(sum > index ) return v;
	}
	return null;
    }


    /**
     *Fonction permettant la construction du graphe
     *(cf méthode vue avec M. de Montgolfier)
     *@return vrai si le graphe à pu etre réalisé
     */
    public boolean make(){
	int compteur = 0;
	while(compteur < this.sumDegree/2){
	    Vertex v1 = this.select_priority();
	    Vertex v2 = this.randomVertexEligible();
	    if(v1 == null){
		System.out.println("Suite de degés non réalisable");
		return false;
	    }else if (this.link(v1,v2)) compteur ++;
	}
	compteur = 0;
	while(compteur < sumDegree){
	    Edge e1 = this.randomEdge();
	    Edge e2 = this.randomEdge();
	    if( ! e1.equals(e2) ){
		Vertex a1 = e1.a;
		Vertex b1 = e1.b;

		Vertex a2 = e2.a;
		Vertex b2 = e2.b;

		if(! a1.neibh.contains(a2) && ! b1.neibh.contains(b2)){
		    this.switchEdges(e1,e2);
		}
		compteur ++;
	    }
	}
	return true;
    }

    /**
     *Interverti deux aretes
     *@param e1 première arete
     *@param e2 deuxième arete
     */
    public void switchEdges(Edge e1, Edge e2){
      Vertex aux = e1.b;
    	e1.b = e2.a;
    	e2.a = aux;
    }

    /**
     *Sélectionne aléatoirement une arete de notre graph
     *@return arete sélectionnée
     */
    public Edge randomEdge(){
			int index = (int)(Math.random()*this.edges.size());
			return this.edges.get(index);
    }


    /**
     *Relie deux sommet si cela est possible
     *@param v1 premier sommets à relier
     *@param v2 deuxième sommet à relier
     *@return vrai si les deux sommets ont été reliés
     */
    public boolean link(Vertex v1, Vertex v2){
			if(!v1.neibh.contains(v2) && !v2.neibh.contains(v1)){
			    v1.addNeighbour(v2);
			    v2.addNeighbour(v1);
			    this.edges.add(new Edge(v1,v2));
			    return true;
			}
			return false;
    }

    /**
     *Choisi un sommet éligible (nb_neibh < degree)
     *@return le sommet éligible
     */
    public Vertex randomVertexEligible(){
			int index =(int)(Math.random()*(this.vertices.size()));
			Vertex result = this.vertices.get(index);
			if(result.priority() != 0) return result;
			return this.randomVertexEligible();
    }

    /**
     *Choisi le sommet prioritaire par rapport à son degré
     *@return le sommet avec la plus haute priorité
     */
    public Vertex select_priority(){
			int max = 0;
			Vertex result = null;
			for(Vertex v : this.vertices){
			    if(v.priority() > max){
				max = v.priority();
				result = v;
			    }
			}
			return result;
    }


    /**
     *Parcours le graphe pour trouver la taille de la composante connexe géante
     *@return la taille de la plus grosse composante connexe
     */
    public int gcc_size(){
	int max = 0;
	int size = 0;
	for(Vertex v : this.vertices){
	    if(v.getColor().equals("white")) size = this.browse(v);
	    if(size > max) max = size;
	}
	return max;
    }

    /**
     * Parcours en profondeur a partir d'un sommet donnée
     * @param v : le sommet par lequel on commence le parcours
     * @param cmpt : correspond a la taille de la composante connexe
     */
    public int browse(Vertex v){
	v.setGrey();
	int size = 1;
	for(Vertex w : v.getNeibh()){
	    if(w.getColor().equals("white")) {
		size += this.browse(w);
	    }
	}
	v.setBlack();
	return size;
    }

}
