package ua.kharkov.repairagency.db.entity;

import java.io.Serializable;


public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	} 

}
