package farn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import net.minecraft.client.Minecraft;
import net.minecraft.src.*;

public class FarnGameSettings {
	protected Minecraft mc = ModLoader.getMinecraftInstance();
	private File optionsFile;

	public boolean skinFix = true;
	public boolean TexturePackButton = true;
	public boolean F2screenshot = true;
	public boolean betacraftSoundFix = true;

	public String skinUrl = "http://resourceproxy.pymcl.net/skinapi.php?user=";
	public String capeUrl = "http://resourceproxy.pymcl.net/capeapi.php?user=";	
	public boolean appendSkinPNG = true;
	public boolean appendCapeUsername = true;	
	public boolean appendCapePNG = true;

	public FarnGameSettings() {
		this.optionsFile = new File(Minecraft.getMinecraftDir(), "farnOptions.txt");
	}

	public void loadOptions() {
		try {
			if(!this.optionsFile.exists()) {
				try {
					PrintWriter printWriter1 = new PrintWriter(new FileWriter(this.optionsFile));
					printWriter1.println(" ");
					printWriter1.println("#Main Option");
					printWriter1.println("skinFix=" + this.skinFix);
					printWriter1.println("TexturePackButton=" + this.appendCapeUsername);
					printWriter1.println("F2_Screenshot=" + this.appendCapePNG);
					printWriter1.println("betacraftSoundFix=" + this.betacraftSoundFix);
					printWriter1.println(" ");
					printWriter1.println("#SkinFix Option");
					printWriter1.println("skinUrl=" + this.skinUrl);
					printWriter1.println("capeUrl=" + this.capeUrl);
					printWriter1.println("appendSkinPNG=" + this.appendSkinPNG);
					printWriter1.println("appendCapeUsername=" + this.appendCapeUsername);
					printWriter1.println("appendCapePNG=" + this.appendCapePNG);
					printWriter1.close();
				} catch (Exception exception3) {
					exception3.printStackTrace();
				}
			}

			BufferedReader bufferedReader1 = new BufferedReader(new FileReader(this.optionsFile));
			String string2 = "";

			while((string2 = bufferedReader1.readLine()) != null) {
				String[] string3 = string2.split("=");
				this.appendSkinPNG = string3[0] == "skinFix" ? string3[1] == "true" : this.skinFix;
				this.appendCapeUsername = string3[0] == "TexturePackButton" ? string3[1] == "true" : this.TexturePackButton;
				this.appendCapePNG = string3[0] == "F2_Screenshot" ? string3[1] == "true" : this.F2screenshot;
				this.appendCapePNG = string3[0] == "betacraftSoundFix" ? string3[1] == "true" : this.betacraftSoundFix;

				this.skinUrl = string3[0] == "skinUrl" ? string3[1] : this.skinUrl; 
				this.capeUrl = string3[0] == "capeUrl" ? string3[1] : this.capeUrl;
				this.appendSkinPNG = string3[0] == "appendSkinPNG" ? string3[1] == "true" : this.appendSkinPNG;
				this.appendCapeUsername = string3[0] == "appendCapeUsername" ? string3[1] == "true" : this.appendCapeUsername;
				this.appendCapePNG = string3[0] == "appendCapePNG" ? string3[1] == "true" : this.appendCapePNG;
			}

			bufferedReader1.close();
		} catch (Exception exception5) {
			exception5.printStackTrace();
		}

	}
}