
public class player {
    public void main(){
        Client client = new Client(new Stopped());
        client.play();
        client.changeState(new Playing());
        client.play();
        client.changeState(new Paused());
        client.pause();
        client.changeState(new Buffering());
        client.onBufferEmpty();
        client.stop();
    }
}

abstract class State{
    abstract void play();
    abstract void pause();
    abstract void stop();
    abstract void onBufferEmpty();
    abstract void onBufferReady();

}

 class Stopped extends State{
    @Override
    void play() {
        System.out.println("Play");
    }
    @Override
    void pause() {
        System.out.println("Unable to Pause");
    }
    @Override
    void stop() {
        System.out.println("Unable to Stop");
    }
    @Override
    void onBufferEmpty() {
        System.out.println("Unable to On Buffer Empty");
    }
    @Override
    void onBufferReady() {
        System.out.println("Unable to On Buffer Ready");
    }
}

 class Playing extends State{
    @Override
    void play() {
        System.out.println("Play");
    }
    @Override
    void pause() {
        System.out.println("Pause");
    }
    @Override
    void stop() {
        System.out.println("Stop");
    }
    @Override
    void onBufferEmpty() {
        System.out.println("On Buffer Empty");
    }
    @Override
    void onBufferReady() {
        System.out.println("On Buffer Ready");
    }
}

 class Paused extends State{
    @Override
    void play() {
        System.out.println("Play");
    }
    @Override
    void pause() {
        System.out.println("Unable to Pause");
    }
    @Override
    void stop() {
        System.out.println("Stop");
    }
    @Override
    void onBufferEmpty() {
        System.out.println("On Buffer Empty");
    }
    @Override
    void onBufferReady() {
        System.out.println("On Buffer Ready");
    }
}

 class Buffering extends State{
    @Override
    void play() {
        System.out.println("Play");
    }
    @Override
    void pause() {
        System.out.println("Unable to Pause");
    }
    @Override
    void stop() {
        System.out.println("Stop");
    }
    @Override
    void onBufferEmpty() {
        System.out.println("On Buffer Empty");
    }
    @Override
    void onBufferReady() {
        System.out.println("On Buffer Ready");
    }
}

 class Client{
    private State state;
    public Client(State state){
        this.state = state;
    }
    public void changeState(State state){
        this.state = state;
    }
    public void play(){
        state.play();
    }
    public void pause(){
        state.pause();
    }
    public void stop(){
        state.stop();
    }
    public void onBufferEmpty(){
        state.onBufferEmpty();
    }
    public void onBufferReady(){
        state.onBufferReady();
    }
    
   }
