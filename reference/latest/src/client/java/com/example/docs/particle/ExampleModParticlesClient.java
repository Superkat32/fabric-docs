package com.example.docs.particle;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;

import net.minecraft.client.particle.EndRodParticle;

// #region entrypoint
public class ExampleModParticlesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// Registers a ParticleProvider for your ParticleType
		// For this example, we use the end rod particle's logic via EndRodParticle.Provider::new
		ParticleProviderRegistry.getInstance().register(ExampleModParticles.SPARKLE_PARTICLE, EndRodParticle.Provider::new);
	}
}
// #endregion entrypoint
