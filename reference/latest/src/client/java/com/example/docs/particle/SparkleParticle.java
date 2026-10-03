package com.example.docs.particle;

import org.jspecify.annotations.NonNull;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class SparkleParticle extends SingleQuadParticle {
	public final SpriteSet sprites;
	protected SparkleParticle(
			ClientLevel level,
			double x, double y, double z,
			double xa, double ya, double za,
			SpriteSet sprites
	) {
		// Even though we change the texture shortly after, we need to
		// use sprites.first() in the super constructor as a fallback texture
		super(level, x, y, z, xa, ya, za, sprites.first());
		this.sprites = sprites;

		// Set the texture based on the particle's age (from here, it'll be the first texture)
		this.setSpriteFromAge(this.sprites);
	}

	@Override
	public void tick() {
		super.tick();
		this.setSpriteFromAge(this.sprites);
	}

	@Override
	protected @NonNull Layer getLayer() {
		// We're using Layer.OPAQUE because our particle's textures are not be semi-transparent
		return Layer.OPAQUE;
	}

	public static record Provider(SpriteSet sprites) implements ParticleProvider<SimpleParticleType> {
		@Override
		public @NonNull Particle createParticle(
				SimpleParticleType options,
				@NonNull ClientLevel level,
				double x, double y, double z,
				double xAux, double yAux, double zAux,
				@NonNull RandomSource random
		) {
			return new SparkleParticle(level, x, y, z, xAux, yAux, zAux, this.sprites);
		}
	}
}
