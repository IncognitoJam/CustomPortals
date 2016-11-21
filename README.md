# Custom Portals
Create portals in your Minecraft server made from alternative materials to teleport your users to different worlds. Custom world materials can be specified in the configuration file.

## Configuration
Below each configuration option is explained in detail.

#### Portal search options
`portal-search-range: 64` This specifies the radius in which the plugin should search for portals at the target location, before deciding to create a new portal (as none are present for the user to teleport to). *This value should always be written as an integer and not as a decimal.*

`portal-creation-range: 24` The radius in which the plugin should search for an empty space to create a portal at the destination. If no space is found in this range then no portal is created and the teleport is cancelled. *This value should always be written as an integer and not as a decimal.*

**Warning:** This option has undefined affects. Please keep this value set to `true`.
`override-nether: true` Forces the plugin to override nether portals (does not work correctly as of now). *This value should always be written as an integer and not as a decimal.*

#### Portal materials

```yaml
portal-materials:
    # Material used to construct a portal 
    # which returns the player to the overworld.
    world: "1"              # Stone
    
    # Material used to construct a portal
    # which will teleport players to the nether.
    # Note: It is recommended to keep the nether
    # portal material as obsidian to prevent
    # player confusion.
    world_nether: "49:0"    # Obsidian
    
    # Material used to construct a portal
    # which will teleport players to a custom
    # world: the "arcade".
    arcade: "35:1"          # Orange Wool
```

Materials should be specified with quotes surrounding them and written in the format `"ID:meta"`. For example, a portal made from Orange Wool may be written as `"35:1"`. The `:meta` is optional, for example stone which does not require a meta value can be written as either `"1"` or `"1:0"`. You cannot write the material as a number, but only as a string with quotes `"string"`. 

***TODO:*** Accept material names.

#### World Scale
```yaml
world-scale:
    world: 1.0
    world_nether: 8.0
    arcade: 2.0
```

You must provide a world scale for each world that a portal may be created for. If this is not specified the teleport will not commence. Decimal values are accepted but not recommended (untested).

***TODO:*** Implement default world scale.

#####Example:
When teleporting from `world_nether` to `arcade`, the x and y coordinates will be converted to `universe coordinates` by diving by the `world-scale` constant for `world_nether` (`8.0`). To convert the `u coordiantes` to `world coordinates` for `arcade` they must be multiplied by the `world-scale` for `arcade` (`2.0`).

######Pseudo-code:
```
sourceWorld = "world_nether"
sourceWorldScale = 8.0
sourceCoords = (100, 64, 100)

destinationWorld = "arcade"
destinationWorldScale = 2.0

universeCoords = sourceCoords / sourceWorldScale
    = (100/8, 64, 100/8)

destinationCoords = universeCoords * destinationWorldScale
    = (100/8 * 2, 64, 100/8 * 2)
    = (25, 64, 25)
```

## Bugs
* There are currently some issues with the creation of portals, including:
    * Portals spawning on trees
    * Portals spawning above the world roof (nether)
    * Portals not being detected and more being created.

Please [contact me](mail_to:cameron.jamco@gmail.com) if you identify any bugs or make a issue thread here on GitHub.

## Releases
~~Compiled JARs for this plugin can be found on the [Spigot resources]() site.~~
Builds will be released soon for 1.10 and 1.11.

## Credits
This plugin was developed by IncognitoJam at the request of [Robotnik](https://www.spigotmc.org/members/robotnik.9977/) on the SpigotMC forums.