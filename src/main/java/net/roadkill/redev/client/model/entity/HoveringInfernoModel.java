package net.roadkill.redev.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.client.renderer.render_sate.HoveringInfernoRenderState;
import net.roadkill.redev.common.entity.HoveringInfernoEntity;
import net.roadkill.redev.util.RDMath;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class HoveringInfernoModel<S extends HoveringInfernoRenderState> extends EntityModel<S>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(ReDev.MOD_ID, "hovering_inferno"), "main");
	private static final Map<HoveringInfernoEntity, List<ModelPart>> SHIELD_ANIMATIONS = new WeakHashMap<>();

	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart shieldSpin1;
	private final ModelPart smallShieldSpin1;
	private final ModelPart shieldSpin2;
	private final ModelPart smallShieldSpin2;
	private final ModelPart shieldSpin3;
	private final ModelPart smallShieldSpin3;
	private final ModelPart shieldSpin4;
	private final ModelPart smallShieldSpin4;

	public HoveringInfernoModel(ModelPart root)
	{
        super(root);
        this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.shieldSpin1 = root.getChild("shieldSpin1");
		this.smallShieldSpin1 = root.getChild("smallShieldSpin1");
		this.shieldSpin2 = root.getChild("shieldSpin2");
		this.smallShieldSpin2 = root.getChild("smallShieldSpin2");
		this.shieldSpin3 = root.getChild("shieldSpin3");
		this.smallShieldSpin3 = root.getChild("smallShieldSpin3");
		this.shieldSpin4 = root.getChild("shieldSpin4");
		this.smallShieldSpin4 = root.getChild("smallShieldSpin4");
	}

	public static LayerDefinition createBodyLayer()
	{	MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(32, 16).addBox(-4.0F, -5.0F, -4.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.75F)), PartPose.offset(0.0F, -17.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 44).addBox(-2.0F, -22.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(48, 34).addBox(-2.0F, -22.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition shieldSpin1 = partdefinition.addOrReplaceChild("shieldSpin1", CubeListBuilder.create(), PartPose.offset(0.0F, -11.0F, 0.0F));

		PartDefinition shield1 = shieldSpin1.addOrReplaceChild("shield1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, 0.0F, -6.0F, 2.0F, 18.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 30).mirror().addBox(-1.0F, 18.0F, -4.0F, 2.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(8.0F, -1.0F, 0.0F));

		PartDefinition smallShieldSpin1 = partdefinition.addOrReplaceChild("smallShieldSpin1", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition smallShield1 = smallShieldSpin1.addOrReplaceChild("smallShield1", CubeListBuilder.create().texOffs(42, 46).mirror().addBox(-4.5F, 0.0F, -1.0F, 9.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, -8.0F));

		PartDefinition shieldSpin2 = partdefinition.addOrReplaceChild("shieldSpin2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -11.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition shield2 = shieldSpin2.addOrReplaceChild("shield2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, 0.0F, -6.0F, 2.0F, 18.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 30).mirror().addBox(-1.0F, 18.0F, -4.0F, 2.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(8.0F, -1.0F, 0.0F));

		PartDefinition smallShieldSpin2 = partdefinition.addOrReplaceChild("smallShieldSpin2", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 0.0F));

		PartDefinition smallShield2 = smallShieldSpin2.addOrReplaceChild("smallShield2", CubeListBuilder.create().texOffs(20, 46).mirror().addBox(-4.5F, 0.0F, -1.0F, 9.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, -8.0F));

		PartDefinition shieldSpin3 = partdefinition.addOrReplaceChild("shieldSpin3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -11.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition shield3 = shieldSpin3.addOrReplaceChild("shield3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, 0.0F, -6.0F, 2.0F, 18.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 30).mirror().addBox(-1.0F, 18.0F, -4.0F, 2.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(8.0F, -1.0F, 0.0F));

		PartDefinition smallShieldSpin3 = partdefinition.addOrReplaceChild("smallShieldSpin3", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition smallShield3 = smallShieldSpin3.addOrReplaceChild("smallShield3", CubeListBuilder.create().texOffs(42, 46).mirror().addBox(-4.5F, 0.0F, -1.0F, 9.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, -8.0F));

		PartDefinition shieldSpin4 = partdefinition.addOrReplaceChild("shieldSpin4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -11.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition shield4 = shieldSpin4.addOrReplaceChild("shield4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, 0.0F, -6.0F, 2.0F, 18.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 30).mirror().addBox(-1.0F, 18.0F, -4.0F, 2.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(8.0F, -1.0F, 0.0F));

		PartDefinition smallShieldSpin4 = partdefinition.addOrReplaceChild("smallShieldSpin4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition smallShield4 = smallShieldSpin4.addOrReplaceChild("smallShield4", CubeListBuilder.create().texOffs(20, 46).mirror().addBox(-4.5F, 0.0F, -1.0F, 9.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, -8.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(S renderState)
	{
        float frameDelta = Minecraft.getInstance().getDeltaTracker().getRealtimeDeltaTicks();
        HoveringInfernoEntity entity = renderState.entity;

        SHIELD_ANIMATIONS.computeIfAbsent(entity, id -> List.of(
				new ModelPart(List.of(), Map.of()),
				new ModelPart(List.of(), Map.of()),
				new ModelPart(List.of(), Map.of()),
				new ModelPart(List.of(), Map.of())));

		// Lerp entity shield rotation speed
        entity.shieldRotationSpeed += (entity.getShieldRotationSpeed() - entity.shieldRotationSpeed) * frameDelta / (entity.hurtAnimation ? 2 : 5);
		// Stop lerping if close enough
        if (Math.abs(entity.shieldRotationSpeed - entity.getShieldRotationSpeed()) < 0.01f)
        {	entity.shieldRotationSpeed = entity.getShieldRotationSpeed();
            entity.hurtAnimation = false;
        }
		// Set shield rotation, accounting for framerate
		if (!Minecraft.getInstance().isPaused())
		{	entity.shieldYRot += entity.shieldRotationSpeed * frameDelta;
		}
		float tick = entity.shieldYRot;

		this.transformShield(entity, shieldSpin1, "shield1", 0, tick);
		this.transformSmallShield(entity, smallShieldSpin1, "smallShield1", 0, tick);

		this.transformShield(entity, shieldSpin2, "shield2", 90, tick);
		this.transformSmallShield(entity, smallShieldSpin2, "smallShield2", 90, tick);

		this.transformShield(entity, shieldSpin3, "shield3", 180, tick);
		this.transformSmallShield(entity, smallShieldSpin3, "smallShield3", 180, tick);

		this.transformShield(entity, shieldSpin4, "shield4", 270, tick);
		this.transformSmallShield(entity, smallShieldSpin4, "smallShield4", 270, tick);

		this.head.yRot = RDMath.toRadians(renderState.yRot) + RDMath.toRadians(renderState.bodyRot);
		this.head.xRot = RDMath.toRadians(renderState.xRot);
	}

	private void transformShield(HoveringInfernoEntity entity, ModelPart shield, String child, float offset, float ageInTicks)
	{
        float partialTick = Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaPartialTick(true);
        float deltaTime = Minecraft.getInstance().getDeltaTracker().getRealtimeDeltaTicks();

		ModelPart shieldTransforms = getShieldTransforms(entity);
		ModelPart desiredShieldTransforms = getDesiredShieldTransforms(entity);

		ModelPart shield1 = shield.getChild(child);
		shield.yRot = ageInTicks * 0.1f + RDMath.toRadians(offset);

        switch (entity.getAttackPhase())
        {
            case BLOCKING ->
            {
                desiredShieldTransforms.zRot = 0f;
			    desiredShieldTransforms.y = -16;
			    desiredShieldTransforms.x = 10;
            }
            case FLAMETHROWER ->
            {
                desiredShieldTransforms.zRot = -1.2f;
				desiredShieldTransforms.y = -6;
				desiredShieldTransforms.x = 10;
            }
            default ->
            {
                desiredShieldTransforms.zRot = -0.3f;
				desiredShieldTransforms.y = -12;
				desiredShieldTransforms.x = 12;
            }
        }
		shield.y = (float) Math.sin((entity.tickCount + partialTick) * 0.2f + offset / 9) * 3 - 4;

		float frametime = deltaTime / 8;
		shieldTransforms.x += (desiredShieldTransforms.x - shieldTransforms.x) * frametime;
		shieldTransforms.y += (desiredShieldTransforms.y - shieldTransforms.y) * frametime;
		shieldTransforms.z += (desiredShieldTransforms.z - shieldTransforms.z) * frametime;
		shieldTransforms.xRot += (desiredShieldTransforms.xRot - shieldTransforms.xRot) * frametime;
		shieldTransforms.yRot += (desiredShieldTransforms.yRot - shieldTransforms.yRot) * frametime;
		shieldTransforms.zRot += (desiredShieldTransforms.zRot - shieldTransforms.zRot) * frametime / 2;
		shield1.copyFrom(shieldTransforms);
	}

	private void transformSmallShield(HoveringInfernoEntity entity, ModelPart shield, String child, float offset, float ageInTicks)
	{
        float partialTick = Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaPartialTick(true);
        float deltaTime = Minecraft.getInstance().getDeltaTracker().getRealtimeDeltaTicks();

		ModelPart smallShieldTransforms = getSmallShieldTransforms(entity);
		ModelPart desiredSmallShieldTransforms = getDesiredSmallShieldTransforms(entity);

		ModelPart shield1 = shield.getChild(child);
		shield.yRot = ageInTicks * 0.2f + RDMath.toRadians(offset);

        switch (entity.getAttackPhase())
        {
            case BLOCKING ->
            {
                desiredSmallShieldTransforms.zRot = 0.2f;
				desiredSmallShieldTransforms.y = -1;
				desiredSmallShieldTransforms.x = 7;
            }
            case FLAMETHROWER ->
            {
                desiredSmallShieldTransforms.zRot = -0.8f;
				desiredSmallShieldTransforms.y = -1;
				desiredSmallShieldTransforms.x = 7;
            }
            default ->
            {
                desiredSmallShieldTransforms.zRot = 0.2f;
				desiredSmallShieldTransforms.x = 9;
            }
        }
		shield.y = (float) Math.sin((entity.tickCount + partialTick) * 0.1f + offset / 9 + 10) * 3;

		float frametime = deltaTime / 8;
		smallShieldTransforms.x += (desiredSmallShieldTransforms.x - smallShieldTransforms.x) * frametime;
		smallShieldTransforms.y += (desiredSmallShieldTransforms.y - smallShieldTransforms.y) * frametime;
		smallShieldTransforms.z += (desiredSmallShieldTransforms.z - smallShieldTransforms.z) * frametime;
		smallShieldTransforms.xRot += (desiredSmallShieldTransforms.xRot - smallShieldTransforms.xRot) * frametime;
		smallShieldTransforms.yRot += (desiredSmallShieldTransforms.yRot - smallShieldTransforms.yRot) * frametime;
		smallShieldTransforms.zRot += (desiredSmallShieldTransforms.zRot - smallShieldTransforms.zRot) * frametime;
		shield1.copyFrom(smallShieldTransforms);
		shield1.yRot = RDMath.toRadians(-90);
		shield1.z = 0;
	}
	
	private ModelPart getShieldTransforms(HoveringInfernoEntity entity)
	{	return SHIELD_ANIMATIONS.get(entity).get(0);
	}
	
	private ModelPart getDesiredShieldTransforms(HoveringInfernoEntity entity)
    {	return SHIELD_ANIMATIONS.get(entity).get(1);
	}
	
	private ModelPart getSmallShieldTransforms(HoveringInfernoEntity entity)
    {	return SHIELD_ANIMATIONS.get(entity).get(2);
	}
	
	private ModelPart getDesiredSmallShieldTransforms(HoveringInfernoEntity entity)
    {	return SHIELD_ANIMATIONS.get(entity).get(3);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int packedColor)
	{	head.render(poseStack, vertexConsumer, packedLight, packedOverlay, packedColor);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, packedColor);
		shieldSpin1.render(poseStack, vertexConsumer, packedLight, packedOverlay, packedColor);
		smallShieldSpin1.render(poseStack, vertexConsumer, packedLight, packedOverlay, packedColor);
		shieldSpin2.render(poseStack, vertexConsumer, packedLight, packedOverlay, packedColor);
		smallShieldSpin2.render(poseStack, vertexConsumer, packedLight, packedOverlay, packedColor);
		shieldSpin3.render(poseStack, vertexConsumer, packedLight, packedOverlay, packedColor);
		smallShieldSpin3.render(poseStack, vertexConsumer, packedLight, packedOverlay, packedColor);
		shieldSpin4.render(poseStack, vertexConsumer, packedLight, packedOverlay, packedColor);
		smallShieldSpin4.render(poseStack, vertexConsumer, packedLight, packedOverlay, packedColor);
	}
}