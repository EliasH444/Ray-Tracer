
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.scene.control.Slider;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.io.*;
import java.lang.Math.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;

/**
 * Worked as a pair
 * @author Mohamed Ainanshe 2147207 and Elias Hassan 2008351
 *
 */

public class Main extends Application {
  int Width = 640;
  int Height = 450;

  //Colours of each sphere
  Vector sphere1Col = new Vector(255,50,255);
  Vector sphere2Col = new Vector(255,255,255);
  Vector sphere3Col = new Vector(255,255,255);
  Vector sphere4Col = new Vector(255,255,255);
  
  //Positions of each sphere
  Vector sphere1Pos = new Vector(0,0,0);
  Vector sphere2Pos = new Vector(200,0,0);
  Vector sphere3Pos = new Vector(0,0,-1);
  Vector sphere4Pos = new Vector(100,120,-60);		  
  
  double cameraX = 0;
  double cameraY = 0;
  
  ToggleGroup group;
  
  int numSpheres = 4;
  boolean[] spheres = new boolean[numSpheres];

  @Override
  public void start(Stage stage) throws FileNotFoundException {
    stage.setTitle("Ray Tracing");

    //We need 3 things to see an image
    //1. We create an image we can write to
    WritableImage image = new WritableImage(Width, 350);
    //2. We create a view of that image
    ImageView view = new ImageView(image);
    //3. Add to the pane (below)

    //Create the simple GUI
    Slider greenSlider = new Slider(0, 255, 255);
    greenSlider.setShowTickMarks(true);
    greenSlider.setShowTickLabels(true);
    greenSlider.setMajorTickUnit(50);
    greenSlider.setPadding(new Insets(-5,100,-15,80));
    
    Slider blueSlider = new Slider(0,255, 255);
    blueSlider.setShowTickMarks(true);
    blueSlider.setShowTickLabels(true);
    blueSlider.setMajorTickUnit(50);
    blueSlider.setPadding(new Insets(-5,100,-15,80));
    
    Slider redSlider = new Slider(0,255, 255);
    redSlider.setShowTickMarks(true);
    redSlider.setShowTickLabels(true);
    redSlider.setMajorTickUnit(50);
    redSlider.setPadding(new Insets(-5,100,-15,80));
    
    Slider xSlider = new Slider(-250, 250, 0);
    xSlider.setShowTickMarks(true);
    xSlider.setShowTickLabels(true);
    xSlider.setMajorTickUnit(50);
    xSlider.setPadding(new Insets(-5,100,-15,80));
    
    Slider ySlider = new Slider(-250, 250, 0);
    ySlider.setShowTickMarks(true);
    ySlider.setShowTickLabels(true);
    ySlider.setMajorTickUnit(50);
    ySlider.setPadding(new Insets(-5,100,-15,80));
    
    Slider zSlider = new Slider(-250, 250, 0);
    zSlider.setShowTickMarks(true);
    zSlider.setShowTickLabels(true);
    zSlider.setMajorTickUnit(50);
    zSlider.setPadding(new Insets(-5,100,-15,80));
    
    Slider azimuthSlider = new Slider(-1000, 1000, 0);
    azimuthSlider.setShowTickMarks(true);
    azimuthSlider.setShowTickLabels(true);
    azimuthSlider.setMajorTickUnit(50);
    azimuthSlider.setPadding(new Insets(-5,100,-15,80));
    
    Slider altitudeSlider = new Slider(-1000, 1000, 0);
    altitudeSlider.setShowTickMarks(true);
    altitudeSlider.setShowTickLabels(true);
    altitudeSlider.setMajorTickUnit(50);
    altitudeSlider.setPadding(new Insets(-5,100,-15,80));
    
    //Labels for each corresponding slider
    Label gLabel = new Label("Green");
    gLabel.setPadding(new Insets(-10,0,0,10));
    
    Label rLabel = new Label("Red");
    rLabel.setPadding(new Insets(-10,0,0,10));
    
    Label bLabel = new Label("Blue");
    bLabel.setPadding(new Insets(-10,0,0,10));
    
    Label xLabel = new Label("X coord");
    xLabel.setPadding(new Insets(-10,0,0,10));
    
    Label yLabel = new Label("Y coord");
    yLabel.setPadding(new Insets(-10,0,0,10));
    
    Label zLabel = new Label("Z coord");
    zLabel.setPadding(new Insets(-10,0,0,10));
    
    Label azimuthLabel = new Label("azimuth");
    azimuthLabel.setPadding(new Insets(-10,0,0,10));
    
    Label altitudeLabel = new Label("altitude");
    altitudeLabel.setPadding(new Insets(-10,0,0,10));
    
    group = new ToggleGroup();
    RadioButton sphere1Btn = new RadioButton("1");
    RadioButton sphere2Btn = new RadioButton("2");
    RadioButton sphere3Btn = new RadioButton("3");
    RadioButton sphere4Btn = new RadioButton("4");
    sphere1Btn.setToggleGroup(group);
    sphere2Btn.setToggleGroup(group);
    sphere3Btn.setToggleGroup(group);
    sphere4Btn.setToggleGroup(group);
    
    ArrayList<Vector> sphereColList = new ArrayList<>();
    sphereColList.add(sphere1Col);
    sphereColList.add(sphere2Col);
    sphereColList.add(sphere3Col);
    sphereColList.add(sphere4Col);
    
    ArrayList<Vector> spherePosList = new ArrayList<>();
    spherePosList.add(sphere1Pos);
    spherePosList.add(sphere2Pos);
    spherePosList.add(sphere3Pos);
    spherePosList.add(sphere4Pos);
    
    group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

		@Override
		public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
			RadioButton selectedRadioBtn = (RadioButton) group.getSelectedToggle();
			
			int selectedSphere = Integer.valueOf(selectedRadioBtn.getText()) - 1;
			for (int i = 0; i < spheres.length; i++) {
				if (i == selectedSphere) {
					spheres[i] = true;
				} else {
					spheres[i] = false;
				}
			}
			
			redSlider.setValue(sphereColList.get(selectedSphere).x);
			greenSlider.setValue(sphereColList.get(selectedSphere).y);
			blueSlider.setValue(sphereColList.get(selectedSphere).z);
			
			xSlider.setValue(spherePosList.get(selectedSphere).x);
			ySlider.setValue(spherePosList.get(selectedSphere).y);
			zSlider.setValue(spherePosList.get(selectedSphere).z);
			
			azimuthSlider.setValue(cameraX);
			altitudeSlider.setValue(cameraY);
		}
   
    });
    
    //Add all the event handlers
    greenSlider.valueProperty().addListener(
      new ChangeListener < Number > () {
        public void changed(ObservableValue < ? extends Number >
          observable, Number oldValue, Number newValue) {
        	
        	for (int i = 0; i < spheres.length; i++) {
        		if (spheres[i] == true) {
        			sphereColList.get(i).y = newValue.intValue();
        		}
        	}
          
        	Render(image);
        }
      });
    
    blueSlider.valueProperty().addListener(
    	      new ChangeListener < Number > () {
    	        public void changed(ObservableValue < ? extends Number >
    	          observable, Number oldValue, Number newValue) {
    	        	for (int i = 0; i < spheres.length; i++) {
    	        		if (spheres[i] == true) {
    	        			sphereColList.get(i).z = newValue.intValue();
    	        		}
    	        	}
    	        	Render(image);
    	        }
    	      });
    
    redSlider.valueProperty().addListener(
  	      new ChangeListener < Number > () {
  	        public void changed(ObservableValue < ? extends Number >
  	          observable, Number oldValue, Number newValue) {
  	        	
  	        	for (int i = 0; i < spheres.length; i++) {
  	        		if (spheres[i] == true) {
  	        			sphereColList.get(i).x = newValue.intValue();
  	        		}
  	        	}
  	        	Render(image);
  	        }
  	      });
    
    xSlider.valueProperty().addListener(
    	      new ChangeListener < Number > () {
    	        public void changed(ObservableValue < ? extends Number >
    	          observable, Number oldValue, Number newValue) {
    	        	
    	        	for (int i = 0; i < spheres.length; i++) {
    	        		if (spheres[i] == true) {
    	        			spherePosList.get(i).x = newValue.intValue();
    	        		}
    	        	}
    	        	Render(image);
    	        }
    	      });
    
    ySlider.valueProperty().addListener(
  	      new ChangeListener < Number > () {
  	        public void changed(ObservableValue < ? extends Number >
  	          observable, Number oldValue, Number newValue) {
  	        	
  	        	for (int i = 0; i < spheres.length; i++) {
  	        		if (spheres[i] == true) {
  	        			spherePosList.get(i).y = newValue.intValue();
  	        		}
  	        	}
  	        	Render(image);
  	        }
  	      });
    
    zSlider.valueProperty().addListener(
  	      new ChangeListener < Number > () {
  	        public void changed(ObservableValue < ? extends Number >
  	          observable, Number oldValue, Number newValue) {
  	        	
  	        	for (int i = 0; i < spheres.length; i++) {
  	        		if (spheres[i] == true) {
  	        			spherePosList.get(i).z = newValue.intValue();
  	        		}
  	        	}
  	        	Render(image);
  	        }
  	      });
    
    azimuthSlider.valueProperty().addListener(
            new ChangeListener < Number > () {
                public void changed(ObservableValue < ? extends Number >
                                            observable, Number oldValue, Number newValue) {
                    cameraX = newValue.intValue();
                    Render(image);
                }
            });
    
    altitudeSlider.valueProperty().addListener(
            new ChangeListener < Number > () {
                public void changed(ObservableValue < ? extends Number >
                                            observable, Number oldValue, Number newValue) {
                    cameraY = newValue.intValue();
                    Render(image);
                }
            });

    //The following is in case you want to interact with the image in any way
    //e.g., for user interaction, or you can find out the pixel position for debugging
    view.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_PRESSED, event -> {
      System.out.println(event.getX() + " " + event.getY());
      event.consume();
    });

    Render(image);

    GridPane root = new GridPane();
    root.setVgap(12);
    root.setHgap(12);

    //3. (referring to the 3 things we need to display an image)
    //we need to add it to the pane
    root.add(view, 0, 0);
    root.add(greenSlider, 0, 1);
    root.add(gLabel, 0, 1);
    root.add(blueSlider, 0, 2);
    root.add(bLabel,0,2);
    root.add(redSlider, 0, 3);
    root.add(rLabel, 0, 3);
    root.add(xSlider, 0, 4);
    root.add(xLabel, 0, 4);
    root.add(ySlider, 0, 5);
    root.add(yLabel, 0, 5);
    root.add(zSlider, 0, 6);
    root.add(zLabel, 0, 6);
    root.add(altitudeSlider, 0, 7);
    root.add(altitudeLabel, 0, 7);
    root.add(azimuthSlider, 0, 8);
    root.add(azimuthLabel, 0, 8);
    root.add(sphere1Btn, 1, 1);
    root.add(sphere2Btn, 1, 2);
    root.add(sphere3Btn, 1, 3);
    root.add(sphere4Btn, 1, 4);
    
    //Display to user
    Scene scene = new Scene(root, 640, 640);
    stage.setScene(scene);
    stage.show();
  }

  public void Render(WritableImage image) {
    //Get image dimensions, and declare loop variables
    int w = (int) image.getWidth(), h = (int) image.getHeight(), i, j;
    PixelWriter image_writer = image.getPixelWriter();
    
    Sphere s1 = new Sphere(100, sphere1Pos, new Vector((sphere1Col.x/255.0),((sphere1Col.y/255.0)/1.9),sphere1Col.z/255.0));
    Sphere s2 = new Sphere(50, sphere2Pos, new Vector((sphere2Col.x/255.0),((sphere2Col.y/255.0)/2),(sphere2Col.z/255)/5.1));
    Sphere s3 = new Sphere(60, sphere3Pos, new Vector((sphere3Col.x/255.0)/10,sphere3Col.y/255.0,(sphere3Col.z/255.0)/20));
    Sphere s4 = new Sphere(30, sphere4Pos, new Vector(((sphere4Col.x/255.0)/1.5),(sphere4Col.y/255.0),(sphere4Col.z/255.0)));
    
    ArrayList<Sphere> sphereList = new ArrayList<>();
    sphereList.add(s2);
    sphereList.add(s1);
    sphereList.add(s3);
    sphereList.add(s4);
    
    //Colour
    Vector background_col = new Vector(0.0,0.0,0.0);
    
    //Light
    Vector light = new Vector(0,200,-200);
  
    
    Vector o = new Vector(0,0,-200);
    Vector d = new Vector(0,0,1);
    Vector p;
    
    //Camera Calculations.
    Vector VRP = new Vector(cameraX,cameraY,400);
    Vector LookAt = new Vector(0,0,1);
    Vector VRV;
    Vector VPN = LookAt.sub(VRP);
    Vector VUV = new Vector(0,1,0);
    VPN.normalise();
    VRV = VPN.cross(VUV);
    VRV.normalise();
    VUV=VRV.cross(VPN);
    VUV.normalise();
    
    int closestSphere = -1;
    double hitDistance = Integer.MAX_VALUE;
    for (j = 0; j < h; j++) {
      for (i = 0; i < w; i++) {
    	  int u = i-w/2;
          int v1 = j-h/2;
          Vector pws = VRP;
          pws = pws.add(VRV.mul(u));
          pws = pws.add(VUV.mul(-v1));
          o = pws;
          d = VPN;
          
          //Finds which sphere is hit first
    	  for (int k = 0; k < sphereList.size(); k++) {
    		  double hitVal = sphereList.get(k).findIntersection(d, o);
    		  if (hitVal < hitDistance && hitVal > 0.0) {
    			  closestSphere = k;
    			  hitDistance = hitVal;
    		  }
    	  }
    	  
 
    	  if (closestSphere == -1 || sphereList.get(closestSphere).findIntersection(d, o) == 0) {
    		  image_writer.setColor(i, j, Color.color(background_col.x, background_col.y, background_col.z, 1.0));
    	  } else { //Hit sphere
    		  p = o.add(d.mul(sphereList.get(closestSphere).findIntersection(d, o)));//Point of intersection
    		  Vector surfaceNormal = p.sub(sphereList.get(closestSphere).cs);
    		  surfaceNormal.normalise();//unit vector now
    		  Vector lightV = light.sub(p);
   			  lightV.normalise();
   			  
   			  double dp = lightV.dot(surfaceNormal);
   			  if (dp < 0) dp = 0;//Behind the object
   			  if (dp > 1) dp = 1;
    			  
    		  Vector col = sphereList.get(closestSphere).sphere_col.mul(dp*0.7).add(sphereList.get(closestSphere).sphere_col.mul(0.3));
    		  image_writer.setColor(i, j, Color.color(col.x, col.y, col.z, 1.0));
    	  }
    	  hitDistance = Integer.MAX_VALUE;
      } // column loop
    } // row loop
  }

  public static void main(String[] args) {
    launch();
  }

}