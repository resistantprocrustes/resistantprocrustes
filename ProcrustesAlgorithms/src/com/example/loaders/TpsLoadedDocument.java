package com.example.loaders;

import java.util.ArrayList;

public class TpsLoadedDocument implements ILoadedDocument {

	ArrayList<PCEntity> _entities = new ArrayList<PCEntity>();
	@Override
	public ArrayList<PCEntity> getEntitiesList() {
		return _entities;
	}

	public void addEntity(PCEntity pcEntity) {
		_entities.add(pcEntity);
	}
	
	public void addLandmarkToLast(Landmark landmark){
		_entities.get(_entities.size() -1).loadLandmark(landmark);
	}

}
