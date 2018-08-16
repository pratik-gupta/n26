package com.n26.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Pojo class to map the statistics properties.
 * 
 * @author Pratik
 *
 */
public class Statistics implements Serializable {

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private BigDecimal sum;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private BigDecimal avg;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private BigDecimal max;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private BigDecimal min;
	private long count;

	public BigDecimal getSum() {
		return sum;
	}

	public Statistics setSum(BigDecimal sum) {
		this.sum = sum;
		return this;
	}

	public BigDecimal getAvg() {
		return avg;
	}

	public Statistics setAvg(BigDecimal avg) {
		this.avg = avg;
		return this;
	}

	public BigDecimal getMax() {
		return max;
	}

	public Statistics setMax(BigDecimal max) {
		this.max = max;
		return this;
	}

	public BigDecimal getMin() {
		return min;
	}

	public Statistics setMin(BigDecimal min) {
		this.min = min;
		return this;
	}

	public long getCount() {
		return count;
	}

	public Statistics setCount(long count) {
		this.count = count;
		return this;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
