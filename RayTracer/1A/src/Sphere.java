
public class Sphere {

	private double radius;
	public Vector cs;
	public Vector sphere_col;
	
	public Sphere(double radius, Vector cs, Vector sphere_col) {
		this.radius = radius;
		this.cs = cs;
		this.sphere_col = sphere_col;
	}
	
	//Take in p from line equation
	public double findIntersection(Vector d, Vector o) {
		Vector v = o.sub(cs);
		double a = d.dot(d);
		double b = 2 * v.dot(d);
		double c = v.dot(v) - radius * radius;
		
		double disc = b * b - 4 * a * c;//Cannot have -ve value/won't intersect
		if (disc < 0.0) {
			return 0.0; //Doesn't hit
		}
		double t = (-b - Math.sqrt(disc))/2*a;//Find the closest hit, if -ve it is behind camera
		if (t < 0) {
			t = (-b + Math.sqrt(disc))/2*a;
		}
		if (t < 0) {
			return 0.0; //Behind camera
		}
		return t;
	}
}
