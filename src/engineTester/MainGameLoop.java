package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grass"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("mud"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("grassFlowers"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));
		
		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));
		
		
		
		
		RawModel model = OBJLoader.loadObjModel("tree", loader);
		
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("tree")));
		
 
		
		ModelTexture fernTextureAtlas = new ModelTexture(loader.loadTexture("fern"));
		fernTextureAtlas.setNumberOfRows(2);
		TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("fern", loader),fernTextureAtlas);
		
		fern.getTexture().setHasTransparency(true);				//przy renderowaniu na przykład trawy czy czegokolwiek z przeroczystym tłem musisz tego użyć
		fern.getTexture().setUseFakeLightnig(true);				// i tego tez, 
		
		Terrain terrain = new Terrain(0,-1,loader,texturePack, blendMap, "heightmap");

		List<Entity> entities = new ArrayList<Entity>();
		Random random = new Random();
		for(int i=0;i<500;i++){
			//entities.add(new Entity(staticModel, new Vector3f(random.nextFloat()*800 - 400,0,random.nextFloat() * -600),0,0,0,3));
			float x = 0, y = 0, z = 0, rx = 0, ry = 0, rz = 0, scale = 1;
			x = random.nextFloat() * 800 ;
            z = random.nextFloat() * 800 - 800 ;
            y = terrain.getHeightOfTerrain(x, z);
            rx = 4 * random.nextFloat() - 2;
            ry = random.nextFloat() * 360;
            rz = 4 * random.nextFloat() - 2;
            scale = random.nextFloat() * 1f + 4f;
            entities.add(new Entity(staticModel, new Vector3f(x, y, z), rx, ry, rz, scale));
          
            
		}
		
		
		
		
		
		for(int i = 0; i < 500; i++) {
			if(i%2 == 0) {
				float x = random.nextFloat() * 800 ;
				float z = random.nextFloat() * 800 - 800 ;
				float y = terrain.getHeightOfTerrain(x, z);
				
				
				float a = random.nextFloat() * 800 ;
				float b = random.nextFloat() * 800 - 800 ;
				float c = terrain.getHeightOfTerrain(x, z);
				
	          entities.add(new Entity(fern, random.nextInt(4),  new Vector3f(x, y, z), 0, random.nextFloat() * 360, 0 , 0.9f));
	           

				
			}
		}
		
		
		
		
		Light light = new Light(new Vector3f(20000,20000,2000),new Vector3f(1,1,1));
		
		//Original
		//Terrain terrain = new Terrain(0,0,loader,new ModelTexture(loader.loadTexture("grass")));
		//Terrain terrain2 = new Terrain(1,0,loader,new ModelTexture(loader.loadTexture("grass")));
		//
		//Terrain terrain = new Terrain(0,-1,loader,texturePack, blendMap, "heightmap");
		//Terrain terrain2 = new Terrain(-1,-1,loader,texturePack, blendMap, "heightmap");
		
		
		MasterRenderer renderer = new MasterRenderer(loader);
		
		RawModel playerModel = OBJLoader.loadObjModel("person", loader);
		TexturedModel person = new TexturedModel(playerModel, new ModelTexture(loader.loadTexture("white")));
		
		RawModel bunnyModel = OBJLoader.loadObjModel("bunny", loader);
		TexturedModel bunny = new TexturedModel(bunnyModel, new ModelTexture(loader.loadTexture("fur")));
		entities.add(new Entity(bunny, new Vector3f(400, 1, -600), 0,0,0,1));
		
		Player player = new Player(person, new Vector3f(400, 0 , -400),0,0,0,1);
		Camera camera = new Camera(player);	
		
		while(!Display.isCloseRequested()){
			camera.move();
			player.move(terrain);
			
			renderer.processEntity(player);
			renderer.processTerrain(terrain);
			//renderer.processTerrain(terrain2);
			for(Entity entity:entities){
				renderer.processEntity(entity);
			}
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}

		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
