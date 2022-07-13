package it.polito.tdp.genes.model;

public class Adiacenti implements Comparable<Adiacenti>{
private String l1;
private String l2;
private int peso;
public Adiacenti(String l1, String l2, int peso) {
	super();
	this.l1 = l1;
	this.l2 = l2;
	this.peso = peso;
}

public Adiacenti(String l1, int peso) {
	super();
	this.l1 = l1;
	this.peso = peso;
}

public String getL1() {
	return l1;
}
public void setL1(String l1) {
	this.l1 = l1;
}
public String getL2() {
	return l2;
}
public void setL2(String l2) {
	this.l2 = l2;
}
public int getPeso() {
	return peso;
}
public void setPeso(int peso) {
	this.peso = peso;
}
@Override
public String toString() {
	return l1+"  "+peso+"\n";
}@Override
public int compareTo(Adiacenti a) {
	return a.getPeso()-this.getPeso();
	
}

}
