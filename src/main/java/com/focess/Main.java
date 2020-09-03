package com.focess;

public class Main {
	public static void main(String[] p_main_0_) {
		if (p_main_0_ == null || p_main_0_.length == 0) {
			System.out.println("Focess Client by MidCoard");
			net.minecraft.client.main.Main.main(new String[] { "--username", "MidCoard", "--accessToken",
					"1ffc531945b83d6dbd0911448e19adf6", "--version", "1.12.2" });
		} else
			net.minecraft.client.main.Main.main(p_main_0_);
	}
}
