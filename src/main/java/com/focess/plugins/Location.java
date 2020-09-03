package com.focess.plugins;

import net.minecraft.util.math.BlockPos;

public class Location {

	public final int x, y, z;

	public Location(final int x, final int y, final int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Location(BlockPos blockPosition) {
		this.x = blockPosition.getX();
		this.y = blockPosition.getY();
		this.z = blockPosition.getZ();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
	
	

}
