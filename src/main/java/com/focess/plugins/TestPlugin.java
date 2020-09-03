package com.focess.plugins;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.focess.api.Focess;
import com.focess.api.command.Command;
import com.focess.api.command.CommandSender;
import com.focess.api.event.ChunkListener;
import com.focess.api.network.PacketListener;
import com.focess.api.render.RenderListener;
import com.focess.api.render.RenderListener.RenderTask;
import com.focess.api.task.Task;
import com.focess.core.Plugin;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.world.WorldServer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityBeaconRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityBeacon.BeamSegment;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class TestPlugin extends Plugin {
	
	public static boolean c = false;

	private Map<World, List<Location>> locations = Maps.newConcurrentMap();

	@Override
	public String getName() {
		return "Test";
	}

	@Override
	public void onEnable() {
		
		Command.registerCommand(new Command("boom", "boom boom boom", 4, Lists.newArrayList(),"error!") {

			@Override
			public List<String> tabComplete(CommandSender sender, String[] args) {
				return null;
			}

			@Override
			public void init() {
				
				this.addExecutor(0, (sender,args)->{
					Focess.getNetworkManager().sendClientPacket(new CPacketPlayerTryUseItemOnBlock(Focess.getPlayer().getPosition().down(), EnumFacing.UP, EnumHand.MAIN_HAND, 0, 0, 0));
					RenderListener.addRenderTask(new RenderTask(40) {
						int x = 0;
						int y = 0;
						int z = 0;
						@Override
						public void render() {
					        Tessellator tessellator = Tessellator.getInstance();
					        BufferBuilder bufferbuilder = tessellator.getBuffer();
					        GlStateManager.disableTexture2D();
					        GlStateManager.disableLighting();
					        GlStateManager.enableBlend();
					        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
					        double[] adouble = new double[8];
					        double[] adouble1 = new double[8];
					        double d0 = 0.0D;
					        double d1 = 0.0D;

					        Random r = new Random();
					        long seed = r.nextLong();
					        Random random = new Random(seed);

					        for (int i = 7; i >= 0; --i)
					        {
					            adouble[i] = d0;
					            adouble1[i] = d1;
					            d0 += (double)(random.nextInt(11) - 5);
					            d1 += (double)(random.nextInt(11) - 5);
					        }

					        for (int k1 = 0; k1 < 4; ++k1)
					        {
					            Random random1 = new Random(seed);

					            for (int j = 0; j < 3; ++j)
					            {
					                int k = 7;
					                int l = 0;

					                if (j > 0)
					                {
					                    k = 7 - j;
					                }

					                if (j > 0)
					                {
					                    l = k - 2;
					                }

					                double d2 = adouble[k] - d0;
					                double d3 = adouble1[k] - d1;

					                for (int i1 = k; i1 >= l; --i1)
					                {
					                    double d4 = d2;
					                    double d5 = d3;

					                    if (j == 0)
					                    {
					                        d2 += (double)(random1.nextInt(11) - 5);
					                        d3 += (double)(random1.nextInt(11) - 5);
					                    }
					                    else
					                    {
					                        d2 += (double)(random1.nextInt(31) - 15);
					                        d3 += (double)(random1.nextInt(31) - 15);
					                    }

					                    bufferbuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);
					                    float f = 0.5F;
					                    float f1 = 0.45F;
					                    float f2 = 0.45F;
					                    float f3 = 0.5F;
					                    double d6 = 0.1D + (double)k1 * 0.2D;

					                    if (j == 0)
					                    {
					                        d6 *= (double)i1 * 0.1D + 1.0D;
					                    }

					                    double d7 = 0.1D + (double)k1 * 0.2D;

					                    if (j == 0)
					                    {
					                        d7 *= (double)(i1 - 1) * 0.1D + 1.0D;
					                    }

					                    for (int j1 = 0; j1 < 5; ++j1)
					                    {
					                        double d8 = x + 0.5D - d6;
					                        double d9 = z + 0.5D - d6;

					                        if (j1 == 1 || j1 == 2)
					                        {
					                            d8 += d6 * 2.0D;
					                        }

					                        if (j1 == 2 || j1 == 3)
					                        {
					                            d9 += d6 * 2.0D;
					                        }

					                        double d10 = x + 0.5D - d7;
					                        double d11 = z + 0.5D - d7;

					                        if (j1 == 1 || j1 == 2)
					                        {
					                            d10 += d7 * 2.0D;
					                        }

					                        if (j1 == 2 || j1 == 3)
					                        {
					                            d11 += d7 * 2.0D;
					                        }
					                        bufferbuilder.pos(d10 + d2, y + (double)(i1 * 16), d11 + d3).color(0.45F, 0.45F, 0.5F, 0.3F).endVertex();
					                        bufferbuilder.pos(d8 + d4, y + (double)((i1 + 1) * 16), d9 + d5).color(0.45F, 0.45F, 0.5F, 0.3F).endVertex();
					                    }

					                    tessellator.draw();
					                }
					            }
					        }

					        GlStateManager.disableBlend();
					        GlStateManager.enableLighting();
					        GlStateManager.enableTexture2D();
						}});
				});
			}});
		
		Focess.getRenderManager().addRenderListener(new RenderListener(this) {

			@Override
			public void render() {
				if (Focess.getPlayer() != null) {
					EntityPlayerMP player = Focess.getPlayer();
					for (Location loc : locations.getOrDefault(Focess.getWorld(), Lists.newCopyOnWriteArrayList()))
						if (player.getPosition().getDistance(loc.getX(), loc.getY(), loc.getZ()) < 70) {
							TileEntitySpecialRenderer<TileEntityBeacon> t = TileEntityRendererDispatcher.instance
									.getSpecialRendererByClass(TileEntityBeacon.class);
							TileEntityBeaconRenderer renderer = (TileEntityBeaconRenderer) t;
							renderer.renderBeacon(loc.getX() - TileEntityRendererDispatcher.staticPlayerX,
									loc.getY() - TileEntityRendererDispatcher.staticPlayerY,
									loc.getZ() - TileEntityRendererDispatcher.staticPlayerZ,
									Focess.getMinecraft().timer.field_194147_b,1,
									Lists.<BeamSegment>newArrayList(new BeamSegment(new float[] { 0f, 0f, 0f }, 184)),
									Focess.getWorld().getTotalWorldTime());
							TileEntityBeaconRenderer.renderBeamSegment(loc.getX() - TileEntityRendererDispatcher.staticPlayerX,
									loc.getY() - TileEntityRendererDispatcher.staticPlayerY,
									loc.getZ() - TileEntityRendererDispatcher.staticPlayerZ, Focess.getMinecraft().timer.field_194147_b,1,Focess.getWorld().getTotalWorldTime(), 0, 184, new float[] {1f,1f,1f}, 0.2, 0.25);
						}
				}
			}
		});
		Focess.getNetworkManager().addPacketListener(new PacketListener<SPacketBlockChange>(this) {

			@Override
			public boolean execute(SPacketBlockChange inPacket) {
				if (inPacket.getBlockState().getBlock().equals(Blocks.DIAMOND_ORE)) {
					List<Location> temp = locations.getOrDefault(Focess.getPlayer().world,
							Lists.newCopyOnWriteArrayList());
					temp.add(new Location(inPacket.getBlockPosition()));
					locations.put(Focess.getPlayer().world, temp);
				} else
					locations.getOrDefault(Focess.getPlayer().world, Lists.newCopyOnWriteArrayList())
							.remove(new Location(inPacket.getBlockPosition()));
				return true;
			}
		});

			
		Focess.getNetworkManager().addPacketListener(new PacketListener<CPacketPlayer>(this) {
			@Override
			public boolean execute(CPacketPlayer inPacket) {
				if (Focess.getPlayer() != null)
				Focess.getNetworkManager().sendClientPacket(new CPacketPlayerTryUseItemOnBlock(Focess.getPlayer().getPosition().down(), EnumFacing.UP, EnumHand.MAIN_HAND, 0, 0, 0));
				return true;
			}});
		

			
		ChunkListener.addChunkListener(new ChunkListener(this) {

			@Override
			public void execute(Chunk chunk) {
				for (int x = 0; x < 16; x++)
					for (int z = 0; z < 16; z++)
						for (int y = 0; y < 255; y++) {
							IBlockState state = chunk.getBlockState(x, y, z);
							if (state.getBlock().equals(Blocks.DIAMOND_ORE)) {
								List<Location> temp = locations.getOrDefault(Focess.getPlayer().world,
										Lists.newCopyOnWriteArrayList());
								temp.add(new Location(chunk.getChunkCoordIntPair().chunkXPos * 16 + x, y,
										chunk.getChunkCoordIntPair().chunkZPos * 16 + z));
								locations.put(Focess.getPlayer().world, temp);
							}
						}
			}
		});
	}

}
