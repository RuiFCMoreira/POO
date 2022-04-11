import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class Equipa implements Serializable
{
    private String NomeDaEquipa;
    private String Treinador;
    private List<Jogador> Plantel;

    public Equipa(){
        this.NomeDaEquipa = "";
        this.Treinador = "";
        this.Plantel = new ArrayList<Jogador>();
    }

    public Equipa(String NomeDaEquipa, String Treinador, List<Jogador> Plantel){
        this.NomeDaEquipa = NomeDaEquipa;
        this.Treinador = Treinador;
        this.Plantel = Plantel;
    }

    public Equipa (Equipa e){
        this.NomeDaEquipa = e.getNomeDaEquipa();
        this.Treinador = e.getTreinador();
        this.Plantel = e.getPlantel();
    }

    // gets e sets

    public String getNomeDaEquipa() {
        return this.NomeDaEquipa;
    }

    public String getTreinador() {
        return this.Treinador;
    }

    public List<Jogador> getPlantel() {
        List<Jogador> aux = new ArrayList<>();
        for (Jogador  t : this.Plantel)
            aux.add(t.clone());
        return aux;
    }

    public void setNomeDaEquipa(String nomeDaEquipa) {
        this.NomeDaEquipa = nomeDaEquipa;
    }

    public void setTreinador(String treinador) {
        this.Treinador = treinador;
    }

    public void setPlantel(List<Jogador> plantel) {
        this.Plantel = plantel;
    }


    public Equipa clone (){
        Equipa aux = new Equipa (this);
        return aux;
    }

    public Equipa(String nomeE){
        this.NomeDaEquipa = nomeE;
        this.Treinador = "";
        this.Plantel = new ArrayList<Jogador>();
    }
    public static Equipa parse(String input){
        String[] campos = input.split(",");
        return new Equipa(campos[0]);
    }

    public void insereJogador (Jogador j){
        int i;
        for (i = j.getNumeroJogador(); containsNum(i); i++);
        if (i != j.getNumeroJogador()) j.setNumero(i);
        this.Plantel.add(j);
    }

    public void removeJogador (Jogador j) {
        this.Plantel.remove(j);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Equipa: ");sb.append(this.NomeDaEquipa);
        List<Jogador> sorted = this.Plantel.stream().sorted().collect(Collectors.toList());
        for (Jogador j : sorted){
            sb.append("\n\t");
            if (j instanceof GuardaRedes) sb.append("GR->");
            if (j instanceof Defesa) sb.append("D ->");
            if (j instanceof Lateral) sb.append("L ->");
            if (j instanceof Medio) sb.append("M ->");
            if (j instanceof Avancado) sb.append("A ->");
            sb.append(j.getHabilidade());sb.append(" ");
            sb.append(j.getNome());
        }
        return sb.toString();
    }

    public boolean containsNum (int num){
        boolean r = false;
        for (Jogador j : this.Plantel){
            if (j.getNumeroJogador() == num)
                r = true;
        }
        return r;
    }

    // supondo que aaequipa tem obrigatoriamente este num
    public Jogador get1Jogador (int num){
        Jogador aux = null;
        for (Jogador j : this.Plantel){
            if (j.getNumeroJogador() == num)
                aux = j.clone();
        }
        return aux;
    }

    public List<Jogador> get11Jogs (List<Integer> nums){
        List<Jogador> aux = new ArrayList<>();
        for (int j : nums){
            aux.add(get1Jogador(j));
        }
        return aux;
    }

    public List<Jogador> getGuardaRedes(){
        return this.Plantel.stream().filter(v -> v instanceof GuardaRedes).
                map(v -> (Jogador) v.clone()).
                collect(Collectors.toList());

    }

    public List<Jogador> getDefesas(){
        return this.Plantel.stream().filter(v -> v instanceof Defesa).
                map(v -> (Jogador) v.clone()).
                collect(Collectors.toList());
    }

    public List<Jogador> getMedios(){
        return this.Plantel.stream().filter(v -> v instanceof Medio).
                map(v -> (Jogador) v.clone()).
                collect(Collectors.toList());
    }

    public List<Jogador> getLaterais(){
        return this.Plantel.stream().filter(v -> v instanceof Lateral).
                map(v -> (Jogador) v.clone()).
                collect(Collectors.toList());
    }


    public List<Jogador> getAvancado(){
        return this.Plantel.stream().filter(v -> v instanceof Avancado).
                map(v -> (Jogador) v.clone()).
                collect(Collectors.toList());
    }



    public double calculaHabilidadeEquipa(List<Integer> tits, Map<Integer,Integer> subs){
        double somaT = 0;
        for (int num : tits){
            somaT += get1Jogador(num).getHabilidade();
        }
        int subsL = subs.size();
        for (int numS : subs.values()){
            somaT += get1Jogador(numS).getHabilidade();
        }
        return somaT/(11+subsL);
    }

    public int calculaHabilidadeAtacar(List<Integer> jogs){
        double somaT = 0;
        for (int num : jogs){
            Jogador j = get1Jogador(num);
            if (j instanceof GuardaRedes)
                somaT += 0.25 * j.getHabilidade();
            if (j instanceof Defesa)
                somaT += 0.75 * j.getHabilidade();
            if (j instanceof Lateral)
                somaT += 1.00 * j.getHabilidade();
            if (j instanceof Medio)
                somaT += 1.00 * j.getHabilidade();
            if (j instanceof Avancado)
                somaT += 1.25 * j.getHabilidade();
        }
        return (int) somaT/jogs.size();
    }

    public int calculaHabilidadeDefender(List<Integer> jogs){
        double somaT = 0;
        for (int num : jogs){
            Jogador j = get1Jogador(num);
            if (j instanceof GuardaRedes)
                somaT += 1.00 * j.getHabilidade();
            if (j instanceof Defesa)
                somaT += 1.25 * j.getHabilidade();
            if (j instanceof Lateral)
                somaT += 1.00 * j.getHabilidade();
            if (j instanceof Medio)
                somaT += 0.75 * j.getHabilidade();
            if (j instanceof Avancado)
                somaT += 0.25 * j.getHabilidade();
        }
        return (int) somaT/jogs.size();
    }


}




