package co.q64.exgregilo.render;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

import org.lwjgl.opengl.GL11;

import co.q64.exgregilo.block.AdvancedSieve;
import co.q64.exgregilo.tile.AdvancedSieveTile;
import co.q64.exgregilo.tile.AdvancedSieveTile.SieveMode;
import exnihilo.blocks.models.ModelSieveContents;

public class AdvancedSieveRender extends TileEntitySpecialRenderer {
	private AdvancedSieveModel model;
	private AdvancedSieveMeshModel mesh;
	private ModelSieveContents contents;

	public AdvancedSieveRender(AdvancedSieveModel model, AdvancedSieveMeshModel mesh) {
		this.model = model;
		this.mesh = mesh;
		this.contents = new ModelSieveContents();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		renderTable(tileentity, x, y, z, f);
		renderMesh(tileentity, x, y, z, f);
		renderContents(tileentity, x, y, z, f);
	}

	private void renderTable(TileEntity tileentity, double x, double y, double z, float f) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glScalef(-1F, -1F, 1F);
		bindSieveTexture();
		model.simpleRender(0.0625F);
		GL11.glPopMatrix();
	}

	private void renderMesh(TileEntity tileentity, double x, double y, double z, float f) {
		AdvancedSieveTile sieve = (AdvancedSieveTile) tileentity;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.69F, (float) z + 0.5F);
		//GL11.glScalef(-1F, -1F, 1F);
		bindTexture(TextureMap.locationBlocksTexture);
		mesh.render(AdvancedSieve.meshIcon, sieve.getMesh());
		GL11.glPopMatrix();
	}

	private void renderContents(TileEntity tileentity, double x, double y, double z, float f) {
		AdvancedSieveTile sieve = (AdvancedSieveTile) tileentity;
		IIcon icon = null;
		switch (sieve.mode) {
		case FILLED:
			icon = sieve.content.getIcon(0, sieve.contentMeta);
			break;
		default:
			break;
		}
		if (sieve.mode != SieveMode.EMPTY) {
			bindTexture(TextureMap.locationBlocksTexture);
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x + 0.5F, (float) y + sieve.getAdjustedVolume(), (float) z + 0.5F);
			contents.renderTop(icon);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x + 0.5F, (float) y + 0.70f, (float) z + 0.5F);
			contents.renderBottom(icon);
			GL11.glPopMatrix();
		}
	}

	public void bindSieveTexture() {
		bindTexture(AdvancedSieveModel.TEXTURE);
	}
}
