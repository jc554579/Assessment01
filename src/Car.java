public class Car {
    private static final int STOPPED = 0;           // car's stop speed
    private static final int NEXT_ROAD_INDEX = 0;   // next road
    private static final int START_POSITION = 1;    // car start position
    public String id; // unique identifier id
    public static float length = 1f; // The number of occupied segments is 1 for the convenience of prototyping.
    private static float breadth = 0.5f;
    private int speed = 0; // car;s speed
    private int position = 1; // car's cur position
    private Road currentRoad; // car's cur road
    private int maxspeed = 1; // Different types of cars have different maximum speed limits

    public Car(String id, Road currentRoad) {
        this.id = "car_" + id;
        this.currentRoad = currentRoad;
        if (currentRoad != null){
            this.currentRoad.addCar(this); //add this car to the road its on.
        }
    }

    public Car() {
        // Default parameter settings
        this("000", null);
    }

    public void gotoNextRoad(){
        this.currentRoad = this.currentRoad.getRandomRoad();
        this.currentRoad.addCar(this);
        this.position = START_POSITION;
    }

    public void move() {
        if (!this.currentRoad.canPass(this.getPosition())) {
            this.speed = STOPPED;
        } else {
            // Calculate driving speed
            this.speed = Math.min(this.currentRoad.getSpeedLimit(), this.maxspeed); // cal car speed

            if (this.currentRoad.getLength() == this.getPosition() && !this.currentRoad.getConnectedRoads().isEmpty()) {
                // When the car comes to an end
                this.currentRoad.getCarsOnRoad().remove(this);     //remove this car
                this.gotoNextRoad();
            } else if (this.currentRoad.getLength() > this.getPosition()) {
                this.position = (this.position + this.speed);
            } else {
                this.position = this.currentRoad.getLength();
                this.speed = STOPPED;
            }
        }
    }

    public void printCarStatus() {
        System.out.printf(this.toString());
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", speed=" + speed +
                ",road=" + this.currentRoad.getId()+
                ", position=" + position +
                ", maxspeed=" + maxspeed +
                "}\n";
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        Car.length = length;
    }

    public float getBreadth() {
        return breadth;
    }

    public void setBreadth(float breadth) {
        Car.breadth = breadth;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Road getCurrentRoad() {
        return currentRoad;
    }

    public void setCurrentRoad(Road currentRoad) {
        this.currentRoad = currentRoad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMaxspeed(){
        return this.maxspeed;
    }

    public void setMaxspeed(int maxspeed){
        this.maxspeed = maxspeed;
    }
}
