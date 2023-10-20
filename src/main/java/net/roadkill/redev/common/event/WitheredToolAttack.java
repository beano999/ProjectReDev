package net.roadkill.redev.common.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.StructureBlockEntity;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.util.registries.ModItems;

import java.util.Optional;

@Mod.EventBusSubscriber
public class WitheredToolAttack
{
    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event)
    {
        if (event.getSource().getEntity() instanceof Player player)
        {
            ItemStack item = player.getMainHandItem();
            if ((item.is(ModItems.WITHERED_SWORD)
            ||  item.is(ModItems.WITHERED_AXE)
            ||  item.is(ModItems.WITHERED_PICKAXE)
            ||  item.is(ModItems.WITHERED_SHOVEL)
            ||  item.is(ModItems.WITHERED_HOE)
            ||  item.is(ModItems.WITHERED_INGOT)
            ||  item.is(ModItems.CHARRED_BONE)))
            {   event.getEntity().addEffect(new MobEffectInstance(MobEffects.WITHER, 50, 2));
            }
        }
    }
}
