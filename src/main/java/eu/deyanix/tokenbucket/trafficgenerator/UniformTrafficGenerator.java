package eu.deyanix.tokenbucket.trafficgenerator;

public class UniformTrafficGenerator extends TrafficGenerator {
	private final double lambda;

	public UniformTrafficGenerator(double lambda) {
		this.lambda = lambda;
	}

	@Override
	public double next() {
		return 1/lambda;
	}
}
