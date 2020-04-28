import java.util.Random;

public class TrafficLight {
    private static final double CHANGE_GREEN = 0.5; // 50/50 chance of changing state.
    private static final String GREEN = "green";
    private static final String RED = "red";
    private String id;              // id
    private String state;           // cur state
    private int position;           // trafficlight's pasition
    private Road roadAttachedTo;    // which road this trafficlight belong

    public TrafficLight(String id, Road road) {
        this.id = "light_" + id;
        state = RED;
        this.roadAttachedTo = road;
        position = this.roadAttachedTo.getLength();
        this.roadAttachedTo.addTrafficLight(this);      // adds this light to the road it belongs to.
    }

    /**
     * Randomly generate initial state
     */
    public void operate() {
        double probability =(new Random()).nextDouble();
        this.setState(probability > CHANGE_GREEN ? GREEN : RED);
    }

    public void printLightStatus() {
        System.out.printf(this.toString());
    }

    public void chageState(){
        if(this.state.equals("green")) this.state = "red";
        else this.state = "green";
    }

    @Override
    public String toString() {
        return "TrafficLight{" +
                "id='" + id + '\'' +
                ", state='" + state + '\'' +
                ", position=" + position +
                ", roadAttachedTo=" + roadAttachedTo +
                '}';
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Road getRoadAttachedTo() {
        return roadAttachedTo;
    }

    public void setRoadAttachedTo(Road roadAttachedTo) {
        this.roadAttachedTo = roadAttachedTo;
    }

    public int getPosition() {
        return position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
