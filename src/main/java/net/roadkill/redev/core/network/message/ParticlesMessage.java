package net.roadkill.redev.core.network.message;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ParticlesMessage
{
    ParticleOptions particle;
    ResourceLocation level;
    double x, y, z;
    double spreadX, spreadY, spreadZ;
    double xSpeed, ySpeed, zSpeed;
    int count;

    public ParticlesMessage(ParticleOptions particle, Level level, double x, double y, double z,
                            double spreadX, double spreadY, double spreadZ,
                            double xSpeed, double ySpeed, double zSpeed, int count)
    {   this.particle = particle;
        this.level = level.dimension().location();
        this.x = x;
        this.y = y;
        this.z = z;
        this.spreadX = spreadX;
        this.spreadY = spreadY;
        this.spreadZ = spreadZ;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.zSpeed = zSpeed;
        this.count = count;
    }

    ParticlesMessage(ParticleOptions particle, ResourceLocation level, double x, double y, double z,
                     double spreadX, double spreadY, double spreadZ,
                     double xSpeed, double ySpeed, double zSpeed, int count)
    {
        this.particle = particle;
        this.level = level;
        this.x = x;
        this.y = y;
        this.z = z;
        this.spreadX = spreadX;
        this.spreadY = spreadY;
        this.spreadZ = spreadZ;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.zSpeed = zSpeed;
        this.count = count;
    }

    public static void encode(ParticlesMessage message, FriendlyByteBuf buffer)
    {   buffer.writeId(BuiltInRegistries.PARTICLE_TYPE, message.particle.getType());
        buffer.writeResourceLocation(message.level);
        buffer.writeDouble(message.x);
        buffer.writeDouble(message.y);
        buffer.writeDouble(message.z);
        buffer.writeDouble(message.spreadX);
        buffer.writeDouble(message.spreadY);
        buffer.writeDouble(message.spreadZ);
        buffer.writeDouble(message.xSpeed);
        buffer.writeDouble(message.ySpeed);
        buffer.writeDouble(message.zSpeed);
        buffer.writeInt(message.count);
    }

    public static ParticlesMessage decode(FriendlyByteBuf buffer)
    {
        ParticleType pParticleType = buffer.readById(BuiltInRegistries.PARTICLE_TYPE);
        ParticleOptions particle = pParticleType.getDeserializer().fromNetwork(pParticleType, buffer);
        return new ParticlesMessage(particle, buffer.readResourceLocation(),
                                    buffer.readDouble(), buffer.readDouble(), buffer.readDouble(),
                                    buffer.readDouble(), buffer.readDouble(), buffer.readDouble(),
                                    buffer.readDouble(), buffer.readDouble(), buffer.readDouble(),
                                    buffer.readInt());
    }

    public static void handle(ParticlesMessage message, Supplier<NetworkEvent.Context> contextSupplier)
    {
        NetworkEvent.Context context = contextSupplier.get();

        if (context.getDirection().getReceptionSide().isClient())
        {
            context.enqueueWork(() ->
            {
                Level level = Minecraft.getInstance().level;
                if (level != null && level.dimension().location().equals(message.level))
                {
                    for (int i = 0; i < message.count; i++)
                    {
                        level.addParticle(message.particle,
                                          message.x + (level.random.nextDouble() - 0.5) * message.spreadX,
                                          message.y + (level.random.nextDouble() - 0.5) * message.spreadY,
                                          message.z + (level.random.nextDouble() - 0.5) * message.spreadZ,
                                          message.xSpeed, message.ySpeed, message.zSpeed);
                    }
                }
            });
        }

        context.setPacketHandled(true);
    }
}
