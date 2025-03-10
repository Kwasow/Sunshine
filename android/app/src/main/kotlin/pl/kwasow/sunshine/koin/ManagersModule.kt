package pl.kwasow.sunshine.koin

import org.koin.dsl.module
import pl.kwasow.sunshine.managers.AudioManager
import pl.kwasow.sunshine.managers.AudioManagerImpl
import pl.kwasow.sunshine.managers.LocationManager
import pl.kwasow.sunshine.managers.LocationManagerImpl
import pl.kwasow.sunshine.managers.MemoriesManager
import pl.kwasow.sunshine.managers.MemoriesManagerImpl
import pl.kwasow.sunshine.managers.MessagingManager
import pl.kwasow.sunshine.managers.MessagingManagerImpl
import pl.kwasow.sunshine.managers.NotificationManager
import pl.kwasow.sunshine.managers.NotificationManagerImpl
import pl.kwasow.sunshine.managers.PermissionManager
import pl.kwasow.sunshine.managers.PermissionManagerImpl
import pl.kwasow.sunshine.managers.PlaybackManager
import pl.kwasow.sunshine.managers.PlaybackManagerImpl
import pl.kwasow.sunshine.managers.RequestManager
import pl.kwasow.sunshine.managers.RequestManagerImpl
import pl.kwasow.sunshine.managers.SettingsManager
import pl.kwasow.sunshine.managers.SettingsManagerImpl
import pl.kwasow.sunshine.managers.SystemManager
import pl.kwasow.sunshine.managers.SystemManagerImpl
import pl.kwasow.sunshine.managers.TokenManager
import pl.kwasow.sunshine.managers.TokenManagerImpl
import pl.kwasow.sunshine.managers.UserManager
import pl.kwasow.sunshine.managers.UserManagerImpl
import pl.kwasow.sunshine.managers.WishlistManager
import pl.kwasow.sunshine.managers.WishlistManagerImpl

val managersModule =
    module {
        single<AudioManager> {
            AudioManagerImpl(get(), get())
        }

        single<LocationManager> {
            LocationManagerImpl(get(), get(), get())
        }

        single<MemoriesManager> {
            MemoriesManagerImpl(get(), get())
        }

        single<MessagingManager> {
            MessagingManagerImpl(get(), get())
        }

        single<NotificationManager> {
            NotificationManagerImpl(get())
        }

        single<PermissionManager> {
            PermissionManagerImpl(get())
        }

        single<PlaybackManager> {
            PlaybackManagerImpl(get(), get())
        }

        single<RequestManager> {
            RequestManagerImpl(get())
        }

        single<SettingsManager> {
            SettingsManagerImpl(get())
        }

        single<SystemManager> {
            SystemManagerImpl(get())
        }

        single<TokenManager> {
            TokenManagerImpl()
        }

        single<UserManager> {
            UserManagerImpl(get(), get(), get(), get())
        }

        single<WishlistManager> {
            WishlistManagerImpl(get())
        }
    }
