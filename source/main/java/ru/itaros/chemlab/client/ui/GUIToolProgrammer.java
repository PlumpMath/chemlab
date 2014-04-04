package ru.itaros.chemlab.client.ui;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import ru.itaros.chemlab.ChemLab;
import ru.itaros.chemlab.network.packets.SetHOEMachineRecipePacket;
import ru.itaros.toolkit.hoe.machines.basic.io.minecraft.gui.elements.Tab;
import ru.itaros.toolkit.hoe.machines.basic.io.minecraft.recipes.Recipe;
import ru.itaros.toolkit.hoe.machines.basic.io.minecraft.recipes.RecipesCollection;
import ru.itaros.toolkit.hoe.machines.basic.io.minecraft.tileentity.MachineTileEntity;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GUIToolProgrammer extends GuiScreen {

	MachineTileEntity tile;
	public GUIToolProgrammer(MachineTileEntity te){
		tile=te;
	}
	
    protected int xSize = 187;
    protected int ySize = 198;
	
	
	public static final int CAPTIONCOLOR = 4210752;
	
	protected int x;
	protected int y;
	
	ResourceLocation background;
	ResourceLocation overlay;
	
	@Override
	public void initGui() {
		background = new ResourceLocation("chemlab","textures/gui/programmerui.png");
		
		overlay = new ResourceLocation("chemlab","textures/gui/programmerui_overlay.png");
		
		int taboffset = -17;
		int tabheight = 19 + 3;
		int i = 1;
		Tab temp;
		temp = new Tab(0+taboffset,0+(tabheight*i),190,2,19,20,193,53);
		infotab=temp;
		temp.setActive(true);
		activeTab=temp;
		tabs.add(temp);//Info
		i++;
		temp = new Tab(0+taboffset,0+(tabheight*i),190,2,19,20,208,53);
		recipetab=temp;
		tabs.add(temp);//Recipe
		i++;		
		temp = new Tab(0+taboffset,0+(tabheight*i),190,2,19,20,208,68);
		statstab=temp;
		tabs.add(temp);//Stats
		i++;	
		temp = new Tab(0+taboffset,0+(tabheight*i),190,2,19,20,193,68);
		securitytab=temp;
		tabs.add(temp);//Security
		i++;			
		
		super.initGui();
	}	
	ArrayList<Tab> tabs = new ArrayList<Tab>();
	
	
	private Tab infotab,recipetab,statstab,securitytab;
	
	
	
	
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		this.drawGuiContainerBackgroundLayer(par3, par1, par2);
		
		if(activeTab==recipetab){
			this.drawRecipes(0,0,0);
		}
		
		super.drawScreen(par1, par2, par3);
	}


	private int currentPage = 0;
	private void drawRecipes(int operation, int x2, int y2) {
		//opertation:
		//0 = DRAW
		//1 = CLICKSIGN
		
		RecipesCollection repcol = tile.getSuperIO().getRecipesCollection();
		//TODO: If there is no col - show "NO RECIPES AVAILABLE. THIS IS A BUG!"
		
		int recipesAmount = repcol.getRecipesAmount();
		int pages = recipesAmount/3;//3 is amount per page
		
		int rangeStart = currentPage * 3;
		int rangeEnd = rangeStart + 3;
		if(rangeEnd>recipesAmount){rangeEnd=recipesAmount;};
		
		int osx=171;
		int osy=44;
		
		int xi = 8;
		int yi = 18;
		int ystep = 45;
		
		//Drawing
		int i=-1;
		for(int xp = rangeStart; xp < rangeEnd; xp++){
			if(operation == 0){
				i++;
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				this.mc.renderEngine.bindTexture(overlay);
				this.drawTexturedModalRect(xi+x, yi+y+(ystep*i), 0, 0, osx, osy);
				GL11.glEnable(GL11.GL_LIGHTING);
				
				Recipe r = repcol.getRecipes()[xp];
				
				//TODO: This shit should be all precached
				//Drawing incomings
				int inc_offset=-1;
				for(Item item : r.getIncomingStricttypes()){
					inc_offset++;
					ItemStack stack_inc = new ItemStack(item);
					drawItemStack(stack_inc, x+xi+60-10-(inc_offset*(16+2)), y+yi+18+(ystep*i), null);
				}
				//Drawing outcomings
				int out_offset=-1;
				for(Item item : r.getOutcomingStricttypes()){
					out_offset++;
					ItemStack stack_out = new ItemStack(item);
					drawItemStack(stack_out, x+xi+97+10+(out_offset*(16+2)), y+yi+18+(ystep*i), null);
				}			
				
			}else if(operation==1){
				int ox = x2-x;
				int oy = y2-y;
				
				int initx=xi;
				int inity=yi+(ystep*i);
				
				
				if(ox>initx && ox<initx+osx){
					if(oy>inity && oy<inity+osy){
						//Clicked
						Recipe r = repcol.getRecipes()[xp];
						this.mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
						//tile.trySetRecipe(r);//Should be sended to server
						//Clicking is always done on a client, you know xD
						ChemLab.getInstance().SendPacketAsClientToServer(new SetHOEMachineRecipePacket(tile,r));
					}
				}
				
			}
		}
		//60/18
		
		
	}

	//Mojangcode
    private void drawItemStack(ItemStack stack, int x, int y, String stackSizeOverride)
    {
        GL11.glTranslatef(0.0F, 0.0F, 32.0F);
        this.zLevel = 200.0F;
        itemRender.zLevel = 200.0F;
        FontRenderer font = null;
        if (stack != null) font = stack.getItem().getFontRenderer(stack);
        if (font == null) font = fontRendererObj;
        itemRender.renderItemAndEffectIntoGUI(font, this.mc.getTextureManager(), stack, x, y);
        itemRender.renderItemOverlayIntoGUI(font, this.mc.getTextureManager(), stack, x, y - 0, stackSizeOverride);//0 or 8
        this.zLevel = 0.0F;
        itemRender.zLevel = 0.0F;
    }

	@Override
	protected void mouseMovedOrUp(int x2, int y2,
			int p_146286_3_) {
		
		//clickTabs(x2,y2,p_146286_3_,x,y);
		
		super.mouseMovedOrUp(x2, y2, p_146286_3_);
	}
	@Override
	protected void mouseClicked(int x2, int y2, int button) {

		clickTabs(x2,y2,button,x,y);
		drawRecipes(1,x2,y2);
		
		super.mouseClicked(x2, y2, button);
	}


	private Tab activeTab=null;
	
	private void clickTabs(int x2, int y2, int button,int x, int y) {
		for(Tab t:tabs){
			if(t.isIn(x2-x,y2-y)){
				this.mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
				if(activeTab!=null){activeTab.setActive(false);}
				t.setActive(true);
				activeTab=t;
			}
		}
	}
	private void drawTabs(int x,int y) {
		for(Tab t:tabs){
			t.draw(this,x,y);
		}
	}






	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(background);
		x = (width - xSize) / 2;
		y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		this.drawTabs(x,y);
		
		fontRendererObj.drawString("Hardware Programmer", x+8, y+6, CAPTIONCOLOR);//4210752
	}
	
}