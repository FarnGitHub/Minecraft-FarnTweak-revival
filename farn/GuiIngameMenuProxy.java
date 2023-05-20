package farn;

import net.minecraft.src.*;

public class GuiIngameMenuProxy extends GuiIngameMenu {
	private int updateCounter2 = 0;
	private int updateCounter = 0;

	public void initGui() {
		super.initGui();
		this.controlList.add(new GuiButton(100, this.width / 2 - 100, this.height / 4 + 96 + 24, StringTranslate.func_20162_a().func_20163_a("menu.mods")));
	}

	protected void actionPerformed(GuiButton guiButton1) {
		super.actionPerformed(guiButton1);
		if(guiButton1.id == 100) {
			this.mc.displayGuiScreen(new GuiTexturePacks(this));
		}
	}
}