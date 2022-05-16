package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Vector3f position = new Vector3f(400,5,400);
	private float pitch = 10;
	private float yaw = 0;
	private float roll;
	
	public Camera(){}
	
	public void printCoords()
	{
		System.out.println("Coordinates: " + this.position);
	}
	
	public void move()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			position.z-=0.2f;
			printCoords();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.z+=0.2f;
			printCoords();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			position.x+=0.2f;
			printCoords();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			position.x-=0.2f;
			printCoords();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
			yaw-=1;
			printCoords();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			yaw+=1;
			printCoords();
		}
	}

	public Vector3f getPosition()
	{
		return position;
	}

	public float getPitch()
	{
		return pitch;
	}

	public float getYaw()
	{
		return yaw;
	}

	public float getRoll()
	{
		return roll;
	}
	
	

}
