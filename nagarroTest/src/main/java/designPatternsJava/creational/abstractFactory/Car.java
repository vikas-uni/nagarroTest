package designPatternsJava.creational.abstractFactory;

import designPatternsJava.creational.factory.CarType;

public abstract class Car {

	public Car(CarType model, Location location) {
		this.model = model;
		this.location = location;
	}

	protected abstract void construct();

	private CarType model = null;
	private Location location = null;

	// getters and setters

	@Override
	public String toString() {
		return "Model- " + model + " built in " + location;
	}
}
