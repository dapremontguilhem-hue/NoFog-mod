package com.nofog.mixin;

import net.minecraft.client.render.fog.FogRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * Ecrit dans le buffer de brouillard (UBO) : on remplace toutes les distances
 * par des valeurs gigantesques => plus aucun brouillard (monde, eau, lave,
 * neige poudreuse, cecite, obscurite, nether, ciel, nuages).
 */
@Mixin(FogRenderer.class)
public abstract class FogRendererMixin {

    private static final float FAR = 1.0E8F;

    // applyFog(ByteBuffer, int, Vector4f, envStart, envEnd, renderDistStart, renderDistEnd, skyEnd, cloudEnd)

    @ModifyVariable(method = "applyFog(Ljava/nio/ByteBuffer;ILorg/joml/Vector4f;FFFFFF)V",
            at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private float nofog$envStart(float original) {
        return FAR;
    }

    @ModifyVariable(method = "applyFog(Ljava/nio/ByteBuffer;ILorg/joml/Vector4f;FFFFFF)V",
            at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private float nofog$envEnd(float original) {
        return FAR * 2.0F;
    }

    @ModifyVariable(method = "applyFog(Ljava/nio/ByteBuffer;ILorg/joml/Vector4f;FFFFFF)V",
            at = @At("HEAD"), ordinal = 2, argsOnly = true)
    private float nofog$renderDistStart(float original) {
        return FAR;
    }

    @ModifyVariable(method = "applyFog(Ljava/nio/ByteBuffer;ILorg/joml/Vector4f;FFFFFF)V",
            at = @At("HEAD"), ordinal = 3, argsOnly = true)
    private float nofog$renderDistEnd(float original) {
        return FAR * 2.0F;
    }

    @ModifyVariable(method = "applyFog(Ljava/nio/ByteBuffer;ILorg/joml/Vector4f;FFFFFF)V",
            at = @At("HEAD"), ordinal = 4, argsOnly = true)
    private float nofog$skyEnd(float original) {
        return FAR * 2.0F;
    }

    @ModifyVariable(method = "applyFog(Ljava/nio/ByteBuffer;ILorg/joml/Vector4f;FFFFFF)V",
            at = @At("HEAD"), ordinal = 5, argsOnly = true)
    private float nofog$cloudEnd(float original) {
        return FAR * 2.0F;
    }
}
