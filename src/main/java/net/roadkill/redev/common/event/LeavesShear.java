package net.roadkill.redev.common.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.roadkill.redev.common.block.HedgeBlock;
import net.roadkill.redev.common.block.LeafyWallBlock;
import net.roadkill.redev.core.init.BlockInit;
import net.roadkill.redev.util.registries.ModBlocks;

import javax.annotation.Nullable;

@EventBusSubscriber
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
            Holder<Block> hedge = getHedgeForBlock(state);

            if (hedge != null)
            {
                BlockState hedgeState = hedge.value().defaultBlockState();
                if (!player.isCreative())
                {   stack.hurtAndBreak(1, (ServerLevel) level, player, (p) -> {});
                }
                if (level.isClientSide)
                {
                    for (int i = 0; i < 20; i++)
                    {
                        level.addParticle(
                                new BlockParticleOption(ParticleTypes.BLOCK, state),
                                pos.getX() + random.nextDouble(),
                                pos.getY() + random.nextDouble(),
                                pos.getZ() + random.nextDouble(),
                                0.0, 0.0, 0.0
                        );
                    }
                }
                level.setBlock(pos, hedgeState, 11);

                level.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 0.5f, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.6F);
                level.playSound(null, pos, SoundEvents.CROP_BREAK, SoundSource.BLOCKS, 1.3f, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                player.swing(event.getHand());
                player.awardStat(Stats.ITEM_USED.get(Items.SHEARS));

                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void breakHedgeShears(PlayerEvent.BreakSpeed event)
    {
        if ((event.getState().getBlock() instanceof HedgeBlock || event.getState().getBlock() instanceof LeafyWallBlock)
        && event.getEntity().getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof ShearsItem)
        {   event.setNewSpeed(event.getOriginalSpeed() * 6f);
        }
    }

    @Nullable
    public static Holder<Block> getHedgeForBlock(BlockState state)
    {
        return state.is(Blocks.OAK_LEAVES)      ? BlockInit.OAK_HEDGE
             : state.is(Blocks.SPRUCE_LEAVES)   ? BlockInit.SPRUCE_HEDGE
             : state.is(Blocks.BIRCH_LEAVES)    ? BlockInit.BIRCH_HEDGE
             : state.is(Blocks.JUNGLE_LEAVES)   ? BlockInit.JUNGLE_HEDGE
             : state.is(Blocks.ACACIA_LEAVES)   ? BlockInit.ACACIA_HEDGE
             : state.is(Blocks.DARK_OAK_LEAVES) ? BlockInit.DARK_OAK_HEDGE
             : state.is(Blocks.MANGROVE_LEAVES) ? BlockInit.MANGROVE_HEDGE
             : state.is(Blocks.AZALEA_LEAVES)   ? BlockInit.AZALEA_HEDGE
             : state.is(Blocks.FLOWERING_AZALEA_LEAVES) ? BlockInit.FLOWERING_AZALEA_HEDGE
             : null;
    }
}
