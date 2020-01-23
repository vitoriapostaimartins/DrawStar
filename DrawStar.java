package q2;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * <p>The DrawStar class draws a five-pointed star
 *  using a rubber banding technique. </p>
 * <p>This class first processes the mouse press, 
 * positioning the atCenter variable
 * initializing the lines that will form the star, as well as adding them to the
 * root group. Then, it processes the mouse drag, where it creates 5 points, 
 * positions them and create the lines that form the star using them.</p>
 *
 * @author Vitoria Postai Martins
 * @version 1.0
 */

public class DrawStar extends Application {
    /**
     * Line 1.
     */
    private Line l1;
    /**
     * Line 2.
     */
    private Line l2;
    /**
     * Line 3.
     */
    private Line l3;
    /**
     * Line 4.
     */
    private Line l4;
    /**
     * Line 5.
     */
    private Line l5;
    
    /**
     * The number of points and lines needed to create a star.
     */
    private final int numberOfPoints = 5;
    
    /**
     * Cos of 1 fraction (out of 5) of a circle.
     */
    private double cos = Math.cos(2 * Math.PI / numberOfPoints);
    
    /**
     * Sin of 1 fraction (out of 5) of a circle.
     */
    private double sin = Math.sin(2 * Math.PI / numberOfPoints);
    
    /** The contents of the application scene. */
    private Group root;

    /** Center point. */
    private Point2D center;
    
    /** Circle to move to first mouse click location. */
    private final Circle atCenter = new Circle(0, 0, 3);
    
    /**
     * Point 1 (used to form the lines).
     */
    private Point2D p1;
    
    /**
     * Point 2 (used to form the lines).
     */
    private Point2D p2; 
    
    /**
     * Point 3 (used to form the lines).
     */
    private Point2D p3;
    
    /**
     * Point 4 (used to form the lines).
     */
    private Point2D p4;
    
    /**
     * Point 5 (used to form the lines).
     */
    private Point2D p5;
    

    /**
     * Displays an initially empty scene, waiting for the user to draw lines
     * with the mouse.
     * 
     * @param primaryStage
     *            a Stage
     */
    
    
    public void start(Stage primaryStage) {
        root = new Group(atCenter);
        atCenter.setFill(Color.CYAN);

        final int appWidth = 1000;
        final int appHeight = 800;
        Scene scene = new Scene(root, appWidth, appHeight, Color.BLACK);
        scene.setOnMousePressed(this::processMousePress);
        scene.setOnMouseDragged(this::processMouseDrag);

        primaryStage.setTitle("Star");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    /**
     * Mouse event when mouse clicks. it makes the dot.
     * @param event
     * mouse click area taken in. 
     */
    public void processMousePress(MouseEvent event) {
        //sets the position of the the circle in the middle
        atCenter.setCenterX(event.getX());
        atCenter.setCenterY(event.getY());
        
        //creates the point in the center of the star based on the mouse press
        center =  new Point2D(event.getX(), event.getY());
        
        //initializes lines to form the star
        l1 = new Line(event.getX(), 
                event.getY(), event.getX(), event.getY());
        l1.setStroke(Color.CYAN);
        
        l2 = new Line(event.getX(), 
                event.getY(), event.getX(), event.getY());
        l2.setStroke(Color.CYAN);
        
        l3 = new Line(event.getX(), 
                event.getY(), event.getX(), event.getY());
        l3.setStroke(Color.CYAN);
        
        l4 = new Line(event.getX(), event.getY(),
                event.getX(), event.getY());
        l4.setStroke(Color.CYAN);
        
        l5 = new Line(event.getX(), event.getY(),
                event.getX(), event.getY());
        l5.setStroke(Color.CYAN);

        root.getChildren().add(l1); 
        root.getChildren().add(l2); 
        root.getChildren().add(l3); 
        root.getChildren().add(l4); 
        root.getChildren().add(l5);  
        
    }
    
    /**
     * Runs the mouse drag event.
     * @param event
     * Event where mouse is dragged.
     */
    public void processMouseDrag(MouseEvent event) {       
        p1 = new Point2D(event.getX(), event.getY());
        
        //formula:
        //x1 = x * cos(angle) - y * sin(angle) 
        //y1 = y * cos(angle) + x * sin(angle)
        
        p2 = new Point2D(((event.getX() - center.getX() * cos) 
                - (event.getY() - center.getY() * sin)), 
                ((event.getX() - center.getX() * sin) 
                        + (event.getY() - center.getY() * cos)));
        p3 = new Point2D(((p2.getX() * cos)
                - (p2.getY() * sin)),  ((p2.getX() * sin) 
                        + (p2.getY() * cos)));
        p4 = new Point2D(((p3.getX() * cos) 
                - (p3.getY() * sin)), ((p3.getX() * sin
                        ) + (p3.getY() * cos)));
        p5 = new Point2D(((p4.getX() * cos) 
                - (p4.getY() * sin)),  ((p4.getX() * sin) + (p4.getY() * cos)));


        p2 = new Point2D((p2.getX() + center.getX()),
                (p2.getY() + center.getY()));


         p3 = new Point2D((p3.getX() 
                 + center.getX()),  (p3.getY() + center.getY()));

         p4 = new Point2D((p4.getX() 
                 + center.getX()),  (p4.getY() + center.getY()));
         p5 = new Point2D((p5.getX() 
                 + center.getX()),  (p5.getY() + center.getY()));
       

         l1.setStartX(p1.getX());
         l1.setStartY(p1.getY());
         
         l1.setEndX(p3.getX());
         l1.setEndY(p3.getY());

         l2.setStartX(p2.getX());
         l2.setStartY(p2.getY());
         
         l2.setEndX(p4.getX());
         l2.setEndY(p4.getY());
         
         l3.setStartX(p3.getX());
         l3.setStartY(p3.getY());
         
         l3.setEndX(p5.getX());
         l3.setEndY(p5.getY());
         
         l4.setStartX(p4.getX());
         l4.setStartY(p4.getY());
         
         l4.setEndX(p1.getX());
         l4.setEndY(p1.getY());
         
         l5.setStartX(p5.getX());
         l5.setStartY(p5.getY());
         
         l5.setEndX(p2.getX());
         l5.setEndY(p2.getY());

       }
    
    /**
     * Launches the JavaFX application.
     * 
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}