package sample;

public class Tile {
    private location loc;
    private Plants plant_placed;

    public Tile(location loc) {
        this.loc = loc;
    }

    public location getLoc() {
        return loc;
    }

    public void setLoc(location loc) {
        this.loc = loc;
    }

    public Plants getPlant_placed() {
        return plant_placed;
    }

    public void setPlant_placed(Plants plant_placed) {
        this.plant_placed = plant_placed;
    }
}
