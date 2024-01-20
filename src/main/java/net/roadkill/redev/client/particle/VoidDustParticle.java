package net.roadkill.redev.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.ParticleStatus;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.LightLayer;

public class VoidDustParticle extends TextureSheetParticle
{
    protected VoidDustParticle(ClientLevel level, double x, double y, double z)
    {   super(level, x, y, z);
        this.scale(0.2f);
        this.lifetime = 40;
        this.setParticleSpeed(level.random.nextDouble() * 0.01 - 0.01,
                              0,
                              level.random.nextDouble() * 0.01 - 0.01);
    }

    @Override
    protected int getLightColor(float pPartialTick)
    {   BlockPos blockpos = BlockPos.containing(this.x, this.y, this.z);
        int i = Math.max(3, level.getBrightness(LightLayer.SKY, blockpos));
        int j = Math.max(3, level.getBrightness(LightLayer.BLOCK, blockpos));
        return i << 20 | j << 4;
    }

    @Override
    public ParticleRenderType getRenderType()
    {   return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public record Factory(SpriteSet sprite) implements ParticleProvider<SimpleParticleType>
    {
        public TextureSheetParticle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double pXSpeed, double pYSpeed, double pZSpeed)
        {
            if (Minecraft.getInstance().options.particles().get() == ParticleStatus.MINIMAL)
            {   return null;
            }

            VoidDustParticle particle = new VoidDustParticle(level, x, y, z);
            particle.pickSprite(this.sprite);
            return particle;
        }
    }
}