public class Main{
  public static void main(String[] args){
    Star star2 = new RealStar();
    star2.act();
    Star star = new ProxyAgent(new RealStar());
    star.act();
    
  }
}

abstract class Star{
  public abstract void act();
}
class RealStar extends Star{
  @Override
  public void act() {
    System.out.println("明星认真拍戏");
  }
}
class ProxyAgent extends Star{
  private RealStar realStar;
  public ProxyAgent(RealStar realStar){
    this.realStar = realStar;
  }
  @Override
  public void act() {
    System.out.println("谈合同");
    System.out.println("接广告");
    realStar.act();
    System.out.println("收工");
  }
}