package eu.deyanix.tokenbucket.trafficgenerator;

import java.util.Random;

public class ExponentialTrafficGenerator extends TrafficGenerator {
	private final Random random = new Random();
	private final double lambda;

	public ExponentialTrafficGenerator(double lambda) {
		this.lambda = lambda;
	}

	public double next() {
		return Math.log(1-random.nextDouble()) / (-lambda);
	}
}
