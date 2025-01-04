package net.roadkill.redev.core.init;

import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.roadkill.redev.ReDev;
import net.roadkill.redev.core.entity.PhantomType;

import java.util.function.Supplier;

public class ModDataAttachments
{
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, ReDev.MOD_ID);

    public static final Supplier<AttachmentType<PhantomType>> PHANTOM_TYPE = ATTACHMENT_TYPES.register(
            "phantom_type", () -> AttachmentType.builder(() -> PhantomType.NORMAL).serialize(PhantomType.CODEC).build()
    );
}
