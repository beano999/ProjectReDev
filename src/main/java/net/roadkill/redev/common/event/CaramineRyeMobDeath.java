package net.roadkill.redev.common.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.roadkill.redev.common.block.CaramineRyeBlock;
import net.roadkill.redev.core.init.BlockInit;
import net.roadkill.redev.util.registries.ModBlocks;
import org.joml.Vector3f;

@EventBusSubscriber
public class CaramineRyeMobDeath
{
    @SubscribeEvent
    public static void onMobDeath(LivingDeathEvent event)
    {
        BlockPos.MutableBlockPos pos = event.getEntity().blockPosition().mutable();
        Level level = event.getEntity().level();
        if (!level.isClientSide)
        {
            for (int x = -10; x < 10; x++)
            {
                for (int y = -10; y < 10; y++)
                {
                    for (int z = -10; z < 10; z++)
                    {
                        pos.set(event.getEntity().blockPosition()).move(x, y, z);
                        BlockState state = level.getBlockState(pos);
                        if (state.is(BlockInit.CARAMINE_RYE) && state.getValue(CaramineRyeBlock.HALF) == Half.BOTTOM)
                        {
                            int age = state.getValue(CaramineRyeBlock.AGE);
                            if (age < CaramineRyeBlock.MAX_AGE)
                            {
                                int growth = level.getRandom().nextInt(1, 4);
                                BlockInit.CARAMINE_RYE.value().grow(level, pos, state, level.getRandom().nextInt(1, 3));
                                age = Math.min(CaramineRyeBlock.MAX_AGE, age + growth);
                                ((ServerLevel) level).sendParticles(new DustParticleOptions(ARGB.color(new Vec3(1f, 0f, 0f)), 1),
                                                                    pos.getX() + 0.5,
                                                                    pos.getY() + age / 6d,
                                                                    pos.getZ() + 0.5,
                                                                    age * 10 + 5, 0.3, age / 10d, 0.3, 0);
                            }
                        }
                    }
                }
            }
        }
    }
}
