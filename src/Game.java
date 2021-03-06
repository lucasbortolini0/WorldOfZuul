/**
 *  Esta � a classe principal do jogo World of Zuul. 
 *  
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Cria o jogo e inicializa o mapa interno.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Cria todas as salas e liga suas sa�das.
     */
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office, attic;
      
        // create the rooms
        outside = new Room("fora da entrada principal da universidade");
        theatre = new Room("em um audit�rio");
        pub = new Room("na cantina do campus");
        lab = new Room("em um laborat�rio de inform�tica");
        office = new Room("na sala dos professores");
        attic = new Room("no s�t�o da laborat�rio");
        
        // initialise room exits
        outside.setExits("leste", theatre);
        outside.setExits("sul", lab);
        outside.setExits("oeste", pub);
        theatre.setExits("oeste", outside);
        pub.setExits("leste", outside);
        lab.setExits("norte", outside);
        lab.setExits("leste", office);
        lab.setExits("cima", attic);
        attic.setExits("baixo", lab);
        office.setExits("oeste", lab);

        currentRoom = outside;  // Come�a o jogo fora 
    }

    /**
     *  A rotina de jogo principal. Faz um loop at� o fim do jogo.
     */
    public void play() 
    {            
        printWelcome();

        // Entra o loop principal. Aqui lemos comandos repetidamente e 
        // os executamos at� que o jogo termime.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Obrigado por jogar.  Adeus.");
    }

    /**
     * Imprime a mensagem de boas vindas ao usu�rio.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bem-vindo ao Mundo de Zuul!");
        System.out.println("Mundo de Zuul � um jogo de aventura, incrivelmente chato.");
        System.out.println("Digite 'ajuda' se voc� precisar de ajuda.");
        System.out.println();
        printLocationInfo();
    }
    
    private void look(Command command) {
    	System.out.println(currentRoom.getLongDescription());
    }
    private void comer(Command command) {
    	System.out.println("Voce n�o esta mais com fome");
    }
    
    /**
     * Imptimr informa��es relativas �
     * Localiza��o atual.
     */
    private void printLocationInfo() {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Dado um comando, processa (ou seja: executa) o comando.
     * @param command O comando a ser processado.
     * @return true Se o comando finaliza o jogo, sen�o, falso.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("N�o sei o que voc� quer dizer...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("ajuda"))
            printHelp();
        else if (commandWord.equals("ir_para"))
            goRoom(command);
        else if (commandWord.equals("sair"))
            wantToQuit = quit(command);
        else if(commandWord.equals("examinar")) 
        	look(command);
        else if(commandWord.equals("comer"))
        	comer(command);
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Imprime informa��es de ajuda.
     * Aqui imprimimos ua mensagem est�pida e listamos os comandos
     * dispon�veis.
     */
    private void printHelp() 
    {
        System.out.println("Voc� est� perdido. Voc� est� s�. Voc� caminha");
        System.out.println("pela universidade.");
        System.out.println();
        System.out.println("Seus comandos s�o:");
        parser.showCommands();
    }

    /** 
     * Tenta ir para uma dire��o. Se h� uma sa�da, entra na
     * nova sala, sen�o imprime uma mensagem de erro.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // se n�o h� segunda palavra, n�o sabemos onde ir...
            System.out.println("Ir para onde?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("N�o h� uma porta!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /** 
     * "Sair" foi digitado. Verifica o resto do comando para saber
     * se o usu�rio quer realmente sair do jogo.
     * @return true, se este comando sair do jogo, falso caso contr�rio.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Sair de do qu�?");
            return false;
        }
        else {
            return true;  // significa que queremos sair
        }
    }
}