package Hello_World_Application;

public class HelloWorldApplication {
    /**
     *
     * @param args acts as the arguments provided to the application
     *             at the entry point level
     *             as on command line :
     *             java HelloWorldApplication arg1 arg2 ...
     *  This simply describes the application that
     *  displays hello world and the list of arguments on the command line.
     */
    public static void main(String[] args) {
        System.out.println("Hello World");
        System.out.println("\n ... List of arguments :  ");
        for(String arg : args) System.out.println(arg);
    }
}
