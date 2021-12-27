package ua.com.epam.agar.hackathon.di

import org.kodein.di.*
import ua.com.epam.agar.hackathon.api.game.socket.WebSocket
import ua.com.epam.agar.hackathon.api.game.socket.caching.CachingGameDataRepository
import ua.com.epam.agar.hackathon.api.game.socket.caching.RemoteGameDataRepository
import ua.com.epam.agar.hackathon.api.game.socket.game.GameDataCachingRepository
import ua.com.epam.agar.hackathon.api.game.socket.game.GameWebSocketAsAPI
import ua.com.epam.agar.hackathon.api.game.socket.game.GameWebSocketInteractor
import ua.com.epam.agar.hackathon.api.game.socket.game.tick.DefaultGameTickHandler
import ua.com.epam.agar.hackathon.api.game.socket.game.tick.GameTickHandler
import ua.com.epam.agar.hackathon.api.game.socket.ktor.KtorWebSocket
import ua.com.epam.agar.hackathon.api.game.socket.mapper.WebSocketModelMapper
import ua.com.epam.agar.hackathon.api.game.socket.model.remote.WebSocketModel
import ua.com.epam.agar.hackathon.api.game.socket.model.remote.data.MapStateModel
import ua.com.epam.agar.hackathon.api.game.storage.Storage
import ua.com.epam.agar.hackathon.api.game.storage.map.ComparatorMapStorage
import ua.com.epam.agar.hackathon.api.game.storage.map.LocalMapStateStorage
import ua.com.epam.agar.hackathon.api.game.storage.map.TimeLoggingMapStorage
import ua.com.epam.agar.hackathon.core.repository.GameRepository
import ua.com.epam.agar.hackathon.data.mapper.config.CellConfigMapper
import ua.com.epam.agar.hackathon.data.mapper.config.FoodConfigMapper
import ua.com.epam.agar.hackathon.data.mapper.config.GameConfigMapper
import ua.com.epam.agar.hackathon.data.mapper.config.MapConfigMapper
import ua.com.epam.agar.hackathon.data.mapper.map_state.MapStateMapper
import ua.com.epam.agar.hackathon.data.mapper.map_state.cell.AlienCellMapper
import ua.com.epam.agar.hackathon.data.mapper.map_state.cell.CellMapper
import ua.com.epam.agar.hackathon.data.mapper.map_state.cell.MyCellMapper
import ua.com.epam.agar.hackathon.data.mapper.map_state.desired_state.CellActivityMapper
import ua.com.epam.agar.hackathon.data.mapper.map_state.desired_state.DesiredCellStateMapper
import ua.com.epam.agar.hackathon.data.mapper.map_state.food.FoodMapper
import ua.com.epam.agar.hackathon.api.game.socket.model.local.LocalMapStateModel

val kodeinContainer = DI.lazy {
    importAll(DataModule.values().map { it.diModule }
//        repositoriesModule,
//        mappersModule,
//        interactorModule,
//        webSocketModule,
//
//        mapStorageTimerModule,//replace with the code bellow
////        mapStorageModule
    )
}

enum class DataModule(moduleName: String, init: DI.Builder.() -> Unit) {
    MapStorageTimer("mapStorageModule", {
        bind<Storage<LocalMapStateModel>>("internal") with singleton { LocalMapStateStorage() }
        bind<Storage<MapStateModel>>("real") with singleton {
            ComparatorMapStorage(
                storage = instance("internal"),
                mapper = instance()
            )
        }
        bind<Storage<MapStateModel>>() with factory {
            TimeLoggingMapStorage(
                storage = instance("real")
            )
        }
    }),
//    MapStorage("mapStorageModule", {
//        bind<Storage<LocalMapStateModel>>("internal") with singleton { LocalMapStateStorage() }
//        bind<Storage<MapStateModel>>() with singleton {
//            ComparatorMapStorage(
//                storage = instance("internal")
//            )
//        }
//    });


