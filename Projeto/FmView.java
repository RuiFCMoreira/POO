import java.awt.font.TextHitInfo;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FmView {
    FmController controller;
    Scanner ins;

    public FmView(FmController controller){

            this.controller = controller;
            this.ins = new Scanner(System.in);
    }

    public int menu(){

        System.out.println("\n1 - Consultar jogadores existentes");
        System.out.println("2 - Consultar equipas existentes");
        System.out.println("3 - Consultar jogos");
        System.out.println("4 - Criar Jogador");
        System.out.println("5 - Criar equipa");
        System.out.println("6 - Realizar jogo");
        System.out.println("7 - Fazer Transferência de Jogador");
        System.out.println("8 - Save");
        System.out.println("9 - Quit");
        int sel = leNumero(1,9,"Comando");

        return sel;

    }

    public void printOpcoes(List<String> ops){

        int i = 1;
        for(String s: ops){

            System.out.println(i + ": " + s);
            i++;
        }
    }

    public void consultarJogadoresExistentes(){

        int selection = -1;
        List<String> jogadores = this.controller.getJogadores();
        List<String> nomes = this.controller.getJogadoresNome();
        if(jogadores.size() == 0){
            System.out.println("Não existem jogadores criados");
            return;
        }
        printOpcoes(jogadores);
        selection = leNumero(1,jogadores.size(),"");
        String s = this.controller.getJogador(nomes.get(selection - 1));
        System.out.println(s);

    }

    public void consultarEquipasExistentes(){

        int selection = -1;
        List<String> equipas = this.controller.getEquipas();
        if(equipas.size() == 0){
            System.out.println("Não existem equipas criadas");
            return;
        }
        printOpcoes(equipas);
        selection = leNumero(1,equipas.size(),"");
        String s = this.controller.getEquipa(equipas.get(selection - 1));
        System.out.println(s);

    }


    //Consulta os jogos existestes

    public void consultarJogosExistentes(){
        int selection = -1;
        List<String> jogos = this.controller.getJogos();
        if(jogos.size() == 0){
            System.out.println("Não existem jogos criados");
            return;
        }
        printOpcoes(jogos);
        selection = leNumero(1,jogos.size(),"");
        String s = this.controller.getJogo(selection);
        System.out.println(s);
    }

    public void fazerTransferencia() {
        //this.ins.nextLine();
        System.out.println("Insira o nome do Jogador que pretende transferir");
        String nomeJogador = this.ins.nextLine();
        System.out.println("Insira a equipa Destino");
        String equipaDestino = this.ins.nextLine();


        try {
            this.controller.transfereEquipa(equipaDestino,nomeJogador);
        } catch (FmModel.JogadorInexistenteException e) {
            System.out.println("O Jogador '" + e.getMessage() + "' não existe.");
        } catch (Jogo.EquipaNaoExisteException e){
            System.out.println("A Equipa '" + e.getMessage() + "' não existe.");
        }
    }

    //Função que lê e verifica validade de atributo

    public int leAtributo(String atributo){
        int atr = this.ins.nextInt();
        while(atr > 99 || atr < 1){
            System.out.println(atributo+ " Inválido");
            atr = this.ins.nextInt();
        }
        return  atr;
    }

    // Função que suporta a funcionaldidade de criar um jogador
    public void criarJogador(){
        System.out.println("\nInsira o tipo de jogador que pretende criar");
        System.out.println("1 - Guarda - Redes");
        System.out.println("2 - Defesa");
        System.out.println("3 - Lateral");
        System.out.println("4 - Medio");
        System.out.println("5 - Avançado");
        int sel = leNumero(1,5,"Posição");
        //this.ins.nextLine();

        List<String> equipas = new ArrayList<>();
        String equipa = "";
        String nome = "";
        int numero = 0;
        int velocidade = 0;
        int resistencia = 0;
        int destreza = 0;
        int impulsao = 0;
        int jogoAereo = 0;
        int remate = 0;
        int passe = 0;
        int elasticidade = 0;
        int reflexos = 0;
        int desarme = 0;
        int marcacao = 0;
        int agressividade = 0;
        int capacidadeCruzamento = 0;
        int drible = 0;
        int recuperacaoDeBola = 0;
        int criatividade = 0;
        int finalizacao = 0;
        int piorPe = 0;

        System.out.println("Insira a equipa do jogador ou F para nao atribuir equipa");
        equipa = this.ins.nextLine();
        while(!this.controller.existeEquipa(equipa) && equipa.compareTo("F") != 0 && equipa.compareTo("f") != 0){
            System.out.println("Equipa inválida");
            equipa = this.ins.nextLine();

        }
        equipas = new ArrayList<>();
        if(equipa.compareTo("F") != 0 && equipa.compareTo("F") != 0) equipas.add(equipa);

        System.out.println("Insira o nome do jogador");
        nome = this.ins.nextLine();
        System.out.println("Insira o numero do jogador (1-99)");
        numero = leNumero(1,99,"Número");
        System.out.println("Insira o velocidade do jogador (1-99)");
        velocidade = leNumero(1,99,"Velocidade");
        System.out.println("Insira o valor de resistencia (1-99)");
        resistencia = leNumero(1,99,"Resistencia");
        System.out.println("Insira o valor da destreza (1-99)");
        destreza = leNumero(1,99,"Destreza");
        System.out.println("Insira o valor da impulsao (1-99)");
        impulsao = leNumero(1,99,"Impulsao");
        System.out.println("Insira o valor do jogo aereo (1-99)");
        jogoAereo = leNumero(1,99,"Jogo Aereo");
        System.out.println("Insira o valor do remate (1-99)");
        remate = leNumero(1,99,"Remate");
        System.out.println("Insira o valor do passe (1-99)");
        passe = leNumero(1,99,"Passe");


        switch(sel){
            case 1:
                System.out.println("Insira o valor da elasticidade do jogador (1-99)");
                elasticidade = leNumero(1,99,"Elasticidade");
                System.out.println("Insira o valor dos reflexos do jogador (1-99)");
                reflexos = leNumero(1,99,"Reflexos");

                this.controller.criaRedes(nome,numero,velocidade,resistencia,destreza,impulsao,jogoAereo,remate,passe,equipas,elasticidade,reflexos);
                break;

            case 2:
                System.out.println("Insira o valor do desarme do jogador (1-99)");
                desarme = leNumero(1,99,"Desarme");
                System.out.println("Insira o valor da marcacao do jogador (1-99)");
                marcacao = leNumero(1,99,"Marcacao");
                System.out.println("Insira o valor da agressividade do jogador (1-99)");
                agressividade = leNumero(1,99,"Agressividade");
                this.controller.criaDefesa(nome,numero,velocidade,resistencia,destreza,impulsao,jogoAereo,remate,passe,equipas,desarme,marcacao,agressividade);
                break;

            case 3 :
                System.out.println("Insira o valor da capacidade de cruzamento do jogador (1-99)");
                capacidadeCruzamento = leNumero(1,99,"Capacidade de Cruzamento");
                System.out.println("Insira o valor do drible do jogador (1-99)");
                drible = leNumero(1,99,"Drible");

                this.controller.criaLateral(nome,numero,velocidade,resistencia,destreza,impulsao,jogoAereo,remate,passe,equipas,capacidadeCruzamento,drible);
                break;

            case 4:
                System.out.println("Insira o valor da recuperação de bola (1-99)");
                recuperacaoDeBola = leNumero(1,99,"Recuperacão De Bola");
                System.out.println("Insira o valor da criatividade do jogador (1-99)");
                criatividade = leNumero(1,99,"Criatividade");

                this.controller.criaMedio(nome,numero,velocidade,resistencia,destreza,impulsao,jogoAereo,remate,passe,equipas,recuperacaoDeBola,criatividade);
                break;

            case 5:

                System.out.println("Insira o valor da Finalização (1-99)");
                finalizacao = leNumero(1,99,"Finalização");
                System.out.println("Insire o valor do drible (1-99)");
                drible = leNumero(1,99,"Drible");
                System.out.println("Insira o valor do pior pé (1-99)");
                piorPe = leNumero(1,99,"Pior pé");
                
                this.controller.criaAvancado(nome,numero,velocidade,resistencia,destreza,impulsao,jogoAereo,remate,passe,equipas,finalizacao,drible,piorPe);
                break;

            default:
                System.out.println("Seleção inválida");
                break;
        }
    }


    public int leNumero (int limInf,int limSup,String tipo) {
        int res = 0;
        while (res == 0) {
            String comando = this.ins.nextLine();
            try {
                res = this.controller.validaComando(comando);
                if (res >= limInf && res <= limSup) ;
                else {
                    System.out.println("Este número '"+ res + "' não está dentro dos parametros. Por favor insira um número entre "+limInf + " e " + limSup);
                    res = 0;
                }
            } catch (FmController.ComandoInvalidoException e) {
                System.out.println(tipo +" '" + e.getMessage() + "' é inválido! Tente novamente.");
            }
        }
        return res;
    }


    //Função que suporta a funcionalidade de criar uma equipa

    public void criarEquipa(){
        int tam = 0;
        //Cria equipa sem jogadores
        List<String> opcs;
        String selection = "";
        System.out.println("Insira o nome da equipa:");
        String nomeEquipa = this.ins.nextLine();
        while (this.controller.existeEquipa(nomeEquipa)){
            System.out.println("Equipa já existe");
            nomeEquipa = this.ins.nextLine();
        }
        System.out.println("Insira o nome do treinador");
        String treinador = this.ins.nextLine();
        List<Jogador> plantel = new ArrayList<>();
        this.controller.criaEquipa(nomeEquipa,treinador,plantel);

        //Lê o plantel
        System.out.println("\nSelecione (1) para  escolher manualmente o plantel (2) para criar a melhor equipa possivel\n");
        int num = this.leNumero(1,2,"Seleção");
        if(num == 2) {
            try {
                this.controller.criaMelhorEquipa(nomeEquipa,treinador);
            } catch (Jogo.EquipaNaoExisteException e) {
                System.out.println("Erro a criar equipa");
            } catch (FmModel.JogadorInexistenteException e) {
                System.out.println("Erro nos dados dos jogadores");
            }
        }else {
            System.out.println("\nSelecione o Plantel\n");

            //Guarda-Redes

            System.out.println("\nSelecione no minimo 2 Guarda-Redes  x,y\n");
            opcs = this.controller.getGuardaRedes();
            if (opcs.size() == 0) {
                System.out.println("Não existem Guarda-Redes suficientes no sistema");
                return;
            }
            printOpcoes(opcs);
            tam = opcs.size();
            selection = ins.nextLine();
            while (!this.controller.verificaSelecaoJogadores(selection, 2, tam, 2)) {
                System.out.println("Seleção inválida");
                selection = ins.nextLine();
            }
            try {
                this.controller.adicionaJogadores(selection, nomeEquipa, "Guarda-Redes");
            } catch (Jogo.EquipaNaoExisteException e) {
                System.out.println("A equipa '" + e.getMessage() + "'não existe\n");
            } catch (FmModel.JogadorInexistenteException e) {
                System.out.println("O jogador '" + e.getMessage() + "' não existe\n");
            }

            //Defesas

            System.out.println("\nSelecione no minimo 3 Defesas  x,y\n");
            opcs = this.controller.getDefesas();
            if (opcs.size() == 0) {
                System.out.println("Não existem Defesas suficientes no sistema");
                return;
            }
            printOpcoes(opcs);
            tam = opcs.size();
            selection = ins.nextLine();
            while (!this.controller.verificaSelecaoJogadores(selection, 3, tam, 2)) {
                System.out.println("Seleção inválida");
                selection = ins.nextLine();
            }
            try {
                this.controller.adicionaJogadores(selection, nomeEquipa, "Defesa");
            } catch (Jogo.EquipaNaoExisteException e) {
                System.out.println("A equipa '" + e.getMessage() + "'não existe\n");
            } catch (FmModel.JogadorInexistenteException e) {
                System.out.println("O jogador '" + e.getMessage() + "' não existe\n");
            }

            //Laterais

            System.out.println("\nSelecione no minimo 5 Laterais  x,y\n");
            opcs = this.controller.getLaterais();
            if (opcs.size() == 0) {
                System.out.println("Não existem Laterais suficientes no sistema");
                return;
            }
            printOpcoes(opcs);
            tam = opcs.size();
            selection = ins.nextLine();
            while (!this.controller.verificaSelecaoJogadores(selection, 5, tam, 2)) {
                System.out.println("Seleção inválida");
                selection = ins.nextLine();
            }
            try {
                this.controller.adicionaJogadores(selection, nomeEquipa, "Lateral");
            } catch (Jogo.EquipaNaoExisteException e) {
                System.out.println("A equipa '" + e.getMessage() + "' não existe\n");
            } catch (FmModel.JogadorInexistenteException e) {
                System.out.println("O jogador '" + e.getMessage() + "' não existe\n");
            }

            //Medios

            System.out.println("\nSelecione no minimo 5 Medios  x,y\n");
            opcs = this.controller.getMedios();
            if (opcs.size() == 0) {
                System.out.println("Não existem Medios suficientes no sistema");
                return;
            }
            printOpcoes(opcs);
            tam = opcs.size();
            selection = ins.nextLine();
            while (!this.controller.verificaSelecaoJogadores(selection, 5, tam, 2)) {
                System.out.println("Seleção inválida");
                selection = ins.nextLine();
            }
            try {
                this.controller.adicionaJogadores(selection, nomeEquipa, "Medio");
            } catch (Jogo.EquipaNaoExisteException e) {
                System.out.println("A equipa '" + e.getMessage() + "' não existe\n");
            } catch (FmModel.JogadorInexistenteException e) {
                System.out.println("O jogador '" + e.getMessage() + "' não existe\n");
            }

            //Avancados

            System.out.println("\nSelecione no minimo 4 Avançados  x,y\n");
            opcs = this.controller.getAvancados();
            if (opcs.size() == 0) {
                System.out.println("Não existem Laterais suficientes no sistema");
                return;
            }
            printOpcoes(opcs);
            tam = opcs.size();
            selection = ins.nextLine();
            while (!this.controller.verificaSelecaoJogadores(selection, 4, tam, 2)) {
                System.out.println("Seleção inválida");
                selection = ins.nextLine();
            }
            try {
                this.controller.adicionaJogadores(selection, nomeEquipa, "Avancado");
            } catch (Jogo.EquipaNaoExisteException e) {
                System.out.println("A equipa '" + e.getMessage() + "'não existe\n");
            } catch (FmModel.JogadorInexistenteException e) {
                System.out.println("O jogador '" + e.getMessage() + "' não existe\n");
            }
        }


    }

    //Função que tranforma um String com uma data em um LocalDate

    public LocalDate parseDate(String data){
        int stop = 0;
        List<Integer> ints = new ArrayList<>();
        String[] splited = data.split("-");
        LocalDate res = LocalDate.now();
            if(splited.length <3){
                System.out.println("Insira uma data válida");
                data = this.ins.nextLine();
                res = parseDate(data);
            }else{
                for(String s: splited){
                    try {
                        ints.add(Integer.parseInt(s));
                    } catch (Exception e){
                        System.out.println("Insira uma data válida");
                        data = this.ins.nextLine();
                        res = parseDate(data);
                    }
                }

                try {
                    res = LocalDate.of(ints.get(0),ints.get(1),ints.get(2));
                }catch (DateTimeException e){
                    System.out.println("Data Inválida");
                    data = this.ins.nextLine();
                    parseDate(data);

                }

            }

        return res;

    }

    //Função Auxiliar Cria11 Incial

    public List<Integer> cria11InicialAux (String posEspecifica,  String posicao, List<String> opcs, List<Integer> titulares,int quantidade,String e) {
        String  selection = "";
        int size = 0;
        System.out.println("Selecione " + Integer.toString(quantidade) + " " + posEspecifica + " x");
        printOpcoes(opcs);
        int tam = opcs.size();
        selection = this.ins.nextLine();
        size = titulares.size();
        titulares = this.controller.adicionaTitulares(selection, titulares, e, posicao, quantidade,tam);

        while (size == titulares.size()) {
            System.out.println("Seleção de jogadores Inválida");
            selection = this.ins.nextLine();
            titulares = this.controller.adicionaTitulares(selection, titulares, e, posicao, quantidade,tam);
        }

        return titulares;
    }

    //Função que recebe e valida o 11 inicial

    public List<Integer> cria11inicial(String tatica,String e) {
        List<Integer> titulares = new ArrayList<>();
        List<String> redes = this.controller.getRedesEquipa(e);
        List<String> defesas = this.controller.getDefesasEquipa(e);
        List<String> laterais = this.controller.getLateraisEquipa(e);
        List<String> medios = this.controller.getMediosEquipa(e);
        List<String> avancados = this.controller.getAvancadosEquipa(e);


        if (tatica.compareTo("4-4-2") == 0) {

            // Guarda Redes

            try {
                titulares = cria11InicialAux("Guarda-Redes","Guarda-Redes",redes,titulares,1,e);
            }catch (IndexOutOfBoundsException exception){
                System.out.println("Seleção invalida - Não existe essa Opção");
                titulares = cria11InicialAux("Guarda-Redes","Guarda-Redes",redes,titulares,1,e);
            }

            //Lateral Esquerdo

            try {
                titulares = cria11InicialAux("Lateral Esquerdo","Laterais",laterais,titulares,1,e);
            }catch (IndexOutOfBoundsException exception){
                System.out.println("Seleção invalida - Não existe essa Opção");
                titulares = cria11InicialAux("Lateral Esquerdo","Laterais",laterais,titulares,1,e);
            }


            //Defesas

            try {
                titulares = cria11InicialAux("Defesas Centrais","Defesas",defesas,titulares,2,e);
            }catch (IndexOutOfBoundsException exception){
                System.out.println("Seleção invalida - Não existe essa Opção");
                titulares = cria11InicialAux("Defesas Centrais","Defesas",defesas,titulares,2,e);
            }


            // Lateral Direito

            try {
                titulares = cria11InicialAux("Lateral Direito","Laterais",laterais,titulares,1,e);
            }catch (IndexOutOfBoundsException exception){
                System.out.println("Seleção invalida - Não existe essa Opção");
                titulares = cria11InicialAux("Lateral Direito","Laterais",laterais,titulares,1,e);
            }


            //Medios

            try {
                titulares = cria11InicialAux("Medios","Medios",medios,titulares,4,e);
            }catch (IndexOutOfBoundsException exception){
                System.out.println("Seleção invalida - Não existe essa Opção");
                titulares = cria11InicialAux("Medios","Medios",medios,titulares,4,e);
            }



            //Avançados

            try {
                titulares = cria11InicialAux("Avançados","Avancados",avancados,titulares,2,e);
            }catch (IndexOutOfBoundsException exception){
                System.out.println("Seleção invalida - Não existe essa Opção");
                titulares = cria11InicialAux("Avançados","Avancados",avancados,titulares,2,e);
            }




        } else {

            // Guarda Redes

            try {
                titulares = cria11InicialAux("Guarda-Redes","Guarda-Redes",redes,titulares,1,e);
            }catch (IndexOutOfBoundsException exception){
                System.out.println("Seleção invalida - Não existe essa Opção");
                titulares = cria11InicialAux("Guarda-Redes","Guarda-Redes",redes,titulares,1,e);
            }


            //Lateral Esquerdo

            try {
                titulares = cria11InicialAux("Lateral Esquerdo","Laterais",laterais,titulares,1,e);
            }catch (IndexOutOfBoundsException exception){
                System.out.println("Seleção invalida - Não existe essa Opção");
                titulares = cria11InicialAux("Lateral Esquerdo","Laterais",laterais,titulares,1,e);
            }



            //Defesas

            try {
                titulares = cria11InicialAux("Defesas Centrais","Defesas",defesas,titulares,2,e);
            }catch (IndexOutOfBoundsException exception){
                System.out.println("Seleção invalida - Não existe essa Opção");
                titulares = cria11InicialAux("Defesas Centrais","Defesas",defesas,titulares,2,e);
            }


            // Lateral Direito

            try {
                titulares = cria11InicialAux("Lateral Direito","Laterais",laterais,titulares,1,e);
            }catch (IndexOutOfBoundsException exception){
                System.out.println("Seleção invalida - Não existe essa Opção");
                titulares = cria11InicialAux("Lateral Direito","Laterais",laterais,titulares,1,e);
            }


            // Medios
            try {
                titulares = cria11InicialAux("Medios Centro","Medios",medios,titulares,3,e);
            }catch (IndexOutOfBoundsException exception){
                System.out.println("Seleção invalida - Não existe essa Opção");
                titulares = cria11InicialAux("Medios Centro","Medios",medios,titulares,3,e);
            }

            //Avancado Esquerdo

            try {
                titulares = cria11InicialAux("Avancado Esquerdo","Laterais",laterais,titulares,1,e);
            }catch (IndexOutOfBoundsException exception){
                System.out.println("Seleção invalida - Não existe essa Opção");
                titulares = cria11InicialAux("Avancado Esquerdo","Laterais",laterais,titulares,1,e);
            }


            //Avancado

            try {
                titulares = cria11InicialAux("Avancado","Avancados",avancados,titulares,1,e);
            }catch (IndexOutOfBoundsException exception){
                System.out.println("Seleção invalida - Não existe essa Opção");
                titulares = cria11InicialAux("Avancado","Avancados",avancados,titulares,1,e);
            }

            //Avancado Direito

            try {
                titulares = cria11InicialAux("Avancado Direito","Laterais",laterais,titulares,1,e);
            }catch (IndexOutOfBoundsException exception){
                System.out.println("Seleção invalida - Não existe essa Opção");
                titulares = cria11InicialAux("Avancado Direito","Laterais",laterais,titulares,1,e);
            }


        }

            return titulares;
        }


     //Função que lê e valida as substituições inseridas

    public Map<Integer,Integer> leSubstituicoes(String equipa, List<Integer> titulares){
        //Formato (S-E,S-E,S-E)
        System.out.println("Insira as Substituições");
        Map<Integer,Integer> subs = new HashMap<>();
        String input;
        input = this.ins.nextLine();
        if(input.length() == 0) return new HashMap<>();
        String[] splited = input.split(",");
        if(!(splited.length <= 3)){
            System.out.println("Substituições a mais");
            return leSubstituicoes(equipa, titulares);
        }else{
            for(String s : splited){
                String[] doisJogadores = s.split("-");
                if(doisJogadores.length != 2){
                    System.out.println("Formato da substiuição errado");
                    return leSubstituicoes(equipa,titulares);
                }else{
                    try {
                        subs.put(Integer.parseInt(doisJogadores[0]),Integer.parseInt(doisJogadores[1]));
                    }
                    catch (NumberFormatException e){

                        System.out.println("As substituições devem ser formadas pelos números dos jogadores");
                        return leSubstituicoes(equipa,titulares);
                    }

                    if(!this.controller.validaSubs(equipa, titulares, subs)) {
                        System.out.println("Substituição impossivel");
                        return leSubstituicoes(equipa,titulares);
                    }

                }
            }
        }
        return subs;
    }


    //Função que mostra as opções de substituição

    public void printOpcsSubstituicao(String equipa, List<Integer> titulares){

        System.out.println("\nTitulares:\n");
        List<String> onze = this.controller.getTitularesNumeroNomeHabilidade(equipa,titulares);
        for(String s : onze){
            System.out.println(s);
        }

        System.out.println("\nPossiveis Suplentes:\n");
        List<String> suplentes = this.controller.getSuplentesNumeroNomeHabilidade(equipa,titulares);
        for(String s : suplentes){
            System.out.println(s);
        }


    }


        //Função que suporta a funcionalidade de criar um jogo

    public List<Integer> leSubstituicao (String equipa, List<Integer> jogadoresEmCampo) {
        System.out.println("Insira a substituição que pretende fazer no formato (S-E)");
        String input;
        input = this.ins.nextLine();
        String[] doisJogadores = input.split("-");
        int nSai = -1,nEntra = -1;
        if (doisJogadores.length != 2) {
            System.out.println("Formato da substiuição errado");
            return leSubstituicao(equipa, jogadoresEmCampo);
        } else {
            try {
                nSai = Integer.parseInt(doisJogadores[0]);
                nEntra = Integer.parseInt(doisJogadores[1]);
                jogadoresEmCampo = this.controller.efectuaSub(equipa,jogadoresEmCampo,nSai,nEntra);
            } catch (NumberFormatException e) {
                System.out.println("As substituições devem ser formadas pelos números dos jogadores");
                return leSubstituicao(equipa, jogadoresEmCampo);
            } catch (Jogo.SubstituicaoInvalidaException e) {
                System.out.println("A substituição que tentou fazer não é válida!");
            } catch (Jogo.SubstituicaoIndisponivelException e) {
                System.out.println("A " + e.getMessage() + "já efectuou todas as substituições disponíveis");
            }
        }
        return jogadoresEmCampo;
    }



    //Função que suporta a funcionalidade de criar um jogo
    public void realizarJogo(){
        String equipa1 = "";
        String equipa2 = "";
        String taticaCasa = "";
        String taticaFora = "";
        String data = "";
        LocalDate parsedData  = LocalDate.now();
        List<Integer> casa11 = new ArrayList<>();
        List<Integer> fora11 = new ArrayList<>();
        int selection = -1;
        Map<Integer,Integer>subsCasa = new HashMap<>();
        Map<Integer,Integer> subsFora = new HashMap<>();

        if(this.controller.getEquipas().size() < 2){
            System.out.println("Não existem equipas suficientes");
            return;
        }

        System.out.println("Insira a equipa da casa");
        equipa1 = this.ins.nextLine();

        while (!this.controller.existeEquipa(equipa1)){
            System.out.println("Insira uma equipa válida");
            equipa1 = this.ins.nextLine();
        }
        System.out.println("Insira a equipa visitante");
        equipa2 = this.ins.nextLine();
        while (!this.controller.existeEquipa(equipa2)){
            System.out.println("Insira uma equipa válida");
            equipa2 = this.ins.nextLine();
        }
        
         // as duas equipas tem jogadores suficientes ?
        try {
            this.controller.validaEquipas(equipa1,equipa2);
        } catch (FmController.JogadoresInsuficientesException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Pretende criar o 11 inicial manualmente(1) ou criar o melhor (2)");
        int num = leNumero(1,2,"Seleção");
        if(num == 2) {
            casa11 = this.controller.criaMelhor11(equipa1,"4-4-2");
            fora11 = this.controller.criaMelhor11(equipa2,"4-4-2");
        }else {

            //11 inicial da equipa da casa

            System.out.println("Insira a tática da equipa da casa 1 -> 4-4-2 ou 2 -> 4-3-3");
            selection = leNumero(1, 2, "Input");

            if (selection == 1) taticaCasa = "4-4-2";
            else taticaCasa = "4-3-3";

            System.out.println("\nEscolha o 11 inicial\n");
            casa11 = cria11inicial(taticaCasa, equipa1);

            //11 inicial da equipa visitante

            System.out.println("Insira a tática da equipa visitante 1 -> 4-4-2 ou 2 -> 4-3-3");
            selection = leNumero(1, 2, "Input");
            if (selection == 1) taticaFora = "4-4-2";
            else taticaFora = "4-3-3";

            System.out.println("\nEscolha o 11 inicial\n");
            fora11 = cria11inicial(taticaFora, equipa2);
        }

            // Data do encontro

            System.out.println("\nInsira a data do encontro AA-MM-DD\n");
            data = this.ins.nextLine();
            parsedData = this.parseDate(data);
            System.out.println(parsedData.toString());

        System.out.println("\nDeseja SIMULAR ENCONTRO(1) ou apenas ver o RESULTADO(2) ?\n");
        selection = leNumero(1, 2, "seleção");

        if(selection == 1){
            // cria jogo
            String print = this.controller.criaSimulaJogo(equipa1,equipa2,parsedData,casa11,subsCasa,fora11,subsFora,taticaCasa,taticaFora);
            System.out.println(print);

            String equipaAcomeçar1parte = "";
            String equipaAcomeçar2parte = "";

            Random gerador = new Random();
            int g = gerador.nextInt(2);
            if (g == 0) {
                equipaAcomeçar1parte = "casa";
                equipaAcomeçar2parte = "fora";
            }
            else{
                equipaAcomeçar1parte = "fora";
                equipaAcomeçar2parte = "casa";
            }

            print = this.controller.avancaTempoSubs(casa11,fora11,equipaAcomeçar1parte,equipaAcomeçar2parte,15);
            System.out.println(print);

            int min = 15;
            int entra = -1;
            int sai = -1;
            int equipa = -1;


            while (min < 90){
                System.out.println("Introduza 1 se quer pausar o jogo para substituições ou 2 caso contrário");
                int x = leNumero(1,2,"Opção");
                while (x == 1){
                    System.out.println("Introduza a equipa em que pretende fazer a substituições 1 - equipa Casa ou 2 - equipa Fora");
                    equipa = leNumero(1,2,"Seleção de Equipa");
                    if (equipa == 1) {
                        printOpcsSubstituicao(equipa1,casa11);
                        casa11 = leSubstituicao("casa",casa11);
                    }
                    else {
                        printOpcsSubstituicao(equipa2,fora11);
                        fora11 = leSubstituicao("fora",fora11);
                    }

                    System.out.println("Introduza 1 se pretende fazer mais Substituições ou 2 caso queira prosseguir o jogo");
                    x = leNumero(1,2,"Opção");
                }
                print = this.controller.avancaTempoSubs(casa11,fora11,equipaAcomeçar1parte,equipaAcomeçar2parte,15);
                System.out.println(print);
                min += 15;
            }


        }else{
                System.out.println("\nInsira as 3 substituições da equipa da casa (S-E,S-E,S-E) \n");
                printOpcsSubstituicao(equipa1,casa11);
                subsCasa = leSubstituicoes(equipa1, casa11);

                System.out.println("\nInsira as 3 substituições da equipa visitante (S-E,S-E,S-E) \n");
                printOpcsSubstituicao(equipa2,fora11);
                subsFora = leSubstituicoes(equipa2, fora11);
                
                String jogo = this.controller.criaCalculaResultadoJogo(equipa1, equipa2, parsedData, casa11, subsCasa, fora11, subsFora, taticaCasa, taticaFora);
                System.out.println(jogo);
            }


    }


    public void saveData() {
        System.out.println("Insira o nome da pasta onde deseja efetuar a gravação");
        String pasta = this.ins.nextLine();
        try {
            this.controller.save(pasta);
        } catch (IOException e) {
            System.out.println("Erro na gravação de ficheiro.");
        }
    }

    public void run() {

        List<String> opcs;
        int selection = -1;
        System.out.println("Insira 1 para carregar os seus dados");
        System.out.println("Insira 2 para carregar os dados predefinidos");
        System.out.println("Insira 3 para começar do zero");
        String nomeDaPasta = "";
        selection = leNumero(1, 3, "Comando");
        if(selection == 1){
            System.out.println("Insira o nome da pasta onde tem os ficheiros guardados");
            nomeDaPasta = ins.nextLine();
        }

        try {
            this.controller.loadDataController(selection,nomeDaPasta);
        } catch (LinhaIncorretaException e) {

            System.out.println("Linha Incorreta. Não tem nenhuma equipa para adicionar Jogador");

        } catch (IOException e) {
            System.out.println("Erro ao abrir o ficheiro");
        } catch (ClassNotFoundException e) {
            System.out.println("Classe do Ficheiro não encontrada");
        }

        boolean quit = false;

        while(!quit){

            selection = menu();

            switch (selection){
                case 1:
                    consultarJogadoresExistentes();
                    break;
                case 2:
                    consultarEquipasExistentes();
                    break;

                case 3:
                    consultarJogosExistentes();
                    break;

                case 4:
                    criarJogador();
                    break;

                case 5:
                    criarEquipa();
                    break;

                case 6:
                    realizarJogo();
                    break;

                case 7:
                    fazerTransferencia();
                    break;
                case 8:
                    saveData();
                case 9:
                    quit = true;


            }
        }
    }


}
