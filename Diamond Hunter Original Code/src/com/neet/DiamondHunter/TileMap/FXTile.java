package com.neet.DiamondHunter.TileMap;
// similar class to tile class
// special to store tileset for image type
// differ from tile class tostore buffered image

import javafx.scene.image.Image;

public class FXTile {

	private Image image;
	private int type;
	
	// tile types
	public static final int NORMAL = 0;
	public static final int BLOCKED = 1;
	
	public FXTile(Image image, int type) {
		this.image = image;
		this.type = type;
	}
	
	public Image getImage() { return image; }
	public int getType() { return type; }

}