    Repositories("repositories", {
        bind<RemoteGameDataRepository>() with singleton {
            GameWebSocketAsAPI(webSocket = instance())
        }
        bind<CachingGameDataRepository>() with factory {
            GameDataCachingRepository(
                gameDataRepository = instance(),
                mapStorage = instance(),
                tickHandler = instance()
            )
        }

        bind<GameTickHandler>() with factory {
            DefaultGameTickHandler(webSocket = instance())
        }
    }),
    Interactor("interactor", {
        bind<GameRepository>() with factory {
            GameWebSocketInteractor(
                gameDataRepository = instance(),
                gameConfigMapper = instance(),
                mapStateMapper = instance(),
                desiredCellStateMapper = instance()
            )
        }
    }),
    Mappers("mappers", {
        bind<WebSocketModelMapper>() with factory { ua.com.epam.agar.hackathon.api.game.socket.mapper.WebSocketModelMapper() }

        bind<MyCellMapper>() with factory { MyCellMapper() }
        bind<AlienCellMapper>() with factory { AlienCellMapper() }
        bind<CellMapper>() with factory {
            CellMapper(
                myCellMapper = instance(),
                alienCellMapper = instance()
            )
        }

        bind<FoodMapper>() with factory { FoodMapper() }
        bind<MapStateMapper>() with factory {
            MapStateMapper(
                cellMapper = instance(),
                foodMapper = instance()
            )
        }

        bind<CellConfigMapper>() with factory { CellConfigMapper() }
        bind<MapConfigMapper>() with factory { MapConfigMapper() }
        bind<FoodConfigMapper>() with factory { FoodConfigMapper() }
        bind<GameConfigMapper>() with factory {
            GameConfigMapper(
                cellConfigMapper = instance(),
                mapConfigMapper = instance(),
                foodConfigMapper = instance()
            )
        }

        bind<CellActivityMapper>() with factory { CellActivityMapper() }
        bind<DesiredCellStateMapper>() with factory {
            DesiredCellStateMapper(mapper = instance())
        }
        bind<ua.com.epam.agar.hackathon.api.game.socket.model.local.LocalMapStateModelMapper>() with factory { ua.com.epam.agar.hackathon.api.game.socket.model.local.LocalMapStateModelMapper() }
    }),
    WebSocket("webSocket", {
        bind<ua.com.epam.agar.hackathon.api.game.socket.WebSocket<WebSocketModel>>() with singleton {
            KtorWebSocket(
                host = "45.77.67.171",
                modelMapper = instance()
            )
        }
    });

    val diModule: DI.Module = DI.Module(name = moduleName, init = init)

}

//val mapStorageTimerModule = DI.Module("mapStorageModule") {
//    bind<Storage<MapStateModel>>("internal") with singleton { LocalMapStateStorage() }
//    bind<Storage<MapStateModel>>("real") with singleton { ComparatorMapStorage(storage = instance("internal")) }
//    bind<Storage<MapStateModel>>() with factory { TimeLoggingMapStorage(storage = instance("real")) }
//}
//val mapStorageModule = DI.Module("mapStorageModule") {
//    bind<Storage<MapStateModel>>("internal") with singleton { LocalMapStateStorage() }
//    bind<Storage<MapStateModel>>() with singleton { ComparatorMapStorage(storage = instance("internal")) }
//}
//
//val repositoriesModule = DI.Module("repositories") {
//    bind<RemoteGameDataRepository>() with singleton { GameWebSocketAsAPI(webSocket = instance()) }
//    bind<CachingGameDataRepository>() with factory {
//        GameDataCachingRepository(
//            gameDataRepository = instance(),
//            mapStorage = instance(),
//            tickHandler = instance()
//        )
//    }
//
//    bind<GameTickHandler>() with factory { DefaultGameTickHandler(webSocket = instance()) }
//}
//
//val interactorModule = DI.Module("interactor") {
//    bind<GameRepository>() with factory {
//        GameWebSocketInteractor(
//            gameDataRepository = instance(),
//            gameConfigMapper = instance(),
//            mapStateMapper = instance(),
//            desiredCellStateMapper = instance()
//        )
//    }
//}
//
//val mappersModule = DI.Module("ç") {
//    bind<WebSocketModelMapper>() with factory { WebSocketModelMapper() }
//
//    bind<MyCellMapper>() with factory { MyCellMapper() }
//    bind<AlienCellMapper>() with factory { AlienCellMapper() }
//    bind<CellMapper>() with factory { CellMapper(myCellMapper = instance(), alienCellMapper = instance()) }
//
//    bind<FoodMapper>() with factory { FoodMapper() }
//    bind<MapStateMapper>() with factory { MapStateMapper(cellMapper = instance(), foodMapper = instance()) }
//
//    bind<CellConfigMapper>() with factory { CellConfigMapper() }
//    bind<MapConfigMapper>() with factory { MapConfigMapper() }
//    bind<FoodConfigMapper>() with factory { FoodConfigMapper() }
//    bind<GameConfigMapper>() with factory {
//        GameConfigMapper(
//            cellConfigMapper = instance(),
//            mapConfigMapper = instance(),
//            foodConfigMapper = instance()
//        )
//    }
//
//    bind<CellActivityMapper>() with factory { CellActivityMapper() }
//    bind<DesiredCellStateMapper>() with factory { DesiredCellStateMapper(mapper = instance()) }
//}
//
//val webSocketModule = DI.Module("webSocket") {
//
//    bind<WebSocket<WebSocketModel>>() with singleton {
//        KtorWebSocket(
//            host = "45.77.67.171",
//            modelMapper = instance()
//        )
//    }
//}