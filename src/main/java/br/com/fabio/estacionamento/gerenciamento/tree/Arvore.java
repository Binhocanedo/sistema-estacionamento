package br.com.fabio.estacionamento.gerenciamento.tree;

public class Arvore <Tipo extends Comparable>{
    private Elemento<Tipo> raiz;

    public Arvore(){
        this.raiz = null;
    }

    public int altura_no(Elemento<Tipo> no){
        if (no.getAltura() == 0) {
            return 0;
        }
        return no.getAltura();
    }

    public int balanceamento(Elemento<Tipo> no){
        if (no.getAltura() == 0) {
            return 0;
        }
        return altura_no(no.getEsquerda()) - altura_no(no.getDireita());
    }


    public void rotacao_direita(Elemento<Tipo> no){
       Elemento<Tipo> no_direita = no.getDireita();
       Elemento<Tipo> no_direita_esquerda = no_direita.getEsquerda();

       no_direita.setEsquerda(no);
       no.setEsquerda(no_direita_esquerda);

       no.setAltura(Math.max(altura_no(no.getEsquerda()), altura_no(no.getDireita())) + 1);
       no_direita.setAltura(Math.max(altura_no(no_direita.getEsquerda()), altura_no(no_direita.getDireita())) + 1);

    }

    public void rotacao_esquerda(Elemento<Tipo> no){
        Elemento<Tipo> no_esquerda = no.getEsquerda();
        Elemento<Tipo> no_esquerda_direita = no_esquerda.getDireita();

        no_esquerda.setDireita(no);
        no.setDireita(no_esquerda_direita);

        no.setAltura(Math.max(altura_no(no.getEsquerda()), altura_no(no.getDireita())) + 1);
        no_esquerda_direita.setAltura(Math.max(altura_no(no_esquerda.getDireita()), altura_no(no_esquerda.getEsquerda())) + 1);
    }

    public void adicionar(Tipo valor){
        Elemento<Tipo> novoElemento = new Elemento<>(valor);
        if(raiz == null){
            this.raiz = novoElemento;
        }else{
            Elemento<Tipo> atual = this.raiz;
            while (true){
                if(novoElemento.getValor().compareTo(atual.getValor()) < 0){
                    if(atual.getEsquerda() != null){
                        atual = atual.getEsquerda();
                    }else{
                        atual.setEsquerda(novoElemento);
                        break;
                    }
                }else{
                    if(atual.getDireita() != null){
                        atual = atual.getDireita();
                    }else{
                        atual.setDireita(novoElemento);
                        break;
                    }
                }
            }
        }
    }
    public Elemento<Tipo> getRaiz() {
        return raiz;
    }

    public void emOrdem(Elemento<Tipo> atual){
        if(atual != null){
            emOrdem(atual.getEsquerda());
            System.out.println(atual.getValor());
            emOrdem(atual.getDireita());
        }
    }
    public void preOrdem(Elemento<Tipo> atual){
        if(atual != null){
            System.out.println(atual.getValor());
            preOrdem(atual.getEsquerda());
            preOrdem(atual.getDireita());
        }
    }
    public void posOrdem(Elemento<Tipo> atual){
        if(atual != null){
            posOrdem(atual.getEsquerda());
            posOrdem(atual.getDireita());
            System.out.println(atual.getValor());
        }
    }

}

