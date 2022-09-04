package math;

public class Convert {
    public static Vec2 to1Vector(Vec2 vector) {
        while(vector.x >= 1 || vector.y >= 1) {
            vector.x -= 1;
            vector.y -= 1;
        }

        return vector;
    }
}
