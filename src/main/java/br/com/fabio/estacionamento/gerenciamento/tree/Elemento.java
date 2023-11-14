package br.com.fabio.estacionamento.gerenciamento.tree;

public class Elemento<Tipo> {
    private Tipo valor;
    private Elemento <Tipo> esquerda;
    private Elemento <Tipo> direita;
    private int altura = 1;

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public Elemento(Tipo valor){
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
    }
    public Tipo getValor() {
        return valor;
    }

    public void setValor(Tipo valor) {
        this.valor = valor;
    }

    public Elemento<Tipo> getEsquerda() {return esquerda;}

    public void setEsquerda(Elemento<Tipo> esquerda) {
        this.esquerda = esquerda;
    }

    public Elemento<Tipo> getDireita() {
        return direita;
    }

    public void setDireita(Elemento<Tipo> direita) {
        this.direita = direita;
    }
}

