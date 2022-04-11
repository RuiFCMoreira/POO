import java.io.LineNumberInputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.Random;

public class Jogo implements Serializable {
    private int tempo;
    private LocalDate data;
    private Equipa equipaVisitada;
    private Equipa equipaVisitante;
    private boolean pausa;
    private int golosCasa;
    private int golosFora;
    private String taticaCasa;
    private String taticaFora;
    private List<Integer> jogadoresCasa;
    private List<Integer> jogadoresFora;
    Map<Integer, Integer> substituicoesCasa = new HashMap<>();
    Map<Integer, Integer> substituicoesFora = new HashMap<>();


    public Jogo() {
        this.tempo = 0;
        this.equipaVisitada = new Equipa();
        this.equipaVisitante = new Equipa();
        this.pausa = true;
        this.golosCasa = 0;
        this.golosFora = 0;
        this.taticaCasa = "";
        this.taticaFora = "";
        this.jogadoresCasa = new ArrayList<>();
        this.jogadoresFora = new ArrayList<>();
        this.substituicoesCasa = new HashMap<>();
        this.substituicoesFora = new HashMap<>();

    }

    public Jogo(Equipa ec, Equipa ef, int gc, int gf, LocalDate d, List<Integer> jc, Map<Integer, Integer> sc, List<Integer> jf, Map<Integer, Integer> sf, String taticaCasa, String taticaFora, int tempo) {
        this.tempo = 0;
        this.pausa = true;
        this.equipaVisitada = ec;
        this.equipaVisitante = ef;
        this.golosCasa = gc;
        this.golosFora = gf;
        this.data = d;
        this.jogadoresCasa = new ArrayList<>(jc);
        this.jogadoresFora = new ArrayList<>(jf);
        this.substituicoesCasa = new HashMap<>(sc);
        this.substituicoesFora = new HashMap<>(sf);
        this.taticaFora = taticaFora;
        this.taticaCasa = taticaCasa;
        this.tempo = tempo;
    }


    public Jogo(Jogo j) {
        this.tempo = j.getTempo();
        this.pausa = j.getPausa();
        this.equipaVisitada = j.getEquipaVisitada();
        this.equipaVisitante = j.getEquipaVisitante();
        this.golosCasa = j.getGolosCasa();
        this.golosFora = j.golosFora;
        this.data = j.getData();
        this.jogadoresCasa = j.getJogadoresCasa();
        this.jogadoresFora = j.getJogadoresFora();
        this.substituicoesCasa = j.getSubstituicoesCasa();
        this.substituicoesFora = j.getSubstitucoesFora();
        this.taticaCasa = j.getTaticaCasa();
        this.taticaFora = j.getTaticaFora();
    }

    // gets e sets

    public int getTempo() {
        return this.tempo;
    }

    public Equipa getEquipaVisitada() {
        return this.equipaVisitada.clone();
    }

    public Equipa getEquipaVisitante() {
        return this.equipaVisitante.clone();
    }

    public boolean getPausa() {
        return this.pausa;
    }

    public int getGolosCasa() {
        return this.golosCasa;
    }

    public int getGolosFora() {
        return this.golosFora;
    }

    public LocalDate getData() {
        return this.data;
    }

    public List<Integer> getJogadoresCasa() {
        return this.jogadoresCasa.stream().collect(Collectors.toList());
    }

    public List<Integer> getJogadoresFora() {
        return this.jogadoresFora.stream().collect(Collectors.toList());
    }

    public Map<Integer, Integer> getSubstitucoesFora() {
        return this.substituicoesFora;
    }

    public Map<Integer, Integer> getSubstituicoesCasa() {
        return this.substituicoesCasa;
    }

    public String getTaticaCasa() {
        return this.taticaCasa;
    }

    public String getTaticaFora() {
        return this.taticaCasa;
    }

    public void setJogadoresCasa(List<Integer> jogadoresCasa) {
        this.jogadoresCasa = jogadoresCasa;
    }

    public void setJogadoresFora(List<Integer> jogadoresFora) {
        this.jogadoresFora = jogadoresFora;
    }

    public void setSubstituicoesCasa(Map<Integer, Integer> substituicoesCasa) {
        this.substituicoesCasa = substituicoesCasa;
    }

