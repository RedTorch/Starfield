import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Starfield extends PApplet {

Particle[] leDots = new Particle[200];
int numchar=1;
public void setup()
{	
	size(displayWidth-80,displayHeight-120);
    for(int i = 0; i < leDots.length; i++)
	{
		leDots[i] = new OddballParticle();
		if(i%2==0)
		{
			leDots[i] = new NormalParticle();
		}
	}
	leDots[3] = new BigOne();
	noCursor();
	frameRate(120);
}
public void draw()
{
	noStroke();
	fill(0,0,0,50);
	rect(0,0,displayWidth-80,displayHeight-120);
	for(int i = 0; i < leDots.length; i++)
	{
		leDots[i].move();
		leDots[i].show();
	}
	noFill();
	stroke(50,100,255);
	rect(mouseX-15,mouseY-15,30,30);
	line(mouseX,mouseY+30,mouseX,mouseY+10);
	line(mouseX,mouseY-30,mouseX,mouseY-10);
	line(mouseX+30,mouseY,mouseX+10,mouseY);
	line(mouseX-30,mouseY,mouseX-10,mouseY);
}
interface Particle
{
	public void move();
	public void show();
}
class NormalParticle implements Particle
{
	double x,y;
	double speed;
	int charnum;
	double angle;
	double xSpeed;
	double ySpeed;
	NormalParticle()
	{
		x = (Math.random()*(displayWidth-80));
		y = (Math.random()*(displayHeight-120));
		//x = ((displayWidth-80)/2);
		//y = ((displayHeight-120)/2);
		//speed = (int)(Math.random()*4+1);
		speed = 1;
		charnum = (int)((Math.random()*numchar)+64);
		angle = Math.random()*2*Math.PI;
		xSpeed = (Math.cos(angle) * speed);
		ySpeed = (Math.sin(angle) * speed);
	}
	public void move()
	{
		int adist = (int)((displayWidth-80)/2);
		int bdist = (int)((displayHeight-120)/2);
		int cdist = (int)(x);
		int ddist = (int)(y);
		speed = (PApplet.parseInt(dist(adist,bdist,cdist,ddist))/10)+1;
		xSpeed = Math.cos(angle) * speed;
		ySpeed = Math.sin(angle) * speed;
		x = x + xSpeed;
		y = y + ySpeed;
		if((dist(adist,bdist,cdist,ddist)>(displayWidth-60))||(dist(adist,bdist,cdist,ddist)>(displayHeight-100)))
		{
			x = (int)((displayWidth-80)/2);
		    y = (int)((displayHeight-120)/2);
		    angle = Math.random()*2*Math.PI;
		}
	}
	public void show()
	{
		stroke(100);
		noFill();
		//ellipse((int)x,(int)y,(int)speed,(int)speed);
		//line((int)((displayWidth-80)/2),(int)((displayHeight-120)/2),(int)x,(int)y);
		//point((int)x,(int)y);
		ellipse((int)x,(int)y,(int)(speed/3),(int)(speed/3));
	}
}
class OddballParticle implements Particle
{
	double x,y;
	double speed;
	int charnum;
	double angle;
	double xSpeed;
	double ySpeed;
	int inum;
	OddballParticle()
	{
		x = (Math.random()*(displayWidth-80));
		y = (Math.random()*(displayHeight-120));
		//x = ((displayWidth-80)/2);
		//y = ((displayHeight-120)/2);
		//speed = (int)(Math.random()*4+1);
		speed = 1;
		charnum = (int)((Math.random()*numchar)+64);
		angle = Math.random()*2*Math.PI;
		xSpeed = (Math.cos(angle) * speed);
		ySpeed = (Math.sin(angle) * speed);
		inum = 2;
	}
	public void move()
	{
		int adist = (int)((displayWidth-80)/2);
		int bdist = (int)((displayHeight-120)/2);
		int cdist = (int)(x);
		int ddist = (int)(y);
		speed = (PApplet.parseInt(dist(adist,bdist,cdist,ddist))/10)+1;
		xSpeed = Math.cos(angle) * speed;
		ySpeed = Math.sin(angle) * speed;
		x = x + xSpeed;
		y = y + ySpeed;
		if((dist(adist,bdist,cdist,ddist)>(displayWidth-60))||(dist(adist,bdist,cdist,ddist)>(displayHeight-100)))
		{
			x = (int)((displayWidth-80)/inum);
		    y = (int)((displayHeight-120)/inum);
		    angle = Math.random()*2*Math.PI;
		}
	}
	
	public void show()
	{
		stroke(200);
		noFill();
		//ellipse((int)x,(int)y,(int)speed,(int)speed);
		//line((int)((displayWidth-80)/2),(int)((displayHeight-120)/2),(int)x,(int)y);
		//point((int)x,(int)y);
		ellipse((int)x,(int)y,(int)(speed/2),(int)(speed/2));
	}
}
class BigOne extends OddballParticle
	{
		BigOne()
		{
			float inum = 1.3f;
		}
	}
public void keyPressed()
{
	numchar++;
	System.out.println(numchar);
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Starfield" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
