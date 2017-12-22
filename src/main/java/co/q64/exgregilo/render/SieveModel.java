package co.q64.exgregilo.render;

import javax.inject.Singleton;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@Singleton
public class SieveModel extends ModelBase {
	private ModelRenderer leg1;
	private ModelRenderer leg2;
	private ModelRenderer leg3;
	private ModelRenderer leg4;
	private ModelRenderer boxSide1;
	private ModelRenderer boxSide2;
	private ModelRenderer boxSide3;
	private ModelRenderer boxSide4;

	public SieveModel() {
		textureWidth = 128;
		textureHeight = 128;
		leg1 = new ModelRenderer(this, 0, 0);
		leg1.addBox(0F, 0F, 0F, 1, 11, 1);
		leg1.setRotationPoint(-7F, 13F, -7F);
		leg1.setTextureSize(128, 128);
		leg1.mirror = true;
		setRotation(leg1, 0F, 0F, 0F);
		leg2 = new ModelRenderer(this, 0, 0);
		leg2.addBox(0F, 0F, 0F, 1, 11, 1);
		leg2.setRotationPoint(-7F, 13F, 6F);
		leg2.setTextureSize(128, 128);
		leg2.mirror = true;
		setRotation(leg2, 0F, 0F, 0F);
		leg3 = new ModelRenderer(this, 0, 0);
		leg3.addBox(0F, 0F, 0F, 1, 11, 1);
		leg3.setRotationPoint(6F, 13F, 6F);
		leg3.setTextureSize(128, 128);
		leg3.mirror = true;
		setRotation(leg3, 0F, 0F, 0F);
		leg4 = new ModelRenderer(this, 0, 0);
		leg4.addBox(0F, 0F, 0F, 1, 11, 1);
		leg4.setRotationPoint(6F, 13F, -7F);
		leg4.setTextureSize(128, 128);
		leg4.mirror = true;
		setRotation(leg4, 0F, 0F, 0F);
		boxSide1 = new ModelRenderer(this, 6, 0);
		boxSide1.addBox(0F, 0F, 0F, 16, 6, 1);
		boxSide1.setRotationPoint(-8F, 8F, -8F);
		boxSide1.setTextureSize(128, 128);
		boxSide1.mirror = true;
		setRotation(boxSide1, 0F, 0F, 0F);
		boxSide2 = new ModelRenderer(this, 6, 8);
		boxSide2.addBox(0F, 0F, 0F, 16, 6, 1);
		boxSide2.setRotationPoint(-8F, 8F, 7F);
		boxSide2.setTextureSize(128, 128);
		boxSide2.mirror = true;
		setRotation(boxSide2, 0F, 0F, 0F);
		boxSide3 = new ModelRenderer(this, 6, 16);
		boxSide3.addBox(0F, 0F, 0F, 1, 6, 14);
		boxSide3.setRotationPoint(7F, 8F, -7F);
		boxSide3.setTextureSize(128, 128);
		boxSide3.mirror = true;
		setRotation(boxSide3, 0F, 0F, 0F);
		boxSide4 = new ModelRenderer(this, 6, 37);
		boxSide4.addBox(0F, 0F, 0F, 1, 6, 14);
		boxSide4.setRotationPoint(-8F, 8F, -7F);
		boxSide4.setTextureSize(128, 128);
		boxSide4.mirror = true;
		setRotation(boxSide4, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		boxSide1.render(f5);
		boxSide2.render(f5);
		boxSide3.render(f5);
		boxSide4.render(f5);
	}

	public void simpleRender(float scale) {
		leg1.render(scale);
		leg2.render(scale);
		leg3.render(scale);
		leg4.render(scale);
		boxSide1.render(scale);
		boxSide2.render(scale);
		boxSide3.render(scale);
		boxSide4.render(scale);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}