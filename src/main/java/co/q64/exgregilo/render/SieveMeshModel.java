package co.q64.exgregilo.render;

import gregtech.api.items.GT_MetaGenerated_Tool;

import javax.inject.Singleton;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import org.lwjgl.opengl.GL11;

import co.q64.exgregilo.link.gregtech.tools.MetaGeneratedTools;

@Singleton
public class SieveMeshModel {
	public void render(IIcon icon) {
		render(icon, 1f, 1f, 1f);
	}

	public void render(IIcon icon, ItemStack mesh) {
		if (mesh != null && mesh.getItem() instanceof MetaGeneratedTools) {
			short[] colors = GT_MetaGenerated_Tool.getPrimaryMaterial(mesh).getRGBA();
			render(icon, colors[0] / 255f, colors[1] / 255f, colors[2] / 255f);
		}
	}

	private void render(IIcon icon, float r, float g, float b) {
		Tessellator tessellator = Tessellator.instance;
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);

		double length = 1.0D;
		double width = 1.0D;
		double x = 0 - width / 2;
		double y = 0;
		double z = 0 - length / 2;

		double minU = (double) icon.getMinU();
		double maxU = (double) icon.getMaxU();
		double minV = (double) icon.getMinV();
		double maxV = (double) icon.getMaxV();

		tessellator.startDrawingQuads();
		tessellator.setColorRGBA_F(r, g, b, 1.0f);
		tessellator.addVertexWithUV(x + width, y, z + length, minU, minV);
		tessellator.addVertexWithUV(x + width, y, z, minU, maxV);
		tessellator.addVertexWithUV(x, y, z, maxU, maxV);
		tessellator.addVertexWithUV(x, y, z + length, maxU, minV);
		tessellator.draw();

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_CULL_FACE);
	}
}