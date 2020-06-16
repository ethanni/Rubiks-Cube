package rubikscube;

import java.awt.Color;
import java.awt.Graphics;
import static java.lang.Math.sqrt;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.*;

public class Cube
{
    int TopLeftX, TopLeftY, TopRightX, TopRightY, BotLeftX, BotLeftY, BotRightX, BotRightY, length, increment, size;
    boolean solid;
    int [] FrontX,FrontY,TopX,TopY,SideX,SideY;
    
    public Cube(int x, int y, int l, int s, boolean flag)
    {
        length = l;
        TopLeftX = x;
        TopLeftY = y;
        TopRightX = x+l;
        TopRightY = y;
        BotLeftX = x;
        BotLeftY = y+l;
        BotRightX = x+l;
        BotRightY = y+l;
        increment = length/s;
        size = s;
        solid = flag;
        //front face
        FrontX = new int[] {TopLeftX,TopRightX,BotRightX,BotLeftX};
        FrontY = new int[] {TopLeftY,TopRightY,BotRightY,BotLeftY};
           
        //top face
        TopX = new int[] {TopLeftX,TopLeftX + (int)((sqrt(2.0)/4)*length),TopRightX +(int)((sqrt(2.0)/4)*length),TopRightX};
        TopY = new int[] {TopLeftY,TopLeftY - (int)((sqrt(2.0)/4)*length),TopRightY -(int)((sqrt(2.0)/4)*length),TopRightY};
        
        //side face
        SideX = new int[] {TopRightX,TopRightX +(int)((sqrt(2.0)/4)*length),BotRightX +(int)((sqrt(2.0)/4)*length),BotRightX};
        SideY = new int[] {TopRightY,TopRightY -(int)((sqrt(2.0)/4)*length),BotRightY -(int)((sqrt(2.0)/4)*length),BotRightY};  
    }
    
    public void draw(Graphics g)
    {      
        if(solid == true)
        {
            drawSolid(g);
        }
        else
        {
            drawTransparent(g);
        }
    }
    
    private void drawSolid(Graphics g)
    {
        g.setColor(Color.green);
        g.fillPolygon(FrontX,FrontY,4);
            
        g.setColor(Color.yellow);
        g.fillPolygon(TopX,TopY,4);
            
        g.setColor(Color.red);
        g.fillPolygon(SideX,SideY,4);
           
        for(int i=1; i<size; i++)
        {
            //front face
            FrontLines(g,i,Color.black);
            //top face
            TopLines(g,i,Color.black);
            //right face
            RightLines(g,i,Color.black);
        }
    }
    
