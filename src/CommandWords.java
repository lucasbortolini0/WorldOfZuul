/**
 * Esta classe � parte da aplica��o "Mundo de Zuul". 
 * 
 */

public class CommandWords
{
    // um array constante que cont�m todos os comandos v�lidos
    private static final String[] VALID_COMMANDS = {
        "ir_para", "sair", "ajuda","examinar","comer"
    };

    /**
     * Construtor - inicializa os comandos
     */
    public CommandWords()
    {
        // nada a fazer no momento...
    }
    /**
     *imprimir todos os comandos validos*/
    
     public void showAll() {
    	 for(String command: VALID_COMMANDS) {
    	System.out.println(command + "");
    	 }
    	 System.out.println();
     }

    /**
     * Checa se uma string � uma palavra v�lida. 
     * @param aString uma string 
     * @return true se a string � um comando v�lido, falso se n�o
     */
    public boolean isCommand(String aString)
    {
        for (String command : VALID_COMMANDS) {
            if (command.equals(aString)) {
                return true;
            }
        }
        // se chegamos aqui, a string n�o foi encontrada nos comandos
        return false;
    }
}