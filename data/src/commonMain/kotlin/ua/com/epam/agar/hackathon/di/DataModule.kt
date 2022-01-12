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
import ua.com.epam.agar.hackathon.api.Host
import ua.com.epam.agar.hackathon.api.game.socket.model.local.LocalMapStateModelMapper
import ua.com.epam.agar.hackathon.api.game.socket.ktor.SocketModelMapper

val kodeinContainer = DI.lazy {
    importAll(DataModule.values().map { it.diModule })
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
        bind<RemoteGameDataRepository>() with multiton { host: Host ->
            GameWebSocketAsAPI(webSocket = instance(arg = host))
        }
        bind<CachingGameDataRepository>() with factory { host: Host ->
            GameDataCachingRepository(
                gameDataRepository = instance(arg = host),
                mapStorage = instance(),
                tickHandler = instance(arg = host)
            )
        }

        bind<GameTickHandler>() with factory { host: Host ->
            DefaultGameTickHandler(webSocket = instance(arg = host))
        }
    }),
    Interactor("interactor", {
        bind<GameRepository>() with factory { host: Host ->
            GameWebSocketInteractor(
                gameDataRepository = instance(arg = host),
                gameConfigMapper = instance(),
                mapStateMapper = instance(),
                desiredCellStateMapper = instance()
            )
        }
    }),
    Mappers("mappers", {
        bind<SocketModelMapper<WebSocketModel>>() with factory { WebSocketModelMapper() }

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
        bind<LocalMapStateModelMapper>() with factory { LocalMapStateModelMapper() }
    }),
    WebSocket("webSocket", {
        bind<ua.com.epam.agar.hackathon.api.game.socket.WebSocket<WebSocketModel>>() with multiton { host: Host ->
            KtorWebSocket(
                host = host.host,
                modelMapper = instance()
            )
        }
    });

    val diModule: DI.Module = DI.Module(name = moduleName, init = init)

}