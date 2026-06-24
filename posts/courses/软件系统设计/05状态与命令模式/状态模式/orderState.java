abstract class State{
    abstract void pay();
    abstract void ship();
    abstract void complete();
    abstract void cancel();
    abstract void refund();
    abstract String getStateName();
}

class Created extends State{
    @Override
    void pay() {
        System.out.println("Pay");
    }
    @Override
    void cancel(){
        System.out.println("Cancel");
    }
    @Override
    void refund(){
        System.out.println("Unable to Refund");
    }
    @Override
    void ship(){
        System.out.println("Unable to Ship");
    }
    @Override
    void complete(){
        System.out.println("Unable to Complete");
    }
    @Override
    String getStateName(){
        return "Created";
    }
}
class Paid extends State{
    @Override
    void pay() {
        System.out.println("Pay");
    }
    @Override
    void cancel(){
        System.out.println("Unable to Cancel");
    }
    @Override
    void refund(){
        System.out.println("Unable to Refund");
    }
    @Override
    void ship(){
        System.out.println("Unable to Ship");
    }
    @Override
    void complete(){
        System.out.println("Unable to Complete");
    }
    @Override
    String getStateName(){
        return "Paid";
    }
}
class Shipped extends State{
    @Override
    void pay() {
        System.out.println("Unable to Pay");
    }
    @Override
    void cancel(){
        System.out.println("Unable to Cancel");
    }
    @Override
    void refund(){
        System.out.println("Unable to Refund");
    }
    @Override
    void ship(){
        System.out.println("Ship");
    }
    @Override
    void complete(){
        System.out.println("Complete");
    }
    @Override
    String getStateName(){
        return "Shipped";
    }
}
class Completed extends State{
    @Override
    void pay() {
        System.out.println("Unable to Pay");
    }
    @Override
    void cancel(){
        System.out.println("Unable to Cancel");
    }
    @Override
    void refund(){
        System.out.println("Unable to Refund");
    }
    @Override
    void ship(){
        System.out.println("Unable to Ship");
    }
    @Override
    void complete(){
        System.out.println("Complete");
    }
    @Override
    String getStateName(){
        return "Completed";
    }
}
class Canceled extends State{
    @Override
    void pay() {
        System.out.println("Unable to Pay");
    }
    @Override
    void cancel(){
        System.out.println("Unable to Cancel");
    }
    @Override
    void refund(){
        System.out.println("Unable to Refund");
    }
    @Override
    void ship(){
        System.out.println("Unable to Ship");
    }
    @Override
    void complete(){
        System.out.println("Unable to Complete");
    }
    @Override
    String getStateName(){
        return "Canceled";
    }
}

class Context{
    private State state;
    public Context(State state){
        this.state = state;
    }
    public void changeState(State state){
        this.state = state;
        System.out.println("Change State to " + state.getStateName());
    }
     void pay(){
        state.pay();
     }
     void ship(){
        state.ship();
     }
     void complete(){
        state.complete();
     }
     void cancel(){
        state.cancel();
     }
     void refund(){
        state.refund();
     }
    
}
//客户端代码
public class orderState {
    public static void main(){
        Context context = new Context(new Created());
        context.pay();
        context.changeState(new Paid());
        context.pay();
        context.ship();
        context.complete();
        context.cancel();
        context.refund();
    }
    
}