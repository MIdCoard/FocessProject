package net.minecraft.client.main;

import java.io.File;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.authlib.properties.PropertyMap;
import com.mojang.authlib.properties.PropertyMap.Serializer;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import net.minecraft.client.Minecraft;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.Session;

public class Main {
	/**
	 * Returns whether a string is either null or empty.
	 */
	private static boolean isNullOrEmpty(final String str) {
		return str != null && !str.isEmpty();
	}

	public static void main(final String[] p_main_0_) {
		final OptionParser optionparser = new OptionParser();
		optionparser.allowsUnrecognizedOptions();
		optionparser.accepts("demo");
		optionparser.accepts("fullscreen");
		optionparser.accepts("checkGlErrors");
		final OptionSpec<String> optionspec = optionparser.accepts("server").withRequiredArg();
		final OptionSpec<Integer> optionspec1 = optionparser.accepts("port").withRequiredArg()
				.<Integer>ofType(Integer.class).defaultsTo(Integer.valueOf(25565));
		final OptionSpec<File> optionspec2 = optionparser.accepts("gameDir").withRequiredArg().<File>ofType(File.class)
				.defaultsTo(new File("."));
		final OptionSpec<File> optionspec3 = optionparser.accepts("assetsDir").withRequiredArg()
				.<File>ofType(File.class);
		final OptionSpec<File> optionspec4 = optionparser.accepts("resourcePackDir").withRequiredArg()
				.<File>ofType(File.class);
		final OptionSpec<String> optionspec5 = optionparser.accepts("proxyHost").withRequiredArg();
		final OptionSpec<Integer> optionspec6 = optionparser.accepts("proxyPort").withRequiredArg().defaultsTo("8080")
				.<Integer>ofType(Integer.class);
		final OptionSpec<String> optionspec7 = optionparser.accepts("proxyUser").withRequiredArg();
		final OptionSpec<String> optionspec8 = optionparser.accepts("proxyPass").withRequiredArg();
		final OptionSpec<String> optionspec9 = optionparser.accepts("username").withRequiredArg()
				.defaultsTo("Player" + Minecraft.getSystemTime() % 1000L);
		final OptionSpec<String> optionspec10 = optionparser.accepts("uuid").withRequiredArg();
		final OptionSpec<String> optionspec11 = optionparser.accepts("accessToken").withRequiredArg().required();
		final OptionSpec<String> optionspec12 = optionparser.accepts("version").withRequiredArg().required();
		final OptionSpec<Integer> optionspec13 = optionparser.accepts("width").withRequiredArg()
				.<Integer>ofType(Integer.class).defaultsTo(Integer.valueOf(854));
		final OptionSpec<Integer> optionspec14 = optionparser.accepts("height").withRequiredArg()
				.<Integer>ofType(Integer.class).defaultsTo(Integer.valueOf(480));
		final OptionSpec<String> optionspec15 = optionparser.accepts("userProperties").withRequiredArg()
				.defaultsTo("{}");
		final OptionSpec<String> optionspec16 = optionparser.accepts("profileProperties").withRequiredArg()
				.defaultsTo("{}");
		final OptionSpec<String> optionspec17 = optionparser.accepts("assetIndex").withRequiredArg();
		final OptionSpec<String> optionspec18 = optionparser.accepts("userType").withRequiredArg().defaultsTo("legacy");
		final OptionSpec<String> optionspec19 = optionparser.accepts("versionType").withRequiredArg()
				.defaultsTo("release");
		final OptionSpec<String> optionspec20 = optionparser.nonOptions();
		final OptionSet optionset = optionparser.parse(p_main_0_);
		final List<String> list = optionset.valuesOf(optionspec20);

		if (!list.isEmpty())
			System.out.println("Completely ignored arguments: " + list);

		final String s = optionset.valueOf(optionspec5);
		Proxy proxy = Proxy.NO_PROXY;

		if (s != null)
			try {
				proxy = new Proxy(Type.SOCKS, new InetSocketAddress(s, optionset.valueOf(optionspec6).intValue()));
			} catch (final Exception var48) {

			}

		final String s1 = optionset.valueOf(optionspec7);
		final String s2 = optionset.valueOf(optionspec8);

		if (!proxy.equals(Proxy.NO_PROXY) && Main.isNullOrEmpty(s1) && Main.isNullOrEmpty(s2))
			Authenticator.setDefault(new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(s1, s2.toCharArray());
				}
			});

		final int i = optionset.valueOf(optionspec13).intValue();
		final int j = optionset.valueOf(optionspec14).intValue();
		final boolean flag = optionset.has("fullscreen");
		final boolean flag1 = optionset.has("checkGlErrors");
		final boolean flag2 = optionset.has("demo");
		final String s3 = optionset.valueOf(optionspec12);
		final Gson gson = new GsonBuilder().registerTypeAdapter(PropertyMap.class, new Serializer()).create();
		final PropertyMap propertymap = JsonUtils.gsonDeserialize(gson, optionset.valueOf(optionspec15),
				PropertyMap.class);
		final PropertyMap propertymap1 = JsonUtils.gsonDeserialize(gson, optionset.valueOf(optionspec16),
				PropertyMap.class);
		final String s4 = optionset.valueOf(optionspec19);
		final File file1 = optionset.valueOf(optionspec2);
		final File file2 = optionset.has(optionspec3) ? (File) optionset.valueOf(optionspec3)
				: new File(file1, "assets/");
		final File file3 = optionset.has(optionspec4) ? (File) optionset.valueOf(optionspec4)
				: new File(file1, "resourcepacks/");
		final String s5 = optionset.has(optionspec10) ? (String) optionspec10.value(optionset)
				: (String) optionspec9.value(optionset);
		final String s6 = optionset.has(optionspec17) ? (String) optionspec17.value(optionset) : null;
		final String s7 = optionset.valueOf(optionspec);
		final Integer integer = optionset.valueOf(optionspec1);
		final Session session = new Session(optionspec9.value(optionset), s5, optionspec11.value(optionset),
				optionspec18.value(optionset));
		final GameConfiguration gameconfiguration = new GameConfiguration(
				new GameConfiguration.UserInformation(session, propertymap, propertymap1, proxy),
				new GameConfiguration.DisplayInformation(i, j, flag, flag1),
				new GameConfiguration.FolderInformation(file1, file3, file2, s6),
				new GameConfiguration.GameInformation(flag2, s3, s4),
				new GameConfiguration.ServerInformation(s7, integer.intValue()));
		Runtime.getRuntime().addShutdownHook(new Thread("Client Shutdown Thread") {
			@Override
			public void run() {
				Minecraft.stopIntegratedServer();
			}
		});
		Thread.currentThread().setName("Client thread");
		new Minecraft(gameconfiguration).run();
	}
}
