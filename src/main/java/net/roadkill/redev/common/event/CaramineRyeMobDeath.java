package net.roadkill.redev.common.event;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.roadkill.redev.common.block.CaramineRyeBlock;
import net.roadkill.redev.util.registries.ModBlocks;
import org.joml.Vector3f;

@Mod.EventBusSubscriber
public class CaramineRyeMobDeath
{
    @SubscribeEvent
    public static void onMobDeath(LivingDeathEvent event)
    {
        BlockPos.MutableBlockPos pos = event.getEntity().blockPosition().mutable();
        Level level = event.getEntity().level;
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
                        if (state.is(ModBlocks.CARAMINE_RYE) && state.getValue(CaramineRyeBlock.HALF) == Half.BOTTOM)
                        {
                            int age = state.getValue(CaramineRyeBlock.AGE);
                            if (age < CaramineRyeBlock.MAX_AGE)
                            {
                                int growth = level.getRandom().nextInt(1, 4);
                                ModBlocks.CARAMINE_RYE.grow(level, pos, state, level.getRandom().nextInt(1, 3));
                                age = Math.min(CaramineRyeBlock.MAX_AGE, age + growth);
                                ((ServerLevel) level).sendParticles(new DustParticleOptions(new Vector3f(1, 0, 0), 1),
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
