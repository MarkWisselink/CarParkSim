package CarParkSim.objects;


public class LocationGrid {
    public LocationGrid(){
        
    }
    
    public Location getFirstFreeLocation(){
        return null;
    }
    
    public void setLocationState(Location loc, int state){
        
    }
    
    public void setCarAt(Location loc, Car car){
        setLocationState(loc, 1);
    }
    
    public void removeCarAt(Location loc){
        setLocationState(loc, 0);
    }
    
    public Car getFirstLeavingCar(){
        return null;
    }
}
