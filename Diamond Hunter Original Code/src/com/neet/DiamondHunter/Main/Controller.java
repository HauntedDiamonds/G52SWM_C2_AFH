package com.neet.DiamondHunter.Main;
// controller class for map editor javafx
// the class draw the whole map on canvas when initialize


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import com.neet.DiamondHunter.TileMap.FXTile;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

	@FXML
	private Button Update;

    @FXML
    private TextField BoatLocationX;

    @FXML
    private TextField BoatLocationY;

    @FXML
    private TextField AxeLocationX;

    @FXML
    private TextField AxeLocationY;

    @FXML
    private Canvas canvas;
    
    // tile sets
    private Image tileset = new Image("/Tilesets/testtileset.gif");
    // to store sub image (newly formed or cropped)
    PixelReader reader = tileset.getPixelReader();
    private double numTilesAcross;
	private FXTile[][] tiles;
	
	// map
	private int[][] map;
	private int tileSize = 16;
	private int numRows;
	private int numCols;
	private int ax;
	private int ay;
	private int bx;
	private int by;
	
	// player and items
	private Image diamond = new Image("/Sprites/diamond.gif");
	private Image player = new Image("/Sprites/playersprites.gif");
	private Image axe = new Image("/Sprites/items.gif");
	private Image boat = new Image("/Sprites/items.gif");
	// to store sub image (newly formed or cropped)
	PixelReader reader2 = diamond.getPixelReader();
	PixelReader reader3 = player.getPixelReader();
	PixelReader reader4 = axe.getPixelReader();
	PixelReader reader5 = boat.getPixelReader();
	
	// obtain coordinate from user
	@FXML
    void UpdateLocation(ActionEvent event) 
	{
		int a1 = Integer.parseInt(AxeLocationX.getText());
		int a2 = Integer.parseInt(AxeLocationY.getText());
		int b1 = Integer.parseInt(BoatLocationX.getText());
		int b2 = Integer.parseInt(BoatLocationY.getText());
    }
	 
	// draw map on canvas with items 
    @FXML
    public void initialize( )
    {	  	
    	// get the graphics context of the canvas
    	GraphicsContext gc = canvas.getGraphicsContext2D();
    	
    	/**canvas.setOnMouseClicked(new EventHandler<Event>(){

    		@Override
    		public void handle(Event event) {
    			GraphicsContext meow = canvas.getGraphicsContext2D();
    	        System.out.println(meow);
    			
    		}
        	
        });
        */
    	
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
		
		// loading the map files, map and boat locations
		try{
			InputStream in = getClass().getResourceAsStream("/Maps/testmap.map");
			BufferedReader br = new BufferedReader(
						new InputStreamReader(in)
					);
			
			File f = new File("Resources/Maps/axeboatloc.map");
			Scanner s = new Scanner(f);
			
			ax = s.nextInt();
			ay = s.nextInt();
			bx = s.nextInt();
			by = s.nextInt();
			
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
		
		// drawing the map on canvas			
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
		
		// drawing the diamonds
		WritableImage subimage2;
		subimage2 = new WritableImage(reader2, 0, 0, tileSize, tileSize);
		
		gc.drawImage(subimage2, 20*tileSize, 20*tileSize);
		gc.drawImage(subimage2, 36*tileSize, 12*tileSize);
		gc.drawImage(subimage2, 4*tileSize, 28*tileSize);
		gc.drawImage(subimage2, 34*tileSize, 4*tileSize);
		gc.drawImage(subimage2, 19*tileSize, 28*tileSize);
		gc.drawImage(subimage2, 26*tileSize, 35*tileSize);
		gc.drawImage(subimage2, 36*tileSize, 38*tileSize);
		gc.drawImage(subimage2, 28*tileSize, 27*tileSize);
		gc.drawImage(subimage2, 30*tileSize, 20*tileSize);
		gc.drawImage(subimage2, 25*tileSize, 14*tileSize);
		gc.drawImage(subimage2, 21*tileSize, 4*tileSize);
		gc.drawImage(subimage2, 14*tileSize, 9*tileSize);
		gc.drawImage(subimage2, 3*tileSize, 4*tileSize);
		gc.drawImage(subimage2, 14*tileSize, 20*tileSize);
		gc.drawImage(subimage2, 20*tileSize, 13*tileSize);
		
		// draw player
		WritableImage subimage3;
		subimage3 = new WritableImage(reader3, 0, 0, tileSize, tileSize);
		gc.drawImage(subimage3, 17*tileSize, 17*tileSize);
		
		// draw axe
		WritableImage subimage4;
		subimage4 = new WritableImage(reader4, tileSize, tileSize, tileSize, tileSize);
		gc.drawImage(subimage4,ay*tileSize, ax*tileSize);
		
		// draw boat
		WritableImage subimage5;
		subimage5 = new WritableImage(reader5, 0, tileSize, tileSize, tileSize);	
		gc.drawImage(subimage5, by*tileSize, bx*tileSize);
		
		
    }
    
    
}
