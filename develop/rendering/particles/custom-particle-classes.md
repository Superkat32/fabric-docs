---
title: Custom Particle Classes
description: Learn how to create a new Particle Class with custom logic.
authors:
  - Superkat32
---
A particle's movement is just as important as its textures. And Particle Classes control how a particle interacts with the world. Let's make one!

## Starting The Particle Class {#starting-the-particle-class}
TODO

## Particle Fields List {#particle-fields-list}
Here is a list of the most commonly used fields from various Particle Classes. Feel free to continuously refer back to this list.

| `Particle` Field          | Description                                                                                                                              |
|---------------------------|------------------------------------------------------------------------------------------------------------------------------------------|
| `level` (ClientLevel)     | The current Level the particle (and client player) are in.                                                                               |
| `random` (RandomSource)   | A RandomSource available for generating random numbers.                                                                                  |
| `x`, `y`, `z` (double)    | The x, y, and z coordinates the particle is located at.                                                                                  |
| `xd`, `yd`, `zd` (double) | The x, y, and z velocities, or distance per tick, of the particle.                                                                       |
| `friction` (float)        | Amount to reduce the velocities by every tick.<br/>Default: `0.98f`                                                                      |
| `gravity` (float)         | The product of `0.04 * gravity` is subtracted from the y velocity every tick.<br/>Default: `0f`                                          |
| `lifetime` (int)          | The maximum number of ticks this particle should exist for before despawning.                                                            |
| `age` (int)               | The number of ticks this particle has existed for, usually starting at 0 and counting up until greater or equal to the `lifetime` value. |
| `hasPhysics` (boolean)    | Determines whether the particle can collide with blocks or not.<br/>Default: `true`                                                      |
| `onGround` (boolean)      | Determines whether the particle has collided with the ground or not.                                                                     |

| `SingleQuadParticle` Field | Description |
|----------------------------|-------------|
| `quadSize` (float)         |             |
|                            |             |
|                            |             |


## Adding Custom Logic {#adding-custom-logic}
TODO

## Creating The Particle Provider {#creating-the-particle-provider}
TODO
