package co.q64.exgregilo.render;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SieveParticle extends EntityFX {

	public SieveParticle(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, IIcon icon) {
		super(par1World, par2, par4, par6, par8, par10, par12);
		this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
		this.setParticleIcon(icon);
		this.setSize(0.02F, 0.02F);
		this.particleScale = 0.07f + (this.rand.nextFloat() * 0.1F);
		this.motionX *= 0.019999999552965164D;
		this.motionY *= -0.4;//0.019999999552965164D;
		this.motionZ *= 0.019999999552965164D;
		this.particleMaxAge = (int) (20.0D / (Math.random() * 0.2D + 0.2D));
	}

	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.99D;
		this.motionY *= 0.99D;
		this.motionZ *= 0.99D;

		this.nextTextureIndexX();

		if (this.particleMaxAge-- <= 0) {
			this.setDead();
		}
	}

	public int getFXLayer() {
		return 1;
	}

	public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
		float f6 = ((float) this.particleTextureIndexX + this.particleTextureJitterX / 4.0F) / 16.0F;
		float f7 = f6 + 0.015609375F;
		float f8 = ((float) this.particleTextureIndexY + this.particleTextureJitterY / 4.0F) / 16.0F;
		float f9 = f8 + 0.015609375F;
		float f10 = 0.1F * this.particleScale;

		if (this.particleIcon != null) {
			f6 = this.particleIcon.getInterpolatedU((double) (this.particleTextureJitterX / 4.0F * 16.0F));
			f7 = this.particleIcon.getInterpolatedU((double) ((this.particleTextureJitterX + 1.0F) / 4.0F * 16.0F));
			f8 = this.particleIcon.getInterpolatedV((double) (this.particleTextureJitterY / 4.0F * 16.0F));
			f9 = this.particleIcon.getInterpolatedV((double) ((this.particleTextureJitterY + 1.0F) / 4.0F * 16.0F));
		}

		float f11 = (float) (this.prevPosX + (this.posX - this.prevPosX) * (double) par2 - interpPosX);
		float f12 = (float) (this.prevPosY + (this.posY - this.prevPosY) * (double) par2 - interpPosY);
		float f13 = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * (double) par2 - interpPosZ);
		float f14 = 1.0F;
		par1Tessellator.setColorOpaque_F(f14 * this.particleRed, f14 * this.particleGreen, f14 * this.particleBlue);
		par1Tessellator.addVertexWithUV((double) (f11 - par3 * f10 - par6 * f10), (double) (f12 - par4 * f10), (double) (f13 - par5 * f10 - par7 * f10), (double) f6, (double) f9);
		par1Tessellator.addVertexWithUV((double) (f11 - par3 * f10 + par6 * f10), (double) (f12 + par4 * f10), (double) (f13 - par5 * f10 + par7 * f10), (double) f6, (double) f8);
		par1Tessellator.addVertexWithUV((double) (f11 + par3 * f10 + par6 * f10), (double) (f12 + par4 * f10), (double) (f13 + par5 * f10 + par7 * f10), (double) f7, (double) f8);
		par1Tessellator.addVertexWithUV((double) (f11 + par3 * f10 - par6 * f10), (double) (f12 - par4 * f10), (double) (f13 + par5 * f10 - par7 * f10), (double) f7, (double) f9);
	}
}