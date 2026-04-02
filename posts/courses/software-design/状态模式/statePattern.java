public class statePattern{
    public void main(){
        Context context = new Context(new Happy());
        context.doWork();
        context.changeState(new Sad());
        context.doWork();
        context.changeState(new Tired());
        context.doWork();
    }
}
abstract class State{
    abstract void doWork();
}

class Happy extends State{
    @Override
    void doWork() {
        System.out.println("Happy");
    }
}
class Sad extends State{
    @Override
    void doWork() {
        System.out.println("Sad");
    }
}
class Tired extends State{
    @Override
    void doWork() {
        System.out.println("Tired");
    }
}

class Context{
    private State state;
    public Context(State state){
        this.state = state;
    }
    public void changeState(State state){
        this.state = state;
    }
    public void doWork(){
        state.doWork();
    }
}
