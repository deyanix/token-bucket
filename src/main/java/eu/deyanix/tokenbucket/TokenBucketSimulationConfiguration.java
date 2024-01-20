package eu.deyanix.tokenbucket;

public class TokenBucketSimulationConfiguration {
	/**
	 * Bucket capacity [B]
	 */
	private long bucketCapacity = 105L;
	/**
	 * Bucket refilling rate [B/s]
	 */
	private double bucketRefillRate = 5d;
	/**
	 * Inbound packet rate [B/s]
	 */
	private double packetArrivalRate = 500d;
	/**
	 * Packet size [B]
	 */
	private long packetSize = 100L;
	/**
	 * Simulation time [s]
	 */
	private double simulationEndTime = 100d;

	public long getBucketCapacity() {
		return bucketCapacity;
	}

	public TokenBucketSimulationConfiguration setBucketCapacity(long bucketCapacity) {
		this.bucketCapacity = bucketCapacity;
		return this;
	}

	public double getBucketRefillRate() {
		return bucketRefillRate;
	}

	public TokenBucketSimulationConfiguration setBucketRefillRate(double bucketRefillRate) {
		this.bucketRefillRate = bucketRefillRate;
		return this;
	}

	public double getPacketArrivalRate() {
		return packetArrivalRate;
	}

	public TokenBucketSimulationConfiguration setPacketArrivalRate(double packetArrivalRate) {
		this.packetArrivalRate = packetArrivalRate;
		return this;
	}

	public double getEndTime() {
		return simulationEndTime;
	}

	public TokenBucketSimulationConfiguration setSimulationEndTime(double simulationEndTime) {
		this.simulationEndTime = simulationEndTime;
		return this;
	}

	public long getPacketSize() {
		return packetSize;
	}

	public TokenBucketSimulationConfiguration setPacketSize(long packetSize) {
		this.packetSize = packetSize;
		return this;
	}
}
