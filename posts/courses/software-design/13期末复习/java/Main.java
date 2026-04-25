public class Main{
  public static void main(String[] args){
    Facade fc = new Facade();
    System.out.println(fc.prove());
  }
}
class SubFlow1{
  public boolean isTrue(){return true;}
}
class SubFlow2{
  public boolean isOk(){return true;}
}
class SubFlow3{
  public boolean isSafe(){return true;}
}
class Facade{
  SubFlow1 sf1 = new SubFlow1();
  SubFlow2 sf2 = new SubFlow2();
  SubFlow3 sf3 = new SubFlow3();
  public boolean prove(){
    return sf1.isTrue()&&sf2.isOk()&&sf3.isSafe();
  }
}