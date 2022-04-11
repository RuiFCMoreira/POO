import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Jogador implements Serializable,Comparable<Jogador>
{
    private String nome;
    private int numero;
    private int velocidade;
    private int resistencia;
    private int destreza;
    private int impulsao;
    private int jogoAereo;
    private int remate;
    private int passe;
    private List<String> equipas;
    private int Habilidade;



    public Jogador(){
        this.nome = "";
        this.numero = 0;
        this.velocidade =0;
        this.resistencia = 0;
        this.destreza = 0;
        this.impulsao = 0;
        this.jogoAereo = 0;
        this.remate = 0;
        this.passe =0;
        this.Habilidade = 0;
        this.equipas = new ArrayList<>();
    }

    public Jogador (String nome, int numero, int velocidade, int resistencia, int destreza, int impulsao, int jogoAereo,
                    int remate, int passe, List<String> equipas){

        this.nome = nome;
        this.numero = numero;
        this.velocidade = velocidade;
        this.resistencia = resistencia;
        this.destreza = destreza;
        this.impulsao = impulsao;
        this.jogoAereo = jogoAereo;
        this.remate = remate;
        this.passe = passe;
        this.equipas = equipas;
        this.Habilidade = 0;
        this.equipas = equipas;

    }

    public Jogador (Jogador j){
        this.nome = j.getNome();
        this.numero = j.getNumeroJogador();
        this.velocidade = j.getVelocidade();
        this.resistencia = j.getResistencia();
        this.destreza = j.getDestreza();
        this.impulsao = j.getImpulsao();
        this.jogoAereo = j.getJogoAereo();
        this.remate = j.getRemate();
        this.passe = j.getPasse();
        this.Habilidade = j.getHabilidade();
        this.equipas = j.getEquipas();

    }

    // Gets e Sets
    public String getNome() {
        return this.nome;
    }

    public int getNumeroJogador() {
        return this.numero;
    }

    public int getVelocidade() {
        return this.velocidade;
    }

    public int getResistencia() {
        return this.resistencia;
    }

    public int getDestreza() {
        return this.destreza;
    }

    public int getImpulsao() {
        return this.impulsao;
    }

    public int getJogoAereo() {
        return this.jogoAereo;
    }

    public int getRemate() {
        return this.remate;
    }

    public int getPasse() {
        return this.passe;
    }

    public int getHabilidade() {
        return this.Habilidade;
    }

    public List<String> getEquipas() {
        return this.equipas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public void setImpulsao(int impulsao) {
        this.impulsao = impulsao;
    }

    public void setJogoAereo(int jogoAereo) {
        this.jogoAereo = jogoAereo;
    }

    public void setRemate(int remate) {
        this.remate = remate;
    }

    public void setPasse(int passe) {
        this.passe = passe;
    }

    public void setHabilidade(int habilidade) {
        this.Habilidade = habilidade;
    }

    public void setEquipas(List<String> equipas) {
        this.equipas = equipas;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return this.numero == jogador.getNumeroJogador()  && this.nome.equals(jogador.getNome());
    }

    public String toString() {
        int size = this.equipas.size();
        StringBuilder sb = new StringBuilder();
        sb.append("\tNome: "); sb.append(this.nome);
        //sb.append("Equipas: ");sb.append(this.equipas);
        sb.append("\n\tEquipa Atual: ");
        if (size == 0)
            sb.append("Sem equipa");
        else
            sb.append(this.equipas.get(size-1));
        if (size > 1) {
            sb.append("\n\tEquipa Anteriores: ");
            int i = 0;
            while (i < size - 1) {
                if (i > 0) sb.append(" , ");
                sb.append(this.equipas.get(i));
                i++;
            }
        }
        sb.append("\n\tNúmero:"); sb.append(this.numero);
        sb.append("\n\tHabilidade="); sb.append(this.Habilidade);
        sb.append("\n\tVelocidade="); sb.append(this.velocidade);
        sb.append("\n\tResistência="); sb.append(this.resistencia);
        sb.append("\n\tDestreza="); sb.append(this.destreza);
        sb.append("\n\tImpulsao="); sb.append(this.impulsao);
        sb.append("\n\tJogo Aéreo="); sb.append(this.jogoAereo);
        sb.append("\n\tRemate="); sb.append(this.remate);
        sb.append("\n\tPasse="); sb.append(this.passe);


        return sb.toString();
    }

    public abstract Jogador clone();

    public int compareTo(Jogador j) {
        if(this instanceof GuardaRedes && j instanceof GuardaRedes ||
                this instanceof Defesa && j instanceof Defesa||
                this instanceof Medio && j instanceof Medio ||
                this instanceof Avancado && j instanceof Avancado ||
                this instanceof Lateral && j instanceof Lateral)
            return (this.Habilidade - j.Habilidade);
        else{
            int a = 0 , b = 0;
            if (this instanceof GuardaRedes) a = 1;
            if (this instanceof Defesa) a = 2;
            if (this instanceof Medio) a = 3;
            if (this instanceof Avancado) a = 4;
            if (this instanceof Lateral) a = 5;
            if (j instanceof GuardaRedes) b = 1;
            if (j instanceof Defesa) b = 2;
            if (j instanceof Medio) b = 3;
            if (j instanceof Avancado) b = 4;
            if (j instanceof Lateral) b = 5;
            return a-b;
        }

    }

    public void insereNovoClube (String d){
        this.equipas.add(d);
    }

    public String getEquipaAtual (){
        return this.equipas.get(this.equipas.size()-1);
    }

}
