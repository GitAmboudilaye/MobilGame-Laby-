package com.example.am2laby;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Laby extends View {
    //public  Context context;
    private boolean sizeChanged = false;
    public  Ball drawBall;
    private List<Wall> walls;
    private Paint paintWall;
    private Paint paintBall;
    private Paint paint;
    public  Random random;
    public  int lignlength;// la longueur d'un obstacle
    public int height;
    public int width;
    public List<Integer> coordonneesObst;

    public float x;
    public float y;


    public Laby(Context context) {
        super(context);
        coordonneesObst = new ArrayList<>();
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        paintWall = new Paint();
        paintWall.setColor(Color.GREEN);
        paintWall.setStrokeWidth(30);
        paintBall = new Paint();
        paintBall.setColor(Color.RED);
        //paint.setStrokeWidth(10);
        walls = new ArrayList<>();
        lignlength =150;
        addBall();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //initialiser le height et width de l'ecran
        //height = getHeight();
        //width = getWidth();
        canvas.drawCircle(drawBall.getX(), drawBall.getY(), drawBall.getRadius(), paintBall);
        for (Wall wall : walls) {
            canvas.drawLine(wall.getStartx(), wall.getStarty(), wall.getEndx(), wall.getEndy(), paintWall);
        }
        Log.d("msg", this.getWidth() + "");
    }
    public void addBall() {

        Random rand = new Random();
        drawBall = new Ball(500, 1000, 30.0f);
        invalidate();
    }
    public void addWall(Wall wall) {
        walls.add(wall);
    }
    public void createwalls(){
        if (!sizeChanged) {
            return;
        }
        //left wall
        Wall wallmurLeft = new Wall(0, 0, 0, height);
        addWall(wallmurLeft);
        //top wall
        Wall wallmurTop = new Wall(0, 0, width, 0);
        addWall(wallmurTop);
        //right wall
        Wall wallmurRight = new Wall(width, 0, width, height);//?????????????
        addWall(wallmurRight);
        //Button wall
        Wall wallmurButton = new Wall(0, height, width, height);
        addWall(wallmurButton);


   //construction des coordonnées de points et calcul de distance (AB =CD selon ma logique)


        for (int i = 0; i <10 ; i++){
            //ligne verticale; coordonnées de points: A(startx,starty) et B(endx,endy), AB = endy - starty et endx = startx
            //int startx = random.nextInt(getWidth());
            int startx = (int) (Math.random() * width);
            int endx = startx;
            //int starty = random.nextInt(getHeight());
            int starty = (int) (Math.random() * height);
            int endy = lignlength + starty;
            //Toast.makeText(getContext(), "" +startx, Toast.LENGTH_SHORT).show();

            //Wall wall2 = new Wall(500, 0, 500, 150);(mon exemple de reference)
            //Wall wallVer = new Wall(startx, starty, endx, endy);
            //addWall(wallVer);

            //ligne Horizontale; coordonnées de points: C(startx,starty) et D(endx,endy), CD = endx - startx et endy = starty

            int startyH = (int) (Math.random() * height);
            int endyH = startyH;

            int startxH = (int) (Math.random() * width);
            int endxH = lignlength + startxH;

            this.coordonneesObst.add(startx);
            this.coordonneesObst.add(starty);
            this.coordonneesObst.add(endx);
            this.coordonneesObst.add(endy);

            this.coordonneesObst.add(startxH);
            this.coordonneesObst.add(startyH);
            this.coordonneesObst.add(endxH);
            this.coordonneesObst.add(endyH);
            int compter = 0;
            for (int coord: coordonneesObst) {

                //distance entre les initiaux
                int dx = (coord -  startx);
                int dy = (coord - starty);
                Double dist = Math.sqrt((double) (dx*dx) + (double) (dy*dy));

                //distance entre les initiaux
                /*int dx = (coord -  startx);
                int dy = (coord - starty);
                Double dist = Math.sqrt((double) (dx*dx) + (double) (dy*dy));*/
                if(coord != startx && coord != endy && dist >100
                    && coord != startxH && coord != endxH && startx >60 && starty> 60
                    && endx <width-60 && endy <height && endxH <width-60 ){

                    //Vertical
                    Wall wallVer = new Wall(startx, starty, endx, endy);
                    addWall(wallVer);

                    //Horitontal
                    Wall wallHor = new Wall(startxH, startyH, endxH, endyH);

                    addWall(wallHor);
                    compter++;
                }
            }
            if (coordonneesObst.size() ==80){
                i = compter;
            }
        }

        //Wall wallH = new Wall(100, 100, 100, 200);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = h;
        width = w;
        sizeChanged = true;
        createwalls();
    }
}
