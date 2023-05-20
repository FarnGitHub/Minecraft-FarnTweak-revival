package farn;

import java.lang.reflect.Field;
import java.util.Set;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.*;
import net.minecraft.src.*;
import farn.GuiIngameMenuProxy;

public class FarnMain {
	public String skinUrl = "http://resourceproxy.pymcl.net/skinapi.php?user=";
	public String capeUrl = "http://resourceproxy.pymcl.net/capeapi.php?user=";	
	public boolean appendSkinPNG = true;
	public boolean appendCapeUsername = true;	
	public boolean appendCapePNG = true;
	public boolean isTakingScreenshot = false;
	public static final FarnMain instance = new FarnMain();
	
	public final String Version() {
		return "v1.0";
	}

	public void OSDHook(Minecraft minecraft, boolean ingui) {
		if(!ingui) {
			updateSkinAndCape(minecraft, minecraft.thePlayer);
			if (minecraft.theWorld instanceof WorldClient) {
				try {
					for (Entity entity : (Set<Entity>) field_20914_EField.get((WorldClient) minecraft.theWorld)) {
						if (entity instanceof EntityOtherPlayerMP) {
							updateSkinAndCape(minecraft, (EntityPlayer) entity);
						} 
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
			this.screenshotListener();
		} else {
			if(minecraft.currentScreen instanceof GuiIngameMenu && !(minecraft.currentScreen instanceof GuiIngameMenuProxy)) {
				minecraft.displayGuiScreen(new GuiIngameMenuProxy());
			}
		}
	}
	
	private final void updateSkinAndCape(Minecraft mc, EntityPlayer entityplayer) {
		if (entityplayer.field_20047_bv != this.skinUrl + entityplayer.field_20047_bv + (this.appendSkinPNG ? ".png" : "") || entityplayer.field_20067_q != this.capeUrl + (this.appendCapeUsername ? mc.session.playerName + (this.appendCapePNG ? ".png" : "") : "") || entityplayer.skinUrl != entityplayer.field_20067_q) {
			entityplayer.field_20047_bv = skinUrl + mc.session.playerName + (this.appendSkinPNG ? ".png" : "");
			mc.renderGlobal.obtainEntitySkin(entityplayer);
			entityplayer.field_20067_q = capeUrl + (appendCapeUsername ? mc.session.playerName + (this.appendCapePNG ? ".png" : "") : "");
			entityplayer.skinUrl = entityplayer.field_20067_q;
			mc.renderEngine.obtainImageData(entityplayer.skinUrl, new ImageBufferDownload());
		}
	}

	private void screenshotListener() {
		if(Keyboard.isKeyDown(Keyboard.KEY_F2) && !Keyboard.isKeyDown(Keyboard.KEY_F1)) {
			if(!this.isTakingScreenshot && !FarnTweakMinecraftAccesor.isTakingScreenshot) {
				ModLoader.getMinecraftInstance().ingameGUI.addChatMessage(ScreenShotHelper.saveScreenshot(Minecraft.getMinecraftDir(), ModLoader.getMinecraftInstance().displayWidth, ModLoader.getMinecraftInstance().displayHeight));
				FarnTweakMinecraftAccesor.setScreenShotStatus(true);
				this.isTakingScreenshot = true;
			}
		} else {
			FarnTweakMinecraftAccesor.setScreenShotStatus(false);
			this.isTakingScreenshot = false;
		}
	}

	private final Field getPrivateField(Class<?> target, String names[]) {
		for (Field field : target.getDeclaredFields())
			for (String name : names)
				if (field.getName() == name) {
					field.setAccessible(true);
					return field;
				}
		return null;
	}
	
	private final Field field_20914_EField = getPrivateField(WorldClient.class, new String[] {"E"});
}