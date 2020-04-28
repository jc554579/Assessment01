public class Motorbike  extends Car {

    public Motorbike(String id, Road road) {
        this.id = ("bike_" + id);
        this.setCurrentRoad(road);
        if (this.getCurrentRoad() != null){
            this.getCurrentRoad().addCar(this); //add this car to the road its on.
        }
        length = super.getLength() * 0.5f;
        this.setMaxspeed(2);
    }

}