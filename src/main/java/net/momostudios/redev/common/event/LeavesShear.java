package net.momostudios.redev.common.event;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.momostudios.redev.common.block.HedgeBlock;
import net.momostudios.redev.util.registries.ModBlocks;

@Mod.EventBusSubscriber
public class LeavesShear
{
    @SubscribeEvent
    public static void onShear(PlayerInteractEvent.RightClickBlock event)
    {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        ItemStack stack = event.getItemStack();
        Player player = event.getEntity();
        RandomSource random = level.getRandom();
        if (state.is(BlockTags.LEAVES) && stack.getItem() instanceof ShearsItem)
        {
            boolean shouldShear = false;
            BlockState hedgeState = null;
            if (state.is(Blocks.OAK_LEAVES))
            {   shouldShear = true;
                hedgeState = ModBlocks.OAK_HEDGE.defaultBlockState();
            }
            else if (state.is(Blocks.SPRUCE_LEAVES))
            {   shouldShear = true;
                hedgeState = ModBlocks.SPRUCE_HEDGE.defaultBlockState();
            }
            else if (state.is(Blocks.BIRCH_LEAVES))
            {   shouldShear = true;
                hedgeState = ModBlocks.BIRCH_HEDGE.defaultBlockState();
            }
            else if (state.is(Blocks.JUNGLE_LEAVES))
            {   shouldShear = true;
                hedgeState = ModBlocks.JUNGLE_HEDGE.defaultBlockState();
            }
            else if (state.is(Blocks.ACACIA_LEAVES))
            {   shouldShear = true;
                hedgeState = ModBlocks.ACACIA_HEDGE.defaultBlockState();
            }
            else if (state.is(Blocks.DARK_OAK_LEAVES))
            {   shouldShear = true;
                hedgeState = ModBlocks.DARK_OAK_HEDGE.defaultBlockState();
            }
            else if (state.is(Blocks.MANGROVE_LEAVES))
            {   shouldShear = true;
                hedgeState = ModBlocks.MANGROVE_HEDGE.defaultBlockState();
            }
            if (shouldShear)
            {
                if (!player.isCreative())
                {   stack.hurtAndBreak(1, player, (p) ->
                    {   p.broadcastBreakEvent(event.getHand());
                    });
                }
                if (level.isClientSide)
                {
                    for (int i = 0; i < 20; i++)
                    {   level.addParticle(
                                new BlockParticleOption(ParticleTypes.BLOCK, state),
                                pos.getX() + random.nextDouble(),
                                pos.getY() + random.nextDouble(),
                                pos.getZ() + random.nextDouble(),
                                0.0, 0.0, 0.0
                        );
                    }
                }
                level.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 0.5f, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.6F);
                level.playSound(null, pos, SoundEvents.CROP_BREAK, SoundSource.BLOCKS, 1.3f, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                level.setBlock(pos, hedgeState, 11);
                player.swing(event.getHand());
                player.awardStat(Stats.ITEM_USED.get(Items.SHEARS));
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void breakHedgeShears(PlayerEvent.BreakSpeed event)
    {
        if (event.getState().getBlock() instanceof HedgeBlock && event.getEntity().getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof ShearsItem)
        {   event.setNewSpeed(event.getOriginalSpeed() * 6f);
        }
    }
}
