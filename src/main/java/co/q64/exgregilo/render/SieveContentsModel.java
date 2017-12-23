package co.q64.exgregilo.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;

import org.lwjgl.opengl.GL11;

public class SieveContentsModel {
	public void renderTop(IIcon icon) {
		Tessellator tessellator = Tessellator.instance;

		GL11.glDisable(GL11.GL_LIGHTING);
		//GL11.glDisable(GL11.GL_CULL_FACE);

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
		tessellator.setColorRGBA_F(1.0f, 1.0f, 1.0f, 1.0f);

		tessellator.addVertexWithUV(x + width, y, z + length, minU, minV);
		tessellator.addVertexWithUV(x + width, y, z, minU, maxV);
		tessellator.addVertexWithUV(x, y, z, maxU, maxV);
		tessellator.addVertexWithUV(x, y, z + length, maxU, minV);

		tessellator.draw();

		GL11.glEnable(GL11.GL_LIGHTING);
		//GL11.glEnable(GL11.GL_CULL_FACE);
	}

	public void renderBottom(IIcon icon) {
		Tessellator tessellator = Tessellator.instance;

		GL11.glDisable(GL11.GL_LIGHTING);
		//GL11.glDisable(GL11.GL_CULL_FACE);

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
		tessellator.setColorRGBA_F(.7f, .7f, .7f, 1.0f);

		tessellator.addVertexWithUV(x, y, z + length, maxU, minV);
		tessellator.addVertexWithUV(x, y, z, maxU, maxV);
		tessellator.addVertexWithUV(x + width, y, z, minU, maxV);
		tessellator.addVertexWithUV(x + width, y, z + length, minU, minV);

		tessellator.draw();

		GL11.glEnable(GL11.GL_LIGHTING);
	}
}
