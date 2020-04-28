import java.util.*;

public class Main {

    public static void main(String[] args) {

    // Set the number of roads and street lights
    Scanner scanner = new Scanner(System.in);
    System.out.println("How many roads?");
    int roadSpawns = scanner.nextInt();
    System.out.println("How many cars?");
    int carSpawns = scanner.nextInt();
    // Store adjacent paths
    List<String> connectedRoad = new ArrayList<>();
    List<TrafficLight> lights = new ArrayList<>();

    //Create objects:
    System.out.println("Object Creation:\n---------------------");
    System.out.println("Roads:");
    ArrayList<Road> roads = new ArrayList<>();
    for (int i = 0; i < roadSpawns; i++) {
        System.out.println("Please input parameters for road_" + i + "...");
        System.out.print("Length:");
        int lengthInput = scanner.nextInt();
        int speedLimitInput = (new Random()).nextInt(11);
        roads.add(new Road(Integer.toString(i), speedLimitInput, lengthInput, new int[]{0, 0}));
        System.out.println("Please input connected roads of road_" + i + "...(like:0,1,2...)");
        String infos = scanner.next();
        connectedRoad.add(infos);
    }
    System.out.println("\nRoads;");
    for (Road road : roads) {
        road.printRoadInfo();
    }

    // Set up TrafficLight at road junctions
    for (int i = 0;i < connectedRoad.size();i ++){
        TrafficLight trafficLight = null;
        if (roads.get(i).getLightsOnRoad().size() > 0) trafficLight = roads.get(i).getLightsOnRoad().get(0);
        else{
            long currentTime=new Date().getTime();
            trafficLight = new TrafficLight(String.valueOf(currentTime), roads.get(i));
            roads.get(i).addTrafficLight(trafficLight);
            lights.add(trafficLight);
        }
        for(String s:connectedRoad.get(i).split(",")){
            roads.get(i).addConnectRoad(roads.get(Integer.parseInt(s)));
            if (roads.get(Integer.parseInt(s)).getLightsOnRoad().size() > 0) continue;
            else  roads.get(Integer.parseInt(s)).addTrafficLight(trafficLight);
        }
    }

    System.out.println("\nCars;");
    ArrayList<Car> cars = new ArrayList<>();
    for (int i = 0; i < carSpawns; i++) {
        // random add bus or motorbke
        if((new Random()).nextInt(10) <= 5){
            cars.add(new Bus(Integer.toString(i), roads.get(0)));
        } else {
            cars.add(new Motorbike(Integer.toString(i), roads.get(0)));
        }
        cars.get(i).printCarStatus();
    }

    // Let car run
    int time = 0;
    System.out.print("\nSet time scale in milliseconds:");
    int maxtime = scanner.nextInt();
    while (true) {
        // change trafficlight state
        for (TrafficLight light : lights) {
            light.chageState();
        }
        for (Car car : cars) {
            car.move();
            car.printCarStatus();
        }
        time = time + 1;
        System.out.println(time + " Seconds have passed.\n");
        if (time >= maxtime){
            System.out.println("timeout");
            break;
        }
    }
}
}
