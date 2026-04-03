public class logger {
   public static void main(String[] args){
    loggerFactory factory = new loggerFactory();
    Logger logger = factory.getLogger("console");
    logger.log("Hello World");
    Logger logger2 = factory.getLogger("file");
    logger2.log("Hello World");
    Logger logger3 = factory.getLogger("null");
    logger3.log("Hello World");
   } 
}

class loggerFactory{
    Logger getLogger(String s){
        if(s.equals("console")){
            return new ConsoleLogger();
        }
        else if(s.equals("file")){
            return new FileLogger();
        }
        else{
            return new NullLogger();
        }
        
    }
}

class Logger{
    void log(String message){
        System.out.println(message);
    }
}

class ConsoleLogger extends Logger{
    @Override
    void log(String message){
        System.out.println("[Console] " + message);
    }
}

class FileLogger extends Logger{
    @Override
    void log(String message){
        System.out.println("[File] " + message);
    }
}

class NullLogger extends Logger{
    @Override
    void log(String message){
        System.out.println("");
    }
}