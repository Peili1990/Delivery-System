package utils;

import java.io.Serializable;

public class Merchandise implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private int quantity;
	
	private String volume;
	
	private String weight;
	
	private String note;

	public Merchandise(String name, int quantity, String volume, String weight,
			String note) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.volume = volume;
		this.weight = weight;
		this.note = note;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getVolume() {
		return volume;
	}

	public String getWeight() {
		return weight;
	}

	public String getNote() {
		return note;
	}

	@Override
	public String toString() {
		return name + ", quantity: " + quantity
				+ ", volume:" + volume + ", weight:" + weight + "\nnote: "
				+ note;
	}
	
}
