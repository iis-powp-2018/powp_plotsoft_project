package edu.iis.powp.command;

public class Coordinates {
	private int posX, posY;
	
	/**
	 * 	Create Coordinate object
	 * @param posX X coordinate
	 * @param posY Y coordinate
	 */
	public Coordinates(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	/**
	 *  returns X coordinate
	 * @return X coordinate
	 */
	public int getPosX() {
		return posX;
	}
	
	/**
	 *  returns Y coordinate
	 * @return Y coordinate
	 */
	public int getPosY() {
		return posY;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + posX;
		result = prime * result + posY;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (posX != other.posX)
			return false;
		if (posY != other.posY)
			return false;
		return true;
	}
	
	
	
	
}
