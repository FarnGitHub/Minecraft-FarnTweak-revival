package net.minecraft.client;

import net.minecraft.src.ModLoader;

public class FarnTweakMinecraftAccesor {
	public static boolean isTakingScreenshot = ModLoader.getMinecraftInstance().isTakingScreenshot;

	public static void setScreenShotStatus(boolean status) {
		ModLoader.getMinecraftInstance().isTakingScreenshot = status;
	}
}