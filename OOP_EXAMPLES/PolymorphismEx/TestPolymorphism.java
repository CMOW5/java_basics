import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class TestPolymorphism {

	public static void main(String[] args) {
		
		 DecimalFormat twoDigits = new DecimalFormat("0.00"); //to format numbers
		 
		//without polymorphism 
		 
		 Point point = new Point(7,11);
		 Circle circle = new Circle(22,8,3.5);
		 Cylinder cylinder = new Cylinder(20,30,3.3,10.75);
		 
		 String outputString = point.getName() + ": " + point + "\n" + 
		                       circle.getName() + ": " + circle + "\n" +
		                       cylinder.getName() + ": " + cylinder + "\n";
		 
		 //using polymorphism 
		 Figure figureArray[] = new Figure[3];
		 
		 figureArray[0] = point; //pointer to the point subclass object
		 figureArray[1] = circle; //pointer to the circle subclass object
		 figureArray[2] = cylinder; //pointer to the cylinder subclass object
		 
		 for (int i = 0; i < figureArray.length ; i++){
			 
			 outputString += "\n\n" + figureArray[i].getName() + ": " + figureArray[i].toString() +
					         "\nArea= " + twoDigits.format(figureArray[i].getArea()) + 
					         "\nVolume= " + twoDigits.format(figureArray[i].getVolume());
		
		 }
		 
		 
		 JOptionPane.showMessageDialog(null, outputString);
		 System.exit(0);

	}

}
