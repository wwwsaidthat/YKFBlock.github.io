public class strategy {
    public static void main(String[] args){
        calStrategy strategy = new FreeShippingOverThreshold();
        int fee = strategy.calcFee(100,100,false);
        System.out.println(fee);
        strategy = new StandardShipping();
        fee = strategy.calcFee(100,100,false);
        System.out.println(fee);
        strategy = new RemoteAreaShipping();
        fee = strategy.calcFee(100,100,false);
        System.out.println(fee);
    }
}


class calStrategy{
     int calcFee(int weightGram, int distanceKm, boolean isRemoteArea){
        return 0;
     }
}
class FreeShippingOverThreshold extends calStrategy{
    @Override
    int calcFee(int weightGram, int distanceKm, boolean isRemoteArea) {
        return 0;
    }
}
class StandardShipping extends calStrategy{
    @Override
    int calcFee(int weightGram, int distanceKm, boolean isRemoteArea) {
        return 0;
    }
}
class RemoteAreaShipping extends calStrategy{
    @Override
    int calcFee(int weightGram, int distanceKm, boolean isRemoteArea) {
        return 0;
    }
}




