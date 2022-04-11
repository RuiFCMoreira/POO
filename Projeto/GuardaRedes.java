import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GuardaRedes extends Jogador implements Serializable
{
    private int elasticidade;
    private int reflexos;

    public GuardaRedes (){
        super();
        this.elasticidade = 0;
        this.reflexos = 0;
        super.setHabilidade(this.calculaHabilidadeJogador());
    }

    public GuardaRedes (String nome, int numero, int velocidade, int resistencia, int destreza, int impulsao, int jogoAereo,
                        int remate, int passe, List<String> equipas, int elasticidade, int reflexos){

        super(nome, numero, velocidade, resistencia, destreza, impulsao, jogoAereo, remate, passe,equipas);
        this.elasticidade = elasticidade;
        this.reflexos = reflexos;
        super.setHabilidade(this.calculaHabilidadeJogador());
    }

    public GuardaRedes (GuardaRedes gr){
        super(gr);
        this.elasticidade = gr.getElasticidade();
        this.reflexos = gr.getReflexos();
    }

    // Gets e Sets
    public int getElasticidade(){
        return this.elasticidade;
    }

    public int getReflexos() {
        return this.reflexos;
    }

    public void setElasticidade(int elasticidade) {
        this.elasticidade = elasticidade;
    }

    public void setReflexos(int reflexos) {
        this.reflexos = reflexos;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuardaRedes that = (GuardaRedes) o;
        return super.equals(o) && this.elasticidade == that.getElasticidade() && this.reflexos == that.getReflexos();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GuardaRedes");
        sb.append("\n");sb.append(super.toString());
        sb.append("\n\tElasticidade="); sb.append(this.elasticidade);
        sb.append("\n\tReflexos="); sb.append(this.reflexos);
        return sb.toString();
    }

    public Jogador clone() {
        return new GuardaRedes(this);
    }

    public static GuardaRedes parse(String input){
        List<String> equipas = new ArrayList<>();
        String[] campos = input.split(",");
        return new GuardaRedes(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                equipas,
                Integer.parseInt(campos[9]),
                50);
    }

    public int calculaHabilidadeJogador() {
        return (int) (0.15 * this.elasticidade + 0.2 * this.reflexos +
                        0.10 * super.getVelocidade() +
                        0.05 * super.getResistencia() +
                        0.10 * super.getDestreza() +
                        0.10 * super.getImpulsao() +
                        0.10 * super.getJogoAereo() +
                        0.05 * super.getRemate() +
                        0.15 * super.getPasse());
    }

}
