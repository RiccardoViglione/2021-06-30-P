package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
private GenesDao dao;
private Graph<String,DefaultWeightedEdge>grafo;

public Model() {
	dao=new GenesDao();
	
}
public void creaGrafo() {
	grafo=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
Graphs.addAllVertices(grafo, this.dao.getVertici());
for(Adiacenti a:this.dao.getArchi()) {
	Graphs.addEdgeWithVertices(grafo, a.getL1(), a.getL2(),a.getPeso());
}
}
public int nVertici() {
	return this.grafo.vertexSet().size();
}

public int nArchi() {
	return this.grafo.edgeSet().size();
}
public boolean grafoCreato() {
	if(this.grafo == null)
		return false;
	else 
		return true;
}
public List<String> getVertici(){
	return new ArrayList<>(this.grafo.vertexSet());
}public List<Adiacenti>getAdiacenti(String localizzazione){
	List<String>vicini=Graphs.neighborListOf(grafo, localizzazione);
	List<Adiacenti>result=new ArrayList<>();
	for(String s:vicini) {
		result.add(new Adiacenti(s,(int)this.grafo.getEdgeWeight(this.grafo.getEdge(localizzazione, s))));
	}Collections.sort(result);
	return result;
}

}