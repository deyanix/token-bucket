package eu.deyanix.tokenbucket;

import eu.deyanix.tokenbucket.randomizer.ExponentialRandomizer;

public class TokenBucketSimulation {
	private final TokenBucket bucket;
	private final TokenBucketSimulationConfiguration configuration;
	private final ExponentialRandomizer randomizer;
	private double now = 0;
	private double nextArrival = 0, nextTime = 0;

	private long droppedPackets = 0, transmittedPackets = 0;

	public TokenBucketSimulation(TokenBucketSimulationConfiguration configuration) {
		this.bucket = new TokenBucket(configuration.getBucketCapacity());
		this.configuration = configuration;
		this.randomizer = new ExponentialRandomizer(configuration.getPacketArrivalRate());
	}

	public void run() {
		while (now < configuration.getEndTime()) {
			long newTokens = (long) (nextTime * configuration.getBucketRefillRate());
			bucket.refill(newTokens);
			now = nextArrival;

			nextTime = randomizer.next();
			nextArrival += nextTime;

			if (bucket.consume(configuration.getPacketSize())) {
				transmittedPackets++;
			} else {
				droppedPackets++;
			}
		}
	}

	public long getDroppedPackets() {
		return droppedPackets;
	}

	public long getTransmittedPackets() {
		return transmittedPackets;
	}

	public long getArrivalPackets() {
		return transmittedPackets + droppedPackets;
	}

	public double getTransmittedPacketsRatio() {
		return ((double) getTransmittedPackets()) / ((double) getArrivalPackets());
	}
}
