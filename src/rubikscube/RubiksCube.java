package rubikscube;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RubiksCube extends JPanel
{
    ArrayList<Cube> c = new ArrayList<>();
    long StartX,StartY,Length,Size;
    boolean isSolid;
    
    public RubiksCube() 
    {
        initUI();
    }
    
    private void initUI() 
    { 
       JSONParser jsonParser = new JSONParser();
        try 
        {
            //FileReader reader = new FileReader("C:\\Users\\ethan\\Documents\\NetBeansProjects\\RubiksCube\\src\\rubikscube\\properties.json");
            FileReader reader = new FileReader("properties.json");
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            
            JSONObject coords = (JSONObject) obj;
            System.out.println(coords);
            StartX = (long)coords.get("StartX");
            StartY = (long)coords.get("StartY");
            Length = (long)coords.get("Length");
            Size = (long)coords.get("Size");
            isSolid = (boolean)coords.get("isSolid");   
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
        catch (ParseException e) 
        {
            e.printStackTrace();
        }
        
        
        //c.add(new Cube(250,250,350,3,false));
        c.add(new Cube((int)StartX,(int)StartY,(int)Length,(int)Size,isSolid));
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(Cube cube: c)
        {
            cube.draw(g);
        }
        
    }

    public static void main(String[] args) 
    {
        JFrame frame = new JFrame();
        frame.setBounds(0,0,1000,1000);
        frame.setTitle("Moving Shapes");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        RubiksCube cube = new RubiksCube();
        frame.setContentPane(cube);
        //cube.setVisible(true);
    }

   
    
    
}
