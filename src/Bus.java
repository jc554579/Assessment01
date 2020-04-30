public class Bus extends Car {

    public Bus(String id, Road road) {
        this.id = ("bus_" + id);
        this.setCurrentRoad(road);
        if (this.getCurrentRoad() != null) {
            this.getCurrentRoad().addCar(this); //add this car to the road its on.
        }
        length = super.getLength() * 3;
        this.setMaxspeed(4);
    }

}

