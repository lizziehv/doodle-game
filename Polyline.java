import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * A multi-segment Shape, with straight lines connecting "joint" points -- (x1,y1) to (x2,y2) to (x3,y3) ...
 * 
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Spring 2016
 * @author CBK, updated Fall 2016
 */

/**
 * Edited by Lizzie Hernandez
 * Partner: Maria Paula Mora
 */
public class Polyline implements Shape {
	// TODO: YOUR CODE HERE
	public ArrayList<Point> segmentPoints;		// holds all points in polyline
	public Color color;

	/**
	 * Constructor, simply initializes an array list to add points to
	 */
	public Polyline(){
		segmentPoints = new ArrayList<>();
	}

	/**
	 *
	 */
	public Polyline(int x, int y, Color color){
		segmentPoints = new ArrayList<>();
		segmentPoints.add(new Point(x, y));
		this.color = color;
	}

	/**
	 * @param x new point coordinates to append to polyline points
	 * @param y new point coordinates to append to polyline points
	 */
	public void addPoint(int x, int y){
		segmentPoints.add(new Point(x, y));
	}

	/**
	 * @param dx x distance to move entire figure
	 * @param dy y distance to move entire figure
	 */
	@Override
	public void moveBy(int dx, int dy) {
		for(Point p: segmentPoints){
			p.setLocation((int)p.getX()+dx, (int)p.getY()+dy);
		}
	}

	/**
	 * @return shape's color
	 */
	@Override
	public Color getColor() {
		return color;
	}

	/**
	 * @param color The shape's color
	 */
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 *
	 * @param x given x coordinate
	 * @param y given y coordinate
	 * @return determine if x,y is "inside" the shape
	 */
	@Override
	public boolean contains(int x, int y) {
		// loop through all points in the shape
		for(int i = 0; i<segmentPoints.size()-1;i++){
			// grab a segment
			Point p1 = segmentPoints.get(i);
			Point p2 = segmentPoints.get(i+1);
			// for each segment, determine if (x,y) is close enough to the segment
			if(Segment.pointToSegmentDistance(x, y, (int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY())<=5){
				return true;
			}
		}
		return false;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		// handle a single point
		if(segmentPoints.size() == 1){
			Point p = segmentPoints.get(0);
			g.fillOval((int)p.getX()-2, (int)p.getY()-2, 4, 4);
		}
		// loop through all points
		for(int i = 0; i<segmentPoints.size()-1;i++) {
			// draw a segment
			Point p1 = segmentPoints.get(i);
			Point p2 = segmentPoints.get(i + 1);
			g.drawLine((int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY());
		}
	}

	@Override
	public String toString() {
		String result = "polyline ";
		for(Point p: segmentPoints){
			result += (int)(p.getX()) + " " + (int)(p.getY()) + " ";
		}
		result += color.getRGB();

		return result;
	}
}
