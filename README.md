# ExGregilo
![Version](https://img.shields.io/badge/minecraft-1.7.10-orange.svg?style=for-the-badge) 
[![Gregtech](https://img.shields.io/badge/gregtech-5.09.31-red.svg?style=for-the-badge)](https://github.com/Blood-Asp/GT5-Unofficial)

[![Build Status](https://travis-ci.org/Quantum64/ExGregilo.svg?branch=master)](https://travis-ci.org/Quantum64/ExGregilo) 
[![Dev builds](https://img.shields.io/badge/download-dev-orange.svg)](https://github.com/Quantum64/ExGregilo/releases/latest)
[![Release builds](https://img.shields.io/badge/download-release-green.svg)](https://minecraft.curseforge.com/projects/exgregilo)
[![Issues](https://img.shields.io/github/issues/Quantum64/ExGregilo.svg)](https://github.com/Quantum64/ExGregilo/issues)
[![License](https://img.shields.io/badge/license-GPLv3-blue.svg)]()

*Bringing Greg’s vision for the world into the sky…*

## Table of Contents
- [Features](#features)
- [Contributing](#contributing)
- [Downloads](#downloads)
- [Issues or Feature Requests](#issues-or-feature-requests)
- [Regarding 1.12](#regarding-1.12)
- [API](#api)
- [Modpacks](#modpacks)
- [Donate](#donate)


## Features

### Core
- **Basic sieve** - Early game sieve designed to replace the original ExNihilo sieve. Requires players to craft sieve meshes which have a limited durability.
- **Advanced sieve** - Similar to the basic sieve, but the selection of meshes is much larger. Advanced meshes also have a higher durability than basic meshes.
- **Heavy sieve** - Inspired by the ExCompressum heavy sieve, it allows compressed gravel, sand, and dust to be sifted for a 7x reward multiplier. Requires compressed meshes, which are crafted from 9 advanced meshes.
- **Compressed blocks** - Introduces compressed gravel, sand, and dust up to 8x which is OreDict compatible with ExtraUtilities compressed blocks.

### GregTech
- **Auto Sieve** - GregTech machine that allows the automated sifting of sand, dust, and gravel. All rewards are in the form of GregTech crushed ores.
- **Gem Extractor** - GregTech machines that allows all GregTech gems to be obtained using gem shards that are received from sieving gravel.
- **Compressed Hammer** - GregTech tool that allows compressed blocks to be mined into their pulverized counterparts (similar to a hammer, but much more expensive).
- **Industrial Forge Hammer** - Machine version of the compressed hammer.
- **Electric Crucible** - Machine that can melt cobblestone, netherrack, and gravel into lava.

### ExNihilo

- **Greg’s Crucible** - Replaces the normal ExNihilo crucible. Similar in functionality except does not allow any interactions with pipes. The Electric Crucible must be used for automation.
- Replaces all ores in ExNihilo’s sieve registry with their GregTech equivalents.
- Removes all ExNihilo hammers by default. GregTech hammers should be used instead.
- Removes the ExNihilo sieve by default. The ExGregilo Basic Sieve should be used instead,

### ExAstris
- Removes the ExAstris auto sieve.

### ExCompressum
- Removes the auto sieve, auto heavy sieve, auto hammer, and auto compressed hammer.
- Removes the recipes for ExCompressum compressed blocks because they are not OreDict compatible.
- Removes ExCompressum compressed hammers by default. ExGregilo compressed hammers should be used instead.
- Removes ExCompressum heavy sieves by default. ExGregilo heavy sieves should be used instead.

### MineTweaker
- The ExGregilo sieve registry (used for all ExGregilo sieves, the auto sieve, and the industrial auto sieve) can be fully customized with MineTweaker.

## Contributing

### Textures
Texture creation is the most valuable way that you can currently contribute. I have no artistic talent whatsoever and most of the textures are either nonexistent or desperately awaiting replacement. Note that GregTech machine overlays should simply accent the underlying machine block, not totally obscure it. Please submit textures by forking the repository, replacing the textures you want to contribute, then submitting a pull request with your updates textures. Feel free to submit changes for any textures, even ones with checked boxes, if you think that you can improve them.


**Texture Locations**

[Blocks](https://github.com/Quantum64/ExGregilo/tree/master/src/main/resources/assets/exgregilo/textures/blocks) -
[Items](https://github.com/Quantum64/ExGregilo/tree/master/src/main/resources/assets/exgregilo/textures/items) - 
[GregTech Tools](https://github.com/Quantum64/ExGregilo/tree/master/src/main/resources/assets/exgregilo/textures/items/icons) - 
[GregTech Machines](https://github.com/Quantum64/ExGregilo/tree/master/src/main/resources/assets/exgregilo/textures/blocks/icons) -
[GregTech GUIs](https://github.com/Quantum64/ExGregilo/tree/master/src/main/resources/assets/gregtech/textures/gui)

**Texture List**
- [X] Auto sieve front
- [ ] Auto sieve active front - Nonexistent
- [X] Auto sieve top
- [X] Auto sieve sides
- [X] Basic auto sieve top
- [ ] Basic auto sieve front - Nonexistent
- [ ] Basic auto sieve active front - Nonexistent
- [X] Auto sieve GUI
- [ ] Gem extractor front - Placeholder
- [ ] Gem extractor active front - Nonexistent
- [ ] Gem extractor top - Placeholder
- [ ] Gem extractor sides - Placeholder
- [ ] Basic gem extractor  top - Nonexistent
- [ ] Basic gem extractor  front - Nonexistent
- [ ] Basic gem extractor active front - Nonexistent
- [X] Gem extractor GUI - Could be improved
- [X] Compressed hammer
- [ ] Industrial forge hammer front - Placeholder
- [ ] Industrial forge hammer active front - Placeholder
- [ ] Industrial forge hammer top - Nonexistent
- [ ] Industrial forge hammer sides - Nonexistent
- [ ] Basic industrial forge hammer  top - Nonexistent
- [ ] Basic industrial forge hammer  front - Nonexistent
- [ ] Basic industrial forge hammer active front - Nonexistent
- [X] Industrial forge hammer GUI
- [ ] Electric crucible front - Nonexistent
- [ ] Electric crucible active front - Nonexistent
- [ ] Electric crucible top - Nonexistent
- [ ] Electric crucible sides - Nonexistent
- [ ] Basic electric crucible  top - Nonexistent
- [ ] Basic electric crucible  front - Nonexistent
- [ ] Basic electric crucible active front - Nonexistent
- [ ] Electric crucible GUI - Placeholder
- [X] Advanced sieve
- [ ] Basic sieve - Placeholder **Important!**
- [ ] Heavy sieve - Placeholder **Important!**
- [X] Advanced sieve mesh
- [X] Basic sieve mesh - Could use some changes.
- [ ] Heavy sieve mesh - Placeholder
- [ ] Compressed gravel 1x-8x - Placeholders **Important!**
- [ ] Compressed sand 1x-8x - Placeholders **Important!**
- [ ] Compressed dust 1x-8x - Placeholders **Important!**
- [X] Gem shards - Could be improved with a more “GregTech Look”
- [ ] Gem sand - Exists but I really hate this texture
- [X] Compressed hammer GUI
- [ ] Compressed silk hammer GUI - Currently the same as compressed hammer
- [X] Advanced rendered sieve mesh - This is the actual texture rendered in the sieve block
- [X] Basic rendered sieve mesh - Same is above, probably fine
- [ ] Rendered heavy sieve mesh - I want this to look different, currently it’s the same
- [ ] Dust - Currently solid grey block **Really Important!**


### Translations
If you speak multiple languages, translating ExGregilo into another language is an appreciated contribution. Fork the repository and create a new file for your language [here](https://github.com/Quantum64/ExGregilo/tree/master/src/main/resources/assets/exgregilo/lang). If the file already exists, you can check if it needs to be updated with new entries. Copy and translate the entries from [here](https://github.com/Quantum64/ExGregilo/blob/master/src/main/resources/assets/exgregilo/lang/en_US.lang) into your language file. Check back from time to time for new entries that need to be translated. Once your changes are finished, submit a pull request so they can be included in the next release.

### Programming

Currently I do most of the programming for ExGregilo myself. If you find an issue that you want to fix yourself, you can fork the repository and submit a pull request. This mod is programmed in a somewhat unconventional way using a dependency injection framework, so be sure to test your changes before submitting them. All pull requests with code changes **must** pass the automated TravisCI test. If your PR does not pass, check for the cause of the issue in the TravisCI and fix it so your request can be merged.

## Downloads
If you want to test the latest changes and report issues, you can download the development build which is automatically generated and deployed after every commit. Do not expect this build to be stable and do not use it in a modpack. The development build of the mod is located on the GitHub releases page [here](https://github.com/Quantum64/ExGregilo/releases/latest).

Stable builds of the mod that can be used in modpacks and should have few stability issues are found on the official Curse project page which can be found [here](https://minecraft.curseforge.com/projects/exgregilo).

## Issues or Feature Requests
Issues and feature requests should only be submitting on the GitHub issue tracker found [here](https://github.com/Quantum64/ExGregilo/issues). When submitting an issue, try to include as much information about the problem as possible. If the game has crashed due to ExGregilo, you are absolutely required to submit a crash report in your issue description. Do not paste the entire crash report into the GitHub issue, but rather include a link to it on a paste service such as [Gist](https://gist.github.com/). Feature requests are also accepted on the issue tracker and will be considered for a future release of the mod.

## Regarding 1.12
Right now a very skilled programmer is working on porting GregTech to 1.12. In fact, the port is almost done aside from the massive amount of missing content. Currently it is used by few to no people, so I am not interested in porting to 1.12 at this time. I do, however, think that many of the 1.12 mods are superior to 1.7 mods, so after the GregTech 1.12 port is complete, I would not be surprised if packs start moving to the 1.12 version of GregTech. If this happens, I plan on updating ExGregilo to 1.12, however until then it is going to stay a 1.7 mod.

## API
Although the programming style of this mod can be hard to wrap your head around, one benefit of it is that the codebase becomes very modular. This is useful for other mods that want to modify the behavior of ExGregilo. The code is currently not documented because I am concerned with adding features as quickly as possible right now with limited time. Once I believe the mod is feature complete I may document the code (probably not until after the 1.12 update, if that ends up happening). More information on how to use the API can be found [here](https://github.com/Quantum64/ExGregilo/blob/master/src/main/java/co/q64/exgregilo/api/ExGregiloAPI.java#L8). I currently am not providing deobf jars of the mod, but you can compile one yourself if you really want to make an addon. NEVER INCLUDE MY API FILES IN YOUR MOD. The only thing this does it break version compatibility and for no reason. Just don’t do it.

## Modpacks
In accordance with the GPLv3 license, you can use this code for pretty much whatever. That means you have full permission to include this mod in an unmodified or modified form in your modpacks. Just remember that any distributed modified version (or any mod that uses any code from this mod) are required to be open source and state changes to comply with the GPLv3 license.

## Donate
Programming takes a lot of effort and a lot of coffee. If you want to support the development of this mod, you can send Ethereum (ETH) or ERC20 tokens to this address: ```0xadf06b287a6571c7971ef65c2102d9fdd3907fd5```. The only thing you get from donating is the satisfaction of knowing that you contributed to the development of this mod. Any support is greatly appreciated.


