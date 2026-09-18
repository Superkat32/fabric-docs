---
title: Creating Custom Particles
description: Learn how to create a custom particle using Fabric API.
authors:
  - Superkat32
---

Particles are a powerful tool. They can add ambience to a beautiful scene, or add tension to an edge of your seat boss battle. Let's add one!

## Quick Overview {#quick-overview}
Particles require 5 main components, as listed below. We'll look at each one in a moment, but here's a quick overview.

- **ParticleType** - The main Registry entry for your particle, mostly used when adding your particle to the world.
- **Particle Class** - Handles the logic behind a particle, including its movement, lifetime (time before despawning), scale, and more.
- **ParticleProvider** - Tells your ParticleType which Particle Class to use.
- **Sprite Set JSON** - The JSON file that points to all the textures to be used.
- **Textures** - The textures that the Sprite Set JSON to point towards.

## Particle Type Registration {#particle-type-registration}
For this example, we'll be adding a new sparkle particle that mimics the logic of an end rod particle.

To begin, we need to register a `ParticleType` in your [mod's initializer](../../getting-started/project-structure#entrypoints). This object will be used every time you want to spawn a particle via code, and once more in the ParticleProvider registration.

<<< @/reference/latest/src/main/java/com/example/docs/ExampleMod.java#particle_register_main

The "sparkle_particle" path of the Identifier is for the Sprite Set JSON file that stores the particle's textures and when spawning the particle with commands. You will be creating a new JSON file with that exact name soon.

## Particle Provider Registration {#particle-provider-registration}

Next, we need to register a ParticleProvider in your [mod's client initializer](../../getting-started/project-structure#entrypoints).

The ParticleProvider tells your ParticleType which Particle Class to use. The Particle Class handles everything about a particle's logic, including its movement, lifetime (time before despawning), scale, and more.

For this example, we want to mimic the end rod particle's logic, which we initialize via the `EndRodParticle.Provider::new` lambda. Most commonly, the ParticleProvider is a static class within the associated Particle Class file.

<<< @/reference/latest/src/client/java/com/example/docs/ExampleModClient.java#particle_register_client

::: tip

You can see all the Particle Providers by looking at all the implementations of the `ParticleProvider` interface. This is helpful if you want to use another particle's logic for your own particle.

- IntelliJ's hotkey: <kbd>Ctrl</kbd>+<kbd>Alt</kbd>+<kbd>B</kbd>
- Visual Studio Code's hotkey: <kbd>Ctrl</kbd>+<kbd>F12</kbd>

:::

## Sprite Set JSON & Textures {#sprite-set-json-and-textures}
After the registrations, you will need to create 2 folders in your `resources/assets/<mod_id>/` folder.

| Folder Path                                                                                 | Explanation                                                                                     |
|---------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------|
| `/textures/particle`                                                                        | The `particle` folder will contain all the textures for all of your particles.                  |
| `/particles`                                                                                | The `particles` folder will contain all of the Sprite Set JSON files for all of your particles. |

Add any textures you want to for your particle to your `/textures/particle` folder.

For this example, we have 6 sparkle textures named `sparkle_1` through `sparkle_6`. The `EndRodParticle` class will animate our particle for us based on these textures.

Next, create a new JSON file in the `/particles` folder with the same name as the Identifier path from your ParticleType registration (in this example, "sparkle_particle"). This is your Sprite Set JSON, and inside it will contain the paths to the textures you want to use for your particle.

:::tabs
== Sparkle Example
<<< @/reference/latest/src/main/resources/assets/example-mod/particles/sparkle_particle.json
== Template
```json
{
  "textures": [

  ]
}
```
:::

Most Vanilla Particle Classes will animate the particle based on that `textures` array, with each texture evenly spaced throughout the particle's lifetime. For example, if a particle has 10 textures and exists for 20 ticks, then each texture will be shown for 2 ticks.

However, some Particle Classes don't do that, instead they choose a single random texture from the `textures` array which lasts the particle's full lifetime. Notable examples include `CritParticle` and `FlameParticle` classes (technically, the textures are randomly chosen from the ParticleProviders in these cases).

For this example, though, our chosen `EndRodParticle` class will animate our particle based on our textures.

## Testing the New Particle {#testing-the-new-particle}
Once you've completed your Sprite Set JSON, it's time to load up Minecraft and test out the particle!

You can test your particle by using the `/particle` command with your mod id and your particle's Identifier path:

```mcfunction
/particle example-mod:sparkle_particle ~ ~1 ~
```

![Showcase of the particle](/assets/develop/rendering/particles/sparkle-particle-showcase.png)

::: info

This command works best with a command block.

If you type it in chat, the particle will spawn inside the player, and you'll likely need to walk backwards to see it well.

:::

## Using the Particle in Code {#using-the-particle-in-code}
What good is a particle if you can't spawn it from code?

There's two ways to spawn a particle depending on your [networking context](../../networking). Most commonly, though, you'll be adding particles from the Client side.

:::tabs
==Client Side
`ClientLevel#addParticle()` will add a particle on that client's screen.

TODO - Client add particle example (also the formatting on the tab and tip here is weird code-wise)

::: tip
If you're spawning particles for blocks, Vanilla's `ParticleUtil` class might be helpful. It includes methods for spawning particles around block faces and around blocks in general.

==Server Side
`ServerLevel#sendParticles()` will send a packet telling clients to add particles to their screens.

TODO - Server add particle example

This method's parameters are different from `ClientLevel#addParticle()`, being more tuned towards spawning multiple particles at once (like the Creaking's trail particles).
:::
