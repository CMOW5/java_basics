import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class TestCylinder {

	public TestCylinder() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
	 Cylinder cylinder = new Cylinder(12,23,2.5,5.7);
	 
	 String outputString = "The X coordinate is " + cylinder.getX() + 
			               "\nThe Y coordinate is " + cylinder.getY() +
			               "\nThe Radius is " + cylinder.getRadius() +
			               "\nThe height is " + cylinder.getHeight();
	 
	 cylinder.setX(35);
	 cylinder.setY(20);
	 cylinder.setRadius(4.25);
	 cylinder.setHeight(10.75);
	 
	 outputString += "\nThe new location, radius and height are\n " + cylinder.toString();
	 
	 //set format to double data to two digits precision
	 DecimalFormat twoDigits = new DecimalFormat("0.00");
	 outputString += "\n\nThe diameter is " + twoDigits.format(cylinder.getDiameter());
	 outputString += "\nThe circumference is " + twoDigits.format(cylinder.getCircumference());
	 outputString += "\nThe Area is " + twoDigits.format(cylinder.getArea());
	 outputString += "\nThe Volume is " + twoDigits.format(cylinder.getVolume());
	 
	 JOptionPane.showMessageDialog(null, outputString);
	 System.exit(0);
	 
	}

}
