import java.util.Scanner;

/**
 * Esta classe � parte da aplica��o "Mundo de Zuul". 
 * 
 */

public class Parser 
{
    private CommandWords commands;  // cont�m os comandos v�lidos
    private Scanner reader;         // origem da entrada de comandos

    /**
     * Cria um parser para ler da janela de terminal.
     */
    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * @return O pr�ximo comando do usu�rio.
     */
    public Command getCommand() 
    {
        String inputLine;   //  vai conter a entrada completa
        String word1 = null;
        String word2 = null;

        System.out.print("> ");     // imprime o prompt

        inputLine = reader.nextLine();

        // Encontra at� duas palavras na linha.
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();      // pega a primeira palavra
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();      // pega a segunda palavra
                // nota: simplesmente ignoramos o resto da linha.
            }
        }

        // Agora checa se esta palavra � conhecida. Caso positivo, cria um
        // comando com ela. Se n�o, cria um comando "null" 
        // (para comando desconhecido).
        if(commands.isCommand(word1)) {
            return new Command(word1, word2);
        }
        else {
            return new Command(null, word2); 
        }
    }
}