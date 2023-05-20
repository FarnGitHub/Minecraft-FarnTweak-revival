package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class mod_FarnTweak2 extends BaseMod {
	
	public final String Version() {
		return "v1.0";
	}

	public void OSDHook(Minecraft minecraft, boolean ingui) {
		farn.FarnMain.instance.OSDHook(minecraft, ingui);
	}
}