    private void drawTransparent(Graphics g)
    {
        g.drawPolygon(FrontX,FrontY,4);
        g.drawPolygon(TopX,TopY,4);
        g.drawPolygon(SideX,SideY,4);
        g.setColor(Color.LIGHT_GRAY);
        drawDashedLine(g,BotLeftX,BotLeftY,BotLeftX + (int)((sqrt(2.0)/4)*length),BotLeftY - (int)((sqrt(2.0)/4)*length));
        drawDashedLine(g,BotLeftX + (int)((sqrt(2.0)/4)*length),BotLeftY - (int)((sqrt(2.0)/4)*length),TopLeftX + (int)((sqrt(2.0)/4)*length),TopLeftY - (int)((sqrt(2.0)/4)*length));
        drawDashedLine(g,BotLeftX + (int)((sqrt(2.0)/4)*length),BotLeftY - (int)((sqrt(2.0)/4)*length),BotRightX +(int)((sqrt(2.0)/4)*length),BotRightY -(int)((sqrt(2.0)/4)*length));
        
        for(int i=1; i<size; i++)
        {
            //front face
            FrontLines(g,i,Color.green);
            //top face
            TopLines(g,i,Color.MAGENTA);
            //right face
            RightLines(g,i,Color.red);
            //back face
            BackLines(g,i,Color.blue);
            //bottom face;
            BottomLines(g,i,Color.LIGHT_GRAY);
            //left face0
            LeftLines(g,i,Color.orange);       
        } 
    }
    public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2)
    {
        //creates a copy of the Graphics instance
        Graphics2D g2d = (Graphics2D) g.create();

        //set the stroke of the copy, not the original 
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{4}, 0);
        g2d.setStroke(dashed);
        g2d.drawLine(x1, y1, x2, y2);

        //gets rid of the copy
        g2d.dispose();
    }
    
    private void FrontLines(Graphics g, int i,Color color)
    {
        g.setColor(color);
        g.drawLine(TopLeftX+increment*i,TopLeftY,BotLeftX+increment*i,BotLeftY);
        g.drawLine(TopLeftX,TopLeftY+increment*i,TopRightX,TopRightY+increment*i);
    }
    
    private void TopLines(Graphics g, int i, Color color)
    {
        g.setColor(color);
        g.drawLine(TopLeftX + (int)(((sqrt(2.0)/4)*length)*i/size),TopLeftY - (int)(((sqrt(2.0)/4)*length)*i/size),TopRightX +(int)(((sqrt(2.0)/4)*length)*i/size),TopRightY -(int)(((sqrt(2.0)/4)*length)*i/size));
        g.drawLine(TopLeftX+increment*i,TopLeftY,TopLeftX + (int)((sqrt(2.0)/4)*length)+increment*i,TopLeftY - (int)((sqrt(2.0)/4)*length));
    }
    
    private void RightLines(Graphics g, int i, Color color)
    {
        g.setColor(color);
        g.drawLine(TopRightX,TopRightY+increment*i,TopRightX +(int)((sqrt(2.0)/4)*length),TopRightY -(int)((sqrt(2.0)/4)*length)+increment*i);
        g.drawLine(TopRightX +(int)(((sqrt(2.0)/4)*length)*i/size),TopRightY -(int)(((sqrt(2.0)/4)*length)*i/size),BotRightX +(int)(((sqrt(2.0)/4)*length)*i/size),BotRightY -(int)(((sqrt(2.0)/4)*length)*i/size));
    }
    
    private void BackLines(Graphics g, int i, Color color)
    {
        g.setColor(color);
        drawDashedLine(g,TopLeftX + (int)((sqrt(2.0)/4)*length)+increment*i,TopLeftY - (int)((sqrt(2.0)/4)*length),BotLeftX + (int)((sqrt(2.0)/4)*length)+increment*i,BotLeftY - (int)((sqrt(2.0)/4)*length));
        drawDashedLine(g,TopLeftX +(int)((sqrt(2.0)/4)*length),TopLeftY -(int)((sqrt(2.0)/4)*length)+increment*i,TopRightX +(int)((sqrt(2.0)/4)*length),TopRightY -(int)((sqrt(2.0)/4)*length)+increment*i);
    }
    
    private void BottomLines(Graphics g, int i, Color color)
    {
        g.setColor(color);
        drawDashedLine(g,BotLeftX + (int)(((sqrt(2.0)/4)*length)*i/size),BotLeftY - (int)(((sqrt(2.0)/4)*length)*i/size),BotRightX +(int)(((sqrt(2.0)/4)*length)*i/size),BotRightY -(int)(((sqrt(2.0)/4)*length)*i/size));
        drawDashedLine(g,BotLeftX+increment*i,BotLeftY,BotLeftX + (int)((sqrt(2.0)/4)*length)+increment*i,BotLeftY - (int)((sqrt(2.0)/4)*length));
    }
    
    private void LeftLines(Graphics g, int i, Color color)
    {
        g.setColor(color);
        drawDashedLine(g,TopLeftX + (int)(((sqrt(2.0)/4)*length)*i/size),TopLeftY - (int)(((sqrt(2.0)/4)*length)*i/size),BotLeftX + (int)(((sqrt(2.0)/4)*length)*i/size),BotLeftY - (int)(((sqrt(2.0)/4)*length)*i/size));
        drawDashedLine(g,TopLeftX,TopLeftY+increment*i,TopLeftX +(int)((sqrt(2.0)/4)*length),TopLeftY -(int)((sqrt(2.0)/4)*length)+increment*i);

    }
}
