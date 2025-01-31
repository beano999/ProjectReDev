package net.roadkill.redev.client.model.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.ResourceLocation;
import net.roadkill.redev.ReDev;

import java.util.function.Function;

public class HoglinHideHeadModel<T extends HumanoidRenderState> extends HumanoidModel<T>
{
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "hoglin_head"), "main");

	public HoglinHideHeadModel(ModelPart root)
    {   super(root);
    }

	public static LayerDefinition createArmorLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1F))
                .texOffs(32, -8).addBox(0F, -13.64F, -1.18F, 0.0F, 13.0F, 11.0F, new CubeDeformation(0f, 1.5f, 1.5F))
                .texOffs(24, 0).addBox(4F, -12F, -4F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.25F, 0.75f, 0.25f))
                .texOffs(24, 0).mirror().addBox(-6F, -12F, -4F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.25F, 0.75f, 0.25f)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition DummyHat = Head.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition DummyBody = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition DummyRightArm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition DummyLeftArm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition DummyRightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition DummyLeftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
	}
}