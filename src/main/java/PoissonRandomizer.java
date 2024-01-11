import org.apache.commons.math3.distribution.PoissonDistribution;

import java.util.Map;
import java.util.TreeMap;

public class PoissonRandomizer {
	private final double mean;
	private final PoissonDistribution poisson;
	private final Map<Integer, Integer> distribution = new TreeMap<Integer, Integer>();

	public PoissonRandomizer(double mean) {
		this.mean = mean;
		this.poisson = new PoissonDistribution(mean);
	}

	public Map<Integer, Integer> getDistribution() {
		return distribution;
	}

	public void showDistribution() {
		for (Map.Entry<Integer, Integer> entry : getDistribution().entrySet()) {
			double v = 1d / entry.getKey();
			System.out.println(String.valueOf(v).replace('.', ',') + "\t" + entry.getValue());
		}
	}

	public int next() {
		int sample = poisson.sample();

		if (distribution.containsKey(sample)) {
			distribution.put(sample, distribution.get(sample) + 1);
		} else {
			distribution.put(sample, 1);
		}

		return sample;
	}

//	private int _next() {
//		int k = 0;
//		double a = random.nextDouble();
//		double p = Math.exp(-mean);
//
//		while (a > p) {
//			k++;
//			a = a - p;
//			p = p * mean / k;
//		}
//		return k;
//	}
}
