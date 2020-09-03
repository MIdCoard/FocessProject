package com.focess.plugins;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import com.google.common.collect.Lists;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;

public class Util {

	public static void drawBoundingBox(final Location player_pos, final Location posA, final Location posB,
			final boolean smooth, final float width) {
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glTranslated(-player_pos.x, -player_pos.y, -player_pos.z);

		final Color c = new Color(255, 0, 0, 150);
		GL11.glColor4d(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
		GL11.glLineWidth(width);
		GL11.glDepthMask(false);
		final Tessellator tessellator = Tessellator.getInstance();
		final BufferBuilder bufferBuilder = tessellator.getBuffer();
		bufferBuilder.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
		final double dx = Math.abs(posA.x - posB.x);
		final double dy = Math.abs(posA.y - posB.y);
		final double dz = Math.abs(posA.z - posB.z);

		// AB
		bufferBuilder.pos(posA.x, posA.y, posA.z).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha())
				.endVertex(); // A
		bufferBuilder.pos(posA.x, posA.y, posA.z + dz).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha())
				.endVertex(); // B
		// BC
		bufferBuilder.pos(posA.x, posA.y, posA.z + dz).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha())
				.endVertex(); // B
		bufferBuilder.pos(posA.x + dx, posA.y, posA.z + dz).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha())
				.endVertex(); // C
		// CD
		bufferBuilder.pos(posA.x + dx, posA.y, posA.z + dz).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha())
				.endVertex(); // C
		bufferBuilder.pos(posA.x + dx, posA.y, posA.z).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha())
				.endVertex(); // D
		// DA
		bufferBuilder.pos(posA.x + dx, posA.y, posA.z).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha())
				.endVertex(); // D
		bufferBuilder.pos(posA.x, posA.y, posA.z).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha())
				.endVertex(); // A
		// EF
		bufferBuilder.pos(posA.x, posA.y + dy, posA.z).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha())
				.endVertex(); // E?
		bufferBuilder.pos(posA.x, posA.y + dy, posA.z + dz).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha())
				.endVertex(); // F
		// FG
		bufferBuilder.pos(posA.x, posA.y + dy, posA.z + dz).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha())
				.endVertex(); // F
		bufferBuilder.pos(posA.x + dx, posA.y + dy, posA.z + dz)
				.color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex(); // G
		// GH
		bufferBuilder.pos(posA.x + dx, posA.y + dy, posA.z + dz)
				.color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex(); // G
		bufferBuilder.pos(posA.x + dx, posA.y + dy, posA.z).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha())
				.endVertex(); // H
		// HE
		bufferBuilder.pos(posA.x + dx, posA.y + dy, posA.z).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha())
				.endVertex(); // H
		bufferBuilder.pos(posA.x, posA.y + dy, posA.z).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha())
				.endVertex(); // E
		// AE
		bufferBuilder.pos(posA.x, posA.y, posA.z).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha())
				.endVertex(); // A
		bufferBuilder.pos(posA.x, posA.y + dy, posA.z).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha())
				.endVertex(); // E
		// BF
		bufferBuilder.pos(posA.x, posA.y, posA.z + dz).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha())
				.endVertex(); // B
		bufferBuilder.pos(posA.x, posA.y + dy, posA.z + dz).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha())
				.endVertex(); // F
		// CG
		bufferBuilder.pos(posA.x + dx, posA.y, posA.z + dz).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha())
				.endVertex(); // C
		bufferBuilder.pos(posA.x + dx, posA.y + dy, posA.z + dz)
				.color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex(); // G
		// DH
		bufferBuilder.pos(posA.x + dx, posA.y, posA.z).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha())
				.endVertex(); // D
		bufferBuilder.pos(posA.x + dx, posA.y + dy, posA.z).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha())
				.endVertex(); // H

		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glPopAttrib();
	}

	public static List<Location> splitLocation(final Location from, final Location to) {
		final List<Location> locations = Lists.newArrayList();
		final double cut = 25;
		final double px = (from.getX() - to.getX()) / cut;
		final double py = (from.getY() - to.getY()) / cut;
		final double pz = (from.getZ() - to.getZ()) / cut;
		for (int i = 0; i <= cut; i++)
			locations.add(
					new Location((int) (to.getX() + i * px), (int) (to.getY() + i * py), (int) (to.getZ() + i * pz)));
		return locations;
	}

	public static String toString(final BlockPos pos) {
		final StringBuilder sb = new StringBuilder();
		sb.append("At X: " + pos.getX()).append(" Y: " + pos.getY()).append(" Z: " + pos.getZ());
		return sb.toString();
	}

	public static String toString(final Location pos) {
		final StringBuilder sb = new StringBuilder();
		sb.append("At X: " + pos.getX()).append(" Y: " + pos.getY()).append(" Z: " + pos.getZ());
		return sb.toString();
	}

}
