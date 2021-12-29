## Stuff to change:

- `settings.gradle`
- `gradle.properties`
- `fabric/src/main/resoursces/fabric.mod.json`
- `fabric/src/main/resources/mixins.json`

## Steps to get up and running
- Open project (root `build.gradle`) in IntelliJ and let it configure gradle tasks
- Run `genIntellijRuns` task and:
  - Edit run configurations (`Edit Configurations`)
  - Click on each of the Forge run configurations and select the `forge` module along with the right JDK version
  
## Some Caveats
Due to the fact that IntelliJ doesn't seem to like it when multiple modules have sources root in the same module 
(i.e. the common module), when building `fabric/` or `forge/`, we will first copy over the common sources to the `common/` directory 
of `fabric/` and `forge/`, which is then added as sources root. Be sure to only modify common files in the root `common/` module, or else
changes will be overwritten when building!