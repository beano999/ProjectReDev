package net.roadkill.redev.core.entity;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.monster.Phantom;
import net.neoforged.neoforge.network.PacketDistributor;
import net.roadkill.redev.core.init.ModDataAttachments;
import net.roadkill.redev.core.network.message.PhantomTypeSyncMessage;

public enum PhantomType implements StringRepresentable
{
    NORMAL("normal"),
    RED("red"),
    BLUE("blue"),
    GREEN("green"),
    HOLLOW("hollow");

    public static final Codec<PhantomType> CODEC = StringRepresentable.fromEnum(PhantomType::values);

    String name;

    PhantomType(String name)
    {   this.name = name;
    }

    public static PhantomType get(Phantom phantom)
    {   return phantom.getData(ModDataAttachments.PHANTOM_TYPE);
    }

    public static void set(Phantom phantom, PhantomType type)
    {
        phantom.setData(ModDataAttachments.PHANTOM_TYPE, type);
        if (!phantom.level().isClientSide)
        {   PacketDistributor.sendToPlayersTrackingEntity(phantom, new PhantomTypeSyncMessage(phantom, type));
        }
    }

    @Override
    public String getSerializedName()
    {   return name;
    }

    public static PhantomType byName(String name)
    {
        for (PhantomType type : values())
        {
            if (type.name.equals(name))
            {   return type;
            }
        }
        return NORMAL;
    }
}
