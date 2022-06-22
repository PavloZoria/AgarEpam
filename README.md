# AgarEpam

# This project in development stage

## Common documentation

There is `CellLogic` class which should be extended in order to write your custom logic to the Cell

#TODO
1. improve documentation of the entities
2. publish library and describe how user can integrate it 

## Android Library

### Set up

In order to integrate this library into you project you have to do the next:

1. Create an empty project without any activity
2. Create a new class and extend it from `AgarApp`
3. Override `cellImplementation`. This method have to return your implementation of the `CellLogic` class

**Sample:**

```kotlin
class MyApp : AgarApp() {
    override fun cellImplementation(): CellLogic {
        return MyCustomCell()
    }
}
```
4. Register your app class in `AndroidManifest.xml` file:
```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
          package="com.epam.agar.hackathon.agar_epam">

  <uses-permission android:name="android.permission.INTERNET"/>
  <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
  <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
  <uses-permission android:name="android.permission.CHANGE_NETWORK_STATE" />
  <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
  <uses-permission android:name="android.permission.WAKE_LOCK"/>

  <application
          android:name=".app.MyApp"
          android:usesCleartextTraffic="true">
  </application>

</manifest>

```
5. Run application

### CellLogic

There is `CellLogic` class which should be extended in order to write your custom logic to the Cell. You have to
override `handleGameUpdate` in order to write your custom logic there:

```kotlin
class MyCellLogic : CellLogic() {
    override fun handleGameUpdate(mapState: MapState): DesiredCellsState? {
        return DesiredCellsState(emptyList())
    }
}
```

_Note:_
> Return type is optional and your cells will do nothing if `null` is returned

## IOS Library

## Flutter Library

## Vocabulary

### Cell

`Cell` - one active entity(circle) on map that can move/split into several new cells/merge with your cells/eat alien
cells/eat food

### Food

`Food` - small inert circles on map that can be eaten in order to gain new energy.

### MapState
The representation of the current map state:
- `gameTick` - Current game tick
- `myCells` - Cells that you can work with in terms of developing/moving/updating them
- `alienCells` - Alien cells that potentially can be harmful. You have to be careful and avoid them until you are sure that you can eat it
- `food` - All food that exists on map

### DesiredCellsState
The `DesiredCellsState` is the representation of how you want to develop/update all your cells that you have on the map
and contains the list of `CellActivity` which is the representation of your cell and the way how you want to
develop/move it.

### CellActivity
This item represents the way how we want to update our item based on `cellId`.

#### - Velocity
Velocity is a speed vector. The direction where we want to move our cell. Vector length will be ignored and will be
calculated based on your speed

#### - GrowIntention
Represent in which way we want to develop our cell parameters

- `eatEfficiency` -
- `maxSpeed` -
- `power` -
- `mass` - the mass of the cell
- `volatilization` - the speed in which your cell lose weight. So you have to eat(get new energy) all the time in order
  to keep your mass and grow

> Note:

#### - TurnAction
`TurnAction` is an additional action that we can perform during the turn. Possible actions:

- `Split` - split the cell into 2 new equal cells
- `Merge` - merge cells together. Should containe the `id` of the taget cell.

### CellProperty
The properties of the `Cell`:
- `mass` - current mass of your cell
- `radius` - the radius of the cell's circle
- `speed` - maximum speed of the cell
- `position`
- `velocity`
- `eatEfficiency`
- `power`

