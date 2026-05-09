class ComputerAssembler{
    private static Builder builder = new ConcreteBuilder();
    
    public static Computer assembleComputer(String type) {
        switch (type) {
            case "office":
                builder.buildCpu("i3");
                builder.buildMemory("8GB");
                builder.buildHardDisk("512GB");
                builder.buildGraphicsCard("集成显卡");
                break;
            case "gaming":
                builder.buildCpu("i7");
                builder.buildMemory("16GB");
                builder.buildHardDisk("1TB");
                builder.buildGraphicsCard("RTX4060");
                break;  
            case "design":
                builder.buildCpu("i9");
                builder.buildMemory("32GB");
                builder.buildHardDisk("2TB");
                builder.buildGraphicsCard("RTX4090");
                break;          
            default:
                break;
        }
        return builder.getComputer();
    }
    
}
abstract class Builder{
    abstract void buildCpu(String cpu);
    abstract void buildMemory(String memory);
    abstract void buildHardDisk(String hardDisk);
    abstract void buildGraphicsCard(String graphicsCard);
    abstract Computer getComputer();
}
class ConcreteBuilder extends Builder{
    private Computer computer = new Computer();
    @Override
    public void buildCpu(String cpu) {
        computer.cpu = cpu;
    }
    @Override
    public void buildMemory(String memory) {
        computer.memory = memory;
    }
    @Override
    public void buildHardDisk(String hardDisk) {
        computer.hardDisk = hardDisk;
    }
    @Override
    public void buildGraphicsCard(String graphicsCard) {
        computer.graphicsCard = graphicsCard;
    }
    @Override
    public Computer getComputer() {
        return computer;
    }
   
}

class Computer{
    String cpu;
    String memory;
    String hardDisk;
    String graphicsCard;
    public String getCpu() {
        return cpu;
    }
    public String getMemory() {
        return memory;
    }
    public String getHardDisk() {
        return hardDisk;
    }
    public String getGraphicsCard() {
        return graphicsCard;
    }
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", memory='" + memory + '\'' +
                ", hardDisk='" + hardDisk + '\'' +
                ", graphicsCard='" + graphicsCard + '\'' +
                '}';
    } 
}

public class Main {

    public static void main(String[] args) {
        Computer office = ComputerAssembler.assembleComputer("office");
        System.out.println(office.toString());
        Computer gaming = ComputerAssembler.assembleComputer("gaming");
        System.out.println(gaming.toString());
        Computer design = ComputerAssembler.assembleComputer("design");
        System.out.println(design.toString());
    }
        
}