    public void setSubstitucoesFora(Map<Integer, Integer> substitucoesFora) {
        this.substituicoesFora = substitucoesFora;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public void setEquipaVisitada(Equipa equipaVisitada) {
        this.equipaVisitada = equipaVisitada;
    }

    public void setEquipaVisitante(Equipa equipaVisitante) {
        this.equipaVisitante = equipaVisitante;
    }

    public void setPausa(boolean pausa) {
        this.pausa = pausa;
    }

    public void setGolosCasa(int golosCasa) {
        this.golosCasa = golosCasa;
    }

    public void setGolosFora(int golosFora) {
        this.golosFora = golosFora;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogo jogo = (Jogo) o;
        return this.tempo == jogo.getTempo() && this.equipaVisitada.equals(jogo.getEquipaVisitada()) && this.equipaVisitante.equals(jogo.getEquipaVisitante()) &&
                this.pausa == jogo.getPausa() && this.golosCasa == jogo.getGolosCasa() && this.golosFora == jogo.getGolosFora();
    }

    private int tamMaiorString(List<Jogador> l) {
        int largestString = l.get(0).getNome().length();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getNome().length() > largestString) {
                largestString = l.get(i).getNome().length();
            }
        }
        return largestString;
    }

    public String toString() {


        List<Jogador> JogsCasa = this.equipaVisitada.get11Jogs(this.jogadoresCasa);
        for (int j : this.substituicoesCasa.values())
            JogsCasa.add(this.equipaVisitada.get1Jogador(j));
        List<Jogador> JogsFora = this.equipaVisitante.get11Jogs(this.jogadoresFora);
        for (int k : this.substituicoesFora.values())
            JogsFora.add(this.equipaVisitante.get1Jogador(k));

        int mC = tamMaiorString(JogsCasa);

        StringBuilder sb = new StringBuilder();
        sb.append("Data: ");
        sb.append(this.data.toString());
        sb.append(" tempo: ");
        sb.append(this.tempo);
        sb.append("'\n");
        sb.append(this.getEquipaVisitada().getNomeDaEquipa());
        for (int k = this.getEquipaVisitada().getNomeDaEquipa().length(); k < (mC + 11); k++)
            sb.append(" ");
        sb.append(this.golosCasa);
        sb.append(" vs ");
        sb.append(this.golosFora);
        sb.append(" ");
        sb.append(this.getEquipaVisitante().getNomeDaEquipa());
        sb.append("\n");
        sb.append("11 inicial:");
        for (int k = 11; k < (mC + 17); k++)
            sb.append(" ");
        sb.append("11 inicial:  \n");
        Jogador jC;
        Jogador jF;
        for (int i = 0; i < 11; i++) {
            jC = JogsCasa.get(i);
            jF = JogsFora.get(i);
            if (jC instanceof GuardaRedes) sb.append("GR->");
            if (jC instanceof Defesa) sb.append("D ->");
            if (jC instanceof Lateral) sb.append("L ->");
            if (jC instanceof Medio) sb.append("M ->");
            if (jC instanceof Avancado) sb.append("A ->");
            sb.append("(");
            sb.append(jC.getHabilidade());
            sb.append(") ");
            sb.append(jC.getNumeroJogador());
            sb.append(" ");
            sb.append(jC.getNome());
            for (int j = jC.getNome().length(); j < mC; j++) sb.append(" ");
            sb.append("\t\t");
            if (jF instanceof GuardaRedes) sb.append("GR->");
            if (jF instanceof Defesa) sb.append("D ->");
            if (jF instanceof Lateral) sb.append("L ->");
            if (jF instanceof Medio) sb.append("M ->");
            if (jF instanceof Avancado) sb.append("A ->");
            sb.append("(");
            sb.append(jF.getHabilidade());
            sb.append(") ");
            sb.append(jF.getNumeroJogador());
            sb.append(" ");
            sb.append(jF.getNome());
            sb.append("\n");
        }

        int subsC = this.substituicoesCasa.size();
        int subsF = this.substituicoesFora.size();
        if (subsF > 0 || subsC > 0) sb.append("Jogadores que entraram: \n");
        int i = 0;
        while ( subsC > 0){
            jC = JogsCasa.get(11+i);
            if (jC instanceof GuardaRedes) sb.append("GR->");
            if (jC instanceof Defesa) sb.append("D ->");
            if (jC instanceof Lateral) sb.append("L ->");
            if (jC instanceof Medio) sb.append("M ->");
            if (jC instanceof Avancado) sb.append("A ->");
            sb.append("(");
            sb.append(jC.getHabilidade());
            sb.append(") ");
            sb.append(jC.getNumeroJogador());
            sb.append(" ");
            sb.append(jC.getNome());
            for (int j = jC.getNome().length(); j < mC; j++) sb.append(" ");
            sb.append("\t\t");
            subsC--;
            if (subsF > 0){
                jF = JogsFora.get(11+i);
                if (jF instanceof GuardaRedes) sb.append("GR->");
                if (jF instanceof Defesa) sb.append("D ->");
                if (jF instanceof Lateral) sb.append("L ->");
                if (jF instanceof Medio) sb.append("M ->");
                if (jF instanceof Avancado) sb.append("A ->");
                sb.append("(");
                sb.append(jF.getHabilidade());
                sb.append(") ");
                sb.append(jF.getNumeroJogador());
                sb.append(" ");
                sb.append(jF.getNome());
                subsF--;
            }
            sb.append("\n");
            i++;
        }
        while (subsF > 0){
            for (int j = 0; j < mC; j++) sb.append(" ");
            sb.append("\t\t");
            jF = JogsFora.get(11+i);
            if (jF instanceof GuardaRedes) sb.append("GR->");
            if (jF instanceof Defesa) sb.append("D ->");
            if (jF instanceof Lateral) sb.append("L ->");
            if (jF instanceof Medio) sb.append("M ->");
            if (jF instanceof Avancado) sb.append("A ->");
            sb.append(jF.getHabilidade());
            sb.append(" ");
            sb.append(jF.getNome());
            subsF--;i++;
            sb.append("\n");
        }
        return sb.toString();
    }

    public Jogo clone() {
        return new Jogo(this);
    }

    public static class EquipaNaoExisteException extends Exception {
        public EquipaNaoExisteException() {
            super();
        }

        public EquipaNaoExisteException(String s) {
            super(s);
        }
    }

    //Devolve a tatica usada por um um 11 de jogadores

    public static String verificaTatica(List<Integer> nums, Equipa e) {
        int medio = 0;
        List<Jogador> plantel = e.getPlantel();
        for (int num : nums) {
            for (Jogador jog : plantel) {
                if (num == jog.getNumeroJogador() && jog instanceof Medio) medio++;
            }
        }

        if (medio == 4) return "4-4-2";
        else return "4-3-3";
    }

    public static Jogo parse(String input, Map<String, Equipa> equipas) throws EquipaNaoExisteException {
        String[] campos = input.split(",");
        String[] data = campos[4].split("-");
        List<Integer> jc = new ArrayList<>();
        List<Integer> jf = new ArrayList<>();
        Map<Integer, Integer> subsC = new HashMap<>();
        Map<Integer, Integer> subsF = new HashMap<>();
        for (int i = 5; i < 16; i++) {
            jc.add(Integer.parseInt(campos[i]));
        }
        for (int i = 16; i < 19; i++) {
            String[] sub = campos[i].split("->");
            subsC.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        for (int i = 19; i < 30; i++) {
            jf.add(Integer.parseInt(campos[i]));
        }
        for (int i = 30; i < 33; i++) {
            String[] sub = campos[i].split("->");
            subsF.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        if (equipas.containsKey(campos[0]) && equipas.containsKey(campos[1])) {

            LocalDate d = LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));

            return new Jogo(equipas.get(campos[0]), equipas.get(campos[1]), Integer.parseInt(campos[2]), Integer.parseInt(campos[3]),
                    d, jc, subsC, jf, subsF, verificaTatica(jc, equipas.get(campos[0])), verificaTatica(jf, equipas.get(campos[1])),90);
        } else throw new EquipaNaoExisteException(campos[0] + " ou " + campos[1]);

    }

    public class SubstituicaoInvalidaException extends Exception {
        public SubstituicaoInvalidaException() {
            super();
        }

        public SubstituicaoInvalidaException(String s) {
            super(s);
        }
    }

    public class SubstituicaoIndisponivelException extends Exception {
        public SubstituicaoIndisponivelException(){
            super();
        }

        public SubstituicaoIndisponivelException(String s){
            super(s);
        }
    }

    public boolean validaSubstituicoesCasa (){
        boolean r = true;
        List<Integer> jogsEmCampo = this.jogadoresCasa;
        if (this.substituicoesCasa.size() <= 3){
            for (Map.Entry e : this.substituicoesCasa.entrySet()){
                r = r & validaSubstituicaoCasa((int) e.getKey(),(int) e.getValue(),jogsEmCampo);
                if (r)
                    jogsEmCampo.set(jogsEmCampo.indexOf(e.getKey()),(int) e.getValue());
                    /*for ( int j : jogsEmCampo){
                        if (j == (int) e.getKey())
                            j = (int) e.getValue();
                    }*/
            }
            return r;
        }else return false;
    }


    public boolean validaSubstituicaoCasa(int nSai, int nEntra,List<Integer> jogsEmCampo) {
        boolean r = true;
            // jogador que vai sair tem que estar no 11
            if (!jogsEmCampo.contains(nSai)) return false;
            // jogador que vai entrar tem que pertencer a equipa
            if (!this.getEquipaVisitada().containsNum(nEntra)) return false;
            // jogdor que vai entrar nao pode estar em campo
            if (jogsEmCampo.contains(nEntra)) return false;
            // se o jogador que vai entrar ja tiver saido FALSE (substituicao MAP <sai , entra>)
            if (this.substituicoesCasa.containsKey(nEntra)) return false;

            if (this.equipaVisitada.get1Jogador(nSai) instanceof Lateral){
                if (!(this.equipaVisitada.get1Jogador(nEntra) instanceof Lateral))
                    return false;
            }
            else {
                if ((this.equipaVisitada.get1Jogador(nEntra) instanceof Lateral))
                    return false;
            }
        return r;
    }


    public boolean validaSubstituicoesFora (){
        boolean r = true;
        List<Integer> jogsEmCampo = this.jogadoresFora;
        if (this.substituicoesFora.size() <= 3){
            for (Map.Entry e : this.substituicoesFora.entrySet()){
                r = r & validaSubstituicaoFora((int) e.getKey(),(int) e.getValue(),jogsEmCampo);
                if (r)
                    jogsEmCampo.set(jogsEmCampo.indexOf(e.getKey()),(int) e.getValue());
            }
            return r;
        }else return false;
    }

    public boolean validaSubstituicaoFora(int nSai, int nEntra,List<Integer> jogsEmCampo) {
        boolean r = true;
        // jogador que vai sair tem que estar no 11
        if (!jogsEmCampo.contains(nSai)) return false;
        // jogador que vai entrar tem que pertencer a equipa
        if (!this.getEquipaVisitante().containsNum(nEntra)) return false;
        // jogdor que vai entrar nao pode estar em campo
        if (jogsEmCampo.contains(nEntra)) return false;
        // se o jogador que vai entrar ja tiver saido FALSE (substituicao MAP <sai , entra>)
        if (this.substituicoesFora.containsKey(nEntra)) return false;

        if (this.equipaVisitante.get1Jogador(nSai) instanceof Lateral){
            if (!(this.equipaVisitante.get1Jogador(nEntra) instanceof Lateral))
                return false;
        }
        else {
            if ((this.equipaVisitante.get1Jogador(nEntra) instanceof Lateral))
                return false;
        }
        return r;
    }

    public List<Integer> efectuaSubstituicao(String equipa,int nSai, int nEntra,List<Integer> jogsEmCampo) throws SubstituicaoInvalidaException,SubstituicaoIndisponivelException {
        if (equipa.equals("casa")){
            if (this.substituicoesCasa.size() >= 3) throw new SubstituicaoIndisponivelException("Equipa Visitada");
            if (validaSubstituicaoCasa(nSai, nEntra,jogsEmCampo)) {
                int posicao = jogsEmCampo.indexOf(nSai);
                jogsEmCampo.set(posicao,nEntra);
                this.substituicoesCasa.put(nSai,nEntra);
                return jogsEmCampo;
            }
            else throw new SubstituicaoInvalidaException();
        }
        else {
            if (this.substituicoesFora.size() >= 3) throw new SubstituicaoIndisponivelException("Equipa Visitante");
            if (validaSubstituicaoFora(nSai, nEntra,jogsEmCampo)) {
                int posicao = jogsEmCampo.indexOf(nSai);
                jogsEmCampo.set(posicao,nEntra);
                this.substituicoesFora.put(nSai,nEntra);
                return jogsEmCampo;
            } else throw new SubstituicaoInvalidaException();
        }
    }


    public void calculaResultado() {
        this.setTempo(90);
        Random gerador = new Random();

        List<Integer> numsUtilizadosCasa = new ArrayList<>();
        numsUtilizadosCasa.addAll(this.jogadoresCasa);numsUtilizadosCasa.addAll(this.substituicoesCasa.values());
        double hcd = (double) this.equipaVisitada.calculaHabilidadeDefender(numsUtilizadosCasa)/(double) 100;
        double hca = (double) this.equipaVisitada.calculaHabilidadeAtacar(numsUtilizadosCasa)/(double) 100;

        List<Integer> numsUtilizadosFora = new ArrayList<>();
        numsUtilizadosFora.addAll(this.jogadoresFora);numsUtilizadosFora.addAll(this.substituicoesFora.values());
        double hfd = (double) this.equipaVisitante.calculaHabilidadeDefender(numsUtilizadosFora)/(double) 100;
        double hfa = (double) this.equipaVisitante.calculaHabilidadeAtacar(numsUtilizadosFora)/(double) 100;
        int golosCasa;
        int x = gerador.nextInt(100);
        if (x < 1){
            golosCasa = 10;
        }else if (1 < x && x < 3){
            golosCasa = 9;
        }else if (3 < x && x < 6){
            golosCasa = 8;
        }else if (6 < x && x < 9){
            golosCasa = 7;
        }else if (9 < x && x < 13){
            golosCasa = 6;
        }else if (13 < x && x < 20){
            golosCasa = 5;
        }else if (20 < x && x < 30){
            golosCasa = 4;
        }else if (30 < x && x < 50){
            golosCasa = 3;
        }else if (50 < x && x < 70){
            golosCasa = 2;
        }else if (70 < x && x < 90){
            golosCasa = 1;
        }else
            golosCasa = 0;
        setGolosCasa((int) ((double) golosCasa * (1+hca-hfd)));
        int golosFora;
        int y = gerador.nextInt(100);
        if (y < 1){
            golosFora = 10;
        }else if (1 < y && y < 3){
            golosFora = 9;
        }else if (3 < y && y < 6){
            golosFora = 8;
        }else if (6 < y && y < 9){
            golosFora = 7;
        }else if (9 < y && y < 13){
            golosFora = 6;
        }else if (13 < y && y < 20){
            golosFora = 5;
        }else if (20 < y && y < 30){
            golosFora = 4;
        }else if (30 < y && y < 50){
            golosFora = 3;
        }else if (50 < y && y < 70){
            golosFora = 2;
        }else if (70 < y && y < 90){
            golosFora = 1;
        }else
            golosFora = 0;
        setGolosFora((int) ((double) golosFora * (1+hfa-hcd)));
    }


    public String avançaTempoJogo(int tempo, String equipaAtacar,List<Integer> jogsEmCampoCasa,List<Integer> jogsEmCampoFora) {
        int habilidadeAtacar, habilidadeDefender;
        StringBuilder res = new StringBuilder();
        setTempo(this.tempo + tempo);
        res.append(this.tempo); res.append("' -> ");
        if (equipaAtacar.compareTo("casa") == 0) {
            habilidadeAtacar = equipaVisitada.calculaHabilidadeAtacar(jogsEmCampoCasa);
            habilidadeDefender = equipaVisitante.calculaHabilidadeDefender(jogsEmCampoFora);
            res.append("Equipa da Casa ");
        } else {
            habilidadeAtacar = equipaVisitante.calculaHabilidadeAtacar(jogsEmCampoFora);
            habilidadeDefender = equipaVisitada.calculaHabilidadeDefender(jogsEmCampoCasa);
            res.append("Equipa de Fora ");
        }
        Random gerador = new Random();
        int x = gerador.nextInt(100);
        if (x < (20 + (habilidadeAtacar - habilidadeDefender))) {
            // caso em que marca golo
            res.append("marcou! (");
            if (equipaAtacar.compareTo("casa") == 0)
                this.golosCasa++;
            else
                this.golosFora++;
            res.append(this.golosCasa);
            res.append(" vs ");
            res.append(this.golosFora);
            res.append(")");
        } else // caso em que perde a posse de bola
            res.append("perdeu a posse de bola.");

        return res.toString();
    }


}
