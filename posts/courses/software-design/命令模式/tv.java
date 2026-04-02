
public class tv {
    public static void main(String[] args){
        TV tv = new TV("Close");
        Controller controller = new Controller(new OpenCommand(tv), new CloseCommand(tv), new ChangeCommand(tv));
        controller.open();
        controller.close();
        controller.changeChannel();
    }
}

class Controller{
    private Command openCommand;
    private Command closeCommand;
    private Command changeCommand;
    public Controller(Command openCommand, Command closeCommand, Command changeCommand){
        this.openCommand = openCommand;
        this.closeCommand = closeCommand;
        this.changeCommand = changeCommand;
    }
    public void open(){
        openCommand.execute();
    }
    public void close(){
        closeCommand.execute();
    }
    public void changeChannel (){
        changeCommand.execute();
    }
}

interface Command {
    void execute();
}

class OpenCommand implements Command{
    private TV tv;
    public OpenCommand(TV tv){
        this.tv = tv;
    }
    
    public void execute(){
        tv.state = "Open";
        tv.open();
    }
}

class CloseCommand implements Command{
    private TV tv;
    public CloseCommand(TV tv){
        this.tv = tv;
    }
    public void execute(){
        tv.state = "Close";
        tv.close();
    }
}
class ChangeCommand implements Command{
    private TV tv;
    public ChangeCommand(TV tv){
        this.tv = tv;
    }
    public void execute(){
        tv.changeChannel();
    }
}

class TV{
    String state;
    int channel;
    public TV(String state){
        this.state = state;
    }
    public void open(){
        state = "Open";
        System.out.println("Open TV");
    }
    public void close(){
        state = "Close";
        System.out.println("Close TV");
    }
    public void changeChannel (){
        this.channel = this.channel + 1;
        System.out.println("Change Channel " + channel);
    }
}