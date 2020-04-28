import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class Road {
    private String id;              // road id
    private int speedLimit;         // road limit
    private int length;             // road length
    private int[] startLocation;    // srart location
    private int[] endLocation;      // end location
    private ArrayList<Car> carsOnRoad = new ArrayList<>();              // a list store car that on the road
    private ArrayList<TrafficLight> lightsOnRoad = new ArrayList<>();   // a list store trafficLight that on the road
    private ArrayList<Road> connectedRoads = new ArrayList<>();         // a list store road that connected this road

    public Road(String id, int speedLimit, int length, int[] startLocation) {
        this.id = "road_" + id;
        this.speedLimit = speedLimit;
        this.length = length;
        this.setStartLocation(startLocation);
    }

    public void setStartLocation(int[] startLocation) {
        this.startLocation = startLocation;
        this.endLocation = new int[]{this.length + this.startLocation[0], 0}; //only works for horizontal roads;
    }

    public boolean canPass(int postion){
        if (this.lightsOnRoad.isEmpty()) return true;
        else{
            // Whether the traversal is now at the light
            for (TrafficLight trafficLight:this.lightsOnRoad){
                if (postion == trafficLight.getPosition() && trafficLight.getState().equals("red")) return false;
            }
        }
        return true;
    }

    /**
     * Randomly generated cars
     * @param carSpawns Generate the number of cars
     */
    public void createCars(int carSpawns) {
        long currentTime=new Date().getTime();
        for (int i = 0; i < carSpawns; i++)
            carsOnRoad.add(new Car(String.valueOf(currentTime + i), this));
    }

    /**
     * Return to the random next road
     * @return
     */
    public Road getRandomRoad(){
        Collections.shuffle(this.connectedRoads);
        return this.connectedRoads.get(0);
    }

    public String printEndLocation() {
        return endLocation[0] + "," + endLocation[1];
    }

    public void printRoadInfo() {
        System.out.printf(this.toString());
    }

    @Override
    public String toString() {
        return "Road{" +
                "id='" + id + '\'' +
                ", speedLimit=" + speedLimit +
                ", length=" + length +
                '}';
    }

    public void addCar(Car car){
        this.carsOnRoad.add(car);
    }

    public void addTrafficLight(TrafficLight trafficLight){
        this.lightsOnRoad.add(trafficLight);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String printStartLocation() {
        return startLocation[0] + "," + startLocation[1];
    }


    public void setEndLocation(int[] endLocation) {
        this.endLocation = endLocation;
    }

    public int[] getStartLocation() {
        return startLocation;
    }

    public int[] getEndLocation() {
        return endLocation;
    }

    public ArrayList<Car> getCarsOnRoad() {
        return carsOnRoad;
    }

    public void setCarsOnRoad(ArrayList<Car> carsOnRoad) {
        this.carsOnRoad = carsOnRoad;
    }

    public ArrayList<TrafficLight> getLightsOnRoad() {
        return lightsOnRoad;
    }

    public void setLightsOnRoad(ArrayList<TrafficLight> lightsOnRoad) {
        this.lightsOnRoad = lightsOnRoad;
    }

    public ArrayList<Road> getConnectedRoads() {
        return connectedRoads;
    }

    public void addConnectRoad(Road road){
        this.connectedRoads.add(road);
    }

    public void setConnectedRoads(ArrayList<Road> connectedRoads) {
        this.connectedRoads = connectedRoads;
    }
}
