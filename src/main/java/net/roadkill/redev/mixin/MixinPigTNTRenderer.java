package net.roadkill.redev.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.PigRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.state.PigRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.roadkill.redev.client.renderer.layer.PigHelmetLayer;
import net.roadkill.redev.client.renderer.layer.PigTNTLayer;
import net.roadkill.redev.mixin_interfaces.IPig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PigRenderer.class)
public abstract class MixinPigTNTRenderer extends MobRenderer<Pig, LivingEntityRenderState, PigModel>
{
    public MixinPigTNTRenderer(EntityRendererProvider.Context pContext, PigModel pModel, float pShadowRadius)
    {   super(pContext, pModel, pShadowRadius);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void addTNT(EntityRendererProvider.Context context ,CallbackInfo ci)
    {   this.addLayer(new PigTNTLayer<>(this));
        this.addLayer(new PigHelmetLayer<>(this, context));
    }

    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/animal/Pig;Lnet/minecraft/client/renderer/entity/state/PigRenderState;F)V", at = @At("TAIL"))
    private void extractRenderState(Pig pig, PigRenderState renderState, float partialTick, CallbackInfo ci)
    {
        IPig iPig = (IPig) pig;
        IPig iRenderPig = (IPig) renderState;
        Minecraft.getInstance().getItemModelResolver().updateForLiving(renderState.headItem, iPig.getHelmet(), ItemDisplayContext.HEAD, true, pig);
        iRenderPig.setHelmet(iPig.getHelmet());
        iRenderPig.setFuse(iPig.getFuse());
        iRenderPig.setHasTNT(iPig.hasTNT());
    }

    @Mixin(PigRenderState.class)
    public static abstract class MixinPigRenderState extends LivingEntityRenderState implements IPig
    {
        public int fuse = 0;
        public boolean hasTNT = false;
        public ItemStack headItem = ItemStack.EMPTY;

        @Override
        public ItemStack getHelmet()
        {   return this.headItem;
        }

        @Override
        public void setHelmet(ItemStack armorItem)
        {   this.headItem = armorItem;
        }

        @Override
        public int getFuse()
        {   return this.fuse;
        }

        @Override
        public void setFuse(int fuse)
        {   this.fuse = fuse;
        }

        @Override
        public boolean hasTNT()
        {   return this.hasTNT;
        }

        @Override
        public void setHasTNT(boolean hasTNT)
        {   this.hasTNT = hasTNT;
        }
    }
}