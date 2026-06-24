import java.util.ArrayList;
import java.util.List;
public class Main{
    public static void main(String[] args){
        Receiver receiver = new Receiver("Receiver");
        ConcreteCommand1 command1 = new ConcreteCommand1(receiver);
        ConcreteCommand2 command2 = new ConcreteCommand2(receiver);
        ConcreteCommand3 command3 = new ConcreteCommand3(receiver);
        MacroCommand macroCommand = new MacroCommand(receiver);
        macroCommand.addCommand(command1);
        macroCommand.addCommand(command2);
        macroCommand.addCommand(command3);
        macroCommand.execute();
    }
}

class Invoker{
    Command command1;
    Command command2;
    Command command3;
    Command macroCommand;
    public Invoker(Command command1, Command command2, Command command3, Command macroCommand){
        this.command1 = command1;
        this.command2 = command2;
        this.command3 = command3;
        this.macroCommand = macroCommand;
    }
}

abstract class Command{
    protected Receiver receiver;
    public Command(Receiver receiver){
        this.receiver = receiver;
    }
    public abstract void execute();
}

class ConcreteCommand1 extends Command{
    public ConcreteCommand1(Receiver receiver){
        super(receiver);
    }
    @Override
    public void execute() {
        System.out.println(receiver.name +" ConcreteCommand1 execute");
    }
}

class ConcreteCommand2 extends Command{
    public ConcreteCommand2(Receiver receiver){
        super(receiver);
    }
    @Override
    public void execute() {
        System.out.println(receiver.name +" ConcreteCommand2 execute");
    }
}

class ConcreteCommand3 extends Command{
    public ConcreteCommand3(Receiver receiver){
        super(receiver);
    }
    @Override
    public void execute() {
        System.out.println(receiver.name +" ConcreteCommand3 execute");
    }
}

class MacroCommand extends Command{
    public MacroCommand(Receiver receiver){
        super(receiver);
    }
    List<Command> commands = new ArrayList<>();
    public void addCommand(Command command){
        commands.add(command);
    }
    public void removeCommand(Command command){
        commands.remove(command);
    }
    @Override
    public void execute() {
        for(Command command : commands){
            command.execute();
        }
    }

}

class Receiver{
    String name;
    public Receiver(String name){
        this.name = name;
    }
}

