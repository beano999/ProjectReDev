package net.roadkill.redev.common.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.PumpkinBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.roadkill.redev.common.block.ModCarvedPumpkinBlock;
import net.roadkill.redev.core.init.BlockInit;

@Mod.EventBusSubscriber
public class PumpkinRightClick
{
//    @SubscribeEvent
    public static void onPumpkinRightClick(PlayerInteractEvent event)
    {
        if(!event.getLevel().isClientSide())
        {
            Level world = event.getLevel();
            Player player = event.getEntity();
            BlockPos pPos = event.getPos();
            Direction direction = event.getFace();
            Direction direction1 = direction.getAxis() == Direction.Axis.Y ? player.getDirection().getOpposite() : direction;
            if (checkForShearsOnPumpkin(event) && world.getBlockState(event.getPos()).getBlock() instanceof PumpkinBlock) {
                world.setBlock(event.getPos(), BlockInit.CARVED_PUMPKIN.get().defaultBlockState().setValue(ModCarvedPumpkinBlock.FACING, event.getEntity().getDirection().getOpposite()), 3);
                player.swing(event.getHand());
                player.getItemInHand(event.getHand()).setDamageValue(event.getItemStack().getDamageValue() - 1);
                ItemEntity itementity = new ItemEntity(world, (double)pPos.getX() + 0.5D + (double)direction1.getStepX() * 0.65D, (double)pPos.getY() + 0.1D, (double)pPos.getZ() + 0.5D + (double)direction1.getStepZ() * 0.65D, new ItemStack(Items.PUMPKIN_SEEDS, 4));
                itementity.setDeltaMovement(0.05D * (double)direction1.getStepX() + world.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double)direction1.getStepZ() + world.random.nextDouble() * 0.02D);

            }
        }
    }

    private static boolean checkForShearsOnPumpkin(PlayerInteractEvent event)
    {
        Block block = event.getLevel().getBlockState(event.getPos()).getBlock();
        Item item = event.getEntity().getItemInHand(event.getHand()).getItem();
        return item instanceof ShearsItem && (block instanceof PumpkinBlock || block instanceof CarvedPumpkinBlock);
    }
}
