import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.neet.DiamondHunter.Main.GamePanel;
import com.neet.DiamondHunter.TileMap.Tile;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.image.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private Button submit;
    
    @FXML
    private Canvas canvas;
    
    @FXML
    private TextField testing;
    
    // tile sets
    private Image tileset = new Image("/Tilesets/testtileset.gif");
    PixelReader reader = tileset.getPixelReader();
    private double numTilesAcross;
	private FXTile[][] tiles;
	
	// map
	private int[][] map;
	private int tileSize = 16;
	private int numRows;
	private int numCols;
	
	// player and items
	private Image diamond = new Image("/Sprites/diamond.gif");
	private Image player = new Image("/Sprites/playersprites.gif");
	private Image axe = new Image("/Sprites/items.gif");
	private Image boat = new Image("/Sprites/items.gif");
	PixelReader reader2 = diamond.getPixelReader();
	PixelReader reader3 = player.getPixelReader();
	PixelReader reader4 = axe.getPixelReader();
	PixelReader reader5 = boat.getPixelReader();
	

    @FXML
    public void initialize( )
    {	  	
    	// get the graphics context of the canvas
    	GraphicsContext gc = canvas.getGraphicsContext2D();
    	
    	// loading the map tiles
    	numTilesAcross = tileset.getWidth() / tileSize;  	
    	tiles = new FXTile[2][(int) numTilesAcross];	
    	WritableImage subimage;
    	
		for(int col = 0; col < numTilesAcross; col++) {
			
			subimage = new WritableImage(reader, col*tileSize, 0, tileSize, tileSize);
			tiles[0][col] = new FXTile(subimage, FXTile.NORMAL);
			
			subimage = new WritableImage(reader, col*tileSize, tileSize, tileSize, tileSize);
			tiles[1][col] = new FXTile(subimage, FXTile.BLOCKED);
		}
		
		// load the map files
		try{
			InputStream in = getClass().getResourceAsStream("/Maps/testmap.map");
			BufferedReader br = new BufferedReader(
						new InputStreamReader(in)
					);
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			
			map = new int[numRows][numCols];
			
			String delims = "\\s+";
			for(int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for(int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		// drawing the map			
		for(int row = 0; row < numRows; row++) {
			
			for(int col = 0; col < numCols; col++) {
					
				int rc = map[row][col];
				int r = (int) (rc / numTilesAcross);
				int c = (int) (rc % numTilesAcross);
				
				gc.drawImage(
					tiles[r][c].getImage(),
					col * tileSize,
					row * tileSize
				);
				
			}
		}
		
		// drawing the diamonds, not placed correctly still identifying bug in locations
		WritableImage subimage2;
		subimage2 = new WritableImage(reader2, 0, 0, tileSize, tileSize);
		
		gc.drawImage(subimage2, 20*tileSize, 20*tileSize);
		gc.drawImage(subimage2, 12*tileSize, 36*tileSize);
		gc.drawImage(subimage2, 28*tileSize, 4*tileSize);
		gc.drawImage(subimage2, 4*tileSize, 34*tileSize);
		gc.drawImage(subimage2, 28*tileSize, 19*tileSize);
		gc.drawImage(subimage2, 35*tileSize, 26*tileSize);
		gc.drawImage(subimage2, 38*tileSize, 36*tileSize);
		gc.drawImage(subimage2, 27*tileSize, 28*tileSize);
		gc.drawImage(subimage2, 20*tileSize, 30*tileSize);
		gc.drawImage(subimage2, 14*tileSize, 25*tileSize);
		gc.drawImage(subimage2, 4*tileSize, 21*tileSize);
		gc.drawImage(subimage2, 9*tileSize, 14*tileSize);
		gc.drawImage(subimage2, 4*tileSize, 3*tileSize);
		gc.drawImage(subimage2, 20*tileSize, 14*tileSize);
		gc.drawImage(subimage2, 13*tileSize, 20*tileSize);
		
		// draw player
		WritableImage subimage3;
		subimage3 = new WritableImage(reader3, 0, 0, tileSize, tileSize);
		
		gc.drawImage(subimage3, 17*tileSize, 17*tileSize);
		
		// draw axe
		WritableImage subimage4;
		subimage4 = new WritableImage(reader4, tileSize, tileSize, tileSize, tileSize);
		
		gc.drawImage(subimage4, 26*tileSize, 37*tileSize);
		
		// draw boat
		WritableImage subimage5;
		subimage5 = new WritableImage(reader5, 0, tileSize, tileSize, tileSize);
		
		gc.drawImage(subimage5, 12*tileSize, 14*tileSize);
    }
}
